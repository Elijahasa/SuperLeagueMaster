package ntv.upgrade.superleaguemaster.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ntv.upgrade.superleaguemaster.R;
import ntv.upgrade.superleaguemaster.Schedule.WeeklySchedule;

/**
 * Created by jfrom on 3/19/2016.
 */
public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.ScheduleHolder>{

    WeeklySchedule mWeeklySchedule;
    Context mContext;
    LayoutInflater inflater ;
    int mWeeklyScheduleID;

    public MatchAdapter(int weeklySchedule, Context context) {

        this.mWeeklyScheduleID = weeklySchedule;
        this.mWeeklySchedule = new WeeklySchedule(weeklySchedule);
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);

    }

private List<ScheduleHolder> MatchItems;

public MatchAdapter(List<ScheduleHolder> MatchItems){
        this.MatchItems = MatchItems;
        }

@Override
public int getItemCount() {
    return (this.mWeeklySchedule.getmWeeklyMatch().size());
        }

@Override
public ScheduleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.match_recycleview_row, parent, false);
        // set the view's size, margins, paddings and app_bar_teams parameters

        return new ScheduleHolder(v);
        }

@Override
public void onBindViewHolder(ScheduleHolder holder, int position) {
        // - get element from your dataset at this vPlayerPosition
        // - replace the contents of the view with that element

    holder.teamName.setText(mWeeklySchedule.getmWeeklyMatch().get(position).getTime());

    //     holder.team2.setText( mTeam.getmWeeklyMatch().get(vPlayerPosition).getTeamName(2));
    holder.teamLogo1.setImageResource(mWeeklySchedule.getmWeeklyMatch().get(position).getTeamImage(1));
    holder.teamLogo2.setImageResource(mWeeklySchedule.getmWeeklyMatch().get(position).getTeamImage(2));
    holder.time.setText(mWeeklySchedule.getmWeeklyMatch().get(position).getDate());

    holder.stadium.setText(mWeeklySchedule.getmWeeklyMatch().get(position).getStadium());

        }

// Provides a reference to the views for each data item


    public static class ScheduleHolder extends RecyclerView.ViewHolder{
        TextView teamName;
        TextView team2;
        ImageView teamLogo1;
        ImageView teamLogo2;
        TextView time;
        TextView stadium;

    public ScheduleHolder(View v){
        super(v);


        teamName = (TextView) v.findViewById(R.id.time1);
       // team2 = (TextView) v.findViewById(R.id.team_2_id);
        teamLogo1= (ImageView) v.findViewById(R.id.image_team1);
        teamLogo2= (ImageView) v.findViewById(R.id.image_team2);
        time = (TextView) v.findViewById(R.id.time);
        stadium = (TextView) v.findViewById(R.id.stadium);

    }
}
}
