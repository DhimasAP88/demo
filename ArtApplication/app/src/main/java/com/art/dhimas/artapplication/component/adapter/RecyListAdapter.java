package com.art.dhimas.artapplication.component.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.art.dhimas.artapplication.R;
import com.art.dhimas.artapplication.component.network.gson.GArtObject;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jakewharton.rxbinding.view.RxView;

import java.util.ArrayList;
import java.util.List;

public class RecyListAdapter extends RecyclerView.Adapter<RecyListAdapter.ViewHolder>{
    private OnClickItem onClickItem;
    private List<GArtObject> listItem;

    public RecyListAdapter(OnClickItem onClickItem){
        listItem = new ArrayList<>();
        this.onClickItem = onClickItem;
    }

    public void setData(List<GArtObject> listArt) {
        listItem = listArt;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyListAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final GArtObject object = listItem.get(position);
        Glide.with(holder.itemView.getContext()).load(object.webImage.url)
                .fitCenter()
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.loading.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.img);

        holder.title.setText(object.title);

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickItem != null) {
                    onClickItem.onClick(object.title, object.webImage.url);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView img;
        private TextView title;
        private LinearLayout container;
        private ProgressBar loading;

        public ViewHolder(View itemView){
            super(itemView);
            img = itemView.findViewById(R.id.item_list_image);
            title = itemView.findViewById(R.id.title_item);
            container = itemView.findViewById(R.id.container_item_list);
            loading = itemView.findViewById(R.id.progressBar);
        }
    }

    public interface OnClickItem {
        void onClick(String title, String imgUrl);
    }
}
