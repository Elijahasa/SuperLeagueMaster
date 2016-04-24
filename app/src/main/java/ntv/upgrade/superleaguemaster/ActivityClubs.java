package ntv.upgrade.superleaguemaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
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
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import ntv.upgrade.superleaguemaster.Adapters.TeamsToolBarSpinnerAdapter;
import ntv.upgrade.superleaguemaster.AppConstants.AppConstant;
import ntv.upgrade.superleaguemaster.Drawer.DrawerSelector;
import ntv.upgrade.superleaguemaster.Schedule.Team;

public class ActivityClubs extends AppCompatActivity implements CollapsingToolbarLayout.OnClickListener, NavigationView.OnNavigationItemSelectedListener, AppBarLayout.OnOffsetChangedListener,
        FragmentPlayers.OnListFragmentInteractionListener, FragmentHistory.OnListFragmentInteractionListener {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private DrawerLayout drawer;
    private CollapsingToolbarLayout collapsingToolbar;
    private SlidingUpPanelLayout slidingUpPanelLayout;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private static final float PERCENTAGE_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;
    private static final float PERCENTAGE_TO_HIDE_TITLE_DETAILS = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION = 200;

    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheTitleContainerVisible = true;

    private LinearLayout mTitleContainer;
    private TextView mTitle;
    private AppBarLayout mAppBarLayout;
    private Toolbar mToolbar;
    private ViewPager mViewPager;

    private Activity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clubs);
        this.thisActivity = this;
        int _teamID = (int) getIntent().getExtras().get("CLUBID");
        Team selectedTeam = new Team(_teamID);

        bindActivity();
        //  setupCollapsingToolbar();
        //  mAppBarLayout.addOnOffsetChangedListener(this);
        slidingUpPanelLayout = (SlidingUpPanelLayout) findViewById(R.id.sliding_layout);
        mToolbar.inflateMenu(R.menu.main);
        // startAlphaAnimation(mTitle, 0, View.INVISIBLE);
        View spinnerContainer = LayoutInflater.from(this).inflate(R.layout.toolbar_spinner,
                mToolbar, false);
        ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mToolbar.addView(spinnerContainer, lp);

        TeamsToolBarSpinnerAdapter spinnerAdapter = new TeamsToolBarSpinnerAdapter(this, selectedTeam);
        spinnerAdapter.addItems(setLeagueDivisions());
        Spinner spinner = (Spinner) findViewById(R.id.toolbar_spinner);
        spinner.setAdapter(spinnerAdapter);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_clubs);
        createDynamicTournamentMenu(navigationView);

        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //actionBar.setSelectedNavigationItem(position);
            }
        });


        slidingUpPanelLayout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                if (slideOffset == 0.0) {
                    slidingUpPanelLayout.setPanelHeight(0);
             //       findViewById(R.id.dragView).setVisibility(View.GONE);
                }
            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
            /*    if (newState == SlidingUpPanelLayout.PanelState.COLLAPSED) {
                    findViewById(R.id.dragView).setVisibility(View.GONE);
                }*/

                Log.i("ActivityClubs", "onPanelStateChanged " + newState);

            }
        });
        slidingUpPanelLayout.setFadeOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
            }
        });


/*
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


    public void onClickedFragmentPlayer() {
      /*  int slideablePanelHeight = 200;
        int animationDuration = 200;
        SlidingUpPanelResizeAnimation animation = new SlidingUpPanelResizeAnimation(slidingUpPanelLayout, slideablePanelHeight, animationDuration);
*/
        slidingUpPanelLayout.setAnchorPoint(0.7f);
        findViewById(R.id.dragView).setVisibility(View.VISIBLE);

        // slidingUpPanelLayout.startAnimation(animation);
        slidingUpPanelLayout.setPanelState(SlidingUpPanelLayout.PanelState.ANCHORED);


    }

    public class SlidingUpPanelResizeAnimation extends Animation {

        private SlidingUpPanelLayout mLayout;

        private float mTo;
        private float mFrom = 0;

        public SlidingUpPanelResizeAnimation(SlidingUpPanelLayout layout, float to, int duration) {
            mLayout = layout;
            mTo = to;

            setDuration(duration);
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            float dimension = (mTo - mFrom) * interpolatedTime + mFrom;

            mLayout.setPanelHeight((int) dimension);
            mLayout.requestLayout();
        }
    }

    public void createDynamicTournamentMenu(NavigationView navigationView) {
        final Menu menu = navigationView.getMenu();
        final NavigationView nav = navigationView;
        //adds all the available tourneys to the navigation Torneos Group
        //TODO:replace the appconstans for the localDB
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
        myLeagueDiv.add("Primera");
        myLeagueDiv.add("Segunda");
        myLeagueDiv.add("Tercera");
        myLeagueDiv.add("Social");
        myLeagueDiv.add("Femenino");
        myLeagueDiv.add("Juvenil");

        return myLeagueDiv;
    }

    private void bindActivity() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //   mTitle = (TextView) findViewById(R.id.main_textview_title);
        //  mTitleContainer = (LinearLayout) findViewById(R.id.main_linearlayout_title);
        //    mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar);
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
           /* startActivity(getParentActivityIntent());
          overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);*/
            super.onBackPressed();
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

        if (id != R.id.nav_clubs) {

            Intent intent = DrawerSelector.onItemSelected(this, id);

            if (intent != null) {
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

    }


    @Override
    public void onListFragmentInteraction() {
        Log.i("ActivityClubs", "onPanelStateChanged " + " FRAGMENTCLICk! ");
        onClickedFragmentPlayer();


    }

    /**
     * A placeholder fragment containing a simple view.
     * <p>
     * <p>
     * /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private Map<String, String> mPageReferenceMap = new HashMap<>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public String getFragment(String key) {

            return mPageReferenceMap.get(key);
        }

        private String makeFragmentName(int viewId, int index) {
            return "android:switcher:" + viewId + ":" + index;
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            String tag = makeFragmentName(mViewPager.getId(), (int) getItemId(position));


            if (position == 0) {

                FragmentTeamInfo fragmentMatches = FragmentTeamInfo.newInstance(position + 1);
                mPageReferenceMap.put("1", tag);
                return fragmentMatches;

            } else if (position == 1) {

                FragmentPlayers fragmentPlayers = FragmentPlayers.newInstance(position + 1);
                mPageReferenceMap.put("2", tag);
                return fragmentPlayers;


            } else {
                FragmentHistory fragmentHistory = FragmentHistory.newInstance(position + 1);
                mPageReferenceMap.put("3", tag);
                return fragmentHistory;

            }


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
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return "INFO";
                case 1:
                    return "JUGADORES";
                case 2:
                    return "historial".toUpperCase(l);


            }
            return null;
        }
    }

    @Override
    public void onClick(View v) {
        onBackPressed();
    }
}
