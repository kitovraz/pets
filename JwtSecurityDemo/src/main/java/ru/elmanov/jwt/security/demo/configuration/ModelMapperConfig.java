package ru.elmanov.jwt.security.demo.configuration;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.modelmapper.*;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STANDARD);
        modelMapper.getConfiguration().setDeepCopyEnabled(true);
//        Provider<LocalDate> localDateProvider = new AbstractProvider<LocalDate>() {
//            @Override
//            public LocalDate get() {
//                return LocalDate.now();
//            }
//        };
//
//        Converter<String, LocalDate> toStringDate = new AbstractConverter<String, LocalDate>() {
//            @Override
//            protected LocalDate convert(String source) {
//                DateTimeFormatter format = DateTimeFormatter. ISO_OFFSET_DATE_TIME;
//                LocalDate localDateTime = LocalDate.parse(source, format);
//                return localDateTime;
//            }
//        };
//
//        modelMapper.createTypeMap(String.class, LocalDate.class);
//        modelMapper.addConverter(toStringDate);
//        modelMapper.getTypeMap(String.class, LocalDate.class).setProvider(localDateProvider);

        return modelMapper;
    }

    public static void main(String[] args) {
        ModelMapper modelmapper = new ModelMapper();

        Provider<LocalDate> localDateProvider = new AbstractProvider<>() {
            @Override
            public LocalDate get() {
                return LocalDate.now();
            }
        };

        Converter<String, LocalDate> toStringDate = new AbstractConverter<>() {
            @Override
            protected LocalDate convert(String source) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate localDate = LocalDate.parse(source, format);
                return localDate;
            }
        };


        modelmapper.createTypeMap(String.class, LocalDate.class);
        modelmapper.addConverter(toStringDate);
        modelmapper.getTypeMap(String.class, LocalDate.class).setProvider(localDateProvider);

        String dateTest = "2000-09-27";
        LocalDate dateConverted = modelmapper.map(dateTest, LocalDate.class);

        System.out.println(dateConverted.toString());
    }
}
