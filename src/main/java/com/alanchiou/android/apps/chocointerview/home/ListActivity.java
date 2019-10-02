package com.alanchiou.android.apps.chocointerview.home;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alanchiou.android.apps.chocointerview.R;

public class ListActivity extends AppCompatActivity {

  private ListViewModel listViewModel;
  private DramaAdapter adapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    listViewModel = ViewModelProviders.of(/* activity= */this).get(ListViewModel.class);

    setContentView(R.layout.activity_list);
    RecyclerView recyclerView = findViewById(R.id.recycler_view);
    recyclerView.setLayoutManager(new LinearLayoutManager(/* context= */this));
    adapter = new DramaAdapter(/* context= */this);
    recyclerView.setAdapter(adapter);
    recyclerView.addItemDecoration(new ListItemDecoration(getResources()));
    listViewModel.getDramasLiveData()
        .observe(/* owner= */this, dramas -> adapter.submitList(dramas));
  }
}
