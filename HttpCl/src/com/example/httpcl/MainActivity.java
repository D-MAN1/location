package com.example.httpcl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	final String LOG="myLogs";
	final String TAG="myLogs";
	TextView tv ;
	String cellId,  lacId;
	String mac;//="12-34-56-78-9A-BC";
	String ipAddress;//="213.134.193.51";
	String operatorId="1";	
	final String URL ="http://api.lbs.yandex.net/geolocation";
	
	 String latitude;
	 String longitude;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv=(TextView)findViewById(R.id.textView1);
	
		telephony();
		wifi();
		connectYandexLocator();
		
		}
	void telephony(){
	
		TelephonyManager tm=(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		  Log.d(LOG, "tm="+tm); 
		if (tm!=null){
		GsmCellLocation location= (GsmCellLocation) tm.getCellLocation();
		Log.d(LOG, "location="+location); 
		//if(location!=null)
		{
			//& location.getCid()!=0 & location.getLac()!=0){
		
			
		int cellIdInt=611207 ;//location.getCid();
    	int iacIdInt= 414 ; //location.getLac();
		cellId= null;//Integer.toString(cellIdInt);
		lacId= null;//Integer.toString(iacIdInt);
		}
		}
	}
	void wifi(){
		ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        if(nInfo.getType()==ConnectivityManager.TYPE_WIFI){Log.d(LOG,"NetworkInfoType=TYPE_WIFI");}
        if(nInfo.getType()==ConnectivityManager.TYPE_MOBILE){Log.d(LOG,"NetworkInfoType=TYPE_MOBILE");}
        
        if (nInfo != null && nInfo.isConnected()) {
        	Log.d(LOG,"status_ONLINE"+nInfo);
        	WifiManager wm=(WifiManager)getSystemService(Context.WIFI_SERVICE);
    		Log.d(LOG, "wm="+wm);
    		if(wm!=null){
    		WifiInfo wi= wm.getConnectionInfo();
    		
    		if(wi!=null){
    			Log.d(LOG, "wi="+wi);
    		mac= wi.getMacAddress();
    		Log.d(LOG, "mac="+mac);
    		ipAddress=wi.getMacAddress();
    		Log.d(LOG, "ipAddress="+ipAddress);
    	}
    	}
        }
        else {
        	Log.d(LOG,"status_OFFLINE");
        }
	
	}
	 void connectYandexLocator()  {
	
				try {
					Log.d(LOG, "cell="+cellId);
					Log.d(LOG, "lacId="+lacId);
					URL url=new URL(URL);
					HttpURLConnection con=(HttpURLConnection) url.openConnection();
					   con.setRequestProperty("Host", "api.lbs.yandex.net");
				       con.setRequestProperty("Accept-Encoding","identity");
				  //  con.setRequestProperty("Content-length","1000");
				       con.setRequestProperty("Content-type","application/x-www-form-urlencoded");
				       con.setDoInput(true);
				       con.setDoOutput(true);//Опционально загрузить тело запроса. В этом случае используется метод setDoOutput(true).
				       StringBuilder params = new StringBuilder();
				       //тело запроса
				       params.append("xml=<ya_lbs_request>\n"
					            +"<common>\n"
					            +"<version>1.0</version>\n"
					       		+ "<api_key>AGqBM1YBAAAAJX-5ewIAY6oRYhGvfQeFxK5Zjv8L3X5xT24AAAAAAAAAAAAFYJqxlz0-Oi7K3FkUNJLhid4QqQ==</api_key>\n"
					       		+ "</common>\n"
					       		+ "<gsm_cells>\n"
					       		
					       		+ "  <cell>\n"
					       		+ "<countrycode>250</countrycode>\n"
					       		+ " <operatorid>"+operatorId+"</operatorid>\n"
					       		+ "<cellid>"+cellId+"</cellid>\n"
					       		+ "<lac>"+lacId+"</lac>\n"
					        	+ "<signal_strength>-80</signal_strength>\n"
					       		+ "</cell>\n"
					       	
					        +" <cell>\n"
					        +"<countrycode>250</countrycode>\n"
					        +"<operatorid>"+operatorId+"</operatorid>\n"
					        +"<cellid>"+cellId+"</cellid>\n"
					        +"<lac>"+lacId+"</lac>\n"
					        +"<signal_strength>-80</signal_strength>\n"    
					        +"<age>1000</age>\n"
					        +"</cell>\n"
					       		
					       		+ "</gsm_cells>\n"
					       		
					       		+"<wifi_networks>\n"
					            +"<network>\n"
					              +" <mac>"+mac+"</mac>\n"
					               +"<signal_strength>-90</signal_strength>\n"
					               +"<age>2000</age>\n"
					            +"</network>\n"
					            +"<network>\n"
					              +" <mac>"+mac+"</mac>\n"
					               +"<signal_strength>-90</signal_strength>\n"
					            +"</network>\n"
					         +"</wifi_networks>\n"
					         +"<ip>\n"
					           +" <address_v4>"+"00.220.237.23"+"</address_v4>\n"
					         +"</ip>\n"
					       		
					       		+ "</ya_lbs_request>\n");
				       OutputStream os= con.getOutputStream();
				       DataOutputStream dos= new DataOutputStream(os);
				       dos.writeBytes(params.toString());
				    //   Log.d(LOG, params.toString());
				       dos.flush();
				     int responseCode=  con.getResponseCode();
				     Log.d(LOG,"responseCod="+responseCode);
				     if (responseCode!=200){
				   //    Log.d(LOG,	 "ContentLength="+ con.getContentLength());
				       InputStream is=con.getErrorStream();
				       BufferedReader br = new BufferedReader(new InputStreamReader(is));
				       StringBuffer  respon = new StringBuffer();
				       String line = "";
				       while((line=br.readLine())!=null){
				    	   Log.d(LOG, "line"+line); 
				       }
				    	  br.close();
				    	 tv.setText(respon);
				    	//  Log.d(LOG, "line"+line);  
				    		 Log.d(LOG, "respon"+respon);  
				       }
				     else{
				    //	 Log.d(LOG,	 "ContentLength="+ con.getContentLength());
				    	 InputStream is= con.getInputStream();
				    	// BufferedReader br= new BufferedReader(new InputStreamReader(is));
				    	 
				    	// упрощенный пример 
				      
				         DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
				         DocumentBuilder builder;
						try {
							builder = factory.newDocumentBuilder();
							   Document document;
							
								document = builder.parse(is);
								   Element root = document.getDocumentElement();
							         // для простоты сразу берем message
							         Element lat = (Element) root.getElementsByTagName("latitude").item(0);
							          latitude = lat.getTextContent(); 
							      //   tv.setText(latitude);
							         Log.d(TAG, "latitude = " + latitude);
							         
							         Element lon = (Element) root.getElementsByTagName("longitude").item(0);
							          longitude = lon.getTextContent(); 
							      //   tv.setText(latitude+":"+(longitude));
							         Log.d(TAG, "longitude = " + longitude);
							         
							         Element typ= (Element) root.getElementsByTagName("type").item(0);
							        String type= typ.getTextContent();
							        Log.d(TAG, "type = " + type);
							} catch (SAXException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						      
						 catch (ParserConfigurationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				      
				    	
				  /*  	 try { 
				    		 Map<String,String> m;
				    		 m= new HashMap<String,String>();
				    		 ArrayList<String> ar=new ArrayList();
				    		 int ii=0;
							XmlPullParserFactory factory= XmlPullParserFactory.newInstance();// получаем фабрику
							factory.setNamespaceAware(true); // включаем поддержку namespace (по умолчанию выключена)
							XmlPullParser parser= factory.newPullParser(); // создаем парсер
							parser.setInput(br); // даем парсеру на вход Reader
							while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {
								
								String tmp = "";

								switch (parser.getEventType()) {
								case XmlPullParser.START_DOCUMENT:
									Log.d(TAG, "Начало документа");
									break;
								// начало тэга
								case XmlPullParser.START_TAG:
									Log.d(TAG,
											"START_TAG: имя тега = " + parser.getName()
													+ ", уровень = " + parser.getDepth()
													+ ", число атрибутов = "
													+ parser.getAttributeCount());
									tmp = "";
									for (int i = 0; i < parser.getAttributeCount(); i++) {
										tmp = tmp + parser.getAttributeName(i) + " = "
												+ parser.getAttributeValue(i) + ", ";
									}
									if (!TextUtils.isEmpty(tmp))
										Log.d(TAG, "Атрибуты: " + tmp);
									break;
								// конец тега
								case XmlPullParser.END_TAG:
									Log.d(TAG, "END_TAG: имя тега = " + parser.getName());
									break;
								// содержимое тега
								case XmlPullParser.TEXT:
									Log.d(TAG, "текст = " + parser.getText());
									ar.add(parser.getText());
									Log.d(TAG, "ar = " + ar.get(ii));
									ii++;
									break;

								default:
									break;
								}
							        // следующий элемент
								parser.next();
							      }
							      Log.d(TAG, "END_DOCUMENT");
							      Log.d(TAG, "arCount = " + ar.size());
							      String str="";
							      for(int i=0;i< ar.size();i++){
							       str=str+ar.get(i);
							      tv.setText(str);
							      }
							      Log.d(TAG, "ar: = " + str);
							    } catch (XmlPullParserException e) {
							      e.printStackTrace();
							    } catch (IOException e) {
							      e.printStackTrace();
							    }
				    	 */
				/////////////////////////////////////////////////////////////////////////			 
				    	 
				    	
				    //	 StringBuffer  respon = new StringBuffer();
				//    	 String line="" ;
				    //	 while((line=br.readLine())!=null){
				    	//	  respon.append(line);
				    	 }
					//    	  br.close();	      
					    //	tv.setText(respon);
					    	// Log.d(LOG, "respon"+respon);  
				//     }
			//		} catch (MalformedURLException e) {
			//	Log.d(LOG,"MalformedURLException"+e);
		//			e.printStackTrace();
				} catch (IOException e) {
				Log.d(LOG,"IOException"+e);
					e.printStackTrace();
				}			
		
			
	}
	public void getCurrencyClick(View v){
		 tv.setText(latitude+":"+(longitude));
		 Intent intent = new Intent();
		 intent.setAction(Intent.ACTION_VIEW);
		 Uri data= Uri.parse("geo:"+latitude+","+longitude);
		 intent.setData(data);
		 startActivity(intent);
		 
	}
}
