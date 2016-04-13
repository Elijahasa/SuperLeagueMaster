package ntv.upgrade.superleaguemaster;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ntv.upgrade.superleaguemaster.Adapters.NewsFeedAdapter;
import ntv.upgrade.superleaguemaster.Drawer.DrawerSelector;
import ntv.upgrade.superleaguemaster.NewsFeed.NewsFeedItem;

public class ActivityNewsDetails extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentNewsFeedDetails.OnListFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private DrawerLayout drawer;
    private Activity thisActivity;

    private List<NewsFeedItem> newsFeedItems = new ArrayList<>();
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_feed_details);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, FragmentNewsFeedDetails.getToolbar(), R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_main);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        populateDummyNewsFeedItems();

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setOffscreenPageLimit(6);



    }

    public void populateDummyNewsFeedItems(){
        newsFeedItems.add(new NewsFeedItem(R.drawable.bg_upgrade, "Domingo 27 abril será la Convivencia Fútbolera de Garrincha FC"));
        newsFeedItems.add(new NewsFeedItem(R.drawable.bg_upgrade, "Garrincha FC logra primer Lugar en Copa Pempén de Media Cancha."));
        newsFeedItems.add(new NewsFeedItem(R.drawable.bg_upgrade, "Garrincha FC defenderá título de Copa Regional en El Seibo"));
        newsFeedItems.add(new NewsFeedItem(R.drawable.bg_upgrade, "Domingo 27 abril será la Convivencia Fútbolera de Garrincha FC"));
        newsFeedItems.add(new NewsFeedItem(R.drawable.bg_upgrade, "Garrincha FC logra primer Lugar en Copa Pempén de Media Cancha."));
        newsFeedItems.add(new NewsFeedItem(R.drawable.bg_upgrade, "Garrincha FC defenderá título de Copa Regional en El Seibo"));
        newsFeedItems.add(new NewsFeedItem(R.drawable.bg_upgrade, "Domingo 27 abril será la Convivencia Fútbolera de Garrincha FC"));
        newsFeedItems.add(new NewsFeedItem(R.drawable.bg_upgrade, "Garrincha FC logra primer Lugar en Copa Pempén de Media Cancha."));
        newsFeedItems.add(new NewsFeedItem(R.drawable.bg_upgrade, "Upgrade, We Create"));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_news_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


            Intent intent = DrawerSelector.onItemSelected(this, id);

            if (intent != null) {

                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onListFragmentInteraction() {

    }

    @Override
    public void onBackPressed() {

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return FragmentNewsFeedDetails.newInstance();
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return newsFeedItems.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}