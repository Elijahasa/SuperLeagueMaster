package ntv.upgrade.superleaguemaster.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ntv.upgrade.superleaguemaster.R;
import ntv.upgrade.superleaguemaster.Schedule.Team;

/**
 * Created by jfrom on 4/4/2016.
 */



public class TeamsToolBarSpinnerAdapter extends BaseAdapter {
    private List<String> mItems = new ArrayList<>();
    private LayoutInflater mInfalter;
    private Team team = null;

    public TeamsToolBarSpinnerAdapter(LayoutInflater inflater, Team team){
        this.mInfalter=inflater;
        this.team = team;
    }

    public TeamsToolBarSpinnerAdapter(LayoutInflater inflater){
        this.mInfalter=inflater;

    }

    public void clear() {
        mItems.clear();
    }

    public void addItem(String yourObject) {
        mItems.add(yourObject);
    }

    public void addItems(List<String> yourObjectList) {
        mItems.addAll(yourObjectList);
    }

    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getDropDownView(int position, View view, ViewGroup parent) {
        if (view == null || !view.getTag().toString().equals("DROPDOWN")) {
            view = mInfalter.inflate(R.layout.toolbar_spinner_item_dropdown, parent, false);
            view.setTag("DROPDOWN");
        }

        TextView textView = (TextView) view.findViewById(R.id.spinner_dropdown);
        textView.setText(getTitle(position));

        return view;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null || !view.getTag().toString().equals("NON_DROPDOWN")) {
            view = mInfalter.inflate(R.layout.
                    toolbar_spinner_item_actionbar, parent, false);
            view.setTag("NON_DROPDOWN");
        }
        TextView textView = (TextView) view.findViewById(R.id.spinner_club_name);
        if(team != null) {textView.setText(team.getmName());}
        else{textView.setText("Copa Garrincha");}

        TextView textView1 = (TextView) view.findViewById(R.id.spinner_club_team);
        textView1.setText(getTitle(position));
        return view;
    }

    private String getTitle(int position) {
        return position >= 0 && position < mItems.size() ? mItems.get(position) : "";
    }
}
