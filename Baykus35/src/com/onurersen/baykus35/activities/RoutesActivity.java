package com.onurersen.baykus35.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.onurersen.baykus35.R;
import com.onurersen.baykus35.customization.CustomTypeFaceSpan;
import com.onurersen.baykus35.db.sql.SQLiteDatabaseHelper;
import com.onurersen.baykus35.list.route.RouteItemAdapter;
import com.onurersen.baykus35.list.route.RouteModel;
import com.onurersen.baykus35.utility.LogCat;

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
		customizeActionBar();
		assignRouteOnClick();
	}

	/**
	 * Sets Custom Text Font to ActionBar
	 */
	private void customizeActionBar() {
		
		switch (getResources().getDisplayMetrics().densityDpi) {
		case DisplayMetrics.DENSITY_LOW:
		    LogCat.INSTANCE.info(this.getClass().getName(), "Screen Size : LDPI");
		    break;
		case DisplayMetrics.DENSITY_MEDIUM:
			LogCat.INSTANCE.info(this.getClass().getName(), "Screen Size : MDPI");
		    break;
		case DisplayMetrics.DENSITY_HIGH:
			LogCat.INSTANCE.info(this.getClass().getName(), "Screen Size : HDPI");
		    break;
		case DisplayMetrics.DENSITY_XHIGH:
			LogCat.INSTANCE.info(this.getClass().getName(), "Screen Size : XDPI");
		    break;
		case DisplayMetrics.DENSITY_XXHIGH:
			LogCat.INSTANCE.info(this.getClass().getName(), "Screen Size : XXDPI");
		    break;
		}
		
		final ActionBar actionBar = getActionBar();
		SpannableString s = new SpannableString(getString(R.string.app_name));
		s.setSpan(new CustomTypeFaceSpan(this, "CustomFont"), 0, s.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		actionBar.setTitle(s);
	}

	/**
	 * Populates defined Routes into Listview
	 */
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

	/**
	 * Starts TariffsActivity to show Tariffs assigned to selected Route
	 */
	private void assignRouteOnClick() {
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

}
