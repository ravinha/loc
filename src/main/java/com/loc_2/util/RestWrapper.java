package com.loc_2.util;

import com.loc_2.exceptions.RiotLimitException;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Created by Rafal on 2016-05-12.
 */
public class RestWrapper extends RestTemplate{
    @Override
    public <T> T getForObject(URI url, Class<T> responseType) throws RestClientException {
        try {
            return super.getForObject(url, responseType);
        } catch (HttpClientErrorException ex) {
            HttpStatus status = ex.getStatusCode();
            if (status != HttpStatus.TOO_MANY_REQUESTS) {
                throw new RiotLimitException();
            }
            throw ex;
        }
    }
}