package project.school.springreacttemplate.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.school.springreacttemplate.Models.ReservationData.ReservationDates;
import project.school.springreacttemplate.Models.ReservationData.Reservations;
import project.school.springreacttemplate.Models.SiteSpotData.SiteSpots;
import project.school.springreacttemplate.Repositories.ReservationRepository;
import project.school.springreacttemplate.Repositories.SiteSpotRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;

@RestController
public class ReservationsController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private SiteSpotRepository siteSpotRepository;

    @PostMapping("/api/getReservationsTable")
    public ArrayList<Reservations> reservationTable(@RequestBody ReservationDates dates) {
        ArrayList<Reservations> list = new ArrayList<>();
        list.add(reservationRepository.findById(1).get());
        return list;
    }

    @PostMapping("/api/getSiteSpots")
    public ArrayList<SiteSpots> siteSpots(@RequestBody Map<String, String> values){
        ArrayList<SiteSpots> list = new ArrayList<>();
        list.add(siteSpotRepository.findById(Integer.parseInt(values.get("site_id"))).get());
        return list;
    }

    @GetMapping("/api/populate")
    public void populateDatabase(){
        Reservations reservation = new Reservations();
        reservation.setSiteSpotId("1");
        reservation.setSite_id(1);
        reservation.setReservationDateStart(new Date(System.currentTimeMillis()));
        reservation.setReservationDateEnd(new Date(System.currentTimeMillis()));
        reservationRepository.save(reservation);
    }
}
