package com.example.sicefapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class menuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }

    public void otvoriMapu(View view) throws IOException, JSONException {
        getElement();
        Intent i=new Intent(this,mapaActivity.class);
        startActivity(i);
    }

    public void dodajUredjaj(View view) {
        Intent i=new Intent(this,dodajActivity.class);
        startActivity(i);
    }

    public JSONArray getElement() throws IOException, JSONException {
        URL urlForGetRequest = new URL("http://192.168.137.130:5000/api/station/nikvujic");
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("GET");
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
            JSONObject jsonObject=new JSONObject(response.toString());
            JSONArray jsonArray=jsonObject.getJSONArray("stations");
            return jsonArray;
        } else {
            System.out.println("GET NOT WORKED");
        }
        return null;
    }


    public void obrisiUredjaj(View view) {
        Intent i=new Intent(this,DeleteActivity.class);
        startActivity(i);
    }
}
