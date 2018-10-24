package com.example.android.pagingstackoverflow;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.pagingstackoverflow.Model.Item;

public class MainActivity extends AppCompatActivity {

    //getting recyclerview
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //setting up recyclerview
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //getting our ItemViewModel
        ItemViewModel itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);

        //creating the Adapter
        final ItemAdapter adapter = new ItemAdapter(this);


        //observing the itemPagedList from view model
        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<Item>>() {
            @Override
            public void onChanged(@Nullable PagedList<Item> items) {

                //in case of any changes
                //submitting the items to adapter
                adapter.submitList(items);
            }
        });

        //setting the adapter
        recyclerView.setAdapter(adapter);
    }
}
