package com.example.minitiktok;
import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.minitiktok.api.IMiniDouyinService;
import com.example.minitiktok.model.GetVideosResponse;
import com.example.minitiktok.model.Video;
import com.example.minitiktok.tool.CommentDialog;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends Fragment {

    private List<Video> feeds = new ArrayList<>();
    private Call<GetVideosResponse> feedCall = null;
    private String[] mPermissionsArrays = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
    private final static int REQUEST_PERMISSION = 123;
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(IMiniDouyinService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private IMiniDouyinService miniDouyinService = retrofit.create(IMiniDouyinService.class);

    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup parent,
                             Bundle savedInstanceState) {
        //申请权限
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            requestPermissions(mPermissionsArrays, REQUEST_PERMISSION);
        }
        //view填充，首页每个view都是全屏
        View view = inflater.inflate(R.layout.fragment_home, parent, false);
        final RecyclerView recyclerView = view.findViewById(R.id.home_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(parent.getContext()));
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = inflater.inflate(R.layout.video_info, parent, false);
                ImageView iv = view.findViewById(R.id.iv_video_cover);
                //iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                iv.setAdjustViewBounds(true);
                return new RecyclerView.ViewHolder(view) {
                };
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
                View view = viewHolder.itemView;
                ImageView iv = view.findViewById(R.id.iv_video_cover);
                String url = feeds.get(i).getImageUrl();
                Glide.with(iv.getContext()).load(url).into(iv);
                ImageView iv_avatar = view.findViewById(R.id.home_iv_avatar);
                iv_avatar.setImageResource(R.drawable.session_robot);
                TextView tv = view.findViewById(R.id.tv_author);
                tv.setText(feeds.get(i).getUserName());
//                TextView tvDate = view.findViewById(R.id.tv_date);//没有date信息
//                tvDate.setText(feeds.get(i).getCreateAt());
                final int finalIconId = R.drawable.session_robot;
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(parent.getContext(), VideoPlayer.class);
                        intent.putExtra("video_path", feeds.get(i).getVideoUrl());
                        intent.putExtra("author", feeds.get(i).getUserName());
                        intent.putExtra("head", finalIconId);
                        intent.putExtra("image_cover", feeds.get(i).getImageUrl());
                        intent.putExtra("id", feeds.get(i).getStudentId());
                        startActivity(intent);
                    }
                });
            }
            @Override public int getItemCount() {
                return feeds.size();
            }
        });


        Call<GetVideosResponse> feedResponseCall = miniDouyinService.getVideos();
        feedCall = feedResponseCall;
        feedResponseCall.enqueue(new Callback<GetVideosResponse>() {
            @Override
            public void onResponse(Call<GetVideosResponse> call, Response<GetVideosResponse> response) {
                feeds = response.body().videos;
                recyclerView.getAdapter().notifyDataSetChanged();
                feedCall = null;
            }

            @Override
            public void onFailure(Call<GetVideosResponse> call, Throwable t) {
                Toast.makeText(parent.getContext(), "获取数据失败", Toast.LENGTH_LONG).show();
                feedCall = null;
            }
        });
        return view;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        //destroy the call when destroy
        if(feedCall != null){
            feedCall.cancel();
        }
    }
    public void showcomment(View view){
        CommentDialog commentDialog = new CommentDialog();
        commentDialog.show(getChildFragmentManager(), "");
    }
}
