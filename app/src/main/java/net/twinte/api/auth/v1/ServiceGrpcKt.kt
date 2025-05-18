package net.twinte.api.auth.v1

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
import net.twinte.api.auth.v1.AuthServiceGrpc.getServiceDescriptor

/**
 * Holder for Kotlin coroutine-based client and server APIs for auth.v1.AuthService.
 */
public object AuthServiceGrpcKt {
  public const val SERVICE_NAME: String = AuthServiceGrpc.SERVICE_NAME

  @JvmStatic
  public val serviceDescriptor: ServiceDescriptor
    get() = getServiceDescriptor()

  public val getMeMethod: MethodDescriptor<Service.GetMeRequest, Service.GetMeResponse>
    @JvmStatic
    get() = AuthServiceGrpc.getGetMeMethod()

  public val deleteUserAuthenticationMethod:
      MethodDescriptor<Service.DeleteUserAuthenticationRequest, Service.DeleteUserAuthenticationResponse>
    @JvmStatic
    get() = AuthServiceGrpc.getDeleteUserAuthenticationMethod()

  public val deleteAccountMethod:
      MethodDescriptor<Service.DeleteAccountRequest, Service.DeleteAccountResponse>
    @JvmStatic
    get() = AuthServiceGrpc.getDeleteAccountMethod()

  /**
   * A stub for issuing RPCs to a(n) auth.v1.AuthService service as suspending coroutines.
   */
  @StubFor(AuthServiceGrpc::class)
  public class AuthServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT,
  ) : AbstractCoroutineStub<AuthServiceCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): AuthServiceCoroutineStub =
        AuthServiceCoroutineStub(channel, callOptions)

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
    public suspend fun getMe(request: Service.GetMeRequest, headers: Metadata = Metadata()):
        Service.GetMeResponse = unaryRpc(
      channel,
      AuthServiceGrpc.getGetMeMethod(),
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
    public suspend fun deleteUserAuthentication(request: Service.DeleteUserAuthenticationRequest,
        headers: Metadata = Metadata()): Service.DeleteUserAuthenticationResponse = unaryRpc(
      channel,
      AuthServiceGrpc.getDeleteUserAuthenticationMethod(),
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
    public suspend fun deleteAccount(request: Service.DeleteAccountRequest, headers: Metadata =
        Metadata()): Service.DeleteAccountResponse = unaryRpc(
      channel,
      AuthServiceGrpc.getDeleteAccountMethod(),
      request,
      callOptions,
      headers
    )
  }

  /**
   * Skeletal implementation of the auth.v1.AuthService service based on Kotlin coroutines.
   */
  public abstract class AuthServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns the response to an RPC for auth.v1.AuthService.GetMe.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun getMe(request: Service.GetMeRequest): Service.GetMeResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method auth.v1.AuthService.GetMe is unimplemented"))

    /**
     * Returns the response to an RPC for auth.v1.AuthService.DeleteUserAuthentication.
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
        fun deleteUserAuthentication(request: Service.DeleteUserAuthenticationRequest):
        Service.DeleteUserAuthenticationResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method auth.v1.AuthService.DeleteUserAuthentication is unimplemented"))

    /**
     * Returns the response to an RPC for auth.v1.AuthService.DeleteAccount.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun deleteAccount(request: Service.DeleteAccountRequest):
        Service.DeleteAccountResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method auth.v1.AuthService.DeleteAccount is unimplemented"))

    final override fun bindService(): ServerServiceDefinition = builder(getServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = AuthServiceGrpc.getGetMeMethod(),
      implementation = ::getMe
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = AuthServiceGrpc.getDeleteUserAuthenticationMethod(),
      implementation = ::deleteUserAuthentication
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = AuthServiceGrpc.getDeleteAccountMethod(),
      implementation = ::deleteAccount
    )).build()
  }
}
