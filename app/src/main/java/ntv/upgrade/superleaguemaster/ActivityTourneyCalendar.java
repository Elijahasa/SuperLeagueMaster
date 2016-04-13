package ntv.upgrade.superleaguemaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import ntv.upgrade.superleaguemaster.Adapters.TeamsToolBarSpinnerAdapter;
import ntv.upgrade.superleaguemaster.AppConstants.AppConstant;
import ntv.upgrade.superleaguemaster.AppConstants.Constants;
import ntv.upgrade.superleaguemaster.Drawer.DrawerSelector;


public class ActivityTourneyCalendar extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        FragmentNewsFeed.OnListFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private TourneyCalendarPagerAdapter mTourneyCalendarPagerAdapter;
    private NewsPagerAdapter mNewsFeedPageAdapater;
    private LeadersPagerAdapter mLeaderPageAdapter;
    private DrawerLayout drawer;
    private Activity thisActivity;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private static int mLastSpinnerSelectedItem = 0;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        getSelectedSpinnerItem();
        this.thisActivity = this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View spinnerContainer = LayoutInflater.from(this).inflate(R.layout.toolbar_spinner,
                toolbar, false);
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        toolbar.addView(spinnerContainer, lp);

        TeamsToolBarSpinnerAdapter spinnerAdapter = new TeamsToolBarSpinnerAdapter(getLayoutInflater());
        spinnerAdapter.addItems(setLeagueDivisions());

        Spinner spinner = (Spinner) findViewById(R.id.toolbar_spinner);

        spinner.setAdapter(spinnerAdapter);


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mTourneyCalendarPagerAdapter = new TourneyCalendarPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mTourneyCalendarPagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // navigationView.setCheckedItem(R.id.nav_matches);
        //dynamically adds the tourneys to follow
        createDynamicTournamentMenu(navigationView);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {/*  actionBar.setSelectedNavigationItem(position);*/}
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                changeScreenForTourney(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


    }

    //catches the extras to set the appropriate display
    public int getSelectedSpinnerItem() {
        int selected = 0;
        List<String> extras = setLeagueDivisions();

        for (int i = 0; i < extras.size(); i++) {
            if (getIntent().hasExtra(extras.get(i))) {
                selected = (int) getIntent().getExtras().get(extras.get(i));
            }
        }
        return selected;
    }

    public void cleanAdapters(int pos) {

        switch (pos) {
            case 0:
                mTourneyCalendarPagerAdapter.clearAll();
                break;
            case 1:
                mNewsFeedPageAdapater.clearAll();
                break;
            case 2:
                mLeaderPageAdapter.clearAll();
                break;

        }
    }

    public void changeScreenForTourney(int position) {

        if (mLastSpinnerSelectedItem != position) {

            if (position == 0) {
                cleanAdapters(mLastSpinnerSelectedItem);
                mViewPager = null;
                mViewPager = (ViewPager) findViewById(R.id.container);
                mLastSpinnerSelectedItem = position;
                tabLayout.setVisibility(View.VISIBLE);
                // Create the adapter that will return a fragment for each of the three
                // primary sections of the activity.
                mTourneyCalendarPagerAdapter = new TourneyCalendarPagerAdapter(getSupportFragmentManager());

                // Set up the ViewPager with the sections adapter.
                mViewPager.setAdapter(mTourneyCalendarPagerAdapter);

                tabLayout = (TabLayout) findViewById(R.id.tabs);
                tabLayout.setupWithViewPager(mViewPager);
                drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

                ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
                drawer.setDrawerListener(toggle);
                toggle.syncState();


            } else if (position == 1) {
                cleanAdapters(mLastSpinnerSelectedItem);
                mViewPager = null;
                mViewPager = (ViewPager) findViewById(R.id.container);
                mLastSpinnerSelectedItem = position;
                if (tabLayout.isShown()) {
                    tabLayout.setVisibility(View.GONE);
                }
                mNewsFeedPageAdapater = new NewsPagerAdapter(getSupportFragmentManager());
                // Set up the ViewPager with the sections adapter.
                mViewPager.setAdapter(mNewsFeedPageAdapater);


            } else if (position == 2) {
                cleanAdapters(mLastSpinnerSelectedItem);
                mViewPager = null;
                mViewPager = (ViewPager) findViewById(R.id.container);
                mLastSpinnerSelectedItem = position;
                if (tabLayout.isShown()) {
                    tabLayout.setVisibility(View.GONE);
                }
                mLeaderPageAdapter = new LeadersPagerAdapter(getSupportFragmentManager());
                // Set up the ViewPager with the sections adapter.
                mViewPager.setAdapter(mLeaderPageAdapter);
            }
        }
    }


    /***
     * add the all the tournament available to follow from the DB
     ***/
    public void createDynamicTournamentMenu(NavigationView navigationView) {
        final Menu menu = navigationView.getMenu();
        final NavigationView nav = navigationView;
        //adds all the available tourneys to the navigation Torneos Group
        for (int tourney = 0; tourney < AppConstant.availableTourneys.size(); tourney++) {
            final int torneyID = tourney;
            menu.findItem(R.id.nav_dynamic_tourney)
                    .getSubMenu()
                    .add(Menu.NONE, torneyID, Menu.NONE, AppConstant.availableTourneys.get(torneyID))
                    .setIcon(R.drawable.ic_soccer_ball)
                    .setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            item.setChecked(true);
                            int id = item.getItemId();

                            if (id != torneyID) {
                                nav.setCheckedItem(torneyID);

                                Intent intent = DrawerSelector.onItemSelected(thisActivity, id);

                                if (intent != null) {

                                    startActivity(intent);
                                    finish();
                                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                                }
                            }
                            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                            drawer.closeDrawer(GravityCompat.START);

                            return false;
                        }
                    });
        }
    }

    public List<String> setLeagueDivisions() {
        List<String> myLeagueDiv = new ArrayList<>(6);
        myLeagueDiv.add("Partidos");
        myLeagueDiv.add("Noticias");
        myLeagueDiv.add("Lideres");

        return myLeagueDiv;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            startActivity(getParentActivityIntent());
            overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
            finish();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_matches_calendar, menu);
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

        if (id != 1) {

            Intent intent = DrawerSelector.onItemSelected(this, id);

            if (intent != null) {
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentInteraction() {

    }

    /**
     * A placeholder fragment containing a simple view.
     * <p>
     * <p>
     * /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class NewsPagerAdapter extends FragmentPagerAdapter {

        private Map<String, FragmentNewsFeed> mPageReferenceMap = new HashMap<>();

        public NewsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public FragmentNewsFeed getFragment(String key) {

            return mPageReferenceMap.get(key);
        }


        public void clearAll() //Clear all page
        {
            FragmentManager fm = getSupportFragmentManager();
            for (int i = 0; i < mPageReferenceMap.size(); i++)
                fm.beginTransaction().remove(getFragmentForPosition(i)).commit();
        }

        private String makeFragmentName(int viewId, int index) {
            return "android:switcher:" + viewId + ":" + index;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            String tag = makeFragmentName(mViewPager.getId(), (int) getItemId(position));
            FragmentNewsFeed fragmentNewsFeed = FragmentNewsFeed.newInstance();

            mPageReferenceMap.put(tag, fragmentNewsFeed);

            return fragmentNewsFeed;

        }

        public
        @Nullable
        Fragment getFragmentForPosition(int position) {
            String tag = makeFragmentName(mViewPager.getId(), (int) getItemId(position));
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
            return fragment;
        }


        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getCount() {
            // Show 1 total pages.
            return 1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return "noticias".toUpperCase(l);
            }
            return null;
        }
    }

    public class LeadersPagerAdapter extends FragmentPagerAdapter {

        private Map<String, FragmentMatches> mPageReferenceMap = new HashMap<>();

        public LeadersPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public FragmentMatches getFragment(String key) {

            return mPageReferenceMap.get(key);
        }

        private String makeFragmentName(int viewId, int index) {
            return "android:switcher:" + viewId + ":" + index;
        }

        public void clearAll() //Clear all page
        {
            FragmentManager fm = getSupportFragmentManager();
            for (int i = 0; i < mPageReferenceMap.size(); i++)
                fm.beginTransaction().remove(getFragmentForPosition(i)).commit();
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            String tag = makeFragmentName(mViewPager.getId(), (int) getItemId(position));
            FragmentMatches fragmentMatches = FragmentMatches.newInstance(position + 1);

            mPageReferenceMap.put(tag, fragmentMatches);

            return fragmentMatches;

        }

        public
        @Nullable
        Fragment getFragmentForPosition(int position) {
            String tag = makeFragmentName(mViewPager.getId(), (int) getItemId(position));
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
            return fragment;
        }


        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getCount() {
            // Show 1 total pages.
            return 1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return "Lideres".toUpperCase(l);
            }
            return null;
        }
    }

    public class TourneyCalendarPagerAdapter extends FragmentPagerAdapter {

        private Map<String, FragmentMatches> mPageReferenceMap = new HashMap<>();

        public TourneyCalendarPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public FragmentMatches getFragment(String key) {

            return mPageReferenceMap.get(key);
        }

        public void clearAll() //Clear all page
        {
            FragmentManager fm = getSupportFragmentManager();
            for (int i = 0; i < mPageReferenceMap.size(); i++)
                fm.beginTransaction().remove(getFragmentForPosition(i)).commit();
        }

        private String makeFragmentName(int viewId, int index) {
            return "android:switcher:" + viewId + ":" + index;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            String tag = makeFragmentName(mViewPager.getId(), (int) getItemId(position));
            FragmentMatches fragmentMatches = FragmentMatches.newInstance(position + 1);

            mPageReferenceMap.put(tag, fragmentMatches);

            return fragmentMatches;

        }

        public
        @Nullable
        Fragment getFragmentForPosition(int position) {
            String tag = makeFragmentName(mViewPager.getId(), (int) getItemId(position));
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(tag);
            return fragment;
        }


        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getCount() {
            // Show 10 total pages.
            return 14;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
                case 3:
                    return getString(R.string.title_section4).toUpperCase(l);
                case 4:
                    return getString(R.string.title_section5).toUpperCase(l);
                case 5:
                    return getString(R.string.title_section6).toUpperCase(l);
                case 6:
                    return getString(R.string.title_section7).toUpperCase(l);
                case 7:
                    return getString(R.string.title_section8).toUpperCase(l);
                case 8:
                    return getString(R.string.title_section9).toUpperCase(l);
                case 9:
                    return getString(R.string.title_section10).toUpperCase(l);
                case 10:
                    return getString(R.string.title_section11).toUpperCase(l);
                case 11:
                    return getString(R.string.title_section112).toUpperCase(l);
                case 12:
                    return "Noticias";
                case 13:
                    return "Lideres";
            }
            return null;
        }
    }


}
