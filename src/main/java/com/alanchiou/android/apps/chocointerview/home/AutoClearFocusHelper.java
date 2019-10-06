package com.alanchiou.android.apps.chocointerview.home;


import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

import androidx.appcompat.widget.SearchView;

final class AutoClearFocusHelper {

    private final SearchView searchView;

    AutoClearFocusHelper(SearchView searchView) {
        this.searchView = searchView;
    }

    void onActivityDispatchTouchEvent(MotionEvent motionEvent) {
        int[] locationOnScreen = new int[2];
        searchView.getLocationOnScreen(locationOnScreen);
        Rect viewRect = new Rect(locationOnScreen[0], locationOnScreen[1],
                locationOnScreen[0] + searchView.getMeasuredWidth(),
                locationOnScreen[1] + searchView.getMeasuredHeight());
        if (!viewRect.contains((int) motionEvent.getRawX(), (int) motionEvent.getRawY())) {
            searchView.clearFocus();
            hideSoftInputFromWindow();
        }
    }

    void hideSoftInputFromWindow() {
        InputMethodManager inputMethodManager =
                (InputMethodManager) searchView.getContext().
                        getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
    }
}
