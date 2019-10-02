package com.alanchiou.android.apps.chocointerview.home;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.alanchiou.android.apps.chocointerview.R;
import com.alanchiou.android.apps.chocointerview.data.Drama;
import com.bumptech.glide.Glide;

final class CardViewHolder extends RecyclerView.ViewHolder {

  private final ImageView thumbImageViewView;
  private final TextView nameTextView;
  private final TextView ratingTextView;
  private final TextView createdAtTextView;

  CardViewHolder(View itemView) {
    super(itemView);
    thumbImageViewView = itemView.findViewById(R.id.thumb);
    nameTextView = itemView.findViewById(R.id.name);
    ratingTextView = itemView.findViewById(R.id.rating);
    createdAtTextView = itemView.findViewById(R.id.created_at);
  }

  void setupDrama(Drama drama) {
    nameTextView.setText(drama.getName());
    ratingTextView.setText(String.valueOf(drama.getRating()));
    createdAtTextView.setText(drama.getCreatedAt().toString());
    Glide
        .with(itemView)
        .load(drama.thumbUrl)
        .into(thumbImageViewView);
  }
}
