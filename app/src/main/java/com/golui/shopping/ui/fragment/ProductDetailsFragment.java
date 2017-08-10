package com.golui.shopping.ui.fragment;

import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.golui.shopping.R;
import com.golui.shopping.adapter.HomeBannerUltraPagerAdapter;
import com.golui.shopping.adapter.HomeHorizontalRecAdapter;
import com.golui.shopping.adapter.HomeVerticalRecAdapter;
import com.golui.shopping.models.ProductListItems;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.Orientation;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

/**
 * Created by RAVI on 7/29/2017.
 */

public class ProductDetailsFragment extends BaseFragment {
    private TextView TV_more_details, TV_item_name, TV_item_price;
    private ProductListItems productListItem;
    private ImageView IV_banner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_details, null);
        initView(view);
        return view;
    }

    private void initView(View view) {

        TV_more_details = (TextView) view.findViewById(R.id.TV_more_details);
        TV_more_details.setPaintFlags(TV_more_details.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        TV_item_name = (TextView) view.findViewById(R.id.TV_item_name);
        TV_item_price = (TextView) view.findViewById(R.id.TV_item_price);
        IV_banner = (ImageView) view.findViewById(R.id.IV_banner);


        Bundle bundle = getArguments();
        if (bundle != null && bundle.containsKey("ProductListItems"))
            productListItem = bundle.getParcelable("ProductListItems");
        postponeEnterTransition();
        if (bundle != null && bundle.containsKey("SharedElement")) {
            String sharedElement = bundle.getString("SharedElement");
            IV_banner.setTransitionName(sharedElement);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

//            setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.fade));
        }

        if (productListItem != null) {
            TV_item_name.setText(productListItem.getName());
            TV_item_price.setText("RS: " + productListItem.getPrice());
            IV_banner.setImageResource(productListItem.getDrawableRes());

        }
        startPostponedEnterTransition();
    }


    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onStop() {
        super.onStop();

    }


    @Override
    public boolean onBackPressed() {
        return true;
    }


}
