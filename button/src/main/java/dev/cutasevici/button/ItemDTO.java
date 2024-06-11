package dev.cutasevici.button;

public class ItemDTO {
    private int itemId;
    private String itemName;
    private int itemPrice;

    private String itemType;
    private String itemCommentary;

    // Constructors
    public ItemDTO() {
    }

    public ItemDTO(int itemId, String itemName, int itemPrice, String itemType) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemType = itemType;

    }

    // Getters and Setters
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

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getItemCommentary() {
        return itemCommentary;
    }

    public void setItemCommentary(String itemCommentary) {
        this.itemCommentary = itemCommentary;
    }

    // toString method
    @Override
    public String toString() {
        return "ItemDTO{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", itemCommentary='" + itemCommentary + '\'' +
                '}';
    }
}
