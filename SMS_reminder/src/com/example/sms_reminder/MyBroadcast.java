package com.example.sms_reminder;



import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcast extends BroadcastReceiver {

		public static final String INNAMB="inNumb";
		//public static final String LONG="long";
		final  String LOG_TAG="myLogsBroadcast";
		 // Идентификатор уведомления
	    private static int NOTIFY_ID = 10;
		@Override
		public void onReceive(Context context, Intent intent) {
			MainActivity.indexSms=MainActivity.indexSms+1;
	        
			 Log.d(LOG_TAG,"MainActivity.on="+MainActivity.on);
			if (MainActivity.on==true){
				
			// TODO Auto-generated method stub
			String inNumb = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
	        Toast.makeText(context, inNumb, Toast.LENGTH_LONG).show();
	      
	       
	      //  Log.d(LOG_TAG,"inNumb="+inNumb);
	   //   Log.d(LOG_TAG,"array="+MainActivity.texts.size());
	       
	       
	     for(int i=0; i<MainActivity.texts.size();i++){
	    	
	    	// Log.d(LOG_TAG,"i="+i);
	    	// Log.d(LOG_TAG,"MainActivity.texts.get(i)="+MainActivity.texts.get(i));
	    	 if(inNumb==null){
	    	 Log.d(LOG_TAG,"inNumb="+inNumb);
	    	 break;
	    	 }
	    	// Log.d(LOG_TAG,"hashCode_inNumb="+inNumb.hashCode());
	    	// Log.d(LOG_TAG,"hashCode_MainActivity.texts.get(i)="+MainActivity.texts.get(i).hashCode());
	    	 Log.d(LOG_TAG,"inNumb="+inNumb);
	        if ((inNumb.equals(MainActivity.texts.get(i))& (MainActivity.indexSms % 2 ==0))){
	        	 Log.d(LOG_TAG,"=hf=");
	        	 SmsManager sms=  SmsManager.getDefault();
	        sms.sendTextMessage(inNumb, null, MainActivity.smsText, null, null);
	        
	        Intent notificationIntent = new Intent(context, MainActivity.class);
	        PendingIntent contentIntent = PendingIntent.getActivity(context,
	                0, notificationIntent,
	                PendingIntent.FLAG_CANCEL_CURRENT);
	        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
	     // оставим только самое необходимое
	     
	        builder.setContentIntent(contentIntent)
	        .setSmallIcon(R.drawable.ic_launcher)
	             .setContentTitle("Отправлено SMS на номер")
	             .setContentText(inNumb); // Текст уведомления
              Notification notification = builder.build();
              NotificationManager notificationManager = (NotificationManager) context
	                .getSystemService(Context.NOTIFICATION_SERVICE);        
	        notificationManager.notify(NOTIFY_ID, notification);
	        NOTIFY_ID=NOTIFY_ID+1;
	        Log.d(LOG_TAG," NOTIFY_ID=" +NOTIFY_ID);
	        break;
	     }
	       
	     }
	        //context.startService(new Intent(context, MyService.class).putExtra(INNAMB, inNumb));
			}
			
		}
	 
	}
