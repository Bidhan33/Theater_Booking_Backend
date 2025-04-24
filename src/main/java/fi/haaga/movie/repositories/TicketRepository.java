package fi.haaga.movie.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fi.haaga.movie.entities.Ticket;

public interface TicketRepository extends JpaRepository<Ticket,Integer> {
}
