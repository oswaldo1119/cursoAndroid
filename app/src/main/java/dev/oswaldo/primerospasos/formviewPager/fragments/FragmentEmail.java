package dev.oswaldo.primerospasos.formviewPager.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.oswaldo.primerospasos.R;
import dev.oswaldo.primerospasos.formviewPager.Usuario;
import dev.oswaldo.primerospasos.util.KeysConstants;

public class FragmentEmail extends Fragment {
    public static final String TAG = FragmentEmail.class.getSimpleName();

    @BindView(R.id.textViewEmail)
    TextView mTextViewEmail;

    Usuario usuario;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuario = (Usuario) getArguments().getSerializable(KeysConstants.USUARIO);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_email, container, false);
        ButterKnife.bind(this, view);

        mTextViewEmail.setText(usuario.getEmail());
        return view;
    }

    public static FragmentEmail newInstance(Usuario usuario){
        FragmentEmail myFragment = new FragmentEmail();
        Bundle args = new Bundle();
        args.putSerializable(KeysConstants.USUARIO, usuario);
        myFragment.setArguments(args);
        return myFragment;
    }

}
