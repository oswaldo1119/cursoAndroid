package dev.oswaldo.primerospasos.activitys;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.oswaldo.primerospasos.R;
import dev.oswaldo.primerospasos.util.KeysConstants;
import dev.oswaldo.primerospasos.util.Util;

public class emailActivity extends AppCompatActivity {

    private static final String TAG = emailActivity.class.getSimpleName();

    @BindView(R.id.textInputEmail)
    TextInputLayout textInputEmail;

    @BindView(R.id.textInputPassword)
    TextInputLayout textInputPassword;

    @BindView(R.id.editTextEmail)
    EditText editTextEmail;

    @BindView(R.id.editTextPassword)
    EditText editTextPassword;

    @BindView(R.id.buttonLogin)
    Button buttonLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonLogin)
    public void logIn(View view){
        textInputEmail.setError(null);
        textInputPassword.setError(null);
        if(areEmptyInputs()){
            Util.showSnackBar(view, getString(R.string.camposVacios));
        }else if( areValidInputs()){
            Util.showSnackBar(view, "Login success");
            Intent intent = new Intent(emailActivity.this, ProfileActivity.class);
            intent.putExtra(KeysConstants.PROFILE, editTextEmail.getText().toString());
            startActivity(intent);
            emailActivity.this.finish();
        }
        //editTextEmail.setError("Error en email");
        //textInputPassword.setError("Error en el password");
        //Util.showLog(TAG, "Click on the log in");
        //Util.showSnackBar(view, "Login success");
    }

    private boolean areValidInputs() {
        return validEmail() && validPassword();
    }

    private boolean validEmail(){
        Pattern pattern = Pattern
                .compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" +
                        "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$");

        if (editTextEmail.getText().toString().isEmpty()) {
            textInputEmail.setError(getString(R.string.emailVacio));
            return false;
        } else if (!pattern.matcher(editTextEmail.getText().toString()).find()) {
            textInputEmail.setError(getString(R.string.errorEmailInvalido));
            return false;
        }
        return true;
    }

    private boolean validPassword(){
        if (editTextPassword.getText().toString().isEmpty()) {
            textInputPassword.setError(getString(R.string.passwordVacio));
            return false;
        } else if (editTextPassword.getText().toString().length() < 8) {
            textInputPassword.setError(getString(R.string.errorPasswordInvalido));

            return false;
        }
        return true;
    }

    private boolean areEmptyInputs() {
        return editTextEmail.getText().toString().isEmpty() &&
                editTextPassword.getText().toString().isEmpty();
    }


}
