package ashtapradhan.com.parkme.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by TechSutra on 4/23/17.
 */

public class ParkMeDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "parkme.db";


    public ParkMeDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_VEHICLE_PARKING_DETAILS_TABLE = "CREATE TABLE " + ParkMeContrack.VehicleParkingDetails.TABLE_NAME + " (" +
                ParkMeContrack.VehicleParkingDetails._ID + " INTEGER PRIMARY KEY," +
                ParkMeContrack.VehicleParkingDetails.VEHICLE_NUMBER + " TEXT NOT NULL, " +
                ParkMeContrack.VehicleParkingDetails.VEHICLE_TYPE + " TEXT NOT NULL, " +
                ParkMeContrack.VehicleParkingDetails.PARK_IN_OUT + " TEXT NOT NULL, " +
                ParkMeContrack.VehicleParkingDetails.PARK_IN_DATETIME + " TEXT NOT NULL, " +
                ParkMeContrack.VehicleParkingDetails.PARK_OUT_DATETIME + " TEXT NOT NULL, " +
                ParkMeContrack.VehicleParkingDetails.AMOUNT + " TEXT NOT NULL, " +
                " );";


        sqLiteDatabase.execSQL(SQL_CREATE_VEHICLE_PARKING_DETAILS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + ParkMeContrack.VehicleParkingDetails.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
