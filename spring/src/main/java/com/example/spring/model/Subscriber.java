package com.example.spring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "subscriber")

public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subscriber_seq")
    @SequenceGenerator(name = "subscriber_seq", sequenceName = "SUBSCRIBER_SEQ", allocationSize = 1)
    @Column(name = "SUBSC_ID")
    private Long id;

    @Column(name = "SUBSC_NAME")
    private String name;

    @Column(name = "SUBSC_SURNAME")
    private String surname;

    @Column(name = "MSISDN")
    private String MSISDN;

    @Column(name = "TARIFF_ID")
    private Integer tariff_id;

    @Column(name = "START_DATE")
    private Date start_date;
}
