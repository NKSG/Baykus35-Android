package com.onurersen.baykus35.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.onurersen.baykus35.R;
import com.onurersen.baykus35.list.route.RouteItemAdapter;
import com.onurersen.baykus35.list.route.RouteModel;
import com.onurersen.baykus35.sql.SQLiteDatabaseHelper;

/**
 * 
 * @author onurersen
 * 
 */
public class RoutesActivity extends Activity {

	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_routes);
		configureRouteList();
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				Intent intent = new Intent(RoutesActivity.this, TariffsActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				intent.putExtra("route", Integer.parseInt(parent.getItemAtPosition(position).toString()));
				startActivity(intent);
			}
		});
	}

	private void configureRouteList() {
		SQLiteDatabaseHelper dbHelper = new SQLiteDatabaseHelper(this);
		RouteModel.LoadModel(dbHelper);
		listView = (ListView) findViewById(R.id.listView);
		String[] ids = new String[RouteModel.Items.size()];
		for (int i = 0; i < ids.length; i++) {
			ids[i] = Integer.toString(i + 1);
		}
		RouteItemAdapter adapter = new RouteItemAdapter(this, R.layout.row_route, ids);
		listView.setAdapter(adapter);
	}

}
