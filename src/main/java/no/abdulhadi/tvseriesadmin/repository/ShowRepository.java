package no.abdulhadi.tvseriesadmin.repository;

import no.abdulhadi.tvseriesadmin.model.dto.tvmaze.ShowDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface ShowRepository extends MongoRepository<ShowDTO, String> {

}
