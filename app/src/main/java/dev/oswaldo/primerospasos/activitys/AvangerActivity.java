package dev.oswaldo.primerospasos.activitys;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.CircularProgressDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import dev.oswaldo.primerospasos.R;
import dev.oswaldo.primerospasos.components.ComponentActivity;
import dev.oswaldo.primerospasos.util.Util;

public class AvangerActivity extends AppCompatActivity {

    private static final String TAG = ComponentActivity.class.getSimpleName();

    private Avenger avenger;

    private ScrollView parentLayout;

    private ConstraintLayout constraintLayoutForm, constraintLayoutResult;

    private ImageView imageAvenger;

    private Spinner spinnerNombre;

    private EditText editTextFrase;

    private CheckBox checkBoxVivo, checkBoxGema;

    private RadioGroup radioGroupGemas;

    private Switch switchVivo;

    private ProgressBar progressBar;

    private Button butttonMostrar;

    private Context context;

    private ImageView imageAvengerPersonal;

    private TextView textViewNombre, textViewFrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avanger);
        initVariables();
    }

    private void initVariables() {
        context = getApplicationContext();
        parentLayout = findViewById(R.id.parentLayout);
        constraintLayoutForm = findViewById(R.id.constraintLayoutForm);
        constraintLayoutResult = findViewById(R.id.constraintLayoutResult);
        imageAvenger = findViewById(R.id.imageAvenger);
        spinnerNombre = findViewById(R.id.spinnerNombre);
        editTextFrase = findViewById(R.id.editTextFrase);
        radioGroupGemas = findViewById(R.id.radioGroupGemas);
        switchVivo = findViewById(R.id.switchVivo);
        butttonMostrar = findViewById(R.id.butttonMostrar);
        imageAvengerPersonal = findViewById(R.id.imageAvengerPersonal);
        textViewNombre = findViewById(R.id.textViewNombre);
        textViewFrase = findViewById(R.id.textViewFrase);
        checkBoxVivo = findViewById(R.id.checkBoxVivo);
        checkBoxGema = findViewById(R.id.checkBoxGema);
    }

    public void clickedMostrar(View view){
        Util.showLog(TAG, "Click on the mostrar");
        if( editTextFrase.getText().toString().isEmpty() ){
            showSnackBar( "Falta frase del avenger " + String.valueOf(spinnerNombre.getSelectedItem()));
        }
        else{
            switch (spinnerNombre.getSelectedItem().toString()) {
                case "Thor":
                    setUpImageView(Util.THOR);
                    break;
                case "Capitan America":
                    setUpImageView(Util.CAPITAN_AMERICA);
                    break;
                case "Iron Man":
                    setUpImageView(Util.IRON_MAN);
                    break;
                case "Spider-Man":
                    setUpImageView(Util.SPIDER_MAN);
                    break;
            }
            textViewNombre.setText( spinnerNombre.getSelectedItem().toString() );
            textViewFrase.setText( editTextFrase.getText().toString() );
            checkBoxVivo.setChecked( switchVivo.isChecked() );
            int index = radioGroupGemas.indexOfChild(findViewById(radioGroupGemas.getCheckedRadioButtonId()));
            checkBoxGema.setChecked( index==0 ? true : false  );
            constraintLayoutForm.setVisibility(View.GONE);
            constraintLayoutResult.setVisibility(View.VISIBLE);
        }
    }

    public void clickedFinalizar(View view) {
        constraintLayoutForm.setVisibility(View.VISIBLE);
        constraintLayoutResult.setVisibility(View.GONE);
    }

        public void setUpImageView(String url){
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(this);
        circularProgressDrawable.setStrokeWidth(20f);
        circularProgressDrawable.setCenterRadius(60f);
        circularProgressDrawable.setColorFilter(ContextCompat.getColor(this,R.color.colorAccent), PorterDuff.Mode.SRC_ATOP);
        circularProgressDrawable.start();
        Glide.with(this)
                .load(url)
                .fitCenter()
                .placeholder(circularProgressDrawable)
                .crossFade(5000)
                .into(imageAvengerPersonal);
    }

    private void showSnackBar(String message){
        Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG)
                .show();
    }
}
