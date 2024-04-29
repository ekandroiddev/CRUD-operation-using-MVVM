package com.example.crudoperationmvvm.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ItemDao {
    @Query("SELECT * FROM itemTable")
    LiveData<List<ItemTable>> getAllItems();

    @Insert
    public void insetItem(ItemTable itemTable);
    @Update
    public void updateItem(ItemTable itemTable);
    @Query("DELETE FROM itemTable WHERE id=:id")
    public void deleteItem(int id);

}
