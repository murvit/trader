package com.vmurashkin.tradermvc.controller;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.vmurashkin.tradermvc.model.Share;

/**
 * Created by OG_ML on 18.08.2015.
 */

public class ParsingJSONImpl implements ParsingJSON {
    Share share;
    @Override
    public Share parse(String json) {
        JsonElement jelement = new JsonParser().parse(json);
        JsonObject jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("query");
        jobject = jobject.getAsJsonObject("results");
        jobject = jobject.getAsJsonObject("quote");
        String ticker = jobject.getAsJsonPrimitive("symbol").getAsString();
        String name = jobject.getAsJsonPrimitive("Name").getAsString();
        double ask = jobject.getAsJsonPrimitive("Ask").getAsDouble();
        double bid = jobject.getAsJsonPrimitive("Bid").getAsDouble();
        double yearLow = jobject.getAsJsonPrimitive("YearLow").getAsDouble();
        double yearHigh = jobject.getAsJsonPrimitive("YearHigh").getAsDouble();
        String marketCapitalization = jobject.getAsJsonPrimitive("MarketCapitalization").getAsString();
        double oneYearTargetPrice = jobject.getAsJsonPrimitive("OneyrTargetPrice").getAsDouble();
 //       share = new Share (ticker, name, ask, bid, yearLow, yearHigh, marketCapitalization, oneYearTargetPrice);
        return share;
    }
}
