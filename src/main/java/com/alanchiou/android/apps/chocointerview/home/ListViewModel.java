package com.alanchiou.android.apps.chocointerview.home;

import android.app.Application;

import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.alanchiou.android.apps.chocointerview.data.Drama;
import com.alanchiou.android.apps.chocointerview.data.Repository;

import java.util.List;

public final class ListViewModel extends AndroidViewModel {

    private final Repository repository;
    private final LiveData<List<Drama>> allDramasLiveData;
    private final MutableLiveData<String> queryLiveData = new MutableLiveData<>();
    private final MediatorLiveData<List<Drama>> dramasLiveData = new MediatorLiveData<>();

    @Nullable
    private LiveData<List<Drama>> queryResultLiveData;

    public ListViewModel(Application application) {
        super(application);
        repository = Repository.getInstance(application);
        allDramasLiveData = repository.queryDramas();
        queryLiveData.observeForever(repository::setLastSearch);
        dramasLiveData.addSource(repository.queryDramas(), dramas -> mergeDramas(dramas,
                queryResultLiveData == null ? null : queryResultLiveData.getValue()));
        dramasLiveData.addSource(queryLiveData, query -> mergeDramas(allDramasLiveData.getValue(),
                queryResultLiveData == null ? null : queryResultLiveData.getValue()));
    }

    public LiveData<List<Drama>> getDramasLiveData() {
        return dramasLiveData;
    }

    void onSearchClosed() {
        if (queryResultLiveData != null) {
            dramasLiveData.removeSource(queryResultLiveData);
            queryResultLiveData = null;
        }
        queryLiveData.setValue(null);
    }

    void search(String query) {
        queryLiveData.setValue(query);
        if (queryResultLiveData != null) {
            dramasLiveData.removeSource(queryResultLiveData);
            queryResultLiveData = null;
        }
        queryResultLiveData = repository.queryDramasByKeyword(query);
        dramasLiveData.addSource(queryResultLiveData,
                queryResult -> mergeDramas(allDramasLiveData.getValue(), queryResult));
    }

    @Nullable
    String getLastSearch() {
        return repository.getLastSearch();
    }

    private void mergeDramas(List<Drama> allDramas, @Nullable List<Drama> queryResult) {
        if (queryResult == null) {
            dramasLiveData.setValue(allDramas);
            return;
        }

        dramasLiveData.setValue(queryResult);
    }
}
