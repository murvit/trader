package com.vmurashkin.tradermvc.controller;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by OG_ML on 18.08.2015.
 */
public class GetShareDataImpl implements GetShareData {
    @Override
    public String getData(String ticker) {

        String request = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20" +
                "from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22" +
                ticker +
                "%22)%20&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpRequest = new HttpGet(request);

        try {
            HttpResponse response = httpClient.execute(httpRequest);
            HttpEntity entity = response.getEntity();
            return EntityUtils.toString(entity);

        } catch (IOException e) {
            // do nothing
        }
        return "";
    }
}
