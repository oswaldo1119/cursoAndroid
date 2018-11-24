package dev.oswaldo.primerospasos.goodviewpager.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.oswaldo.primerospasos.R;
import dev.oswaldo.primerospasos.util.Util;

public class FragmentThree extends Fragment {
    public static final String TAG = FragmentThree.class.getSimpleName();

    @BindView(R.id.buttonFragmentThree)
    Button mButtonFragmentThree;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_three, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.buttonFragmentThree)
    public void click(){
        Util.showSnackBar(getView(), "Click fragment three");
        Toast.makeText(getActivity(),"Click frament three",Toast.LENGTH_SHORT).show();

    }

    public static FragmentThree newInstance(){
        FragmentThree myFragment = new FragmentThree();
        return myFragment;
    }
}
