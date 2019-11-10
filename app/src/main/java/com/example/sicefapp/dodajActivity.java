package com.example.sicefapp;
import android.content.pm.ActivityInfo;
import android.os.StrictMode;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class dodajActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dodaj);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.podmapa);
        mapFragment.getMapAsync(this);
        StrictMode.ThreadPolicy policy =
                new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng position = new LatLng(1, 1);
        mMap.addMarker(new MarkerOptions().position(position).title("Prvi marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
    }

    public void postRequest() throws IOException, JSONException {
          JSONObject jsonObject=new JSONObject();
        jsonObject.put("x_coor","1");
        jsonObject.put("y_coor","1");
        jsonObject.put("air_temp","1");
        jsonObject.put("air_hum","1");
        jsonObject.put("light","1");
        jsonObject.put("earth_temp","1");
        jsonObject.put("earth_hum","1");
        jsonObject.put("username","nikvujic");
        String json=jsonObject.toString();
        URL obj = new URL("http://192.168.137.130:5000/api/station");
        HttpURLConnection postConnection = (HttpURLConnection) obj.openConnection();
        postConnection.setRequestMethod("POST");
        postConnection.setRequestProperty("userId", "a1bcdefgh");
        postConnection.setRequestProperty("Content-Type", "application/json");
        postConnection.setDoOutput(true);
        OutputStream os = postConnection.getOutputStream();
        os.write(json.getBytes());
        os.flush();
        os.close();
        int responseCode = postConnection.getResponseCode();
        System.out.println("POST Response Code :  " + responseCode);
        System.out.println("POST Response Message : " + postConnection.getResponseMessage());
        //if (responseCode == HttpURLConnection.HTTP_CREATED) { //success
        BufferedReader in = new BufferedReader(new InputStreamReader(
                postConnection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in .readLine()) != null) {
            response.append(inputLine);
        } in .close();
        // print result
        JSONObject jsonObject1=new JSONObject(response.toString());

}

    public void posaljiStanicu(View view) {
        try {
            postRequest();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
