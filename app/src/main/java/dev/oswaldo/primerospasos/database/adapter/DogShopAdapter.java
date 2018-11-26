package dev.oswaldo.primerospasos.database.adapter;

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
import dev.oswaldo.primerospasos.database.model.DogShop;
import io.realm.RealmList;

public class DogShopAdapter extends RecyclerView.Adapter<DogShopAdapter.ViewHolderAdapter> {

    RealmList<DogShop> dogShops;

    Context adapterContext;

    DogShopClick dogShopClick;

    DogShopLongClick dogShopLongClick;

    public DogShopAdapter(RealmList<DogShop> dogShops, Context adapterContext, DogShopClick dogShopClick, DogShopLongClick dogShopLongClick) {
        this.dogShops = dogShops;
        this.adapterContext = adapterContext;
        this.dogShopClick = dogShopClick;
        this.dogShopLongClick = dogShopLongClick;
    }


    @NonNull
    @Override
    public ViewHolderAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        adapterContext = viewGroup.getContext();
        View view = LayoutInflater.from(adapterContext).inflate(R.layout.item_dog_shop, viewGroup, false);
        return new ViewHolderAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAdapter viewHolderAdapter, int position) {
        DogShop dogShop = dogShops.get(position);
        viewHolderAdapter.setTextsAndImage(dogShop);
        viewHolderAdapter.rootView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dogShopClick.onDogShopClickListener(dogShop);
            }
        });
        viewHolderAdapter.rootView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                dogShopLongClick.onDogShopLongClickListener(dogShop);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return dogShops.size();
    }

    public class ViewHolderAdapter extends RecyclerView.ViewHolder {

        @BindView(R.id.imageViewDogShop) ImageView mImgShop;
        @BindView(R.id.textViewDogShopName) TextView mTvDogShopName;
        @BindView(R.id.textViewAddress) TextView mTvDogShopAddress;

        View rootView;

        public ViewHolderAdapter(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            rootView = itemView;
        }

        public void setTextsAndImage(DogShop dogShop){
            Glide.with(adapterContext)
                    .load(dogShop.image)
                    .centerCrop()
                    .crossFade(1500)
                    .into(mImgShop);
            mTvDogShopName.setText("Nombre: " + dogShop.name);
            mTvDogShopAddress.setText("Dirección: " + dogShop.address);
        }
    }
}
