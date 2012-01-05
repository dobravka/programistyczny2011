package pwr.wppt.inf.kalkulator;

import java.util.Timer;


import com.google.android.maps.GeoPoint;


import android.app.Activity;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

public class myService extends Service {
	
    private LocationManager lm;
    private LocationListener locationListener;
    private GeoPoint gp=null;
    private double lat=0;
    private double lng=0;
    
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		lm = (LocationManager) 
        getSystemService(Context.LOCATION_SERVICE);    
    
    locationListener = new MyLocationListener();
    lm.requestLocationUpdates(
        LocationManager.GPS_PROVIDER, 
        0, 
        0, 
        locationListener);

	}
	
    private class MyLocationListener implements LocationListener 
    {
        @Override
        public void onLocationChanged(Location loc) {
            if (loc != null) {
            	lat=loc.getLatitude();
            	lng=loc.getLongitude();
                gp=new GeoPoint((int)(lat*1E6),(int)(lng*1E6));
                GeoPoint p = new GeoPoint(
                        (int) (loc.getLatitude() * 1E6), 
                        (int) (loc.getLongitude() * 1E6));
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStatusChanged(String provider, int status, 
            Bundle extras) {
            // TODO Auto-generated method stub
        }
    }

}
