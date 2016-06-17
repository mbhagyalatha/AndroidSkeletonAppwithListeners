package com.imaginnovatestructureapp.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class JSONDateDeserializer implements JsonDeserializer<Date> {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

    public Date deserialize(JsonElement json, Type typeOfT,
                            JsonDeserializationContext context) throws JsonParseException {
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date dt = null;
        try {
            dt = new Date(df.parse(json.getAsJsonPrimitive().getAsString())
                    .getTime());
        } catch (ParseException e) {

            e.printStackTrace();
        }
        return dt;
    }
}