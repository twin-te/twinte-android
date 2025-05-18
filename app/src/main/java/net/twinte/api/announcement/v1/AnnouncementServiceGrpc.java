package net.twinte.api.announcement.v1;

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
    comments = "Source: announcement/v1/service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AnnouncementServiceGrpc {

  private AnnouncementServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "announcement.v1.AnnouncementService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<net.twinte.api.announcement.v1.Service.ListAnnouncementsRequest,
      net.twinte.api.announcement.v1.Service.ListAnnouncementsResponse> getListAnnouncementsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListAnnouncements",
      requestType = net.twinte.api.announcement.v1.Service.ListAnnouncementsRequest.class,
      responseType = net.twinte.api.announcement.v1.Service.ListAnnouncementsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.announcement.v1.Service.ListAnnouncementsRequest,
      net.twinte.api.announcement.v1.Service.ListAnnouncementsResponse> getListAnnouncementsMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.announcement.v1.Service.ListAnnouncementsRequest, net.twinte.api.announcement.v1.Service.ListAnnouncementsResponse> getListAnnouncementsMethod;
    if ((getListAnnouncementsMethod = AnnouncementServiceGrpc.getListAnnouncementsMethod) == null) {
      synchronized (AnnouncementServiceGrpc.class) {
        if ((getListAnnouncementsMethod = AnnouncementServiceGrpc.getListAnnouncementsMethod) == null) {
          AnnouncementServiceGrpc.getListAnnouncementsMethod = getListAnnouncementsMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.announcement.v1.Service.ListAnnouncementsRequest, net.twinte.api.announcement.v1.Service.ListAnnouncementsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListAnnouncements"))
              .setSafe(true)
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.announcement.v1.Service.ListAnnouncementsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.announcement.v1.Service.ListAnnouncementsResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getListAnnouncementsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.twinte.api.announcement.v1.Service.ReadAnnouncementsRequest,
      net.twinte.api.announcement.v1.Service.ReadAnnouncementsResponse> getReadAnnouncementsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReadAnnouncements",
      requestType = net.twinte.api.announcement.v1.Service.ReadAnnouncementsRequest.class,
      responseType = net.twinte.api.announcement.v1.Service.ReadAnnouncementsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.announcement.v1.Service.ReadAnnouncementsRequest,
      net.twinte.api.announcement.v1.Service.ReadAnnouncementsResponse> getReadAnnouncementsMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.announcement.v1.Service.ReadAnnouncementsRequest, net.twinte.api.announcement.v1.Service.ReadAnnouncementsResponse> getReadAnnouncementsMethod;
    if ((getReadAnnouncementsMethod = AnnouncementServiceGrpc.getReadAnnouncementsMethod) == null) {
      synchronized (AnnouncementServiceGrpc.class) {
        if ((getReadAnnouncementsMethod = AnnouncementServiceGrpc.getReadAnnouncementsMethod) == null) {
          AnnouncementServiceGrpc.getReadAnnouncementsMethod = getReadAnnouncementsMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.announcement.v1.Service.ReadAnnouncementsRequest, net.twinte.api.announcement.v1.Service.ReadAnnouncementsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReadAnnouncements"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.announcement.v1.Service.ReadAnnouncementsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.announcement.v1.Service.ReadAnnouncementsResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getReadAnnouncementsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AnnouncementServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AnnouncementServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AnnouncementServiceStub>() {
        @java.lang.Override
        public AnnouncementServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AnnouncementServiceStub(channel, callOptions);
        }
      };
    return AnnouncementServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static AnnouncementServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AnnouncementServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AnnouncementServiceBlockingV2Stub>() {
        @java.lang.Override
        public AnnouncementServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AnnouncementServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return AnnouncementServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AnnouncementServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AnnouncementServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AnnouncementServiceBlockingStub>() {
        @java.lang.Override
        public AnnouncementServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AnnouncementServiceBlockingStub(channel, callOptions);
        }
      };
    return AnnouncementServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AnnouncementServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AnnouncementServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AnnouncementServiceFutureStub>() {
        @java.lang.Override
        public AnnouncementServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AnnouncementServiceFutureStub(channel, callOptions);
        }
      };
    return AnnouncementServiceFutureStub.newStub(factory, channel);
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
    default void listAnnouncements(net.twinte.api.announcement.v1.Service.ListAnnouncementsRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.announcement.v1.Service.ListAnnouncementsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListAnnouncementsMethod(), responseObserver);
    }

    /**
     */
    default void readAnnouncements(net.twinte.api.announcement.v1.Service.ReadAnnouncementsRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.announcement.v1.Service.ReadAnnouncementsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReadAnnouncementsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service AnnouncementService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static abstract class AnnouncementServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return AnnouncementServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service AnnouncementService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static final class AnnouncementServiceStub
      extends io.grpc.stub.AbstractAsyncStub<AnnouncementServiceStub> {
    private AnnouncementServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AnnouncementServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AnnouncementServiceStub(channel, callOptions);
    }

    /**
     */
    public void listAnnouncements(net.twinte.api.announcement.v1.Service.ListAnnouncementsRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.announcement.v1.Service.ListAnnouncementsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListAnnouncementsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void readAnnouncements(net.twinte.api.announcement.v1.Service.ReadAnnouncementsRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.announcement.v1.Service.ReadAnnouncementsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReadAnnouncementsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service AnnouncementService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static final class AnnouncementServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<AnnouncementServiceBlockingV2Stub> {
    private AnnouncementServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AnnouncementServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AnnouncementServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public net.twinte.api.announcement.v1.Service.ListAnnouncementsResponse listAnnouncements(net.twinte.api.announcement.v1.Service.ListAnnouncementsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListAnnouncementsMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.announcement.v1.Service.ReadAnnouncementsResponse readAnnouncements(net.twinte.api.announcement.v1.Service.ReadAnnouncementsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReadAnnouncementsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service AnnouncementService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static final class AnnouncementServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AnnouncementServiceBlockingStub> {
    private AnnouncementServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AnnouncementServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AnnouncementServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public net.twinte.api.announcement.v1.Service.ListAnnouncementsResponse listAnnouncements(net.twinte.api.announcement.v1.Service.ListAnnouncementsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListAnnouncementsMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.announcement.v1.Service.ReadAnnouncementsResponse readAnnouncements(net.twinte.api.announcement.v1.Service.ReadAnnouncementsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReadAnnouncementsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service AnnouncementService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static final class AnnouncementServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<AnnouncementServiceFutureStub> {
    private AnnouncementServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AnnouncementServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AnnouncementServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.announcement.v1.Service.ListAnnouncementsResponse> listAnnouncements(
        net.twinte.api.announcement.v1.Service.ListAnnouncementsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListAnnouncementsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.announcement.v1.Service.ReadAnnouncementsResponse> readAnnouncements(
        net.twinte.api.announcement.v1.Service.ReadAnnouncementsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReadAnnouncementsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LIST_ANNOUNCEMENTS = 0;
  private static final int METHODID_READ_ANNOUNCEMENTS = 1;

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
        case METHODID_LIST_ANNOUNCEMENTS:
          serviceImpl.listAnnouncements((net.twinte.api.announcement.v1.Service.ListAnnouncementsRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.announcement.v1.Service.ListAnnouncementsResponse>) responseObserver);
          break;
        case METHODID_READ_ANNOUNCEMENTS:
          serviceImpl.readAnnouncements((net.twinte.api.announcement.v1.Service.ReadAnnouncementsRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.announcement.v1.Service.ReadAnnouncementsResponse>) responseObserver);
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
          getListAnnouncementsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.announcement.v1.Service.ListAnnouncementsRequest,
              net.twinte.api.announcement.v1.Service.ListAnnouncementsResponse>(
                service, METHODID_LIST_ANNOUNCEMENTS)))
        .addMethod(
          getReadAnnouncementsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.announcement.v1.Service.ReadAnnouncementsRequest,
              net.twinte.api.announcement.v1.Service.ReadAnnouncementsResponse>(
                service, METHODID_READ_ANNOUNCEMENTS)))
        .build();
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AnnouncementServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .addMethod(getListAnnouncementsMethod())
              .addMethod(getReadAnnouncementsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
