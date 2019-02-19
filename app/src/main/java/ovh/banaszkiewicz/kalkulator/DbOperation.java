package ovh.banaszkiewicz.kalkulator;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DbOperation {
    private DbHelper dbHelper;

    DbOperation(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    void putDataIntoSqliteDb(String text) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DbSettings.DbEntry.COLUMN_NAME, text);


        db.insert(DbSettings.DbEntry.TABLE_NAME, null, values);

    }

    List<String> getHistoryFromDb() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(
                DbSettings.DbEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        final List<String> history = new ArrayList<>();

        while (cursor.moveToNext()) {
            history.add(cursor.getString(1));
        }

        cursor.close();

        return history;
    }

    void deleteData() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DbSettings.DbEntry.TABLE_NAME, "1", null);
    }
}
