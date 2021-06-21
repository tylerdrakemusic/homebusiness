package com.vt.fish.service;

import com.vt.fish.model.request.VibrantTropicalOrderRequest;
import com.vt.fish.repository.VibrantTropicalOrderRequestRepository;
import org.springframework.stereotype.Service;

import java.util.GregorianCalendar;

@Service
public record DatabaseService(
        VibrantTropicalOrderRequestRepository vibrantTropicalOrderRequestRepository) {

    public void saveVibrantTropicalOrderRequest(VibrantTropicalOrderRequest vibrantTropicalOrderRequest) {
        vibrantTropicalOrderRequest.setTimeStamp(GregorianCalendar.getInstance().getTime());
        vibrantTropicalOrderRequestRepository.save(vibrantTropicalOrderRequest);
        System.out.println("request saved");
    }
}
