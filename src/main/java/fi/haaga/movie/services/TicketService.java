package fi.haaga.movie.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fi.haaga.movie.convertor.TicketConvertor;
import fi.haaga.movie.entities.Show;
import fi.haaga.movie.entities.ShowSeat;
import fi.haaga.movie.entities.Ticket;
import fi.haaga.movie.entities.User;
import fi.haaga.movie.repositories.ShowRepository;
import fi.haaga.movie.repositories.TicketRepository;
import fi.haaga.movie.repositories.UserRepository;
import fi.haaga.movie.request.TicketRequest;
import fi.haaga.movie.response.TicketResponse;
import fi.haaga.movie.exceptions.SeatsNotAvailable;
import fi.haaga.movie.exceptions.ShowDoesNotExists;
import fi.haaga.movie.exceptions.UserDoesNotExists;
import fi.haaga.movie.exceptions.TicketDoesNotExist;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ShowRepository showRepository;

    @Autowired
    private UserRepository userRepository;

    public TicketResponse ticketBooking(TicketRequest ticketRequest) {
        Optional<Show> showOpt = showRepository.findById(ticketRequest.getShowId());

        if (showOpt.isEmpty()) {
            throw new ShowDoesNotExists();
        }

        Optional<User> userOpt = userRepository.findById(ticketRequest.getUserId());

        if (userOpt.isEmpty()) {
            throw new UserDoesNotExists();
        }

        User user = userOpt.get();
        Show show = showOpt.get();

        Boolean isSeatAvailable = isSeatAvailable(show.getShowSeatList(), ticketRequest.getRequestSeats());

        if (!isSeatAvailable) {
            throw new SeatsNotAvailable();
        }

        // Count price and assign seats
        Integer getPriceAndAssignSeats = getPriceAndAssignSeats(show.getShowSeatList(), ticketRequest.getRequestSeats());

        String seats = listToString(ticketRequest.getRequestSeats());

        Ticket ticket = new Ticket();
        ticket.setTotalTicketsPrice(getPriceAndAssignSeats);
        ticket.setBookedSeats(seats);
        ticket.setUser(user);
        ticket.setShow(show);

        ticket = ticketRepository.save(ticket);

        user.getTicketList().add(ticket);
        show.getTicketList().add(ticket);
        userRepository.save(user);
        showRepository.save(show);

        return TicketConvertor.returnTicket(show, ticket);
    }

    private Boolean isSeatAvailable(List<ShowSeat> showSeatList, List<String> requestSeats) {
        for (ShowSeat showSeat : showSeatList) {
            String seatNo = showSeat.getSeatNo();

            if (requestSeats.contains(seatNo) && !showSeat.getIsAvailable()) {
                return false;
            }
        }

        return true;
    }

    private Integer getPriceAndAssignSeats(List<ShowSeat> showSeatList, List<String> requestSeats) {
        Integer totalAmount = 0;

        for (ShowSeat showSeat : showSeatList) {
            if (requestSeats.contains(showSeat.getSeatNo())) {
                totalAmount += showSeat.getPrice();
                showSeat.setIsAvailable(Boolean.FALSE);
            }
        }

        return totalAmount;
    }

    private String listToString(List<String> requestSeats) {
        StringBuilder sb = new StringBuilder();

        for (String s : requestSeats) {
            sb.append(s).append(",");
        }

        return sb.toString();
    }

    // New method to delete a ticket
    public void deleteTicket(Integer ticketId) {
        // Find the ticket by ID
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new TicketDoesNotExist("Ticket not found"));

        // Remove the ticket from the user's list and the show's list (optional, depending on your need)
        User user = ticket.getUser();
        Show show = ticket.getShow();

        user.getTicketList().remove(ticket);
        show.getTicketList().remove(ticket);

        // Delete the ticket from the database
        ticketRepository.delete(ticket);

        // Optionally, you can save the user and show if needed after removing the ticket
        userRepository.save(user);
        showRepository.save(show);
    }
}
