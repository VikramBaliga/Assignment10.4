package projects.android.my.pagerfragment;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ActionBar.TabListener {

    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // find the viewpager
        viewPager = (ViewPager)findViewById(R.id.myPager);
        // Create acton bar
        ActionBar bar = getSupportActionBar();
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        bar.addTab(bar.newTab().setText("Audio").setTabListener(this));
        bar.addTab(bar.newTab().setText("Video").setTabListener(this));
        // Set adaper
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        // Setup listner when we scroll through the fragments
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position)
            {
                //set the action bar same position as that of fragment
                getSupportActionBar().setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft)
    {
        //set appropriate page based on the tab selected through pager
                viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    public static class AudioFragment extends Fragment
    {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            //Load the layout for audio fragment
            View view = inflater.inflate(R.layout.audio_fragment,null);
            return view;
        }
    }
    public static class VideoFragment extends Fragment
    {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            super.onCreateView(inflater, container, savedInstanceState);
            //Load the layout for audio fragment
            View view = inflater.inflate(R.layout.video_fragment,null);
            return view;
        }
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter
    {

        public ViewPagerAdapter(FragmentManager fm)
        {
            super(fm);

        }

        @Override
        public Fragment getItem(int position)
        {
            // if position is 0 load audio else if position is 1 load video
            switch (position)
            {
                case 0: return  new AudioFragment();
                case 1: return  new VideoFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
