package com.example.minitiktok;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.minitiktok.database.UserCollectionDao;
import com.example.minitiktok.database.UserCollectionDatabase;
import com.example.minitiktok.database.UserCollectionEntity;
import com.example.minitiktok.tool.FullScreenVideoView;

public class VideoPlayer extends AppCompatActivity {
    private long time = 0;
    private FullScreenVideoView videoView;
    private TextView tvAuthor;
    private ImageView headicon;
    private TextView dianzan;
    private LottieAnimationView like;


    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        videoView = findViewById(R.id.video_view);
        tvAuthor = findViewById(R.id.player_tv_author);
        headicon = findViewById(R.id.player_iv_headicon);
        dianzan = findViewById(R.id.tv_dz);
        dianzan.bringToFront();
        like = findViewById(R.id.like);
        videoView.setVideoPath(getIntent().getStringExtra("video_path"));
        videoView.setMediaController(new MediaController(this));
        tvAuthor.setText("@" + getIntent().getStringExtra("author"));
        headicon.setImageResource(getIntent().getIntExtra("head", 0));
        like.setVisibility(View.GONE);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                videoView.start();
            }
        });
//        videoView.setOnTouchListener(new OnDoubleClickListener(new OnDoubleClickListener.DoubleClickCallback(){
//
//            @Override
//            public void onDoubleClick() {
//                Toast.makeText(VideoPlayer.this, "双击", Toast.LENGTH_LONG).show();
//                like.setVisibility(View.VISIBLE);
//                handler.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        like.setVisibility(View.GONE);
//                    }
//                }, 1000);
//            }
//        }));
        dianzan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                like.setVisibility(View.VISIBLE);
                new Thread()
                {
                    @Override
                    public void run() {
                        UserCollectionDao dao= UserCollectionDatabase.inst(getApplicationContext()).userCollectionDao();
                        String videoUri,ImageUri,userid;
                        userid=getIntent().getStringExtra("id");
                        ImageUri=getIntent().getStringExtra("image_cover");
                        videoUri=getIntent().getStringExtra("video_path");
                        dao.addCollectVideo(new UserCollectionEntity(userid,ImageUri,videoUri));
                        try {
                            sleep(1000);
                            like.setVisibility(View.GONE);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });
        //加入mediacontroller之后，长按时间就不管用了
//        videoView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                like.setVisibility(View.VISIBLE);
//                handler.postDelayed(new Runnable() {
//                   @Override
//                    public void run() {
//                        like.setVisibility(View.GONE);
//                    }
//                }, 1000);
//                return true;
//            }
//        });
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(videoView.isPlaying())
                {
                    videoView.pause();
                }
                else
                {
                    videoView.start();
                }
            }
        });
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        handler.removeCallbacksAndMessages(this);
    }
}
