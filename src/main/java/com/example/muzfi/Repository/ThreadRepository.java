package com.example.muzfi.Repository;

import com.example.muzfi.Model.Thread;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThreadRepository extends MongoRepository<Thread,String> {
    List<Thread> findByTitleContainingIgnoreCase(String query);
}
