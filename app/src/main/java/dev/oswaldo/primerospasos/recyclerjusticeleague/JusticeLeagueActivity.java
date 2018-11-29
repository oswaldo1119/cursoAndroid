package dev.oswaldo.primerospasos.recyclerjusticeleague;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.oswaldo.primerospasos.R;
import dev.oswaldo.primerospasos.database.RealmRecyclerSampleActivity;
import dev.oswaldo.primerospasos.database.adapter.DogShopAdapter;
import dev.oswaldo.primerospasos.database.model.DogShop;
import dev.oswaldo.primerospasos.recyclerjusticeleague.adapter.HeroesAdapter;
import dev.oswaldo.primerospasos.recyclerjusticeleague.model.Heroe;
import dev.oswaldo.primerospasos.util.Util;
import io.realm.RealmList;

public class JusticeLeagueActivity extends AppCompatActivity {

    private static String TAG = JusticeLeagueActivity.class.getSimpleName();

    private List<Heroe> heroes = new ArrayList<>();

    private HeroesAdapter heroesAdapter;

    @BindView(R.id.heroesRecyclerView) RecyclerView mHeroesRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avenger_recycle);
        ButterKnife.bind(this);
        setUpRecyclerView(mHeroesRecyclerView);
        createDoomies();
        heroesAdapter.notifyDataSetChanged();
    }

    private void createDoomies() {
        heroes.add(new Heroe("Batman"
            ,"Batman 1"
            ,"2001"
            ,"https://static.vix.com/es/sites/default/files/styles/large/public/btg/cine.batanga.com/files/curiosidades-batman-begins.jpg?itok=o6IHnBZF"
            ,String.valueOf(Util.getRandomID())));

        heroes.add(new Heroe("Batman 2"
                ,"Batman 2"
                ,"2003"
                ,"https://cdn.vox-cdn.com/thumbor/K1WKyMb31K-K1vvseGAyFsjfYYE=/0x0:1200x675/1200x800/filters:focal(478x31:670x223)/cdn.vox-cdn.com/uploads/chorus_image/image/60384393/0_c9S8ajFBpwX89ZuU.0.jpeg"
                ,String.valueOf(Util.getRandomID())));

        heroes.add(new Heroe("Mujer Maravilla"
                ,"Wonder Woman"
                ,"2010"
                ,"https://laopinionla.files.wordpress.com/2017/08/96268248_2ca421dc-198a-4888-aeb3-db18c3fba916.gif?w=940"
                ,String.valueOf(Util.getRandomID())));

        heroes.add(new Heroe("Clark Kent"
                ,"Superman"
                ,"2011"
                ,"http://wallpapershdnow.com/images/movies/superhero/superman-man-of-steel/superman-man-of-steel-wallpaper-9.jpg"
                ,String.valueOf(Util.getRandomID())));

        heroes.add(new Heroe("Heroes"
                ,"Batman vs Superman"
                ,"2015"
                ,"https://sm.ign.com/ign_es/screenshot/default/bvsartjpg-19cc10_149915_57rj.jpg"
                ,String.valueOf(Util.getRandomID())));


    }

    private void setUpRecyclerView(RecyclerView mDogShopsRecyclerView) {
        heroesAdapter= new HeroesAdapter(heroes, getApplicationContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mHeroesRecyclerView.setAdapter(heroesAdapter);
        mHeroesRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
