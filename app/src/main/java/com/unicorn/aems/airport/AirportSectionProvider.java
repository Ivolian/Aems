package com.unicorn.aems.airport;

import com.unicorn.aems.airport.model.Airport;
import com.unicorn.aems.airport.model.AirportSection;
import com.unicorn.aems.app.dagger.App;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

@App
public class AirportSectionProvider {

    @Inject
    public AirportSectionProvider() {
    }

    public List<AirportSection> provide() {
        List<AirportSection> airportSections = new ArrayList<>();
        airportSections.add(new AirportSection(true, "常用"));
        airportSections.add(new AirportSection(new Airport("北京首都机场")));
        airportSections.add(new AirportSection(new Airport("上海浦东机场")));
        airportSections.add(new AirportSection(new Airport("广州白云机场")));
        airportSections.add(new AirportSection(new Airport("西安咸阳机场")));
        airportSections.add(new AirportSection(true, "常用"));
        airportSections.add(new AirportSection(new Airport("北京首都机场")));
        airportSections.add(new AirportSection(new Airport("上海浦东机场")));
        airportSections.add(new AirportSection(new Airport("广州白云机场")));
        airportSections.add(new AirportSection(new Airport("西安咸阳机场")));
        airportSections.add(new AirportSection(true, "常用"));
        airportSections.add(new AirportSection(new Airport("北京首都机场")));
        airportSections.add(new AirportSection(new Airport("上海浦东机场")));
        airportSections.add(new AirportSection(new Airport("广州白云机场")));
        airportSections.add(new AirportSection(new Airport("西安咸阳机场")));
        airportSections.add(new AirportSection(true, "常用"));
        airportSections.add(new AirportSection(new Airport("北京首都机场")));
        airportSections.add(new AirportSection(new Airport("上海浦东机场")));
        airportSections.add(new AirportSection(new Airport("广州白云机场")));
        airportSections.add(new AirportSection(new Airport("西安咸阳机场")));
        airportSections.add(new AirportSection(true, "常用"));
        airportSections.add(new AirportSection(new Airport("北京首都机场")));
        airportSections.add(new AirportSection(new Airport("上海浦东机场")));
        airportSections.add(new AirportSection(new Airport("广州白云机场")));
        airportSections.add(new AirportSection(new Airport("西安咸阳机场")));
        return airportSections;
    }

}
