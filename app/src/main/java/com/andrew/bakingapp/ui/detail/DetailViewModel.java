package com.andrew.bakingapp.ui.detail;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.andrew.bakingapp.database.ApplicationDB;
import com.andrew.bakingapp.database.entity.BakeEntity;
import com.andrew.bakingapp.model.Bake;

public class DetailViewModel extends AndroidViewModel {

    private Context mContext;
    private DetailCallback callback;

    public void setCallback(DetailCallback callback) {
        this.callback = callback;
    }

    public DetailViewModel(@NonNull Application application) {
        super(application);
        mContext = application.getApplicationContext();
    }

    public void insertData(Bake bake) {
        BakeEntity bakeEntity = new BakeEntity();
        bakeEntity.setId(bake.getId());
        ApplicationDB.getInstance(mContext).bakeDao().insertToDB(bakeEntity);
        getFavoriteBake(bakeEntity);
    }

    public void deleteData(Bake bake) {
        BakeEntity bakeEntity = new BakeEntity();
        bakeEntity.setId(bake.getId());
        ApplicationDB.getInstance(mContext).bakeDao().deleteFromDB(bakeEntity);
        getFavoriteBake(bakeEntity);
    }

    public void getFavoriteBake(BakeEntity bakeEntity) {
        BakeEntity entity = ApplicationDB.getInstance(mContext).bakeDao().selectDB(bakeEntity.getId());
        boolean isFavorite = entity != null;
        callback.onFavoriteChanges(isFavorite);
    }
}
