package com.zaki.retrouser.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zaki.retrouser.R;
import com.zaki.retrouser.adapter.UserAdapter;
import com.zaki.retrouser.model.User;

import java.util.ArrayList;

public class UserListFragment extends Fragment {

    private ArrayList<User> userArrayList=new ArrayList<>();
    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView= inflater.inflate(R.layout.user_list,container,false);
        return rootView;
    }
}
