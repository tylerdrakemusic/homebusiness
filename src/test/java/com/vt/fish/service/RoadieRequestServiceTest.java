package com.vt.fish.service;

import com.vt.fish.config.RoadieRequestServiceConfig;
import com.vt.fish.model.request.VibrantTropicalOrderRequest;
import com.vt.fish.model.roadierequest.EstimateRequest;
import com.vt.fish.model.roadierequest.RoadieAddress;
import com.vt.fish.model.roadierequest.ShipmentRequest;
import com.vt.fish.utility.DateUtility;
import com.vt.fish.utility.TestUtilities;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RoadieRequestServiceConfig.class})
public class RoadieRequestServiceTest {

    @Autowired
    private RoadieRequestServiceConfig roadieRequestServiceConfig;
    private RoadieRequestService roadieRequestService;

    @Before
    public void setup() {
        roadieRequestService = new RoadieRequestService(roadieRequestServiceConfig);
    }

    @Test
    public void vibrantTropicalOrderRequest_shouldProperlyBuildShipmentRequest() {
        VibrantTropicalOrderRequest expectedVibrantTropicalOrderRequest = TestUtilities.buildVibrantTropicalOrderRequestSample();
        ShipmentRequest actualShipmentRequest = roadieRequestService.buildShipmentRequest(expectedVibrantTropicalOrderRequest);

        assertEquals(actualShipmentRequest.getVibrantTropicalRequestId(), expectedVibrantTropicalOrderRequest.getVibrantTropicalRequestId());

        assertEquals(actualShipmentRequest.getDeliveryLocation().getRoadieContact().getName(), expectedVibrantTropicalOrderRequest.getShippingName());
        assertEquals(actualShipmentRequest.getDeliveryLocation().getRoadieContact().getPhone(), expectedVibrantTropicalOrderRequest.getShippingPhone());

        RoadieAddress actualDeliveryLocationAddress = actualShipmentRequest.getDeliveryLocation().getRoadieAddress();
        assertEquals(expectedVibrantTropicalOrderRequest.getShippingAddress(), actualDeliveryLocationAddress.getStreet1());
        assertEquals(expectedVibrantTropicalOrderRequest.getShippingAddress2(), actualDeliveryLocationAddress.getStreet2());
        assertEquals(expectedVibrantTropicalOrderRequest.getShippingCity(), actualDeliveryLocationAddress.getCity());
        assertEquals(expectedVibrantTropicalOrderRequest.getShippingState(), actualDeliveryLocationAddress.getState());
        assertEquals(expectedVibrantTropicalOrderRequest.getShippingZip(), actualDeliveryLocationAddress.getZip());

        RoadieAddress actualPickupLocationAddress = actualShipmentRequest.getPickupLocation().getRoadieAddress();
        assertEquals(roadieRequestServiceConfig.getPickupStreet(), actualPickupLocationAddress.getStreet1());
        assertEquals(roadieRequestServiceConfig.getPickupCity(), actualPickupLocationAddress.getCity());
        assertEquals(roadieRequestServiceConfig.getPickupState(), actualPickupLocationAddress.getState());
        assertEquals(roadieRequestServiceConfig.getPickupZip(), actualPickupLocationAddress.getZip());

        assertEquals(
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
                        .format(DateUtility.addMinutesToJavaUtilDate(expectedVibrantTropicalOrderRequest.getTimeStamp(), 1)),
                actualShipmentRequest.getPickupAfter());
        assertEquals(actualShipmentRequest.getRoadieTimeWindow().getStart(), actualShipmentRequest.getPickupAfter());
        assertEquals(
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
                        .format(DateUtility.addHoursToJavaUtilDate(expectedVibrantTropicalOrderRequest.getTimeStamp(), 2)),
                actualShipmentRequest.getRoadieTimeWindow().getEnd());

        //todo: Verify products array depends on externalize config and bag split logic
    }

    @Test
    public void vibrantTropicalOrderRequest_shouldProperlyBuildEstimateRequest() {
        VibrantTropicalOrderRequest expectedVibrantTropicalOrderRequest = TestUtilities.buildVibrantTropicalOrderRequestSample();
        EstimateRequest actualEstimateRequest = roadieRequestService.buildEstimateRequest(expectedVibrantTropicalOrderRequest);

        assertEquals(actualEstimateRequest.getCorrelationId(), expectedVibrantTropicalOrderRequest.getCorrelationId());
        assertEquals(actualEstimateRequest.getVibrantTropicalRequestId(), expectedVibrantTropicalOrderRequest.getVibrantTropicalRequestId());

        assertEquals(actualEstimateRequest.getDeliveryLocation().getRoadieContact().getName(), expectedVibrantTropicalOrderRequest.getShippingName());
        assertEquals(actualEstimateRequest.getDeliveryLocation().getRoadieContact().getPhone(), expectedVibrantTropicalOrderRequest.getShippingPhone());

        RoadieAddress actualDeliveryLocationAddress = actualEstimateRequest.getDeliveryLocation().getRoadieAddress();
        assertEquals(expectedVibrantTropicalOrderRequest.getShippingAddress(), actualDeliveryLocationAddress.getStreet1());
        assertEquals(expectedVibrantTropicalOrderRequest.getShippingAddress2(), actualDeliveryLocationAddress.getStreet2());
        assertEquals(expectedVibrantTropicalOrderRequest.getShippingCity(), actualDeliveryLocationAddress.getCity());
        assertEquals(expectedVibrantTropicalOrderRequest.getShippingState(), actualDeliveryLocationAddress.getState());
        assertEquals(expectedVibrantTropicalOrderRequest.getShippingZip(), actualDeliveryLocationAddress.getZip());

        RoadieAddress actualPickupLocationAddress = actualEstimateRequest.getPickupLocation().getRoadieAddress();
        assertEquals(roadieRequestServiceConfig.getPickupStreet(), actualPickupLocationAddress.getStreet1());
        assertEquals(roadieRequestServiceConfig.getPickupCity(), actualPickupLocationAddress.getCity());
        assertEquals(roadieRequestServiceConfig.getPickupState(), actualPickupLocationAddress.getState());
        assertEquals(roadieRequestServiceConfig.getPickupZip(), actualPickupLocationAddress.getZip());

        assertEquals(
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
                        .format(DateUtility.addMinutesToJavaUtilDate(expectedVibrantTropicalOrderRequest.getTimeStamp(), 1)),
                actualEstimateRequest.getPickupAfter());
        assertEquals(actualEstimateRequest.getRoadieTimeWindow().getStart(), actualEstimateRequest.getPickupAfter());
        assertEquals(
                new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
                        .format(DateUtility.addHoursToJavaUtilDate(expectedVibrantTropicalOrderRequest.getTimeStamp(), 2)),
                actualEstimateRequest.getRoadieTimeWindow().getEnd());

        //todo: Verify products array depends on externalize config and bag split logic
    }


}
