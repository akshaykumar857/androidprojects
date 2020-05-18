package com.example.akshay.hikerswatch;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    LocationManager locationManager;
    LocationListener locationListener;
    TextView latTextView,lonTextView,altTextView,addTextView,accTextView;
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {
            startLis();
       }
    }

    private void startLis() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        latTextView=(TextView)findViewById(R.id.latTextView);
        lonTextView=(TextView)findViewById(R.id.lonTextView2);
        altTextView=(TextView)findViewById(R.id.altTextView);
        addTextView=(TextView)findViewById(R.id.addTextView);
        accTextView=(TextView)findViewById(R.id.accTextView);
        locationManager=(LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        locationListener=new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                updateLocationInfo(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
        else
        {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);
            Location lastkno=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if(lastkno!=null)
                updateLocationInfo(lastkno);
        }
    }

    private void updateLocationInfo(Location loc) {
        Log.i("Location",loc.toString());
        latTextView.setText("Latitide "+Double.toString(loc.getLatitude()));
        lonTextView.setText("Longitude "+Double.toString(loc.getLongitude()));
        accTextView.setText("Accuracy "+Double.toString(loc.getAccuracy()));
        altTextView.setText("Altitude "+Double.toString(loc.getAltitude()));
        String add="COuld not find address :(";
        Geocoder geocoder=new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addressList=geocoder.getFromLocation(loc.getLatitude(),loc.getLongitude(),1);
            if(addressList!=null && addressList.size()>0)
                add="Address:\n";
            if(addressList.get(0).getThoroughfare()!=null)
            add+=addressList.get(0).getThoroughfare();
            if(addressList.get(0).getLocality()!=null)
                add+=addressList.get(0).getLocality();
            if(addressList.get(0).getPostalCode()!=null)
                add+=addressList.get(0).getPostalCode();
            if(addressList.get(0).getAdminArea()!=null)
                add+=addressList.get(0).getAdminArea();

        } catch (IOException e) {
            e.printStackTrace();
        }
        addTextView.setText(add);

    }
}
