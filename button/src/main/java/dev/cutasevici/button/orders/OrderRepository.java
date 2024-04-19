package dev.cutasevici.button.orders;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    // You can define custom database queries here if needed
}