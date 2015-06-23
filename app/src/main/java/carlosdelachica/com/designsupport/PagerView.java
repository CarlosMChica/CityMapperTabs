package carlosdelachica.com.designsupport;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class PagerView extends FrameLayout {

    @InjectView(R.id.list)
    RecyclerView list;

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

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.pager_view, this, true);
        ButterKnife.inject(this);
        list.setLayoutManager(new LinearLayoutManager(getContext()));
        list.setAdapter(new Adapter());
    }

    class Adapter extends RecyclerView.Adapter<ItemViewHolder> {

        private List<String> items = new ArrayList<>();

        public Adapter() {
            for (int i = 0; i < 100; i++) {
                items.add("Item: " + i);
            }
        }

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new ItemViewHolder(getContext(), viewGroup);
        }

        @Override
        public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
            itemViewHolder.text.setText(items.get(i));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }
}
