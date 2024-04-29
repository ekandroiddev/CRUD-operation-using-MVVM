package com.example.crudoperationmvvm.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.crudoperationmvvm.database.ItemDao;
import com.example.crudoperationmvvm.database.ItemTable;
import com.example.crudoperationmvvm.database.RoomDataBase;

import java.util.List;

public class ItemRepository {
    public ItemDao itemDao;
    public LiveData<List<ItemTable>> allItems;

    public ItemRepository(Application application) {
        RoomDataBase roomDataBase = RoomDataBase.getInstance(application);
        itemDao = roomDataBase.itemDao();
        allItems = itemDao.getAllItems();
    }

    public LiveData<List<ItemTable>> getAllItems() {
        return allItems;
    }

    public void updateItem(ItemTable itemTable) {
        itemDao.updateItem(itemTable);
    }

    public void deleteItem(int id) {
        itemDao.deleteItem(id);
    }

    public void insertItem(ItemTable itemTable) {
        itemDao.insetItem(itemTable);
    }
}
