package dk.aau.sw3.p32operate.dataAccess.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "electricity_consumption")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConsumptionEntity {

    @Id
    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "kwh", nullable = false)
    private double kwh;

    @Column(name = "kr_total", nullable = false)
    private double kr;

    @Column(name = "min_kr_total", nullable = false)
    private double min_kr_total;

    @PrePersist
    public void setDate(){
        this.date = LocalDateTime.now().with(LocalTime.MIN);
    }
}
