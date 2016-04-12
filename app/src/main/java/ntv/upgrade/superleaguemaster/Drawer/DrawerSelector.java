package ntv.upgrade.superleaguemaster.Drawer;

import android.app.Activity;
import android.content.Intent;

import ntv.upgrade.superleaguemaster.ActivityAbout;
import ntv.upgrade.superleaguemaster.ActivityClubSelect;
import ntv.upgrade.superleaguemaster.ActivityClubs;
import ntv.upgrade.superleaguemaster.ActivityMain;
import ntv.upgrade.superleaguemaster.ActivityTourneyCalendar;
import ntv.upgrade.superleaguemaster.ActivityTour;
import ntv.upgrade.superleaguemaster.AppConstants.Constants;
import ntv.upgrade.superleaguemaster.R;

/**
 * Created by xeros on 10/19/2015.
 */
public class DrawerSelector {

    private static int selectedItem;

    public static Intent onItemSelected(Activity callingActivity, int id) {

        selectedItem = id;

        Intent intent = null;

        switch (selectedItem) {
            case R.id.nav_main:
                intent = new Intent(callingActivity, ActivityMain.class);
                break;
            /*case R.id.nav_matches:
                intent = new Intent(callingActivity, ActivityTourneyCalendar.class);
                break;*/
            case R.id.nav_location:
                intent = new Intent(callingActivity, ActivityTour.class);
                break;
            case R.id.nav_clubs:
                //TODO: add history activity
                intent = new Intent(callingActivity, ActivityClubSelect.class);

                break;
            // 100 = ActivityClubs
            case Constants.CLUBS_ACTIVITY_BY_TEAM:
                intent = new Intent(callingActivity, ActivityClubs.class);

                break;

           /* case R.id.nav_history:
                //TODO: add history activity
                intent = new Intent(callingActivity, ActivityClubs.class);
                break;*/
          /*  case R.id.nav_settings:
                intent = new Intent(callingActivity, ActivitySettings.class);
                break;*/
           case R.id.nav_about:
                intent = new Intent(callingActivity, ActivityAbout.class);
                break;

            // 0 = ActivityTourneyCalendar
            case Constants.TOURNAMENT_ACTIVITY:
                intent = new Intent(callingActivity, ActivityTourneyCalendar.class);
                break;
            case 1:
                intent = new Intent(callingActivity, ActivityTourneyCalendar.class);
                break;
            case 2:
                intent = new Intent(callingActivity, ActivityTourneyCalendar.class);
                break;

            default:
        }
        return intent;
    }
}
