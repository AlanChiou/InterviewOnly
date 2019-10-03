package com.alanchiou.android.apps.chocointerview.home;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ItemDecoration;
import androidx.recyclerview.widget.RecyclerView.State;
import com.alanchiou.android.apps.chocointerview.R;

final class ListItemDecoration extends ItemDecoration {

  private final int horizontalGap;
  private final int verticalGap;

  ListItemDecoration(Resources resources) {
    horizontalGap = resources.getDimensionPixelSize(R.dimen.home_horizontal_gap);
    verticalGap = resources.getDimensionPixelSize(R.dimen.home_vertical_gap);
  }

  @Override
  public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
      @NonNull RecyclerView parent, @NonNull State state) {
    outRect.set(horizontalGap, verticalGap, horizontalGap, verticalGap);
  }
}
