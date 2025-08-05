package org.example.environement.dto.travellogs;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class TravellogDtoStat {
    private double totalDistanceKm;
    private double totalEmissionsKg;
    private Map<String,Double> byMode;

    public TravellogDtoStat() {
        totalDistanceKm = 0;
        totalEmissionsKg = 0;
        byMode = new HashMap<>();
    }

    public void addTotalDistanceKm(double totalDistanceKm) {
        this.totalDistanceKm += totalDistanceKm;
    }

    public void addTotalEmissionsKg(double totalEmissionsKg) {
        this.totalEmissionsKg += totalEmissionsKg;
    }

    public void addMode(String mode,double distance) {
        this.byMode.put(mode,distance);
    }
}
