package com.alanchiou.android.apps.chocointerview.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alanchiou.android.apps.chocointerview.R;
import com.alanchiou.android.apps.chocointerview.data.Drama;
import com.alanchiou.android.apps.chocointerview.databinding.ActivityListBinding;
import com.alanchiou.android.apps.chocointerview.drama.DramaActivity;
import com.alanchiou.android.apps.chocointerview.http.DownloadJobService;

import java.util.List;

public class ListActivity extends AppCompatActivity implements DramaAdapter.OnDramaClickListener {

    private ListViewModel listViewModel;
    @Nullable
    private AutoClearFocusHelper autoClearFocusHelper;

    @BindingAdapter("dramas")
    public static void updateDramas(RecyclerView recyclerView, List<Drama> dramas) {
        if (recyclerView.getAdapter() instanceof DramaAdapter) {
            ((DramaAdapter) recyclerView.getAdapter()).submitList(dramas);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        listViewModel = ViewModelProviders.of(/* activity= */this).get(ListViewModel.class);
        ActivityListBinding binding = DataBindingUtil.setContentView(/* activity= */this,
                R.layout.activity_list);
        binding.setLifecycleOwner(this);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(/* context= */this));
        DramaAdapter adapter = new DramaAdapter(/* context= */this, /* dramaClickListener= */this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new ListItemDecoration(getResources()));
        binding.setDramas(listViewModel.getDramasLiveData());

        DownloadJobService.enqueueJob(/* context= */this);
    }

    @Override
    public void onDramaClicked(View view, int id) {
        Intent intent = new Intent(/* packageContext= */this, DramaActivity.class);
        intent.putExtra(DramaActivity.EXTRA_ID, id);
        ActivityOptionsCompat options =
                ActivityOptionsCompat.makeSceneTransitionAnimation(/* activity= */this,
                        view.findViewById(R.id.thumb),
                        "thumb");
        startActivity(intent, options.toBundle());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        autoClearFocusHelper = new AutoClearFocusHelper(searchView);
        searchView.setOnCloseListener(() -> {
            listViewModel.onSearchClosed();
            invalidateOptionsMenu();

            return false;
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                listViewModel.search(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                listViewModel.search(newText);

                return false;
            }
        });
        String query = listViewModel.getLastSearch();
        if (!TextUtils.isEmpty(query)) {
            searchItem.expandActionView();
            searchView.setQuery(query, /* submit= */true);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (autoClearFocusHelper != null) {
            autoClearFocusHelper.onActivityDispatchTouchEvent(ev);
        }

        return super.dispatchTouchEvent(ev);
    }
}
