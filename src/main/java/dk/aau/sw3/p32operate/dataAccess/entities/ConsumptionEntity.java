package dk.aau.sw3.p32operate.dataAccess.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

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
        if(this.date == null){
            this.date = LocalDateTime.now().with(LocalTime.MIN);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ConsumptionEntity entity = (ConsumptionEntity) o;
        return Double.compare(kwh, entity.kwh) == 0 && Double.compare(kr, entity.kr) == 0 && Double.compare(min_kr_total, entity.min_kr_total) == 0 && Objects.equals(date, entity.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, kwh, kr, min_kr_total);
    }
}
