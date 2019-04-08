package com.example.manabie.view;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.manabie.R;
import com.example.manabie.event.OnItemClick;
import com.example.manabie.resources.model.ManabieItem;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Actual ListAdapter build on Diff Util technical, we should apply it for reuse animation
 */
public class AdapterManabie extends ListAdapter<ManabieItem, AdapterManabie.ManabieHolder> {
    private OnItemClick<ManabieItem> mCallback;

    public void setOnClickListener(OnItemClick<ManabieItem> callBack) {
        mCallback = callBack;
    }

    public AdapterManabie() {
        super(DIFF_CALLBACK);
    }

    /**
     * Using Diff Util to compare more list and combine animation
     *
     */
    private static final DiffUtil.ItemCallback<ManabieItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<ManabieItem>() {
        @Override
        public boolean areItemsTheSame(ManabieItem oldItem, ManabieItem newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(ManabieItem oldItem, ManabieItem newItem) {
            return oldItem.getId() == (newItem.getId()) &&
                    oldItem.getBackgroundColor() == (newItem.getBackgroundColor()) &&
                    oldItem.getCounter() == newItem.getCounter();
        }
    };

    @NonNull
    @Override
    public ManabieHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_manabie, viewGroup, false);
        return new ManabieHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManabieHolder manabieHolder, int i) {
        ManabieItem manabie = getItem(i);

        manabieHolder.manabieItem.setText(String.valueOf(manabie.getCounter()));
        manabieHolder.manabieItem.setBackgroundColor(manabie.getBackgroundColor());

    }

    protected class ManabieHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.manabieItem)
        Button manabieItem;

        public ManabieHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            manabieItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCallback != null) {
                        mCallback.onClick(getItem(getAdapterPosition()), getAdapterPosition());
                    }
                }
            });

        }
    }
}
