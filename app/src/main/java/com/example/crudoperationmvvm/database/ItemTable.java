package com.example.crudoperationmvvm.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class ItemTable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "taskName")
    public String taskName;
    @ColumnInfo(name = "taskDesc")
    public String taskDesc;

    public ItemTable(int id, String taskName, String taskDesc) {
        this.id = id;
        this.taskName = taskName;
        this.taskDesc = taskDesc;
    }
    @Ignore
    public ItemTable(String taskName, String taskDesc) {
        this.taskName = taskName;
        this.taskDesc = taskDesc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }
}
