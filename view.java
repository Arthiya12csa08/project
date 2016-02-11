package com.example.kite;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.kite.post.pass_value_to_db;

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
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class view extends Activity {
	ListView list;
	Spinner book;
	Button search;
	String sbook,b,name;
	String[] books,a;
	//int[] b;

	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		setContentView(R.layout.view);
		Integer imgid=R.drawable.del;
		//CustomListAdapter adapter=new CustomListAdapter(view.this, itemname, imgid);
		Intent in = getIntent();
	      name = in.getStringExtra(("uname"));
		list = (ListView) findViewById(R.id.bookpost);
		book = (Spinner) findViewById(R.id.book);
		search = (Button) findViewById(R.id.search);
		search.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				sbook = book.getSelectedItem().toString();
				try {

					sbook = URLEncoder.encode(sbook, "UTF-8");
					String url = "http://10.100.9.183/books.php?Bookname="
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
				a = x.split(":");
				b = a[0].toString();
				Intent i1 = new Intent(view.this, details.class);
				//i1.putExtra("book",bname);
				i1.putExtra("name",b);
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
			dialog = new ProgressDialog(view.this);
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
				books = result.split("@");
				// String[] bid = result.split(":");
				ArrayAdapter<String> adapter = new ArrayAdapter<String>(
						view.this, android.R.layout.simple_list_item_1, books);

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
