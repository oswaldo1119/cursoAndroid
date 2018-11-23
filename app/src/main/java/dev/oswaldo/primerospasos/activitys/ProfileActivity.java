package dev.oswaldo.primerospasos.activitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import dev.oswaldo.primerospasos.R;
import dev.oswaldo.primerospasos.util.KeysConstants;
import dev.oswaldo.primerospasos.util.Util;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = ProfileActivity.class.getSimpleName();

    String mail;

    @BindView(R.id.textViewEmail)
    TextView textViewEmail;

    @BindView(R.id.profileImage)
    CircleImageView circleProfileImage;

    @BindView(R.id.scrollViewProfile)
    ScrollView scrollViewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        Glide.with(this)
                .load("https://i.pinimg.com/236x/b3/10/66/b310665f7f1473565a5e58c7162cd36b.jpg")
                .centerCrop()
                .crossFade(2300)
                .into(circleProfileImage);
        if(intent!= null){
            mail = intent.getExtras().getString(KeysConstants.PROFILE);
            textViewEmail.setText(mail);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflete the menu; this adds items to the action bar if is present.
        getMenuInflater().inflate(R.menu.profile_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item1:
                Util.showSnackBar(scrollViewProfile, "Item 1 Selected");
                return true;
            case R.id.item2:
                Util.showSnackBar(scrollViewProfile, "Item 2 Selected");
                return true;
            case R.id.item3:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout(){
        Util.changeActivity(ProfileActivity.this, emailActivity.class);
    }
}
