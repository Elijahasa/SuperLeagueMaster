package ntv.upgrade.superleaguemaster;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ntv.upgrade.superleaguemaster.Adapters.NewsFeedAdapter;
import ntv.upgrade.superleaguemaster.Adapters.PlayersAdapter;
import ntv.upgrade.superleaguemaster.AppConstants.Constants;
import ntv.upgrade.superleaguemaster.Decorators.DividerItemDecoration;
import ntv.upgrade.superleaguemaster.Drawer.DrawerSelector;
import ntv.upgrade.superleaguemaster.NewsFeed.NewsFeedItem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class FragmentNewsFeed extends Fragment {

    private List<NewsFeedItem> newsFeedItems = new ArrayList<>();
    // TODO: Customize parameter argument names
    private static final String NEWS_ID = "news-id";
    private static int mNewsID;
    private NewsFeedAdapter newsFeedAdapter;
   private Context mContext;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FragmentNewsFeed() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static FragmentNewsFeed newInstance() {
        FragmentNewsFeed fragment = new FragmentNewsFeed();
       // Bundle args = new Bundle();
       // args.putInt(NEWS_ID, newId);
       // fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mNewsID = getArguments().getInt(NEWS_ID);
        }


    }
    public void populateDummyNewsFeedItems(){
        newsFeedItems.add(new NewsFeedItem(R.drawable.bg_upgrade, "Domingo 27 abril será la Convivencia Fútbolera de Garrincha FC"));
        newsFeedItems.add(new NewsFeedItem(R.drawable.bg_upgrade, "Garrincha FC logra primer Lugar en Copa Pempén de Media Cancha."));
        newsFeedItems.add(new NewsFeedItem(R.drawable.bg_upgrade, "Garrincha FC defenderá título de Copa Regional en El Seibo"));
        newsFeedItems.add(new NewsFeedItem(R.drawable.bg_upgrade, "Domingo 27 abril será la Convivencia Fútbolera de Garrincha FC"));
        newsFeedItems.add(new NewsFeedItem(R.drawable.bg_upgrade, "Garrincha FC logra primer Lugar en Copa Pempén de Media Cancha."));
        newsFeedItems.add(new NewsFeedItem(R.drawable.bg_upgrade, "Garrincha FC defenderá título de Copa Regional en El Seibo"));
        newsFeedItems.add(new NewsFeedItem(R.drawable.bg_upgrade, "Domingo 27 abril será la Convivencia Fútbolera de Garrincha FC"));
        newsFeedItems.add(new NewsFeedItem(R.drawable.bg_upgrade, "Garrincha FC logra primer Lugar en Copa Pempén de Media Cancha."));
        newsFeedItems.add(new NewsFeedItem(R.drawable.bg_upgrade, "Upgrade, We Create"));

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newsfeed, container, false);

        populateDummyNewsFeedItems();
        newsFeedAdapter = new NewsFeedAdapter(newsFeedItems);


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(newsFeedAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), null));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addOnItemTouchListener(new RecyclerItemClickLister(ActivityTourneyCalendar.getReference()
                , recyclerView, new RecyclerItemClickLister.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Intent intent = DrawerSelector.onItemSelected(ActivityTourneyCalendar.getReference(),
                        Constants.NEWS_FEED_DETAILS_ACTIVITY);
                //intent.putExtra("CLUBID", position);

                if (intent != null) {
                    startActivity(intent);

                }
            }

            @Override
            public void onItemLongClick(View view, int position) {
                // ...
            }
        }));

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction();
    }
}
