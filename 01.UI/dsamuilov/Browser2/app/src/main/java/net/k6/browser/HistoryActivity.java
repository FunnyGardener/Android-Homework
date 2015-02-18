package net.k6.browser;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


import java.util.Iterator;
import java.util.List;


public class HistoryActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        TextView historyText = (TextView) findViewById(R.id.historyText);
        List<String> historyList = History.getHistory();
        for (String iterator :historyList) {
            historyText.append(iterator + "\n");
        }
    }
}
