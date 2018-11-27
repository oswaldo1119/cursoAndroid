package dev.oswaldo.primerospasos.animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.oswaldo.primerospasos.R;

public class AnimationsSampleActivity extends AppCompatActivity {

    @BindView(R.id.imageViewAnimation)
    ImageView mImgViewAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animations_sample);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonZoom) public void zoom(View view){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext()
                , R.anim.myanimation);
        mImgViewAnimation.startAnimation(animation);
    }

    @OnClick(R.id.buttonClockwise) public void clockwise(View view){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext()
                , R.anim.clockwise);
        mImgViewAnimation.startAnimation(animation);
    }

    @OnClick(R.id.buttonFade) public void fade(View view){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext()
                , R.anim.fade);
        mImgViewAnimation.startAnimation(animation);
    }

    @OnClick(R.id.buttonBlink) public void blink(View view){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext()
                , R.anim.blink);
        mImgViewAnimation.startAnimation(animation);
    }

    @OnClick(R.id.buttonMove) public void move(View view){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext()
                , R.anim.move);
        mImgViewAnimation.startAnimation(animation);
    }

    @OnClick(R.id.buttonSlide) public void slide(View view){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext()
                , R.anim.slide);
        mImgViewAnimation.startAnimation(animation);
    }


}
