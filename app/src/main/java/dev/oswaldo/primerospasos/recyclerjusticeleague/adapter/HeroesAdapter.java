package dev.oswaldo.primerospasos.recyclerjusticeleague.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dev.oswaldo.primerospasos.R;
import dev.oswaldo.primerospasos.recyclerjusticeleague.model.Heroe;


public class HeroesAdapter extends RecyclerView.Adapter<HeroesAdapter.ViewHolderAdapter>{

    List<Heroe> heroes;

    Context adapterContext;

    public HeroesAdapter(List<Heroe> heroes, Context adapterContext) {
        this.heroes = heroes;
        this.adapterContext = adapterContext;
    }

    @NonNull
    @Override
    public HeroesAdapter.ViewHolderAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        adapterContext = viewGroup.getContext();
        View view = LayoutInflater.from(adapterContext).inflate(R.layout.item_heroe, viewGroup, false);
        return new HeroesAdapter.ViewHolderAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroesAdapter.ViewHolderAdapter viewHolderAdapter, int position) {
        Heroe heroe = heroes.get(position);
        viewHolderAdapter.setTextsAndImage(heroe);
    }

    @Override
    public int getItemCount() {
        return heroes.size();
    }

    public class ViewHolderAdapter extends RecyclerView.ViewHolder {

        @BindView(R.id.imageViewHeroe)
        ImageView mImgHeroe;
        @BindView(R.id.textViewMainCharacter) TextView mTvHeroeMainCharacter;
        @BindView(R.id.textViewTitle) TextView mTvHeroeTitle;
        @BindView(R.id.textViewYear) TextView mTvHeroeYear;

        View rootView;

        public ViewHolderAdapter(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            rootView = itemView;
        }

        public void setTextsAndImage(Heroe heroe){
            Glide.with(adapterContext)
                    .load(heroe.image)
                    .centerCrop()
                    .crossFade(1500)
                    .into(mImgHeroe);
            mTvHeroeMainCharacter.setText("Protagonista: " + heroe.mainCharacter);
            mTvHeroeTitle.setText("Titulo: " + heroe.title);
            mTvHeroeYear.setText("Año: " + heroe.year);
        }
    }
}
