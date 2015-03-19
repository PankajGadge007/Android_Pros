package pankaj.com.geolocation;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends ActionBarActivity implements OnMapReadyCallback, View.OnClickListener {

    private MapFragment mapFragment;
    private GoogleMap googleMap;
    private TextView textviewLatitude, textviewLongitude, textviewCountry, textviewCity, textviewPostalCode, textviewAddressLine;
    private Double latitude, longitude;
    private Button btnShowLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowLocation = (Button)findViewById(R.id.btnShowLocation);

        mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        googleMap = mapFragment.getMap();

        if (googleMap==null){
            googleMap = mapFragment.getMap();
            if (googleMap!=null){
                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
            }
        }


        GPSTracker gpsTracker = new GPSTracker(this);

        if (gpsTracker.canGetLocation()) {
            latitude = gpsTracker.latitude;
            textviewLatitude = (TextView) findViewById(R.id.fieldLatitude);

            longitude = gpsTracker.longitude;
            textviewLongitude = (TextView) findViewById(R.id.fieldLongitude);

            String stringLatitude = String.valueOf(gpsTracker.latitude);
            textviewLatitude.setText(stringLatitude);
            String stringLongitude = String.valueOf(gpsTracker.longitude);
            textviewLongitude.setText(stringLongitude);

//            String country = gpsTracker.getCountryName(this);
//            textviewCountry = (TextView)findViewById(R.id.fieldCountry);
//            textviewCountry.setText(country);
//
//            String city = gpsTracker.getLocality(this);
//            textviewCity = (TextView)findViewById(R.id.fieldCity);
//            textviewCity.setText(city);
//
//            String postalCode = gpsTracker.getPostalCode(this);
//            textviewPostalCode = (TextView)findViewById(R.id.fieldPostalCode);
//            textviewPostalCode.setText(postalCode);
//
//            String addressLine = gpsTracker.getAddressLine(this);
//            textviewAddressLine = (TextView)findViewById(R.id.fieldAddressLine);
//            textviewAddressLine.setText(addressLine);
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gpsTracker.showSettingsAlert();
        }

        btnShowLocation.setOnClickListener(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .title("Marker"));

    }

    @Override
    public void onClick(View v) {
        GPSTracker gpsTracker1 = new GPSTracker(this);

        if (gpsTracker1.canGetLocation()) {
            latitude = gpsTracker1.latitude;
            longitude = gpsTracker1.longitude;
            onMapReady(googleMap);

            String stringLatitude = String.valueOf(gpsTracker1.latitude);
            textviewLatitude.setText(stringLatitude);
            String stringLongitude = String.valueOf(gpsTracker1.longitude);
            textviewLongitude.setText(stringLongitude);

        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gpsTracker1.showSettingsAlert();
        }
    }
}