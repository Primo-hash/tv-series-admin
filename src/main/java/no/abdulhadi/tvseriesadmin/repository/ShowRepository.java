package no.abdulhadi.tvseriesadmin.repository;

import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.ShowDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public interface ShowRepository extends MongoRepository<ShowDTO, String> {
    @Query("{'embedded.episodes.airdate': {$gte: ?0, $lte: ?1}}")
    List<ShowDTO> findByEmbeddedEpisodesAirdateBetween(Date start, Date end);

}
