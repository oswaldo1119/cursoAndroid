package dev.oswaldo.primerospasos.database;

import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.oswaldo.primerospasos.R;
import dev.oswaldo.primerospasos.database.model.DogShop;
import dev.oswaldo.primerospasos.util.KeysConstants;
import dev.oswaldo.primerospasos.util.Util;
import io.realm.Realm;
import io.realm.RealmQuery;

public class EditShopActivity extends AppCompatActivity {

    @BindView(R.id.imageViewShop) ImageView mImgShop;

    @BindView(R.id.textInputLayout) TextInputLayout mTextInputLayoutName;

    @BindView(R.id.etName) EditText mEtName;

    @BindView(R.id.textInputLayoutAddress) TextInputLayout mTinLAddress;

    @BindView(R.id.etAddress) EditText mEtAddress;

    @BindView(R.id.parent) ConstraintLayout mParentLayout;

    private int MODE;

    private String SHOP_ID;

    private DogShop dogShop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shop);
        ButterKnife.bind(this);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        MODE = getIntent().getIntExtra(KeysConstants.MODE_KEY, 0);
        setUpMode(MODE);
    }

    private void setUpMode(int mode) {
        if(mode==KeysConstants.EDIT_MODE){
            updateMode();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    private void updateMode() {
        SHOP_ID = getIntent().getStringExtra(KeysConstants.DOG_SHOP_ID);
        Realm realm = Realm.getDefaultInstance();
        RealmQuery<DogShop> query = realm.where(DogShop.class);
        query.equalTo(KeysConstants.DOG_SHOP_ID, SHOP_ID);
        dogShop = query.findFirst();
        if(dogShop != null){
            mEtName.setText(dogShop.name);
            mEtAddress.setText(dogShop.address);
            Glide.with(this)
                    .load(dogShop.image)
                    .crossFade(1500)
                    .into(mImgShop);
        }
    }
    @OnClick(R.id.buttonEnd) public void finalize(View view){
        if(noEmptyFields()){
            if(validateFields()){
                if(MODE==KeysConstants.CREATE_MODE) {
                    createShop();
                }else{
                    updateShop();
                }
            }
        }else{
            Snackbar.make(mParentLayout, getString(R.string.empty_fields), Snackbar.LENGTH_SHORT).show();
        }
    }

    private boolean noEmptyFields() {
        return !(mEtName.getText().toString().isEmpty()
                && mEtAddress.getText().toString().isEmpty());
    }

    private boolean validateFields(){
        if(noEmptyName() & noEmptyAddress()){
            return true;
        }
        else{
            return false;
        }
    }

    private boolean noEmptyName() {
        if(mEtName.getText().toString().isEmpty()){
            mTextInputLayoutName.setError(getString(R.string.empty_name));
            return false;
        }else{
            return true;
        }
    }

    private boolean noEmptyAddress() {
        if(mEtAddress.getText().toString().isEmpty()){
            mTinLAddress.setError(getString(R.string.empty_address));
            return false;
        }else {
            return true;
        }
    }

    private void createShop() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            realm1.copyToRealm(new DogShop(mEtName.getText().toString()
                    , mEtAddress.getText().toString()
                    , Util.setRandomImage()
                    ,String.valueOf(Util.getRandomID())
            ));
        });
        finish();
    }


    private void updateShop() {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realmTransaction) {
                dogShop.address = mEtAddress.getText().toString();
                dogShop.name = mEtName.getText().toString();
                realmTransaction.copyToRealmOrUpdate(dogShop);
            }
        });
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
