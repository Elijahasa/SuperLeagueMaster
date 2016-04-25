package ntv.upgrade.superleaguemaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import ntv.upgrade.superleaguemaster.AppConstants.AppConstant;
import ntv.upgrade.superleaguemaster.Drawer.DrawerSelector;


public class ActivityAbout extends AppCompatActivity implements CollapsingToolbarLayout.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private Activity thisActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        thisActivity = this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fabContactUs = (FloatingActionButton) findViewById(R.id.fab_contact_us);
        fabContactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("message/rfc822");
                intent.putExtra(Intent.EXTRA_EMAIL, R.string.dev_email);
                intent.putExtra(Intent.EXTRA_SUBJECT, R.string.email_subject);
                intent.putExtra(Intent.EXTRA_TEXT, R.string.email_body);
                startActivity(Intent.createChooser(intent, "Send Email"));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_about);

        createDynamicTournamentMenu(navigationView);
    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            startActivity(getParentActivityIntent());
          //  overridePendingTransition(R.anim.animation_enter, R.anim.animation_leave);

        }
    }

    @Override
    public void onClick(View v) {
        // onBackPressed();
    }

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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id != R.id.nav_about) {

            Intent intent = DrawerSelector.onItemSelected(this, id);

            if (intent != null) {
                startActivity(intent);
               // overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
