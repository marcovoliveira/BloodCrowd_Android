package com.example.marco.bloodcrowd;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
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

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private ListView listView_search;
    private DonatorAdapter adapter;
    BloodDonator bloodDonator = new BloodDonator();
    String escolha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        this.listView_search = findViewById(R.id.listView_search);
        Button searchButton = findViewById(R.id.searchButton);
        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.lista_procurar, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(this);
        searchButton.setOnClickListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        escolha = adapterView.getItemAtPosition(pos).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
            //Vazio
    }

    @Override
    public void onClick(View view) {
        EditText editText_search = findViewById(R.id.editText_search);
        String campoPesquisa = editText_search.getText().toString();
        if (campoPesquisa.isEmpty()) {
            Toast.makeText(this, "Por favor introduza o/a "+ escolha +" a pesquisar!",Toast.LENGTH_LONG).show();
        } else {
            String url ="";
            String parametro = "";

            RequestQueue queue = Volley.newRequestQueue(this);

            if (escolha.equals("Nome")) {
                parametro = "/donators/";
            } else if (escolha.equals("Idade")) {
                parametro = "/donators/getByAge/";
            } else if (escolha.equals("Grupo Sanguíneo")){
                parametro = "/donators/getByBloodType/";
            } else if (escolha.equals("Compatibilidade com Grupo Sanguíneo")){
                parametro = "/donators/getByBloodTypeCompatibility/";
            } else if(escolha.equals(">= IMC")){
                try {
                    campoPesquisa = Double.toString(Double.parseDouble(campoPesquisa.replace(",", ".")) * 10);
                }catch (NumberFormatException nfe){
                    Toast.makeText(SearchActivity.this,
                            "IMC em formato invalido!",
                            Toast.LENGTH_LONG).show();
                }
                parametro = "/donators/getByImcMaiorOuIgual/";
            } else if(escolha.equals("<= IMC")){
                try {
                    campoPesquisa = Double.toString(Double.parseDouble(campoPesquisa.replace(",", ".")) * 10);
                }catch (NumberFormatException nfe){
                    Toast.makeText(SearchActivity.this,
                            "IMC em formato invalido!",
                            Toast.LENGTH_LONG).show();
                }
                parametro = "/donators/getByImcMenorOuIgual/";
            }

                Uri.Builder uri = null;
                try {
                    uri = new Uri.Builder();
                    uri.scheme("http")
                            .appendEncodedPath("/bloodcrowdservice.apphb.com/Service1.svc/rest"+parametro+campoPesquisa);
                    url = uri.build().toString();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            JsonArrayRequest request = new JsonArrayRequest(
                    Request.Method.GET,
                    url,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            bloodDonator.clear();
                            Gson gson = new Gson();
                            Donator[] donators = gson.fromJson(
                                    response.toString(), Donator[].class);
                            for (Donator donator : donators) {
                                bloodDonator.addDonatorPesquisado(donator);
                            }

                            adapter.notifyDataSetChanged();

                            if(bloodDonator.getDonatorsPesquisados().isEmpty()){
                                Toast.makeText(SearchActivity.this,
                                        "Não foram encontrados resultados!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            bloodDonator.clear();
                            // Certificar que a app não rebenta mesmo com erro 404 ou 400
                                Toast.makeText(SearchActivity.this,
                                        "Erro ao receber os donators",
                                        Toast.LENGTH_LONG).show();
                            }
                    }
            ) {
                @Override
                public Map<String, String> getHeaders() {
                    HashMap<String, String> headers = new HashMap<>();
                    headers.put("Content-Type", "application/json");
                    return headers;
                }
            };
            queue.add(request);

            adapter = new DonatorAdapter(
                    this,
                    R.layout.row,
                    bloodDonator.getDonatorsPesquisados());
            listView_search.setAdapter(adapter);

            listView_search.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> adapterView,View view, int position, long l){
                    Intent intent = new Intent(SearchActivity.this, ProfileActivity.class);
                    Donator donator = (Donator)  adapter.getItem(position);
                    intent.putExtra("number", ""+donator.getNumber());
                    startActivity(intent);
                }
            });
        }
    }
}


