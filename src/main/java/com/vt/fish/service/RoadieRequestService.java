package com.vt.fish.service;

import com.vt.fish.model.request.Product;
import com.vt.fish.model.request.VibrantTropicalOrderRequest;
import com.vt.fish.model.roadierequest.*;
import com.vt.fish.model.roadieresponse.EstimateResponse;
import com.vt.fish.model.roadieresponse.RoadieErrorResponse;
import com.vt.fish.utility.DateUtility;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.net.URI;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

@Component
public class RoadieRequestService {

    private static final String LIVE_TROPICAL_FISH = "Live Tropical Fish";
    private static final String HOME_STREET = "10292 Tall Oaks Circle";
    private static final String HOME_CITY = "Parker";
    private static final String HOME_STATE = "CO";
    private static final String HOME_ZIP = "80134";
    private static final String HOME_CONTACT_NAME = "Tyler James Drake";
    private static final String HOME_PHONE_NUMBER = "3038150107";

    private static final String AUTHORIZATION_TYPE = "Bearer ";
    private static final String ROADIE_SANDBOX_URL = "https://connect-sandbox.roadie.com";
    private  static final String ROADIE_SANDBOX_API_KEY = "6b09744affd183c29e8d699ec5917085b41142ca";

    private static final String ESTIMATE_PATH = "/v1/estimates";

    public EstimateResponse makeEstimateRequest(EstimateRequest estimateRequest){

        Mono<EstimateResponse> estimateResponseMono = WebClient.create()
                .post()
                .uri( URI.create(ROADIE_SANDBOX_URL+ESTIMATE_PATH))
                .body(BodyInserters.fromValue( estimateRequest ) )
                .accept( MediaType.APPLICATION_JSON )
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, AUTHORIZATION_TYPE + ROADIE_SANDBOX_API_KEY)
                .exchange()
                .timeout(Duration.ofSeconds(5))
                .flatMap( clientResponse -> {
                    //Error handling
                    if ( clientResponse.statusCode()== HttpStatus.BAD_REQUEST) {
                        return clientResponse.bodyToMono(RoadieErrorResponse.class).flatMap(
                                roadieErrorResponse1 -> Mono.error(new RuntimeException(roadieErrorResponse1.toString()))
                        );
                    }
                    return clientResponse.bodyToMono( EstimateResponse.class );
                } );
        return  estimateResponseMono.block();
    }

    public EstimateRequest buildEstimateRequest(VibrantTropicalOrderRequest vibrantTropicalOrderRequest){
        ArrayList<RoadieItem> roadieItemArrayList = new ArrayList<>();
        for(Product product:vibrantTropicalOrderRequest.getProducts()){
            roadieItemArrayList.add(new RoadieItem((LIVE_TROPICAL_FISH),
                    product.getProductName(), product.getDollars(), 4, 2, 8,1, 1));
        }

        RoadieAddress pickupAddress = new RoadieAddress("Vibrant Tropical Home","1",HOME_STREET,null,HOME_CITY,HOME_STATE,HOME_ZIP, null,null);
        RoadieContact tylerContact = new RoadieContact(HOME_CONTACT_NAME,HOME_PHONE_NUMBER);
        RoadieLocation pickupLocation = new RoadieLocation(pickupAddress,tylerContact);

        RoadieAddress deliveryAddress = new RoadieAddress(vibrantTropicalOrderRequest.getShippingName(),null,vibrantTropicalOrderRequest.getShippingAddress(),vibrantTropicalOrderRequest.getShippingAddress2(), vibrantTropicalOrderRequest.getShippingCity(), vibrantTropicalOrderRequest.getShippingState(), vibrantTropicalOrderRequest.getShippingZip(), null,null);
        RoadieContact deliveryContact = new RoadieContact(vibrantTropicalOrderRequest.getShippingName(),vibrantTropicalOrderRequest.getShippingPhone());
        RoadieLocation deliveryLocation = new RoadieLocation(deliveryAddress,deliveryContact);

        String pickupAfter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
                .format(DateUtility.addMinutesToJavaUtilDate(vibrantTropicalOrderRequest.getTimeStamp(),1));
        String end = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
                .format(DateUtility.addHoursToJavaUtilDate(vibrantTropicalOrderRequest.getTimeStamp(),2));
        RoadieTimeWindow roadieTimeWindow = new RoadieTimeWindow(pickupAfter,end);

        return new EstimateRequest(roadieItemArrayList,pickupLocation,deliveryLocation,pickupAfter, roadieTimeWindow);
    }


}
