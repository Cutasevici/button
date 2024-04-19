package dev.cutasevici.button.orders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.stream.Collectors;

import dev.cutasevici.button.Item;
import dev.cutasevici.button.ItemDTO;
import dev.cutasevici.button.ItemRepository;
import dev.cutasevici.button.order_items.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Transactional
    public boolean createOrder(List<ItemDTO> itemsDto) {
        Order order = new Order();
        Set<OrderItem> orderItems = new HashSet<>();
        int total = 0; // Initialize total

        for (ItemDTO dto : itemsDto) {
            OrderItem orderItem = new OrderItem();
            Item item = new Item(); // Suppose Item entity needs to be filled with DTO data
            item.setItemId(dto.getItemId());
            item.setItemName(dto.getItemName());
            item.setItemPrice(dto.getItemPrice());

            orderItem.setItem(item);
            orderItem.setQuantity(1); // Example quantity
            orderItem.setOrder(order);
            orderItems.add(orderItem);

            total += dto.getItemPrice(); // Calculate total based on item prices
        }

        order.setOrderItems(orderItems); // Set the items
        order.setTotal(total); // Set the calculated total
        orderRepository.save(order); // Save the order
        return true;
    }
    @Transactional
    public OrderDTO updateOrder(Long orderId, OrderDTO orderDTO) {
        Order order = orderRepository.findById(Math.toIntExact(orderId))
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

        // Map existing items for quick lookup
        Map<Integer, OrderItem> existingItemsMap = new HashMap<>();
        for (OrderItem orderItem : order.getOrderItems()) {
            existingItemsMap.put(orderItem.getItem().getItemId(), orderItem);
        }

        Set<OrderItem> updatedItems = new HashSet<>();

        // Check each DTO item to see if it should be added or already exists
        for (ItemDTO dto : orderDTO.getItems()) {
            OrderItem existingItem = existingItemsMap.get(dto.getItemId());
            if (existingItem != null) {
                // Item already exists, retain it in the order
                updatedItems.add(existingItem);
                existingItemsMap.remove(dto.getItemId()); // Remove from map to prevent deletion
            } else {
                // Item does not exist, create a new OrderItem
                Item newItem = new Item();
                newItem.setItemId(dto.getItemId());
                newItem.setItemName(dto.getItemName());
                newItem.setItemPrice(dto.getItemPrice());

                OrderItem newOrderItem = new OrderItem();
                newOrderItem.setItem(newItem);
                newOrderItem.setQuantity(1); // Adjust quantity as necessary
                newOrderItem.setOrder(order);
                updatedItems.add(newOrderItem);
            }
        }

        // Remove items not included in the new DTO list
        existingItemsMap.values().forEach(order.getOrderItems()::remove);

        // Add all retained and new items to the order
        order.getOrderItems().addAll(updatedItems);

        // Optionally recalculate the total if necessary
        int newTotal = updatedItems.stream()
                .mapToInt(item -> item.getItem().getItemPrice() * item.getQuantity())
                .sum();
        order.setTotal(newTotal);

        orderRepository.save(order);

        return convertToOrderDTO(order); // Return the updated OrderDTO
    }



    private OrderDTO convertToOrderDTO(Order order) {
        if (order == null) {
            logger.error("Attempted to convert a null Order object to OrderDTO");
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getOrderId());
        orderDTO.setOrderDate(order.getOrderDate());
        orderDTO.setCustomerId(order.getCustomerId());
        orderDTO.setStatus(order.getStatus());
        orderDTO.setTotal(order.getTotal());

        if (order.getOrderItems() != null && !order.getOrderItems().isEmpty()) {
            List<ItemDTO> itemDTOs = order.getOrderItems().stream()
                    .map(orderItem -> {
                        if (orderItem != null && orderItem.getItem() != null) {
                            Item item = orderItem.getItem();
                            return new ItemDTO(item.getItemId(), item.getItemName(), item.getItemPrice());
                        } else {
                            logger.error("Null OrderItem or Item found while converting to ItemDTO");
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            orderDTO.setItems(itemDTOs);
        } else {
            logger.info("No items found for Order ID: {}", order.getOrderId());
        }

        return orderDTO;
    }
    @Transactional(readOnly = true)
    public OrderDTO findOrderById(Long orderId) {
        return orderRepository.findById(Math.toIntExact(orderId))
                .map(this::convertToOrderDTO)
                .orElse(null);  // Return null if the order is not found
    }

    public List<OrderDTO> findAllOrders() {
        List<Order> orders = orderRepository.findAll();  // Assuming you have a method to fetch all orders
        return orders.stream().map(this::convertToOrderDTO).collect(Collectors.toList());
    }

}
