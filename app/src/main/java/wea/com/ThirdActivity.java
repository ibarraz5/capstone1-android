package wea.com;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.widget.Button;
        import androidx.appcompat.app.AppCompatActivity;

public class ThirdActivity extends AppCompatActivity {
    Button next_button, previous_button,button1;
    String hello;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        // Add button Move to next Activity and previous Activity
        next_button = (Button) findViewById(R.id.second_activity_next_button);
        previous_button = (Button) findViewById(R.id.second_activity_previous_button);
        previous_button.setOnClickListener(v -> {
            // Intents are objects of the android.content.Intent type. The code can send them to the Android system defining
            // the components you are targeting. Intent to start an activity called ThirdActivity with the following code.
            Intent intent = new Intent(ThirdActivity.this, SecondActivity.class);
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