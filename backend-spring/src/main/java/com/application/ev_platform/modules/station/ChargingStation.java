package com.application.ev_platform.modules.station;

import com.application.ev_platform.shared.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.locationtech.jts.geom.Point;

@Entity
@Getter
@Setter
public class ChargingStation extends BaseEntity {

    private String name;

    // PostGIS geometry
    private Point location;
}
