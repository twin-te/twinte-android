package net.twinte.api.unified.v1

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
import net.twinte.api.unified.v1.UnifiedServiceGrpc.getServiceDescriptor

/**
 * Holder for Kotlin coroutine-based client and server APIs for unified.v1.UnifiedService.
 */
public object UnifiedServiceGrpcKt {
  public const val SERVICE_NAME: String = UnifiedServiceGrpc.SERVICE_NAME

  @JvmStatic
  public val serviceDescriptor: ServiceDescriptor
    get() = getServiceDescriptor()

  public val getByDateMethod: MethodDescriptor<Service.GetByDateRequest, Service.GetByDateResponse>
    @JvmStatic
    get() = UnifiedServiceGrpc.getGetByDateMethod()

  /**
   * A stub for issuing RPCs to a(n) unified.v1.UnifiedService service as suspending coroutines.
   */
  @StubFor(UnifiedServiceGrpc::class)
  public class UnifiedServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT,
  ) : AbstractCoroutineStub<UnifiedServiceCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): UnifiedServiceCoroutineStub =
        UnifiedServiceCoroutineStub(channel, callOptions)

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
    public suspend fun getByDate(request: Service.GetByDateRequest, headers: Metadata = Metadata()):
        Service.GetByDateResponse = unaryRpc(
      channel,
      UnifiedServiceGrpc.getGetByDateMethod(),
      request,
      callOptions,
      headers
    )
  }

  /**
   * Skeletal implementation of the unified.v1.UnifiedService service based on Kotlin coroutines.
   */
  public abstract class UnifiedServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns the response to an RPC for unified.v1.UnifiedService.GetByDate.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun getByDate(request: Service.GetByDateRequest): Service.GetByDateResponse
        = throw
        StatusException(UNIMPLEMENTED.withDescription("Method unified.v1.UnifiedService.GetByDate is unimplemented"))

    final override fun bindService(): ServerServiceDefinition = builder(getServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = UnifiedServiceGrpc.getGetByDateMethod(),
      implementation = ::getByDate
    )).build()
  }
}
