package com.example.kite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class lib extends Activity {
	TextView search,adsearch;
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.lib);
	    search=(TextView)findViewById(R.id.search);
	    adsearch=(TextView)findViewById(R.id.adsearch);
search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(lib.this,search.class);
				//i.putExtra("uname",name);
				startActivity(i);  
			}
		});
	    adsearch.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(lib.this,akey.class);
				//i.putExtra("uname",name);
				startActivity(i);  
			}
		});
	}

}
