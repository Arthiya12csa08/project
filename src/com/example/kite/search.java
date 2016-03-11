package com.example.kite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class search extends Activity {
	TextView enter;
	EditText book;
	ImageButton search;
	String sbook,a;
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.search);
	    enter=(TextView)findViewById(R.id.textView1);
	    book=(EditText)findViewById(R.id.editText1);
	    search=(ImageButton)findViewById(R.id.imageButton1);
	    sbook=book.getText().toString();
        search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(search.this,seadetails.class);
				i.putExtra("a",sbook);
				startActivity(i);  
			}
		});
	}

}
