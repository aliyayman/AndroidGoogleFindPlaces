package com.aliyayman.googlefindplacesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.aliyayman.googlefindplacesapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private ActivityMainBinding design;
    private String providerLocation="gps";
    private int controlPermission;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       design= DataBindingUtil. setContentView(this,R.layout.activity_main);

       locationManager= (LocationManager) getSystemService(Context.LOCATION_SERVICE);


       design.buttonGo.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String latitude=design.editTextLatitude.getText().toString();
               String meridian=design.editTextMeridian.getText().toString();

               Intent intent=new Intent(MainActivity.this,PlacesActivity.class);
               intent.putExtra("latitude",latitude);
               intent.putExtra("meridian",meridian);
               startActivity(intent);

           }
       });
       design.buttonLocation.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {


               controlPermission= ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
               if(controlPermission!= PackageManager.PERMISSION_GRANTED){

                   ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},10);

               }else{
                   Location location=locationManager.getLastKnownLocation(providerLocation);
                   if(location!=null){

                       onLocationChanged(location);
                   }else{
                       Toast.makeText(getApplicationContext(),"konum alınamadı.",Toast.LENGTH_SHORT);

                   }

               }
           }
       });
    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        Double latitude=location.getLatitude();
        Double meridian=location.getLongitude();

        Intent intent=new Intent(MainActivity.this,PlacesActivity.class);
        intent.putExtra("latitude",String.valueOf(latitude));
        intent.putExtra("meridian",String.valueOf(meridian));
        startActivity(intent);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {

    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==10){

            controlPermission= ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);

            if(grantResults.length>0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(getApplicationContext(),"izin verildi.",Toast.LENGTH_SHORT);

                Location location=locationManager.getLastKnownLocation(providerLocation);
                if(location!=null){

                    onLocationChanged(location);
                }else{
                    Toast.makeText(getApplicationContext(),"konuma alınamadı.",Toast.LENGTH_SHORT);

                }
            }else{
                Toast.makeText(getApplicationContext(),"izin verilmedi",Toast.LENGTH_SHORT);

            }

        }
    }
}