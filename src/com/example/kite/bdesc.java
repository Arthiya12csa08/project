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

import com.example.kite.view.pass_value_to_db;

import android.R.string;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class bdesc extends Activity {
	String data,pass;
	String[] id;
	ListView list;
	@Override
	  public void onCreate(Bundle icicle) {
	    super.onCreate(icicle);
	    setContentView(R.layout.bdesc);
	    list = (ListView) findViewById(R.id.bdetails);
	    data = getIntent().getExtras().getString("book");
	    id = data.split(":");
	    data.replace(":'%'"," ");
	    try {
			
		
			String url = "http://10.100.9.183/bdesc.php?Id="
					+ data.trim();
			System.out.println(url);
			pass_value_to_db get = new pass_value_to_db();
			get.execute(new String[] { url });

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

    




public class pass_value_to_db extends AsyncTask<String, Void, String> {

ProgressDialog dialog;

@Override
protected void onPreExecute() { // TODO Auto-generated method stub
	/*dialog = new ProgressDialog(bdesc.this);
	dialog.setTitle("Processing...");
	dialog.setMessage("Please wait.");
	dialog.setCancelable(false);
	dialog.show();*/
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
		String[] books = result.split("@");
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(
				bdesc.this, android.R.layout.simple_list_item_1,books);

		list.setAdapter(adapter);
		

		
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


