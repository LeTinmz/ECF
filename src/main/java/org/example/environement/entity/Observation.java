package org.example.environement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.environement.dto.observation.ObservationDtoResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Observation {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private String observerName;
    @Column(nullable = false)
    private String location;
    @Column(nullable = false)
    private Double latitude,longitude;
    @Column(nullable = false)
    private LocalDate observationDate;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "specie_id")
    private Specie specie;

    @OneToMany(mappedBy = "observation", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<TravelLog> travellogs;


    public ObservationDtoResponse entityToDto (){
        return ObservationDtoResponse.builder()
                .id(this.getId())
                .observerName(this.getObserverName())
                .location(this.getLocation())
                .latitude(this.getLatitude())
                .longitude(this.getLongitude())
                .observationDate(this.getObservationDate())
                .comment(this.getComment())
                .specie(this.getSpecie().entityToDto())
                .travellogs(this.getTravellogs().stream().map(TravelLog::entityToDto).collect(Collectors.toList()))
                .build();
    }
}
