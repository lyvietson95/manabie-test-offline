package com.example.manabie.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.manabie.resources.ManabieRepository;
import com.example.manabie.resources.model.ManabieItem;

import java.util.List;

public class ManabieViewModel extends AndroidViewModel {
    private ManabieRepository manabieRepository;
    private LiveData<List<ManabieItem>> mListManabie;

    public ManabieViewModel(@NonNull Application application) {
        super(application);
        manabieRepository = new ManabieRepository(application);
        mListManabie = manabieRepository.getListManabie();
    }

    public LiveData<List<ManabieItem>> getListManabie(){
        return mListManabie;
    }
}
