package com.example.android.pagingstackoverflow;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.example.android.pagingstackoverflow.Model.ApiStackData;
import com.example.android.pagingstackoverflow.Model.Item;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer, Item> {

    //el tamaño de la pagina que solicitamos
    public static final int PAGE_SIZE = 50;

    //empezaremos con la pagina 1
    private static final int FIRST_PAGE = 1;

    //necesitamos obtener datos de StackOverFlow
    private static final String SITE_NAME = "stackoverflow";


    //this will be called once to load the initial data
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Item> callback) {
        RetrofitClient.getInstance()
                .getApi().getAnswers(FIRST_PAGE, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<ApiStackData>() {
                    @Override
                    public void onResponse(Call<ApiStackData> call, Response<ApiStackData> response) {
                        if (response.body() != null) {
                            callback.onResult(response.body().getItems(), null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiStackData> call, Throwable t) {

                    }
                });
    }

    //this will load the previous page
    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {
        RetrofitClient.getInstance()
                .getApi().getAnswers(params.key, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<ApiStackData>() {
                    @Override
                    public void onResponse(Call<ApiStackData> call, Response<ApiStackData> response) {

                        //if the current page is greater than one
                        //we are decrementing the page number
                        //else there is no previous page
                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                        if (response.body() != null) {

                            //passing the loaded data
                            //and the previous page key
                            callback.onResult(response.body().getItems(), adjacentKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiStackData> call, Throwable t) {

                    }
                });
    }

    //this will load the next page
    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {
        RetrofitClient.getInstance()
                .getApi()
                .getAnswers(params.key, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<ApiStackData>() {
                    @Override
                    public void onResponse(Call<ApiStackData> call, Response<ApiStackData> response) {

                        if (response.body() != null) {
                            //if the response has next page
                            //incrementing the next page number
                            Integer key = response.body().getHasMore() ? params.key + 1 : null;

                            //passing the loaded data and next page value
                            callback.onResult(response.body().getItems(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiStackData> call, Throwable t) {

                    }
                });
    }
}