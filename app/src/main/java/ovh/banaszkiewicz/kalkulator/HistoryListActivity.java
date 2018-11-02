package ovh.banaszkiewicz.kalkulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class HistoryListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_list);

        ArrayList<String> items = getIntent().getStringArrayListExtra(MainActivity.HISTORY);
        ListView listView = findViewById(R.id.resultListView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.history_list_item, items);
        listView.setAdapter(adapter);
    }

    public void onClickBackToCalculatorButton(View v) {
        this.finishAndRemoveTask();
    }
}
