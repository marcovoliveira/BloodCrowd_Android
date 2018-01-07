package com.example.marco.bloodcrowd;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
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

import static java.lang.Integer.MAX_VALUE;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final TextView nome = findViewById(R.id.Nome);
        final TextView idade = findViewById(R.id.Idade);
        final TextView grupoSanguineo = findViewById(R.id.GrupoSanguineo);
        final TextView dataNascimento = findViewById(R.id.dataNascimento);
        final TextView imc = findViewById(R.id.imc);
        final TextView telemovel = findViewById(R.id.telemovel);
        final TextView cidade = findViewById(R.id.cidade);
        final TextView rua = findViewById(R.id.rua);
        final TextView codigopostal = findViewById(R.id.codigopostal);
        final TextView email = findViewById(R.id.email);
        final TextView empresa = findViewById(R.id.empresa);
        final TextView ocupacao = findViewById(R.id.ocupacao);
        final TextView genero = findViewById(R.id.genero);
        final TextView peso = findViewById(R.id.peso);
        final TextView altura = findViewById(R.id.altura);
        Button buttonCall = findViewById(R.id.makeCall);

        int number = Integer.parseInt(getIntent().getStringExtra("number"));

        RequestQueue queue = Volley.newRequestQueue(this);
        final String url =
                "http://bloodcrowdservice.apphb.com/Service1.svc/rest/donatorsnumber/" + number;

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Gson gson = new Gson();
                        Donator donator[] = gson.fromJson(
                                response.toString(), Donator[].class);

                        for (Donator donators : donator) {

                            nome.setText(donators.getFirstName() + " " + donators.getLastName());
                            grupoSanguineo.setText(donators.getBloodType());
                            dataNascimento.setText(donators.getBirthDay());
                            idade.setText(donators.getAge() + "");
                            imc.setText(donators.getImc() + "");
                            telemovel.setText(donators.getTelephoneNumber() + "");
                            cidade.setText(donators.getCity());
                            rua.setText(donators.getStreetAddress());
                            codigopostal.setText(donators.getZipCode());
                            email.setText(donators.geteMail());
                            empresa.setText(donators.getCompany());
                            ocupacao.setText(donators.getOccupation());
                            genero.setText(donators.getSexo());
                            peso.setText(donators.getKilograms() + "");
                            altura.setText(donators.getCentimeters() + "");

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ProfileActivity.this,
                                "Erro ao receber os donator do WebService",
                                Toast.LENGTH_SHORT).show();
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


        // ACTION_DIAL
        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + telemovel.getText().toString()));
                startActivity(callIntent);
            }
        });
    }
}
