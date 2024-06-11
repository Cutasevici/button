package dev.cutasevici.button.order_items;

import dev.cutasevici.button.Item;
import dev.cutasevici.button.orders.Order;
import jakarta.persistence.*;



@Entity
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderItemId;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;  // This retains a reference to Item

    @Column(name = "processed", nullable = false)
    private Boolean processed = false; // Default value set to false

    @Column(name = "item_commentary")
    private String itemCommentary;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Integer lineTotal;  // Ensure this matches your database schema

    // Constructors
    public OrderItem() {
    }

    // Getters and Setters
    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    // Setters
    public void setItem(Item item) {
        this.item = item;
        calculateLineTotal();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        calculateLineTotal();
    }

    private void calculateLineTotal() {
        if (item != null && quantity != null) {
            this.lineTotal = item.getItemPrice() * quantity;
        }
    }

    public String getItemCommentary() {
        return itemCommentary;
    }

    public void setItemCommentary(String itemCommentary) {
        this.itemCommentary = itemCommentary;
    }

    public Boolean getProcessed() {
        return processed;
    }

    public void setProcessed(Boolean processed) {
        this.processed = processed;
    }

    public Integer getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(Integer lineTotal) {
        this.lineTotal = lineTotal;
    }
}