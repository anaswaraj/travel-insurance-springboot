package com.demo.travelinsurance.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HttpBinResponse {

        private Map<String, Object> args;

        private String data;

        private Map<String, Object> files;

        private Map<String, Object> form;

        private Map<String, String> headers;

        private HttpBinRequest json;

        private String origin;

        private String url;

        private String policyNumber;
}

