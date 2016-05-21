package com.happyshop.adapter;

import android.content.Context;
import android.content.ContextWrapper;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.happyshop.R;
import com.happyshop.helper.VolleyHelper;
import com.happyshop.model.CategoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by terril1 on 20/05/16.
 */
public class CategorListAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CategoryModel> mDataset;
    Context mContext;

    public CategorListAdapter(Context context, List<CategoryModel> myDataSet) {
        mDataset = myDataSet;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_category_list, parent, false);

        vh = new CustomViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CustomViewHolder) {
            ((CustomViewHolder) holder).lblTitle.setText(mDataset.get(position).getName());
            ((CustomViewHolder) holder).lblPricing.setText(mDataset.get(position).getPrice());
            ((CustomViewHolder) holder).imvImageData.setImageUrl(mDataset.get(position).getImgUrl(), new VolleyHelper(mContext).getImageLoader());
            ;

            if (mDataset.get(position).getUnderSale()) {
                ((CustomViewHolder) holder).lblStatus.setVisibility(View.VISIBLE);
            }else{
                ((CustomViewHolder) holder).lblStatus.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        public TextView lblTitle, lblPricing, lblStatus;
        public NetworkImageView imvImageData;

        public CustomViewHolder(View v) {
            super(v);
            lblTitle = (TextView) v.findViewById(R.id.lblTitle);
            lblPricing = (TextView) v.findViewById(R.id.lblPricing);
            lblStatus = (TextView) v.findViewById(R.id.lblStatus);
            imvImageData = (NetworkImageView) v.findViewById(R.id.imvDataImage);
        }
    }
}
