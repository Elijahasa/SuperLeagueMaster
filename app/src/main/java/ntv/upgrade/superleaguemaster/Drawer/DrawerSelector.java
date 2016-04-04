package ntv.upgrade.superleaguemaster.Drawer;

import android.app.Activity;
import android.content.Intent;

import ntv.upgrade.superleaguemaster.ActivityAbout;
import ntv.upgrade.superleaguemaster.ActivityMain;
import ntv.upgrade.superleaguemaster.ActivityMatchesCalendar;
import ntv.upgrade.superleaguemaster.ActivityTeams;
import ntv.upgrade.superleaguemaster.ActivityTour;
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
            case R.id.nav_matches:
                intent = new Intent(callingActivity, ActivityMatchesCalendar.class);
                break;
            case R.id.nav_location:
                intent = new Intent(callingActivity, ActivityTour.class);
                break;
            case R.id.nav_teams:
                //TODO: add history activity
                intent = new Intent(callingActivity, ActivityTeams.class);

                break;
           /* case R.id.nav_history:
                //TODO: add history activity
                intent = new Intent(callingActivity, ActivityTeams.class);
                break;*/
          /*  case R.id.nav_settings:
                intent = new Intent(callingActivity, ActivitySettings.class);
                break;*/
           case R.id.nav_about:
                intent = new Intent(callingActivity, ActivityAbout.class);
                break;

            default:
        }
        return intent;
    }
}
