package com.example.crudoperationmvvm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudoperationmvvm.adapter.RecyclerViewAdapter;
import com.example.crudoperationmvvm.viewmodel.ItemViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OperationInterface {

    RecyclerView recyclerView;
    ImageButton imageButton;
    ItemViewModel itemViewModel;
    RecyclerViewAdapter recyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.skyBlue));


        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        recyclerView=findViewById(R.id.recyclerView);
        imageButton=findViewById(R.id.additem);
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getApplicationContext());
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerViewAdapter = new RecyclerViewAdapter(this, new ArrayList<>(), this);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, Activity_Add_Item.class)));

        itemViewModel.getAllItems().observe(this, itemTables -> {
            Log.d("MainActivity", "Received item list size: " + itemTables.size());
            recyclerViewAdapter.submitList(itemTables);
            recyclerView.setAdapter(recyclerViewAdapter);
        });
    }

    @Override
    public void deleteItem(int id) {
        itemViewModel.deleteItem(id);
    }
}
