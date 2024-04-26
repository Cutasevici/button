package dev.cutasevici.button.Order_View_Edit;

import dev.cutasevici.button.ItemDTO;
import dev.cutasevici.button.orders.OrderDTO;
import dev.cutasevici.button.orders.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders/edit")
public class OrderEditController {

    @Autowired
    private OrderService orderService;

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long orderId, @RequestBody OrderDTO orderDTO) {
        try {
            List<ItemDTO> itemsDto = orderDTO.getItems();
            String action = determineAction(orderDTO); // You'll need to implement this method based on your application's logic
            OrderDTO updatedOrder = orderService.updateOrder(orderId, itemsDto, action);
            return ResponseEntity.ok(updatedOrder);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    private String determineAction(OrderDTO orderDTO) {
        if (orderDTO.getStatus().equals("add")) {
            return "add";
        } else if (orderDTO.getStatus().equals("delete")) {
            return "delete";
        } else {
            throw new IllegalArgumentException("Invalid status: " + orderDTO.getStatus());
        }
    }
}

