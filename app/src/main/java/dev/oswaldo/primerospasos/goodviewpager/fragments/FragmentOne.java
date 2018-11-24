package dev.oswaldo.primerospasos.goodviewpager.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.oswaldo.primerospasos.R;

public class FragmentOne extends Fragment {

    public static final String TAG = FragmentOne.class.getSimpleName();

    @BindView(R.id.imageViewFragmentOne)
    ImageView mImgFragmentOne;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, container, false);
        ButterKnife.bind(this, view);

        Glide.with(getActivity())
                .load("https://vignette.wikia.nocookie.net/terraria/images/9/98/3371738-roronoa_zoro_render_17_by_roronoaroel-d5lhovy.png/revision/latest?cb=20140408211006&path-prefix=es")
                .crossFade(1500)
                .into(mImgFragmentOne);

        return view;
    }

    public static FragmentOne newInstance(){
        FragmentOne myFragment = new FragmentOne();
        return myFragment;
    }
}
