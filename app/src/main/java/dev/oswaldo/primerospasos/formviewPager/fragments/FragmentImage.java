package dev.oswaldo.primerospasos.formviewPager.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import dev.oswaldo.primerospasos.R;
import dev.oswaldo.primerospasos.util.Util;

public class FragmentImage extends Fragment {
    public static final String TAG = FragmentImage.class.getSimpleName();

    @BindView(R.id.profileImage)
    CircleImageView circleProfileImage;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_image, container, false);
        ButterKnife.bind(this, view);

        Glide.with(this)
                .load("https://www.writeups.org/wp-content/uploads/Spider-Man-Marvel-Comics-Peter-Parker-Profile.jpg")
                .centerCrop()
                .crossFade(2300)
                .into(circleProfileImage);

        return view;
    }

    @OnClick(R.id.fab) public void facClick(View view){
        Util.showSnackBar(view, getString(R.string.telaraña));
    }

    public static FragmentImage newInstance(){
        FragmentImage myFragment = new FragmentImage();
        return myFragment;
    }

}
