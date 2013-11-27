package com.onurersen.baykus35.list.tariff;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.onurersen.baykus35.R;

/**
 * 
 * @author onurersen
 * 
 */
public class TariffItemAdapter extends ArrayAdapter<String> {

	private final Context context;
	private final String[] Ids;
	private final int rowResourceId;

	public TariffItemAdapter(Context context, int textViewResourceId, String[] objects) {
		super(context, textViewResourceId, objects);
		this.context = context;
		this.Ids = objects;
		this.rowResourceId = textViewResourceId;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(rowResourceId, parent, false);

		TextView tariffTime1 = (TextView) rowView.findViewById(R.id.tariffTime1View);
		TextView tariffTime2 = (TextView) rowView.findViewById(R.id.tariffTime2View);
		TextView tariffRoute = (TextView) rowView.findViewById(R.id.tariffRouteView);

		int id = Integer.parseInt(Ids[position]);
		tariffTime1.setText(TariffModel.GetbyId(id).getTime1());
		tariffTime2.setText(TariffModel.GetbyId(id).getTime2());
		tariffRoute.setText(TariffModel.GetbyId(id).getRoute());
		rowView.setEnabled(false);
		rowView.setOnClickListener(null);
		return rowView;

	}

}
