package com.vt.fish.repository;

import com.vt.fish.model.response.SubordinateResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SubordinateResponseRepository extends MongoRepository<SubordinateResponse,String> {
}
