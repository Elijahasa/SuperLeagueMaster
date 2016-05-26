package ntv.upgrade.superleaguemaster;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import ntv.upgrade.superleaguemaster.Adapters.ClubsToFollowAdapter;
import ntv.upgrade.superleaguemaster.AppConstants.AppConstant;
import ntv.upgrade.superleaguemaster.Decorators.DividerItemDecoration;
import ntv.upgrade.superleaguemaster.Drawer.DrawerSelector;
import ntv.upgrade.superleaguemaster.Schedule.Team;

public class ActivityFavoriteNFollow extends AppCompatActivity {

    private Activity thisActivity;
    private List<Team> clubItems = new ArrayList<>();
    private ClubsToFollowAdapter clubsAdapter;
    private GridLayoutManager lLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //references to self
        thisActivity=this;
        populateDummyClubsItems();

        setContentView(R.layout.activity_favorite_nfollow);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.fav_n_follow_recuclerview);
        recyclerView.setHasFixedSize(true);

        // LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lLayout = new GridLayoutManager(ActivityFavoriteNFollow.this, 1);
        recyclerView.setLayoutManager(lLayout);

        clubsAdapter = new ClubsToFollowAdapter(clubItems, this);
        recyclerView.setAdapter(clubsAdapter);
       // recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, null));

        recyclerView.addOnItemTouchListener(new RecyclerItemClickLister(this, recyclerView, new RecyclerItemClickLister.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                clubsAdapter.getClubID(position);

                Intent intent = DrawerSelector.onItemSelected(thisActivity, 100);
                intent.putExtra("CLUBID", position);

                ClubsToFollowAdapter.TeamHolder myholder   = (ClubsToFollowAdapter.TeamHolder) clubsAdapter.getItemHolder(position).getTag();


                if( myholder != null){

                    myholder.setCheckBoxState(!myholder.getCheckBox().isChecked());

                }

                if (intent != null) {
                 //   startActivity(intent);
                    // overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                // ...
            }
        }));


    }
 /*   public void onFavTeamButtonClick(View view) {
        clubsAdapter.getClubID(position);

        Intent intent = DrawerSelector.onItemSelected(thisActivity, 100);
        intent.putExtra("CLUBID", position);

        if (intent != null) {
            startActivity(intent);
            // overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        }
    }*/
 //dummy data for the global news feed
 public void populateDummyClubsItems() {
     for (int i = 0; i < AppConstant.mTeamArrayList.length; i++) {
         Team myTeam = new Team(i);
         clubItems.add(myTeam);
     }
 }

}
