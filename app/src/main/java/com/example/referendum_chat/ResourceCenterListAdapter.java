package com.example.referendum_chat;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResourceCenterListAdapter extends RecyclerView.Adapter<ResourceCenterListAdapter.ResourceCenterViewHolder> {
    private List<ResourceCenter> mResourceCenters;
    private Context mContext;

    public ResourceCenterListAdapter(Context context, List<ResourceCenter> resourceCenters) {
        mResourceCenters = resourceCenters;
        mContext = context;
    }
}
