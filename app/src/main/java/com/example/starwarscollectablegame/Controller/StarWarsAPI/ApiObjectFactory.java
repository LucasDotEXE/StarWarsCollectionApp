package com.example.starwarscollectablegame.Controller.StarWarsAPI;

import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;

public class ApiObjectFactory {

    public static RetryPolicy getRetryPolacy() {
        return new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 5000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 5;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {
                error.printStackTrace();
            }
        };
    }
}
