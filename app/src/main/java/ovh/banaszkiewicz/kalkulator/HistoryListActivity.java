package ovh.banaszkiewicz.kalkulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HistoryListActivity extends AppCompatActivity {

    private DbOperation dbOperation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DbHelper dbHelper = new DbHelper(getBaseContext());
        this.dbOperation = new DbOperation(dbHelper);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        List<String> items = dbOperation.getHistoryFromDb();
        ListView listView = findViewById(R.id.resultListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.history_list_item, items);
        listView.setAdapter(adapter);
    }

    public void onClickBackToCalculatorButton(View v) {
        this.finishAndRemoveTask();
    }
}
