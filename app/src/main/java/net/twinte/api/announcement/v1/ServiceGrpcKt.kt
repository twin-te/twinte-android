package net.twinte.api.announcement.v1

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
import net.twinte.api.announcement.v1.AnnouncementServiceGrpc.getServiceDescriptor

/**
 * Holder for Kotlin coroutine-based client and server APIs for announcement.v1.AnnouncementService.
 */
public object AnnouncementServiceGrpcKt {
  public const val SERVICE_NAME: String = AnnouncementServiceGrpc.SERVICE_NAME

  @JvmStatic
  public val serviceDescriptor: ServiceDescriptor
    get() = getServiceDescriptor()

  public val listAnnouncementsMethod:
      MethodDescriptor<Service.ListAnnouncementsRequest, Service.ListAnnouncementsResponse>
    @JvmStatic
    get() = AnnouncementServiceGrpc.getListAnnouncementsMethod()

  public val readAnnouncementsMethod:
      MethodDescriptor<Service.ReadAnnouncementsRequest, Service.ReadAnnouncementsResponse>
    @JvmStatic
    get() = AnnouncementServiceGrpc.getReadAnnouncementsMethod()

  /**
   * A stub for issuing RPCs to a(n) announcement.v1.AnnouncementService service as suspending
   * coroutines.
   */
  @StubFor(AnnouncementServiceGrpc::class)
  public class AnnouncementServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT,
  ) : AbstractCoroutineStub<AnnouncementServiceCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): AnnouncementServiceCoroutineStub
        = AnnouncementServiceCoroutineStub(channel, callOptions)

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
    public suspend fun listAnnouncements(request: Service.ListAnnouncementsRequest,
        headers: Metadata = Metadata()): Service.ListAnnouncementsResponse = unaryRpc(
      channel,
      AnnouncementServiceGrpc.getListAnnouncementsMethod(),
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
    public suspend fun readAnnouncements(request: Service.ReadAnnouncementsRequest,
        headers: Metadata = Metadata()): Service.ReadAnnouncementsResponse = unaryRpc(
      channel,
      AnnouncementServiceGrpc.getReadAnnouncementsMethod(),
      request,
      callOptions,
      headers
    )
  }

  /**
   * Skeletal implementation of the announcement.v1.AnnouncementService service based on Kotlin
   * coroutines.
   */
  public abstract class AnnouncementServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns the response to an RPC for announcement.v1.AnnouncementService.ListAnnouncements.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun listAnnouncements(request: Service.ListAnnouncementsRequest):
        Service.ListAnnouncementsResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method announcement.v1.AnnouncementService.ListAnnouncements is unimplemented"))

    /**
     * Returns the response to an RPC for announcement.v1.AnnouncementService.ReadAnnouncements.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun readAnnouncements(request: Service.ReadAnnouncementsRequest):
        Service.ReadAnnouncementsResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method announcement.v1.AnnouncementService.ReadAnnouncements is unimplemented"))

    final override fun bindService(): ServerServiceDefinition = builder(getServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = AnnouncementServiceGrpc.getListAnnouncementsMethod(),
      implementation = ::listAnnouncements
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = AnnouncementServiceGrpc.getReadAnnouncementsMethod(),
      implementation = ::readAnnouncements
    )).build()
  }
}
