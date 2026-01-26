package com.demo.travelinsurance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class HttpBinPostResponse {

    private PolicyRequest json;
    private String url;
    private String origin;

}
