package com.zadev.aaron.retrofitexample;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PokemonViewHolder extends RecyclerView.ViewHolder {

    public TextView pokemonName;
    public ImageView imagePokemon;

    public PokemonViewHolder(View itemView) {
        super(itemView);
        pokemonName = itemView.findViewById(R.id.pokemonName);
        imagePokemon = itemView.findViewById(R.id.pokemonImage);
    }
}
