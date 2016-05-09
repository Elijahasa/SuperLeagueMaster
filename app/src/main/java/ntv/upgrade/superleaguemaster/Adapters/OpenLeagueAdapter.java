package ntv.upgrade.superleaguemaster.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ntv.upgrade.superleaguemaster.R;


import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersAdapter;


/**
 * Created by jfrom on 3/22/2016.
 */
public class OpenLeagueAdapter extends StickyListAdapter<OpenLeagueAdapter.OpenItemHolder>
        implements StickyRecyclerHeadersAdapter<OpenLeagueAdapter.OpenItemHolder>{

    Context mContext;


    public OpenLeagueAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public long getHeaderId(int position) {
        if (position == 0) {
            return -1;
        } else {
            return getItem(position).getEvent_id() ;
        }
    }

    @Override
    public OpenLeagueAdapter.OpenItemHolder onCreateHeaderViewHolder(ViewGroup parent) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_open_league_header, parent, false);
        return new OpenItemHolder(v, true);
    }

    @Override
    public void onBindHeaderViewHolder(OpenItemHolder holder, int position) {
        holder.vEventIcon.setImageResource(getItem(position).getEventTypeImage());
        holder.vEventTitle.setText(getItem(position).getEventTitle());

    }



    @Override
    public OpenItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_open_league, parent, false);
        // set the view's size, margins, paddings and app_bar_teams parameters
        return new OpenItemHolder(v, false);
    }

    @Override
    public void onBindViewHolder(OpenLeagueAdapter.OpenItemHolder holder, int position) {
        // - get element from your dataset at this vTeamPosition
        // - replace the contents of the view with that element
        holder.vEventDate.setText(getItem(position).getEventDate());
        holder.vEventLocation.setText(getItem(position).getEventLocation());
        holder.vEventDescription.setText(getItem(position).getEventDescription());
        holder.vEventPrice.setText(getItem(position).getEventPrice());
      //  holder.Id.setText(mOpenEventsList.get(position).getEventPrice());

    }
    // Provides a reference to the views for each data item

    public static class OpenItemHolder extends RecyclerView.ViewHolder {

        //Headers
        ImageView vEventIcon;
        TextView vEventTitle;

        //Rows

        TextView vEventDate;
        TextView vEventLocation;
        TextView vEventDescription;
        TextView vEventPrice;
        TextView  Id;

        /**
         *
         * @param v View for the viewholder
         * @param type false= row element, true= header element
         */

        public OpenItemHolder(View v, boolean type) {
            super(v);
            if(type){
                vEventIcon = (ImageView) v.findViewById(R.id.open_league_header_img);
                vEventTitle = (TextView) v.findViewById(R.id.open_league_header_event_tittle);

            }else {
                vEventDate = (TextView) v.findViewById(R.id.open_league_event_date);
                vEventLocation = (TextView) v.findViewById(R.id.open_league_location);
                vEventDescription = (TextView) v.findViewById(R.id.open_league_description_txt);
                vEventPrice = (TextView) v.findViewById(R.id.open_league_price_txt);
            }
        }


    }
}
