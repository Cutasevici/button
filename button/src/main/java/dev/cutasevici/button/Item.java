package dev.cutasevici.button;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "new_item_table")  // The name of your database table
public class Item {

    @Id
    @Column(name = "item_ID")  // Exact column name in your database
    private int itemId;

    @Column(name = "item_name")  // Exact column name in your database
    private String itemName;

    @Column(name = "item_price")  // Exact column name in your database
    private int itemPrice;

    @Column(name = "item_type")
    private String itemType;

    // Default constructor
    public Item() {}

    // Full constructor
    public Item(int itemId, String itemName, int itemPrice, String itemType) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemType = itemType;
    }

    // Getters and setters
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemType() {return itemType;}

    public void setItemType(String itemType) {this.itemType = itemType;}
}