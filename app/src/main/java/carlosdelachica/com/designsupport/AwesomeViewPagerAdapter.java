package carlosdelachica.com.designsupport;

import android.content.Context;
import android.view.View;
import carlosdelachica.com.designsupport.view_pager_adapter.ViewPagerAdapter;
import java.util.ArrayList;
import java.util.List;

public class AwesomeViewPagerAdapter extends ViewPagerAdapter {

  private static List<String> items = new ArrayList<>();

  static {
    items.add("1");
    items.add("2");
    items.add("3");
  }

  @Override public CharSequence getPageTitle(int position) {
    return items.get(position);
  }

  @Override public int getCount() {
    return items.size();
  }

  @Override protected View getView(Context context) {
    return new PagerView(context);
  }
}
