package com.example.minitiktok.tool;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.minitiktok.MainActivity;
import com.example.minitiktok.R;
import com.example.minitiktok.VideoPlayer;
import com.example.minitiktok.tool.VideoBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GridVideoAdapter extends BaseRvAdapter<VideoBean, GridVideoAdapter.GridVideoViewHolder> {

    public GridVideoAdapter(Context context, List<VideoBean> datas) {
        super(context, datas);
    }

    @Override
    protected void onBindData(GridVideoViewHolder holder, VideoBean videoBean, int position) {
        holder.ivCover.setBackgroundResource(videoBean.getCoverRes());
        holder.tvContent.setText(videoBean.getContent());
        ///holder.tvDistance.setText("666km");
        holder.ivHead.setImageResource(videoBean.getUserBean().getHead());

        holder.itemView.setOnClickListener(v -> {
            //videoBean.getVideoRes()返回视频内容
            Intent intent=new Intent(context, VideoPlayer.class);
            intent.putExtra("video_path", "android.resource://com.example.minitiktok" + "/"+videoBean.getVideoRes());
            intent.putExtra("author", videoBean.getContent());
            intent.putExtra("head", videoBean.getUserBean().getHead());
            context.startActivity(intent);
        });
    }

    @NonNull
    @Override
    public GridVideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_gridvideo, parent, false);
        return new GridVideoViewHolder(view);
    }

    public class GridVideoViewHolder extends BaseRvViewHolder {
        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_content)
        TextView tvContent;
        //@BindView(R.id.tv_distance)
        //IconFontTextView tvDistance;
        @BindView(R.id.iv_head)
        ImageView ivHead;

        public GridVideoViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
