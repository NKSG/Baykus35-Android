package com.onurersen.baykus35.customization;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.onurersen.baykus35.R;
import com.onurersen.baykus35.activities.TariffsActivity;

/**
 * 
 * @author onurersen
 *
 */
public class CustomArrayAdapter extends ArrayAdapter<String> {

	private TariffsActivity activity;

	String[] titles;

	public CustomArrayAdapter(TariffsActivity activity, Context context, int textViewResourceId, String[] titles) {
		super(context, textViewResourceId, titles);
		this.activity = activity;
		this.titles = titles;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = activity.getLayoutInflater();
		View row = inflater.inflate(R.layout.navigator_tariff, parent, false);

		TextView item = (TextView) row.findViewById(R.id.txtListText);
		item.setText(titles[position]);

		return row;
	}
}
