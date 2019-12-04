package com.example.starwarscollectablegame.Controller.StarWarsAPI;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.starwarscollectablegame.Model.StarwarsData.StarWarsDataType;
import com.example.starwarscollectablegame.Model.StarwarsData.SwapiEntry;
import com.example.starwarscollectablegame.Util.SwapiFactoryHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StarwarsApiManager {

    private RequestQueue queue;

    public StarwarsApiManager(Context context) {
        this.queue = Volley.newRequestQueue(context);
    }


    public void getSwapiEntry(final SwapiEntryListener listener, final int identifier, final StarWarsDataType type) {
        final JsonRequest request = new JsonObjectRequest(
                Request.Method.GET,
                "https://swapi.co/api/" + type.getDataType() + "/" + identifier + "/",
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        SwapiEntry entry = SwapiFactoryHandler.parseJson(response, type);
                        listener.onEntryAvailavle(entry, type);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onEntryError();
                    }
                }
        );
        request.setRetryPolicy(ApiObjectFactory.getRetryPolacy());
        this.queue.add(request);
    }
}
