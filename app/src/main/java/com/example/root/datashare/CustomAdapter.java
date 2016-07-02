package com.example.root.datashare;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by root on 1/7/16.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<AppDetails> mDataSet;
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mImageView;
        private final TextView mTextView;
        private final CheckBox mCheckBox;

        public ViewHolder(View view) {
           super(view);
            mImageView = (ImageView) view.findViewById(R.id.shareImageView);
            mCheckBox = (CheckBox) view.findViewById(R.id.shareCheckbox);
            mTextView = (TextView) view.findViewById(R.id.shareTextView);
        }

        public CheckBox getCheckBox() {
            return mCheckBox;
        }

        public ImageView getImageView() {
            return mImageView;
        }

        public TextView getTextView() {
            return mTextView;
        }
    }

    public CustomAdapter(ArrayList<AppDetails> dataset) {
        mDataSet = dataset;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_share,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getTextView().setText(mDataSet.get(position).getAppName());
        holder.getImageView().setImageResource(R.drawable.art_clear);
        holder.getCheckBox().setChecked(false);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}