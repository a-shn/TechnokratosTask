package ru.itis.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.itis.models.Cv;

@Repository
public interface CvRepository extends MongoRepository<Cv, String> {
}
