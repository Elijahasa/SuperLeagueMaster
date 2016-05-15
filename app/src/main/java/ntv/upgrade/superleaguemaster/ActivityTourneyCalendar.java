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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

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
        FragmentNewsFeed.OnListFragmentInteractionListener, FragmentLeaders.OnListFragmentInteractionListener, FragmentTourneyStats.OnListFragmentInteractionListener {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private TourneyCalendarPagerAdapter mTourneyCalendarPagerAdapter;/*
    private NewsPagerAdapter mNewsFeedPageAdapater;
    private LeadersPagerAdapter mLeaderPageAdapter;*/
    private DrawerLayout drawer;
    private static Activity thisActivity;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    private Spinner spinner;
    private SlidingUpPanelLayout slidingUpPanelLayout;
    private int mLastSpinnerSelectedItem = 10;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    public static Activity getReference() {
        return thisActivity;
    }

    public int getmLastSpinnerSelectedItem() {
        return mLastSpinnerSelectedItem;
    }

    public void setLastSpinnerSelectedItem(int mLastSpinnerSelectedItem) {
        this.mLastSpinnerSelectedItem = mLastSpinnerSelectedItem;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mLastSpinnerSelectedItem = 10;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourney_matches);

        slidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        ActivityTourneyCalendar.thisActivity = this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        View spinnerContainer = LayoutInflater.from(this).inflate(R.layout.toolbar_spinner,
                toolbar, false);
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        toolbar.addView(spinnerContainer, lp);

        TeamsToolBarSpinnerAdapter spinnerAdapter = new TeamsToolBarSpinnerAdapter(this);
        spinnerAdapter.addItems(setTourneySpinner());

        spinner = (Spinner) findViewById(R.id.toolbar_spinner);
        spinner.setAdapter(spinnerAdapter);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        // tabLayout.setupWithViewPager(mViewPager);
        if (!tabLayout.isShown()) {
            tabLayout.setVisibility(View.VISIBLE);
        }
        mTourneyCalendarPagerAdapter = new TourneyCalendarPagerAdapter(getSupportFragmentManager());
        mTourneyCalendarPagerAdapter.setCurrentSpinnerID(0);
        mTourneyCalendarPagerAdapter.setPagerCount(AppConstant.mMatchArrayList.length);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mTourneyCalendarPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(Constants.TOURNAMENT_ACTIVITY);
        //dynamically adds the tourneys to follow
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {/*  actionBar.setSelectedNavigationItem(position);*/}
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                              @Override
                                              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {                // onSpinnerSelectionChangeScreen(position);

                                                  onSpinnerSelecterWorker(position);

                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> parent) {
                                              }
                                          }

        );

        slidingUpPanelLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                if (slideOffset == 0.0) {
                    slidingUpPanelLayout.setAnchorPoint(0.0f);
                    slidingUpPanelLayout.setPanelHeight(0);
                    //       findViewById(R.id.dragView).setVisibility(View.GONE);
                }
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                    slidingUpPanelLayout.setAnchorPoint(0.0f);
                    slidingUpPanelLayout.setPanelHeight(0);

                    Log.i("ActivityClubs", "onPanelStateChanged " + newState.name());

                }
                if (previousState == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                    slidingUpPanelLayout.setAnchorPoint(0.0f);

                }
                Log.i("ActivityClubs", "onPanelStateChanged " + newState);

            }
        });
        slidingUpPanelLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
            }
        });


/* TODO: bind the layout and methods to populate accordingly
        TextView vPlayerName = (TextView) findViewById(R.id.leaders_player_name);;
        CircleImageView vPlayerAvatar ;
        TextView vPlayerNumber;
        TextView vLeaderPosition;
        TextView vPlayerClub;
        int Id;

            vPlayerName = (TextView) findViewById(R.id.leaders_player_name);
            vPlayerAvatar = (CircleImageView) findViewById(R.id.leaders_player_avatar);
            vPlayerNumber = (TextView) findViewById(R.id.leaders_player_number);
            vLeaderPosition = (TextView) findViewById(R.id.leaders_position_text);
            vPlayerClub = (TextView) findViewById(R.id.leaders_player_club);
*/

/*

        if (slidingUpPanelLayout != null) {
            if (slidingUpPanelLayout.getPanelState() != SlidingUpPanelLayout.PanelState.HIDDEN) {
                slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);}
*/


    }

    private void onClickedFragmentLeaders() {
        findViewById(R.id.dragView).setVisibility(View.VISIBLE);
        slidingUpPanelLayout.setAnchorPoint(0.7f);
        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);
    }

    public void onSpinnerSelecterWorker(int position) {
        if (getmLastSpinnerSelectedItem() != position) {

            mTourneyCalendarPagerAdapter.clearAll();
            mTourneyCalendarPagerAdapter.setCurrentSpinnerID(position);
            switch (position) {
                case 0:
                    if (!tabLayout.isShown()) {
                        tabLayout.setVisibility(View.VISIBLE);
                    }

                    mTourneyCalendarPagerAdapter.setPagerCount(AppConstant.mMatchArrayList.length);
                    mTourneyCalendarPagerAdapter.getItem(0);
                    mTourneyCalendarPagerAdapter.notifyDataSetChanged();
                    setLastSpinnerSelectedItem(position);
                    break;
                case 1:
                    if (tabLayout.isShown()) {
                        tabLayout.setVisibility(View.GONE);
                    }
                    mTourneyCalendarPagerAdapter.setPagerCount(1);
                    mTourneyCalendarPagerAdapter.getItem(0);
                    mTourneyCalendarPagerAdapter.notifyDataSetChanged();
                    setLastSpinnerSelectedItem(position);
                    break;

                case 2:
                    if (tabLayout.isShown()) {
                        tabLayout.setVisibility(View.GONE);
                    }
                    mTourneyCalendarPagerAdapter.setPagerCount(1);
                    mTourneyCalendarPagerAdapter.getItem(0);
                    mTourneyCalendarPagerAdapter.notifyDataSetChanged();
                    setLastSpinnerSelectedItem(position);
                    break;

            }
        }
    }
    //catches the extras to set the appropriate display

    public int getSelectedSpinnerItem() {
        int selected = 0;
        List<String> extras = setTourneySpinner();

        for (int i = 0; i < extras.size(); i++) {
            if (getIntent().hasExtra(extras.get(i))) {
                selected = (int) getIntent().getExtras().get(extras.get(i));
            }
        }
        return selected;
    }


    /***
     * add the all the tournament available to follow from the DB
     ***/

    public List<String> setTourneySpinner() {
        List<String> myLeagueDiv = new ArrayList<>(4);
        myLeagueDiv.add("Partidos");
        myLeagueDiv.add("Lideres");
        myLeagueDiv.add("Tabla de Posiciones");

        return myLeagueDiv;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (slidingUpPanelLayout != null &&
                (slidingUpPanelLayout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || slidingUpPanelLayout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED)) {
            slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
        } else {
            super.onBackPressed();
            //startActivity(getParentActivityIntent());
            // overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);
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
        if (id != Constants.TOURNAMENT_ACTIVITY) {
            Intent intent = DrawerSelector.onItemSelected(this, id);
            if (intent != null) {
                startActivity(intent);
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onListFragmentInteraction() {
        onClickedFragmentLeaders();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //       Toast.makeText(ActivityTourneyCalendar.this, "onStart tourAct", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //    Toast.makeText(ActivityTourneyCalendar.this, "onDestroy tourAct", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        //   Toast.makeText(ActivityTourneyCalendar.this, "onRestart tourAct", Toast.LENGTH_SHORT).show();

    }

    /**
     * A placeholder fragment containing a simple view.
     * <p>
     * <p>
     * /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */


    public class TourneyCalendarPagerAdapter extends FragmentPagerAdapter {

        private int currentSpinnerID = 0;
        private int pagerCount = 1;
        private Map<String, Fragment> mPageReferenceMap = new HashMap<>();

        public TourneyCalendarPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void clearAll() //Clear all pages
        {
            if (mPageReferenceMap != null) {
                if (mPageReferenceMap.size() > 0) {
                    FragmentManager fm = getSupportFragmentManager();
                    for (int i = 0; i < mPageReferenceMap.size(); i++)
                        fm.beginTransaction().remove(getFragmentForPosition(i)).commit();
                }
                mPageReferenceMap.clear();
            }
        }

        public int getCurrentSpinnerID() {
            return currentSpinnerID;
        }

        public void setCurrentSpinnerID(int currentSpinnerID) {
            this.currentSpinnerID = currentSpinnerID;
        }

        public int getPagerCount() {
            return pagerCount;
        }

        public void setPagerCount(int pagerCount) {
            this.pagerCount = pagerCount;
        }

        private String makeFragmentName(int viewId, int index) {
            return "android:switcher:" + viewId + ":" + index;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return fragmentType(position);
        }

        /**
         * returns the fragment depending on the spinner selection
         **/
        public Fragment fragmentType(int position) {
            Fragment selectedFragmentType = null;
            String tag = makeFragmentName(mViewPager.getId(), (int) getItemId(position));
            int typeID = getCurrentSpinnerID();
            switch (typeID) {
                case 0:
                    selectedFragmentType = FragmentMatches.newInstance(position + 1);
                    mPageReferenceMap.put(tag, selectedFragmentType);
                    break;

                case 1:
                    selectedFragmentType = FragmentLeaders.newInstance();
                    mPageReferenceMap.put(tag, selectedFragmentType);
                    break;

                case 2:
                    selectedFragmentType = FragmentTourneyStats.newInstance();
                    mPageReferenceMap.put(tag, selectedFragmentType);
                    break;
            }
            return selectedFragmentType;
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
            return getPagerCount();

        }

        @Override
        public int getItemPosition(Object object) {
            // POSITION_NONE makes it possible to reload the PagerAdapter
            return POSITION_NONE;
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


            }
            return null;
        }
    }


}
