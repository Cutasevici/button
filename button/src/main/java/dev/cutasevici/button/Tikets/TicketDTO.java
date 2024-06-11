package dev.cutasevici.button.Tikets;

import java.time.LocalDateTime;

public class TicketDTO {
    private Long id;
    private LocalDateTime creationTimestamp;
    private String itemsText;
    private LocalDateTime closingTimestamp;
    private String status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreationTimestamp() {
        return creationTimestamp;
    }

    public void setCreationTimestamp(LocalDateTime creationTimestamp) {
        this.creationTimestamp = creationTimestamp;
    }

    public String getItemsText() {
        return itemsText;
    }

    public void setItemsText(String itemsText) {
        this.itemsText = itemsText;
    }

    public LocalDateTime getClosingTimestamp() {
        return closingTimestamp;
    }

    public void setClosingTimestamp(LocalDateTime closingTimestamp) {
        this.closingTimestamp = closingTimestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}