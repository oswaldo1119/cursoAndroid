package dev.oswaldo.primerospasos.database;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.oswaldo.primerospasos.R;
import dev.oswaldo.primerospasos.database.adapter.DogShopAdapter;
import dev.oswaldo.primerospasos.database.adapter.DogShopClick;
import dev.oswaldo.primerospasos.database.adapter.DogShopLongClick;
import dev.oswaldo.primerospasos.database.model.DogShop;
import dev.oswaldo.primerospasos.util.Util;

public class RealmRecyclerSampleActivity extends AppCompatActivity implements DogShopClick, DogShopLongClick {

    private static String TAG = RealmRecyclerSampleActivity.class.getSimpleName();

    private List<DogShop> dogShops = new ArrayList<>();

    private DogShopAdapter dogShopAdapter;

    @BindView(R.id.shopsRecyclerView) RecyclerView mDogShopsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_recycler_sample);
        ButterKnife.bind(this);
        setUpRecyclerView(mDogShopsRecyclerView);
    }

    @OnClick(R.id.floatingActionButton) public void addDoomie(){
        dogShops.add(createDoomie());
        dogShopAdapter.notifyDataSetChanged();
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
        Snackbar.make(mDogShopsRecyclerView, "Click Listener", Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void onDogShopLongClickListener(DogShop dogShop) {
        Util.showLog(TAG, "Long click on "+ dogShop.toString());
        Snackbar.make(mDogShopsRecyclerView, "Click Long Listener", Snackbar.LENGTH_SHORT)
                .show();
    }

    public DogShop createDoomie(){
        return new DogShop("Test " + String.valueOf(Util.getRandomID())
                , "Calle Vidrio 1792"
                , Util.setRandomImage()
                , String.valueOf(Util.getRandomID()));
    }
}
