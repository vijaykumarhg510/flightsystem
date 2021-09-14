package com.flightbooking.Data;

import com.flightbooking.Data.Airline;
import com.flightbooking.converter.ListToString;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class AirlineSchedule implements Serializable {

  private static final long serialVersionUID = 2L;

   @Id
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

    @OneToOne(targetEntity = AirlineSchedule.class)
    @JoinColumn(name="seat_id",referencedColumnName = "id")
    private Seats seats;

    @Override
    public String toString() {
        return "Airline" + "-" + airline.getAirlineNumber() + " of id " + id + " works on " + workingDays
        + " departs from " + fromPlace + " at " + startDateTime + " reaches " + toPlace +
                " at " + endDateTime;
    }
}
