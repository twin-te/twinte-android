package net.twinte.api.schoolcalendar.v1

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
import net.twinte.api.schoolcalendar.v1.SchoolCalendarServiceGrpc.getServiceDescriptor

/**
 * Holder for Kotlin coroutine-based client and server APIs for
 * schoolcalendar.v1.SchoolCalendarService.
 */
public object SchoolCalendarServiceGrpcKt {
  public const val SERVICE_NAME: String = SchoolCalendarServiceGrpc.SERVICE_NAME

  @JvmStatic
  public val serviceDescriptor: ServiceDescriptor
    get() = getServiceDescriptor()

  public val listEventsByDateMethod:
      MethodDescriptor<Service.ListEventsByDateRequest, Service.ListEventsByDateResponse>
    @JvmStatic
    get() = SchoolCalendarServiceGrpc.getListEventsByDateMethod()

  public val getModuleByDateMethod:
      MethodDescriptor<Service.GetModuleByDateRequest, Service.GetModuleByDateResponse>
    @JvmStatic
    get() = SchoolCalendarServiceGrpc.getGetModuleByDateMethod()

  /**
   * A stub for issuing RPCs to a(n) schoolcalendar.v1.SchoolCalendarService service as suspending
   * coroutines.
   */
  @StubFor(SchoolCalendarServiceGrpc::class)
  public class SchoolCalendarServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT,
  ) : AbstractCoroutineStub<SchoolCalendarServiceCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions):
        SchoolCalendarServiceCoroutineStub = SchoolCalendarServiceCoroutineStub(channel,
        callOptions)

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
    public suspend fun listEventsByDate(request: Service.ListEventsByDateRequest, headers: Metadata
        = Metadata()): Service.ListEventsByDateResponse = unaryRpc(
      channel,
      SchoolCalendarServiceGrpc.getListEventsByDateMethod(),
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
    public suspend fun getModuleByDate(request: Service.GetModuleByDateRequest, headers: Metadata =
        Metadata()): Service.GetModuleByDateResponse = unaryRpc(
      channel,
      SchoolCalendarServiceGrpc.getGetModuleByDateMethod(),
      request,
      callOptions,
      headers
    )
  }

  /**
   * Skeletal implementation of the schoolcalendar.v1.SchoolCalendarService service based on Kotlin
   * coroutines.
   */
  public abstract class SchoolCalendarServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns the response to an RPC for schoolcalendar.v1.SchoolCalendarService.ListEventsByDate.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun listEventsByDate(request: Service.ListEventsByDateRequest):
        Service.ListEventsByDateResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method schoolcalendar.v1.SchoolCalendarService.ListEventsByDate is unimplemented"))

    /**
     * Returns the response to an RPC for schoolcalendar.v1.SchoolCalendarService.GetModuleByDate.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun getModuleByDate(request: Service.GetModuleByDateRequest):
        Service.GetModuleByDateResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method schoolcalendar.v1.SchoolCalendarService.GetModuleByDate is unimplemented"))

    final override fun bindService(): ServerServiceDefinition = builder(getServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = SchoolCalendarServiceGrpc.getListEventsByDateMethod(),
      implementation = ::listEventsByDate
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = SchoolCalendarServiceGrpc.getGetModuleByDateMethod(),
      implementation = ::getModuleByDate
    )).build()
  }
}
