package dev.cutasevici.button.Tikets;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "creation_timestamp", nullable = false)
    private LocalDateTime creationTimestamp;

    @Column(name = "items_text", nullable = false, columnDefinition = "TEXT")
    private String itemsText;

    @Column(name = "closing_timestamp")
    private LocalDateTime closingTimestamp;

    @Column(name = "status", nullable = false)
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

    public Ticket() {
        this.creationTimestamp = LocalDateTime.now();
        this.status = "inProcess";
    }

}