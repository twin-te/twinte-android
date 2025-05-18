package net.twinte.api.auth.v1;

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
    comments = "Source: auth/v1/service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class AuthServiceGrpc {

  private AuthServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "auth.v1.AuthService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<net.twinte.api.auth.v1.Service.GetMeRequest,
      net.twinte.api.auth.v1.Service.GetMeResponse> getGetMeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetMe",
      requestType = net.twinte.api.auth.v1.Service.GetMeRequest.class,
      responseType = net.twinte.api.auth.v1.Service.GetMeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.auth.v1.Service.GetMeRequest,
      net.twinte.api.auth.v1.Service.GetMeResponse> getGetMeMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.auth.v1.Service.GetMeRequest, net.twinte.api.auth.v1.Service.GetMeResponse> getGetMeMethod;
    if ((getGetMeMethod = AuthServiceGrpc.getGetMeMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getGetMeMethod = AuthServiceGrpc.getGetMeMethod) == null) {
          AuthServiceGrpc.getGetMeMethod = getGetMeMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.auth.v1.Service.GetMeRequest, net.twinte.api.auth.v1.Service.GetMeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetMe"))
              .setSafe(true)
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.auth.v1.Service.GetMeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.auth.v1.Service.GetMeResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getGetMeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.twinte.api.auth.v1.Service.DeleteUserAuthenticationRequest,
      net.twinte.api.auth.v1.Service.DeleteUserAuthenticationResponse> getDeleteUserAuthenticationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteUserAuthentication",
      requestType = net.twinte.api.auth.v1.Service.DeleteUserAuthenticationRequest.class,
      responseType = net.twinte.api.auth.v1.Service.DeleteUserAuthenticationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.auth.v1.Service.DeleteUserAuthenticationRequest,
      net.twinte.api.auth.v1.Service.DeleteUserAuthenticationResponse> getDeleteUserAuthenticationMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.auth.v1.Service.DeleteUserAuthenticationRequest, net.twinte.api.auth.v1.Service.DeleteUserAuthenticationResponse> getDeleteUserAuthenticationMethod;
    if ((getDeleteUserAuthenticationMethod = AuthServiceGrpc.getDeleteUserAuthenticationMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getDeleteUserAuthenticationMethod = AuthServiceGrpc.getDeleteUserAuthenticationMethod) == null) {
          AuthServiceGrpc.getDeleteUserAuthenticationMethod = getDeleteUserAuthenticationMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.auth.v1.Service.DeleteUserAuthenticationRequest, net.twinte.api.auth.v1.Service.DeleteUserAuthenticationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteUserAuthentication"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.auth.v1.Service.DeleteUserAuthenticationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.auth.v1.Service.DeleteUserAuthenticationResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getDeleteUserAuthenticationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.twinte.api.auth.v1.Service.DeleteAccountRequest,
      net.twinte.api.auth.v1.Service.DeleteAccountResponse> getDeleteAccountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteAccount",
      requestType = net.twinte.api.auth.v1.Service.DeleteAccountRequest.class,
      responseType = net.twinte.api.auth.v1.Service.DeleteAccountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.auth.v1.Service.DeleteAccountRequest,
      net.twinte.api.auth.v1.Service.DeleteAccountResponse> getDeleteAccountMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.auth.v1.Service.DeleteAccountRequest, net.twinte.api.auth.v1.Service.DeleteAccountResponse> getDeleteAccountMethod;
    if ((getDeleteAccountMethod = AuthServiceGrpc.getDeleteAccountMethod) == null) {
      synchronized (AuthServiceGrpc.class) {
        if ((getDeleteAccountMethod = AuthServiceGrpc.getDeleteAccountMethod) == null) {
          AuthServiceGrpc.getDeleteAccountMethod = getDeleteAccountMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.auth.v1.Service.DeleteAccountRequest, net.twinte.api.auth.v1.Service.DeleteAccountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteAccount"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.auth.v1.Service.DeleteAccountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.auth.v1.Service.DeleteAccountResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getDeleteAccountMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AuthServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthServiceStub>() {
        @java.lang.Override
        public AuthServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthServiceStub(channel, callOptions);
        }
      };
    return AuthServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static AuthServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthServiceBlockingV2Stub>() {
        @java.lang.Override
        public AuthServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return AuthServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AuthServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthServiceBlockingStub>() {
        @java.lang.Override
        public AuthServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthServiceBlockingStub(channel, callOptions);
        }
      };
    return AuthServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AuthServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<AuthServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<AuthServiceFutureStub>() {
        @java.lang.Override
        public AuthServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new AuthServiceFutureStub(channel, callOptions);
        }
      };
    return AuthServiceFutureStub.newStub(factory, channel);
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
    default void getMe(net.twinte.api.auth.v1.Service.GetMeRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.auth.v1.Service.GetMeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetMeMethod(), responseObserver);
    }

    /**
     */
    default void deleteUserAuthentication(net.twinte.api.auth.v1.Service.DeleteUserAuthenticationRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.auth.v1.Service.DeleteUserAuthenticationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteUserAuthenticationMethod(), responseObserver);
    }

    /**
     */
    default void deleteAccount(net.twinte.api.auth.v1.Service.DeleteAccountRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.auth.v1.Service.DeleteAccountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteAccountMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service AuthService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static abstract class AuthServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return AuthServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service AuthService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static final class AuthServiceStub
      extends io.grpc.stub.AbstractAsyncStub<AuthServiceStub> {
    private AuthServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthServiceStub(channel, callOptions);
    }

    /**
     */
    public void getMe(net.twinte.api.auth.v1.Service.GetMeRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.auth.v1.Service.GetMeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetMeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteUserAuthentication(net.twinte.api.auth.v1.Service.DeleteUserAuthenticationRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.auth.v1.Service.DeleteUserAuthenticationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteUserAuthenticationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteAccount(net.twinte.api.auth.v1.Service.DeleteAccountRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.auth.v1.Service.DeleteAccountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteAccountMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service AuthService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static final class AuthServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<AuthServiceBlockingV2Stub> {
    private AuthServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public net.twinte.api.auth.v1.Service.GetMeResponse getMe(net.twinte.api.auth.v1.Service.GetMeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetMeMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.auth.v1.Service.DeleteUserAuthenticationResponse deleteUserAuthentication(net.twinte.api.auth.v1.Service.DeleteUserAuthenticationRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteUserAuthenticationMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.auth.v1.Service.DeleteAccountResponse deleteAccount(net.twinte.api.auth.v1.Service.DeleteAccountRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteAccountMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service AuthService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static final class AuthServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<AuthServiceBlockingStub> {
    private AuthServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public net.twinte.api.auth.v1.Service.GetMeResponse getMe(net.twinte.api.auth.v1.Service.GetMeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetMeMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.auth.v1.Service.DeleteUserAuthenticationResponse deleteUserAuthentication(net.twinte.api.auth.v1.Service.DeleteUserAuthenticationRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteUserAuthenticationMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.auth.v1.Service.DeleteAccountResponse deleteAccount(net.twinte.api.auth.v1.Service.DeleteAccountRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteAccountMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service AuthService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static final class AuthServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<AuthServiceFutureStub> {
    private AuthServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AuthServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new AuthServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.auth.v1.Service.GetMeResponse> getMe(
        net.twinte.api.auth.v1.Service.GetMeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetMeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.auth.v1.Service.DeleteUserAuthenticationResponse> deleteUserAuthentication(
        net.twinte.api.auth.v1.Service.DeleteUserAuthenticationRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteUserAuthenticationMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.auth.v1.Service.DeleteAccountResponse> deleteAccount(
        net.twinte.api.auth.v1.Service.DeleteAccountRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteAccountMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_ME = 0;
  private static final int METHODID_DELETE_USER_AUTHENTICATION = 1;
  private static final int METHODID_DELETE_ACCOUNT = 2;

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
        case METHODID_GET_ME:
          serviceImpl.getMe((net.twinte.api.auth.v1.Service.GetMeRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.auth.v1.Service.GetMeResponse>) responseObserver);
          break;
        case METHODID_DELETE_USER_AUTHENTICATION:
          serviceImpl.deleteUserAuthentication((net.twinte.api.auth.v1.Service.DeleteUserAuthenticationRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.auth.v1.Service.DeleteUserAuthenticationResponse>) responseObserver);
          break;
        case METHODID_DELETE_ACCOUNT:
          serviceImpl.deleteAccount((net.twinte.api.auth.v1.Service.DeleteAccountRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.auth.v1.Service.DeleteAccountResponse>) responseObserver);
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
          getGetMeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.auth.v1.Service.GetMeRequest,
              net.twinte.api.auth.v1.Service.GetMeResponse>(
                service, METHODID_GET_ME)))
        .addMethod(
          getDeleteUserAuthenticationMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.auth.v1.Service.DeleteUserAuthenticationRequest,
              net.twinte.api.auth.v1.Service.DeleteUserAuthenticationResponse>(
                service, METHODID_DELETE_USER_AUTHENTICATION)))
        .addMethod(
          getDeleteAccountMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.auth.v1.Service.DeleteAccountRequest,
              net.twinte.api.auth.v1.Service.DeleteAccountResponse>(
                service, METHODID_DELETE_ACCOUNT)))
        .build();
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AuthServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .addMethod(getGetMeMethod())
              .addMethod(getDeleteUserAuthenticationMethod())
              .addMethod(getDeleteAccountMethod())
              .build();
        }
      }
    }
    return result;
  }
}
