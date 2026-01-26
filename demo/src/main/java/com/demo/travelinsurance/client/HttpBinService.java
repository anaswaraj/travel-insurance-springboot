package com.demo.travelinsurance.client;

import com.demo.travelinsurance.dto.HttpBinRequest;
import com.demo.travelinsurance.dto.HttpBinResponse;
import com.demo.travelinsurance.exception.PolicyCreationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


@Service
public class HttpBinService {

    private static final Logger logger =
            LoggerFactory.getLogger(HttpBinService.class);

    @Autowired
    private RestTemplate restTemplate;

    public HttpBinResponse callPostBinApi(HttpBinRequest request) throws PolicyCreationException {

        String url = "https://httpbin.org/post";
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<HttpBinRequest> entity =
                    new HttpEntity<>(request, headers);

            ResponseEntity<HttpBinResponse> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.POST,
                            entity,
                            HttpBinResponse.class
                    );
            logger.info("Response is " + response);
            if (response.getBody() != null) {
                HttpBinResponse httpBinResponsebody = response.getBody();
                return httpBinResponsebody;
            }
        }catch (HttpClientErrorException | HttpServerErrorException he){
            logger.error("Inside HttpServerErrorException:: "+ he.getMessage());
            throw new PolicyCreationException(he.getMessage(),he);
        } catch(Exception e){
            logger.error("Inside Exception:: "+ e.getMessage());
            throw new PolicyCreationException(e.getMessage(),e);
        }
        return null;
    }
}
