package com.test.app2.loadmaps.model.API;

import com.test.app2.loadmaps.DataSets.CountriesApi;
import com.test.app2.loadmaps.DataSets.CountryDetailsApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiCall {
//    @GET("index.php?route=app/feed/getList")
//    public Call<RestaurantListApi> getRestaurantList(@Query("latitude") String latitude, @Query("longitude") String longitude,
//                                                     @Query("order_type") String order_type, @Query("page") String page, @Query("limit") String limit
//            , @Query("sort") String sort, @Query("best_seller") String best_seller, @Query("delivery_fee") String delivery_fee
//            , @Query("vegetarian") String vegetarian, @Query("vegan") String vegan, @Query("gultan_free") String gultan_free, @Query("halal") String halal
//            , @Query("language_id") String language_id);
//
//
//    @GET("index.php?route=app/feed/getInfo")
//    public Call<RestaurantDetailsApi> getRestaurantInfo(@Query("latitude") String latitude, @Query("longitude") String longitude,
//                                                        @Query("order_type") String order_type, @Query("restaurant_id") String restaurant_id,
//                                                        @Query("language_id") String language_id);

    @GET("v3.1/independent?")
    public Call<ArrayList<CountriesApi>> getAllCountrieslist(@Query("status") boolean status, @Query("fields") String fields);

    @GET("v3.1/name/{countryName}")
    public Call<ArrayList<CountryDetailsApi>> getCountryInfo(@Path("countryName")String countryName);

}
