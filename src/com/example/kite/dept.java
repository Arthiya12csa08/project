package com.example.kite;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class dept extends Activity {
	ListView list;
		
		public void onCreate(Bundle icicle) {
		    super.onCreate(icicle);
		    setContentView(R.layout.dept);
		    list=(ListView)findViewById(R.id.list);
		    String[] values = new String[] { "ComputerScience And Engineering","Information Technology","Electronics and Communication Engineering","Mechanical Engineering","Civil Engineering"};
		    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1, values);
		    list.setAdapter(adapter);
		  


		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
		    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {   
		    	
		        switch( position )
			    {
			       case 0:  Intent i1 = new Intent(dept.this, cse.class);     
			                startActivity(i1);
			                break;
			       case 1:  Intent i2 = new Intent(dept.this, it.class);     
			                startActivity(i2);
			                break;
			       case 2:  Intent i3 = new Intent(dept.this, ece.class);     
			                startActivity(i3);
			                break;
			       case 3:  Intent i4 = new Intent(dept.this, me.class);     
			                startActivity(i4);
			                break;
			       case 4:  Intent i5 = new Intent(dept.this, ce.class);     
			                startActivity(i5);
			                break;
			      }

		    }
		});
		    
		 
 }
}

