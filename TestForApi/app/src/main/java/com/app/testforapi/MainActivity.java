package com.app.testforapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.app.testforapi.adapter.RecyclerviewAdapter;
import com.app.testforapi.api.ApiClient;
import com.app.testforapi.api.ApiInterface;
import com.app.testforapi.api.PojoList;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerviewAdapter recyclerviewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        listingData();
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(llm);

    }

    private void listingData() {

        ApiInterface apiInterface = ApiClient.getRetrofit().create(ApiInterface.class);
        Call<PojoList> listingData = apiInterface.getData();

        listingData.enqueue(new Callback<PojoList>() {
            @Override
            public void onResponse(Call<PojoList> call, Response<PojoList> response) {

                if (response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "Fetch success", Toast.LENGTH_SHORT).show();
                    Log.d("Response", "onResponse: Fetched");

                    PojoList pojoList =response.body();
                    List<PojoList.Datum> data;
                    data =pojoList.getData();

                    if (data.size()>0) {
                        recyclerviewAdapter = new RecyclerviewAdapter(data,MainActivity.this);
                        recyclerView.setAdapter(recyclerviewAdapter);
                    }else
                    {
                        Log.d("Response", "onResponse: " + data.size());
                    }
                }
            }

            @Override
            public void onFailure(Call<PojoList> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Fetch failed", Toast.LENGTH_SHORT).show();

            }
        });





    }
}