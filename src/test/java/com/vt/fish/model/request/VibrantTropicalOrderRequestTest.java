package com.vt.fish.model.request;

import com.vt.fish.utility.TestUtilities;
import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class VibrantTropicalOrderRequestTest {

    @Test
    public void vibrantTropicalOrderRequest_shouldConstructRandomRequestId() {
        VibrantTropicalOrderRequest vibrantTropicalOrderRequest = new VibrantTropicalOrderRequest();
        assertTrue(Pattern.matches("([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})", vibrantTropicalOrderRequest.getVibrantTropicalRequestId()));
    }

    @Test
    public void vibrantTropicalOrderRequest_onNullArgument_shouldSetCorrelationId_(){
        VibrantTropicalOrderRequest vibrantTropicalOrderRequest = new VibrantTropicalOrderRequest();
        vibrantTropicalOrderRequest.setCorrelationId(null);
        assertTrue(Pattern.matches("([a-f0-9]{8}(-[a-f0-9]{4}){4}[a-f0-9]{8})", vibrantTropicalOrderRequest.getCorrelationId()));
    }

    @Test
    public void vibrantTropicalOrderRequest_shouldReturnCorrectTotalOrderPrice(){
        VibrantTropicalOrderRequest vibrantTropicalOrderRequest = TestUtilities.buildVibrantTropicalOrderRequestSample();
        assertEquals(15,vibrantTropicalOrderRequest.getTotalOrderPrice(),0);

    }
}
