package com.example.kite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class details extends Activity{
	String data,pass;
	String[] id;
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.details);
	    Intent in = getIntent();
	    String name = in.getStringExtra(("name"));
	    //data = getIntent().getExtras().getString("book");
	    //id = data.split(":");
	    //pass=data.replace(":'%'"," ");
	    Toast.makeText(details.this,name, Toast.LENGTH_LONG).show();
	    
	    
	}

}
