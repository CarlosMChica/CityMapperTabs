package carlosdelachica.com.designsupport;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import butterknife.ButterKnife;
import butterknife.InjectView;
import java.util.ArrayList;
import java.util.List;

public class PagerView extends FrameLayout {

  public static final String AWD = "AWD";
  @InjectView(R.id.list) RecyclerView list;
  private Parcelable parcelable;

  public PagerView(Context context) {
    super(context);
    init();
  }

  public PagerView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init();
  }

  public PagerView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  @Override protected Parcelable onSaveInstanceState() {
    Bundle bundle = new Bundle();
    bundle.putParcelable(AWD, list.getLayoutManager().onSaveInstanceState());
    return super.onSaveInstanceState();
  }

  @Override protected void onRestoreInstanceState(Parcelable state) {
    parcelable = ((Bundle) state).getParcelable(AWD);
    super.onRestoreInstanceState(state);
  }

  private void init() {
    setId(View.generateViewId());
    LayoutInflater.from(getContext()).inflate(R.layout.pager_view, this, true);
    ButterKnife.inject(this);
    list.setLayoutManager(new LinearLayoutManager(getContext()));
    list.setAdapter(new Adapter());
    list.getLayoutManager().onRestoreInstanceState(parcelable);
  }

  class Adapter extends RecyclerView.Adapter<ItemViewHolder> {

    private List<String> items = new ArrayList<>();

    public Adapter() {
      for (int i = 0; i < 100; i++) {
        items.add("Item: " + i);
      }
    }

    @Override public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
      return new ItemViewHolder(getContext(), viewGroup);
    }

    @Override public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
      itemViewHolder.text.setText(items.get(i));
    }

    @Override public int getItemCount() {
      return items.size();
    }
  }
}
