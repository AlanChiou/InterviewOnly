package com.alanchiou.android.apps.chocointerview.drama;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.alanchiou.android.apps.chocointerview.data.Drama;
import com.alanchiou.android.apps.chocointerview.data.Repository;

public final class DramaViewModel extends AndroidViewModel {

    private final Repository repository;

    public DramaViewModel(Application application) {
        super(application);
        repository = Repository.getInstance(application);
    }

    public LiveData<Drama> getDramaLiveData(int id) {
        return repository.queryDrama(id);
    }
}
