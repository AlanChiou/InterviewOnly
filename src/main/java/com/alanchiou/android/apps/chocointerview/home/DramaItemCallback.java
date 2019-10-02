package com.alanchiou.android.apps.chocointerview.home;

import android.text.TextUtils;

import androidx.recyclerview.widget.DiffUtil;

import com.alanchiou.android.apps.chocointerview.data.Drama;

final class DramaItemCallback extends DiffUtil.ItemCallback<Drama> {

  @Override
  public boolean areItemsTheSame(Drama oldDrama, Drama newDrama) {
    return oldDrama.getId() == newDrama.getId();
  }

  @Override
  public boolean areContentsTheSame(Drama oldDrama, Drama newDrama) {
    if (!TextUtils.equals(oldDrama.getName(), newDrama.getName())) {
      return false;
    }
    if (oldDrama.getTotalViews() != newDrama.getTotalViews()) {
      return false;
    }
    if (!oldDrama.getCreatedAt().equals(newDrama.getCreatedAt())) {
      return false;
    }
    if (!TextUtils.equals(oldDrama.getThumbUrl(), newDrama.getThumbUrl())) {
      return false;
    }
    return oldDrama.getRating() == newDrama.getRating();
  }
}
