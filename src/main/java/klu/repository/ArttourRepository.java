package klu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.transaction.Transactional;
import klu.model.ArtFair;

public interface ArttourRepository extends JpaRepository<ArtFair, Long>
{
	
	
	// Find all art fairs
    List<ArtFair> findAll();

    // Find art fairs by a specific attribute, for example, by theme
    //List<ArtFair> findByTheme(String theme);

    List<ArtFair> findByCuratorId(String curatorId);

    
    
    @Transactional
    @Modifying
    @Query("UPDATE ArtFair a SET a.name = :name, a.tdescription = :tdescription, a.startDate = :startDate, " +
           "a.endDate = :endDate, a.location = :location, a.capacity = :capacity, a.entryFee = :entryFee, " +
           "a.theme = :theme WHERE a.tourid = :tourid")
    int updateAllTours(@Param("tourid") Long tourid,
                       @Param("name") String name,
                       @Param("tdescription") String tdescription,
                       @Param("startDate") String startDate,
                       @Param("endDate") String endDate,
                       @Param("location") String location,
                       @Param("capacity") Integer capacity,
                       @Param("entryFee") Double entryFee,
                       @Param("theme") String theme);

    @Transactional
    @Modifying
    @Query("DELETE FROM ArtFair a WHERE a.tourid = :tourId")
    int deleteTourById(@Param("tourId") Long tourId);



    
    

    

}
