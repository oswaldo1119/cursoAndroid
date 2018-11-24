package dev.oswaldo.primerospasos.formviewPager.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.oswaldo.primerospasos.R;
import dev.oswaldo.primerospasos.formviewPager.Usuario;
import dev.oswaldo.primerospasos.util.KeysConstants;

public class FragmentDatos extends Fragment {
    public static final String TAG = FragmentDatos.class.getSimpleName();

    @BindView(R.id.textViewNombre)
    TextView mTextViewNombre;

    @BindView(R.id.textViewApellidos)
    TextView mTextViewApellidos;

    Usuario usuario;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        usuario = (Usuario) getArguments().getSerializable(KeysConstants.USUARIO);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_datos, container, false);
        ButterKnife.bind(this, view);

        mTextViewNombre.setText(usuario.getNombre());
        mTextViewApellidos.setText(usuario.getApellidos());

        return view;
    }

    public static FragmentDatos newInstance(Usuario usuario){
        FragmentDatos myFragment = new FragmentDatos();
        Bundle args = new Bundle();
        args.putSerializable(KeysConstants.USUARIO, usuario);
        myFragment.setArguments(args);
        return myFragment;
    }
}
