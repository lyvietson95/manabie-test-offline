package com.example.manabie.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.example.manabie.R;
import com.example.manabie.base.BaseActivity;
import com.example.manabie.event.OnItemClick;
import com.example.manabie.resources.model.ManabieItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ManabieActivity extends BaseActivity implements OnItemClick<ManabieItem> {
    private static final String TAG = ManabieActivity.class.getSimpleName();

    ManabieViewModel manabieViewModel;

    @BindView(R.id.rvManabie)
    RecyclerView rvManabie;

    @BindView(R.id.detailView)
    Button detailView;

    AdapterManabie mAdapterManabie;
    List<ManabieItem> listManabie;

    private int positionDetail = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @OnClick(R.id.detailView)
    public void onCounterDetail() {
        if (listManabie != null && listManabie.size() > 0 && positionDetail != -1) {
            int currentCount = listManabie.get(positionDetail).getCounter();
            currentCount++;

            listManabie.get(positionDetail).setCounter(currentCount);
            mAdapterManabie.notifyItemChanged(positionDetail);

            detailView.setText(String.valueOf(currentCount));
        }
    }

    private void init() {
        ButterKnife.bind(this);
        listManabie = new ArrayList<>();
        mAdapterManabie = new AdapterManabie();
        mAdapterManabie.setOnClickListener(this);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvManabie.setLayoutManager(layoutManager);

        rvManabie.setItemAnimator(new DefaultItemAnimator());
        rvManabie.setAdapter(mAdapterManabie);

        manabieViewModel = ViewModelProviders.of(ManabieActivity.this).get(ManabieViewModel.class);

        // get list Manabie ( assume in this case list Manabie get from network)
        manabieViewModel.getListManabie().observe(this, new Observer<List<ManabieItem>>() {
            @Override
            public void onChanged(@Nullable List<ManabieItem> manabieItems) {
                listManabie.clear();
                listManabie.addAll(manabieItems);
                mAdapterManabie.submitList(manabieItems);
                //mAdapterManabie.notifyDataSetChanged();
            }
        });

    }


    @Override
    public void onClick(ManabieItem item, int position) {
        positionDetail = position;

        detailView.setBackgroundColor(item.getBackgroundColor());
        detailView.setText(String.valueOf(item.getCounter()));
    }
}
