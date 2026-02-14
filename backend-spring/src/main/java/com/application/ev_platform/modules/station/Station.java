package com.application.ev_platform.modules.station;

import jakarta.persistence.*;
import org.locationtech.jts.geom.Point;

@Entity
@Table(name = "stations")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;

    @Column(columnDefinition = "geometry(Point, 4326)")
    private Point location;

    private Boolean active = true;

    // ===== Getters & Setters =====
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public Point getLocation() { return location; }
    public void setLocation(Point location) { this.location = location; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
