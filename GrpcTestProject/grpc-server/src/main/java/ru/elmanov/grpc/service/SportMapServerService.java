package ru.elmanov.grpc.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import ru.elmanov.grpc.db.RuntimeDb;
import ru.elmanov.grpc.sport.Person;
import ru.elmanov.grpc.sport.Sport;
import ru.elmanov.grpc.sport.SportMapGrpc;

import java.util.Optional;

@GrpcService
public class SportMapServerService extends SportMapGrpc.SportMapImplBase {

    @Override
    public StreamObserver<Person> getSportsByPersons(StreamObserver<Sport> responseObserver) {
        return new StreamObserver<Person>() {
            @Override
            public void onNext(Person value) {
                //Get one person by one by personId
                    Optional<Person> person = RuntimeDb.getPersons().stream()
                        .filter(p -> p.getPersonId() == value.getPersonId())
                        .findAny();
                //Iterable get sport by person's sportId and ::onNext it
                person.ifPresentOrElse(p ->
                                p.getSportIdsList().forEach(sportId ->
                                        RuntimeDb.getSports().stream()
                                                .filter(sport -> sport.getSportId() == sportId)
                                                .forEach(responseObserver::onNext)),
                        () -> responseObserver.onNext(null));
            }

            @Override
            public void onError(Throwable t) {
                responseObserver.onError(t);
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public void getPersonsBySport(Sport request, StreamObserver<Person> responseObserver) {
        RuntimeDb.getPersons().stream()
                .filter(person -> person.getSportIdsList().contains(request.getSportId()))
                .forEach(responseObserver::onNext);
        responseObserver.onCompleted();
    }
}
