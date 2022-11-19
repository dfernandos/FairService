package com.feirapp.fairService.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Fair")
public class Fair {

    @Id
    @GeneratedValue

    private int id;
    private String name;
    private String address;
    private String time;
    private String weekday;
    private String latitude;
    private String longitude;
}
