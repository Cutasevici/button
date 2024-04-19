package dev.cutasevici.button;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ButtonApplication {

	@Autowired
	private DatabaseService databaseService;  // Autowire the DatabaseService

	public static void main(String[] args) {
		SpringApplication.run(ButtonApplication.class, args);
	}

	@GetMapping("/")
	public String apiRoot(){
		boolean newItemTableExists = databaseService.checkIfTableExists("new_item_table");
		boolean ordersTableExists = databaseService.checkIfTableExists("orders");
		boolean orderItemsTableExists = databaseService.checkIfTableExists("order_items");

		return "Table 'new_item_table' exists: " + newItemTableExists + ", " +
				"Table 'orders' exists: " + ordersTableExists + ", " +
				"Table 'order_items' exists: " + orderItemsTableExists;
	}
}