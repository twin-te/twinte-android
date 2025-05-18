package net.twinte.api.schoolcalendar.v1;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * The following error codes are not stated explicitly in the each rpc, but may be returned.
 *   - shared.InvalidArgument
 *   - shared.Unauthenticated
 *   - shared.Unauthorized
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.72.0)",
    comments = "Source: schoolcalendar/v1/service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class SchoolCalendarServiceGrpc {

  private SchoolCalendarServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "schoolcalendar.v1.SchoolCalendarService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateRequest,
      net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateResponse> getListEventsByDateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListEventsByDate",
      requestType = net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateRequest.class,
      responseType = net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateRequest,
      net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateResponse> getListEventsByDateMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateRequest, net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateResponse> getListEventsByDateMethod;
    if ((getListEventsByDateMethod = SchoolCalendarServiceGrpc.getListEventsByDateMethod) == null) {
      synchronized (SchoolCalendarServiceGrpc.class) {
        if ((getListEventsByDateMethod = SchoolCalendarServiceGrpc.getListEventsByDateMethod) == null) {
          SchoolCalendarServiceGrpc.getListEventsByDateMethod = getListEventsByDateMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateRequest, net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListEventsByDate"))
              .setSafe(true)
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getListEventsByDateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateRequest,
      net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateResponse> getGetModuleByDateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetModuleByDate",
      requestType = net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateRequest.class,
      responseType = net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateRequest,
      net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateResponse> getGetModuleByDateMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateRequest, net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateResponse> getGetModuleByDateMethod;
    if ((getGetModuleByDateMethod = SchoolCalendarServiceGrpc.getGetModuleByDateMethod) == null) {
      synchronized (SchoolCalendarServiceGrpc.class) {
        if ((getGetModuleByDateMethod = SchoolCalendarServiceGrpc.getGetModuleByDateMethod) == null) {
          SchoolCalendarServiceGrpc.getGetModuleByDateMethod = getGetModuleByDateMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateRequest, net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetModuleByDate"))
              .setSafe(true)
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getGetModuleByDateMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static SchoolCalendarServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SchoolCalendarServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SchoolCalendarServiceStub>() {
        @java.lang.Override
        public SchoolCalendarServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SchoolCalendarServiceStub(channel, callOptions);
        }
      };
    return SchoolCalendarServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static SchoolCalendarServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SchoolCalendarServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SchoolCalendarServiceBlockingV2Stub>() {
        @java.lang.Override
        public SchoolCalendarServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SchoolCalendarServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return SchoolCalendarServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static SchoolCalendarServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SchoolCalendarServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SchoolCalendarServiceBlockingStub>() {
        @java.lang.Override
        public SchoolCalendarServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SchoolCalendarServiceBlockingStub(channel, callOptions);
        }
      };
    return SchoolCalendarServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static SchoolCalendarServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<SchoolCalendarServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<SchoolCalendarServiceFutureStub>() {
        @java.lang.Override
        public SchoolCalendarServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new SchoolCalendarServiceFutureStub(channel, callOptions);
        }
      };
    return SchoolCalendarServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public interface AsyncService {

    /**
     */
    default void listEventsByDate(net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListEventsByDateMethod(), responseObserver);
    }

    /**
     */
    default void getModuleByDate(net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetModuleByDateMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service SchoolCalendarService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static abstract class SchoolCalendarServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return SchoolCalendarServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service SchoolCalendarService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static final class SchoolCalendarServiceStub
      extends io.grpc.stub.AbstractAsyncStub<SchoolCalendarServiceStub> {
    private SchoolCalendarServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SchoolCalendarServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SchoolCalendarServiceStub(channel, callOptions);
    }

    /**
     */
    public void listEventsByDate(net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListEventsByDateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getModuleByDate(net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetModuleByDateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service SchoolCalendarService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static final class SchoolCalendarServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<SchoolCalendarServiceBlockingV2Stub> {
    private SchoolCalendarServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SchoolCalendarServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SchoolCalendarServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateResponse listEventsByDate(net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListEventsByDateMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateResponse getModuleByDate(net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetModuleByDateMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service SchoolCalendarService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static final class SchoolCalendarServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<SchoolCalendarServiceBlockingStub> {
    private SchoolCalendarServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SchoolCalendarServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SchoolCalendarServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateResponse listEventsByDate(net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListEventsByDateMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateResponse getModuleByDate(net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetModuleByDateMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service SchoolCalendarService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static final class SchoolCalendarServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<SchoolCalendarServiceFutureStub> {
    private SchoolCalendarServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected SchoolCalendarServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new SchoolCalendarServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateResponse> listEventsByDate(
        net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListEventsByDateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateResponse> getModuleByDate(
        net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetModuleByDateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LIST_EVENTS_BY_DATE = 0;
  private static final int METHODID_GET_MODULE_BY_DATE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_LIST_EVENTS_BY_DATE:
          serviceImpl.listEventsByDate((net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateResponse>) responseObserver);
          break;
        case METHODID_GET_MODULE_BY_DATE:
          serviceImpl.getModuleByDate((net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getListEventsByDateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateRequest,
              net.twinte.api.schoolcalendar.v1.Service.ListEventsByDateResponse>(
                service, METHODID_LIST_EVENTS_BY_DATE)))
        .addMethod(
          getGetModuleByDateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateRequest,
              net.twinte.api.schoolcalendar.v1.Service.GetModuleByDateResponse>(
                service, METHODID_GET_MODULE_BY_DATE)))
        .build();
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (SchoolCalendarServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .addMethod(getListEventsByDateMethod())
              .addMethod(getGetModuleByDateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
