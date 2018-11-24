package dev.oswaldo.primerospasos.goodviewpager.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.oswaldo.primerospasos.R;
import dev.oswaldo.primerospasos.util.KeysConstants;
import dev.oswaldo.primerospasos.util.Util;

public class FragmentTwo extends Fragment {

    public static final String TAG = FragmentTwo.class.getSimpleName();

    @BindView(R.id.textViewFragmentTwo)
    TextView mtextViewFragmentTwo;

    String message;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        message = getArguments().getString(KeysConstants.FRAGMENT_ARG_FLAG);
        Util.showLog(TAG, "Message received "+ message);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        ButterKnife.bind(this, view);

        mtextViewFragmentTwo.setText(message);

        return view;
    }

    public static FragmentTwo newInstance(String someString){
        FragmentTwo myFragment = new FragmentTwo();
        Bundle args = new Bundle();
        args.putString(KeysConstants.FRAGMENT_ARG_FLAG, someString);
        myFragment.setArguments(args);
        return myFragment;
    }
}
