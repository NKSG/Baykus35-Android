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
	private TariffModel model;

	public TariffItemAdapter(Context context, int textViewResourceId, String[] objects, TariffModel model) {

		super(context, textViewResourceId, objects);

		this.context = context;
		this.Ids = objects;
		this.rowResourceId = textViewResourceId;
		this.model = model;

	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(rowResourceId, parent, false);

		TextView tariffTime1 = (TextView) rowView.findViewById(R.id.tariffTime1View);
		TextView tariffTime2 = (TextView) rowView.findViewById(R.id.tariffTime2View);

		int id = Integer.parseInt(Ids[position]);
		tariffTime1.setText(model.GetbyId(id).Time1);
		tariffTime2.setText(model.GetbyId(id).Time2);

		return rowView;

	}

}
