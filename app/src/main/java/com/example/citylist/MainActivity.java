package com.example.citylist;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import androidx.recyclerview.widget.RecyclerView;




public class MainActivity extends AppCompatActivity {
    private ArrayAdapter adapter;
    private String Cities[];
    private ImageView img;
    private String cityName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.Listcity);
        Cities = getcityName();

        adapter = new RowIconAdapter();
        listView.setAdapter(new RowIconAdapter());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedcity = Cities[position];

                Location selectedLocation = loadLocationsData().get(selectedcity);
                Toast.makeText(getApplicationContext(), selectedLocation.toString(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, CityDetail.class);
                intent.putExtra("selected_province", selectedcity);
                intent.putExtra("selected_location", selectedLocation);
                startActivity(intent);
            }


        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    private HashMap<String, Location> loadLocationsData() {
        HashMap<String, Location> location = new HashMap<>();
        location.put("PnomPenh1", new Location(111.89, 222.33));
        location.put("PnomPenh2", new Location(23.456, 12.667));
        location.put("PnomPenh3", new Location(234.456, 23.56));
        return location;

    }

    private String[] getcityName() {
        String[] city = new String[loadLocationsData().size()];
        city = loadLocationsData().keySet().toArray(city);

        return city;
    }

    private void displaySelectedCityInfo() {
        displaySelectedCityInfo(null);
    }

    private void displaySelectedCityInfo(String cityName) {
        Location loc = loadLocationsData().get(cityName);
        Toast.makeText(getApplicationContext(), loc.toString(), Toast.LENGTH_LONG).show();

    }

    private static class ViewHolder {
        TextView name;
        ImageView img;
    }

    class RowIconAdapter extends ArrayAdapter<String> {
        public RowIconAdapter() {
            super(MainActivity.this, R.layout.listrow, R.id.row_label, Cities);
        }


        public View getView(int pos, View cView, @NonNull ViewGroup parent) {
            View row = super.getView(pos, cView, parent);
            ImageView icon = (ImageView) row.findViewById(R.id.row_image);
            String city = Cities[pos];
            ViewHolder viewHolder = new ViewHolder();

            if (cView == null) {
                LayoutInflater inflater = LayoutInflater.from(getApplicationContext());

                cView = inflater.inflate(R.layout.listrow, parent, false);
                viewHolder.name = (TextView) cView.findViewById(R.id.row_label);
                viewHolder.img = (ImageView) cView.findViewById(R.id.row_image);
                cView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) cView.getTag();
            }
            if (city.startsWith("PnomPenh1")) {
                viewHolder.img.setImageResource(R.drawable.img_3);
            } else if (city.startsWith("PnomPenh2")) {
                icon.setImageResource(R.drawable.img);
            } else {
                viewHolder.img.setImageResource(R.drawable.img_2);
            }
            return cView;
        }
    }
}

