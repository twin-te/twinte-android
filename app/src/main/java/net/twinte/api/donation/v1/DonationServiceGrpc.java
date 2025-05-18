package net.twinte.api.donation.v1;

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
    comments = "Source: donation/v1/service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class DonationServiceGrpc {

  private DonationServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "donation.v1.DonationService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionRequest,
      net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionResponse> getCreateOneTimeCheckoutSessionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateOneTimeCheckoutSession",
      requestType = net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionRequest.class,
      responseType = net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionRequest,
      net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionResponse> getCreateOneTimeCheckoutSessionMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionRequest, net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionResponse> getCreateOneTimeCheckoutSessionMethod;
    if ((getCreateOneTimeCheckoutSessionMethod = DonationServiceGrpc.getCreateOneTimeCheckoutSessionMethod) == null) {
      synchronized (DonationServiceGrpc.class) {
        if ((getCreateOneTimeCheckoutSessionMethod = DonationServiceGrpc.getCreateOneTimeCheckoutSessionMethod) == null) {
          DonationServiceGrpc.getCreateOneTimeCheckoutSessionMethod = getCreateOneTimeCheckoutSessionMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionRequest, net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateOneTimeCheckoutSession"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getCreateOneTimeCheckoutSessionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionRequest,
      net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionResponse> getCreateSubscriptionCheckoutSessionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateSubscriptionCheckoutSession",
      requestType = net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionRequest.class,
      responseType = net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionRequest,
      net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionResponse> getCreateSubscriptionCheckoutSessionMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionRequest, net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionResponse> getCreateSubscriptionCheckoutSessionMethod;
    if ((getCreateSubscriptionCheckoutSessionMethod = DonationServiceGrpc.getCreateSubscriptionCheckoutSessionMethod) == null) {
      synchronized (DonationServiceGrpc.class) {
        if ((getCreateSubscriptionCheckoutSessionMethod = DonationServiceGrpc.getCreateSubscriptionCheckoutSessionMethod) == null) {
          DonationServiceGrpc.getCreateSubscriptionCheckoutSessionMethod = getCreateSubscriptionCheckoutSessionMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionRequest, net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateSubscriptionCheckoutSession"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getCreateSubscriptionCheckoutSessionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.GetPaymentUserRequest,
      net.twinte.api.donation.v1.Service.GetPaymentUserResponse> getGetPaymentUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetPaymentUser",
      requestType = net.twinte.api.donation.v1.Service.GetPaymentUserRequest.class,
      responseType = net.twinte.api.donation.v1.Service.GetPaymentUserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.GetPaymentUserRequest,
      net.twinte.api.donation.v1.Service.GetPaymentUserResponse> getGetPaymentUserMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.GetPaymentUserRequest, net.twinte.api.donation.v1.Service.GetPaymentUserResponse> getGetPaymentUserMethod;
    if ((getGetPaymentUserMethod = DonationServiceGrpc.getGetPaymentUserMethod) == null) {
      synchronized (DonationServiceGrpc.class) {
        if ((getGetPaymentUserMethod = DonationServiceGrpc.getGetPaymentUserMethod) == null) {
          DonationServiceGrpc.getGetPaymentUserMethod = getGetPaymentUserMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.donation.v1.Service.GetPaymentUserRequest, net.twinte.api.donation.v1.Service.GetPaymentUserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetPaymentUser"))
              .setSafe(true)
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.donation.v1.Service.GetPaymentUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.donation.v1.Service.GetPaymentUserResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getGetPaymentUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.UpdatePaymentUserRequest,
      net.twinte.api.donation.v1.Service.UpdatePaymentUserResponse> getUpdatePaymentUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdatePaymentUser",
      requestType = net.twinte.api.donation.v1.Service.UpdatePaymentUserRequest.class,
      responseType = net.twinte.api.donation.v1.Service.UpdatePaymentUserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.UpdatePaymentUserRequest,
      net.twinte.api.donation.v1.Service.UpdatePaymentUserResponse> getUpdatePaymentUserMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.UpdatePaymentUserRequest, net.twinte.api.donation.v1.Service.UpdatePaymentUserResponse> getUpdatePaymentUserMethod;
    if ((getUpdatePaymentUserMethod = DonationServiceGrpc.getUpdatePaymentUserMethod) == null) {
      synchronized (DonationServiceGrpc.class) {
        if ((getUpdatePaymentUserMethod = DonationServiceGrpc.getUpdatePaymentUserMethod) == null) {
          DonationServiceGrpc.getUpdatePaymentUserMethod = getUpdatePaymentUserMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.donation.v1.Service.UpdatePaymentUserRequest, net.twinte.api.donation.v1.Service.UpdatePaymentUserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdatePaymentUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.donation.v1.Service.UpdatePaymentUserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.donation.v1.Service.UpdatePaymentUserResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getUpdatePaymentUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.ListPaymentHistoriesRequest,
      net.twinte.api.donation.v1.Service.ListPaymentHistoriesResponse> getListPaymentHistoriesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListPaymentHistories",
      requestType = net.twinte.api.donation.v1.Service.ListPaymentHistoriesRequest.class,
      responseType = net.twinte.api.donation.v1.Service.ListPaymentHistoriesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.ListPaymentHistoriesRequest,
      net.twinte.api.donation.v1.Service.ListPaymentHistoriesResponse> getListPaymentHistoriesMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.ListPaymentHistoriesRequest, net.twinte.api.donation.v1.Service.ListPaymentHistoriesResponse> getListPaymentHistoriesMethod;
    if ((getListPaymentHistoriesMethod = DonationServiceGrpc.getListPaymentHistoriesMethod) == null) {
      synchronized (DonationServiceGrpc.class) {
        if ((getListPaymentHistoriesMethod = DonationServiceGrpc.getListPaymentHistoriesMethod) == null) {
          DonationServiceGrpc.getListPaymentHistoriesMethod = getListPaymentHistoriesMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.donation.v1.Service.ListPaymentHistoriesRequest, net.twinte.api.donation.v1.Service.ListPaymentHistoriesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListPaymentHistories"))
              .setSafe(true)
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.donation.v1.Service.ListPaymentHistoriesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.donation.v1.Service.ListPaymentHistoriesResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getListPaymentHistoriesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.ListSubscriptionPlansRequest,
      net.twinte.api.donation.v1.Service.ListSubscriptionPlansResponse> getListSubscriptionPlansMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListSubscriptionPlans",
      requestType = net.twinte.api.donation.v1.Service.ListSubscriptionPlansRequest.class,
      responseType = net.twinte.api.donation.v1.Service.ListSubscriptionPlansResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.ListSubscriptionPlansRequest,
      net.twinte.api.donation.v1.Service.ListSubscriptionPlansResponse> getListSubscriptionPlansMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.ListSubscriptionPlansRequest, net.twinte.api.donation.v1.Service.ListSubscriptionPlansResponse> getListSubscriptionPlansMethod;
    if ((getListSubscriptionPlansMethod = DonationServiceGrpc.getListSubscriptionPlansMethod) == null) {
      synchronized (DonationServiceGrpc.class) {
        if ((getListSubscriptionPlansMethod = DonationServiceGrpc.getListSubscriptionPlansMethod) == null) {
          DonationServiceGrpc.getListSubscriptionPlansMethod = getListSubscriptionPlansMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.donation.v1.Service.ListSubscriptionPlansRequest, net.twinte.api.donation.v1.Service.ListSubscriptionPlansResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListSubscriptionPlans"))
              .setSafe(true)
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.donation.v1.Service.ListSubscriptionPlansRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.donation.v1.Service.ListSubscriptionPlansResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getListSubscriptionPlansMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.GetActiveSubscriptionRequest,
      net.twinte.api.donation.v1.Service.GetActiveSubscriptionResponse> getGetActiveSubscriptionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetActiveSubscription",
      requestType = net.twinte.api.donation.v1.Service.GetActiveSubscriptionRequest.class,
      responseType = net.twinte.api.donation.v1.Service.GetActiveSubscriptionResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.GetActiveSubscriptionRequest,
      net.twinte.api.donation.v1.Service.GetActiveSubscriptionResponse> getGetActiveSubscriptionMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.GetActiveSubscriptionRequest, net.twinte.api.donation.v1.Service.GetActiveSubscriptionResponse> getGetActiveSubscriptionMethod;
    if ((getGetActiveSubscriptionMethod = DonationServiceGrpc.getGetActiveSubscriptionMethod) == null) {
      synchronized (DonationServiceGrpc.class) {
        if ((getGetActiveSubscriptionMethod = DonationServiceGrpc.getGetActiveSubscriptionMethod) == null) {
          DonationServiceGrpc.getGetActiveSubscriptionMethod = getGetActiveSubscriptionMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.donation.v1.Service.GetActiveSubscriptionRequest, net.twinte.api.donation.v1.Service.GetActiveSubscriptionResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetActiveSubscription"))
              .setSafe(true)
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.donation.v1.Service.GetActiveSubscriptionRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.donation.v1.Service.GetActiveSubscriptionResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getGetActiveSubscriptionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.UnsubscribeRequest,
      net.twinte.api.donation.v1.Service.UnsubscribeResponse> getUnsubscribeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Unsubscribe",
      requestType = net.twinte.api.donation.v1.Service.UnsubscribeRequest.class,
      responseType = net.twinte.api.donation.v1.Service.UnsubscribeResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.UnsubscribeRequest,
      net.twinte.api.donation.v1.Service.UnsubscribeResponse> getUnsubscribeMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.UnsubscribeRequest, net.twinte.api.donation.v1.Service.UnsubscribeResponse> getUnsubscribeMethod;
    if ((getUnsubscribeMethod = DonationServiceGrpc.getUnsubscribeMethod) == null) {
      synchronized (DonationServiceGrpc.class) {
        if ((getUnsubscribeMethod = DonationServiceGrpc.getUnsubscribeMethod) == null) {
          DonationServiceGrpc.getUnsubscribeMethod = getUnsubscribeMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.donation.v1.Service.UnsubscribeRequest, net.twinte.api.donation.v1.Service.UnsubscribeResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Unsubscribe"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.donation.v1.Service.UnsubscribeRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.donation.v1.Service.UnsubscribeResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getUnsubscribeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.GetTotalAmountRequest,
      net.twinte.api.donation.v1.Service.GetTotalAmountResponse> getGetTotalAmountMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetTotalAmount",
      requestType = net.twinte.api.donation.v1.Service.GetTotalAmountRequest.class,
      responseType = net.twinte.api.donation.v1.Service.GetTotalAmountResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.GetTotalAmountRequest,
      net.twinte.api.donation.v1.Service.GetTotalAmountResponse> getGetTotalAmountMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.GetTotalAmountRequest, net.twinte.api.donation.v1.Service.GetTotalAmountResponse> getGetTotalAmountMethod;
    if ((getGetTotalAmountMethod = DonationServiceGrpc.getGetTotalAmountMethod) == null) {
      synchronized (DonationServiceGrpc.class) {
        if ((getGetTotalAmountMethod = DonationServiceGrpc.getGetTotalAmountMethod) == null) {
          DonationServiceGrpc.getGetTotalAmountMethod = getGetTotalAmountMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.donation.v1.Service.GetTotalAmountRequest, net.twinte.api.donation.v1.Service.GetTotalAmountResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetTotalAmount"))
              .setSafe(true)
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.donation.v1.Service.GetTotalAmountRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.donation.v1.Service.GetTotalAmountResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getGetTotalAmountMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.ListContributorsRequest,
      net.twinte.api.donation.v1.Service.ListContributorsResponse> getListContributorsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListContributors",
      requestType = net.twinte.api.donation.v1.Service.ListContributorsRequest.class,
      responseType = net.twinte.api.donation.v1.Service.ListContributorsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.ListContributorsRequest,
      net.twinte.api.donation.v1.Service.ListContributorsResponse> getListContributorsMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.donation.v1.Service.ListContributorsRequest, net.twinte.api.donation.v1.Service.ListContributorsResponse> getListContributorsMethod;
    if ((getListContributorsMethod = DonationServiceGrpc.getListContributorsMethod) == null) {
      synchronized (DonationServiceGrpc.class) {
        if ((getListContributorsMethod = DonationServiceGrpc.getListContributorsMethod) == null) {
          DonationServiceGrpc.getListContributorsMethod = getListContributorsMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.donation.v1.Service.ListContributorsRequest, net.twinte.api.donation.v1.Service.ListContributorsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListContributors"))
              .setSafe(true)
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.donation.v1.Service.ListContributorsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.donation.v1.Service.ListContributorsResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getListContributorsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DonationServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DonationServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DonationServiceStub>() {
        @java.lang.Override
        public DonationServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DonationServiceStub(channel, callOptions);
        }
      };
    return DonationServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static DonationServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DonationServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DonationServiceBlockingV2Stub>() {
        @java.lang.Override
        public DonationServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DonationServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return DonationServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DonationServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DonationServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DonationServiceBlockingStub>() {
        @java.lang.Override
        public DonationServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DonationServiceBlockingStub(channel, callOptions);
        }
      };
    return DonationServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DonationServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DonationServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DonationServiceFutureStub>() {
        @java.lang.Override
        public DonationServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DonationServiceFutureStub(channel, callOptions);
        }
      };
    return DonationServiceFutureStub.newStub(factory, channel);
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
    default void createOneTimeCheckoutSession(net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateOneTimeCheckoutSessionMethod(), responseObserver);
    }

    /**
     */
    default void createSubscriptionCheckoutSession(net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateSubscriptionCheckoutSessionMethod(), responseObserver);
    }

    /**
     */
    default void getPaymentUser(net.twinte.api.donation.v1.Service.GetPaymentUserRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.GetPaymentUserResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetPaymentUserMethod(), responseObserver);
    }

    /**
     */
    default void updatePaymentUser(net.twinte.api.donation.v1.Service.UpdatePaymentUserRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.UpdatePaymentUserResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdatePaymentUserMethod(), responseObserver);
    }

    /**
     */
    default void listPaymentHistories(net.twinte.api.donation.v1.Service.ListPaymentHistoriesRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.ListPaymentHistoriesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListPaymentHistoriesMethod(), responseObserver);
    }

    /**
     */
    default void listSubscriptionPlans(net.twinte.api.donation.v1.Service.ListSubscriptionPlansRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.ListSubscriptionPlansResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListSubscriptionPlansMethod(), responseObserver);
    }

    /**
     */
    default void getActiveSubscription(net.twinte.api.donation.v1.Service.GetActiveSubscriptionRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.GetActiveSubscriptionResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetActiveSubscriptionMethod(), responseObserver);
    }

    /**
     */
    default void unsubscribe(net.twinte.api.donation.v1.Service.UnsubscribeRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.UnsubscribeResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUnsubscribeMethod(), responseObserver);
    }

    /**
     */
    default void getTotalAmount(net.twinte.api.donation.v1.Service.GetTotalAmountRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.GetTotalAmountResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetTotalAmountMethod(), responseObserver);
    }

    /**
     */
    default void listContributors(net.twinte.api.donation.v1.Service.ListContributorsRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.ListContributorsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListContributorsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service DonationService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static abstract class DonationServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return DonationServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service DonationService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static final class DonationServiceStub
      extends io.grpc.stub.AbstractAsyncStub<DonationServiceStub> {
    private DonationServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DonationServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DonationServiceStub(channel, callOptions);
    }

    /**
     */
    public void createOneTimeCheckoutSession(net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateOneTimeCheckoutSessionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createSubscriptionCheckoutSession(net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateSubscriptionCheckoutSessionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPaymentUser(net.twinte.api.donation.v1.Service.GetPaymentUserRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.GetPaymentUserResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetPaymentUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updatePaymentUser(net.twinte.api.donation.v1.Service.UpdatePaymentUserRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.UpdatePaymentUserResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdatePaymentUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listPaymentHistories(net.twinte.api.donation.v1.Service.ListPaymentHistoriesRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.ListPaymentHistoriesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListPaymentHistoriesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listSubscriptionPlans(net.twinte.api.donation.v1.Service.ListSubscriptionPlansRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.ListSubscriptionPlansResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListSubscriptionPlansMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getActiveSubscription(net.twinte.api.donation.v1.Service.GetActiveSubscriptionRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.GetActiveSubscriptionResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetActiveSubscriptionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void unsubscribe(net.twinte.api.donation.v1.Service.UnsubscribeRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.UnsubscribeResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUnsubscribeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getTotalAmount(net.twinte.api.donation.v1.Service.GetTotalAmountRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.GetTotalAmountResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetTotalAmountMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listContributors(net.twinte.api.donation.v1.Service.ListContributorsRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.ListContributorsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListContributorsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service DonationService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static final class DonationServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<DonationServiceBlockingV2Stub> {
    private DonationServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DonationServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DonationServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionResponse createOneTimeCheckoutSession(net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateOneTimeCheckoutSessionMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionResponse createSubscriptionCheckoutSession(net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateSubscriptionCheckoutSessionMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.donation.v1.Service.GetPaymentUserResponse getPaymentUser(net.twinte.api.donation.v1.Service.GetPaymentUserRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetPaymentUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.donation.v1.Service.UpdatePaymentUserResponse updatePaymentUser(net.twinte.api.donation.v1.Service.UpdatePaymentUserRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdatePaymentUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.donation.v1.Service.ListPaymentHistoriesResponse listPaymentHistories(net.twinte.api.donation.v1.Service.ListPaymentHistoriesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListPaymentHistoriesMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.donation.v1.Service.ListSubscriptionPlansResponse listSubscriptionPlans(net.twinte.api.donation.v1.Service.ListSubscriptionPlansRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListSubscriptionPlansMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.donation.v1.Service.GetActiveSubscriptionResponse getActiveSubscription(net.twinte.api.donation.v1.Service.GetActiveSubscriptionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetActiveSubscriptionMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.donation.v1.Service.UnsubscribeResponse unsubscribe(net.twinte.api.donation.v1.Service.UnsubscribeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUnsubscribeMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.donation.v1.Service.GetTotalAmountResponse getTotalAmount(net.twinte.api.donation.v1.Service.GetTotalAmountRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetTotalAmountMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.donation.v1.Service.ListContributorsResponse listContributors(net.twinte.api.donation.v1.Service.ListContributorsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListContributorsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service DonationService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static final class DonationServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<DonationServiceBlockingStub> {
    private DonationServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DonationServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DonationServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionResponse createOneTimeCheckoutSession(net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateOneTimeCheckoutSessionMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionResponse createSubscriptionCheckoutSession(net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateSubscriptionCheckoutSessionMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.donation.v1.Service.GetPaymentUserResponse getPaymentUser(net.twinte.api.donation.v1.Service.GetPaymentUserRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetPaymentUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.donation.v1.Service.UpdatePaymentUserResponse updatePaymentUser(net.twinte.api.donation.v1.Service.UpdatePaymentUserRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdatePaymentUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.donation.v1.Service.ListPaymentHistoriesResponse listPaymentHistories(net.twinte.api.donation.v1.Service.ListPaymentHistoriesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListPaymentHistoriesMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.donation.v1.Service.ListSubscriptionPlansResponse listSubscriptionPlans(net.twinte.api.donation.v1.Service.ListSubscriptionPlansRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListSubscriptionPlansMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.donation.v1.Service.GetActiveSubscriptionResponse getActiveSubscription(net.twinte.api.donation.v1.Service.GetActiveSubscriptionRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetActiveSubscriptionMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.donation.v1.Service.UnsubscribeResponse unsubscribe(net.twinte.api.donation.v1.Service.UnsubscribeRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUnsubscribeMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.donation.v1.Service.GetTotalAmountResponse getTotalAmount(net.twinte.api.donation.v1.Service.GetTotalAmountRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetTotalAmountMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.donation.v1.Service.ListContributorsResponse listContributors(net.twinte.api.donation.v1.Service.ListContributorsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListContributorsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service DonationService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static final class DonationServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<DonationServiceFutureStub> {
    private DonationServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DonationServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DonationServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionResponse> createOneTimeCheckoutSession(
        net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateOneTimeCheckoutSessionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionResponse> createSubscriptionCheckoutSession(
        net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateSubscriptionCheckoutSessionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.donation.v1.Service.GetPaymentUserResponse> getPaymentUser(
        net.twinte.api.donation.v1.Service.GetPaymentUserRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetPaymentUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.donation.v1.Service.UpdatePaymentUserResponse> updatePaymentUser(
        net.twinte.api.donation.v1.Service.UpdatePaymentUserRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdatePaymentUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.donation.v1.Service.ListPaymentHistoriesResponse> listPaymentHistories(
        net.twinte.api.donation.v1.Service.ListPaymentHistoriesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListPaymentHistoriesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.donation.v1.Service.ListSubscriptionPlansResponse> listSubscriptionPlans(
        net.twinte.api.donation.v1.Service.ListSubscriptionPlansRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListSubscriptionPlansMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.donation.v1.Service.GetActiveSubscriptionResponse> getActiveSubscription(
        net.twinte.api.donation.v1.Service.GetActiveSubscriptionRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetActiveSubscriptionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.donation.v1.Service.UnsubscribeResponse> unsubscribe(
        net.twinte.api.donation.v1.Service.UnsubscribeRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUnsubscribeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.donation.v1.Service.GetTotalAmountResponse> getTotalAmount(
        net.twinte.api.donation.v1.Service.GetTotalAmountRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetTotalAmountMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.donation.v1.Service.ListContributorsResponse> listContributors(
        net.twinte.api.donation.v1.Service.ListContributorsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListContributorsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_ONE_TIME_CHECKOUT_SESSION = 0;
  private static final int METHODID_CREATE_SUBSCRIPTION_CHECKOUT_SESSION = 1;
  private static final int METHODID_GET_PAYMENT_USER = 2;
  private static final int METHODID_UPDATE_PAYMENT_USER = 3;
  private static final int METHODID_LIST_PAYMENT_HISTORIES = 4;
  private static final int METHODID_LIST_SUBSCRIPTION_PLANS = 5;
  private static final int METHODID_GET_ACTIVE_SUBSCRIPTION = 6;
  private static final int METHODID_UNSUBSCRIBE = 7;
  private static final int METHODID_GET_TOTAL_AMOUNT = 8;
  private static final int METHODID_LIST_CONTRIBUTORS = 9;

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
        case METHODID_CREATE_ONE_TIME_CHECKOUT_SESSION:
          serviceImpl.createOneTimeCheckoutSession((net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionResponse>) responseObserver);
          break;
        case METHODID_CREATE_SUBSCRIPTION_CHECKOUT_SESSION:
          serviceImpl.createSubscriptionCheckoutSession((net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionResponse>) responseObserver);
          break;
        case METHODID_GET_PAYMENT_USER:
          serviceImpl.getPaymentUser((net.twinte.api.donation.v1.Service.GetPaymentUserRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.GetPaymentUserResponse>) responseObserver);
          break;
        case METHODID_UPDATE_PAYMENT_USER:
          serviceImpl.updatePaymentUser((net.twinte.api.donation.v1.Service.UpdatePaymentUserRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.UpdatePaymentUserResponse>) responseObserver);
          break;
        case METHODID_LIST_PAYMENT_HISTORIES:
          serviceImpl.listPaymentHistories((net.twinte.api.donation.v1.Service.ListPaymentHistoriesRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.ListPaymentHistoriesResponse>) responseObserver);
          break;
        case METHODID_LIST_SUBSCRIPTION_PLANS:
          serviceImpl.listSubscriptionPlans((net.twinte.api.donation.v1.Service.ListSubscriptionPlansRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.ListSubscriptionPlansResponse>) responseObserver);
          break;
        case METHODID_GET_ACTIVE_SUBSCRIPTION:
          serviceImpl.getActiveSubscription((net.twinte.api.donation.v1.Service.GetActiveSubscriptionRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.GetActiveSubscriptionResponse>) responseObserver);
          break;
        case METHODID_UNSUBSCRIBE:
          serviceImpl.unsubscribe((net.twinte.api.donation.v1.Service.UnsubscribeRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.UnsubscribeResponse>) responseObserver);
          break;
        case METHODID_GET_TOTAL_AMOUNT:
          serviceImpl.getTotalAmount((net.twinte.api.donation.v1.Service.GetTotalAmountRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.GetTotalAmountResponse>) responseObserver);
          break;
        case METHODID_LIST_CONTRIBUTORS:
          serviceImpl.listContributors((net.twinte.api.donation.v1.Service.ListContributorsRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.donation.v1.Service.ListContributorsResponse>) responseObserver);
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
          getCreateOneTimeCheckoutSessionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionRequest,
              net.twinte.api.donation.v1.Service.CreateOneTimeCheckoutSessionResponse>(
                service, METHODID_CREATE_ONE_TIME_CHECKOUT_SESSION)))
        .addMethod(
          getCreateSubscriptionCheckoutSessionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionRequest,
              net.twinte.api.donation.v1.Service.CreateSubscriptionCheckoutSessionResponse>(
                service, METHODID_CREATE_SUBSCRIPTION_CHECKOUT_SESSION)))
        .addMethod(
          getGetPaymentUserMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.donation.v1.Service.GetPaymentUserRequest,
              net.twinte.api.donation.v1.Service.GetPaymentUserResponse>(
                service, METHODID_GET_PAYMENT_USER)))
        .addMethod(
          getUpdatePaymentUserMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.donation.v1.Service.UpdatePaymentUserRequest,
              net.twinte.api.donation.v1.Service.UpdatePaymentUserResponse>(
                service, METHODID_UPDATE_PAYMENT_USER)))
        .addMethod(
          getListPaymentHistoriesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.donation.v1.Service.ListPaymentHistoriesRequest,
              net.twinte.api.donation.v1.Service.ListPaymentHistoriesResponse>(
                service, METHODID_LIST_PAYMENT_HISTORIES)))
        .addMethod(
          getListSubscriptionPlansMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.donation.v1.Service.ListSubscriptionPlansRequest,
              net.twinte.api.donation.v1.Service.ListSubscriptionPlansResponse>(
                service, METHODID_LIST_SUBSCRIPTION_PLANS)))
        .addMethod(
          getGetActiveSubscriptionMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.donation.v1.Service.GetActiveSubscriptionRequest,
              net.twinte.api.donation.v1.Service.GetActiveSubscriptionResponse>(
                service, METHODID_GET_ACTIVE_SUBSCRIPTION)))
        .addMethod(
          getUnsubscribeMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.donation.v1.Service.UnsubscribeRequest,
              net.twinte.api.donation.v1.Service.UnsubscribeResponse>(
                service, METHODID_UNSUBSCRIBE)))
        .addMethod(
          getGetTotalAmountMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.donation.v1.Service.GetTotalAmountRequest,
              net.twinte.api.donation.v1.Service.GetTotalAmountResponse>(
                service, METHODID_GET_TOTAL_AMOUNT)))
        .addMethod(
          getListContributorsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.donation.v1.Service.ListContributorsRequest,
              net.twinte.api.donation.v1.Service.ListContributorsResponse>(
                service, METHODID_LIST_CONTRIBUTORS)))
        .build();
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (DonationServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .addMethod(getCreateOneTimeCheckoutSessionMethod())
              .addMethod(getCreateSubscriptionCheckoutSessionMethod())
              .addMethod(getGetPaymentUserMethod())
              .addMethod(getUpdatePaymentUserMethod())
              .addMethod(getListPaymentHistoriesMethod())
              .addMethod(getListSubscriptionPlansMethod())
              .addMethod(getGetActiveSubscriptionMethod())
              .addMethod(getUnsubscribeMethod())
              .addMethod(getGetTotalAmountMethod())
              .addMethod(getListContributorsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
