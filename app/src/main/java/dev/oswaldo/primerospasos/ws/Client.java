package dev.oswaldo.primerospasos.ws;

import dev.oswaldo.primerospasos.ws.wsmodels.StarWarsCharacterResponse;
import dev.oswaldo.primerospasos.ws.wsmodels.UserResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Client {

    @GET(ClientEndPoints.getPeople)
    Call<StarWarsCharacterResponse> getCharacter(@Path("id") String id);

    @FormUrlEncoded
    @POST("login.php")
    Call<UserResponse> login(@Field("email") String email, @Field("password") String password);
}
