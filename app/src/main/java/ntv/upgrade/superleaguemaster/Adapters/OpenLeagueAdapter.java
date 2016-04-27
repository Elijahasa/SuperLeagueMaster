package ntv.upgrade.superleaguemaster.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ntv.upgrade.superleaguemaster.NewsFeed.OpenLeagueItem;
import ntv.upgrade.superleaguemaster.R;


/**
 * Created by jfrom on 3/22/2016.
 */
public class OpenLeagueAdapter extends RecyclerView.Adapter<OpenLeagueAdapter.OpenItemHolder> {

    List<OpenLeagueItem> mOpenEventsList;
    Context mContext;


    public OpenLeagueAdapter(Context context, List items) {
        this.mContext = context;
        this.mOpenEventsList = items;
    }


    @Override
    public int getItemCount() {
        return this.mOpenEventsList.size();
    }


    @Override
    public OpenItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_open_league, parent, false);
        // set the view's size, margins, paddings and app_bar_teams parameters
        return new OpenItemHolder(v);
    }

    @Override
    public void onBindViewHolder(OpenLeagueAdapter.OpenItemHolder holder, int position) {
        // - get element from your dataset at this vTeamPosition
        // - replace the contents of the view with that element
        holder.vEventTypeImage.setImageResource(mOpenEventsList.get(position).getEventTypeImage());
        holder.vEventTitle.setText(mOpenEventsList.get(position).getEventTitle());
        holder.vEventDate.setText(mOpenEventsList.get(position).getEventDate());
        holder.vEventLocation.setText(mOpenEventsList.get(position).getEventLocation());
        holder.vEventDescription.setText(mOpenEventsList.get(position).getEventDescription());
        holder.vEventPrice.setText(mOpenEventsList.get(position).getEventPrice());
        holder.Id = position;

    }
    // Provides a reference to the views for each data item

    public static class OpenItemHolder extends RecyclerView.ViewHolder {


        ImageView vEventTypeImage;
        TextView vEventTitle;
        TextView vEventDate;
        TextView vEventLocation;
        TextView vEventDescription;
        TextView vEventPrice;

        int Id;

        public OpenItemHolder(View v) {
            super(v);
            vEventTypeImage = (ImageView) v.findViewById(R.id.open_league_type_img);
            vEventTitle = (TextView) v.findViewById(R.id.open_league_event_tittle);
            vEventDate = (TextView) v.findViewById(R.id.open_league_event_date);
            vEventLocation = (TextView) v.findViewById(R.id.open_league_location);
            vEventDescription = (TextView) v.findViewById(R.id.open_league_description_txt);
            vEventPrice = (TextView) v.findViewById(R.id.open_league_price_txt);
        }


    }
}
