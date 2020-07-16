package com.example.minitiktok;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.net.Uri;
import com.example.minitiktok.api.IMiniDouyinService;
import com.example.minitiktok.model.PostVideoResponse;
import com.example.minitiktok.tool.ResourceUtils;


import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private RelativeLayout relativelayout;
    private TextView tv_home;
    private ImageView iv_home;
    private ImageView iv_shoot;
    private TextView tv_msg;
    private ImageView iv_msg;
    private TextView tv_city;
    private ImageView iv_city;
    private TextView tv_person;
    private ImageView iv_person;
    private Button post,cancel;
    private int REQUEST_CODE_RECORD_VIDEO=666;
    private static final int PICK_IMAGE = 1;
    public Uri mSelectedImage;
    private Uri mSelectedVideo;
    private String customid="19883151841";
    private String customname="jianzihao";
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(IMiniDouyinService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    private IMiniDouyinService miniDouyinService = retrofit.create(IMiniDouyinService.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //getSupportActionBar().hide();
        getSupportFragmentManager().beginTransaction().replace(R.id.relativelayout, new Home()).commit();
    }

    private void initView() {
        relativelayout = (RelativeLayout) findViewById(R.id.relativelayout);
        tv_home = (TextView) findViewById(R.id.tv_home);
        tv_home.setOnClickListener(this);
        iv_home = (ImageView) findViewById(R.id.iv_home);
        iv_shoot = (ImageView) findViewById(R.id.iv_shoot);
        iv_shoot.setOnClickListener(this);
        tv_msg = (TextView) findViewById(R.id.tv_msg);
        iv_msg = (ImageView) findViewById(R.id.iv_msg);
        tv_msg.setOnClickListener(this);
        tv_city = (TextView) findViewById(R.id.tv_city);
        iv_city = (ImageView) findViewById(R.id.iv_city);
        tv_city.setOnClickListener(this);
        tv_person = (TextView) findViewById(R.id.tv_person);
        iv_person = (ImageView) findViewById(R.id.iv_person);
        tv_person.setOnClickListener(this);
        post=findViewById(R.id.confirm_post);
        post.setOnClickListener(this);
        post.setClickable(false);
        post.setVisibility(View.INVISIBLE);
        cancel=findViewById(R.id.cancel_post);
        cancel.setOnClickListener(this);
        cancel.setClickable(false);
        cancel.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.relativelayout, new Home()).commit();
                iv_msg.setBackgroundResource(R.drawable.linen);
                tv_msg.setTextColor(Color.parseColor("#ffffff"));
                iv_person.setBackgroundResource(R.drawable.linen);
                tv_person.setTextColor(Color.parseColor("#ffffff"));
                iv_city.setBackgroundResource(R.drawable.linen);
                tv_city.setTextColor(Color.parseColor("#ffffff"));
                iv_home.setBackgroundResource(R.drawable.linew);
                tv_home.setTextColor(Color.parseColor("#55c7bb"));
                break;
            case R.id.iv_shoot:
                Intent videointent=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if(videointent.resolveActivity(getPackageManager())!=null)
                {
                    startActivityForResult(videointent,REQUEST_CODE_RECORD_VIDEO);
                }
                break;
            case R.id.tv_msg:
                getSupportFragmentManager().beginTransaction().replace(R.id.relativelayout, new News()).commit();
                iv_home.setBackgroundResource(R.drawable.linen);
                tv_home.setTextColor(Color.parseColor("#ffffff"));
                iv_person.setBackgroundResource(R.drawable.linen);
                tv_person.setTextColor(Color.parseColor("#ffffff"));
                iv_city.setBackgroundResource(R.drawable.linen);
                tv_city.setTextColor(Color.parseColor("#ffffff"));
                iv_msg.setBackgroundResource(R.drawable.linew);
                tv_msg.setTextColor(Color.parseColor("#55c7bb"));
                break;
            case R.id.tv_city:
                iv_msg.setBackgroundResource(R.drawable.linen);
                tv_msg.setTextColor(Color.parseColor("#ffffff"));
                iv_person.setBackgroundResource(R.drawable.linen);
                tv_person.setTextColor(Color.parseColor("#ffffff"));
                iv_home.setBackgroundResource(R.drawable.linen);
                tv_home.setTextColor(Color.parseColor("#ffffff"));
                iv_city.setBackgroundResource(R.drawable.linew);
                tv_city.setTextColor(Color.parseColor("#55c7bb"));
                break;
            case R.id.tv_person:
                getSupportFragmentManager().beginTransaction().replace(R.id.relativelayout, new PersonalPage()).commit();
                iv_home.setBackgroundResource(R.drawable.linen);
                tv_home.setTextColor(Color.parseColor("#ffffff"));
                iv_msg.setBackgroundResource(R.drawable.linen);
                tv_msg.setTextColor(Color.parseColor("#ffffff"));
                iv_city.setBackgroundResource(R.drawable.linen);
                tv_city.setTextColor(Color.parseColor("#ffffff"));
                iv_person.setBackgroundResource(R.drawable.linew);
                tv_person.setTextColor(Color.parseColor("#55c7bb"));
                break;
            case R.id.cancel_post:
                post.setClickable(false);
                post.setVisibility(View.INVISIBLE);
                cancel.setClickable(false);
                cancel.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this,"post canceled", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.confirm_post:
                postVideo();
                post.setClickable(false);
                post.setVisibility(View.INVISIBLE);
                cancel.setClickable(false);
                cancel.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this,"post success", Toast.LENGTH_SHORT)
                        .show();
                getSupportFragmentManager().beginTransaction().replace(R.id.relativelayout, new Home()).commit();
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestedCode,int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestedCode,resultCode,data);

        Log.d(TAG, "onActivityResult() called with: requestCode = ["
                + requestedCode
                + "], resultCode = ["
                + resultCode
                + "], data = ["
                + data
                + "]");
        if(requestedCode==REQUEST_CODE_RECORD_VIDEO&&resultCode==RESULT_OK)
        {
            mSelectedVideo=data.getData();
            Toast.makeText(getApplicationContext(),"Select the image cover",Toast.LENGTH_SHORT).show();
            chooseImage();
        }
        else if(requestedCode==PICK_IMAGE&&resultCode==RESULT_OK)
        {
            mSelectedImage=data.getData();
            post.setClickable(true);
            post.setVisibility(View.VISIBLE);
            cancel.setClickable(true);
            cancel.setVisibility(View.VISIBLE);
        }
    }
    public void chooseImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"),
                PICK_IMAGE);
    }
    private void postVideo() {
        MultipartBody.Part coverImagePart = getMultipartFromUri("cover_image", mSelectedImage);
        MultipartBody.Part videoPart = getMultipartFromUri("video", mSelectedVideo);
        //@TODO 4下面的id和名字替换成自己的
        miniDouyinService.postVideo(customid, customname, coverImagePart, videoPart).enqueue(
                new Callback<PostVideoResponse>() {
                    @Override
                    public void onResponse(Call<PostVideoResponse> call, Response<PostVideoResponse> response) {
                        if (response.body() != null) {
                            Toast.makeText(MainActivity.this, response.body().toString(), Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                    @Override
                    public void onFailure(Call<PostVideoResponse> call, Throwable throwable) {
                        Toast.makeText(MainActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private MultipartBody.Part getMultipartFromUri(String name, Uri uri) {
        File f = new File(ResourceUtils.getRealPath(MainActivity.this, uri));
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), f);
        return MultipartBody.Part.createFormData(name, f.getName(), requestFile);
    }
}
