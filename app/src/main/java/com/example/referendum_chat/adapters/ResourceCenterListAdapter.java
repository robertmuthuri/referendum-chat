package com.example.referendum_chat.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.referendum_chat.R;
import com.example.referendum_chat.models.ResourceCenter;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ResourceCenterListAdapter extends RecyclerView.Adapter<ResourceCenterListAdapter.ResourceCenterViewHolder> {
    private List<ResourceCenter> mResourceCenters;
    private Context mContext;

    public ResourceCenterListAdapter(Context context, List<ResourceCenter> resourceCenters) {
        mContext = context;
        mResourceCenters = resourceCenters;
    }
    // onCreateViewHolder method
    @Override public ResourceCenterListAdapter.ResourceCenterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rc_list_item, parent, false);
        ResourceCenterViewHolder resourceCenterViewHolder = new ResourceCenterViewHolder(view);
        return resourceCenterViewHolder;
    }
    // onBindViewHolder method
    @Override public void onBindViewHolder(ResourceCenterListAdapter.ResourceCenterViewHolder holder, int position) {
        holder.bindResourceCenter(mResourceCenters.get(position));
    }
    // getItemCount method
    @Override public int getItemCount() { return mResourceCenters.size(); }

    // ViewHolder
    public class ResourceCenterViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.rcImageView) ImageView mRcImageView;
        @BindView(R.id.rcNameTextView) TextView mRcNameTextView;
        @BindView(R.id.rcLocationTextView) TextView mRcLocationTextView;
        @BindView(R.id.rcWebsiteTextView) TextView mRcWebsiteTextView;

        private Context mContext;

        public ResourceCenterViewHolder (View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindResourceCenter(ResourceCenter resourceCenter) {
            Picasso.get().load(resourceCenter.getImageUrl()).into(mRcImageView);
            mRcNameTextView.setText(resourceCenter.getName());
            mRcLocationTextView.setText(resourceCenter.getLocation());
            mRcWebsiteTextView.setText(resourceCenter.getUrl());
        }
    }
}
