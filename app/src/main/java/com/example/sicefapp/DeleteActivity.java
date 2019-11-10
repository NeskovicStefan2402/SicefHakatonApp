package com.example.sicefapp;

import android.content.pm.ActivityInfo;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.LinkedList;

public class DeleteActivity extends AppCompatActivity {
    MyAdapter lAdapter;
    ListView lView;
    LinkedList<String> nazivi=new LinkedList<>();
    LinkedList<String> cene=new LinkedList<>();
    LinkedList<Integer> slike=new LinkedList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        napuniListu();
        lView = (ListView) findViewById(R.id.androidList);
        lAdapter = new MyAdapter(DeleteActivity.this, nazivi,slike);
        lView.setAdapter(lAdapter);
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


    }

    public void obrisiStavku(View view,String naziv) throws IOException {
        URL urlForGetRequest = new URL("http://192.168.137.130:5000/api/station/"+naziv);
        String readLine = null;
        HttpURLConnection conection = (HttpURLConnection) urlForGetRequest.openConnection();
        conection.setRequestMethod("DELETE");
        int responseCode = conection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(conection.getInputStream()));
            StringBuffer response = new StringBuffer();
            while ((readLine = in .readLine()) != null) {
                response.append(readLine);
            } in .close();
        } else {
            System.out.println("GET NOT WORKED");
        }
    }
    public void napuniListu(){
        nazivi.add("Kukuruz");
        nazivi.add("Psenica");
        nazivi.add("Kukuruz");
        nazivi.add("Kukuruz");
        nazivi.add("Kukuruz");
        nazivi.add("Kukuruz");
        nazivi.add("Kukuruz");
        nazivi.add("Kukuruz");
        nazivi.add("Kukuruz");
        nazivi.add("Kukuruz");


        slike.add(0);
        slike.add(0);
        slike.add(0);
        slike.add(0);
        slike.add(0);
        slike.add(0);
        slike.add(0);
        slike.add(0);
        slike.add(0);
        slike.add(0);



    }
}
