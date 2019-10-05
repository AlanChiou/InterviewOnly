package com.alanchiou.android.apps.chocointerview.home;

import android.text.TextUtils;

import androidx.recyclerview.widget.DiffUtil;

import com.alanchiou.android.apps.chocointerview.data.Drama;

final class DramaItemCallback extends DiffUtil.ItemCallback<Drama> {

    @Override
    public boolean areItemsTheSame(Drama oldDrama, Drama newDrama) {
        return oldDrama.id == newDrama.id;
    }

    @Override
    public boolean areContentsTheSame(Drama oldDrama, Drama newDrama) {
        if (!TextUtils.equals(oldDrama.name, newDrama.name)) {
            return false;
        }
        if (oldDrama.totalViews != newDrama.totalViews) {
            return false;
        }
        if (!oldDrama.createdAt.equals(newDrama.createdAt)) {
            return false;
        }
        if (!TextUtils.equals(oldDrama.thumbUrl, newDrama.thumbUrl)) {
            return false;
        }
        return oldDrama.rating == newDrama.rating;
    }
}
