package dev.oswaldo.primerospasos.goodviewpager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.oswaldo.primerospasos.R;
import dev.oswaldo.primerospasos.goodviewpager.fragments.FragmentOne;
import dev.oswaldo.primerospasos.goodviewpager.fragments.FragmentThree;
import dev.oswaldo.primerospasos.goodviewpager.fragments.FragmentTwo;
import dev.oswaldo.primerospasos.util.Util;

public class GoodViewPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private static final String TAG = GoodViewPagerActivity.class.getSimpleName();
    @BindView(R.id.viewPagerGood)
    ViewPager mViewPagerGood;

    @BindView(R.id.buttonFragment1)
    Button mButtonFragment1;

    @BindView(R.id.buttonFragment2)
    Button mButtonFragment2;

    @BindView(R.id.buttonFragment3)
    Button mButtonFragment3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_view_pager);
        ButterKnife.bind(this);
        setUpViewPager();

    }

    private void setUpViewPager() {
        GoodPagerAdapter goodPagerAdapter = new GoodPagerAdapter(getSupportFragmentManager());
        goodPagerAdapter.addFragment(FragmentOne.newInstance(), FragmentOne.TAG);
        goodPagerAdapter.addFragment(FragmentTwo.newInstance("Prueba de enviar datos a fragments"), FragmentTwo.TAG);
        goodPagerAdapter.addFragment(FragmentThree.newInstance(), FragmentThree.TAG);
        mViewPagerGood.setAdapter(goodPagerAdapter);
        mViewPagerGood.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        Util.showLog(TAG, "Page selected "+ i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {
        Util.showLog(TAG, "on page scroll "+ i);
    }


    @OnClick(R.id.buttonFragment1)
    public void clickFragment(View view){
        mViewPagerGood.setCurrentItem(0);
    }

    @OnClick(R.id.buttonFragment2)
    public void clickFragment2(View view){
        mViewPagerGood.setCurrentItem(1);
    }

    @OnClick(R.id.buttonFragment3)
    public void click(View view){
        mViewPagerGood.setCurrentItem(2);
    }
}
