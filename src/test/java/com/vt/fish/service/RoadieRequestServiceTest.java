package com.vt.fish.service;

import com.vt.fish.model.request.VibrantTropicalOrderRequest;
import com.vt.fish.model.roadierequest.EstimateRequest;
import com.vt.fish.model.roadierequest.RoadieAddress;
import com.vt.fish.model.roadierequest.RoadieLocation;
import com.vt.fish.utility.DateUtility;
import com.vt.fish.utility.TestUtilities;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

public class RoadieRequestServiceTest {

    private RoadieRequestService roadieRequestService;

    @Before
    public void setup() {
        roadieRequestService = new RoadieRequestService();
    }

    @Test
    public void vibrantTropicalOrderRequest_shouldProperlyBuildEstimateRequest() {
        VibrantTropicalOrderRequest vibrantTropicalOrderRequest = TestUtilities.buildVibrantTropicalOrderRequestSample();
        EstimateRequest estimateRequest = roadieRequestService.buildEstimateRequest(vibrantTropicalOrderRequest);

        assertEquals(estimateRequest.getCorrelationId(), vibrantTropicalOrderRequest.getCorrelationId());
        assertEquals(estimateRequest.getVibrantTropicalRequestId(), vibrantTropicalOrderRequest.getVibrantTropicalRequestId());

        assertEquals(estimateRequest.getDeliveryLocation().getRoadieContact().getName(), vibrantTropicalOrderRequest.getShippingName());
        assertEquals(estimateRequest.getDeliveryLocation().getRoadieContact().getPhone(), vibrantTropicalOrderRequest.getShippingPhone());

        RoadieAddress deliveryLocationAddress = estimateRequest.getDeliveryLocation().getRoadieAddress();
        assertEquals(deliveryLocationAddress.getStreet1(), vibrantTropicalOrderRequest.getShippingAddress());
        assertEquals(deliveryLocationAddress.getStreet2(), vibrantTropicalOrderRequest.getShippingAddress2());
        assertEquals(deliveryLocationAddress.getCity(), vibrantTropicalOrderRequest.getShippingCity());
        assertEquals(deliveryLocationAddress.getState(), vibrantTropicalOrderRequest.getShippingState());
        assertEquals(deliveryLocationAddress.getZip(), vibrantTropicalOrderRequest.getShippingZip());

        //todo: Verify pickup location depends on externalize config

        assertEquals(estimateRequest.getPickupAfter(), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
                .format(DateUtility.addMinutesToJavaUtilDate(vibrantTropicalOrderRequest.getTimeStamp(), 1)));
        assertEquals(estimateRequest.getPickupAfter(), estimateRequest.getRoadieTimeWindow().getStart());
        assertEquals(estimateRequest.getRoadieTimeWindow().getEnd(), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
                .format(DateUtility.addHoursToJavaUtilDate(vibrantTropicalOrderRequest.getTimeStamp(), 2)));

        //todo: Verify products array depends on externalize config and bag split logic
    }


}
