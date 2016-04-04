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
public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ScheduleHolder> {

    WeeklySchedule mWeeklySchedule;
    Context mContext;
    LayoutInflater inflater;
    int mWeeklyScheduleID;


    public HistoryAdapter(int weeklySchedule, Context context) {

        this.mWeeklyScheduleID = weeklySchedule;
        this.mWeeklySchedule = new WeeklySchedule(weeklySchedule);
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);

    }

    private List<ScheduleHolder> MatchItems;

    public HistoryAdapter(List<ScheduleHolder> MatchItems) {
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
                .inflate(R.layout.history_match_row, parent, false);
        // set the view's size, margins, paddings and app_bar_teams parameters

        return new ScheduleHolder(v);
    }

    @Override
    public void onBindViewHolder(ScheduleHolder holder, int position) {
        // - get element from your dataset at this vPlayerPosition
        // - replace the contents of the view with that element

        holder.teamName1.setText(mWeeklySchedule.getmWeeklyMatch().get(position).getTeamName(1));
        holder.teamName2.setText(mWeeklySchedule.getmWeeklyMatch().get(position).getTeamName(2));
        holder.teamLogo1.setImageResource(mWeeklySchedule.getmWeeklyMatch().get(position).getTeamImage(1));
        holder.teamLogo2.setImageResource(mWeeklySchedule.getmWeeklyMatch().get(position).getTeamImage(2));

        holder.scoreTeam1.setText(String.valueOf(5 + (int) (Math.random() * ((5) + 1))));
        holder.scoreTeam2.setText(String.valueOf(5 + (int) (Math.random() * ((5) + 1))));


    }

// Provides a reference to the views for each data item


    public static class ScheduleHolder extends RecyclerView.ViewHolder {
        TextView teamName1;
        TextView teamName2;
        ImageView teamLogo1;
        ImageView teamLogo2;
        TextView scoreTeam1;
        TextView scoreTeam2;

        public ScheduleHolder(View v) {
            super(v);


            teamName1 = (TextView) v.findViewById(R.id.history_team1_name);
            teamName2 = (TextView) v.findViewById(R.id.history_team2_name);
            teamLogo1 = (ImageView) v.findViewById(R.id.history_image_team1);
            teamLogo2 = (ImageView) v.findViewById(R.id.history_image_team2);
            scoreTeam1 = (TextView) v.findViewById(R.id.history_text_score_team1);
            scoreTeam2 = (TextView) v.findViewById(R.id.history_text_score_team2);

        }
    }
}
