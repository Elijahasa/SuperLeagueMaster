package ntv.upgrade.superleaguemaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ntv.upgrade.superleaguemaster.Adapters.ClubsAdapter;
import ntv.upgrade.superleaguemaster.AppConstants.AppConstant;
import ntv.upgrade.superleaguemaster.Decorators.DividerItemDecoration;
import ntv.upgrade.superleaguemaster.Drawer.DrawerSelector;
import ntv.upgrade.superleaguemaster.Schedule.Team;


public class ActivityClubSelect extends AppCompatActivity {

    // name of the file to preserve areas
    private Activity thisActivity;
    private List<Team> clubItems = new ArrayList<>();
    private ClubsAdapter clubsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.thisActivity = this;
        populateDummyClubsItems();
        setContentView(R.layout.activity_clubs_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.clubs_cardList);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        clubsAdapter = new ClubsAdapter(clubItems, this);
        recyclerView.setAdapter(clubsAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, null));

        recyclerView.addOnItemTouchListener(new RecyclerItemClickLister(this, recyclerView, new RecyclerItemClickLister.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                clubsAdapter.getClubID(position);

                Intent intent = DrawerSelector.onItemSelected(thisActivity, 100);
                intent.putExtra("CLUBID", position);

                if (intent != null) {
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                // ...
            }
        }));




    }

    //dummy data for the global news feed
    public void populateDummyClubsItems() {
        for (int i = 0; i < AppConstant.mTeamArrayList.length; i++) {
            Team myTeam = new Team(i);
            clubItems.add(myTeam);
        }
    }

    public void onClickedTeam(View v) {


        Intent intent = DrawerSelector.onItemSelected(this, 100);
      //  intent.putExtra("ID", );

        if (intent != null) {
            startActivity(intent);
            finish();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
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
      /*  int id = item.getItemId();
        Intent intent;
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            intent = new Intent(this, ActivitySettings.class);
            startActivity(intent);
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }


}
