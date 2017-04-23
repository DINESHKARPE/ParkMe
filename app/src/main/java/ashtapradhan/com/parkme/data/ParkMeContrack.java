package ashtapradhan.com.parkme.data;

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by TechSutra on 4/23/17.
 */

public class ParkMeContrack {

    public static final String CONTENT_AUTHORITY = "";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PARKING = "parkingdetails";


    public static final class VehicleParkingDetails implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PARKING).build();

        public static final String TABLE_NAME = "parkingdetails";

        public static final String VEHICLE_NUMBER = "vehiclenumber";
        public static final String VEHICLE_TYPE = "vehicletype";
        public static final String PARK_IN_OUT = "parkinout";
        public static final String PARK_IN_DATETIME = "parkindatetime";
        public static final String PARK_OUT_DATETIME = "parkoutdatetime";
        public static final String AMOUNT = "amount";


        public static Uri buildAccountUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
