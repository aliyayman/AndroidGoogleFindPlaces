package com.aliyayman.googlefindplacesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.aliyayman.googlefindplacesapp.databinding.ActivityMainBinding;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PlacesActivity extends AppCompatActivity {
    private RecyclerView rv;
    private String latitude;
    private String meridian;
    private ArrayList<Places> placesArrayList;
    private PlacesAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);


        rv=findViewById(R.id.rv);
        latitude=getIntent().getStringExtra("latitude");
        meridian=getIntent().getStringExtra("meridian");


        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));


       getPlace();

    }
    public void getPlace(){
        String key="AIzaSyBSCSOL8owi6e590wwbg_rBlbllkR_9nMw";
        String location=latitude+","+meridian;
        String radius="1000";

        String url="https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+location+"&radius="+radius+"&key="+key;
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    placesArrayList=new ArrayList<>();
                    JSONObject jsonObject=new JSONObject(response);

                    JSONArray places=jsonObject.getJSONArray("results");
                    for(int i=0;i<places.length();i++){
                        JSONObject place=places.getJSONObject(i);

                        String placeName=place.getString("name");
                        String adress=place.getString("vicinity");

                        JSONObject geometry=place.getJSONObject("geometry");
                        JSONObject location=geometry.getJSONObject("location");

                        String latitude=location.getString("lat");
                        String meridian=location.getString("lng");

                        Log.e("ad:",placeName);
                        Log.e("adress:",adress);
                        Log.e("enlem:",latitude);
                        Log.e("boylam:",meridian);


                       Places p=new Places(placeName,Double.parseDouble(latitude),Double.parseDouble(meridian),adress);

                        placesArrayList.add(p);


                    }

                    adapter=new PlacesAdapter(PlacesActivity.this,placesArrayList);
                    rv.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(PlacesActivity.this).add(stringRequest);


    }

}