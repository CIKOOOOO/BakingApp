package com.andrew.bakingapp.ui.main.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andrew.bakingapp.R;
import com.andrew.bakingapp.adapter.RecyclerAdapter;
import com.andrew.bakingapp.delegate.OnItemClickListener;
import com.andrew.bakingapp.model.Bake;
import com.andrew.bakingapp.ui.detail.DetailActivity;
import com.andrew.bakingapp.utils.Constant;
import com.google.gson.Gson;

import java.util.List;

public class MainFragment extends Fragment implements OnItemClickListener, MainCallback {

    private static final String TAB_KEY = "tab_key";
    private RecyclerAdapter adapter;
    private Activity mActivity;

    public static MainFragment newInstance(int position) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(TAB_KEY, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = getActivity();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_main_fragment);

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        adapter = new RecyclerAdapter(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(adapter);

        Bundle bundle = getArguments();
        if (bundle != null) {
            int pos = bundle.getInt(TAB_KEY);
            String type = Constant.TAB_NAME[pos];
            viewModel.getReceiptList(type);
            if (pos == 0) {
                viewModel.getMutableLiveData().observe(getViewLifecycleOwner(), bakes -> {
                    adapter.setBakeList(bakes);
                    adapter.notifyDataSetChanged();
                });
            } else {
                viewModel.setCallback(this);
                viewModel.getListLiveData().observe(getViewLifecycleOwner(), viewModel::fetchingData);
            }
        }
    }

    @Override
    public void onItemClick(Bake bake) {
        Gson gson = new Gson();
        String data = gson.toJson(bake);

        Intent intent = new Intent(mActivity, DetailActivity.class);
        intent.putExtra(DetailActivity.DATA, data);
        startActivity(intent);
    }

    @Override
    public void onCallbackFromDB(List<Bake> bakeList) {
        adapter.setBakeList(bakeList);
        adapter.notifyDataSetChanged();
    }
}