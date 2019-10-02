package com.alanchiou.android.apps.chocointerview.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.ListAdapter;

import com.alanchiou.android.apps.chocointerview.R;
import com.alanchiou.android.apps.chocointerview.data.Drama;


final class DramaAdapter extends ListAdapter<Drama, CardViewHolder> {

  private final LayoutInflater layoutInflater;

  DramaAdapter(Context context) {
    super(new DramaItemCallback());
    layoutInflater = LayoutInflater.from(context);
  }

  @Override
  public int getItemViewType(int position) {
    return R.layout.drama_card;
  }

  @Override
  public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return new CardViewHolder(
        layoutInflater.inflate(viewType, parent, /* attachToRoot= */  false));
  }

  @Override
  public void onBindViewHolder(CardViewHolder holder, int position) {
    holder.setupDrama(getItem(position));
  }
}
