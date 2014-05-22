package com.onurersen.baykus35.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LevelListDrawable;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.onurersen.baykus35.R;
import com.onurersen.baykus35.busstop.BusStopModel;
import com.onurersen.baykus35.customization.CustomArrayAdapter;
import com.onurersen.baykus35.db.data.ClsBusStops;
import com.onurersen.baykus35.db.sql.SQLiteDatabaseHelper;
import com.onurersen.baykus35.list.route.RouteItem;
import com.onurersen.baykus35.list.route.RouteModel;
import com.onurersen.baykus35.list.tariff.TariffItemAdapter;
import com.onurersen.baykus35.list.tariff.TariffModel;
import com.onurersen.baykus35.utility.GPSTracker;
import com.onurersen.baykus35.utility.LogCat;

/**
 * 
 * @author onurersen
 * 
 */
public class TariffsActivity extends FragmentActivity implements
		ActionBar.OnNavigationListener {

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

		ArrayAdapter<String> adapter = new CustomArrayAdapter(this,
				actionBar.getThemedContext(),
				android.R.layout.simple_list_item_1, new String[] {
						getString(R.string.title_tariff),
						getString(R.string.title_route),
						getString(R.string.title_info), });
		actionBar.setListNavigationCallbacks(adapter, this);
	}

	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
		if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
			getActionBar().setSelectedNavigationItem(
					savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		outState.putInt(STATE_SELECTED_NAVIGATION_ITEM, getActionBar()
				.getSelectedNavigationIndex());
	}

	@Override
	public boolean onNavigationItemSelected(int position, long id) {
		if (position == DROPDOWN_INDEX_TARIFF) {
			Fragment fragment = new TariffSectionFragment();
			Bundle args = new Bundle();
			args.putInt(TariffSectionFragment.ARG_SECTION_NUMBER, position + 1);
			args.putInt(TariffSectionFragment.ARG_SELECTED_ROUTE_ID,
					getSelectedRouteId());
			fragment.setArguments(args);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, fragment).commit();
		} else if (position == DROPDOWN_INDEX_MAP) {
			Fragment fragment = new MapSectionFragment();
			Bundle args = new Bundle();
			args.putInt(TariffSectionFragment.ARG_SECTION_NUMBER, position + 1);
			args.putInt(TariffSectionFragment.ARG_SELECTED_ROUTE_ID,
					getSelectedRouteId());
			fragment.setArguments(args);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, fragment).commit();
		} else if (position == DROPDOWN_INDEX_ABOUT) {
			Fragment fragment = new AboutSectionFragment();
			Bundle args = new Bundle();
			args.putInt(TariffSectionFragment.ARG_SECTION_NUMBER, position + 1);

			fragment.setArguments(args);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, fragment).commit();
		}
		return true;
	}

	/**
	 * Fragment to show Tariffs those belong to selected Route
	 * 
	 * @author onurersen
	 * 
	 */
	public static class TariffSectionFragment extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";
		public static final String ARG_SELECTED_ROUTE_ID = "route";

		ListView listView;

		public TariffSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_tariff,
					container, false);
			int routeId = getArguments().getInt(ARG_SELECTED_ROUTE_ID);
			configureTariffList(routeId, rootView);
			return rootView;
		}

		/**
		 * Populates Tariff ListView with selectedRouteId data
		 * 
		 * @param routeId
		 * @param rootView
		 */
		private void configureTariffList(int routeId, View rootView) {
			SQLiteDatabaseHelper dbHelper = new SQLiteDatabaseHelper(
					getActivity());
			TariffModel.LoadModel(dbHelper, routeId);
			RouteItem route = RouteModel.GetbyId(routeId);
			TextView firstStopStart = (TextView) rootView
					.findViewById(R.id.firstStopStartView);
			TextView lastStopStart = (TextView) rootView
					.findViewById(R.id.lastStopStartView);
			firstStopStart.setText(getActivity().getString(
					R.string.tariff_stop, route.getFirstStopName()));
			lastStopStart.setText(getActivity().getString(R.string.tariff_stop,
					route.getLastStopName()));
			listView = (ListView) rootView.findViewById(R.id.listView);
			String[] ids = new String[TariffModel.Items.size()];
			for (int i = 0; i < ids.length; i++) {
				ids[i] = Integer.toString(i + 1);
			}
			TariffItemAdapter adapter = new TariffItemAdapter(getActivity(),
					R.layout.row_tariff, ids);
			listView.setAdapter(adapter);
		}
	}

	/**
	 * Fragment to show MapView with BusStop markers
	 * 
	 * @author onurersen
	 * 
	 */
	public static class MapSectionFragment extends Fragment implements
			OnInfoWindowClickListener, OnMarkerClickListener {

		public static final String ARG_SECTION_NUMBER = "section_number";
		public static final String ARG_SELECTED_ROUTE_ID = "route";

		public static final int MENU_ITEM_ID_NAVIGATE = 0;

		SupportMapFragment fm;
		GoogleMap map;

		private static View rootView;

		ArrayList<MarkerData> markers = new ArrayList<MarkerData>();

		Marker selectedMarker;

		int selectedRouteId;

		/**
		 * Custom Class to hold MarkerData with latitude,longitude,title and
		 * snippet info
		 * 
		 * @author onurersen
		 * 
		 */
		class MarkerData {

			public MarkerData(double lng, double lat, String title,
					String snippet) {
				super();
				this.lat = (double) lat;
				this.lng = (double) lng;
				this.title = title;
				this.snippet = snippet;
			}

			double lat;
			double lng;
			String title;
			String snippet;

		}

		public MapSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			setSelectedRouteId(getArguments().getInt(ARG_SELECTED_ROUTE_ID));
			if (rootView != null) {
				ViewGroup parent = (ViewGroup) rootView.getParent();
				if (parent != null)
					parent.removeView(rootView);
			}
			try {
				rootView = inflater.inflate(R.layout.fragment_map, container,
						false);
			} catch (Exception exception) {
				LogCat.INSTANCE.error(this.getClass().getName(),
						exception.getMessage());
			}

			LocationManager locationManager = (LocationManager) getActivity()
					.getSystemService(LOCATION_SERVICE);
			if (!locationManager
					.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
				getActivity().getSupportFragmentManager().popBackStack();
				showGPSDisabledAlertToUser();
			} else {
				populateMap();
			}

			return rootView;
		}

		private void populateMap() {
			if (!isNetworkAvailable()) {
				Toast.makeText(getActivity(),
						getActivity().getText(R.string.network_unavailable),
						Toast.LENGTH_LONG).show();
			}
			fillBusStopMarkers(markers, getSelectedRouteId());

			for (MarkerData d : markers) {
				LatLng location = new LatLng(d.lat, d.lng);
				Drawable busStopMark = getResources().getDrawable(R.drawable.img_busstop);
				//busStopMark.setLevel(165324);
				BitmapDrawable bd=(BitmapDrawable) busStopMark.getCurrent();
				Bitmap b=bd.getBitmap();
				Bitmap busStopDoubleSize=Bitmap.createScaledBitmap(b, b.getWidth()*4,b.getHeight()*4, false);
				getMap().addMarker(
						new MarkerOptions()
								.icon(BitmapDescriptorFactory.fromBitmap(busStopDoubleSize))
								.position(location).title(d.title)
								.snippet(d.snippet));
			}

			UiSettings settings = getMap().getUiSettings();
			settings.setAllGesturesEnabled(true);
			settings.setZoomControlsEnabled(false);

			getMap().setIndoorEnabled(true);
			getMap().setBuildingsEnabled(true);
			getMap().setOnInfoWindowClickListener(this);
			getMap().setOnMarkerClickListener(this);
			getMap().setTrafficEnabled(true);
			getMap().setMyLocationEnabled(true);
			getMap().moveCamera(CameraUpdateFactory.zoomTo(14));
			getMap().animateCamera(
					CameraUpdateFactory.newLatLng(new LatLng(
							markers.get(0).lat, markers.get(0).lng)), 1750,
					null);

			getMap().setInfoWindowAdapter(new InfoWindowAdapter() {
				@Override
				public View getInfoWindow(Marker marker) {
					return null;
				}

				@Override
				public View getInfoContents(Marker marker) {
					View v = getActivity().getLayoutInflater().inflate(
							R.layout.info_window, null);
					TextView tvLat = (TextView) v
							.findViewById(R.id.busStopTitleView);
					TextView tvLng = (TextView) v
							.findViewById(R.id.busStopDescriptionView);
					tvLat.setText(marker.getTitle());
					tvLng.setText(marker.getSnippet());
					return v;
				}
			});
			registerForContextMenu(rootView);
		}

		@Override
		public void onActivityResult(int requestCode, int resultCode,
				Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			LocationManager locationManager = (LocationManager) getActivity()
					.getSystemService(LOCATION_SERVICE);
			if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
				populateMap();
			} else {
				showGPSDisabledAlertToUser();
			}
		}

		/**
		 * Method checks if internet connection is available or not
		 * 
		 * @return
		 */
		private boolean isNetworkAvailable() {
			ConnectivityManager connectivityManager = (ConnectivityManager) getActivity()
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo activeNetworkInfo = connectivityManager
					.getActiveNetworkInfo();
			return activeNetworkInfo != null && activeNetworkInfo.isConnected();
		}

		/**
		 * Method to return last known geo location of the user
		 * 
		 * @return
		 */
		private Location getLastKnownLocation() {
			LocationManager lm = (LocationManager) getActivity()
					.getSystemService(Context.LOCATION_SERVICE);
			List<String> providers = lm.getProviders(true);
			Location location = null;
			for (int i = providers.size() - 1; i >= 0; i--) {
				location = lm.getLastKnownLocation(providers.get(i));
				if (location != null)
					break;
			}
			return location;
		}

		/**
		 * Method to alert user about status of GPS Service on device
		 */
		private void showGPSDisabledAlertToUser() {
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					getActivity());
			alertDialogBuilder
					.setMessage(
							getActivity().getString(R.string.enable_gps_text))
					.setCancelable(false)
					.setPositiveButton(
							getActivity()
									.getString(R.string.go_to_gps_settings),
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int id) {
									Intent callGPSSettingIntent = new Intent(
											android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
									startActivityForResult(
											callGPSSettingIntent, 1);
								}
							});
			alertDialogBuilder.setNegativeButton(
					getActivity().getString(R.string.cancel_action),
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							getMap().clear();
						}
					});
			AlertDialog alert = alertDialogBuilder.create();
			alert.show();
		}

		/**
		 * Method selects and populates Bus Stop Marker List
		 * 
		 * @param markers
		 * @param selectedRouteId
		 */
		private void fillBusStopMarkers(ArrayList<MarkerData> markers,
				int selectedRouteId) {
			SQLiteDatabaseHelper dbHelper = new SQLiteDatabaseHelper(
					getActivity());
			BusStopModel.LoadModel(dbHelper, selectedRouteId);
			List<ClsBusStops> items = BusStopModel.Items;
			Location currentLocation = null;
			GPSTracker tracker = new GPSTracker(getActivity());
			boolean isLocationAvailable = tracker.canGetLocation();
			if (isLocationAvailable) {
				currentLocation = new Location("CurrentLocation");
				currentLocation.setLatitude(tracker.getLatitude());
				currentLocation.setLongitude(tracker.getLongitude());
			}
			if (currentLocation != null) {
				Location markerLocation = new Location("BusStopMarker");
				for (ClsBusStops clsBusStop : items) {
					markerLocation.setLatitude(clsBusStop.getLatitude());
					markerLocation.setLongitude(clsBusStop.getLongitude());
					float distance = currentLocation.distanceTo(markerLocation);
					if (distance > 1000) {
						markers.add(new MarkerData(clsBusStop.getLongitude(),
								clsBusStop.getLatitude(), clsBusStop
										.getStopName(), getActivity()
										.getString(
												R.string.distance_kilometers,
												distance / 1000)));
					} else {
						markers.add(new MarkerData(clsBusStop.getLongitude(),
								clsBusStop.getLatitude(), clsBusStop
										.getStopName(), getActivity()
										.getString(R.string.distance_meters,
												distance)));
					}
				}
			}
		}

		@Override
		public void onCreateContextMenu(ContextMenu menu, View v,
				ContextMenuInfo menuInfo) {
			super.onCreateContextMenu(menu, v, menuInfo);
			menu.setHeaderTitle(getSelectedMarker().getTitle());
			menu.add(0, v.getId(), 0,
					getActivity().getString(R.string.take_me_to_this_stop));
		}

		@Override
		public boolean onMarkerClick(Marker marker) {
			setSelectedMarker(marker);
			return false;
		}

		@Override
		public boolean onContextItemSelected(MenuItem item) {
			Intent intent = new Intent(Intent.ACTION_VIEW,
					Uri.parse("google.navigation:q="
							+ getSelectedMarker().getPosition().latitude + ","
							+ getSelectedMarker().getPosition().longitude));
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(intent);
			return true;
		}

		@Override
		public void onInfoWindowClick(Marker marker) {
			getActivity().openContextMenu(rootView);
		}

		/**
		 * @return the fm
		 */
		public SupportMapFragment getFm() {
			if (fm == null) {
				fm = (SupportMapFragment) getActivity()
						.getSupportFragmentManager().findFragmentById(R.id.map);
			}
			return fm;
		}

		/**
		 * @param fm
		 *            the fm to set
		 */
		public void setFm(SupportMapFragment fm) {
			this.fm = fm;
		}

		/**
		 * @return the map
		 */
		public GoogleMap getMap() {
			if (map == null) {
				map = getFm().getMap();
			}
			return map;
		}

		/**
		 * @param map
		 *            the map to set
		 */
		public void setMap(GoogleMap map) {
			this.map = map;
		}

		/**
		 * @return the selectedMarker
		 */
		public Marker getSelectedMarker() {
			return selectedMarker;
		}

		/**
		 * @param selectedMarker
		 *            the selectedMarker to set
		 */
		public void setSelectedMarker(Marker selectedMarker) {
			this.selectedMarker = selectedMarker;
		}

		/**
		 * @return the selectedRouteId
		 */
		public int getSelectedRouteId() {
			return selectedRouteId;
		}

		/**
		 * @param selectedRouteId
		 *            the selectedRouteId to set
		 */
		public void setSelectedRouteId(int selectedRouteId) {
			this.selectedRouteId = selectedRouteId;
		}

	}

	/**
	 * Fragment to show application info
	 * 
	 * @author onurersen
	 * 
	 */
	public static class AboutSectionFragment extends Fragment {

		public static final String ARG_SECTION_NUMBER = "section_number";

		public AboutSectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_about,
					container, false);
			return rootView;
		}
	}

	/**
	 * @return the selectedRouteId
	 */
	public int getSelectedRouteId() {
		return selectedRouteId;
	}

	/**
	 * @param selectedRouteId
	 *            the selectedRouteId to set
	 */
	public void setSelectedRouteId(int selectedRouteId) {
		this.selectedRouteId = selectedRouteId;
	}

}
