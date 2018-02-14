package com.waracle.androidtest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.waracle.androidtest.R;
import com.waracle.androidtest.manager.AppManager;
import com.waracle.androidtest.model.CakeModel;
import com.waracle.androidtest.network.ImageLoader;

import java.util.ArrayList;

/**
 * Created by arslanlodhi on 2/14/18.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    // Can you think of a better way to represent these items???
    private ArrayList<CakeModel> mItems;
    private ImageLoader mImageLoader;

    public MyAdapter() {
        this(new ArrayList<CakeModel>());
    }

    public MyAdapter(ArrayList<CakeModel> items) {
        mItems = items;
        mImageLoader = new ImageLoader(AppManager.getInstance().getNetworkManager());
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title,desc;
        ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            desc = (TextView) itemView.findViewById(R.id.desc);
            image = (ImageView) itemView.findViewById(R.id.image);
        }

    }



    @Override
    public int getItemCount() {
        return mItems.size();
    }


    public CakeModel getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View root = inflater.inflate(R.layout.list_item_layout, parent, false);
        MyAdapter.ViewHolder viewHolder = new MyAdapter.ViewHolder(root);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(MyAdapter.ViewHolder holder, int position) {
        CakeModel object =  getItem(position);
        holder.title.setText(object.getTitle());
        holder.desc.setText(object.getDescription());
        mImageLoader.load(object.getImage(), holder.image);
    }

    public void setItems(ArrayList<CakeModel> items) {
        mItems = items;
        notifyDataSetChanged();
    }
}