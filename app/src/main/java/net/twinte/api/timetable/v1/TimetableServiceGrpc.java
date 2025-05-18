package net.twinte.api.timetable.v1;

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
    comments = "Source: timetable/v1/service.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class TimetableServiceGrpc {

  private TimetableServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "timetable.v1.TimetableService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.ListCoursesByCodesRequest,
      net.twinte.api.timetable.v1.Service.ListCoursesByCodesResponse> getListCoursesByCodesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListCoursesByCodes",
      requestType = net.twinte.api.timetable.v1.Service.ListCoursesByCodesRequest.class,
      responseType = net.twinte.api.timetable.v1.Service.ListCoursesByCodesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.ListCoursesByCodesRequest,
      net.twinte.api.timetable.v1.Service.ListCoursesByCodesResponse> getListCoursesByCodesMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.ListCoursesByCodesRequest, net.twinte.api.timetable.v1.Service.ListCoursesByCodesResponse> getListCoursesByCodesMethod;
    if ((getListCoursesByCodesMethod = TimetableServiceGrpc.getListCoursesByCodesMethod) == null) {
      synchronized (TimetableServiceGrpc.class) {
        if ((getListCoursesByCodesMethod = TimetableServiceGrpc.getListCoursesByCodesMethod) == null) {
          TimetableServiceGrpc.getListCoursesByCodesMethod = getListCoursesByCodesMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.timetable.v1.Service.ListCoursesByCodesRequest, net.twinte.api.timetable.v1.Service.ListCoursesByCodesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListCoursesByCodes"))
              .setSafe(true)
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.timetable.v1.Service.ListCoursesByCodesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.timetable.v1.Service.ListCoursesByCodesResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getListCoursesByCodesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.SearchCoursesRequest,
      net.twinte.api.timetable.v1.Service.SearchCoursesResponse> getSearchCoursesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SearchCourses",
      requestType = net.twinte.api.timetable.v1.Service.SearchCoursesRequest.class,
      responseType = net.twinte.api.timetable.v1.Service.SearchCoursesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.SearchCoursesRequest,
      net.twinte.api.timetable.v1.Service.SearchCoursesResponse> getSearchCoursesMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.SearchCoursesRequest, net.twinte.api.timetable.v1.Service.SearchCoursesResponse> getSearchCoursesMethod;
    if ((getSearchCoursesMethod = TimetableServiceGrpc.getSearchCoursesMethod) == null) {
      synchronized (TimetableServiceGrpc.class) {
        if ((getSearchCoursesMethod = TimetableServiceGrpc.getSearchCoursesMethod) == null) {
          TimetableServiceGrpc.getSearchCoursesMethod = getSearchCoursesMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.timetable.v1.Service.SearchCoursesRequest, net.twinte.api.timetable.v1.Service.SearchCoursesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SearchCourses"))
              .setSafe(true)
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.timetable.v1.Service.SearchCoursesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.timetable.v1.Service.SearchCoursesResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getSearchCoursesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesRequest,
      net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesResponse> getCreateRegisteredCoursesByCodesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateRegisteredCoursesByCodes",
      requestType = net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesRequest.class,
      responseType = net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesRequest,
      net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesResponse> getCreateRegisteredCoursesByCodesMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesRequest, net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesResponse> getCreateRegisteredCoursesByCodesMethod;
    if ((getCreateRegisteredCoursesByCodesMethod = TimetableServiceGrpc.getCreateRegisteredCoursesByCodesMethod) == null) {
      synchronized (TimetableServiceGrpc.class) {
        if ((getCreateRegisteredCoursesByCodesMethod = TimetableServiceGrpc.getCreateRegisteredCoursesByCodesMethod) == null) {
          TimetableServiceGrpc.getCreateRegisteredCoursesByCodesMethod = getCreateRegisteredCoursesByCodesMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesRequest, net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateRegisteredCoursesByCodes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getCreateRegisteredCoursesByCodesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyRequest,
      net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyResponse> getCreateRegisteredCourseManuallyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateRegisteredCourseManually",
      requestType = net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyRequest.class,
      responseType = net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyRequest,
      net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyResponse> getCreateRegisteredCourseManuallyMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyRequest, net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyResponse> getCreateRegisteredCourseManuallyMethod;
    if ((getCreateRegisteredCourseManuallyMethod = TimetableServiceGrpc.getCreateRegisteredCourseManuallyMethod) == null) {
      synchronized (TimetableServiceGrpc.class) {
        if ((getCreateRegisteredCourseManuallyMethod = TimetableServiceGrpc.getCreateRegisteredCourseManuallyMethod) == null) {
          TimetableServiceGrpc.getCreateRegisteredCourseManuallyMethod = getCreateRegisteredCourseManuallyMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyRequest, net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateRegisteredCourseManually"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getCreateRegisteredCourseManuallyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.ListRegisteredCoursesRequest,
      net.twinte.api.timetable.v1.Service.ListRegisteredCoursesResponse> getListRegisteredCoursesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListRegisteredCourses",
      requestType = net.twinte.api.timetable.v1.Service.ListRegisteredCoursesRequest.class,
      responseType = net.twinte.api.timetable.v1.Service.ListRegisteredCoursesResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.ListRegisteredCoursesRequest,
      net.twinte.api.timetable.v1.Service.ListRegisteredCoursesResponse> getListRegisteredCoursesMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.ListRegisteredCoursesRequest, net.twinte.api.timetable.v1.Service.ListRegisteredCoursesResponse> getListRegisteredCoursesMethod;
    if ((getListRegisteredCoursesMethod = TimetableServiceGrpc.getListRegisteredCoursesMethod) == null) {
      synchronized (TimetableServiceGrpc.class) {
        if ((getListRegisteredCoursesMethod = TimetableServiceGrpc.getListRegisteredCoursesMethod) == null) {
          TimetableServiceGrpc.getListRegisteredCoursesMethod = getListRegisteredCoursesMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.timetable.v1.Service.ListRegisteredCoursesRequest, net.twinte.api.timetable.v1.Service.ListRegisteredCoursesResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListRegisteredCourses"))
              .setSafe(true)
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.timetable.v1.Service.ListRegisteredCoursesRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.timetable.v1.Service.ListRegisteredCoursesResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getListRegisteredCoursesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseRequest,
      net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseResponse> getUpdateRegisteredCourseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateRegisteredCourse",
      requestType = net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseRequest.class,
      responseType = net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseRequest,
      net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseResponse> getUpdateRegisteredCourseMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseRequest, net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseResponse> getUpdateRegisteredCourseMethod;
    if ((getUpdateRegisteredCourseMethod = TimetableServiceGrpc.getUpdateRegisteredCourseMethod) == null) {
      synchronized (TimetableServiceGrpc.class) {
        if ((getUpdateRegisteredCourseMethod = TimetableServiceGrpc.getUpdateRegisteredCourseMethod) == null) {
          TimetableServiceGrpc.getUpdateRegisteredCourseMethod = getUpdateRegisteredCourseMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseRequest, net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateRegisteredCourse"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getUpdateRegisteredCourseMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseRequest,
      net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseResponse> getDeleteRegisteredCourseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteRegisteredCourse",
      requestType = net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseRequest.class,
      responseType = net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseRequest,
      net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseResponse> getDeleteRegisteredCourseMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseRequest, net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseResponse> getDeleteRegisteredCourseMethod;
    if ((getDeleteRegisteredCourseMethod = TimetableServiceGrpc.getDeleteRegisteredCourseMethod) == null) {
      synchronized (TimetableServiceGrpc.class) {
        if ((getDeleteRegisteredCourseMethod = TimetableServiceGrpc.getDeleteRegisteredCourseMethod) == null) {
          TimetableServiceGrpc.getDeleteRegisteredCourseMethod = getDeleteRegisteredCourseMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseRequest, net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteRegisteredCourse"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getDeleteRegisteredCourseMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.CreateTagRequest,
      net.twinte.api.timetable.v1.Service.CreateTagResponse> getCreateTagMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateTag",
      requestType = net.twinte.api.timetable.v1.Service.CreateTagRequest.class,
      responseType = net.twinte.api.timetable.v1.Service.CreateTagResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.CreateTagRequest,
      net.twinte.api.timetable.v1.Service.CreateTagResponse> getCreateTagMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.CreateTagRequest, net.twinte.api.timetable.v1.Service.CreateTagResponse> getCreateTagMethod;
    if ((getCreateTagMethod = TimetableServiceGrpc.getCreateTagMethod) == null) {
      synchronized (TimetableServiceGrpc.class) {
        if ((getCreateTagMethod = TimetableServiceGrpc.getCreateTagMethod) == null) {
          TimetableServiceGrpc.getCreateTagMethod = getCreateTagMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.timetable.v1.Service.CreateTagRequest, net.twinte.api.timetable.v1.Service.CreateTagResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateTag"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.timetable.v1.Service.CreateTagRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.timetable.v1.Service.CreateTagResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getCreateTagMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.ListTagsRequest,
      net.twinte.api.timetable.v1.Service.ListTagsResponse> getListTagsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListTags",
      requestType = net.twinte.api.timetable.v1.Service.ListTagsRequest.class,
      responseType = net.twinte.api.timetable.v1.Service.ListTagsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.ListTagsRequest,
      net.twinte.api.timetable.v1.Service.ListTagsResponse> getListTagsMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.ListTagsRequest, net.twinte.api.timetable.v1.Service.ListTagsResponse> getListTagsMethod;
    if ((getListTagsMethod = TimetableServiceGrpc.getListTagsMethod) == null) {
      synchronized (TimetableServiceGrpc.class) {
        if ((getListTagsMethod = TimetableServiceGrpc.getListTagsMethod) == null) {
          TimetableServiceGrpc.getListTagsMethod = getListTagsMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.timetable.v1.Service.ListTagsRequest, net.twinte.api.timetable.v1.Service.ListTagsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListTags"))
              .setSafe(true)
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.timetable.v1.Service.ListTagsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.timetable.v1.Service.ListTagsResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getListTagsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.UpdateTagRequest,
      net.twinte.api.timetable.v1.Service.UpdateTagResponse> getUpdateTagMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateTag",
      requestType = net.twinte.api.timetable.v1.Service.UpdateTagRequest.class,
      responseType = net.twinte.api.timetable.v1.Service.UpdateTagResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.UpdateTagRequest,
      net.twinte.api.timetable.v1.Service.UpdateTagResponse> getUpdateTagMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.UpdateTagRequest, net.twinte.api.timetable.v1.Service.UpdateTagResponse> getUpdateTagMethod;
    if ((getUpdateTagMethod = TimetableServiceGrpc.getUpdateTagMethod) == null) {
      synchronized (TimetableServiceGrpc.class) {
        if ((getUpdateTagMethod = TimetableServiceGrpc.getUpdateTagMethod) == null) {
          TimetableServiceGrpc.getUpdateTagMethod = getUpdateTagMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.timetable.v1.Service.UpdateTagRequest, net.twinte.api.timetable.v1.Service.UpdateTagResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateTag"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.timetable.v1.Service.UpdateTagRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.timetable.v1.Service.UpdateTagResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getUpdateTagMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.DeleteTagRequest,
      net.twinte.api.timetable.v1.Service.DeleteTagResponse> getDeleteTagMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteTag",
      requestType = net.twinte.api.timetable.v1.Service.DeleteTagRequest.class,
      responseType = net.twinte.api.timetable.v1.Service.DeleteTagResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.DeleteTagRequest,
      net.twinte.api.timetable.v1.Service.DeleteTagResponse> getDeleteTagMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.DeleteTagRequest, net.twinte.api.timetable.v1.Service.DeleteTagResponse> getDeleteTagMethod;
    if ((getDeleteTagMethod = TimetableServiceGrpc.getDeleteTagMethod) == null) {
      synchronized (TimetableServiceGrpc.class) {
        if ((getDeleteTagMethod = TimetableServiceGrpc.getDeleteTagMethod) == null) {
          TimetableServiceGrpc.getDeleteTagMethod = getDeleteTagMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.timetable.v1.Service.DeleteTagRequest, net.twinte.api.timetable.v1.Service.DeleteTagResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteTag"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.timetable.v1.Service.DeleteTagRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.timetable.v1.Service.DeleteTagResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getDeleteTagMethod;
  }

  private static volatile io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.RearrangeTagsRequest,
      net.twinte.api.timetable.v1.Service.RearrangeTagsResponse> getRearrangeTagsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RearrangeTags",
      requestType = net.twinte.api.timetable.v1.Service.RearrangeTagsRequest.class,
      responseType = net.twinte.api.timetable.v1.Service.RearrangeTagsResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.RearrangeTagsRequest,
      net.twinte.api.timetable.v1.Service.RearrangeTagsResponse> getRearrangeTagsMethod() {
    io.grpc.MethodDescriptor<net.twinte.api.timetable.v1.Service.RearrangeTagsRequest, net.twinte.api.timetable.v1.Service.RearrangeTagsResponse> getRearrangeTagsMethod;
    if ((getRearrangeTagsMethod = TimetableServiceGrpc.getRearrangeTagsMethod) == null) {
      synchronized (TimetableServiceGrpc.class) {
        if ((getRearrangeTagsMethod = TimetableServiceGrpc.getRearrangeTagsMethod) == null) {
          TimetableServiceGrpc.getRearrangeTagsMethod = getRearrangeTagsMethod =
              io.grpc.MethodDescriptor.<net.twinte.api.timetable.v1.Service.RearrangeTagsRequest, net.twinte.api.timetable.v1.Service.RearrangeTagsResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RearrangeTags"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.timetable.v1.Service.RearrangeTagsRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.lite.ProtoLiteUtils.marshaller(
                  net.twinte.api.timetable.v1.Service.RearrangeTagsResponse.getDefaultInstance()))
              .build();
        }
      }
    }
    return getRearrangeTagsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TimetableServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TimetableServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TimetableServiceStub>() {
        @java.lang.Override
        public TimetableServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TimetableServiceStub(channel, callOptions);
        }
      };
    return TimetableServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static TimetableServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TimetableServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TimetableServiceBlockingV2Stub>() {
        @java.lang.Override
        public TimetableServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TimetableServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return TimetableServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TimetableServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TimetableServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TimetableServiceBlockingStub>() {
        @java.lang.Override
        public TimetableServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TimetableServiceBlockingStub(channel, callOptions);
        }
      };
    return TimetableServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TimetableServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TimetableServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TimetableServiceFutureStub>() {
        @java.lang.Override
        public TimetableServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TimetableServiceFutureStub(channel, callOptions);
        }
      };
    return TimetableServiceFutureStub.newStub(factory, channel);
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
    default void listCoursesByCodes(net.twinte.api.timetable.v1.Service.ListCoursesByCodesRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.ListCoursesByCodesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListCoursesByCodesMethod(), responseObserver);
    }

    /**
     */
    default void searchCourses(net.twinte.api.timetable.v1.Service.SearchCoursesRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.SearchCoursesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSearchCoursesMethod(), responseObserver);
    }

    /**
     */
    default void createRegisteredCoursesByCodes(net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateRegisteredCoursesByCodesMethod(), responseObserver);
    }

    /**
     */
    default void createRegisteredCourseManually(net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateRegisteredCourseManuallyMethod(), responseObserver);
    }

    /**
     */
    default void listRegisteredCourses(net.twinte.api.timetable.v1.Service.ListRegisteredCoursesRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.ListRegisteredCoursesResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListRegisteredCoursesMethod(), responseObserver);
    }

    /**
     */
    default void updateRegisteredCourse(net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateRegisteredCourseMethod(), responseObserver);
    }

    /**
     */
    default void deleteRegisteredCourse(net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteRegisteredCourseMethod(), responseObserver);
    }

    /**
     */
    default void createTag(net.twinte.api.timetable.v1.Service.CreateTagRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.CreateTagResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateTagMethod(), responseObserver);
    }

    /**
     */
    default void listTags(net.twinte.api.timetable.v1.Service.ListTagsRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.ListTagsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getListTagsMethod(), responseObserver);
    }

    /**
     */
    default void updateTag(net.twinte.api.timetable.v1.Service.UpdateTagRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.UpdateTagResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateTagMethod(), responseObserver);
    }

    /**
     */
    default void deleteTag(net.twinte.api.timetable.v1.Service.DeleteTagRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.DeleteTagResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteTagMethod(), responseObserver);
    }

    /**
     */
    default void rearrangeTags(net.twinte.api.timetable.v1.Service.RearrangeTagsRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.RearrangeTagsResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getRearrangeTagsMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service TimetableService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static abstract class TimetableServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return TimetableServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service TimetableService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static final class TimetableServiceStub
      extends io.grpc.stub.AbstractAsyncStub<TimetableServiceStub> {
    private TimetableServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TimetableServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TimetableServiceStub(channel, callOptions);
    }

    /**
     */
    public void listCoursesByCodes(net.twinte.api.timetable.v1.Service.ListCoursesByCodesRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.ListCoursesByCodesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListCoursesByCodesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void searchCourses(net.twinte.api.timetable.v1.Service.SearchCoursesRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.SearchCoursesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSearchCoursesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createRegisteredCoursesByCodes(net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateRegisteredCoursesByCodesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createRegisteredCourseManually(net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateRegisteredCourseManuallyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listRegisteredCourses(net.twinte.api.timetable.v1.Service.ListRegisteredCoursesRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.ListRegisteredCoursesResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListRegisteredCoursesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateRegisteredCourse(net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateRegisteredCourseMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteRegisteredCourse(net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteRegisteredCourseMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createTag(net.twinte.api.timetable.v1.Service.CreateTagRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.CreateTagResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateTagMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listTags(net.twinte.api.timetable.v1.Service.ListTagsRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.ListTagsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getListTagsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateTag(net.twinte.api.timetable.v1.Service.UpdateTagRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.UpdateTagResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateTagMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteTag(net.twinte.api.timetable.v1.Service.DeleteTagRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.DeleteTagResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteTagMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rearrangeTags(net.twinte.api.timetable.v1.Service.RearrangeTagsRequest request,
        io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.RearrangeTagsResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getRearrangeTagsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service TimetableService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static final class TimetableServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<TimetableServiceBlockingV2Stub> {
    private TimetableServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TimetableServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TimetableServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     */
    public net.twinte.api.timetable.v1.Service.ListCoursesByCodesResponse listCoursesByCodes(net.twinte.api.timetable.v1.Service.ListCoursesByCodesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListCoursesByCodesMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.timetable.v1.Service.SearchCoursesResponse searchCourses(net.twinte.api.timetable.v1.Service.SearchCoursesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSearchCoursesMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesResponse createRegisteredCoursesByCodes(net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateRegisteredCoursesByCodesMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyResponse createRegisteredCourseManually(net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateRegisteredCourseManuallyMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.timetable.v1.Service.ListRegisteredCoursesResponse listRegisteredCourses(net.twinte.api.timetable.v1.Service.ListRegisteredCoursesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListRegisteredCoursesMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseResponse updateRegisteredCourse(net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateRegisteredCourseMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseResponse deleteRegisteredCourse(net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteRegisteredCourseMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.timetable.v1.Service.CreateTagResponse createTag(net.twinte.api.timetable.v1.Service.CreateTagRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateTagMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.timetable.v1.Service.ListTagsResponse listTags(net.twinte.api.timetable.v1.Service.ListTagsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListTagsMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.timetable.v1.Service.UpdateTagResponse updateTag(net.twinte.api.timetable.v1.Service.UpdateTagRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateTagMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.timetable.v1.Service.DeleteTagResponse deleteTag(net.twinte.api.timetable.v1.Service.DeleteTagRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteTagMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.timetable.v1.Service.RearrangeTagsResponse rearrangeTags(net.twinte.api.timetable.v1.Service.RearrangeTagsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRearrangeTagsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service TimetableService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static final class TimetableServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<TimetableServiceBlockingStub> {
    private TimetableServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TimetableServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TimetableServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public net.twinte.api.timetable.v1.Service.ListCoursesByCodesResponse listCoursesByCodes(net.twinte.api.timetable.v1.Service.ListCoursesByCodesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListCoursesByCodesMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.timetable.v1.Service.SearchCoursesResponse searchCourses(net.twinte.api.timetable.v1.Service.SearchCoursesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSearchCoursesMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesResponse createRegisteredCoursesByCodes(net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateRegisteredCoursesByCodesMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyResponse createRegisteredCourseManually(net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateRegisteredCourseManuallyMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.timetable.v1.Service.ListRegisteredCoursesResponse listRegisteredCourses(net.twinte.api.timetable.v1.Service.ListRegisteredCoursesRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListRegisteredCoursesMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseResponse updateRegisteredCourse(net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateRegisteredCourseMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseResponse deleteRegisteredCourse(net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteRegisteredCourseMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.timetable.v1.Service.CreateTagResponse createTag(net.twinte.api.timetable.v1.Service.CreateTagRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateTagMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.timetable.v1.Service.ListTagsResponse listTags(net.twinte.api.timetable.v1.Service.ListTagsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getListTagsMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.timetable.v1.Service.UpdateTagResponse updateTag(net.twinte.api.timetable.v1.Service.UpdateTagRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateTagMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.timetable.v1.Service.DeleteTagResponse deleteTag(net.twinte.api.timetable.v1.Service.DeleteTagRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteTagMethod(), getCallOptions(), request);
    }

    /**
     */
    public net.twinte.api.timetable.v1.Service.RearrangeTagsResponse rearrangeTags(net.twinte.api.timetable.v1.Service.RearrangeTagsRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getRearrangeTagsMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service TimetableService.
   * <pre>
   * The following error codes are not stated explicitly in the each rpc, but may be returned.
   *   - shared.InvalidArgument
   *   - shared.Unauthenticated
   *   - shared.Unauthorized
   * </pre>
   */
  public static final class TimetableServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<TimetableServiceFutureStub> {
    private TimetableServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TimetableServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TimetableServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.timetable.v1.Service.ListCoursesByCodesResponse> listCoursesByCodes(
        net.twinte.api.timetable.v1.Service.ListCoursesByCodesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListCoursesByCodesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.timetable.v1.Service.SearchCoursesResponse> searchCourses(
        net.twinte.api.timetable.v1.Service.SearchCoursesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSearchCoursesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesResponse> createRegisteredCoursesByCodes(
        net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateRegisteredCoursesByCodesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyResponse> createRegisteredCourseManually(
        net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateRegisteredCourseManuallyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.timetable.v1.Service.ListRegisteredCoursesResponse> listRegisteredCourses(
        net.twinte.api.timetable.v1.Service.ListRegisteredCoursesRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListRegisteredCoursesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseResponse> updateRegisteredCourse(
        net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateRegisteredCourseMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseResponse> deleteRegisteredCourse(
        net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteRegisteredCourseMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.timetable.v1.Service.CreateTagResponse> createTag(
        net.twinte.api.timetable.v1.Service.CreateTagRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateTagMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.timetable.v1.Service.ListTagsResponse> listTags(
        net.twinte.api.timetable.v1.Service.ListTagsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getListTagsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.timetable.v1.Service.UpdateTagResponse> updateTag(
        net.twinte.api.timetable.v1.Service.UpdateTagRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateTagMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.timetable.v1.Service.DeleteTagResponse> deleteTag(
        net.twinte.api.timetable.v1.Service.DeleteTagRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteTagMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<net.twinte.api.timetable.v1.Service.RearrangeTagsResponse> rearrangeTags(
        net.twinte.api.timetable.v1.Service.RearrangeTagsRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getRearrangeTagsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_LIST_COURSES_BY_CODES = 0;
  private static final int METHODID_SEARCH_COURSES = 1;
  private static final int METHODID_CREATE_REGISTERED_COURSES_BY_CODES = 2;
  private static final int METHODID_CREATE_REGISTERED_COURSE_MANUALLY = 3;
  private static final int METHODID_LIST_REGISTERED_COURSES = 4;
  private static final int METHODID_UPDATE_REGISTERED_COURSE = 5;
  private static final int METHODID_DELETE_REGISTERED_COURSE = 6;
  private static final int METHODID_CREATE_TAG = 7;
  private static final int METHODID_LIST_TAGS = 8;
  private static final int METHODID_UPDATE_TAG = 9;
  private static final int METHODID_DELETE_TAG = 10;
  private static final int METHODID_REARRANGE_TAGS = 11;

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
        case METHODID_LIST_COURSES_BY_CODES:
          serviceImpl.listCoursesByCodes((net.twinte.api.timetable.v1.Service.ListCoursesByCodesRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.ListCoursesByCodesResponse>) responseObserver);
          break;
        case METHODID_SEARCH_COURSES:
          serviceImpl.searchCourses((net.twinte.api.timetable.v1.Service.SearchCoursesRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.SearchCoursesResponse>) responseObserver);
          break;
        case METHODID_CREATE_REGISTERED_COURSES_BY_CODES:
          serviceImpl.createRegisteredCoursesByCodes((net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesResponse>) responseObserver);
          break;
        case METHODID_CREATE_REGISTERED_COURSE_MANUALLY:
          serviceImpl.createRegisteredCourseManually((net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyResponse>) responseObserver);
          break;
        case METHODID_LIST_REGISTERED_COURSES:
          serviceImpl.listRegisteredCourses((net.twinte.api.timetable.v1.Service.ListRegisteredCoursesRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.ListRegisteredCoursesResponse>) responseObserver);
          break;
        case METHODID_UPDATE_REGISTERED_COURSE:
          serviceImpl.updateRegisteredCourse((net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseResponse>) responseObserver);
          break;
        case METHODID_DELETE_REGISTERED_COURSE:
          serviceImpl.deleteRegisteredCourse((net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseResponse>) responseObserver);
          break;
        case METHODID_CREATE_TAG:
          serviceImpl.createTag((net.twinte.api.timetable.v1.Service.CreateTagRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.CreateTagResponse>) responseObserver);
          break;
        case METHODID_LIST_TAGS:
          serviceImpl.listTags((net.twinte.api.timetable.v1.Service.ListTagsRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.ListTagsResponse>) responseObserver);
          break;
        case METHODID_UPDATE_TAG:
          serviceImpl.updateTag((net.twinte.api.timetable.v1.Service.UpdateTagRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.UpdateTagResponse>) responseObserver);
          break;
        case METHODID_DELETE_TAG:
          serviceImpl.deleteTag((net.twinte.api.timetable.v1.Service.DeleteTagRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.DeleteTagResponse>) responseObserver);
          break;
        case METHODID_REARRANGE_TAGS:
          serviceImpl.rearrangeTags((net.twinte.api.timetable.v1.Service.RearrangeTagsRequest) request,
              (io.grpc.stub.StreamObserver<net.twinte.api.timetable.v1.Service.RearrangeTagsResponse>) responseObserver);
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
          getListCoursesByCodesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.timetable.v1.Service.ListCoursesByCodesRequest,
              net.twinte.api.timetable.v1.Service.ListCoursesByCodesResponse>(
                service, METHODID_LIST_COURSES_BY_CODES)))
        .addMethod(
          getSearchCoursesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.timetable.v1.Service.SearchCoursesRequest,
              net.twinte.api.timetable.v1.Service.SearchCoursesResponse>(
                service, METHODID_SEARCH_COURSES)))
        .addMethod(
          getCreateRegisteredCoursesByCodesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesRequest,
              net.twinte.api.timetable.v1.Service.CreateRegisteredCoursesByCodesResponse>(
                service, METHODID_CREATE_REGISTERED_COURSES_BY_CODES)))
        .addMethod(
          getCreateRegisteredCourseManuallyMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyRequest,
              net.twinte.api.timetable.v1.Service.CreateRegisteredCourseManuallyResponse>(
                service, METHODID_CREATE_REGISTERED_COURSE_MANUALLY)))
        .addMethod(
          getListRegisteredCoursesMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.timetable.v1.Service.ListRegisteredCoursesRequest,
              net.twinte.api.timetable.v1.Service.ListRegisteredCoursesResponse>(
                service, METHODID_LIST_REGISTERED_COURSES)))
        .addMethod(
          getUpdateRegisteredCourseMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseRequest,
              net.twinte.api.timetable.v1.Service.UpdateRegisteredCourseResponse>(
                service, METHODID_UPDATE_REGISTERED_COURSE)))
        .addMethod(
          getDeleteRegisteredCourseMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseRequest,
              net.twinte.api.timetable.v1.Service.DeleteRegisteredCourseResponse>(
                service, METHODID_DELETE_REGISTERED_COURSE)))
        .addMethod(
          getCreateTagMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.timetable.v1.Service.CreateTagRequest,
              net.twinte.api.timetable.v1.Service.CreateTagResponse>(
                service, METHODID_CREATE_TAG)))
        .addMethod(
          getListTagsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.timetable.v1.Service.ListTagsRequest,
              net.twinte.api.timetable.v1.Service.ListTagsResponse>(
                service, METHODID_LIST_TAGS)))
        .addMethod(
          getUpdateTagMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.timetable.v1.Service.UpdateTagRequest,
              net.twinte.api.timetable.v1.Service.UpdateTagResponse>(
                service, METHODID_UPDATE_TAG)))
        .addMethod(
          getDeleteTagMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.timetable.v1.Service.DeleteTagRequest,
              net.twinte.api.timetable.v1.Service.DeleteTagResponse>(
                service, METHODID_DELETE_TAG)))
        .addMethod(
          getRearrangeTagsMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              net.twinte.api.timetable.v1.Service.RearrangeTagsRequest,
              net.twinte.api.timetable.v1.Service.RearrangeTagsResponse>(
                service, METHODID_REARRANGE_TAGS)))
        .build();
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TimetableServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .addMethod(getListCoursesByCodesMethod())
              .addMethod(getSearchCoursesMethod())
              .addMethod(getCreateRegisteredCoursesByCodesMethod())
              .addMethod(getCreateRegisteredCourseManuallyMethod())
              .addMethod(getListRegisteredCoursesMethod())
              .addMethod(getUpdateRegisteredCourseMethod())
              .addMethod(getDeleteRegisteredCourseMethod())
              .addMethod(getCreateTagMethod())
              .addMethod(getListTagsMethod())
              .addMethod(getUpdateTagMethod())
              .addMethod(getDeleteTagMethod())
              .addMethod(getRearrangeTagsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
