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

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.onurersen.baykus35.R;
import com.onurersen.baykus35.customization.CustomArrayAdapter;
import com.onurersen.baykus35.db.sql.SQLiteDatabaseHelper;
import com.onurersen.baykus35.list.tariff.TariffItemAdapter;
import com.onurersen.baykus35.list.tariff.TariffModel;
import com.onurersen.baykus35.utility.AlertUtil;
import com.onurersen.baykus35.utility.LogCat;

/**
 * 
 * @author onurersen
 * 
 */
public class TariffsActivity extends FragmentActivity implements ActionBar.OnNavigationListener {

	private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
	private int selectedRouteId = -1;

	private int DROPDOWN_INDEX_TARIFF = 0;
	private int DROPDOWN_INDEX_MAP = 1;
	private int DROPDOWN_INDEX_ABOUT = 2;

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
		if (position == DROPDOWN_INDEX_TARIFF) {
			Fragment fragment = new TariffSectionFragment();
			Bundle args = new Bundle();
			args.putInt(TariffSectionFragment.ARG_SECTION_NUMBER, position + 1);
			args.putInt(TariffSectionFragment.ARG_SELECTED_ROUTE_ID, getSelectedRouteId());
			fragment.setArguments(args);
			getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
		} else if (position == DROPDOWN_INDEX_MAP) {
			Fragment fragment = new MapSectionFragment();
			Bundle args = new Bundle();
			args.putInt(TariffSectionFragment.ARG_SECTION_NUMBER, position + 1);

			fragment.setArguments(args);
			getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
		} else if (position == DROPDOWN_INDEX_ABOUT) {
			Fragment fragment = new AboutSectionFragment();
			Bundle args = new Bundle();
			args.putInt(TariffSectionFragment.ARG_SECTION_NUMBER, position + 1);

			fragment.setArguments(args);
			getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
		}
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
		SupportMapFragment fm;
		GoogleMap map;
		private static View rootView;

		final LatLng CENTER = new LatLng(43.661049, -79.400917);

		class Data {
			public Data(float lng, float lat, String title, String snippet) {
				super();
				this.lat = (float) lat;
				this.lng = (float) lng;
				this.title = title;
				this.snippet = snippet;
			}

			float lat;
			float lng;
			String title;
			String snippet;
		}

		Data[] data = {
				new Data(-79.400917f, 43.661049f, "New New College Res", "Residence building (new concrete high-rise)"),
				new Data(-79.394524f, 43.655796f, "Baldwin Street", "Here be many good restaurants!"),
				new Data(-79.402206f, 43.657688f, "College St",
						"Many discount computer stores if you forgot a cable or need to buy hardware."),
				new Data(-79.390381f, 43.659878f, "Queens Park Subway",
						"Quickest way to the north-south (Yonge-University-Spadina) subway/metro line"),
				new Data(-79.403732f, 43.666801f, "Spadina Subway",
						"Quickest way to the east-west (Bloor-Danforth) subway/metro line"),
				new Data(-79.399696f, 43.667873f, "St George Subway back door",
						"Token-only admittance, else use Spadina or Bedford entrances!"),
				new Data(-79.384163f, 43.655083f, "Eaton Centre (megamall)",
						"One of the largest indoor shopping centres in eastern Canada. Runs from Dundas to Queen."), };

		public MapSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

			if (rootView != null) {
				ViewGroup parent = (ViewGroup) rootView.getParent();
				if (parent != null)
					parent.removeView(rootView);
			}
			try {
				if (rootView == null)
					rootView = inflater.inflate(R.layout.fragment_map, container, false);
			} catch (Exception exception) {
				LogCat.INSTANCE.error(this.getClass().getName(), exception.getMessage());
				// AlertUtil.messageAlert(getActivity().getApplicationContext(),
				// getActivity().getString(R.string.map_error_title),
				// exception.getMessage());
			}

			for (Data d : data) {
				LatLng location = new LatLng(d.lat, d.lng);
				getMap().addMarker(new MarkerOptions().position(location).title(d.title).snippet(d.snippet));
			}

			getMap().setIndoorEnabled(true);
			// getMap().setMyLocationEnabled(true);
			getMap().moveCamera(CameraUpdateFactory.zoomTo(14));
			getMap().animateCamera(CameraUpdateFactory.newLatLng(CENTER), 1750, null);

			return rootView;
		}

		public SupportMapFragment getFm() {
			if (fm == null) {
				fm = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.map);
			}
			return fm;
		}

		public void setFm(SupportMapFragment fm) {
			this.fm = fm;
		}

		public GoogleMap getMap() {
			if (map == null) {
				map = getFm().getMap();
			}
			return map;
		}

		public void setMap(GoogleMap map) {
			this.map = map;
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
