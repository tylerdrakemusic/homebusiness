package com.vt.fish.repository;

import com.vt.fish.model.request.VibrantTropicalOrderRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VibrantTropicalOrderRequestRepository extends MongoRepository <VibrantTropicalOrderRequest, String> {
}
