package kookmin.ac.kr.finalproject;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class InsertMapActivity extends AppCompatActivity {

    int check = 0; // 마크가 여러번 찍히지 않게 체크해주는 변수
    String save_address; // insertActivity로 전달할 한글주소
    TextView logView; // 현재 위치를 띄워줄 텍스트뷰
    Button mBtTodayInsert; // 한 일 입력 버튼
    Button mBtBack; // 메인으로 돌아가기 버튼
    private GoogleMap map; // 구글맵


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Log.d("Main", "onCreate");

        mBtTodayInsert = (Button) findViewById(R.id.bt_insert2);
        mBtBack = (Button) findViewById(R.id.bt_back);

        mBtTodayInsert.setOnClickListener(new View.OnClickListener() { // 한 일 입력 버튼 클릭
            @Override
            public void onClick(View v) {
                Intent input_intent = new Intent(InsertMapActivity.this, InsertActivity.class); // InsertActivity로 이동한다.
                input_intent.putExtra("address", save_address); // 현재주소를 InsertActivity로 전달한다.
                startActivity(input_intent);

            }
        });

        mBtBack.setOnClickListener(new View.OnClickListener() { // 돌아가기 버튼 클릭
            @Override
            public void onClick(View v) { // InsertMapActivity로 이동한다.
                finish();
            }
        });

        logView = (TextView) findViewById(R.id.log);
        logView.setText("현재 위치좌표");

        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        // GPS 프로바이더 사용가능여부
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 네트워크 프로바이더 사용가능여부
        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        Log.d("Main", "isGPSEnabled=" + isGPSEnabled);
        Log.d("Main", "isNetworkEnabled=" + isNetworkEnabled);

        LocationListener locationListener = new LocationListener() {
            Marker ME;

            public void onLocationChanged(Location location) {
                double lat = location.getLatitude();
                double lng = location.getLongitude();

                logView.setText("longtitude=" + lng + ", latitude=" + lat+"\n"+"현재 위치: "+getAddress(lat,lng));
                save_address = getAddress(lat,lng); // 한글주소를 저장한다.

                if (check == 0) {
                    LatLng MyLocation = new LatLng(lat, lng);

                    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
                    ME = map.addMarker(new MarkerOptions().position(MyLocation).title("ME"));
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(MyLocation, 15));
                    map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
                    check = 1; // 처음 위치를 잡으면 check
                } else if (check == 1) {
                    ME.remove(); // 전에 찍힌 마크를 삭제한다.
                                // -> 삭제되고 새로운 마크가 찍혀서 마크가 하나만 찍히게 한다.
                    LatLng MyLocation = new LatLng(lat, lng);


                    map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
                    ME = map.addMarker(new MarkerOptions().position(MyLocation).title("ME"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(MyLocation));
                }

                logView.setText("longtitude=" + lng + ", latitude=" + lat+"\n"+"현재 위치: "+getAddress(lat,lng));
                save_address = getAddress(lat,lng);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
                logView.setText("onStatusChanged");
            }

            public void onProviderEnabled(String provider) {
                logView.setText("onProviderEnabled");
            }

            public void onProviderDisabled(String provider) {
                logView.setText("onProviderDisabled");
            }
        };

        // Register the listener with the Location Manager to receive location updates
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

        // 수동으로 위치 구하기
        String locationProvider = LocationManager.GPS_PROVIDER;
        Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
        if (lastKnownLocation != null) {
            double lng = lastKnownLocation.getLatitude();
            double lat = lastKnownLocation.getLatitude();
            Log.d("Main", "longtitude=" + lng + ", latitude=" + lat);
            logView.setText("longtitude=" + lng + ", latitude=" + lat);
        }
    }


    private String getAddress(double lat, double lng) { // 현재 위치의 좌표를 이용해 한글주소로 변경한다.
        Geocoder gcK = new Geocoder(getApplicationContext(), Locale.KOREA);
        String res = "정보없음";
        try {
            List<Address> addresses = gcK.getFromLocation(lat, lng, 1);
            StringBuilder sb = new StringBuilder();

            if (null != addresses && addresses.size() > 0) {
                Address address = addresses.get(0);
                // sb.append(address.getCountryName()).append("/");
                // sb.append(address.getPostalCode()).append("/");
                sb.append(address.getLocality()).append("/");
                sb.append(address.getThoroughfare()).append("/");
                sb.append(address.getFeatureName());
                res = sb.toString();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}