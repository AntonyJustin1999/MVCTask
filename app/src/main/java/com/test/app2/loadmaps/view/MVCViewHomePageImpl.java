package com.test.app2.loadmaps.view;

import static com.test.app2.loadmaps.MyApplication.getRealmDBAdapterInstance;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.test.app2.R;
import com.test.app2.loadmaps.Activities.HomeActivity;
import com.test.app2.loadmaps.Activities.LoginActivity;
import com.test.app2.loadmaps.Activities.RegisterActivity;
import com.test.app2.loadmaps.DataSets.CountriesApi;
import com.test.app2.loadmaps.controller.MVCHomeController;
import com.test.app2.loadmaps.controller.MVCLoginController;
import com.test.app2.loadmaps.model.API.DataFactory;
import com.test.app2.loadmaps.model.MVCModelImplementor;
import com.test.app2.loadmaps.model.Observer;

import java.util.ArrayList;

public class MVCViewHomePageImpl implements MVCViewHomePage, Observer {

    View rootView;
    AppCompatActivity activity;
    MVCHomeController mvcHomeController;
    MVCModelImplementor mvcModel;

    NavigationView navigationView;
    View headerView;
    private CountiresListAdapter mRestaurantListAdapter;
    private LottieAnimationView animationLoader;
    private RecyclerView rv_restaurant_list;
    private Context context;

    public MVCViewHomePageImpl(Context context, AppCompatActivity activity, ViewGroup continer){
        this.activity = activity;
        this.context = context;
        rootView = LayoutInflater.from(context).inflate(R.layout.home_activity,continer);
        mvcModel = new MVCModelImplementor(getRealmDBAdapterInstance());
        mvcHomeController = new MVCHomeController(mvcModel,this,context);
        mvcModel.registerObserver(this);
    }

    @Override
    public View getRootView() {
        return rootView;
    }

    @Override
    public void initViews() {
        animationLoader = rootView.findViewById(R.id.progress_bar);
        rv_restaurant_list = rootView.findViewById(R.id.rv_restaurant_list);

        activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Toolbar toolbar = rootView.findViewById(R.id.toolbar);
        activity.setSupportActionBar(toolbar);

        DrawerLayout drawer = rootView.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                activity, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = rootView.findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                return false;
            }
        });
        headerView = navigationView.getHeaderView(0);

        mvcHomeController.OnLoadCountryList();
    }

    @Override
    public void navigationToHome() {
        Intent intent = new Intent(rootView.getContext(), HomeActivity.class);
        rootView.getContext().startActivity(intent);
        activity.finish();
    }

    @Override
    public void showprogressbar() {
        animationLoader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideprogressbar() {
        animationLoader.setVisibility(View.GONE);
    }

    @Override
    public void navigationToLogin() {
        Intent intent = new Intent(rootView.getContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        rootView.getContext().startActivity(intent);
        activity.finish();
    }

    @Override
    public void showError(String ErrorMessage) {
        showalertDialogBox("",ErrorMessage);
    }

    public void OnLogOut() {
        try {
            mvcModel.LogOut();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void showalertDialogBox(String title,String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    @Override
    public void updateData(DataFactory dataFactory) {
        Log.e("Test","updateData called()");
        if(dataFactory.getCountriesList()!=null){
            RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
            rv_restaurant_list.setLayoutManager(linearLayoutManager);
            mRestaurantListAdapter = new CountiresListAdapter(dataFactory.getCountriesList());
            rv_restaurant_list.setAdapter(mRestaurantListAdapter);
        }
    }

    @Override
    public void showProgress() {
        Log.e("Test","showProgress called()");
        showprogressbar();
    }

    @Override
    public void hideProgress() {
        Log.e("Test","hideProgress called()");
        hideprogressbar();
    }

    @Override
    public void onNetworkFailed() {
        Log.e("Test","onNetworkFailed called()");
        ArrayList<CountriesApi> countrieslist = new ArrayList<>();
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        rv_restaurant_list.setLayoutManager(linearLayoutManager);
        mRestaurantListAdapter = new CountiresListAdapter(countrieslist);
        rv_restaurant_list.setAdapter(mRestaurantListAdapter);
    }

    @Override
    public void showErrorMsg(String ErrorMessage) {
        Log.e("Test","showErrMessage called()");
        ArrayList<CountriesApi> countrieslist = new ArrayList<>();
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        rv_restaurant_list.setLayoutManager(linearLayoutManager);
        mRestaurantListAdapter = new CountiresListAdapter(countrieslist);
        rv_restaurant_list.setAdapter(mRestaurantListAdapter);
    }

    public class CountiresListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private ArrayList<CountriesApi> mCountryList;
        public CountiresListAdapter(ArrayList<CountriesApi> countryList) {

            this.mCountryList = countryList;

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == 2) {
                return new CountryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.country_details, parent, false));
            } else {
                return new EmptyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_view, parent, false));
            }
        }

        @Override
        public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

            if (holder.getItemViewType() == 2) {
                CountryViewHolder countryViewHolder = (CountryViewHolder) holder;
                countryViewHolder.tv_common_name.setText(mCountryList.get(position).getName().getCommon());
                countryViewHolder.tv_official_name.setText(mCountryList.get(position).getName().getOfficial());
                Glide.with(context).load(mCountryList.get(position).getFlags().getPng_url()).into(countryViewHolder.iv_icon);
            } else{
                EmptyViewHolder emptyViewHolder = (EmptyViewHolder) holder;
                emptyViewHolder.tv_Empty.setText("No Data Found!!");
            }

        }

        @Override
        public int getItemViewType(int position) {
            if (mCountryList != null) {
                if (mCountryList.size() > 0) {
                    return 2;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }

        @Override
        public int getItemCount() {
            if (mCountryList != null) {
                if (mCountryList.size() > 0) {
                    return mCountryList.size();
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        }

        public class CountryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            TextView tv_common_name,tv_official_name;
            ImageView iv_icon;
            CardView layout_country_detail_holder;

            public CountryViewHolder(View itemView) {
                super(itemView);

                tv_common_name = (TextView) itemView.findViewById(R.id.tv_common_name);
                tv_official_name = (TextView) itemView.findViewById(R.id.tv_official_name);
                iv_icon = itemView.findViewById(R.id.iv_icon);
                layout_country_detail_holder = itemView.findViewById(R.id.layout_country_detail_holder);
                layout_country_detail_holder.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.layout_country_detail_holder){
                    //homeController.OnCountryDetailsPageRedirect(mCountryList.get(getAdapterPosition()).getName().getCommon());
                }
            }
        }

        public class EmptyViewHolder extends RecyclerView.ViewHolder {
            private TextView tv_Empty;

            EmptyViewHolder(View itemView) {
                super(itemView);
                tv_Empty = itemView.findViewById(R.id.tv_empty);
            }
        }
    }

}
