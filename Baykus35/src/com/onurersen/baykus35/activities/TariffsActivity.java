package com.onurersen.baykus35.activities;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.onurersen.baykus35.R;
import com.onurersen.baykus35.customization.CustomArrayAdapter;
import com.onurersen.baykus35.db.sql.SQLiteDatabaseHelper;
import com.onurersen.baykus35.list.tariff.TariffItemAdapter;
import com.onurersen.baykus35.list.tariff.TariffModel;

/**
 * 
 * @author onurersen
 * 
 */
public class TariffsActivity extends FragmentActivity implements ActionBar.OnNavigationListener {

	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	private int selectedRouteId = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tariff);

		Bundle bundle = getIntent().getExtras();
		setSelectedRouteId(bundle.getInt("route"));

		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowTitleEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		ArrayAdapter<String> adapter = new CustomArrayAdapter(this, actionBar.getThemedContext(),
				android.R.layout.simple_list_item_1, new String[] { getString(R.string.title_tariff),
						getString(R.string.title_route), getString(R.string.title_info), });
		actionBar.setListNavigationCallbacks(adapter, this);
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getActionBar().setSelectedNavigationItem(savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar().getSelectedNavigationIndex());
	}

	@Override
	public boolean onNavigationItemSelected(int position, long id) {
		Fragment fragment = new TariffSectionFragment();
		Bundle args = new Bundle();
		args.putInt(TariffSectionFragment.ARG_SECTION_NUMBER, position + 1);
		args.putInt(TariffSectionFragment.ARG_SELECTED_ROUTE_ID, getSelectedRouteId());
		fragment.setArguments(args);
		getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
		return true;
	}

	public static class TariffSectionFragment extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";
		public static final String ARG_SELECTED_ROUTE_ID = "route";

		ListView listView;

		public TariffSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_tariff, container, false);
			int routeId = getArguments().getInt(ARG_SELECTED_ROUTE_ID);
			configureTariffList(routeId, rootView);
			return rootView;
		}

		private void configureTariffList(int routeId, View rootView) {
			SQLiteDatabaseHelper dbHelper = new SQLiteDatabaseHelper(getActivity());
			TariffModel.LoadModel(dbHelper, routeId);
			listView = (ListView) rootView.findViewById(R.id.listView);
			String[] ids = new String[TariffModel.Items.size()];
			for (int i = 0; i < ids.length; i++) {
				ids[i] = Integer.toString(i + 1);
			}
			TariffItemAdapter adapter = new TariffItemAdapter(getActivity(), R.layout.row_tariff, ids);
			listView.setAdapter(adapter);
		}
	}

	public static class MapSectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public MapSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_map, container, false);
			TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
			return rootView;
		}
	}

	public static class AboutSectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public static final String ARG_SECTION_NUMBER = "section_number";

		public AboutSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_about, container, false);
			TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
			return rootView;
		}
	}

	public int getSelectedRouteId() {
		return selectedRouteId;
	}

	public void setSelectedRouteId(int selectedRouteId) {
		this.selectedRouteId = selectedRouteId;
	}

}
