package com.example.location;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;



import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.TextView;



    public class MainActivity extends Activity {
 final String LOG_TAG="myLogs";
 
    	  TextView tvEnabledGPS;
    	  TextView tvStatusGPS;
    	  TextView tvLocationGPS;
    	  TextView tvEnabledNet;
    	  TextView tvStatusNet;
    	  TextView tvLocationNet;

    	  private LocationManager lm;
    	  StringBuilder sbGPS = new StringBuilder();
    	  StringBuilder sbNet = new StringBuilder();

    	  @Override
    	  protected void onCreate(Bundle savedInstanceState) {
    	    super.onCreate(savedInstanceState);
    	    setContentView(R.layout.activity_main);
    	    tvEnabledGPS = (TextView) findViewById(R.id.tvEnabledGPS);
    	    tvStatusGPS = (TextView) findViewById(R.id.tvStatusGPS);
    	    tvLocationGPS = (TextView) findViewById(R.id.tvLocationGPS);
    	    tvEnabledNet = (TextView) findViewById(R.id.tvEnabledNet);
    	    tvStatusNet = (TextView) findViewById(R.id.tvStatusNet);
    	    tvLocationNet = (TextView) findViewById(R.id.tvLocationNet);
    	    lm=(LocationManager)getSystemService(LOCATION_SERVICE);
    	    Location loc=lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    	    
      /*	  if(loc.getProvider().equals(LocationManager.NETWORK_PROVIDER)){
  			//	Log.d(LOG_TAG,"lm.NETWORK_PROVIDER; \nlocation.getProvider()="+loc.getProvider());
  		        double lat=loc.getLatitude();
  		        double lon=loc.getLongitude();
  		        Log.d(LOG_TAG,"lat="+lat);
  	         	Log.d(LOG_TAG,"lon="+lon);
  		
  	        	sbGPS.append(String.format("lat = %.3f",lat))
  			     .append(String.format("%nlon = %.5f",lon));
  	        	tvLocationGPS.setText(sbGPS);
  	        	*/
  	        	try {	
  	          for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
  	            NetworkInterface intf = en.nextElement();
  	            String inet = String.format("%d.%d.%d.%d", (intf.hashCode() & 0xff), (intf.hashCode() >> 8 & 0xff), (intf.hashCode()>>16  & 0xff), (intf.hashCode() >>24 & 0xff));
  	          String net = Formatter.formatIpAddress(intf.hashCode());
  	        Log.d(LOG_TAG, " NetworkInterface ="+ net+":::"+inet);
  	            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
  	                InetAddress inetAddress = (InetAddress) enumIpAddr.nextElement();
  	                if (!inetAddress.isLoopbackAddress()) {
  	                	
  	                    String ip = Formatter.formatIpAddress(inetAddress.hashCode());
  	                    Log.d(LOG_TAG, "***** IP="+ ip);
  	                  tvStatusNet.setText("www"+ip);
  	                }
  	            }
  	        }
  	    } catch (SocketException ex) {
  	        Log.e(LOG_TAG, ex.toString());
  	    }
  			//	}
    	    
    	//   lm.requestLocationUpdates(lm.GPS_PROVIDER, 1000*10, 10, locationListener);
    	  // lm.requestLocationUpdates(lm.NETWORK_PROVIDER, 1000*10, 10, locationListener);
    	  // Log.d(LOG_TAG,"requestLocationUpdates");
    	 }
    	 
    	  
    /*	    LocationListener locationListener=new LocationListener(){
    	    	
				@Override
				public void onLocationChanged(Location location) {
					
					if(location.getProvider().equals(lm.GPS_PROVIDER)){
						Log.d(LOG_TAG,"GPS_PROVIDER; \nlocation.getProvider()="+location.getProvider());
				        double lat=location.getLatitude();
				        double lon=location.getLongitude();
				        Log.d(LOG_TAG,"lat="+lat);
			         	Log.d(LOG_TAG,"lon="+lon);
				
			        	sbGPS.append(String.format("lat = %.3f",lat))
					     .append(String.format("%nlon = %.5f",lon));
			        	tvLocationGPS.setText(sbGPS);
						}
						if(location.getProvider().equals(lm.NETWORK_PROVIDER)){
							Log.d(LOG_TAG,"NETWORK_PROVIDER; \nlocation.getProvider()="+location.getProvider());
							double lat=location.getLatitude();
							double lon=location.getLongitude();
							Log.d(LOG_TAG,"lat="+lat);
							Log.d(LOG_TAG,"lon="+lon);
							
							sbNet.append(String.format("lat = %.3f",lat))
								.append(String.format("%nlon = %.5f",lon));
							tvLocationNet.setText(sbNet);
						}
					}
				

				@Override
				public void onProviderDisabled(String provider) {
					if(!lm.isProviderEnabled(lm.GPS_PROVIDER))
					tvStatusGPS.setText("GpsProviderDisabled");
					if(!lm.isProviderEnabled(lm.NETWORK_PROVIDER))
						tvStatusNet.setText("NetworkProviderDisabled");
				}

				@Override
				public void onProviderEnabled(String provider) {
					 tvStatusGPS.setText("onProviderEnabled=");
					
					 if(lm.isProviderEnabled(lm.GPS_PROVIDER))
							tvStatusGPS.setText("GpsProviderEnabled");
							if(lm.isProviderEnabled(lm.NETWORK_PROVIDER))
								tvStatusNet.setText("NetworkProviderEnabled");
						}

				@Override
				public void onStatusChanged(String provider, int arg1, Bundle arg2) {
					// TODO Auto-generated method stub
					
				}};
				*/
    	  
public void onClickLocationSettings(View v){
	Intent intent=new Intent();
	intent.setAction(Intent.ACTION_VIEW);
	intent.setData(Uri.parse("geo:"));
	startActivity(intent);
	}
    	
    }
