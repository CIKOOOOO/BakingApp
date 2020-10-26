package com.andrew.bakingapp.ui.main.fragment;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.andrew.bakingapp.api.ApiClient;
import com.andrew.bakingapp.api.ApiInterface;
import com.andrew.bakingapp.database.ApplicationDB;
import com.andrew.bakingapp.database.entity.BakeEntity;
import com.andrew.bakingapp.model.Bake;
import com.andrew.bakingapp.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<List<Bake>> mutableLiveData;
    private LiveData<List<BakeEntity>> listLiveData;
    private Context mContext;
    private MainCallback callback;

    public void setCallback(MainCallback callback) {
        this.callback = callback;
    }

    public MainViewModel(@NonNull Application application) {
        super(application);
        mutableLiveData = new MutableLiveData<>();
        mContext = application.getApplicationContext();
    }

    public LiveData<List<Bake>> getMutableLiveData() {
        return mutableLiveData;
    }

    public LiveData<List<BakeEntity>> getListLiveData() {
        return listLiveData;
    }

    public void getReceiptList(String receiptType) {
        if (receiptType.equals(Constant.TAB_RECEIPT)) {
            fromServer();
        } else {
            fromDb();
        }
    }

    private void fromServer() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Bake>> listCall = apiInterface.getReceiptList();
        listCall.enqueue(new Callback<List<Bake>>() {
            @Override
            public void onResponse(Call<List<Bake>> call, Response<List<Bake>> response) {
                if (response.body() != null) {
                    for (int i = 0; i < response.body().size(); i++) {
                        response.body().get(i).setImage(Constant.IMAGE_ASSET_URL[i]);
                    }
                    mutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Bake>> call, Throwable t) {

            }
        });
    }

    private void fromDb() {
        listLiveData = ApplicationDB.getInstance(mContext).bakeDao().selectAllFavorite();
    }

    public void fetchingData(List<BakeEntity> bakeEntities) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<List<Bake>> listCall = apiInterface.getReceiptList();
        listCall.enqueue(new Callback<List<Bake>>() {
            @Override
            public void onResponse(Call<List<Bake>> call, Response<List<Bake>> response) {
                if (response.body() != null) {
                    List<Bake> bakeList = new ArrayList<>();

                    for (BakeEntity entity : bakeEntities) {
                        for (int i = 0; i < response.body().size(); i++) {
                            Bake bake = response.body().get(i);
                            if (entity.getId() == bake.getId()) {
                                bake.setImage(Constant.IMAGE_ASSET_URL[i]);
                                bakeList.add(bake);
                                break;
                            }
                        }
                    }

                    callback.onCallbackFromDB(bakeList);
                }
            }

            @Override
            public void onFailure(Call<List<Bake>> call, Throwable t) {

            }
        });
    }
}
