package org.example.environement.service;

import org.example.environement.dto.observation.ObservationDtoResponse;
import org.example.environement.dto.travellogs.TravellogDtoReceive;
import org.example.environement.dto.travellogs.TravellogDtoResponse;
import org.example.environement.dto.travellogs.TravellogDtoStat;
import org.example.environement.entity.Observation;
import org.example.environement.entity.TravelLog;
import org.example.environement.exception.NotFoundException;
import org.example.environement.repository.TravellogRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TravelLogService {
    private final TravellogRepository travellogRepository;
    public TravelLogService(TravellogRepository travellogRepository) {
        this.travellogRepository = travellogRepository;
    }

    public List<TravellogDtoResponse> get(){
        return convertList(travellogRepository.findAll());
    }

    public TravellogDtoStat getStat(Long id){
        List<TravelLog> travelLogs = travellogRepository.findByObservationId(id);
        if(travelLogs.isEmpty()){
            throw new NotFoundException("yépatrouvé : "+id);
        }
        TravellogDtoStat travellogDtoStat = new TravellogDtoStat();
        for(TravelLog travelLog : travelLogs){
            travellogDtoStat.addTotalDistanceKm(travelLog.getDistanceKm());
            travellogDtoStat.addTotalEmissionsKg(travelLog.getEstimatedCo2Kg());
            travellogDtoStat.addMode(String.valueOf(travelLog.getMode()), travelLog.getDistanceKm());
        }
        return travellogDtoStat;
    }

    private List<TravellogDtoResponse> convertList (List<TravelLog> travelLogs){
        return travelLogs.stream().map(TravelLog::entityToDto).collect(Collectors.toList());
    }

    public TravellogDtoResponse create(TravellogDtoReceive request) {
        return travellogRepository.save(request.dtoToEntity()).entityToDto();
    }
}
