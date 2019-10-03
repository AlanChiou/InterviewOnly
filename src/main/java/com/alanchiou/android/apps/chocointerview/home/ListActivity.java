package com.alanchiou.android.apps.chocointerview.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alanchiou.android.apps.chocointerview.R;
import com.alanchiou.android.apps.chocointerview.data.Drama;
import com.alanchiou.android.apps.chocointerview.databinding.ActivityListBinding;
import com.alanchiou.android.apps.chocointerview.http.DownloadJobService;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    @BindingAdapter("bind:dramas")
    public static void updateDramas(RecyclerView recyclerView, List<Drama> dramas) {
        if (recyclerView.getAdapter() instanceof DramaAdapter) {
            ((DramaAdapter) recyclerView.getAdapter()).submitList(dramas);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListViewModel listViewModel =
                ViewModelProviders.of(/* activity= */this).get(ListViewModel.class);
        ActivityListBinding binding = DataBindingUtil.setContentView(/* activity= */this,
                R.layout.activity_list);
        binding.setLifecycleOwner(this);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(/* context= */this));
        DramaAdapter adapter = new DramaAdapter(/* context= */this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new ListItemDecoration(getResources()));
        binding.setDramas(listViewModel.getDramasLiveData());

        DownloadJobService.enqueueJob(/* context= */this);
    }
}
