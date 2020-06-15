package com.github.wumrwds.ods.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.wumrwds.ods.util.DateTimeUtils;

import java.io.IOException;
import java.time.ZoneOffset;

/**
 * Json serializer for byte array.
 *
 * @author wumrwds
 */
public class DateSerializer extends JsonSerializer<String> {
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {

        gen.writeNumber(DateTimeUtils.parseDate(value).atStartOfDay(ZoneOffset.UTC).toInstant().toEpochMilli());
    }
}
