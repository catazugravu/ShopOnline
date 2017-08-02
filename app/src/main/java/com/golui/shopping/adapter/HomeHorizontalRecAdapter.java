package com.golui.shopping.adapter;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.golui.shopping.R;
import com.golui.shopping.listeners.ItemClickedListener;
import com.golui.shopping.models.ProductListItems;

import java.util.List;

/**
 * Created by RAVI on 7/29/2017.
 */

public class HomeHorizontalRecAdapter extends RecyclerView.Adapter<HomeHorizontalRecAdapter.ViewHolder> {
    private RecyclerView parentRecycler;
    private ItemClickedListener itemClickedListener;
    private List<ProductListItems> productListItemses;


    public HomeHorizontalRecAdapter(Context context, List<ProductListItems> productListItemses, ItemClickedListener itemClickedListener) {
        this.itemClickedListener = itemClickedListener;
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
        View v = inflater.inflate(R.layout.adapter_home_horizontal_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.IV_banner.setImageResource(productListItemses.get(position).getDrawableRes());
        holder.TV_item_name.setText(productListItemses.get(position).getName());
        holder.TV_item_price.setText("RS: " + productListItemses.get(position).getPrice());
        ViewCompat.setTransitionName(holder.IV_banner, "Horizontal_position_" + position);
    }

    @Override
    public int getItemCount() {
        return productListItemses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView IV_banner;
        private TextView TV_item_name, TV_item_price;

        public ViewHolder(View itemView) {
            super(itemView);
            IV_banner = (ImageView) itemView.findViewById(R.id.IV_banner);
            TV_item_name = (TextView) itemView.findViewById(R.id.TV_item_name);
            TV_item_price = (TextView) itemView.findViewById(R.id.TV_item_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickedListener.onItemClicked(getAdapterPosition(), productListItemses.get(getAdapterPosition()), v.findViewById(R.id.IV_banner));
                }
            });

        }


        @Override
        public void onClick(View v) {
            parentRecycler.smoothScrollToPosition(getAdapterPosition());
        }
    }

}
