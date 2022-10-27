package wea.com;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
    Button next_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        next_button = (Button) findViewById(R.id.main_activity_next_button);
        // Add_button add clicklistener
        next_button.setOnClickListener(view -> {
            // Intents are objects of the android.content.Intent type. The code can send them to the Android system defining
            // the components you are targeting. Intent to start an activity called SecondActivity with the following code.
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            // start the activity connect to the specified class
            startActivity(intent);
        });
    }
}
