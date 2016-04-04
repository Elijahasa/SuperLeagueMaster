package ntv.upgrade.superleaguemaster.HTTPRequest;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ntv.upgrade.superleaguemaster.ActivityMain;
import ntv.upgrade.superleaguemaster.Attraction.Area;
import ntv.upgrade.superleaguemaster.Attraction.Attraction;
import ntv.upgrade.superleaguemaster.service.UtilityService;

/**
 * Created by frO on 11/4/2015.
 */
public class JSONHandler implements RequestQueue.RequestFinishedListener {

    // for log porpuses
    private static final String TAG = JSONHandler.class.getSimpleName();

    // URLs for database requests
    private static final String LA_ZONA_DB_BASE_URL = "http://lazonawebapi.azurewebsites.net/api";
    private static final String LA_ZONA_DB_AREAS_URL = LA_ZONA_DB_BASE_URL + "/areas";
    private static final String LA_ZONA_DB_ATTRACTIONS_URL = LA_ZONA_DB_BASE_URL + "/attractions";
    private static final String LA_ZONA_DB_BEACONS_URL = LA_ZONA_DB_BASE_URL + "/beacons";

    // Request actions
    private static final String ACTION_AREAS_REQUEST = "areas_request";
    private static final String ACTION_ATTRACTIONS_REQUEST = "attractions_request";
    private static final String ACTION_BEACONS_REQUEST = "beacons_request";

    private static Context mContext;
    private static RequestQueue mRequestQueue;
    private int mCurrentRequests = 0;



    public JSONHandler(Context context) {
        mContext = context;   //  context constructor
    }


    /*********************************************************************************************
     * JSON Request QueueHandler Handlers
     *********************************************************************************************/
    public <T> void addToRequestQueue(Request<T> req) {

        getRequestQueue().add(req);
        Log.i(TAG, req.getTag() + " added to queue");
    }

    private RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(mContext);
            mRequestQueue.addRequestFinishedListener(this);
        }
        return mRequestQueue;
    }

    private void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    /*********************************************************************************************
     * JSON Request Functions
     *********************************************************************************************/

    /**
     * Gets list of Areas to create geoFences
     */
    public void getAreasListRequest() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(LA_ZONA_DB_AREAS_URL,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<Area> mAreasList = new ArrayList<>();
                        Area area = null;

                        // catches the response areas list from the DB
                        for (int i = 0; i < response.length(); i++) {

                            try {
                                area = getAreaFromJSONObject(response.getJSONObject(i));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            // adds area to list
                            mAreasList.add(area);
                        }

                        ActivityMain.mAreasArrayList.clear();
                        ActivityMain.mAreasArrayList.addAll(mAreasList);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, error.getMessage());
                Log.i("Volley Error: ", "Couldnt get Areas List.");
               // getAreasListRequest();
            }
        });

        jsonArrayRequest.setTag(ACTION_AREAS_REQUEST);
        addToRequestQueue(jsonArrayRequest);
    }

    /**
     * Gets list of Attractions for an specific Area
     *
     * @param areaId
     */
   /* public void getAttractionsFromAreaRequest(final int areaId) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(LA_ZONA_DB_AREAS_URL + "/" + areaId,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<Attraction> mAttractionsList = new ArrayList<>();
                        Attraction attraction = null;

                        // catches the response attractions list for specified area from the DB
                        for (int i = 0; i < response.length(); i++) {

                            try {
                                attraction = getAttractionFromJSONObject(response.getJSONObject(i));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            mAttractionsList.add(attraction);
                            // mBeaconHandlerMap uses the Major as a KeyValue pair to return the appropriate BeaconHandle Object
                        //    mAttractionHandlerMap.put(attraction.getName(), attraction);
                        }

                        ActivityMain.mAttractionsArrayList.clear();
                        ActivityMain.mAttractionsArrayList.addAll(mAttractionsList);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, error.getMessage());
                Log.i("Volley Error: ", "No attractions on selected area.");
              //  getAttractionsFromAreaRequest(areaId);
            }
        });

        jsonArrayRequest.setTag(ACTION_ATTRACTIONS_REQUEST);
        addToRequestQueue(jsonArrayRequest);
    }*/

  /*  public void getAllAttractionsFromDB() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(LA_ZONA_DB_ATTRACTIONS_URL,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<Attraction> mAttractionsList = new ArrayList<>();
                        Attraction attraction = null;

                        // catches the response attractions list for specified area from the DB
                        for (int i = 0; i < response.length(); i++) {

                            try {
                                attraction = getAttractionFromJSONObject(response.getJSONObject(i));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            mAttractionsList.add(attraction);
                            // mBeaconHandlerMap uses the Major as a KeyValue pair to return the appropriate BeaconHandle Object

                        }

                        ActivityMain.mAttractionsArrayList.clear();
                        ActivityMain.mAttractionsArrayList.addAll(mAttractionsList);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, error.getMessage());
                Log.i("Volley Error: ", "No attractions on selected area.");
            }
        });

        jsonArrayRequest.setTag(ACTION_ATTRACTIONS_REQUEST);
        addToRequestQueue(jsonArrayRequest);
    }*/

    /**
     * Gets list of Beacons for an specific Attraction
     *
     * @param attractionId
     */

    /*********************************************************************************************
     * Get Objects from JSON Request Functions
     *********************************************************************************************/
    // Get AREA
    private static Area getAreaFromJSONObject(JSONObject jsonObject) {

        Area area = null;

        try {
            int Id = jsonObject.getInt("Id");
            String Area_Name = jsonObject.getString("Area_Name");
            double Area_Lat = jsonObject.getDouble("Area_Lat");
            double Area_Lng = jsonObject.getDouble("Area_Lng");
            int Area_Type = jsonObject.getInt("Area_Type");

            area = new Area(Id, Area_Name, new LatLng(Area_Lat, Area_Lng), Area_Type);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return area;
    }

    // Get ATTRACTION
   /* private Attraction getAttractionFromJSONObject(JSONObject jsonObject) {

        Attraction attraction = null;

        try {
            int Id = jsonObject.getInt("Id");
            String Att_Name = jsonObject.getString("Att_Name");
            double Att_Lat = jsonObject.getDouble("Att_Lat");
            double Att_Lng = jsonObject.getDouble("Att_Lng");
            String Att_Short_Desc = jsonObject.getString("Att_Short_Desc");
            String Att_Long_Desc = jsonObject.getString("Att_Long_Desc");
            Drawable Att_Image = jsonObject.getString("Att_Image");
            Uri Att_Image2 = Uri.parse(jsonObject.getString("Att_Image2"));
            int Att_Type = jsonObject.getInt("Att_Type");
            String Att_Schedule = jsonObject.getString("Att_Sched");
            String Att_Address = jsonObject.getString("Att_Address");
            String Att_Notes = jsonObject.getString("Att_Notes");

            attraction = new Attraction(Id, Att_Name, new LatLng(Att_Lat, Att_Lng),
                    Att_Short_Desc, Att_Long_Desc, Att_Image, Att_Image2,
                    Att_Type, Att_Schedule, Att_Address, Att_Notes);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return attraction;
    }*/

    // Get BEACON
   /* private Beacon getBeaconFromJSONObject(JSONObject jsonObject) {

        Beacon beacon = null;

        try {
            int Id = jsonObject.getInt("Id");
            String Beac_Name = jsonObject.getString("Beac_Name");
            Identifier Beac_Namespace = Identifier.parse(jsonObject.getString("Beac_Namespace"));
            Identifier Beac_Instance = Identifier.parse(jsonObject.getString("Beac_Instance"));
            String Beac_Content_Near = jsonObject.getString("Beac_Content_Near");
            String Beac_Content_Near_Txt = jsonObject.getString("Beac_Content_Near_Txt");
            String Beac_Content_Far = jsonObject.getString("Beac_Content_Far");
            String Beac_Content_Far_Txt = jsonObject.getString("Beac_Content_Far_Txt");

            beacon = new Beacon(Id, Beac_Name, Beac_Namespace, Beac_Instance,
                    Beac_Content_Near, Beac_Content_Near_Txt, Beac_Content_Far, Beac_Content_Far_Txt);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return beacon;
    }*/

    /*********************************************************************************************
     * Request Finished Listener
     *********************************************************************************************/
    @Override
    public void onRequestFinished(Request request) {

        String action = request != null ? (String) request.getTag() : null;


        switch (action) {
            case ACTION_AREAS_REQUEST:
                // logs completion
                // adds geofence listener for the fetched areas
                UtilityService.addGeofences(mContext);
                Log.i(ACTION_AREAS_REQUEST, "completed");
                break;

            case ACTION_ATTRACTIONS_REQUEST:
                // logs completion

                mCurrentRequests = ActivityMain.mAttractionsArrayList.size();
                for (Attraction attraction : ActivityMain.mAttractionsArrayList) {
                    Log.i("Attraction : ", attraction.getName());
                }
                Log.i(ACTION_ATTRACTIONS_REQUEST, "completed");
                break;

        }
    }
}
