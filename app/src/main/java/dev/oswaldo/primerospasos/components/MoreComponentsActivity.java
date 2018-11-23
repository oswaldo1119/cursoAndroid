package dev.oswaldo.primerospasos.components;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dev.oswaldo.primerospasos.R;

public class MoreComponentsActivity extends AppCompatActivity {

    private static final String TAG = ComponentActivity.class.getSimpleName();

    private Spinner spinnerCountry, spinnerList;

    private Button mButtonSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_component);
        initVariables();
        addListenerOnSpinnerItemSelection();
        addItemsOnSpinnerList();
        addListenerOnButton();
    }

    private void initVariables(){
        spinnerCountry = findViewById(R.id.spinnerCountry);
        spinnerList = findViewById(R.id.spinnerList);
        mButtonSelect = findViewById(R.id.buttonSelect);
    }

    public void addListenerOnSpinnerItemSelection(){
        spinnerCountry.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    //add items into spinner dynamically
    public void addItemsOnSpinnerList(){
        List<String> list = new ArrayList<>(Arrays.asList("List 1", "List 2", "List 3"));
        list.add("List 4");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_item, list);
        dataAdapter.setDropDownViewResource(R.layout.spinner_item);
        spinnerList.setAdapter(dataAdapter);
    }

    public void addListenerOnButton(){
        mButtonSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createSimpleDialog(MoreComponentsActivity.this);
            }
        });
    }

    public void createSimpleDialog(Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Seleccionaste")
                .setMessage("\nSpinner Country: " + String.valueOf(spinnerCountry.getSelectedItem())+
                    "\nSpinner List: " + String.valueOf(spinnerList.getSelectedItem()))
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        builder.show();
    }
}
