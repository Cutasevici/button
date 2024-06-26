package dev.cutasevici.button;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private static final Logger log = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemRepository itemRepository;

    public ItemDTO getItemById(int id) {
        log.debug("Fetching item with ID: {}", id);
        Item item = itemRepository.findById(id).orElse(null);
        if (item == null) {
            log.debug("No item found with ID: {}", id);
            return null;
        }
        log.debug("Item found: {}", item);
        return new ItemDTO(item.getItemId(), item.getItemName(), item.getItemPrice());
    }
}