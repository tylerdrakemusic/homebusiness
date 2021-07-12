package com.vt.fish.repository;

import com.vt.fish.model.roadierequest.EstimateRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EstimateRequestRepository extends MongoRepository <EstimateRequest, String> {
}
