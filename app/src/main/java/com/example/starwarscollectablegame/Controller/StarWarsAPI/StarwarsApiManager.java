package com.example.starwarscollectablegame.Controller.StarWarsAPI;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.StarWarsDataType;
import com.example.starwarscollectablegame.Model.StarwarsDatabase.StarwarsDatabaseData.SwapiEntry;
import com.example.starwarscollectablegame.Util.SwapiFactoryHandler;

import org.json.JSONException;
import org.json.JSONObject;

public class StarwarsApiManager {

    private RequestQueue queue;

    public StarwarsApiManager(Context context) {
        this.queue = Volley.newRequestQueue(context);
    }

    public void getSwapiEntryPage(final SwapiEntryPageListener listener, final StarWarsDataType type) {
        getSwapiEntryPage(listener, 1, type);
    }

    public void getSwapiEntryPage(final SwapiEntryPageListener listener, final int pageNumb, final StarWarsDataType type) {
        final JsonRequest request = ApiObjectFactory.getJsonRequest(
                "https://swapi.co/api/" + type.getDataType().toString() + "/?page=" + pageNumb,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.wtf("sadgfhasiudfhsaoiudfh", response.toString());
                            String next = response.getString("next");
                            String[] splitNext = next.split("=");
                            int nextPageNr;
                            if (splitNext.length < 2) {
                                nextPageNr = -1;
                            } else {
                                nextPageNr = Integer.parseInt(splitNext[1]);
                            }
                            listener.onSwapiEntryPageListener(
                                    SwapiFactoryHandler.parseSwapiPageJson(response, type),
                                    type,
                                    nextPageNr
                            );
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.wtf("ERROR ERROR ERROR", error.getMessage());
                        listener.onSwapiEntryPageError();
                    }
                }
        );

        request.setRetryPolicy(ApiObjectFactory.getRetryPolacy());

        Log.wtf("sdjfhsfdjhsalkfjhldkfjhaslkf", "Json request sendt" + pageNumb + "   type: " + type + "\n " + "https://swapi.co/api/" + type.getDataType().toString() + "/?page=" + pageNumb);
        this.queue.add(request);
    }



    public void getSwapiEntry(final SwapiEntryListener listener, final int identifier, final StarWarsDataType type) {
        JsonRequest request = ApiObjectFactory.getJsonRequest(
                "https://swapi.co/api/" + type.getDataType() + "/" + identifier + "/",
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        SwapiEntry entry = SwapiFactoryHandler.parseSwapiEntryJson(response, type);
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
