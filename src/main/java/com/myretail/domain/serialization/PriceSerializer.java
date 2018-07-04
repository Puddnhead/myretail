package com.myretail.domain.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Serializes BigDecimal objects to a price format with 2 decimals rounded half up
 *
 * Ex:  33.33333 = 33.33
 *      33.555 = 33.56
 * Created by MVW on 7/3/2018.
 */
public class PriceSerializer extends JsonSerializer<BigDecimal> {

    @Override
    public void serialize(BigDecimal value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeString(value.setScale(2, RoundingMode.HALF_UP).toString());
    }
}