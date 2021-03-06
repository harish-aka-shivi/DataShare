package com.example.root.datashare;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    private static final String MAIN_ACTIVITY_FRAGMENT = "Main_Fragment";
    private static final int VERTICAL_ITEM_SPACE = 20;
    private static final int HORIZONTAL_ITEM_SPACE = 10;
    protected RecyclerView mRecyclerView;
    protected ArrayList<AppDetails> mDataset;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected CustomAdapter mCustomAdapter;
    protected FloatingActionButton mFloatingActionButton;

    public MainActivityFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataSet();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main,container,false);

        mFloatingActionButton = (FloatingActionButton) rootView.findViewById(R.id.fab);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_ITEM_SPACE));
        mRecyclerView.addItemDecoration(new HorizontalSpaceItemDecoration(HORIZONTAL_ITEM_SPACE));
        mCustomAdapter = new CustomAdapter(mDataset);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mCustomAdapter);

        // click Listener for Floating Action Button
        mFloatingActionButton.setOnClickListener(new FloatingActionButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Uri> listAppUri = new ArrayList<Uri>();
                for(int i = 0; i < mDataset.size(); i++) {
                    AppDetails singleApp = mDataset.get(i);
                    if (singleApp.getCheckBoxSelected()) {
                        String filePath = mDataset.get(i).getAppLocation();
                        Uri pathUri = Uri.fromFile(new File(filePath));
                        listAppUri.add(pathUri);
                    }
                }
                if (listAppUri.size() == 0) {
                    Toast.makeText(getActivity(),R.string.no_selection,Toast.LENGTH_SHORT).show();
                } else {
                    Intent sendIntent = new Intent(Intent.ACTION_SEND_MULTIPLE);
                    sendIntent.setType("*/*");

                    //Uncomment if specific applications are needed
                    //sendIntent.setType("application/nd.android.package-archive");

                    sendIntent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, listAppUri);
                    startActivity(Intent.createChooser(sendIntent, "DataShare"));
                }
            }
        });
        return rootView;
    }

    // Initializing DataSet with AppDetails object
    private void initDataSet() {
        mDataset = new ArrayList<>();
        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        final List<ResolveInfo> AppList = getContext().getPackageManager().queryIntentActivities
                (mainIntent,0);

        for (ResolveInfo resolveInfo:AppList) {
            String appName = resolveInfo.activityInfo.applicationInfo.
                    loadLabel(getContext().getPackageManager()).toString();
            Drawable appIconDrawable = resolveInfo.activityInfo.
                    applicationInfo.loadIcon(getContext().getPackageManager());
            String appLocation = resolveInfo.activityInfo.applicationInfo.publicSourceDir;
            mDataset.add(new AppDetails(appName,appIconDrawable,appLocation));
        }
    }

    /*
     Adding vertical spaces between CardViews
    */
    public class VerticalSpaceItemDecoration extends RecyclerView.ItemDecoration {
        private final int mVerticalSpaceHeight;

        public VerticalSpaceItemDecoration(int mVerticalSpaceHeight) {
            this.mVerticalSpaceHeight = mVerticalSpaceHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            outRect.bottom = mVerticalSpaceHeight;
        }
    }

    /*
    Adding horizontal space in RecyclerView
    */
    public class HorizontalSpaceItemDecoration extends RecyclerView.ItemDecoration {
        private final int mHorizontalSpaceHeight;

        public HorizontalSpaceItemDecoration(int mHorizontalSpaceHeight) {
            this.mHorizontalSpaceHeight = mHorizontalSpaceHeight;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            outRect.left = mHorizontalSpaceHeight;
            outRect.right = mHorizontalSpaceHeight;
        }

    }
}
