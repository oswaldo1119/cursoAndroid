package dev.oswaldo.primerospasos.formviewPager;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.oswaldo.primerospasos.R;
import dev.oswaldo.primerospasos.formviewPager.fragments.FragmentDatos;
import dev.oswaldo.primerospasos.formviewPager.fragments.FragmentEmail;
import dev.oswaldo.primerospasos.formviewPager.fragments.FragmentImage;
import dev.oswaldo.primerospasos.util.FramentAdapter;
import dev.oswaldo.primerospasos.util.KeysConstants;
import dev.oswaldo.primerospasos.util.Util;

public class FormViewPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private static final String TAG = FormViewPagerActivity.class.getSimpleName();

    @BindView(R.id.viewPagerProfile)
    ViewPager mViewPagerProfile;

    Usuario usuario;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_view_pager);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if(intent!= null){
            usuario = (Usuario) intent.getExtras().getSerializable(KeysConstants.USUARIO);
            setUpViewPager(usuario);

        }
    }

    private void setUpViewPager(Usuario user) {
        FramentAdapter framentAdapter= new FramentAdapter(getSupportFragmentManager());
        framentAdapter.addFragment(FragmentImage.newInstance(), FragmentImage.TAG);
        framentAdapter.addFragment(FragmentDatos.newInstance(usuario), FragmentDatos.TAG);
        framentAdapter.addFragment(FragmentEmail.newInstance(usuario), FragmentEmail.TAG);

        mViewPagerProfile.setAdapter(framentAdapter);
        mViewPagerProfile.addOnPageChangeListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflete the menu; this adds items to the action bar if is present.
        getMenuInflater().inflate(R.menu.profile_view__page_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.itemProfile:
                mViewPagerProfile.setCurrentItem(0);
                return true;
            case R.id.itemDatos:
                mViewPagerProfile.setCurrentItem(1);
                return true;
            case R.id.itemEmail:
                mViewPagerProfile.setCurrentItem(2);
                return true;
            case R.id.itemLogout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void logout(){
        Util.changeActivity(FormViewPagerActivity.this, FormLoginActivity.class);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) < 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
        }

        return super.onKeyDown(keyCode, event);
    }

    public void onBackPressed() {
        if(mViewPagerProfile.getCurrentItem() <= 0 ){
            Util.changeActivity(FormViewPagerActivity.this, FormLoginActivity.class);
        }else{
            mViewPagerProfile.setCurrentItem(mViewPagerProfile.getCurrentItem()-1);
        }

        return;
    }
}