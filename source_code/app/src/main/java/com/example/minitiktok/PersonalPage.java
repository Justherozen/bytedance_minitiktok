package com.example.minitiktok;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.bumptech.glide.Glide;
import com.example.minitiktok.R;
import com.example.minitiktok.database.UserCollectionDao;
import com.example.minitiktok.database.UserCollectionDatabase;
import com.example.minitiktok.database.UserCollectionEntity;
import com.example.minitiktok.tool.BaseFragment;
import com.example.minitiktok.tool.CircleImageView;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscription;
import rx.functions.Action1;

public class PersonalPage extends Fragment{
    List<UserCollectionEntity> list= new ArrayList<UserCollectionEntity>();
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_home, container, false);
        RecyclerView collectview= view.findViewById(R.id.collect_video);
        collectview.setLayoutManager(new LinearLayoutManager(getContext()));
        collectview.setAdapter(new RecyclerView.Adapter() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = inflater.inflate(R.layout.video_info, container, false);
                ImageView iv = view.findViewById(R.id.iv_video_cover);
                iv.setAdjustViewBounds(true);
                return new RecyclerView.ViewHolder(view) {
                };
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
                new Thread()
                {
                    @Override
                    public void run() {
                        UserCollectionDao dao= UserCollectionDatabase.inst(getContext()).userCollectionDao();
                        list= dao.loadAll();
                    }
                }.start();
                View view = viewHolder.itemView;
                ImageView iv = view.findViewById(R.id.iv_video_cover);
                String url = list.get(i).imagecoverUri;
                Glide.with(iv.getContext()).load(url).into(iv);
                ImageView iv_avatar = view.findViewById(R.id.home_iv_avatar);
                iv_avatar.setImageResource(R.drawable.session_robot);
                TextView tv = view.findViewById(R.id.tv_author);
                tv.setText(list.get(i).UserId);
//                TextView tvDate = view.findViewById(R.id.tv_date);//没有date信息
//                tvDate.setText(feeds.get(i).getCreateAt());
                final int finalIconId = R.drawable.session_robot;
                iv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), VideoPlayer.class);
                        intent.putExtra("video_path", list.get(i).videoUri);
                        intent.putExtra("author", "unknown");
                        intent.putExtra("head", finalIconId);
                        intent.putExtra("image_cover", list.get(i).imagecoverUri);
                        intent.putExtra("id", list.get(i).imagecoverUri);
                        startActivity(intent);
                    }
                });
            }
            @Override public int getItemCount() {
                return list.size();
            }
        });
        return view;
    }


}
