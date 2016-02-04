package com.example.kite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class list extends Activity{
	Button book,blog,lib;
	@Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.list);
	    book = (Button) findViewById(R.id.book);
	    blog = (Button) findViewById(R.id.blog);
	    lib = (Button) findViewById(R.id.lib);
	    book.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(list.this,books.class);
				startActivity(i);  
			}
		});
	    blog.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				
				Intent i = new Intent(list.this,dept.class);
				startActivity(i);  
			}
		});

	}

}
