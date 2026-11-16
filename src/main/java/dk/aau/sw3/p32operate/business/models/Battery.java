package dk.aau.sw3.p32operate.business.models;

import lombok.Getter;
import lombok.Setter;

public class Battery implements Actuator {
    @Getter
    private Status status;
    @Setter @Getter
    private int batteryLevel;

    public Battery() {
        this.status = Status.IDLE;
        this.batteryLevel = 0;
    }

    @Override
    public void start() {
        status = Status.ACTIVE;
    }

    @Override
    public void stop() {
        status = Status.IDLE;
    }

    public void startCharge() {
        status = Status.BATTERYCHARGING;
    }

    public void stopCharge() {
        status = Status.IDLE;
    }

    static void main() {
        Battery battery = new Battery();

        battery.getBatteryLevel();
        System.out.println(battery.batteryLevel);
        battery.setBatteryLevel(37);
        System.out.println(battery.batteryLevel);


    }

}
