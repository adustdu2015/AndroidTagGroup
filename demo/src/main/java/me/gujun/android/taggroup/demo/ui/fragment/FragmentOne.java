package me.gujun.android.taggroup.demo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.gujun.android.taggroup.demo.R;
import me.gujun.android.taggroup.demo.ReAdapter;

public class FragmentOne extends Fragment{
    RecyclerView one ;
    RecyclerView two ;
    ReAdapter reAdapter;
    ReAdapter reAdapterTwo;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =getLayoutInflater().inflate(R.layout.fragment_container,container,false);
        one = view.findViewById(R.id.rv_one);
        two = view.findViewById(R.id.rv_two);
        one.setLayoutManager(new LinearLayoutManager(getActivity()));
        two.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        reAdapter = new ReAdapter(getActivity());
        reAdapterTwo = new ReAdapter(getActivity());
        one.setAdapter(reAdapter);
        two.setAdapter(reAdapterTwo);
        List<String> list = new ArrayList<>();
        list.add("11111");
        list.add("11111");
        list.add("11111");
        list.add("11111");
        list.add("11111");
        list.add("11111");
        list.add("11111");
        list.add("11111");
        list.add("11111");
        reAdapter.addData(list);
        reAdapterTwo.addData(list);
        return view;
    }
}
