package dev.cutasevici.button.order_items;

import dev.cutasevici.button.ItemDTO;

public class OrderItemDTO {
    private ItemDTO item;
    private int quantity;

    private String itemCommentary;

    // Constructors
    public OrderItemDTO() {
    }

    public OrderItemDTO(ItemDTO item, int quantity) {
        this.item = item;
        this.quantity = quantity;
        this.itemCommentary = itemCommentary;
    }

    // Getters and Setters
    public ItemDTO getItem() {
        return item;
    }

    public void setItem(ItemDTO item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
        return "OrderItemDTO{" +
                "item=" + item +
                ", quantity=" + quantity +
                ", itemCommentary='" + itemCommentary + '\'' +
                '}';
    }
}