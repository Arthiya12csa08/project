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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class search extends Activity {
	TextView enter;
	EditText book;
	ImageButton search;
	String sbook;
	ListView list;
	String[] a;
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.search);
	    Intent in = getIntent();
	      final String name = in.getStringExtra(("uname"));
	    enter=(TextView)findViewById(R.id.textView1);
	    book=(EditText)findViewById(R.id.editText1);
	    list=(ListView)findViewById(R.id.listView1);
	    search=(ImageButton)findViewById(R.id.imageButton1);
	    sbook=book.getText().toString();
        search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				//sbook = book.getText().toString();
				try {

					sbook = URLEncoder.encode(sbook, "UTF-8");
					String url = "http://10.100.9.159/while.php?Bookname="
							+ sbook.trim();
					System.out.println(url);
					pass_value_to_db get = new pass_value_to_db();
					get.execute(new String[] { url });

				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			 	}


			}
		});
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {

				// final String selected = ((TextView) view).getText()) ;
				// Intent i1 = new Intent(view.this, details.class);
					// //i1.putExtra("book",bname);
				// i1.putExtra("name", selected);
				// startActivity(i1);
				String x = ((TextView) v).getText().toString();
				//x = x.replace(":\\", " ");
				//a = x.split(":");
				String b = a[0].toString();
				Intent i1 = new Intent(search.this,seadetails.class);
				//i1.putExtra("book",bname);
				i1.putExtra("name",b);
				i1.putExtra("uname",name);
				startActivity(i1);
				//Toast.makeText(getApplicationContext(), b, Toast.LENGTH_SHORT)
						//.show();

			}
		});

	}
	public class pass_value_to_db extends AsyncTask<String, Void, String> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() { // TODO Auto-generated method stub
			dialog = new ProgressDialog(search.this);
			dialog.setTitle("Searching...");
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
			if (result.equals("f")) {

				// *******************************************************

				Toast.makeText(getApplicationContext(), " No books available",
						Toast.LENGTH_LONG).show();
			} else {
				String[] books = result.split("@");
				// String[] bid = result.split(":");
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(
						search.this, android.R.layout.simple_list_item_1, books);

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


