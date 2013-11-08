package com.onurersen.baykus35.list.route;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.onurersen.baykus35.R;

/**
 * 
 * @author onurersen
 * 
 */
public class RouteItemAdapter extends ArrayAdapter<String> {

	private final Context context;
	private final String[] Ids;
	private final int rowResourceId;

	public RouteItemAdapter(Context context, int textViewResourceId, String[] objects) {

		super(context, textViewResourceId, objects);

		this.context = context;
		this.Ids = objects;
		this.rowResourceId = textViewResourceId;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(rowResourceId, parent, false);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);
		imageView.setBackgroundResource(R.drawable.img_bus);
		TextView numberView = (TextView) rowView.findViewById(R.id.numberView);
		TextView routeView = (TextView) rowView.findViewById(R.id.routeView);
		TextView descriptionView = (TextView) rowView.findViewById(R.id.descriptionView);

		int id = Integer.parseInt(Ids[position]);
		numberView.setText(RouteModel.GetbyId(id).Number);
		routeView.setText(RouteModel.GetbyId(id).Route);
		descriptionView.setText(RouteModel.GetbyId(id).Description);

		return rowView;

	}

}
