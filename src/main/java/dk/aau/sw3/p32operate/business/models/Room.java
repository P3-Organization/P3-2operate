package dk.aau.sw3.p32operate.business.models;

import lombok.Getter;

public class Room {
    private Pump pump;
    @Getter
    private double temperature;
    @Getter
    private double humidity;

    public Room(Pump pump, double temperature, double humidity) {
        this.pump = pump;
        this.temperature = temperature;
        this.humidity = humidity;
    }

}
