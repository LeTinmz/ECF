package org.example.environement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.environement.dto.observation.ObservationDtoResponse;
import org.example.environement.dto.travellogs.TravellogDtoResponse;
import org.example.environement.dto.travellogs.TravellogDtoStat;
import org.example.environement.entity.enums.TravelMode;

import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TravelLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


@ManyToOne
@JoinColumn(name = "observation_id")
private Observation observation;
private double distanceKm;
private TravelMode mode;
private double estimatedCo2Kg;

    public TravellogDtoResponse entityToDto() {
        return TravellogDtoResponse.builder()
                .id(this.getId())
                .distanceKm(this.getDistanceKm())
                .estimatedCo2Kg(this.getEstimatedCo2Kg())
                .mode(String.valueOf(this.getMode()))
                .build();
    }

    public void calculateCO2() {
        double facteurEmission;

        switch (this.mode) {
            case WALKING:
            case BIKE:
                facteurEmission = 0.0;
                break;
            case CAR:
                facteurEmission = 0.22;
                break;
            case BUS:
                facteurEmission = 0.11;
                break;
            case TRAIN:
                facteurEmission = 0.03;
                break;
            case PLANE:
                facteurEmission = 0.259;
                break;
            default:
                throw new IllegalArgumentException("yéconépa  " + this.mode);
        }

        this.estimatedCo2Kg = this.distanceKm * facteurEmission;
    }


}
