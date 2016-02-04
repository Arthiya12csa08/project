package com.example.kite;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

public class log extends ActionBarActivity {
	EditText un,pd;
	TextView user,pwd;
	Button submit,signup;
	String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);
       un=(EditText)findViewById(R.id.un);
       user=(TextView)findViewById(R.id.user);
       pwd=(TextView)findViewById(R.id.pwd);
       pd= (EditText) findViewById(R.id.pd); 
       submit=(Button)findViewById(R.id.submit);
      // signup=(Button)findViewById(R.id.signup);
       
       submit.setOnClickListener(new View.OnClickListener() {
   		
   		@Override
   		public void onClick(View view) {
   			// TODO Auto-generated method stub
   			username = un.getText().toString();
			password = pd.getText().toString();
			try {
				username = URLEncoder.encode(username, "UTF-8");
				password = URLEncoder.encode(password, "UTF-8");
				String url = "http://10.100.9.183/log.php?Username="
						+ username.trim() + "&Password="
						+ password.trim();
				System.out.println(url);
				pass_value_to_db get = new pass_value_to_db();
				get.execute(new String[] { url });

			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	});

}

public class pass_value_to_db extends AsyncTask<String, Void, String> {

	ProgressDialog dialog;

	@Override
	protected void onPreExecute() { // TODO Auto-generated method stub
		dialog = new ProgressDialog(log.this);
		//dialog.setTitle("Processing...");
		dialog.setMessage("Please wait.");
		dialog.setCancelable(false);
		dialog.show();
	}

	@Override
	protected String doInBackground(String... urls) {
		String result = "";
		for (String url : urls) {
			InputStream is = null;
			try {

				HttpClient httpclient = new DefaultHttpClient();
				HttpPost httppost = new HttpPost(url);
				HttpResponse response = httpclient.execute(httppost);
				int status = response.getStatusLine().getStatusCode();
				Log.d("KG", "status=" + status);

				if (status == 200) {
					HttpEntity entity = response.getEntity();
					is = entity.getContent();
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(is, "iso-8859-1"), 8);
					String line = "";
					while ((line = reader.readLine()) != null) {
						result += line;
					}
					is.close();

					Log.v("KG", result);

				}
			} catch (Exception ex) {
				Log.e("Error", ex.toString());
			}
		}
		return result;
	}

	protected void onPostExecute(String result) {
		Log.v("KG", "output=" + result);
		result = result.trim(); //
		// Toast.makeText(getApplicationContext(), result, //
		// Toast.LENGTH_LONG).show();
		if (result.equals("false")) {

			// *******************************************************

			Toast.makeText(getApplicationContext(),
					" Please try again later...", Toast.LENGTH_LONG).show();
		} 
		else {
			Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
			Intent i = new Intent(log.this, list.class);
			 startActivity(i);

		}

		if (dialog != null)
			dialog.dismiss();

	}
}

@Override
public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
getMenuInflater().inflate(R.menu.main, menu);
return true;
}


   			//Intent a = new Intent(MainActivity.this, list.class);
   			//startActivity(a);  
   		
          
    }


   