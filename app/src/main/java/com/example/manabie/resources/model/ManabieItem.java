package com.example.manabie.resources.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "manabieItem_table")
public class ManabieItem {
    @PrimaryKey
    private int id;
    private int backgroundColor;
    private int counter;

    public ManabieItem(int id, int backgroundColor, int counter) {
        this.id = id;
        this.backgroundColor = backgroundColor;
        this.counter = counter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
