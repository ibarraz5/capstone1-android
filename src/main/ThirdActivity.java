package wea.com;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ThirdActivity extends AppCompatActivity {
    Button next_button, previous_button,button1;
    String hello;
    TextView textView;
    ListView listView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        listView2=(ListView)findViewById(R.id.listview2);
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
        listView2.setAdapter(arrayAdapter);
        // Add button Move to next Activity and previous Activity
        next_button = (Button) findViewById(R.id.third_activity_next_button);
        previous_button = (Button) findViewById(R.id.third_activity_previous_button);
        previous_button.setOnClickListener(v -> {
            // Intents are objects of the android.content.Intent type. The code can send them to the Android system defining
            // the components you are targeting. Intent to start an activity called ThirdActivity with the following code.
            Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
            // start the activity connect to the specified class
            startActivity(intent);
        });
        next_button.setOnClickListener(view -> {
            // Intents are objects of the android.content.Intent type. The code can send them to the Android system defining
            // the components you are targeting. Intent to start an activity called SecondActivity with the following code.
            Intent intent = new Intent(ThirdActivity.this, FourthActivity.class);
            // start the activity connect to the specified class
            startActivity(intent);
        });
        button1 =  (Button)findViewById(R.id.weaeng);
        textView=(TextView)findViewById(R.id.textView1);

        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                textView.setText(hello);

            }         });
    }


}