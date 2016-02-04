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

import com.example.kite.R;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class reg extends Activity {
	 TextView name,reg,batch,pwd,cpwd,dept,hint;
	 EditText ename,ereg,ebatch,epwd,ecpwd,ehint;
	 Spinner sdept;
	 Button register;
	 String pwd_value,cpwd_value,name_value,reg_value,batch_value,hint_value,dept_value;
	 //ProgressBar preg;
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.reg);
	    name = (TextView) findViewById(R.id.name);
	    reg = (TextView) findViewById(R.id.regno);
	    batch = (TextView) findViewById(R.id.batch);
	    dept=(TextView) findViewById(R.id.dept);
	    pwd = (TextView) findViewById(R.id.pwd);
	    cpwd = (TextView) findViewById(R.id.cpwd);
	    hint = (TextView) findViewById(R.id.hint);
	    ename = (EditText) findViewById(R.id.ename);
	    ereg = (EditText) findViewById(R.id.eregno);
	    ebatch = (EditText) findViewById(R.id.ebatch);
	    epwd = (EditText) findViewById(R.id.epwd);
	    ecpwd = (EditText) findViewById(R.id.ecpwd);
	    ehint = (EditText) findViewById(R.id.ehint);
	    sdept = (Spinner) findViewById(R.id.sdept);
	    register = (Button) findViewById(R.id.reg);
	   // preg=(ProgressBar) findViewById(R.id.preg);
	    //preg.setVisibility(View.GONE);
	    
	    
	    register.setOnClickListener(new View.OnClickListener() {
	    	
			@Override
			public void onClick(View v) {
				name_value=ename.getText().toString();
				reg_value=ereg.getText().toString();
				batch_value=ebatch.getText().toString();
			    pwd_value = epwd.getText().toString();
				cpwd_value = ecpwd.getText().toString();
				hint_value=ehint.getText().toString();
				dept_value=sdept.getSelectedItem().toString();
				try {
					name_value = URLEncoder.encode(name_value, "UTF-8");
					reg_value = URLEncoder.encode(reg_value, "UTF-8");
					batch_value = URLEncoder.encode(batch_value, "UTF-8");
					pwd_value = URLEncoder.encode(pwd_value, "UTF-8");
					hint_value = URLEncoder.encode(hint_value, "UTF-8");
					dept_value = URLEncoder.encode(dept_value, "UTF-8");
					if(pwd_value.equals(cpwd_value))
					{	
						if("".equalsIgnoreCase(name_value) 
							   || "".equalsIgnoreCase(reg_value)
							   || "".equalsIgnoreCase(batch_value)
							   || "".equalsIgnoreCase(pwd_value)
							   || "".equalsIgnoreCase(cpwd_value)
							   || "".equalsIgnoreCase(hint_value)
							   || "".equalsIgnoreCase(dept_value))
							{
							    Toast.makeText(reg.this, "All Fields Required.", 
							         Toast.LENGTH_SHORT).show();
							}
						else
						{
							//preg.setVisibility(View.VISIBLE);
							Toast.makeText(getApplicationContext(),"Registered successfully. . .",Toast.LENGTH_LONG).show();
							
							String url = "http://10.100.9.183/reg.php?Name="
									+ name_value.trim() + "&RegisterNo="
									+ reg_value.trim() + "&Batch=" + batch_value.trim() + "&Department=" + dept_value.trim()
									+"&Password=" + pwd_value.trim() + "&Hint=" + hint_value.trim();
							System.out.println(url);
							pass_value_to_db get = new pass_value_to_db();
							get.execute(new String[] { url });
							Intent t = new Intent(reg.this, list.class);
							startActivity(t);

						}
					}
					else
					{
						Toast.makeText(getApplicationContext(),"Password does not match",Toast.LENGTH_LONG).show();
						 
					}
					
					
					
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
			dialog = new ProgressDialog(reg.this);
			dialog.setTitle("Processing...");
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
			} else {
				System.out.println(result);

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

}

	
	
