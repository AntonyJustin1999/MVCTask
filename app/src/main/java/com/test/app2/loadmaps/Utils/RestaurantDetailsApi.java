package com.test.app2.loadmaps.Utils;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RestaurantDetailsApi {
    @SerializedName("restaurant")
    RestaurantRealtedDetails RestaurantRealtedDetails = null;

    public RestaurantRealtedDetails getRestaurantRealtedDetails() {
        return RestaurantRealtedDetails;
    }

    public void setRestaurantRealtedDetails(com.test.app2.loadmaps.Utils.RestaurantRealtedDetails restaurantRealtedDetails) {
        RestaurantRealtedDetails = restaurantRealtedDetails;
    }
}



