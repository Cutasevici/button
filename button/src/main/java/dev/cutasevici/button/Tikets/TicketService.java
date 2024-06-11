package dev.cutasevici.button.Tikets;

import dev.cutasevici.button.ItemDTO;
import dev.cutasevici.button.orders.OrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private SimpMessagingTemplate template;

    public Ticket createTicket(OrderDTO order, List<ItemDTO> newItems) {
        Ticket ticket = new Ticket();
        ticket.setItemsText(convertItemsToString(newItems));
        ticketRepository.save(ticket);

        // Convert the saved Ticket to a TicketDTO
        TicketDTO ticketDto = convertToDTO(ticket);

        // Send a message to the /topic/tickets topic
        template.convertAndSend("/topic/tickets", ticketDto);

        return ticket;
    }

    private String convertItemsToString(List<ItemDTO> items) {
        return items.stream()
                .map(item -> item.getItemName() + " (Comment: " + item.getItemCommentary() + ")")
                .collect(Collectors.joining(", "));
    }

    private TicketDTO convertToDTO(Ticket ticket) {
        TicketDTO dto = new TicketDTO();
        dto.setId(ticket.getId());
        dto.setCreationTimestamp(ticket.getCreationTimestamp());
        dto.setItemsText(ticket.getItemsText());
        dto.setClosingTimestamp(ticket.getClosingTimestamp());
        dto.setStatus(ticket.getStatus());
        return dto;
    }

    public List<Ticket> fetchPendingTickets() {
        return ticketRepository.findByStatus("inProcess");
    }

    public TicketDTO closeTicket(Long ticketId, Date closingTimestamp) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found with id: " + ticketId));

        // Convert Date to LocalDateTime
        LocalDateTime localDateTime = closingTimestamp.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        ticket.setClosingTimestamp(localDateTime);
        ticket.setStatus("done");
        ticketRepository.save(ticket);

        // Convert the updated Ticket to a TicketDTO and return it
        return convertToDTO(ticket);
    }
}