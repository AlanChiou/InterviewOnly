package com.alanchiou.android.apps.chocointerview.home;

import android.content.Context;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.alanchiou.android.apps.chocointerview.data.Drama;
import com.alanchiou.android.apps.chocointerview.databinding.DramaCardBinding;
import com.bumptech.glide.Glide;

public final class CardViewHolder extends RecyclerView.ViewHolder {

    private final DramaCardBinding binding;

    @BindingAdapter("bind:imageUrl")
    public static void bindImage(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context)
                .load(url)
                .into(imageView);
    }

    CardViewHolder(DramaCardBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    void bind(Drama drama) {
        binding.setDrama(drama);
        binding.executePendingBindings();
    }
}
