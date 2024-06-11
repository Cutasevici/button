package dev.cutasevici.button.Tikets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TicketController {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private TicketRepository ticketRepository;


    public List<Ticket> fetchPendingTickets() {
        return ticketRepository.findByStatus("inProcess");
    }



    @Autowired
    private TicketService ticketService;

    @PostMapping("/tickets/close")
    public TicketDTO closeTicket(@RequestBody CloseTicketRequest request) {
        return ticketService.closeTicket(request.getTicketId(), request.getClosingTimestamp());
    }
    @MessageMapping("/getPendingTickets")
    public void getPendingTickets() {
        List<Ticket> pendingTickets = fetchPendingTickets();
        for (Ticket ticket : pendingTickets) {
            template.convertAndSend("/topic/tickets", ticket);
        }
    }

    @GetMapping("/api/pending-tickets")
    public ResponseEntity<List<TicketDTO>> getPendingTicketsHttp() {
        List<Ticket> tickets = fetchPendingTickets();
        List<TicketDTO> ticketDTOs = tickets.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ticketDTOs);
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

    public static class CloseTicketRequest {
        private Long ticketId;
        private Date closingTimestamp;



        public Long getTicketId() {
            return ticketId;
        }

        public void setTicketId(Long ticketId) {
            this.ticketId = ticketId;
        }

        public Date getClosingTimestamp() {
            return closingTimestamp;
        }

        public void setClosingTimestamp(Date closingTimestamp) {
            this.closingTimestamp = closingTimestamp;
        }
    }
}
