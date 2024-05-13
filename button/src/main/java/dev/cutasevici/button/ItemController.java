package dev.cutasevici.button;
import dev.cutasevici.button.Response.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    private static final Logger log = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private ItemService itemService;

    public ItemController() {
        log.info("ItemController initialized");
    }

    @GetMapping("/item/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable int id) {
        log.info("Handling getItemById for ID: {}", id);
        ItemDTO item = itemService.getItemById(id);
        if (item == null) {
            log.info("Item not found for ID: {}", id);
            return ResponseEntity.notFound().build();
        }
        log.info("Item found: {}", item);
        return ResponseEntity.ok(item);
    }

    @GetMapping("/items")
    public ResponseEntity<List<ItemDTO>> getAllItems() {
        List<ItemDTO> items = itemService.getAllItems();
        if (items.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(items);
    }

    @GetMapping("/api/ping")
    public ResponseEntity<ApiResponse> ping() {
        log.info("Ping received");
        return ResponseEntity.ok(new ApiResponse(true, "Pong!"));
    }
}