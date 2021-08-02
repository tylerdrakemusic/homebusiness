package com.vt.fish.repository;

import com.vt.fish.model.roadierequest.ShipmentRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShipmentRequestRepository extends MongoRepository<ShipmentRequest,String> {
}
