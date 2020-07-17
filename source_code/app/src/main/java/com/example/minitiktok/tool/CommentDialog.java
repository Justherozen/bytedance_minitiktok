package com.example.minitiktok.tool;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.minitiktok.R;
import com.example.minitiktok.tool.CommentAdapter;
import com.example.minitiktok.tool.CommentBean;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentDialog extends BaseBottomSheetDialog {
    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private CommentAdapter commentAdapter;
    private ArrayList<CommentBean> datas = new ArrayList<>();
    private View view;
    private int[] likeArray = new int[]{4919, 334, 121, 423, 221, 23};
    private String[] commentArray = new String[]{"真的太好笑了", "bytedance的老师们辛苦了", "哈哈哈哈", "吼腻害", "23333333","666666"};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_comment, container);
        ButterKnife.bind(this, view);

        init();

        return view;
    }

    private void init() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        commentAdapter = new CommentAdapter(getContext(), datas);

        for (int i = 0; i < DataCreate.userList.size(); i++) {
            CommentBean commentBean = new CommentBean();
            commentBean.setUserBean(DataCreate.userList.get(i));
            commentBean.setContent(commentArray[(int) (Math.random() * commentArray.length)]);
            commentBean.setLikeCount(likeArray[(int) (Math.random() * likeArray.length)]);
            datas.add(commentBean);
        }
        commentAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(commentAdapter);
    }


    @Override
    protected int getHeight() {
        return getResources().getDisplayMetrics().heightPixels - 600;
    }
}
