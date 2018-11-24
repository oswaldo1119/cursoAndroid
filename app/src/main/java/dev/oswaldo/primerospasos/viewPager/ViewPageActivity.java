package dev.oswaldo.primerospasos.viewPager;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.oswaldo.primerospasos.R;

public class ViewPageActivity extends AppCompatActivity {

    private static final String TAG = ViewPageActivity.class.getSimpleName();

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_page);
        ButterKnife.bind(this);
        viewPager.setAdapter(new CustomPagerAdapter(this));
    }
}
