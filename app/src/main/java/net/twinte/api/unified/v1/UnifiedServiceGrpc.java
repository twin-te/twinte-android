package net.twinte.api.unified.v1;

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
    comments = "Source: unified/v1/service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class UnifiedServiceGrpc {

  private UnifiedServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "unified.v1.UnifiedService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<net.twinte.api.unified.v1.Service.GetByDateRequest,
      net.twinte.api.unified.v1.Service.GetByDateResponse> getGetByDateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetByDate",
      requestType = net.twinte.api.unified.v1.Service.GetByDateRequest.class,
      responseType = net.twinte.api.unified.v1.Service.GetByDateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.unified.v1.Service.GetByDateRequest,
      net.twinte.api.unified.v1.Service.GetByDateResponse> getGetByDateMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.unified.v1.Service.GetByDateRequest, net.twinte.api.unified.v1.Service.GetByDateResponse> getGetByDateMethod;
    if ((getGetByDateMethod = UnifiedServiceGrpc.getGetByDateMethod) == null) {
      synchronized (UnifiedServiceGrpc.class) {
        if ((getGetByDateMethod = UnifiedServiceGrpc.getGetByDateMethod) == null) {
          UnifiedServiceGrpc.getGetByDateMethod = getGetByDateMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.unified.v1.Service.GetByDateRequest, net.twinte.api.unified.v1.Service.GetByDateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetByDate"))
              .setSafe(true)
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.unified.v1.Service.GetByDateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.unified.v1.Service.GetByDateResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getGetByDateMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UnifiedServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UnifiedServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UnifiedServiceStub>() {
        @java.lang.Override
        public UnifiedServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UnifiedServiceStub(channel, callOptions);
        }
      };
    return UnifiedServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static UnifiedServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UnifiedServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UnifiedServiceBlockingV2Stub>() {
        @java.lang.Override
        public UnifiedServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UnifiedServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return UnifiedServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UnifiedServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UnifiedServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UnifiedServiceBlockingStub>() {
        @java.lang.Override
        public UnifiedServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UnifiedServiceBlockingStub(channel, callOptions);
        }
      };
    return UnifiedServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UnifiedServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<UnifiedServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<UnifiedServiceFutureStub>() {
        @java.lang.Override
        public UnifiedServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new UnifiedServiceFutureStub(channel, callOptions);
        }
      };
    return UnifiedServiceFutureStub.newStub(factory, channel);
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
     * <pre>
     * GetByDate returns the resources related to the given date.
     * Only registered courses which will be held on the given date will be returned.
     * </pre>
     */
    default void getByDate(net.twinte.api.unified.v1.Service.GetByDateRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.unified.v1.Service.GetByDateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetByDateMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service UnifiedService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static abstract class UnifiedServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return UnifiedServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service UnifiedService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static final class UnifiedServiceStub
      extends io.grpc.stub.AbstractAsyncStub<UnifiedServiceStub> {
    private UnifiedServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UnifiedServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UnifiedServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * GetByDate returns the resources related to the given date.
     * Only registered courses which will be held on the given date will be returned.
     * </pre>
     */
    public void getByDate(net.twinte.api.unified.v1.Service.GetByDateRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.unified.v1.Service.GetByDateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetByDateMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service UnifiedService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static final class UnifiedServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<UnifiedServiceBlockingV2Stub> {
    private UnifiedServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UnifiedServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UnifiedServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     * <pre>
     * GetByDate returns the resources related to the given date.
     * Only registered courses which will be held on the given date will be returned.
     * </pre>
     */
    public net.twinte.api.unified.v1.Service.GetByDateResponse getByDate(net.twinte.api.unified.v1.Service.GetByDateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetByDateMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service UnifiedService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static final class UnifiedServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<UnifiedServiceBlockingStub> {
    private UnifiedServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UnifiedServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UnifiedServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * GetByDate returns the resources related to the given date.
     * Only registered courses which will be held on the given date will be returned.
     * </pre>
     */
    public net.twinte.api.unified.v1.Service.GetByDateResponse getByDate(net.twinte.api.unified.v1.Service.GetByDateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetByDateMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service UnifiedService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static final class UnifiedServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<UnifiedServiceFutureStub> {
    private UnifiedServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected UnifiedServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new UnifiedServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * GetByDate returns the resources related to the given date.
     * Only registered courses which will be held on the given date will be returned.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.unified.v1.Service.GetByDateResponse> getByDate(
        net.twinte.api.unified.v1.Service.GetByDateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetByDateMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_BY_DATE = 0;

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
        case METHODID_GET_BY_DATE:
          serviceImpl.getByDate((net.twinte.api.unified.v1.Service.GetByDateRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.unified.v1.Service.GetByDateResponse>) responseObserver);
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
          getGetByDateMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.unified.v1.Service.GetByDateRequest,
              net.twinte.api.unified.v1.Service.GetByDateResponse>(
                service, METHODID_GET_BY_DATE)))
        .build();
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (UnifiedServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .addMethod(getGetByDateMethod())
              .build();
        }
      }
    }
    return result;
  }
}
