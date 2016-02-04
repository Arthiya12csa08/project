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

public class post extends Activity {
	TextView bname,author,pub,edi,desc,cost;
	EditText ebname,eauthor,epub,eedi,edesc,ecost;
	String sbname,sauthor,spub,sedi,sdesc,scost,ssbook;
	Spinner sbook;
	Button post;
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.post);
	    bname=(TextView)findViewById(R.id.bname);
	    author=(TextView)findViewById(R.id.author);
	   pub=(TextView)findViewById(R.id.pub);
	    edi=(TextView)findViewById(R.id.edi);
	    //desc=(TextView)findViewById(R.id.desc);
	    cost=(TextView)findViewById(R.id.cost);
	    sbook = (Spinner) findViewById(R.id.sbook);
	    //ebname=(EditText)findViewById(R.id.ebname);
	    eauthor=(EditText)findViewById(R.id.eauthor);
	    epub=(EditText)findViewById(R.id.epub);
	    eedi=(EditText)findViewById(R.id.eedi);
	    //edesc=(EditText)findViewById(R.id.edesc);
	    ecost=(EditText)findViewById(R.id.ecost);
	    post=(Button)findViewById(R.id.post);
        post.setOnClickListener(new View.OnClickListener() {
	    	
			@Override
			public void onClick(View v) {
				//sbname=ebname.getText().toString();
				sauthor=eauthor.getText().toString();
				spub=epub.getText().toString();
			    sedi = eedi.getText().toString();
				ssbook = sbook.getSelectedItem().toString();
				scost=ecost.getText().toString();
				
					
					if("".equalsIgnoreCase(scost) 
						   || "".equalsIgnoreCase(sauthor)
						   || "".equalsIgnoreCase(spub)
						   || "".equalsIgnoreCase(sedi)
						   || "".equalsIgnoreCase(ssbook))
						{
						    Toast.makeText(post.this, "All Fields Required.", 
						         Toast.LENGTH_SHORT).show();
						}
					else
					{
						//preg.setVisibility(View.VISIBLE);
						Toast.makeText(getApplicationContext(),"Registered successfully. . .",Toast.LENGTH_LONG).show();
						Intent i1 = new Intent(post.this, list.class);     
		                startActivity(i1);
					}
				
				
				
				try {
					//sbname = URLEncoder.encode(sbname, "UTF-8");
					sauthor = URLEncoder.encode(sauthor, "UTF-8");
					spub = URLEncoder.encode(spub, "UTF-8");
					sedi = URLEncoder.encode(sedi, "UTF-8");
					ssbook = URLEncoder.encode(ssbook, "UTF-8");
					scost = URLEncoder.encode(scost, "UTF-8");
					
					String url = "http://10.100.9.183/post.php?Bookname="
							+ ssbook.trim() + "&Authorname="
							+ sauthor.trim() + "&Publication=" + spub.trim() + "&Edition=" + sedi.trim()
							+ "&Cost=" + scost.trim();
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
			dialog = new ProgressDialog(post.this);
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


