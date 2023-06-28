package com.example.lab_5_1.data;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "Items",
        foreignKeys = {
                @ForeignKey(
                        entity = User.class,
                        parentColumns = "user_id",
                        childColumns = "ownerId",
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class Item {
    @PrimaryKey(autoGenerate = true)
        public int itemId;
    public int ownerId;
    public String nameOfItem;

    public Item (String nameOfItem, int ownerId){
        this.nameOfItem = nameOfItem;
        this.ownerId = ownerId;
    }
}
