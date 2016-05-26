package ntv.upgrade.superleaguemaster.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ntv.upgrade.superleaguemaster.R;
import ntv.upgrade.superleaguemaster.Schedule.Team;


/**
 * Created by jfrom on 3/22/2016.
 */
public class ClubsToFollowAdapter extends RecyclerView.Adapter<ClubsToFollowAdapter.TeamHolder>{

   private List<Team> mTeamList;
    private Context mContext;
    private LayoutInflater inflater ;
    private List<View> itemHolder = new ArrayList<>();

    public ClubsToFollowAdapter(List<Team> list, Context context) {
        this.mTeamList = list;
        this.mContext = context;

    }


    public View getItemHolder(int pos) {
        return itemHolder.get(pos);
    }

    private void addItemHolder(View itemHolder) {
        this.itemHolder.add(itemHolder);
    }

    public int getClubID(int position){
        return mTeamList.get(position).getmTeamID();
    }

    @Override
    public int getItemCount() {
        return (this.mTeamList.size());
    }



    @Override
    public TeamHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_follow_clubs_list, parent, false);
        // set the view's size, margins, paddings and app_bar_teams parameters
        addItemHolder(v);
        return new TeamHolder(v);
    }

    @Override
    public void onBindViewHolder(ClubsToFollowAdapter.TeamHolder holder, int position) {
        // - get element from your dataset at this vTeamPosition
        // - replace the contents of the view with that element
        holder.vClubName.setText(mTeamList.get(position).getmName());
        holder.vClubAvatar.setImageResource(mTeamList.get(position).getmTeamImage());
        holder.Id = mTeamList.get(position).getmTeamID();
        getItemHolder(position).setTag(holder);


    }
        // Provides a reference to the views for each data item

    public static class TeamHolder extends FavoritesViewHolder{
       private TextView vClubName;
        private  ImageView vClubAvatar;
        private CheckBox cCheckBox;
        private  int Id;

        public TeamHolder(View v ){
            super(v);
            cCheckBox = (CheckBox) v.findViewById(R.id.checkBox);
            vClubName = (TextView) v.findViewById(R.id.club_name_row);
            vClubAvatar = (ImageView) v.findViewById(R.id.club_image_row);
          //  v.setTag();

        }

        public CheckBox getCheckBox() {
            return this.cCheckBox;
        }

        public void setCheckBoxState(boolean set){
            cCheckBox.setChecked(set);
        }
    }
}
