package dev.oswaldo.primerospasos.ws;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.oswaldo.primerospasos.R;
import dev.oswaldo.primerospasos.ws.wsmodels.StarWarsCharacterResponse;
import dev.oswaldo.primerospasos.ws.wsmodels.UserResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebServiceSamplesActivity extends AppCompatActivity {

    @BindView(R.id.editTextPokemon) EditText mEtPokemonID;

    @BindView(R.id.textViewPokemon) TextView mTvPokemonInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service_samples);
        ButterKnife.bind(this);
        postMethod();
    }

    private void postMethod() {
        Client client = ServiceLoggingGenerator.createService(Client.class);
        client.login("hola@uble.mx", "sachubab13")
                .enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.code() == 200) {
                            UserResponse user = response.body();
                            mTvPokemonInfo.setText(user.getUserInfo().getEmail());
                        } else{
                            mTvPokemonInfo.setText("Error code: "+response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        mTvPokemonInfo.setText("Error!!!!!! ");
                        t.printStackTrace();
                    }
                });
    }


    @OnClick(R.id.buttonObtener) public void getCharacter(View view){
        String input = mEtPokemonID.getText().toString();
        if(!input.isEmpty()){
            Client client = ServiceGenerator.createService(Client.class);
            client.getCharacter(input)
                    .enqueue(new Callback<StarWarsCharacterResponse>() {
                        @Override
                        public void onResponse(Call<StarWarsCharacterResponse> call, Response<StarWarsCharacterResponse> response) {
                            if(response.code() == 200) {
                                StarWarsCharacterResponse characterResponse = response.body();
                                mTvPokemonInfo.setText(characterResponse.getName());
                            }
                            else{
                                mTvPokemonInfo.setText("Error code: "+response.code());
                            }
                        }

                        @Override
                        public void onFailure(Call<StarWarsCharacterResponse> call, Throwable t) {
                            mTvPokemonInfo.setText("Error!!!!!! ");
                            t.printStackTrace();
                        }
                    });
        }
    }
}
