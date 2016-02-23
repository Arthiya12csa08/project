package com.example.kite;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class cse extends Activity {
	TextView com,eng,date,idea,view;
	EditText da,id;
	Button post;
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.cse);
	    date=(TextView)findViewById(R.id.date);
	    com=(TextView)findViewById(R.id.co);
	    idea=(TextView)findViewById(R.id.idea);
	    view=(TextView)findViewById(R.id.vp);
	    eng=(TextView)findViewById(R.id.textView2);
	    id=(EditText)findViewById(R.id.ml);
	    da=(EditText)findViewById(R.id.editText1);
	    post=(Button)findViewById(R.id.button1);
	}
}
