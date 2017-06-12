package tugas.budi.smsenkripsi;

import tugas.budi.enkripsi.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class SMSKripto extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smskripto);
        final Button btnKirim = (Button) findViewById(R.id.btnKirim);
        final Button btnBaca = (Button) findViewById(R.id.btnBaca);
        
        btnKirim.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    Intent intent = new Intent(getBaseContext(), SendSMS.class);
			     startActivity(intent);	
			}
		});
        
        btnBaca.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			    Intent intent = new Intent(getBaseContext(), InboxSMS.class);
			     startActivity(intent);	
				
			}
		});
       
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_smskripto, menu);
        return true;
    }
}
