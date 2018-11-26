package dev.oswaldo.primerospasos.database;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.oswaldo.primerospasos.R;
import dev.oswaldo.primerospasos.database.adapter.DogShopAdapter;
import dev.oswaldo.primerospasos.database.adapter.DogShopClick;
import dev.oswaldo.primerospasos.database.adapter.DogShopLongClick;
import dev.oswaldo.primerospasos.database.model.DogShop;
import dev.oswaldo.primerospasos.util.KeysConstants;
import dev.oswaldo.primerospasos.util.Util;
import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class RealmRecyclerSampleActivity extends AppCompatActivity implements DogShopClick, DogShopLongClick {

    private static String TAG = RealmRecyclerSampleActivity.class.getSimpleName();

    private RealmList<DogShop> dogShops = new RealmList<>();

    private DogShopAdapter dogShopAdapter;

    @BindView(R.id.shopsRecyclerView) RecyclerView mDogShopsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_recycler_sample);
        ButterKnife.bind(this);
        setUpRecyclerView(mDogShopsRecyclerView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getRealmObjects();
    }

    private void getRealmObjects() {
        dogShops.clear();
        Realm realm = Realm.getDefaultInstance();
        RealmResults<DogShop> dogShopsResults = realm.where(DogShop.class).findAll();
        //dogShops.addAll(dogShopsResults.subList(0, dogShopsResults.size()));
        dogShops.addAll(dogShopsResults);
        dogShopAdapter.notifyDataSetChanged();
    }

    @OnClick(R.id.floatingActionButton) public void createDogShop(){
        Intent intent = new Intent(this, EditShopActivity.class);
        intent.putExtra(KeysConstants.MODE_KEY, KeysConstants.CREATE_MODE);
        this.startActivity(intent);
        //dogShops.add(createDoomie());
        //dogShopAdapter.notifyDataSetChanged();
    }
    private void setUpRecyclerView(RecyclerView mDogShopsRecyclerView) {
        dogShopAdapter = new DogShopAdapter(dogShops,
                getApplicationContext()
                , this
                , this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mDogShopsRecyclerView.setAdapter(dogShopAdapter);
        mDogShopsRecyclerView.setLayoutManager(linearLayoutManager);
        /*mDogShopsRecyclerView.setLayoutManager(new GridLayoutManager(
                this, 3));*/

    }


    @Override
    public void onDogShopClickListener(DogShop dogShop) {
        Util.showLog(TAG, "Click on "+ dogShop.toString());
        Intent intent = new Intent(this, EditShopActivity.class);
        intent.putExtra(KeysConstants.DOG_SHOP_ID, dogShop.dogShopID);
        intent.putExtra(KeysConstants.MODE_KEY, KeysConstants.EDIT_MODE);
        startActivity(intent);
    }

    @Override
    public void onDogShopLongClickListener(DogShop dogShop) {
        Util.showLog(TAG, "Long click on "+ dogShop.toString());
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransaction(realm1 -> {
            dogShops.remove(dogShop);
            dogShopAdapter.notifyDataSetChanged();
            RealmResults<DogShop> shops = realm.where(DogShop.class)
                    .equalTo(KeysConstants.DOG_SHOP_ID, dogShop.dogShopID)
                    .findAll();
            shops.deleteAllFromRealm();
        });
    }

    public DogShop createDoomie(){
        return new DogShop("Test " + String.valueOf(Util.getRandomID())
                , "Calle Vidrio 1792"
                , Util.setRandomImage()
                , String.valueOf(Util.getRandomID()));
    }
}
