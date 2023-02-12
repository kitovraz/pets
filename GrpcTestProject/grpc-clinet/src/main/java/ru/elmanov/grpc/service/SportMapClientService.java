package ru.elmanov.grpc.service;

import com.google.protobuf.Descriptors;
import com.google.protobuf.GeneratedMessageV3;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.apache.commons.collections4.IteratorUtils;
import org.springframework.stereotype.Service;
import ru.elmanov.grpc.sport.Person;
import ru.elmanov.grpc.sport.Sport;
import ru.elmanov.grpc.sport.SportMapGrpc;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class SportMapClientService {

    @GrpcClient("sport-map-service-sync")
    SportMapGrpc.SportMapBlockingStub synchronousClientStub;

    @GrpcClient("sport-map-service-async")
    SportMapGrpc.SportMapStub asynchronousClientStub;

    public List<Map<Descriptors.FieldDescriptor, Object>> getPersonsBySport(Long sportId) {
        Sport sport = Sport.newBuilder().setSportId(sportId).build();
        List<Person> people = IteratorUtils.toList(synchronousClientStub.getPersonsBySport(sport));
        return people.stream().map(GeneratedMessageV3::getAllFields).collect(Collectors.toList());
    }

    public List<Person> getPersonsBySport2(Long sportId) {
        Sport sport = Sport.newBuilder().setSportId(sportId).build();
        List<Person> people = IteratorUtils.toList(synchronousClientStub.getPersonsBySport(sport));
        return people;
    }

    public Set<Map<Descriptors.FieldDescriptor, Object>> getSportsByPersonIds(Set<Long> personIds) throws InterruptedException {
        Set<Map<Descriptors.FieldDescriptor, Object>> response = new HashSet<>();
        CountDownLatch latch = new CountDownLatch(1);
        StreamObserver<Person> streamObserver = asynchronousClientStub.getSportsByPersons(new StreamObserver<Sport>() {
            @Override
            public void onNext(Sport value) {
                response.add(value.getAllFields());
            }

            @Override
            public void onError(Throwable t) {
                latch.countDown();
            }

            @Override
            public void onCompleted() {
                latch.countDown();
            }
        });

        personIds.forEach(pId -> streamObserver.onNext(Person.newBuilder().setPersonId(pId).build()));
        streamObserver.onCompleted();
        boolean await = latch.await(1, TimeUnit.MINUTES);
        return await ? response : Collections.emptySet();
    }
}
