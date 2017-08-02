package com.golui.shopping.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.text.TextUtilsCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.transition.TransitionInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.golui.shopping.R;
import com.golui.shopping.adapter.NavDrawerExpandableListAdapter;
import com.golui.shopping.models.NavDrawerExpandedMenuChildModel;
import com.golui.shopping.models.NavDrawerExpandedMenuParentModel;
import com.golui.shopping.ui.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private ExpandableListView ELV_navigation_view;
    private RelativeLayout RL_container_logged_in_user, RL_container_logged_out_user;
    private TextView TV_login, TV_logout;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private boolean mToolBarNavigationListenerIsRegistered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initAdapter();
        loadFragment(HomeFragment.class.getSimpleName());
    }

    private void enableToolbarBackButton(boolean isEnable) {
//        setDrawerState(isEnable);
        if (isEnable) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        } else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
        }
    }

    /**
     * To be semantically or contextually correct, maybe change the name
     * and signature of this function to something like:
     * <p>
     * private void showBackButton(boolean show)
     * Just a suggestion.
     */
    private void enableViews(boolean enable) {

        // To keep states of ActionBar and ActionBarDrawerToggle synchronized,
        // when you enable on one, you disable on the other.
        // And as you may notice, the order for this operation is disable first, then enable - VERY VERY IMPORTANT.
        if (enable) {
            // Remove hamburger
            toggle.setDrawerIndicatorEnabled(false);
            // Show back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            // when DrawerToggle is disabled i.e. setDrawerIndicatorEnabled(false), navigation icon
            // clicks are disabled i.e. the UP button will not work.
            // We need to add a listener, as in below, so DrawerToggle will forward
            // click events to this listener.
            if (!mToolBarNavigationListenerIsRegistered) {
                toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Doesn't have to be onBackPressed
                        onBackPressed();
                    }
                });

                mToolBarNavigationListenerIsRegistered = true;
            }

        } else {
            // Remove back button
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            // Show hamburger
            toggle.setDrawerIndicatorEnabled(true);
            // Remove the/any drawer toggle listener
            toggle.setToolbarNavigationClickListener(null);
            mToolBarNavigationListenerIsRegistered = false;
        }
    }

    public void setDrawerState(boolean isEnabled) {
        if (isEnabled) {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            toggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_UNLOCKED);
            toggle.setDrawerIndicatorEnabled(true);
            toggle.syncState();

        } else {
            drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            toggle.onDrawerStateChanged(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            toggle.setDrawerIndicatorEnabled(false);
            toggle.syncState();
        }
    }

    private void loadFragment(String fragName) {
        if (!TextUtils.isEmpty(fragName)) {
            if (fragName.equalsIgnoreCase(HomeFragment.class.getSimpleName())) {
                replaceFragment(new HomeFragment());
            }
        }
    }

    public void addFragment(Fragment fragment, View sharedView) {

        enableViews(true);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();


        fragmentTransaction.add(R.id.FL_container_body, fragment, fragment.getClass().getSimpleName());
        fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        fragmentTransaction.addSharedElement(sharedView, ViewCompat.getTransitionName(sharedView));
        fragmentTransaction.commit();
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.FL_container_body, fragment, fragment.getClass().getSimpleName());
        fragmentTransaction.commit();
    }

    private void initAdapter() {
        NavDrawerExpandableListAdapter navDrawerExpandableListAdapter = new NavDrawerExpandableListAdapter(this, getNavDrawerExpandedMenuParentModels(), ELV_navigation_view);
        ELV_navigation_view.setAdapter(navDrawerExpandableListAdapter);

    }

    @Override
    protected void setActivityContentView() {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ELV_navigation_view = (ExpandableListView) findViewById(R.id.ELV_navigation_view);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        RL_container_logged_in_user = (RelativeLayout) findViewById(R.id.RL_container_logged_in_user);
        RL_container_logged_out_user = (RelativeLayout) findViewById(R.id.RL_container_logged_out_user);
        RL_container_logged_in_user.setVisibility(View.GONE);

        TV_login = (TextView) findViewById(R.id.TV_login);
        TV_logout = (TextView) findViewById(R.id.TV_logout);

        TV_login.setOnClickListener(this);
        TV_logout.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            if (getSupportFragmentManager().getBackStackEntryCount() == 0)
                enableViews(false);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                onBackPressed();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    private List<NavDrawerExpandedMenuParentModel> getNavDrawerExpandedMenuParentModels() {
        List<NavDrawerExpandedMenuParentModel> navDrawerExpandedMenuParentModels = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            NavDrawerExpandedMenuParentModel navDrawerExpandedMenuParentModel = new NavDrawerExpandedMenuParentModel();
            navDrawerExpandedMenuParentModel.setTitle("Category " + (i + 1));
            List<NavDrawerExpandedMenuChildModel> navDrawerExpandedMenuChildModels = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                NavDrawerExpandedMenuChildModel navDrawerExpandedMenuChildModel = new NavDrawerExpandedMenuChildModel();
                navDrawerExpandedMenuChildModel.setTitle("Item " + (j + 1));
                navDrawerExpandedMenuChildModels.add(navDrawerExpandedMenuChildModel);
            }
            navDrawerExpandedMenuParentModel.setExpandedMenuChildModelList(navDrawerExpandedMenuChildModels);
            navDrawerExpandedMenuParentModels.add(navDrawerExpandedMenuParentModel);
        }
        return navDrawerExpandedMenuParentModels;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.TV_login:
                RL_container_logged_in_user.setVisibility(View.VISIBLE);
                RL_container_logged_out_user.setVisibility(View.GONE);
                break;
            case R.id.TV_logout:
                RL_container_logged_out_user.setVisibility(View.VISIBLE);
                RL_container_logged_in_user.setVisibility(View.GONE);
                break;
        }
    }
}
