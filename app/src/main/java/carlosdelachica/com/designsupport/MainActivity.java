package carlosdelachica.com.designsupport;

import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity
        implements AppBarLayout.OnOffsetChangedListener {

    @InjectView(R.id.appbar)
    AppBarLayout appbar;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;
    @InjectView(R.id.tabs)
    TabLayout tabs;
    @InjectView(R.id.awesomeToolbar)
    AwesomeHeader awesomeHeader;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.fab)
    FloatingActionButton fab;

    private ColorCalculator colorCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
        viewpager.setAdapter(new ViewPagerAdapter());
        viewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                setHeaderColor(colorCalculator.calculateBrightColor(positionOffset, position));
                setFabColor(colorCalculator.calculateAccentColor(positionOffset, position));
                setStatusBarColor(colorCalculator.calculateDarkColor(positionOffset, position));
            }
        });
        tabs.setupWithViewPager(viewpager);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
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

    class ViewPagerAdapter extends PagerAdapter {

        private List<String> items = new ArrayList<>();

        public ViewPagerAdapter() {
            items.add("1");
            items.add("2");
            items.add("3");
        }

        @Override
        public int getCount() {
            return items.size();
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PagerView pagerView = new PagerView(MainActivity.this);
            container.addView(pagerView);
            return pagerView;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return items.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
