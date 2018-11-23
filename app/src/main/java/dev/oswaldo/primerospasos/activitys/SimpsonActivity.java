package dev.oswaldo.primerospasos.activitys;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dev.oswaldo.primerospasos.R;
import dev.oswaldo.primerospasos.components.ComponentActivity;
import dev.oswaldo.primerospasos.util.Util;

public class SimpsonActivity extends AppCompatActivity{

    private static final String TAG = ComponentActivity.class.getSimpleName();

    private ImageView mImageMain;

    private TextView mNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpson);

        mImageMain = findViewById(R.id.imageMain);
        mNombre = findViewById(R.id.nombre);
        setUpViews();
    }

    private void setUpViews() {
        mNombre.setTextColor( ContextCompat.getColor(this, R.color.colorText ) );
        mImageMain.setImageDrawable( ContextCompat.getDrawable( this, R.drawable.simpsons ) );
        mImageMain.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    public void clickedSimpson(View view){
        switch (view.getId()) {
            case R.id.buttonLisa:
                setSimpson("Click on Lisa", R.drawable.lisa, R.string.lisa, R.color.colorTextBackground);
                break;
            case R.id.buttonHomero:
                setSimpson("Click on homero", R.drawable.homero, R.string.homero, R.color.colorText);
                break;
            case R.id.linearBart:
                setSimpson("Click on bart", R.drawable.bart, R.string.bart, R.color.colorAccent);
                break;
            case R.id.linearMoe:
                setSimpson("Click on Moe", R.drawable.moe, R.string.moe, R.color.colorPrimaryDark);
                break;
        }
    }

    private void setSimpson(String message, int imgId, int nombre, int color) {
        Util.showLogWarning(TAG, message);
        mNombre.setText(nombre);
        mNombre.setTextColor( ContextCompat.getColor(SimpsonActivity.this, color ) );
        mImageMain.setImageDrawable( ContextCompat.getDrawable( SimpsonActivity.this, imgId ) );
        mImageMain.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }
}
