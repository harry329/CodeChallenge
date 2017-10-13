package com.example.harry.singhharsimranandroidcodingchallenge.model;

import com.google.gson.annotations.Expose;
import com.google.gson.internal.LinkedTreeMap;

import java.util.ArrayList;

/**
 * Created by harry on 10/12/17.
 */

public class JsonResponse extends ArrayList {


    public ArrayList<LinkedTreeMap<String, String>> getData() {
        return data;
    }

    public void setData(ArrayList<LinkedTreeMap<String, String>> data) {
        this.data = data;
    }

    @Expose
    private ArrayList<LinkedTreeMap<String,String>> data;

}
