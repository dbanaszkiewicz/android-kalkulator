package ovh.banaszkiewicz.kalkulator;

import android.provider.BaseColumns;

public final class DbSettings {
    private DbSettings() {}


    static class DbEntry implements BaseColumns {

        private DbEntry() {
        }

        static final String TABLE_NAME = "history";
        static final String ID = "id";
        static final String COLUMN_NAME = "text";


    }
}
