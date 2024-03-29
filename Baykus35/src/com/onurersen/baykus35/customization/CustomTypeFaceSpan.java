package com.onurersen.baykus35.customization;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import android.util.LruCache;

/**
 * 
 * @author onurersen
 * 
 */
public class CustomTypeFaceSpan extends MetricAffectingSpan {

	private static LruCache<String, Typeface> sTypefaceCache = new LruCache<String, Typeface>(12);

	private Typeface mTypeface;

	public CustomTypeFaceSpan(Context context, String typefaceName) {
		mTypeface = sTypefaceCache.get(typefaceName);

		if (mTypeface == null) {
			mTypeface = Typeface.createFromAsset(context.getApplicationContext().getAssets(),
					String.format("fonts/%s.ttf", typefaceName));
			sTypefaceCache.put(typefaceName, mTypeface);
		}
	}

	@Override
	public void updateMeasureState(TextPaint p) {
		p.setTypeface(mTypeface);
		p.setFlags(p.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
	}

	@Override
	public void updateDrawState(TextPaint tp) {
		tp.setTypeface(mTypeface);
		tp.setFlags(tp.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
	}
}