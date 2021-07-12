package com.vt.fish.repository;

import com.vt.fish.model.roadieresponse.EstimateResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EstimateResponseRepository extends MongoRepository<EstimateResponse,String> {
}
