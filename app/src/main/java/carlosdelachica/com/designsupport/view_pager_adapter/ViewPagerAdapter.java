package carlosdelachica.com.designsupport.view_pager_adapter;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

public abstract class ViewPagerAdapter extends PagerAdapter {

  private static final String SAVED_STATE = "ViewPagerAdapter:saved_state";

  private final SparseArray<Parcelable>[] savedStates;
  private final View[] views;

  public ViewPagerAdapter() {
    this.savedStates = new SparseArray[getCount()];
    this.views = new View[getCount()];
  }

  @Override public Parcelable saveState() {
    Bundle state = new Bundle();
    for (int i = 0; i < views.length; i++) {
      SparseArray<Parcelable> container = new SparseArray<>();
      View view = views[i];
      if (view != null) {
        view.saveHierarchyState(container);
      }
      savedStates[i] = container;
      state.putSparseParcelableArray(SAVED_STATE + i, savedStates[i]);
    }
    return state;
  }

  @Override public void restoreState(Parcelable state, ClassLoader loader) {
    if (state instanceof Bundle) {
      for (int i = 0; i < views.length; i++) {
        SparseArray<Parcelable> viewStates =
            ((Bundle) state).getSparseParcelableArray(SAVED_STATE + i);
        if (viewStates != null) {
          savedStates[i] = viewStates;
        }
      }
    }
  }

  @Override public Object instantiateItem(ViewGroup container, int position) {
    View view = getView(container.getContext());
    restoreViewState(position, view);
    views[position] = view;
    container.addView(view);
    return view;
  }

  @Override public void destroyItem(ViewGroup container, int position, Object object) {
    View view = (View) object;
    container.removeView(view);
    SparseArray<Parcelable> viewStateContainer = new SparseArray<>();
    views[position].saveHierarchyState(viewStateContainer);
    savedStates[position] = viewStateContainer;
    views[position] = null;
  }

  @Override public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  private void restoreViewState(int position, View view) {
    if (savedStates.length > position) {
      SparseArray<Parcelable> state = savedStates[position];
      if (state != null) {
        view.restoreHierarchyState(state);
      }
    }
  }

  protected abstract View getView(Context context);
}
