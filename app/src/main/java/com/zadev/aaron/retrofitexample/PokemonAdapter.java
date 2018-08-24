package com.zadev.aaron.retrofitexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

    private ArrayList<Pokemon> items;
    private Context context;


    public PokemonAdapter(Context c) {
        items = new ArrayList<Pokemon>();
        context = c;
    }

    public void setItems (ArrayList<Pokemon> newData){
        items.addAll(newData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pokemon, parent, false);
        return new PokemonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
        holder.pokemonName.setText(items.get(position).getName().toUpperCase());
        Glide.with(context).
                load("https://pokeapi.co/media/sprites/pokemon/"+items.get(position).getIDPokemon()+".png").
                apply(RequestOptions.centerCropTransform()).
                apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)).
                transition(DrawableTransitionOptions.withCrossFade()).
                into(holder.imagePokemon);
    }

    @Override
    public int getItemCount() {
        if(items != null){
            return items.size();
        }
        else {
            return  0;
        }
    }
}
