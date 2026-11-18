package dk.aau.sw3.p32operate.business.models;

public class Pump implements Actuator {
    private Status status;

    public Pump() {
        this.status = Status.IDLE;
    }

    @Override
    public void start() {
        status = Status.ACTIVE;
    }

    @Override
    public void stop() {
        status = Status.IDLE;
    }

    public Status getStatus() {return status;}
}
