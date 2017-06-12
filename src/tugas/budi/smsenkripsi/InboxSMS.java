package tugas.budi.smsenkripsi;

import java.util.ArrayList;
import java.util.List;

import tugas.budi.enkripsi.R;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class InboxSMS extends Activity {

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox_sms);
        final ListView list = (ListView) findViewById(R.id.list);
        final Button btnKey = (Button) findViewById(R.id.btnKey);
        btnKey.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				final EditText edtKey= (EditText) findViewById(R.id.edtKey);
		        List<String> msgs = getSMS(edtKey.getText().toString().trim());
		        if(msgs.isEmpty()){
		        	msgs.add("Tidak Ada SMS Yang Bisa Dipecahkan Dengan Key Tersebut");
		        }
		        ArrayAdapter<String> smsAdapter = new ArrayAdapter<String>(getBaseContext(),
		        		android.R.layout.simple_list_item_1 ,msgs);
		         list.setAdapter(smsAdapter);
				
			}
		});

      
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_inbox_sms, menu);
        return true;
    }
    
    public List<String>  getSMS(String strkey) {      
        List<String> list = new ArrayList<String>();
        Uri uri = Uri.parse("content://sms/inbox");
        Cursor c = null;
        try{
            c = getApplicationContext().getContentResolver().query(uri, null, null ,null,null); 
        }catch(Exception e){
            e.printStackTrace();
        }
        try{
            for (boolean hasData = c.moveToFirst(); hasData; hasData = c.moveToNext()) {

                final String noHP = c.getString(c.getColumnIndex("address"));
                final String msg = c.getString(c.getColumnIndexOrThrow("body"));

               String plainTeks= Kripto.deskripsi(msg,strkey);;
               if(!plainTeks.equalsIgnoreCase("ERROR")){
            	   list.add(noHP + "\nText: " + plainTeks);
            	   
               }       
               
            }
        }catch(Exception e){
            e.printStackTrace();
        }
     c.close(); 
     return list;
    }
}
