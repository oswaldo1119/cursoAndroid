package dev.oswaldo.primerospasos.util;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import java.util.regex.Pattern;

import dev.oswaldo.primerospasos.R;

import static android.provider.Settings.System.getString;

public class ValidInput {

    public static boolean validEmptyString(Context context, TextInputLayout textInputLayout, EditText editText){
        if (editText.getText().toString().isEmpty()) {
            textInputLayout.setError(context.getString(R.string.emptyString));
            return false;
        }
        return true;
    }

    public static boolean validEmail(Context context, TextInputLayout textInputLayout, EditText editText){
        Pattern pattern = Pattern
                .compile("^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@" +
                        "[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,4})$");

        if (editText.getText().toString().isEmpty()) {
            textInputLayout.setError(context.getString(R.string.emailVacio));
            return false;
        } else if (!pattern.matcher(editText.getText().toString()).find()) {
            textInputLayout.setError(context.getString(R.string.errorEmailInvalido));
            return false;
        }
        return true;
    }

    public static boolean validPassword(Context context, TextInputLayout textInputLayout, EditText editText){
        if (editText.getText().toString().isEmpty()) {
            textInputLayout.setError(context.getString(R.string.passwordVacio));
            return false;
        } else if (editText.getText().toString().length() < 8) {
            textInputLayout.setError(context.getString(R.string.errorPasswordInvalido));

            return false;
        }
        return true;
    }
}
