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
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    Button next_button, previous_button,button1;
    String hello;
    TextView textView;
    private GoogleMap mMap;
    MapView view;
    ListView listView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        view=(MapView)findViewById(R.id.mapview2);
        view.onCreate(savedInstanceState);
        view.getMapAsync(this);
    }

    @Override
    protected void onResume(){
        view.onResume();
        super.onResume();
    }
    @Override
    public void onMapReady(GoogleMap googleMap){
        mMap= googleMap;

        LatLng phoenix =new LatLng(33.44, 112.07);
        mMap.addMarker(new MarkerOptions().position(phoenix).title("Marker in Phoenix!"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(phoenix));
    }

}