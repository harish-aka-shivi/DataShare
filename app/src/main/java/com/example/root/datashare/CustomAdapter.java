package com.example.root.datashare;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by root on 1/7/16.
 * Adapter for recycler view.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<AppDetails> mDataSet;

    public CustomAdapter(ArrayList<AppDetails> dataSet) {
        mDataSet = dataSet;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mImageView;
        private final TextView mTextView;
        private final CheckBox mCheckBox;

        public ViewHolder(final View view, final CustomAdapter customAdapter) {
           super(view);

            // handling clicks on the view not on CheckBoK
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int adapterPosition = getAdapterPosition();
                    AppDetails appDetails = customAdapter.mDataSet.get(adapterPosition);
                    String appLocation = appDetails.getAppLocation();
                    Log.d("CUSTOM ADAPTER", appLocation);
                    Uri fileUri = Uri.fromFile(new File(appLocation));
                    Intent sendIntent = new Intent(Intent.ACTION_SEND);

                    // MIME of .apk is "application/vnd.android.package-archive".
                    // but Bluetooth does not accept this. Let's use "*/*" instead.
                    sendIntent.setType("*/*");

                    /*
                    *UnComment to just send it via bluetooth
                     */
                    //sendIntent.setPackage("com.android.bluetooth");

                    sendIntent.putExtra(Intent.EXTRA_STREAM, fileUri);
                    v.getContext().startActivity(Intent.createChooser(sendIntent, "Share app"));
                }
            });
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

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_share,parent,false);
        return new ViewHolder(view,this);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.getTextView().setText(mDataSet.get(position).getAppName());
        holder.getImageView().setImageDrawable(mDataSet.get(position).getIconDrawableId());
        holder.getCheckBox().setChecked(mDataSet.get(position).getCheckBoxSelected());
        holder.getCheckBox().setTag(mDataSet.get(position));
        holder.getCheckBox().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox checkBox = (CheckBox) v;
                AppDetails appDetail = (AppDetails) checkBox.getTag();
                appDetail.setCheckBoxSelected(checkBox.isSelected());
                mDataSet.get(position).setCheckBoxSelected(checkBox.isSelected());

                Toast.makeText(
                        v.getContext(),
                        "Clicked on Checkbox: " + ((AppDetails) checkBox.getTag()).getAppName() + " is "
                                + checkBox.isChecked(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

}