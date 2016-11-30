package com.example.sms_reminder;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements
OnCheckedChangeListener{
	
    public static boolean on=false;
	public static String mPhoneNumber;
	public static String mContactName;
	//public static String EDIT_TEXT="edSmsText";
	private static final int CONTACT_PICK_RESULT = 3;
	final String LOG_TAG="myLogs";
	static ArrayList<String> texts;
	public static String smsText;
	 final String ATTRIBUTE_NAME_TEXT = "text";
	 
	 final String NUMBER ="number";
	 private static final int CM_DELETE_ID = 1;
	public static int indexSms=2;
	  DB db;
	  
	  Cursor cursor;
	 
ListView lvMain;	
Button btStart, btAdd;
TextView tvNumb,tvContact,tvSms;
EditText etSms;
String read="";
String mContactId;
String sms;


SimpleCursorAdapter scAdapter;
ToggleButton toogleButton;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvNumb= (TextView)findViewById(R.id.itemtvNumber);
		tvContact= (TextView)findViewById(R.id.itemtvContact);
		tvSms= (TextView)findViewById(R.id.itemtvSms);
		
		etSms=(EditText)findViewById(R.id.etName);
		toogleButton = (ToggleButton) findViewById(R.id.ToggleButton);
		toogleButton.setOnCheckedChangeListener(this);
	
		 Log.d(LOG_TAG, "on="+on);
		if(on==true){toogleButton.setChecked(on); }
		simpleAdapt();
	}
       public void simpleAdapt(){
    	  
    	// открываем подключение к БД
    	    db = new DB(this);
    	    db.open();

    	    // получаем курсор
    	    cursor = db.getAllData();
    	    startManagingCursor(cursor);
    	    int index=cursor.getColumnIndex(DB.DB_EDIT_TEXT);
    	    if (cursor.moveToFirst()){
    	  String str=cursor.getString(index);
    	//  Log.d(LOG_TAG, "str"+str);
    	   etSms.setText(str);
    	    }
    	    inBroadcast();
    	 // формируем столбцы сопоставления
    	    String[] from = new String[] {  DB.COLUMN_NUMBER, DB.COLUMN_NAME, DB.COLUMN_SMS };
    	    int[] to = new int[] { R.id.itemtvNumber, R.id.itemtvContact, R.id.itemtvSms};
    SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,  R.layout.item,cursor, from, to);
    lvMain=(ListView)findViewById(R.id.lvMain);
    lvMain.setAdapter(adapter);
   
    // добавляем контекстное меню к списку
    registerForContextMenu(lvMain);
 
	}
       public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
   		// TODO Auto-generated method stub
   		if (isChecked){
   			on=true;
   			smsText();
   		Log.d(LOG_TAG, "on=true");
   		
   		}else
   		if (!isChecked){
   			on=false;
   		Log.d(LOG_TAG, "on=false");
   		}
   	}
	
	public void onClickAdd(View v){
	//	 Log.d(LOG_TAG,"onClickAdd");
		   Intent contactPickerIntent = new Intent(Intent.ACTION_PICK,
               android.provider.ContactsContract.Contacts.CONTENT_URI);
       startActivityForResult(contactPickerIntent, CONTACT_PICK_RESULT);
	  }
	public void onClickTest(View v){
		//Log.d(LOG_TAG,"array="+texts.size());
	}
	 
	 protected void onActivityResult(int requestCode, int resultCode,
             Intent data) {

if (resultCode == RESULT_OK) {

switch (requestCode) {
case CONTACT_PICK_RESULT:
Uri contactData = data.getData();
Cursor c = getContentResolver().query(contactData, null, null, null, null);
if (c.moveToNext()) {
 mContactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
 mContactName = c.getString(c.getColumnIndexOrThrow(
         ContactsContract.Contacts.DISPLAY_NAME));

 String hasPhone = c.getString(c.getColumnIndex(
         ContactsContract.Contacts.HAS_PHONE_NUMBER));

// Log.d(LOG_TAG, "name: " + mContactName);
// Log.d(LOG_TAG, "hasPhone:" + hasPhone);
 //Log.d(LOG_TAG, "contactId:" + mContactId);
 if (hasPhone.equalsIgnoreCase("1"))
 {
     Cursor phones = getContentResolver().query(
             ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
             null,
             ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ mContactId,
             null,
             null);

     while (phones.moveToNext()) {
         mPhoneNumber = phones.getString(phones.getColumnIndex(
                 ContactsContract.CommonDataKinds.Phone.NUMBER));
       
        // tvTask1.setText(mPhoneNumber);
       addBasa();
     }
     phones.close();
 }
}
break;
}

} else {
}

}
	private void addBasa() {
		  // добавляем запись
		smsText();
	//	Log.d(LOG_TAG, "EDIT_TEXT="+smsText);
	    db.addRec(mPhoneNumber, smsText, mContactName, sms);
	    // обновляем курсор
	    cursor.requery();
	    inBroadcast();
	  //  Log.d(LOG_TAG,"arrayADD="+texts.size());
	}
	
	public void onCreateContextMenu(ContextMenu menu, View v,
		      ContextMenuInfo menuInfo) {
		    super.onCreateContextMenu(menu, v, menuInfo);
		    menu.add(0, CM_DELETE_ID, 0, R.string.delete_record);
		  }
	
	public boolean onContextItemSelected(MenuItem item) {
	    if (item.getItemId() == CM_DELETE_ID) {
	      // получаем из пункта контекстного меню данные по пункту списка 
	      AdapterContextMenuInfo acmi = (AdapterContextMenuInfo) item.getMenuInfo();
	      // извлекаем id записи и удаляем соответствующую запись в БД
	      db.delRec(acmi.id);
	      // обновляем курсор
	      cursor.requery();
	      inBroadcast();
	      Log.d(LOG_TAG,"arrayDelete="+texts.size());
	      return true;
	    }
	    return super.onContextItemSelected(item);
	  }
	
	 protected void onDestroy() {
		    super.onDestroy();
		    // закрываем подключение при выходе
		    db.close();
		 //   on=false;
		    Log.d(LOG_TAG,"onDestroy="+on);
		  }
	
		private void inBroadcast() {
			smsText();
		//	Log.d(LOG_TAG,"smsText="+smsText);
		texts= new ArrayList<String>();
	  	  if(cursor.moveToFirst()){
	  		  do{
	  			  
	  				int iCursor= cursor.getColumnIndex(DB.COLUMN_NUMBER);
	  				String str=cursor.getString(iCursor);
	  				texts.add(str);
	  				//Log.d(LOG_TAG,"i="+str);
	  				
	  			  
	  		  }
	  		  while(cursor.moveToNext());
	  	  }
	  	Log.d(LOG_TAG,"array="+texts.size());
	  	for(int i=0; i<texts.size();i++){
	  //	Log.d(LOG_TAG,"arr[i]="+texts.get(i));
	  			
	  			}
		}
		private void smsText(){
			smsText=etSms.getText().toString();
		}
	
}
