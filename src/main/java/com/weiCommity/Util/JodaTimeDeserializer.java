package com.weiCommity.Util;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import org.joda.time.*;

import java.io.IOException;
import java.lang.reflect.Type;


/**
 * @author javaer.cn
 *         Created on 2015-10-26 21:51
 */

public class JodaTimeDeserializer implements ObjectSerializer, ObjectDeserializer {
    public static final JodaTimeDeserializer instance = new JodaTimeDeserializer();

    public <T> T deserialze(DefaultJSONParser parser, Type type, Object fieldName) {
        JSONLexer lexer = parser.getLexer();
        if (lexer.token() == JSONToken.LITERAL_STRING) {
            String text = lexer.stringVal();
            lexer.nextToken();
            if (type == LocalDateTime.class) {
                return (T) LocalDateTime.parse(text);
            } else if (type == LocalDate.class) {
                return (T) LocalDate.parse(text);
            } else if (type == LocalTime.class) {
                return (T) LocalTime.parse(text);
            } else if (type == Period.class) {
                return (T) Period.parse(text);
            } else if (type == Duration.class) {
                return (T) Duration.parse(text);
            } else if (type == Instant.class) {
                return (T) Instant.parse(text);
            }
        } else {
            throw new UnsupportedOperationException();
        }
        return null;
    }

    public int getFastMatchToken() {
        return JSONToken.LITERAL_STRING;
    }


    @Override
    public void write(JSONSerializer serializer, Object object, Object o1, Type type, int i) throws IOException {
        SerializeWriter out = serializer.getWriter();
        if (object == null) {
            out.writeNull();
            return;
        }
        out.writeString(object.toString());
    }
}