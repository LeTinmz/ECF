package org.example.environement.repository;

import org.example.environement.entity.TravelLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravellogRepository extends JpaRepository<TravelLog,Long> {
    public List<TravelLog> findTravellogByObservation_Id (Long id);

//    @Query("select t from Travellog t where t.observation.observerName = ?1 and t.observation.observationDate > ?2")
//    public List<Travellog> findTravellogByUserForLastMonth (String user, LocalDate date);

    List<TravelLog> findByObservationId(Long observationId);
}
