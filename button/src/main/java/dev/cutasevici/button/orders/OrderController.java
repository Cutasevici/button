package dev.cutasevici.button.orders;

import dev.cutasevici.button.ItemDTO;
import dev.cutasevici.button.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder")
    public ResponseEntity<ApiResponse> createOrder(@RequestBody List<ItemDTO> items) {
        try {
            orderService.createOrder(items);  // Assuming this method processes the list and creates an order
            ApiResponse apiResponse = new ApiResponse(true, "Order created successfully");
            return ResponseEntity.ok(apiResponse);  // Return the ApiResponse encapsulated in ResponseEntity
        } catch (Exception e) {
            ApiResponse apiResponse = new ApiResponse(false, "Error creating order: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);  // Return error details in ApiResponse
        }
    }

}
