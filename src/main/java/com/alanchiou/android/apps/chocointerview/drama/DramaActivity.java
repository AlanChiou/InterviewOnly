package com.alanchiou.android.apps.chocointerview.drama;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import com.alanchiou.android.apps.chocointerview.R;
import com.alanchiou.android.apps.chocointerview.databinding.ActivityDramaBinding;
import com.alanchiou.android.apps.chocointerview.http.DownloadJobService;

public class DramaActivity extends AppCompatActivity {

    /**
     * Passes a specific drama id to show in {@link DramaActivity}.
     */
    public static final String EXTRA_ID = "id";

    public interface OnImageLoadedListener {
        void onImageLoaded();
    }

    private OnImageLoadedListener imageLoadedListener = this::onImageLoaded;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int id = parseIdFromIntent(getIntent());
        if (id == -1) {
            finish();

            return;
        }

        ActivityDramaBinding binding = DataBindingUtil.setContentView(/* activity= */ this,
                R.layout.activity_drama);
        DramaViewModel dramaViewModel = ViewModelProviders.of(/* activity= */this).get(DramaViewModel.class);
        binding.setImageLoadedListener(imageLoadedListener);
        binding.setDrama(dramaViewModel.getDramaLiveData(id));
        binding.setLifecycleOwner(this);
        dramaViewModel.getDramaLiveData(id).observe(/* owner= */this, drama -> {
            if (drama != null) {
                setTitle(drama.name);
            } else {
                DownloadJobService.enqueueJob(/* context= */this);
            }
        });

        postponeEnterTransition();
    }

    private void onImageLoaded() {
        startPostponedEnterTransition();
    }

    private int parseIdFromIntent(Intent intent) {
        Uri launchUri = intent.getData();
        if (launchUri != null) {
            String lastSegment = launchUri.getLastPathSegment();
            try {
                return TextUtils.isEmpty(lastSegment) ? -1 : Integer.parseInt(lastSegment);
            } catch (NumberFormatException e) {
                return -1;
            }
        }

        return intent.getIntExtra(EXTRA_ID, /* defaultValue= */-1);
    }
}
