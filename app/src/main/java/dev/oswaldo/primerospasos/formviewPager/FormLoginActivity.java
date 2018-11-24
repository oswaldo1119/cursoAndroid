package dev.oswaldo.primerospasos.formviewPager;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.oswaldo.primerospasos.R;
import dev.oswaldo.primerospasos.activitys.emailActivity;
import dev.oswaldo.primerospasos.util.KeysConstants;
import dev.oswaldo.primerospasos.util.Util;
import dev.oswaldo.primerospasos.util.ValidInput;

public class FormLoginActivity extends AppCompatActivity {

    public static final String TAG = FormLoginActivity.class.getSimpleName();

    @BindView(R.id.textInputNombre) TextInputLayout mTextInputNombre;
    @BindView(R.id.editTextNombre) EditText mEditTextNombre;

    @BindView(R.id.textInputApellidos) TextInputLayout mTextInputApellidos;
    @BindView(R.id.editTextApellidos) EditText mEditTextApellidos;

    @BindView(R.id.textInputEmail) TextInputLayout mTextInputEmail;
    @BindView(R.id.editTextEmail) EditText mEditTextEmail;

    @BindView(R.id.textInputPassword) TextInputLayout mTextInputPassword;
    @BindView(R.id.editTextPassword) EditText mEditTextPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonLogin)
    public void login(View view){
        cleanInputError();
        if(areEmptyInputs()){
            Util.showSnackBar(view, getString(R.string.camposVacios));
        }
        else if(areValidInputs()){
            Util.showSnackBar(view, "Login success");
            Intent intent = new Intent(FormLoginActivity.this, FormViewPagerActivity.class);
            Usuario usuario = new Usuario(mEditTextNombre.getText().toString()
                    , mEditTextApellidos.getText().toString()
                    , mEditTextEmail.getText().toString()
                    , mEditTextPassword.getText().toString());
            intent.putExtra(KeysConstants.USUARIO, usuario);
            startActivity(intent);
            FormLoginActivity.this.finish();
        }
    }

    private boolean areValidInputs() {
        return ValidInput.validEmptyString(getApplicationContext(), mTextInputNombre, mEditTextNombre)
                & ValidInput.validEmptyString(getApplicationContext(), mTextInputApellidos, mEditTextApellidos)
                & ValidInput.validEmail(getApplicationContext(), mTextInputEmail, mEditTextEmail)
                & ValidInput.validPassword(getApplicationContext(), mTextInputPassword, mEditTextPassword);
    }

    private boolean areEmptyInputs() {
        return mEditTextNombre.getText().toString().isEmpty() &&
                mEditTextApellidos.getText().toString().isEmpty() &&
                mEditTextEmail.getText().toString().isEmpty() &&
                mEditTextPassword.getText().toString().isEmpty();
    }

    private void cleanInputError() {
        mTextInputNombre.setError(null);
        mTextInputApellidos.setError(null);
        mTextInputEmail.setError(null);
        mTextInputPassword.setError(null);
    }

}
