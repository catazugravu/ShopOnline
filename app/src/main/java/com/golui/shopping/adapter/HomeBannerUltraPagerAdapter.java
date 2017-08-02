package com.golui.shopping.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.golui.shopping.R;
import com.golui.shopping.models.ProductListItems;

import java.util.List;

/**
 * Created by RAVI on 7/29/2017.
 */

public class HomeBannerUltraPagerAdapter extends RecyclerView.Adapter<HomeBannerUltraPagerAdapter.ViewHolder> {
    private RecyclerView parentRecycler;
    private List<ProductListItems> productListItemses;

    public HomeBannerUltraPagerAdapter(Context context, List<ProductListItems> productListItemses) {
        this.productListItemses = productListItemses;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        parentRecycler = recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.adapter_home_banner_pager, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.IV_banner.setImageResource(productListItemses.get(position).getDrawableRes());
    }

    @Override
    public int getItemCount() {
        return productListItemses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView IV_banner;

        public ViewHolder(View itemView) {
            super(itemView);
            IV_banner = (ImageView) itemView.findViewById(R.id.IV_banner);

        }


        @Override
        public void onClick(View v) {
            parentRecycler.smoothScrollToPosition(getAdapterPosition());
        }
    }

}
