package com.example.crudoperationmvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.example.crudoperationmvvm.database.ItemTable;
import com.example.crudoperationmvvm.repository.ItemRepository;

import java.util.List;

public class ItemViewModel extends AndroidViewModel {
    public ItemRepository itemRepository;
    private final LiveData<List<ItemTable>> allItems;

    public ItemViewModel(@NonNull Application application) {
        super(application);
        itemRepository = new ItemRepository(application);
        allItems=itemRepository.getAllItems();
    }
    public LiveData<List<ItemTable>> getAllItems() {
        return allItems;
    }


    public void updateItem(ItemTable itemTable) {
        itemRepository.updateItem(itemTable);
    }

    public void insertItem(ItemTable itemTable) {
        itemRepository.insertItem(itemTable);
    }

    public void deleteItem(int id) {
        itemRepository.deleteItem(id);
    }
}
