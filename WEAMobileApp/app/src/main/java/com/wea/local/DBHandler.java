import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "cmac_device_local";
    private static final int DB_VERSION = 1;
    private static final String CMAC_MESSAGE_TABLE_NAME = "cmac_message";
    private static final String CMAC_ALERT_TABLE_NAME = "cmac_alert";
    private static final String CMAC_MESSAGE_NO_COL = "CMACMessageNumber";
    private static final String CMAC_CAP_ID_COL = "CMACCapIdentifier";
    private static final String CMAC_SENDER_COL = "CMACSender";
    private static final String CMAC_DATE_TIME_COL = "CMACDateTime";
    private static final String CMAC_MESSAGE_TYPE_COL = "CMACMessageType";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + CMAC_ALERT_TABLE_NAME + " ("
                + CMAC_MESSAGE_NO_COL + " TEXT, "
                + CMAC_CAP_ID_COL + " TEXT, "
                + CMAC_SENDER_COL + " TEXT, "
                + CMAC_DATE_TIME_COL + " DATETIME, "
                + CMAC_MESSAGE_TYPE_COL + " TEXT)";
    }

    public DBHandler(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

}