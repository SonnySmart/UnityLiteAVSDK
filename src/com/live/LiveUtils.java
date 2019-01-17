package com.live;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.TypedValue;
import android.view.View;

public class LiveUtils {
	
	/**
	 * 通过资源生成Bitmap
	 * @param resources
	 * @param id
	 * @return
	 */
    @SuppressLint("NewApi") 
    public static Bitmap decodeResource(Resources resources, int id) {
        TypedValue value = new TypedValue();
        resources.openRawResource(id, value);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inTargetDensity = value.density;
        return BitmapFactory.decodeResource(resources, id, opts);
    }
    
    /**
     * 获取控件,fix打成jar包无法获取控件
     * @param context
     * @param id
     * @return View
     */
    public static View findViewById(Activity context, String id) {
    	if (context == null)
    		return null;
    	
    	int res_id = context.getResources().getIdentifier(id, "id", context.getPackageName()); 
    	return context.findViewById(res_id);
    }
}
