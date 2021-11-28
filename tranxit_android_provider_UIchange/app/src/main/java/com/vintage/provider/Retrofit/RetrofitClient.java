package com.vintage.provider.Retrofit;

import android.util.Log;

import com.vintage.provider.Helper.URLHelper;

import retrofit2.Retrofit;

import static com.vintage.provider.Fragment.Map.TAG;

/**
 * Created by CSS on 8/4/2017.
 */

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getLiveTrackingClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URLHelper.base)
                    .build();
        }
        return retrofit;
    }
}
