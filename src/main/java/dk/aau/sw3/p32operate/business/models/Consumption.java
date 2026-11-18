package dk.aau.sw3.p32operate.business.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Consumption {
    private LocalDateTime date;
    private double kwh;
    private double kr;
    private double min_kr;
}
