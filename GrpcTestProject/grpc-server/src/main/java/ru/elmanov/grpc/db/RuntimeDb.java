package ru.elmanov.grpc.db;

import com.google.common.collect.Lists;
import ru.elmanov.grpc.sport.Person;
import ru.elmanov.grpc.sport.Sport;

import java.util.ArrayList;
import java.util.List;

public class RuntimeDb {
    public static List<Sport> getSports() {
        return new ArrayList<>(){{
            add(Sport.newBuilder().setSportId(1).setName("Sambo").build());
            add(Sport.newBuilder().setSportId(2).setName("Judo").build());
            add(Sport.newBuilder().setSportId(3).setName("Basketball").build());
            add(Sport.newBuilder().setSportId(4).setName("Football").build());
            add(Sport.newBuilder().setSportId(5).setName("Chess").build());
            add(Sport.newBuilder().setSportId(6).setName("Fitness").build());
        }};
    }

    public static List<Person> getPersons() {
        return new ArrayList<>(){{
            add(Person.newBuilder().setPersonId(1)
//                    .setSex(Sex.MALE)
                    .addAllSportIds(Lists.newArrayList(1L, 2L))
                    .build());
            add(Person.newBuilder().setPersonId(2)
//                    .setSex(Sex.FEMALE)
                    .addAllSportIds(Lists.newArrayList(3L, 1L))
                    .build());
            add(Person.newBuilder().setPersonId(3)
//                    .setSex(Sex.MALE)
                    .addAllSportIds(Lists.newArrayList(5L))
                    .build());
            add(Person.newBuilder().setPersonId(4)
//                    .setSex(Sex.FEMALE)
                    .addAllSportIds(Lists.newArrayList(4L, 6L))
                    .build());
        }};
    }
}
