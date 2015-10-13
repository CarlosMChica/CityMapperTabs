package carlosdelachica.com.designsupport;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity
    implements AppBarLayout.OnOffsetChangedListener {

  @InjectView(R.id.appbar) AppBarLayout appbar;
  @InjectView(R.id.viewpager) ViewPager viewPager;
  @InjectView(R.id.tabs) TabLayout tabs;
  @InjectView(R.id.awesomeToolbar) AwesomeHeader awesomeHeader;
  @InjectView(R.id.toolbar) Toolbar toolbar;
  @InjectView(R.id.fab) FloatingActionButton fab;

  private ColorCalculator colorCalculator;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.inject(this);
    initUi();
  }

  private void initUi() {
    colorCalculator = new ColorCalculator(this);
    initToolbar();
    initViewPager();
    appbar.addOnOffsetChangedListener(this);
  }

  private void initToolbar() {
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowHomeEnabled(true);
  }

  private void initViewPager() {
    viewPager.setOffscreenPageLimit(1);
    viewPager.setAdapter(new AwesomeViewPagerAdapter());
    viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        setHeaderColor(colorCalculator.calculateBrightColor(positionOffset, position));
        setFabColor(colorCalculator.calculateAccentColor(positionOffset, position));
        setStatusBarColor(colorCalculator.calculateDarkColor(positionOffset, position));
      }
    });
    tabs.setupWithViewPager(viewPager);
  }

  @Override public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
    awesomeHeader.onOffsetChanged(offset);
  }

  private void setHeaderColor(int brightColor) {
    awesomeHeader.setColor(brightColor);
  }

  private void setFabColor(int brightColor) {
    fab.setBackgroundTintList(ColorStateList.valueOf(brightColor));
  }

  private void setStatusBarColor(int color) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      getWindow().setStatusBarColor(color);
    }
  }
}
