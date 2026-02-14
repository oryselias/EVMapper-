package com.application.ev_platform.modules.vehicle;

import com.application.ev_platform.shared.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Vehicle extends BaseEntity {

    private String model;
    private String registrationNumber;
}
