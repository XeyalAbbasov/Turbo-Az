package com.khayal.turboaz.trimmer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class StringWithoutSpaceDeserializer extends StdDeserializer<String> {
    protected StringWithoutSpaceDeserializer(Class<String> vc) {
        super(vc);
    }

    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return p.getText() != null ? p.getText().trim() : null;
        //getText cevrilme anidaki setrin bir basha ozunu qaytarir
    }
}
