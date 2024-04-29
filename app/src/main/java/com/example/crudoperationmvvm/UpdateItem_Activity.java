package com.example.crudoperationmvvm;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.crudoperationmvvm.database.ItemTable;
import com.example.crudoperationmvvm.viewmodel.ItemViewModel;

public class UpdateItem_Activity extends AppCompatActivity {
    EditText upitemName,upitemDesc;
    Button updateBtn;
    Intent intent;
    int itemId;
    String upItemName,upItemDesc;
    ItemViewModel itemViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_item);

        upitemName=findViewById(R.id.upitemName);
        upitemDesc=findViewById(R.id.upitemDesc);
        updateBtn=findViewById(R.id.updateBtn);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.skyBlue));

        itemViewModel=new ViewModelProvider(this).get(ItemViewModel.class);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        intent=getIntent();
        itemId=intent.getIntExtra("id",0);
        upItemName=intent.getStringExtra("itemname");
        upItemDesc=intent.getStringExtra("itemdesc");

        upitemName.setText(upItemName);
        upitemDesc.setText(upItemDesc);

        updateBtn.setOnClickListener(v -> {
            String updatedItemName=upitemName.getText().toString();
            String updatedItemDesc=upitemDesc.getText().toString();
            ItemTable itemTable=new ItemTable(itemId,updatedItemName,updatedItemDesc);
            itemViewModel.updateItem(itemTable);
            Toast.makeText(this, "item updated", Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}