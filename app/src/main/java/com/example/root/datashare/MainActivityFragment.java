package com.example.root.datashare;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private static final String MAIN_ACTIVITY_FRAGMENT = "Main_Fragment";

    protected RecyclerView mRecyclerView;
    protected ArrayList<AppDetails> mDataset;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected CustomAdapter mCustomAdapter;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main,container,false);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mCustomAdapter = new CustomAdapter(mDataset);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mCustomAdapter);

        /*Intent sendIntent = new Intent(Intent.ACTION_SEND);
        String filePath = "/data/app/com.lifemaker-1/base.apk";
*/
        // MIME of .apk is "application/vnd.android.package-archive".
        // but Bluetooth does not accept this. Let's use "*/*" instead.
        //sendIntent.setType("*/*");

        // Append file and send Intent
        /*sendIntent.setPackage("com.android.bluetooth");
        sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(filePath)));
        startActivity(Intent.createChooser(sendIntent, "Share app"));
*/
        return rootView;
    }

    private void initDataset() {
        mDataset = new ArrayList<>();
        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        final List<ResolveInfo> AppList = getContext().getPackageManager().queryIntentActivities
                (mainIntent,0);
        for (ResolveInfo resolveInfo:AppList) {
            /*Log.d(MAIN_ACTIVITY_FRAGMENT,resolveInfo.activityInfo.applicationInfo.
                    loadLabel(getContext().getPackageManager()).toString() + " directory ::  " + resolveInfo.
                    activityInfo.applicationInfo.sourceDir);*/
            String appName = resolveInfo.activityInfo.applicationInfo.loadLabel(getContext().getPackageManager()).toString();
             = resolveInfo.activityInfo.applicationInfo.loadIcon(getContext().getPackageManager()).;


        mDataset.add(new AppDetails("LifeMaker",R.drawable.art_clear));

    }
}
