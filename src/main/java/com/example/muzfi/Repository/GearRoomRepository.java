package com.example.muzfi.Repository;

import com.example.muzfi.Model.GearRoom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GearRoomRepository extends MongoRepository<GearRoom, String> {
    List<GearRoom> findAllByUserId(Integer userId);

    List<GearRoom> findAllByOrderByCreatedDateDesc();

}
