package com.onurersen.baykus35;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

import com.onurersen.baykus35.list.route.RouteItemAdapter;
import com.onurersen.baykus35.list.route.RouteModel;
import com.onurersen.baykus35.sql.SQLiteDatabaseHelper;

/**
 * 
 * @author onurersen
 *
 */
public class MainActivity extends Activity {

	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		configureRouteList();
	}

	private void configureRouteList() {
		SQLiteDatabaseHelper dbHelper = new SQLiteDatabaseHelper(this);
		RouteModel.LoadModel(dbHelper);
		listView = (ListView) findViewById(R.id.listView);
		String[] ids = new String[RouteModel.Items.size()];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = Integer.toString(i + 1);
		}
		RouteItemAdapter adapter = new RouteItemAdapter(this, R.layout.row, ids);
		listView.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
