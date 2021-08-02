package com.vt.fish.repository;

import com.vt.fish.model.roadieresponse.ShipmentResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ShipmentResponseRepository extends MongoRepository<ShipmentResponse,String> {
}
