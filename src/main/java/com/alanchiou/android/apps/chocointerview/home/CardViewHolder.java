package com.alanchiou.android.apps.chocointerview.home;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.alanchiou.android.apps.chocointerview.data.Drama;
import com.alanchiou.android.apps.chocointerview.databinding.DramaCardBinding;
import com.alanchiou.android.apps.chocointerview.drama.DramaActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

public final class CardViewHolder extends RecyclerView.ViewHolder {

    private final DramaCardBinding binding;

    @BindingAdapter(value = {"imageUrl", "imageLoadedListener"}, requireAll = false)
    public static void bindImage(ImageView imageView, String url,
                                 @Nullable DramaActivity.OnImageLoadedListener imageLoadedListener) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model,
                                                Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model,
                                                   Target<Drawable> target, DataSource dataSource,
                                                   boolean isFirstResource) {
                        if (imageLoadedListener != null) {
                            imageLoadedListener.onImageLoaded();
                        }

                        return false;
                    }
                })
                .into(imageView);
    }

    CardViewHolder(DramaCardBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    void bind(Drama drama, DramaAdapter.OnDramaClickListener dramaClickListener) {
        binding.setDrama(drama);
        binding.setClickListener(dramaClickListener);
        binding.executePendingBindings();
    }
}
