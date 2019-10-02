package com.alanchiou.android.apps.chocointerview.home;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.alanchiou.android.apps.chocointerview.data.Drama;
import com.alanchiou.android.apps.chocointerview.data.Repository;
import java.util.List;

public class ListViewModel extends AndroidViewModel {

  private final Repository repository;

  public ListViewModel(Application application) {
    super(application);
    repository = Repository.getInstance(application);
  }

  LiveData<List<Drama>> getDramasLiveData() {
    return repository.queryDramas();
  }
}
