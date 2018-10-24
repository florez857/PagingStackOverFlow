package com.example.android.pagingstackoverflow;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.example.android.pagingstackoverflow.Model.Item;

public class ItemDataSourceFactory extends DataSource.Factory {

    //creating the mutable live data
    private MutableLiveData<PageKeyedDataSource<Integer, Item>> itemLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Integer, Item> create() {
        //getting our data source object
        ItemDataSource itemDataSource = new ItemDataSource();

        //posting the datasource to get the values
        itemLiveDataSource.postValue(itemDataSource);

        //returning the datasource
        return itemDataSource;
    }


    //getter for itemlivedatasource
    public MutableLiveData<PageKeyedDataSource<Integer, Item>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}