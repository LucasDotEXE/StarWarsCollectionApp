package com.example.starwarscollectablegame.Util;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class JsonParsingMethods {

    public static ArrayList<String> jsonArrayToStringArray(JSONArray array) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            for (int i = 0; i < array.length(); i++) {
                arrayList.add(array.getString(i));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
