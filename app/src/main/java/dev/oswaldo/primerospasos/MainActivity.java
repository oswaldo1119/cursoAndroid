package dev.oswaldo.primerospasos;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import dev.oswaldo.primerospasos.util.Util;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ImageView mImageView;

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Util.showLog(TAG, "on Create");
        mImageView = findViewById(R.id.imageView);
        mTextView = findViewById(R.id.textView);
        setUpViews();
        readAssets();
    }

    private void setUpViews() {
        mTextView.setText(R.string.text_string);
        mTextView.setTextColor( ContextCompat.getColor(this, R.color.colorText ) );
        mTextView.setBackgroundResource( R.color.colorTextBackground );
        mImageView.setImageDrawable( ContextCompat.getDrawable( this, R.drawable.arturito ) );
    }

    private void readAssets() {
        try{
            InputStream inputStream = getAssets().open("ejemplotexto.txt");
            int size = inputStream.available();
            byte [] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String textoRecibido = new String( buffer );
            Util.showLog( TAG, textoRecibido );
        }catch (IOException e){
            Util.showLog(TAG, "Error al leer archivo");
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Util.showLog(TAG, "on Start");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Util.showLog(TAG, "on Resume");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Util.showLog(TAG, "on Stop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Util.showLog(TAG, "on Pause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Util.showLog(TAG, "on Destroy");
    }
}
