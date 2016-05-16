package ntv.upgrade.superleaguemaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersTouchListener;

import java.util.ArrayList;
import java.util.List;

import ntv.upgrade.superleaguemaster.Adapters.OpenLeagueAdapter;
import ntv.upgrade.superleaguemaster.AppConstants.AppConstant;
import ntv.upgrade.superleaguemaster.Drawer.DrawerSelector;
import ntv.upgrade.superleaguemaster.NewsFeed.OpenLeagueItem;



public class ActivityOpenLeague extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private DrawerLayout drawer;
    private Activity thisActivity;
    List<OpenLeagueItem> mOpenWeeklyEventsList = new ArrayList<>();
    List<OpenLeagueItem> mOpenRecurrentEventsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.thisActivity=this;
        createDummyEventList();
        setContentView(R.layout.activity_open_league);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        assert navigationView != null;
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_open_league);

        //dynamically adds the tourneys to follow
        createDynamicTournamentMenu(navigationView);

        RecyclerView recyclerViewWeekly = (RecyclerView) findViewById(R.id.open_league_sticky_recycleview);
        assert recyclerViewWeekly != null;
        recyclerViewWeekly.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManagerWeekly = new LinearLayoutManager(this);
        recyclerViewWeekly.setLayoutManager(linearLayoutManagerWeekly);

        OpenLeagueAdapter openLeagueAdapterWeekly = new OpenLeagueAdapter(this);
        openLeagueAdapterWeekly.addAll(mOpenWeeklyEventsList);
        recyclerViewWeekly.setAdapter(openLeagueAdapterWeekly);

        // Add the sticky headers decoration
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(openLeagueAdapterWeekly);
        recyclerViewWeekly.addItemDecoration(headersDecor);

        // Add touch listeners
        StickyRecyclerHeadersTouchListener touchListener =
                new StickyRecyclerHeadersTouchListener(recyclerViewWeekly, headersDecor);
        touchListener.setOnHeaderClickListener(
                new StickyRecyclerHeadersTouchListener.OnHeaderClickListener() {
                    @Override
                    public void onHeaderClick(View header, int position, long headerId) {
//                         Toast.makeText(MainActivity.this, "Header position: " + position + ", id: " + headerId + " probando: " + header.getId(),
//                                 Toast.LENGTH_SHORT).show();

                    }
                });
        recyclerViewWeekly.addOnItemTouchListener(touchListener);

        // recyclerView.setItemAnimator(new DefaultItemAnimator());


       /* recyclerView.addOnItemTouchListener(new RecyclerItemClickLister(this, recyclerView, new RecyclerItemClickLister.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

           *//*     Intent intent = DrawerSelector.onItemSelected(thisActivity, Constants.NEWS_FEED_DETAILS_ACTIVITY);
                //intent.putExtra("CLUBID", position);

                if (intent != null) {
                    startActivity(intent);
                }*//*
            }

            @Override
            public void onItemLongClick(View view, int position) {
                // ...
            }
        }));*/
    }

    public void createDummyEventList(){
        mOpenWeeklyEventsList = new ArrayList<>();

        for (int i = 0; i < 10 ; i++) {
            OpenLeagueItem myItem = new OpenLeagueItem();
            myItem.setEventTitle(AppConstant.mTeamArrayList[i][1] + " Contral el que quiera!");
            myItem.setEventDate("Martes %i de Abril - 5:00pm");
            myItem.setEventLocation("La Media Cancha");
            myItem.setEventDescription("Partido Amistoso");
            myItem.setEventPrice("120"+i+".00 $");

            if (i%2==0) {
                // weekly id = 0
                myItem.setEvent_id(01L);
                myItem.setEventTypeImage(R.drawable.open_league_weekly_icon);

            }else{
                //  recurrent id = 1
                myItem.setEvent_id(02L);
                myItem.setEventTypeImage(R.drawable.open_league_recurrent_icon);

            }
            mOpenWeeklyEventsList.add(myItem);

        }
    }

    /***
     * add the all the tournament available to follow from the DB
     ***/
    public void createDynamicTournamentMenu(NavigationView navigationView) {
        final Menu menu = navigationView.getMenu();
        final NavigationView nav = navigationView;
        //adds all the available tourneys to the navigation Torneos Group
        for (int tourney = 0; tourney  < AppConstant.availableTourneys.size() ; tourney ++ ){
            final int torneyID = tourney ;
            menu.findItem(R.id.nav_dynamic_tourney)
                    .getSubMenu()
                    .add(Menu.NONE, torneyID  , Menu.NONE, AppConstant.availableTourneys.get(torneyID))
                    .setIcon(R.drawable.ic_goals_icon)
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
                            assert drawer != null;
                            drawer.closeDrawer(GravityCompat.START);

                            return false;
                        }
                    });
        }
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        Intent intent;
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            intent = new Intent(this, ActivitySettings.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id != R.id.nav_open_league) {

            Intent intent = DrawerSelector.onItemSelected(this, id);

            if (intent != null) {

                startActivity(intent);
              //  overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        assert drawer != null;
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }




}
