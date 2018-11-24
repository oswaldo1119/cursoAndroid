package dev.oswaldo.primerospasos.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CircularProgressDrawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

import dev.oswaldo.primerospasos.R;

public class Util {

    public static final String THOR = "https://imgc.allpostersimages.com/img/print/posters/avengers-infinity-war-thor-and-stormbreaker_a-G-15605542-13372582.jpg";
    public static final String CAPITAN_AMERICA = "https://i0.wp.com/codigoespagueti.com/wp-content/uploads/2018/10/chris-evans-capitan-america.jpg?resize=1080%2C600&quality=80&ssl=1";
    public static final String IRON_MAN = "http://mouse.latercera.com/wp-content/uploads/2017/10/avengers-ironman.jpg";
    public static final String SPIDER_MAN = "https://emptylighthouse-production.s3-us-west-2.amazonaws.com/s3fs-public/styles/728x_hero/public/field/image/30740878_10155432528682344_9083589659031764992_n.jpg?itok=-Jn0xdbw";

    public static void showLog(String TAG, String message){
        Log.e(TAG, "==========> " + message + " <==========");
    }

    public static void showLogWarning(String TAG, String message){
        Log.w(TAG, "==========> " + message + " <==========");
    }

    public static void showSnackBar(View view, String message){
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                .show();
    }

    public void showToast(Activity activity, String message){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    public static void changeActivity(Activity activity, Class aClass, HashMap<String, String> extraData){
        Intent intent = new Intent(activity, aClass);
        if( extraData != null) {
            for(Map.Entry<String, String> entry : extraData.entrySet()) {
                intent.putExtra(entry.getKey(), entry.getValue());
            }
        }
        activity.startActivity(intent);
        activity.finish();
    }

    public static void changeActivity(Activity activity, Class aClass){
        changeActivity(activity, aClass, null);
    }

    public void setUpImageView(Context context, ImageView imageViewLoad, String url){
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(context);
        circularProgressDrawable.setStrokeWidth(20f);
        circularProgressDrawable.setCenterRadius(60f);
        circularProgressDrawable.setColorFilter(ContextCompat.getColor(context,R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        circularProgressDrawable.start();
        Glide.with(context)
                .load(url)
                .fitCenter()
                .placeholder(circularProgressDrawable)
                .crossFade(5000)
                .into(imageViewLoad);
    }
}
