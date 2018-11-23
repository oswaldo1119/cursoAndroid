package dev.oswaldo.primerospasos.components;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import dev.oswaldo.primerospasos.R;
import dev.oswaldo.primerospasos.util.Util;

public class ComponentActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = ComponentActivity.class.getSimpleName();

    private LinearLayout mlinearLayout;

    private Button mButtton;

    private Button mButtton5;

    private ScrollView parentLayout;

    private ImageView imageViewLoad;

    private EditText editTextName;

    private CheckBox checkBoxCondiciones;

    private RadioGroup radioGroupGenero;

    private Switch switchEncendido;

    private ProgressBar progressBar;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component);
        initVariables();
        setUpImageView();
        setUpCheckBoxCondiciones();
        setUpRadioGroupGenero();
        setUpSwitch();
    }

    public void initVariables(){
        context = getApplicationContext();

        parentLayout = findViewById( R.id.parentLayout);
        mlinearLayout = findViewById(R.id.linearLayout);
        mlinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.showLog(TAG, "Click on the button linear Layout");
            }
        });
        mButtton = findViewById(R.id.button4);
        mButtton.setOnClickListener(this);

        mButtton5 = findViewById(R.id.button5);

        imageViewLoad = findViewById(R.id.imageViewLoad);
        editTextName = findViewById(R.id.editTextName);
        checkBoxCondiciones = findViewById(R.id.checkBoxCondiciones);
        radioGroupGenero = findViewById(R.id.radioGroupGenero);
        switchEncendido = findViewById(R.id.switchEncendido);
        progressBar  = findViewById(R.id.progressBar);
    }

    public void clicked(View view){
        Util.showLog(TAG, "Click on the button");
        if(! editTextName.getText().toString().isEmpty() ){
            showSnackBar( editTextName.getText().toString() );
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button4:
                Util.showLog(TAG, "Click on button 4");
        }
    }

    private void setUpImageView(){
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(this);
        circularProgressDrawable.setStrokeWidth(20f);
        circularProgressDrawable.setCenterRadius(60f);
        circularProgressDrawable.setColorFilter(ContextCompat.getColor(this,R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        circularProgressDrawable.start();
        Glide.with(this)
                .load("https://i.pinimg.com/originals/56/b7/64/56b7642388e52d62910a8806a18e10be.gif")
                .fitCenter()
                .placeholder(circularProgressDrawable)
                .crossFade(5000)
                .into(imageViewLoad);
    }

    private void setUpCheckBoxCondiciones(){
        checkBoxCondiciones.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            Util.showLog( TAG,"Checked condiciones y términos " + isChecked);
            showSnackBar("Check the box " + isChecked);
        }
    };

    private void setUpRadioGroupGenero(){
        radioGroupGenero.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.radioButtonHombre:
                        Util.showLog( TAG,"Checked Hombre ");
                        showToast("Hombre");
                        break;
                    case R.id.radioButtonMujer:
                        Util.showLog( TAG,"Checked Mujer ");
                        showToast("Mujer");
                        break;
                }
            }
        });
    }

    private void setUpSwitch(){
        switchEncendido.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if( isChecked ) {
                    //if (progressBar.getVisibility() == View.VISIBLE)
                        progressBar.setVisibility(View.VISIBLE);
                }
                else{
                    progressBar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void showSnackBar(String message){
        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG)
                .show();
    }

    private void showToast(String message){
        Toast.makeText(ComponentActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
