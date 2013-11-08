package com.onurersen.baykus35.customization;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 
 * @author onurersen
 * 
 */
public class CustomTextView extends TextView {

	public CustomTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public CustomTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public CustomTextView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		Typeface tf = CustomFontCache.get("fonts/ChamsBold.ttf", context);
		if (tf == null) {
			tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/ChamsBold.ttf");
		}
		setTypeface(tf);
	}
}