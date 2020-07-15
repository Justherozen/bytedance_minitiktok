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

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.androidkun.xtablayout.XTabLayout;
import com.example.minitiktok.R;
import com.example.minitiktok.tool.BaseFragment;
import com.example.minitiktok.tool.CircleImageView;
import com.google.android.material.appbar.AppBarLayout;

import java.util.ArrayList;

import butterknife.BindView;
import rx.Subscription;
import rx.functions.Action1;

/**
 * create by libo
 * create on 2020-05-19
 * description 个人主页fragment
 */
public class PersonalPage extends Fragment{
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal_home, container, false);
        return view;
    }


}
