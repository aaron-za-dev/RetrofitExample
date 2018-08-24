package com.zadev.aaron.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private RecyclerView pokeRecycler;
    private List<Pokemon> pokemons;
    private PokemonAdapter adapter;
    private int offset = 0;
    private boolean isEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pokeRecycler = findViewById(R.id.recycler_poke);
        pokeRecycler.setHasFixedSize(true);
        adapter = new PokemonAdapter(this);
        pokeRecycler.setAdapter(adapter);
        pokeRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(dy > 0){
                    LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();
                    if(isEnd){
                        if((llm.getChildCount() + llm.findFirstVisibleItemPosition()) >= llm.getItemCount()){
                            isEnd = false;
                            offset += 20;
                            loadData(offset);
                        }
                    }
                }
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        isEnd = true;
        loadData(offset);

    }

    private void loadData(int var){

        PokeService service = retrofit.create(PokeService.class);
        service.response(20, var).enqueue(new Callback<MyResponse>() {
            @Override
            public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
                isEnd = true;
                if(response.isSuccessful()){
                    pokemons = response.body().getResults();
                    adapter.setItems(new ArrayList<Pokemon>(pokemons));
                }
            }

            @Override
            public void onFailure(Call<MyResponse> call, Throwable t) {
                isEnd = true;
            }
        });

    }



}
