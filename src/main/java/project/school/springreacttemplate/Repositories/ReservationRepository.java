package project.school.springreacttemplate.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import project.school.springreacttemplate.Models.ReservationData.Reservations;

public interface ReservationRepository extends JpaRepository<Reservations, Integer> {}
