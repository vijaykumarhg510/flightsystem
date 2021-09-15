package com.flightbooking.Data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flightbooking.Data.Airline;
import com.flightbooking.converter.ListToString;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class AirlineSchedule implements Serializable {

  private static final long serialVersionUID = 1L;

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime startDateTime;


    private LocalDateTime endDateTime;
    private String fromPlace;
    private String toPlace;

    @Convert(converter = ListToString.class)
    private List<String> workingDays;

    @ManyToOne(targetEntity = Airline.class)
    @JoinColumn(name="airline_number",referencedColumnName = "airlineNumber",
                 nullable = false)
    private Airline airline;

    @OneToOne(targetEntity = Seats.class)
    @JoinColumn(name="seats_id",referencedColumnName = "id")
    private Seats seats;

    @Override
    public String toString() {
        return "Airline" + "-" + airline.getAirlineNumber() + " of id " + id + " works on " + workingDays
        + " departs from " + fromPlace + " at " + startDateTime + " reaches " + toPlace +
                " at " + endDateTime;
    }
}
