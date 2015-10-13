package carlosdelachica.com.designsupport;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class ItemViewHolder extends RecyclerView.ViewHolder {
  @InjectView(R.id.text) TextView text;

  public ItemViewHolder(final Context context, ViewGroup viewGroup) {
    super(LayoutInflater.from(context).inflate(R.layout.item_view, viewGroup, false));
    ButterKnife.inject(this, itemView);
    itemView.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        context.startActivity(new Intent(context, SecondActivity.class));
      }
    });
  }
}
