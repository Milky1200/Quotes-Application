package com.mishraaditya.quoteapp;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    private ArrayList<quote> quoteArrayList=new ArrayList<>();
    private int index;
    Context context;
    public MainViewModel(Context context) {
        this.context=context;
        index=0;
        quoteArrayList=getQuotesFromJson(context);
    }

    public ArrayList<quote> getQuotesFromJson(Context context) {
        ArrayList<quote> quotesList = new ArrayList<>();
        Gson gson = new Gson();
        try {
            InputStream is = context.getAssets().open("quotes.json");
            InputStreamReader reader = new InputStreamReader(is);
            Type quoteListType = new TypeToken<ArrayList<quote>>() {}.getType();
            quotesList = gson.fromJson(reader, quoteListType);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return quotesList;
    }
}
