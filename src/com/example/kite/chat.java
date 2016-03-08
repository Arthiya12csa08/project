
package com.example.kite;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class chat extends Activity{
	
	ImageButton Send;
	EditText mess;
	TextView no,num;
	
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.chat);
	    Intent in = getIntent();
	    String name = in.getStringExtra(("name"));
	    Send=(ImageButton) findViewById(R.id.send);
		no=(TextView) findViewById(R.id.no);
		mess=(EditText) findViewById(R.id.mess);
		num=(TextView) findViewById(R.id.to);
		no.setText(name);

        Send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			String phoneNo=no.getText().toString();
			String SMS=mess.getText().toString();
			
			try {
					SmsManager smsManager=SmsManager.getDefault();
					smsManager.sendTextMessage(phoneNo, null, SMS, null, null);
					Toast.makeText(getApplicationContext(),"SMS Sent!...",Toast.LENGTH_LONG).show();
					
			} catch (Exception e) {				
					Toast.makeText(getApplicationContext(),	"SMS faild, please try again later!",Toast.LENGTH_LONG).show();
					e.printStackTrace();
			}
			}
		});
		 
	}
	
}
