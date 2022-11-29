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
    private double latitude;
    private double longitude;

    public Fair (String name, String address, String time, String weekday, double lat, double lng){
        this.name = name;
        this.address = address;
        this.time = time;
        this.weekday = weekday;
        this.latitude = lat;
        this.latitude = lng;
    }
}
