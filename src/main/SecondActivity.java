package wea.com;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity{
    Button next_button, previous_button, data_button;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        listView=(ListView)findViewById(R.id.listview);
        ArrayList<String> arrayList= new ArrayList<>();

        arrayList.add("CMAC_message_number: 00001056");
        arrayList.add("CMAC_sent_date_time: 2022-10-19 20:59:46");
        arrayList.add("averageTime: 00:05:00");
        arrayList.add("shortestTime: 00:05:00");
        arrayList.add("longestTime: 00:05:00");
        arrayList.add("averageDelay: 00:00:36");
        arrayList.add("deviceCount: 2");
        arrayList.add("receivedOutsideCount: 0");
        arrayList.add("displayedOutsideCount: 1");

        ArrayAdapter arrayAdapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
        // Add button Move to next Activity and previous Activity
        next_button = (Button) findViewById(R.id.second_activity_next_button);
        previous_button = (Button) findViewById(R.id.second_activity_previous_button);
        previous_button.setOnClickListener(v -> {
            // Intents are objects of the android.content.Intent type. The code can send them to the Android system defining
            // the components you are targeting. Intent to start an activity called ThirdActivity with the following code.
            Intent intent = new Intent(SecondActivity.this, MainActivity.class);
            // start the activity connect to the specified class
            startActivity(intent);
        });
        next_button.setOnClickListener(view -> {
            // Intents are objects of the android.content.Intent type. The code can send them to the Android system defining
            // the components you are targeting. Intent to start an activity called SecondActivity with the following code.
            Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
            // start the activity connect to the specified class
            startActivity(intent);
        });
    }

}
