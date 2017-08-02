package com.golui.shopping.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.golui.shopping.R;
import com.golui.shopping.adapter.HomeBannerUltraPagerAdapter;
import com.golui.shopping.adapter.HomeHorizontalRecAdapter;
import com.golui.shopping.adapter.HomeVerticalRecAdapter;
import com.golui.shopping.listeners.ItemClickedListener;
import com.golui.shopping.models.ProductListItems;
import com.golui.shopping.ui.activity.HomeActivity;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.Orientation;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RAVI on 7/29/2017.
 */

public class HomeFragment extends BaseFragment {
    DiscreteScrollView scrollView;
    private Handler handler = new Handler();
    private RecyclerView rec_horizontal, rec_vertical;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        scrollView = (DiscreteScrollView) view.findViewById(R.id.rec_banner);
//        InfiniteScrollAdapter wrapper = InfiniteScrollAdapter.wrap();
        rec_horizontal = (RecyclerView) view.findViewById(R.id.rec_horizontal);
//        rec_horizontal.setNestedScrollingEnabled(false);
        rec_vertical = (RecyclerView) view.findViewById(R.id.rec_vertical);
        rec_vertical.setNestedScrollingEnabled(false);
        scrollView.setAdapter(new HomeBannerUltraPagerAdapter(getActivity(), getBannerItems()));
        scrollView.setOrientation(Orientation.HORIZONTAL);
        scrollView.setNestedScrollingEnabled(false);
//        scrollView.scrollToPosition(0);
        scrollView.setItemTransitionTimeMillis(300);
        scrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.8f)
                .build());

        setHorizontalRecAdapter();
        setVerticalRecAdapter();
        setUpViewPager();
        setPagerAdapter();

    }

    private void setHorizontalRecAdapter() {
        HomeHorizontalRecAdapter homeHorizontalRecAdapter = new HomeHorizontalRecAdapter(getActivity(), getRecentViewItems(), new ItemClickedListener() {
            @Override
            public void onItemClicked(Object... obj) {
                goToProductDetails(((ProductListItems) obj[1]), (View) obj[2]);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
//        DividerItemDecoration mDividerItemDecoration = new DividerItemDecoration(rec_horizontal.getContext(),
//                linearLayoutManager.getOrientation());
//        rec_horizontal.addItemDecoration(mDividerItemDecoration);
        rec_horizontal.setLayoutManager(linearLayoutManager);
        rec_horizontal.setAdapter(homeHorizontalRecAdapter);

    }

    private void setVerticalRecAdapter() {
        HomeVerticalRecAdapter homeHorizontalRecAdapter = new HomeVerticalRecAdapter(getActivity(), getProductListItems(), new ItemClickedListener() {
            @Override
            public void onItemClicked(Object... obj) {
                goToProductDetails(((ProductListItems) obj[1]), (View) obj[2]);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rec_vertical.setLayoutManager(linearLayoutManager);
        rec_vertical.setAdapter(homeHorizontalRecAdapter);

    }

    private void goToProductDetails(ProductListItems productListItems, View view) {
        ProductDetailsFragment productDetailsFragment = new ProductDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("ProductListItems", productListItems);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.setSharedElementReturnTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.default_transition));
            this.setExitTransition(TransitionInflater.from(getActivity()).inflateTransition(android.R.transition.no_transition));

            productDetailsFragment.setSharedElementEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(R.transition.default_transition));
            productDetailsFragment.setEnterTransition(TransitionInflater.from(getActivity()).inflateTransition(android.R.transition.no_transition));

            bundle.putString("SharedElement", ViewCompat.getTransitionName(view));
        }
        productDetailsFragment.setArguments(bundle);
        ((HomeActivity) getActivity()).addFragment(productDetailsFragment, view);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.removeCallbacks(myRunnable);
        handler.postDelayed(myRunnable, 2000);
    }

    @Override
    public void onStop() {
        super.onStop();
        handler.removeCallbacks(myRunnable);
    }

    private void setUpViewPager() {

    }

    private void setPagerAdapter() {
    }

    @Override
    public boolean onBackPressed() {
        return true;
    }

    private Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            if (getActivity() != null && isAdded()) {
                if ((scrollView.getAdapter().getItemCount() - scrollView.getCurrentItem()) > 1)
                    scrollView.smoothScrollToPosition((scrollView.getCurrentItem() + 1));
                else
                    scrollView.scrollToPosition(0);

                handler.removeCallbacks(myRunnable);
                handler.postDelayed(myRunnable, 2000);
            }
        }
    };

    private List<ProductListItems> getRecentViewItems() {
        List<ProductListItems> productListItemses = new ArrayList<>();
        productListItemses.add(new ProductListItems(R.drawable.earphone, "Senheiser S132", "2200"));
        productListItemses.add(new ProductListItems(R.drawable.fridge, "Godrej G545", "12200"));
        productListItemses.add(new ProductListItems(R.drawable.hp_laptops, "HP Pavilion", "42400"));
        productListItemses.add(new ProductListItems(R.drawable.ipads, "Apple iPad", "37200"));
        productListItemses.add(new ProductListItems(R.drawable.jaguar_colonge, "Jaguar EDT", "1200"));
        productListItemses.add(new ProductListItems(R.drawable.kids_fashion, "Liliput Shirt", "1500"));
        productListItemses.add(new ProductListItems(R.drawable.lenovo_smartphone, "Lenovo K5", "16000"));
        productListItemses.add(new ProductListItems(R.drawable.ring, "Diamong Ring", "30000"));
        productListItemses.add(new ProductListItems(R.drawable.sony_bravia_tv, "Sony bravia 524A", "85499"));
        productListItemses.add(new ProductListItems(R.drawable.suit, "Suit piece", "1100"));
        productListItemses.add(new ProductListItems(R.drawable.womens_jacket, "Levis jackets", "2300"));
        productListItemses.add(new ProductListItems(R.drawable.lenovo_smartphone, "Samsung A6", "17000"));
        productListItemses.add(new ProductListItems(R.drawable.kids_fashion, "Kids Kurta", "1499"));
        productListItemses.add(new ProductListItems(R.drawable.fridge, "LG refrigerator", "23200"));
        productListItemses.add(new ProductListItems(R.drawable.hp_laptops, "Dell notebook", "52599"));

        return productListItemses;
    }

    private List<ProductListItems> getProductListItems() {
        List<ProductListItems> productListItemses = new ArrayList<>();
        productListItemses.add(new ProductListItems(R.drawable.suit, "Suit piece", "1100"));
        productListItemses.add(new ProductListItems(R.drawable.womens_jacket, "Levis jackets", "2300"));
        productListItemses.add(new ProductListItems(R.drawable.kids_fashion, "Kids Kurta", "1499"));
        productListItemses.add(new ProductListItems(R.drawable.jaguar_colonge, "Jaguar EDT", "1200"));
        productListItemses.add(new ProductListItems(R.drawable.kids_fashion, "Liliput Shirt", "1500"));
        productListItemses.add(new ProductListItems(R.drawable.lenovo_smartphone, "Lenovo K5", "16000"));
        productListItemses.add(new ProductListItems(R.drawable.ring, "Diamong Ring", "30000"));
        productListItemses.add(new ProductListItems(R.drawable.sony_bravia_tv, "Sony bravia 524A", "85499"));
        productListItemses.add(new ProductListItems(R.drawable.earphone, "Senheiser S132", "2200"));
        productListItemses.add(new ProductListItems(R.drawable.fridge, "Godrej G545", "12200"));
        productListItemses.add(new ProductListItems(R.drawable.hp_laptops, "HP Pavilion", "42400"));
        productListItemses.add(new ProductListItems(R.drawable.ipads, "Apple iPad", "37200"));
        productListItemses.add(new ProductListItems(R.drawable.lenovo_smartphone, "Samsung A6", "17000"));
        productListItemses.add(new ProductListItems(R.drawable.fridge, "LG refrigerator", "23200"));
        productListItemses.add(new ProductListItems(R.drawable.hp_laptops, "Dell notebook", "52599"));

        return productListItemses;
    }

    private List<ProductListItems> getBannerItems() {
        List<ProductListItems> productListItemses = new ArrayList<>();

        productListItemses.add(new ProductListItems(R.drawable.lenovo_smartphone, "Lenovo K5", "16000"));
        productListItemses.add(new ProductListItems(R.drawable.ring, "Diamong Ring", "30000"));
        productListItemses.add(new ProductListItems(R.drawable.sony_bravia_tv, "Sony bravia 524A", "85499"));
        productListItemses.add(new ProductListItems(R.drawable.earphone, "Senheiser S132", "2200"));
        productListItemses.add(new ProductListItems(R.drawable.womens_jacket, "Levis jackets", "2300"));
        productListItemses.add(new ProductListItems(R.drawable.jaguar_colonge, "Jaguar EDT", "1200"));
        productListItemses.add(new ProductListItems(R.drawable.fridge, "Godrej G545", "12200"));
        productListItemses.add(new ProductListItems(R.drawable.hp_laptops, "HP Pavilion", "42400"));
        productListItemses.add(new ProductListItems(R.drawable.ipads, "Apple iPad", "37200"));

        return productListItemses;
    }
}
