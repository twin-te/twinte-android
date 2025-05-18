package net.twinte.api.timetable.v1

import io.grpc.CallOptions
import io.grpc.CallOptions.DEFAULT
import io.grpc.Channel
import io.grpc.Metadata
import io.grpc.MethodDescriptor
import io.grpc.ServerServiceDefinition
import io.grpc.ServerServiceDefinition.builder
import io.grpc.ServiceDescriptor
import io.grpc.Status.UNIMPLEMENTED
import io.grpc.StatusException
import io.grpc.kotlin.AbstractCoroutineServerImpl
import io.grpc.kotlin.AbstractCoroutineStub
import io.grpc.kotlin.ClientCalls.unaryRpc
import io.grpc.kotlin.ServerCalls.unaryServerMethodDefinition
import io.grpc.kotlin.StubFor
import kotlin.String
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic
import net.twinte.api.timetable.v1.TimetableServiceGrpc.getServiceDescriptor

/**
 * Holder for Kotlin coroutine-based client and server APIs for timetable.v1.TimetableService.
 */
public object TimetableServiceGrpcKt {
  public const val SERVICE_NAME: String = TimetableServiceGrpc.SERVICE_NAME

  @JvmStatic
  public val serviceDescriptor: ServiceDescriptor
    get() = getServiceDescriptor()

  public val listCoursesByCodesMethod:
      MethodDescriptor<Service.ListCoursesByCodesRequest, Service.ListCoursesByCodesResponse>
    @JvmStatic
    get() = TimetableServiceGrpc.getListCoursesByCodesMethod()

  public val searchCoursesMethod:
      MethodDescriptor<Service.SearchCoursesRequest, Service.SearchCoursesResponse>
    @JvmStatic
    get() = TimetableServiceGrpc.getSearchCoursesMethod()

  public val createRegisteredCoursesByCodesMethod:
      MethodDescriptor<Service.CreateRegisteredCoursesByCodesRequest, Service.CreateRegisteredCoursesByCodesResponse>
    @JvmStatic
    get() = TimetableServiceGrpc.getCreateRegisteredCoursesByCodesMethod()

  public val createRegisteredCourseManuallyMethod:
      MethodDescriptor<Service.CreateRegisteredCourseManuallyRequest, Service.CreateRegisteredCourseManuallyResponse>
    @JvmStatic
    get() = TimetableServiceGrpc.getCreateRegisteredCourseManuallyMethod()

  public val listRegisteredCoursesMethod:
      MethodDescriptor<Service.ListRegisteredCoursesRequest, Service.ListRegisteredCoursesResponse>
    @JvmStatic
    get() = TimetableServiceGrpc.getListRegisteredCoursesMethod()

  public val updateRegisteredCourseMethod:
      MethodDescriptor<Service.UpdateRegisteredCourseRequest, Service.UpdateRegisteredCourseResponse>
    @JvmStatic
    get() = TimetableServiceGrpc.getUpdateRegisteredCourseMethod()

  public val deleteRegisteredCourseMethod:
      MethodDescriptor<Service.DeleteRegisteredCourseRequest, Service.DeleteRegisteredCourseResponse>
    @JvmStatic
    get() = TimetableServiceGrpc.getDeleteRegisteredCourseMethod()

  public val createTagMethod: MethodDescriptor<Service.CreateTagRequest, Service.CreateTagResponse>
    @JvmStatic
    get() = TimetableServiceGrpc.getCreateTagMethod()

  public val listTagsMethod: MethodDescriptor<Service.ListTagsRequest, Service.ListTagsResponse>
    @JvmStatic
    get() = TimetableServiceGrpc.getListTagsMethod()

  public val updateTagMethod: MethodDescriptor<Service.UpdateTagRequest, Service.UpdateTagResponse>
    @JvmStatic
    get() = TimetableServiceGrpc.getUpdateTagMethod()

  public val deleteTagMethod: MethodDescriptor<Service.DeleteTagRequest, Service.DeleteTagResponse>
    @JvmStatic
    get() = TimetableServiceGrpc.getDeleteTagMethod()

  public val rearrangeTagsMethod:
      MethodDescriptor<Service.RearrangeTagsRequest, Service.RearrangeTagsResponse>
    @JvmStatic
    get() = TimetableServiceGrpc.getRearrangeTagsMethod()

  /**
   * A stub for issuing RPCs to a(n) timetable.v1.TimetableService service as suspending coroutines.
   */
  @StubFor(TimetableServiceGrpc::class)
  public class TimetableServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT,
  ) : AbstractCoroutineStub<TimetableServiceCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): TimetableServiceCoroutineStub =
        TimetableServiceCoroutineStub(channel, callOptions)

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun listCoursesByCodes(request: Service.ListCoursesByCodesRequest,
        headers: Metadata = Metadata()): Service.ListCoursesByCodesResponse = unaryRpc(
      channel,
      TimetableServiceGrpc.getListCoursesByCodesMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun searchCourses(request: Service.SearchCoursesRequest, headers: Metadata =
        Metadata()): Service.SearchCoursesResponse = unaryRpc(
      channel,
      TimetableServiceGrpc.getSearchCoursesMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend
        fun createRegisteredCoursesByCodes(request: Service.CreateRegisteredCoursesByCodesRequest,
        headers: Metadata = Metadata()): Service.CreateRegisteredCoursesByCodesResponse = unaryRpc(
      channel,
      TimetableServiceGrpc.getCreateRegisteredCoursesByCodesMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend
        fun createRegisteredCourseManually(request: Service.CreateRegisteredCourseManuallyRequest,
        headers: Metadata = Metadata()): Service.CreateRegisteredCourseManuallyResponse = unaryRpc(
      channel,
      TimetableServiceGrpc.getCreateRegisteredCourseManuallyMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun listRegisteredCourses(request: Service.ListRegisteredCoursesRequest,
        headers: Metadata = Metadata()): Service.ListRegisteredCoursesResponse = unaryRpc(
      channel,
      TimetableServiceGrpc.getListRegisteredCoursesMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun updateRegisteredCourse(request: Service.UpdateRegisteredCourseRequest,
        headers: Metadata = Metadata()): Service.UpdateRegisteredCourseResponse = unaryRpc(
      channel,
      TimetableServiceGrpc.getUpdateRegisteredCourseMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun deleteRegisteredCourse(request: Service.DeleteRegisteredCourseRequest,
        headers: Metadata = Metadata()): Service.DeleteRegisteredCourseResponse = unaryRpc(
      channel,
      TimetableServiceGrpc.getDeleteRegisteredCourseMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun createTag(request: Service.CreateTagRequest, headers: Metadata = Metadata()):
        Service.CreateTagResponse = unaryRpc(
      channel,
      TimetableServiceGrpc.getCreateTagMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun listTags(request: Service.ListTagsRequest, headers: Metadata = Metadata()):
        Service.ListTagsResponse = unaryRpc(
      channel,
      TimetableServiceGrpc.getListTagsMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun updateTag(request: Service.UpdateTagRequest, headers: Metadata = Metadata()):
        Service.UpdateTagResponse = unaryRpc(
      channel,
      TimetableServiceGrpc.getUpdateTagMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun deleteTag(request: Service.DeleteTagRequest, headers: Metadata = Metadata()):
        Service.DeleteTagResponse = unaryRpc(
      channel,
      TimetableServiceGrpc.getDeleteTagMethod(),
      request,
      callOptions,
      headers
    )

    /**
     * Executes this RPC and returns the response message, suspending until the RPC completes
     * with [`Status.OK`][io.grpc.Status].  If the RPC completes with another status, a
     * corresponding
     * [StatusException] is thrown.  If this coroutine is cancelled, the RPC is also cancelled
     * with the corresponding exception as a cause.
     *
     * @param request The request message to send to the server.
     *
     * @param headers Metadata to attach to the request.  Most users will not need this.
     *
     * @return The single response from the server.
     */
    public suspend fun rearrangeTags(request: Service.RearrangeTagsRequest, headers: Metadata =
        Metadata()): Service.RearrangeTagsResponse = unaryRpc(
      channel,
      TimetableServiceGrpc.getRearrangeTagsMethod(),
      request,
      callOptions,
      headers
    )
  }

  /**
   * Skeletal implementation of the timetable.v1.TimetableService service based on Kotlin
   * coroutines.
   */
  public abstract class TimetableServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns the response to an RPC for timetable.v1.TimetableService.ListCoursesByCodes.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun listCoursesByCodes(request: Service.ListCoursesByCodesRequest):
        Service.ListCoursesByCodesResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method timetable.v1.TimetableService.ListCoursesByCodes is unimplemented"))

    /**
     * Returns the response to an RPC for timetable.v1.TimetableService.SearchCourses.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun searchCourses(request: Service.SearchCoursesRequest):
        Service.SearchCoursesResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method timetable.v1.TimetableService.SearchCourses is unimplemented"))

    /**
     * Returns the response to an RPC for
     * timetable.v1.TimetableService.CreateRegisteredCoursesByCodes.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend
        fun createRegisteredCoursesByCodes(request: Service.CreateRegisteredCoursesByCodesRequest):
        Service.CreateRegisteredCoursesByCodesResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method timetable.v1.TimetableService.CreateRegisteredCoursesByCodes is unimplemented"))

    /**
     * Returns the response to an RPC for
     * timetable.v1.TimetableService.CreateRegisteredCourseManually.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend
        fun createRegisteredCourseManually(request: Service.CreateRegisteredCourseManuallyRequest):
        Service.CreateRegisteredCourseManuallyResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method timetable.v1.TimetableService.CreateRegisteredCourseManually is unimplemented"))

    /**
     * Returns the response to an RPC for timetable.v1.TimetableService.ListRegisteredCourses.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun listRegisteredCourses(request: Service.ListRegisteredCoursesRequest):
        Service.ListRegisteredCoursesResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method timetable.v1.TimetableService.ListRegisteredCourses is unimplemented"))

    /**
     * Returns the response to an RPC for timetable.v1.TimetableService.UpdateRegisteredCourse.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun updateRegisteredCourse(request: Service.UpdateRegisteredCourseRequest):
        Service.UpdateRegisteredCourseResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method timetable.v1.TimetableService.UpdateRegisteredCourse is unimplemented"))

    /**
     * Returns the response to an RPC for timetable.v1.TimetableService.DeleteRegisteredCourse.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun deleteRegisteredCourse(request: Service.DeleteRegisteredCourseRequest):
        Service.DeleteRegisteredCourseResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method timetable.v1.TimetableService.DeleteRegisteredCourse is unimplemented"))

    /**
     * Returns the response to an RPC for timetable.v1.TimetableService.CreateTag.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun createTag(request: Service.CreateTagRequest): Service.CreateTagResponse
        = throw
        StatusException(UNIMPLEMENTED.withDescription("Method timetable.v1.TimetableService.CreateTag is unimplemented"))

    /**
     * Returns the response to an RPC for timetable.v1.TimetableService.ListTags.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun listTags(request: Service.ListTagsRequest): Service.ListTagsResponse =
        throw
        StatusException(UNIMPLEMENTED.withDescription("Method timetable.v1.TimetableService.ListTags is unimplemented"))

    /**
     * Returns the response to an RPC for timetable.v1.TimetableService.UpdateTag.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun updateTag(request: Service.UpdateTagRequest): Service.UpdateTagResponse
        = throw
        StatusException(UNIMPLEMENTED.withDescription("Method timetable.v1.TimetableService.UpdateTag is unimplemented"))

    /**
     * Returns the response to an RPC for timetable.v1.TimetableService.DeleteTag.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun deleteTag(request: Service.DeleteTagRequest): Service.DeleteTagResponse
        = throw
        StatusException(UNIMPLEMENTED.withDescription("Method timetable.v1.TimetableService.DeleteTag is unimplemented"))

    /**
     * Returns the response to an RPC for timetable.v1.TimetableService.RearrangeTags.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun rearrangeTags(request: Service.RearrangeTagsRequest):
        Service.RearrangeTagsResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method timetable.v1.TimetableService.RearrangeTags is unimplemented"))

    final override fun bindService(): ServerServiceDefinition = builder(getServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = TimetableServiceGrpc.getListCoursesByCodesMethod(),
      implementation = ::listCoursesByCodes
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = TimetableServiceGrpc.getSearchCoursesMethod(),
      implementation = ::searchCourses
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = TimetableServiceGrpc.getCreateRegisteredCoursesByCodesMethod(),
      implementation = ::createRegisteredCoursesByCodes
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = TimetableServiceGrpc.getCreateRegisteredCourseManuallyMethod(),
      implementation = ::createRegisteredCourseManually
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = TimetableServiceGrpc.getListRegisteredCoursesMethod(),
      implementation = ::listRegisteredCourses
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = TimetableServiceGrpc.getUpdateRegisteredCourseMethod(),
      implementation = ::updateRegisteredCourse
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = TimetableServiceGrpc.getDeleteRegisteredCourseMethod(),
      implementation = ::deleteRegisteredCourse
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = TimetableServiceGrpc.getCreateTagMethod(),
      implementation = ::createTag
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = TimetableServiceGrpc.getListTagsMethod(),
      implementation = ::listTags
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = TimetableServiceGrpc.getUpdateTagMethod(),
      implementation = ::updateTag
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = TimetableServiceGrpc.getDeleteTagMethod(),
      implementation = ::deleteTag
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = TimetableServiceGrpc.getRearrangeTagsMethod(),
      implementation = ::rearrangeTags
    )).build()
  }
}
