package com.example.android.pagingstackoverflow;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.example.android.pagingstackoverflow.Model.Item;

public class ItemViewModel extends ViewModel {

    //creamos LiveData PagedList  and PagedKeyedDataSource
    LiveData<PagedList<Item>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, Item>> liveDataSource;

    //constructor
    public ItemViewModel() {
        //creamos nuestro DataSourcefactory
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();

        //getting the live data source from data source factory
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();

        //Getting PagedList config
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ItemDataSource.PAGE_SIZE).build();

        //Building the paged list
        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig))
                .build();
    }
}