package com.example.starwarscollectablegame.Controller.StarWarsAPI;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.example.starwarscollectablegame.Model.StarwarsData.SwapiEntry;
import com.example.starwarscollectablegame.Util.SwapiFactoryHandler;

import org.json.JSONObject;

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

    public static JsonRequest getJsonRequest(String url, Response.Listener<JSONObject> jsonObjectListener, Response.ErrorListener errorListener) {
        return new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                jsonObjectListener,
                errorListener
        );
    }
}
