package com.example.marco.bloodcrowd;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import org.json.JSONArray;
import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    BloodDonator bloodDonator = Singleton.getInstance().getBloodDonator();
    private ArrayAdapter<Donator> adapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestQueue queue = Volley.newRequestQueue(this);
        final String url =
                "http://bloodcrowdservice.apphb.com/Service1.svc/rest/donatorsshort";

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();
                        Donator[] donators = gson.fromJson(
                                response.toString(), Donator[].class);
                        for (Donator donator : donators) {

                            Singleton.getInstance().getBloodDonator().addDonator(donator);
                        }
                        adapter.notifyDataSetChanged();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,
                                "Erro ao receber os donators do WebService",
                                Toast.LENGTH_SHORT).show();
                    }
                }
        )
        {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };
        queue.add(request);


        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                bloodDonator.getDonators());
        listView = findViewById(R.id.listView_contacts);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView,View view, int position, long l){

                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                Donator donator = adapter.getItem(position);
                intent.putExtra("number", ""+donator.getNumber());

                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onClick_SearchItem(MenuItem item) {
        Intent i = new Intent(this, SearchActivity.class);
        startActivity(i);

    }
}
