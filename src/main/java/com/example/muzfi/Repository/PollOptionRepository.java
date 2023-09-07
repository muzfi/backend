package com.example.muzfi.Repository;

import com.example.muzfi.Model.Post.PollOption;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PollOptionRepository extends MongoRepository<PollOption, String> {
}
