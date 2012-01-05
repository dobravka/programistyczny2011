package pwr.wppt.inf.kalkulator;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.content.Context;
import android.graphics.*;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class Map extends MapActivity 
{    
    private LocationManager lm;
    private LocationListener locationListener;

    private MapView mapView;
    private MapController mc;
    private double lat=0;
    private double lng=0;
    private GeoPoint gp=null;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map); 
        
        //---use the LocationManager class to obtain GPS locations---
        lm = (LocationManager) 
            getSystemService(Context.LOCATION_SERVICE);    
        
        locationListener = new MyLocationListener();
        lm.requestLocationUpdates(
            LocationManager.GPS_PROVIDER, 
            0, 
            0, 
            locationListener);
        
        mapView = (MapView) findViewById(R.id.mapview1);
        mc = mapView.getController();
        mapView.displayZoomControls(true);

        
    }

    @Override
    protected boolean isRouteDisplayed() {
        // TODO Auto-generated method stub
        return false;
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
                mc.animateTo(p);
                
                mapView.getOverlays().add(new DirectionPathOverlay(gp,p));
                mapView.postInvalidate();
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
//    @Override
//    public void onDestroy(){
//    	super.onDestroy();
//    }
    
    class DirectionPathOverlay extends Overlay {

        private GeoPoint gp1;
        private GeoPoint gp2;

        public DirectionPathOverlay(GeoPoint gp1, GeoPoint gp2) {
            this.gp1 = gp1;
            this.gp2 = gp2;
        }

        @Override
        public boolean draw(Canvas canvas, MapView mapView, boolean shadow, long when) {
            Paint paint = new Paint();

            super.draw(canvas, mapView, shadow);
            // Converts lat/lng-Point to OUR coordinates on the screen.
            Point myScreenCoords = new Point();

            mapView.getProjection().toPixels(gp1, myScreenCoords);

            paint.setStrokeWidth(1);
            paint.setARGB(255, 255, 255, 255);
            paint.setStyle(Paint.Style.STROKE);

            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.push);

            canvas.drawBitmap(bmp, myScreenCoords.x, myScreenCoords.y, paint);
            return true;
        }

    }
}