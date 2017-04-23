package ashtapradhan.com.parkme.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by TechSutra on 4/23/17.
 */

public class ParkMeProvider extends ContentProvider {

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    static final int V_P_D= 100;

    private ParkMeDbHelper parkMeDbHelper;

    @Override
    public boolean onCreate() {
        parkMeDbHelper = new ParkMeDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor retCursor;
        switch (sUriMatcher.match(uri)) {
            /**
             * Retun Vehicle Parking Details
             */
            case V_P_D:{
                retCursor = parkMeDbHelper.getReadableDatabase().query(
                        ParkMeContrack.VehicleParkingDetails.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }


            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db = parkMeDbHelper.getWritableDatabase();
        Uri returnUri;

        switch (sUriMatcher.match(uri)) {
            case V_P_D: {

                long _id = db.insert(ParkMeContrack.VehicleParkingDetails.TABLE_NAME, null, values);
                if ( _id > 0 )
                    returnUri = ParkMeContrack.VehicleParkingDetails.buildAccountUri(_id);
                else
                    throw new android.database.SQLException("Failed to insert row into " + uri);
                break;
            }

            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    static UriMatcher buildUriMatcher() {

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = ParkMeContrack.CONTENT_AUTHORITY;

        matcher.addURI(authority, ParkMeContrack.PARKING, V_P_D);
        return matcher;
    }
}
