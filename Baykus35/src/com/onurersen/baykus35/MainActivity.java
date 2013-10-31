package com.onurersen.baykus35;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.onurersen.baykus35.actionbar.ActionBar;
import com.onurersen.baykus35.actionbar.ActionBar.Action;
import com.onurersen.baykus35.actionbar.ActionBar.IntentAction;
import com.onurersen.baykus35.list.ItemAdapter;
import com.onurersen.baykus35.list.Model;

public class MainActivity extends Activity {

	ListView listView;
	LinearLayout actionButtonLayout;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_PROGRESS);
        setContentView(R.layout.activity_main);
        configureActionBar();
        configureRouteList();
    }
    
    private void configureActionBar(){
    	final ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
        //actionBar.setHomeAction(new IntentAction(this, createIntent(this), R.drawable.ic_title_home_demo));
        actionBar.setTitle("Home");
        
        final Action shareAction = new IntentAction(this, createShareIntent(), R.drawable.ic_title_share_default);
        actionBar.addAction(shareAction);
        actionButtonLayout = (LinearLayout) actionBar
				.findViewById(R.id.actionbar_actions);
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
    
    private Intent createShareIntent() {
        final Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "Shared from the ActionBar widget.");
        return Intent.createChooser(intent, "Share");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
