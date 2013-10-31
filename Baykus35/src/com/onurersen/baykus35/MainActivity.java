package com.onurersen.baykus35;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.onurersen.baykus35.list.ItemAdapter;
import com.onurersen.baykus35.list.Model;

public class MainActivity extends Activity {

	ListView listView;
	LinearLayout actionButtonLayout;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureRouteList();
    }
    
    private void configureRouteList(){
    	Model.LoadModel();
        listView = (ListView) findViewById(R.id.listView);
        String[] ids = new String[Model.Items.size()];
        for (int i= 0; i < ids.length; i++){

            ids[i] = Integer.toString(i+1);
        }

        ItemAdapter adapter = new ItemAdapter(this,R.layout.row, ids);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
