package com.flightbooking.data;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDateTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDateTime;
    private String fromPlace;
    private String toPlace;

    @Column
    @Convert(converter = ListToString.class)
    private List<String> workingDays;

 @OneToOne(targetEntity = Seats.class)
 @JoinColumn(name="seats_id",referencedColumnName = "id")
 private Seats seats;


    @ManyToOne(targetEntity = Airline.class)
    @JoinColumn(name="airline_number",referencedColumnName = "airlineNumber",
            nullable = true)
    private Airline airline;

    @Override
    public String toString() {
        return "Airline" + "-" + airline.getAirlineNumber() + " of id " + id + " works on " + workingDays
                + " departs from " + fromPlace + " at " + startDateTime + " reaches " + toPlace +
                " at " + endDateTime;
    }

}
