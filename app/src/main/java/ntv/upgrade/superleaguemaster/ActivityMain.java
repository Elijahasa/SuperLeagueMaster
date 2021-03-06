package ntv.upgrade.superleaguemaster;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import ntv.upgrade.superleaguemaster.Attraction.Area;
import ntv.upgrade.superleaguemaster.Attraction.Attraction;
import ntv.upgrade.superleaguemaster.Drawer.DrawerSelector;
import ntv.upgrade.superleaguemaster.NewsFeed.NewsFeedAdapter;
import ntv.upgrade.superleaguemaster.NewsFeed.NewsFeedItem;
import ntv.upgrade.superleaguemaster.Utils.JsonReader;
import ntv.upgrade.superleaguemaster.Utils.JsonWriter;
import ntv.upgrade.superleaguemaster.Utils.Permissions;
import ntv.upgrade.superleaguemaster.service.UtilityService;


public class ActivityMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ActivityCompat.OnRequestPermissionsResultCallback {


    public static final int PERMISSION_REQUEST_INTERNET = 1;
    public static final int PERMISSION_REQUEST_FINE_LOCATION = 2;

    public static final float TRIGGER_RADIUS = 4000; // 50m
    private static final int TRIGGER_TRANSITION = Geofence.GEOFENCE_TRANSITION_ENTER |
            Geofence.GEOFENCE_TRANSITION_EXIT;
    private static final long EXPIRATION_DURATION = Geofence.NEVER_EXPIRE;

    // List of sites
    public static ArrayList<Area> mAreasArrayList = new ArrayList<>();
    public static ArrayList<Attraction> mAttractionsArrayList = new ArrayList<>();


    // name of the file to preserve areas
    private final String AREAS_DATA_FILE_NAME = "areas_data";
    private DrawerLayout drawer;
    private List<NewsFeedItem> newsFeedItems = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();

        try {
            InputStream in = openFileInput(AREAS_DATA_FILE_NAME);
            JsonReader reader = new JsonReader();
            mAreasArrayList = reader.readJsonStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onStop() {
        try {
            OutputStream out = openFileOutput(AREAS_DATA_FILE_NAME, Context.MODE_PRIVATE);
            JsonWriter writer = new JsonWriter();
            writer.writeJsonStream(out, mAreasArrayList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onStop();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_main);


        Area a1= new Area(1,"Garrincha FC",new LatLng(18.437013, -69.964647),1);
        mAreasArrayList.add(a1);

        LatLng myTestLatLong  = new LatLng(18.467425, -69.915474);
       Attraction a2= new Attraction(1, "Garrincha FC", myTestLatLong, "Nace con el espíritu de nunca rendirse, de estar siempre juntos y de lograr metas mediante el trabajo duro y el amor por el deporte.", "Nuestro nombre es el primer paso de nuestra originalidad, y en el nombre basamos todas nuestras acciones, líneas a seguir, método de trabajo y mercadeo de imagen. Garrincha fue uno de los mejores jugadores que ha tenido nuestro deporte, brasileño, nació con problemas físicos graves en una de las favelas mas pobres de Brasil. Era imposible apostar por este sujeto, sin embargo Garrincha es para los brasileños el jugador que mas Alegría le dio al Pueblo, ganando dos Copas Mundiales y siendo el mejor jugador de su posición y del Campeonato. ",
               ContextCompat.getDrawable(this, R.drawable.garrincha_attraction),
        1, "8:00am - 10:00pm", "Colegio Dominicano de la Salle, Av Simón Bolívar 807, Santo Domingo 10106", "Garrincha F.C nosotros nunca ponemos excusas.");
        mAttractionsArrayList.add(a2);

        if (!Permissions.checkInternetPermission(this)) {
            // See if user has denied permission in the past
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.INTERNET)) {
                // Show a simple snackbar explaining the request instead
                showPermissionSnackbar(PERMISSION_REQUEST_INTERNET);
            } else {
                // Otherwise request permission from user
                if (savedInstanceState == null) {
                    requestInternetPermission();
                }
            }
        } else {
            // Otherwise permission is granted (which is always the case on pre-M devices)
            onInternetPermissionGranted();
        }


        newsFeedItems.add(new NewsFeedItem(R.drawable.garrincha_newsfeed5, "Domingo 27 abril será la Convivencia Fútbolera de Garrincha FC"));
        newsFeedItems.add(new NewsFeedItem(R.drawable.garrincha_newsfeed2, "Garrincha FC logra primer Lugar en Copa \"Pempén\" de Media Cancha."));
        newsFeedItems.add(new NewsFeedItem(R.drawable.garrincha_newsfeed3, "Garrincha FC defenderá título de Copa Regional en El Seibo"));
        newsFeedItems.add(new NewsFeedItem(R.drawable.bg_upgrade,"Upgrade, we Create"));

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.main_newsfeed_cardList);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        NewsFeedAdapter newsFeedAdapter = new NewsFeedAdapter(newsFeedItems);
        recyclerView.setAdapter(newsFeedAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }

    /*********************************************************************************************
     * Permission Requests
     *********************************************************************************************/

    // Request the internet permission from the user
    private void requestInternetPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.INTERNET}, PERMISSION_REQUEST_INTERNET);
    }

    // Request the fine location permission from the user
    private void requestFineLocationPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_REQUEST_FINE_LOCATION);
    }

    /*********************************************************************************************
     * Permissions Request result callback
     *********************************************************************************************/
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_INTERNET:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onInternetPermissionGranted();
                }
                break;
            case PERMISSION_REQUEST_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    onFineLocationPermissionGranted();
                }
                break;
        }
    }

    /*********************************************************************************************
     * onPermissions granted methods
     *********************************************************************************************/
    // Run when fine location permission has been granted
    private void onInternetPermissionGranted() {


        // Check fine location permission has been granted
        if (!Permissions.checkFineLocationPermission(this)) {
            // See if user has denied permission in the past
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                // Show a simple snackbar explaining the request instead
                showPermissionSnackbar(PERMISSION_REQUEST_FINE_LOCATION);
            } else {
                requestFineLocationPermission();
            }
        } else {
            // Otherwise permission is granted (which is always the case on pre-M devices)
            onFineLocationPermissionGranted();
        }

    }

    //Run when fine location permission has been granted
    private void onFineLocationPermissionGranted() {
        UtilityService.requestLocation(this);
    }

    /**
     * Show a permission explanation snackbar
     */
    private void showPermissionSnackbar(final int permission) {
        Snackbar.make(
                findViewById(R.id.main_content), R.string.permission_explanation, Snackbar.LENGTH_LONG)
                .setAction(R.string.permission_explanation_action, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (permission) {
                            case PERMISSION_REQUEST_INTERNET:
                                requestInternetPermission();
                                break;
                            case PERMISSION_REQUEST_FINE_LOCATION:
                                requestFineLocationPermission();
                        }
                    }
                }).show();
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
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

    public static List<Geofence> getGeofenceList() {

        List<Geofence> geofenceList = new ArrayList<>();

      for (Area area : mAreasArrayList) {

            geofenceList.add(new Geofence.Builder()
                    .setCircularRegion(area.getGeo().latitude, area.getGeo().longitude, TRIGGER_RADIUS)
                    .setRequestId(String.format("%d", area.getId()))
                    .setTransitionTypes(TRIGGER_TRANSITION)
                    .setExpirationDuration(EXPIRATION_DURATION)
                    .build());
            Log.i("Geofence List", String.format("Added area %d - ",area.getId()) + area.getName());
        }
        return geofenceList;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id != R.id.nav_main) {

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
}
