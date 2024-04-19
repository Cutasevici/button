package dev.cutasevici.button.order_items;

import dev.cutasevici.button.ItemDTO;

public class OrderItemDTO {
    private ItemDTO item;
    private int quantity;

    // Constructors
    public OrderItemDTO() {
    }

    public OrderItemDTO(ItemDTO item, int quantity) {
        this.item = item;
        this.quantity = quantity;
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

    // toString method
    @Override
    public String toString() {
        return "OrderItemDTO{" +
                "item=" + item +
                ", quantity=" + quantity +
                '}';
    }
}