package ru.elmanov.grpc.sport;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: sport_map.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class SportMapGrpc {

  private SportMapGrpc() {}

  public static final String SERVICE_NAME = "SportMap";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ru.elmanov.grpc.sport.Person,
      ru.elmanov.grpc.sport.Sport> getGetSportMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getSport",
      requestType = ru.elmanov.grpc.sport.Person.class,
      responseType = ru.elmanov.grpc.sport.Sport.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<ru.elmanov.grpc.sport.Person,
      ru.elmanov.grpc.sport.Sport> getGetSportMethod() {
    io.grpc.MethodDescriptor<ru.elmanov.grpc.sport.Person, ru.elmanov.grpc.sport.Sport> getGetSportMethod;
    if ((getGetSportMethod = SportMapGrpc.getGetSportMethod) == null) {
      synchronized (SportMapGrpc.class) {
        if ((getGetSportMethod = SportMapGrpc.getGetSportMethod) == null) {
          SportMapGrpc.getGetSportMethod = getGetSportMethod =
              io.grpc.MethodDescriptor.<ru.elmanov.grpc.sport.Person, ru.elmanov.grpc.sport.Sport>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getSport"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.elmanov.grpc.sport.Person.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ru.elmanov.grpc.sport.Sport.getDefaultInstance()))
              .setSchemaDescriptor(new SportMapMethodDescriptorSupplier("getSport"))
              .build();
        }
      }
    }
    return getGetSportMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SportMapStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SportMapStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SportMapStub>() {
        @java.lang.Override
        public SportMapStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SportMapStub(channel, callOptions);
        }
      };
    return SportMapStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SportMapBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SportMapBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SportMapBlockingStub>() {
        @java.lang.Override
        public SportMapBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SportMapBlockingStub(channel, callOptions);
        }
      };
    return SportMapBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SportMapFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SportMapFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SportMapFutureStub>() {
        @java.lang.Override
        public SportMapFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SportMapFutureStub(channel, callOptions);
        }
      };
    return SportMapFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class SportMapImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<ru.elmanov.grpc.sport.Person> getSport(
        io.grpc.stub.StreamObserver<ru.elmanov.grpc.sport.Sport> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getGetSportMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetSportMethod(),
            io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
              new MethodHandlers<
                ru.elmanov.grpc.sport.Person,
                ru.elmanov.grpc.sport.Sport>(
                  this, METHODID_GET_SPORT)))
          .build();
    }
  }

  /**
   */
  public static final class SportMapStub extends io.grpc.stub.AbstractAsyncStub<SportMapStub> {
    private SportMapStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SportMapStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SportMapStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<ru.elmanov.grpc.sport.Person> getSport(
        io.grpc.stub.StreamObserver<ru.elmanov.grpc.sport.Sport> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getGetSportMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class SportMapBlockingStub extends io.grpc.stub.AbstractBlockingStub<SportMapBlockingStub> {
    private SportMapBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SportMapBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SportMapBlockingStub(channel, callOptions);
    }
  }

  /**
   */
  public static final class SportMapFutureStub extends io.grpc.stub.AbstractFutureStub<SportMapFutureStub> {
    private SportMapFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SportMapFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SportMapFutureStub(channel, callOptions);
    }
  }

  private static final int METHODID_GET_SPORT = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final SportMapImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(SportMapImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_SPORT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.getSport(
              (io.grpc.stub.StreamObserver<ru.elmanov.grpc.sport.Sport>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class SportMapBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    SportMapBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ru.elmanov.grpc.sport.SportMapProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("SportMap");
    }
  }

  private static final class SportMapFileDescriptorSupplier
      extends SportMapBaseDescriptorSupplier {
    SportMapFileDescriptorSupplier() {}
  }

  private static final class SportMapMethodDescriptorSupplier
      extends SportMapBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    SportMapMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SportMapGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new SportMapFileDescriptorSupplier())
              .addMethod(getGetSportMethod())
              .build();
        }
      }
    }
    return result;
  }
}
