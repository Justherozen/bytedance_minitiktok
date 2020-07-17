package com.example.minitiktok;

import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.minitiktok.tool.GridVideoAdapter;
import com.example.minitiktok.tool.BaseFragment;
import com.example.minitiktok.tool.DataCreate;

import butterknife.BindView;

public class CurrentLocationFragment extends BaseFragment {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    private GridVideoAdapter adapter;

    @BindView(R.id.refreshlayout)
    SwipeRefreshLayout refreshLayout;
    public CurrentLocationFragment() {
        // Required empty public constructor
    }
    @Override
    protected int setLayoutId() {
        return R.layout.fragment_current_location;
    }
    @Override
    protected void init(){
        //申请权限
        //view填充，首页每个view都是全屏
        new DataCreate().initData();
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        adapter = new GridVideoAdapter(getActivity(), DataCreate.datas);
        recyclerView.setAdapter(adapter);
        refreshLayout.setColorSchemeResources(R.color.color_link);
        refreshLayout.setOnRefreshListener(() -> new CountDownTimer(1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                refreshLayout.setRefreshing(false);
            }
        }.start());
    }
}
