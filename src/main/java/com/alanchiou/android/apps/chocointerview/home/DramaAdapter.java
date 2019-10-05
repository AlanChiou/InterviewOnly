package com.alanchiou.android.apps.chocointerview.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.ListAdapter;

import com.alanchiou.android.apps.chocointerview.R;
import com.alanchiou.android.apps.chocointerview.data.Drama;
import com.alanchiou.android.apps.chocointerview.databinding.DramaCardBinding;


public final class DramaAdapter extends ListAdapter<Drama, CardViewHolder> {

    private final LayoutInflater layoutInflater;
    private final OnDramaClickListener dramaClickListener;

    public interface OnDramaClickListener {
        void onDramaClicked(View view, int id);
    }

    DramaAdapter(Context context, OnDramaClickListener dramaClickListener) {
        super(new DramaItemCallback());
        layoutInflater = LayoutInflater.from(context);
        this.dramaClickListener = dramaClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.drama_card;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CardViewHolder(DramaCardBinding.inflate(layoutInflater, parent,
                /* attachToRoot= */false));
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        holder.bind(getItem(position), dramaClickListener);
    }
}
