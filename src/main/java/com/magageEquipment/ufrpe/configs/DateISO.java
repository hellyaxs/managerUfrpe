package com.magageEquipment.ufrpe.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;

@Configuration
public class DateISO {

    private static final String PATTERN_DATE= "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static LocalDateTimeSerializer LOCAL_DATE_TIME_SERIALIZER = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(PATTERN_DATE));

    @Bean
    @Primary
    public ObjectMapper objectMapper(){
        var javaModule = new JavaTimeModule();
        javaModule.addSerializer(LOCAL_DATE_TIME_SERIALIZER);
        return new ObjectMapper().registerModule(javaModule);
    }
}
