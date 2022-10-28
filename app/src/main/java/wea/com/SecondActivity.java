package wea.com;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity{
    Button next_button, previous_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
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
