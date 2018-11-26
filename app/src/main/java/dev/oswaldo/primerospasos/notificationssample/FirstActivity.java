package dev.oswaldo.primerospasos.notificationssample;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.oswaldo.primerospasos.R;
import dev.oswaldo.primerospasos.util.KeysConstants;

public class FirstActivity extends AppCompatActivity {

    @BindView(R.id.editTextMessage)
    EditText mEditTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonSend) public void sendButton(){
        if(!mEditTextMessage.getText().toString().isEmpty()){
            Intent intent = new Intent(this, NotificationsActivity.class);
            intent.putExtra(KeysConstants.message, mEditTextMessage.getText().toString());
            startActivity(intent);
            finish();
        }else {
            createSimpleDialog(FirstActivity.this);
        }
    }

    private void createSimpleDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.empty_input_message)
                .setMessage(getString(R.string.insert_a_message_to_send))
                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
        builder.show();
    }
}
