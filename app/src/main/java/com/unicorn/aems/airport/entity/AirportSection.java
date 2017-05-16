package com.unicorn.aems.airport.entity;

import com.chad.library.adapter.base.entity.SectionEntity;

public class AirportSection extends SectionEntity<Airport> {

    public AirportSection(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public AirportSection(Airport airport) {
        super(airport);
    }

}
