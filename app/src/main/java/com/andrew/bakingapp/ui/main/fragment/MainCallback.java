package com.andrew.bakingapp.ui.main.fragment;

import com.andrew.bakingapp.model.Bake;

import java.util.List;

public interface MainCallback {
    void onCallbackFromDB(List<Bake> bakeList);
}
