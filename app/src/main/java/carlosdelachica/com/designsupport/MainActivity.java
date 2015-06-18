package carlosdelachica.com.designsupport;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
    implements AppBarLayout.OnOffsetChangedListener {

  @InjectView(R.id.toolbar) Toolbar toolbar;
  @InjectView(R.id.appbar) AppBarLayout appbar;
  @InjectView(R.id.viewpager) ViewPager viewpager;
  //@InjectView(R.id.collapsingToolbarLayout) CollapsingToolbarLayout collapsingToolbarLayout;
  @InjectView(R.id.tabs) TabLayout tabs;
  @InjectView(R.id.awesomeToolbar) AwesomeToolbar awesomeToolbar;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.inject(this);
    initUi();
  }

  private void initUi() {
    setSupportActionBar(toolbar);
    initViewPager();
    appbar.addOnOffsetChangedListener(this);
    //collapsingToolbarLayout.setTitle(getTitle());
  }

  private void initViewPager() {
    viewpager.setOffscreenPageLimit(4);
    viewpager.setAdapter(new ViewPagerAdapter());
    viewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int color;
        switch (position) {
          default:
          case 0:
            color = R.color.accent;
            break;
          case 1:
            color = R.color.primary;
            break;
          case 2:
            color = R.color.primary_dark;
            break;
        }
        awesomeToolbar.setColor(getResources().getColor(color));
      }
    });
    tabs.setupWithViewPager(viewpager);
  }

  @Override public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
    awesomeToolbar.onOffsetChanged(offset);
  }

  class ViewPagerAdapter extends PagerAdapter {

    private List<String> items = new ArrayList<>();

    public ViewPagerAdapter() {
      items.add("1");
      items.add("2");
      items.add("3");
    }

    @Override public int getCount() {
      return items.size();
    }

    @Override public Object instantiateItem(ViewGroup container, int position) {
      TextView textView = new TextView(container.getContext());
      textView.setText(items.get(position));
      container.addView(textView);
      return textView;
    }

    @Override public CharSequence getPageTitle(int position) {
      return items.get(position);
    }

    @Override public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView((View) object);
    }

    @Override public boolean isViewFromObject(View view, Object object) {
      return view == object;
    }
  }
}
