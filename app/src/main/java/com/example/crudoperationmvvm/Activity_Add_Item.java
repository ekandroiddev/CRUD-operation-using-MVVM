package com.example.crudoperationmvvm;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.crudoperationmvvm.database.ItemTable;
import com.example.crudoperationmvvm.databinding.ActivityAddItemBinding;
import com.example.crudoperationmvvm.viewmodel.ItemViewModel;

public class Activity_Add_Item extends AppCompatActivity {
    ActivityAddItemBinding binding;
    ItemViewModel itemViewModel;

    String itemName,itemDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding=ActivityAddItemBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.skyBlue));


        itemViewModel=new ViewModelProvider(this).get(ItemViewModel.class);

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.saveBtn.setOnClickListener(v -> {
            itemName=binding.itemName.getText().toString();
            itemDesc=binding.itemDesc.getText().toString();
            ItemTable itemTable=new ItemTable(itemName,itemDesc);
            itemViewModel.insertItem(itemTable);
            Toast.makeText(this, "item inserted", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}