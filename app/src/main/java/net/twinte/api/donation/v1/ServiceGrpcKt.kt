package net.twinte.api.donation.v1

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
import net.twinte.api.donation.v1.DonationServiceGrpc.getServiceDescriptor

/**
 * Holder for Kotlin coroutine-based client and server APIs for donation.v1.DonationService.
 */
public object DonationServiceGrpcKt {
  public const val SERVICE_NAME: String = DonationServiceGrpc.SERVICE_NAME

  @JvmStatic
  public val serviceDescriptor: ServiceDescriptor
    get() = getServiceDescriptor()

  public val createOneTimeCheckoutSessionMethod:
      MethodDescriptor<Service.CreateOneTimeCheckoutSessionRequest, Service.CreateOneTimeCheckoutSessionResponse>
    @JvmStatic
    get() = DonationServiceGrpc.getCreateOneTimeCheckoutSessionMethod()

  public val createSubscriptionCheckoutSessionMethod:
      MethodDescriptor<Service.CreateSubscriptionCheckoutSessionRequest, Service.CreateSubscriptionCheckoutSessionResponse>
    @JvmStatic
    get() = DonationServiceGrpc.getCreateSubscriptionCheckoutSessionMethod()

  public val getPaymentUserMethod:
      MethodDescriptor<Service.GetPaymentUserRequest, Service.GetPaymentUserResponse>
    @JvmStatic
    get() = DonationServiceGrpc.getGetPaymentUserMethod()

  public val updatePaymentUserMethod:
      MethodDescriptor<Service.UpdatePaymentUserRequest, Service.UpdatePaymentUserResponse>
    @JvmStatic
    get() = DonationServiceGrpc.getUpdatePaymentUserMethod()

  public val listPaymentHistoriesMethod:
      MethodDescriptor<Service.ListPaymentHistoriesRequest, Service.ListPaymentHistoriesResponse>
    @JvmStatic
    get() = DonationServiceGrpc.getListPaymentHistoriesMethod()

  public val listSubscriptionPlansMethod:
      MethodDescriptor<Service.ListSubscriptionPlansRequest, Service.ListSubscriptionPlansResponse>
    @JvmStatic
    get() = DonationServiceGrpc.getListSubscriptionPlansMethod()

  public val getActiveSubscriptionMethod:
      MethodDescriptor<Service.GetActiveSubscriptionRequest, Service.GetActiveSubscriptionResponse>
    @JvmStatic
    get() = DonationServiceGrpc.getGetActiveSubscriptionMethod()

  public val unsubscribeMethod:
      MethodDescriptor<Service.UnsubscribeRequest, Service.UnsubscribeResponse>
    @JvmStatic
    get() = DonationServiceGrpc.getUnsubscribeMethod()

  public val getTotalAmountMethod:
      MethodDescriptor<Service.GetTotalAmountRequest, Service.GetTotalAmountResponse>
    @JvmStatic
    get() = DonationServiceGrpc.getGetTotalAmountMethod()

  public val listContributorsMethod:
      MethodDescriptor<Service.ListContributorsRequest, Service.ListContributorsResponse>
    @JvmStatic
    get() = DonationServiceGrpc.getListContributorsMethod()

  /**
   * A stub for issuing RPCs to a(n) donation.v1.DonationService service as suspending coroutines.
   */
  @StubFor(DonationServiceGrpc::class)
  public class DonationServiceCoroutineStub @JvmOverloads constructor(
    channel: Channel,
    callOptions: CallOptions = DEFAULT,
  ) : AbstractCoroutineStub<DonationServiceCoroutineStub>(channel, callOptions) {
    override fun build(channel: Channel, callOptions: CallOptions): DonationServiceCoroutineStub =
        DonationServiceCoroutineStub(channel, callOptions)

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
        fun createOneTimeCheckoutSession(request: Service.CreateOneTimeCheckoutSessionRequest,
        headers: Metadata = Metadata()): Service.CreateOneTimeCheckoutSessionResponse = unaryRpc(
      channel,
      DonationServiceGrpc.getCreateOneTimeCheckoutSessionMethod(),
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
        fun createSubscriptionCheckoutSession(request: Service.CreateSubscriptionCheckoutSessionRequest,
        headers: Metadata = Metadata()): Service.CreateSubscriptionCheckoutSessionResponse =
        unaryRpc(
      channel,
      DonationServiceGrpc.getCreateSubscriptionCheckoutSessionMethod(),
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
    public suspend fun getPaymentUser(request: Service.GetPaymentUserRequest, headers: Metadata =
        Metadata()): Service.GetPaymentUserResponse = unaryRpc(
      channel,
      DonationServiceGrpc.getGetPaymentUserMethod(),
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
    public suspend fun updatePaymentUser(request: Service.UpdatePaymentUserRequest,
        headers: Metadata = Metadata()): Service.UpdatePaymentUserResponse = unaryRpc(
      channel,
      DonationServiceGrpc.getUpdatePaymentUserMethod(),
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
    public suspend fun listPaymentHistories(request: Service.ListPaymentHistoriesRequest,
        headers: Metadata = Metadata()): Service.ListPaymentHistoriesResponse = unaryRpc(
      channel,
      DonationServiceGrpc.getListPaymentHistoriesMethod(),
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
    public suspend fun listSubscriptionPlans(request: Service.ListSubscriptionPlansRequest,
        headers: Metadata = Metadata()): Service.ListSubscriptionPlansResponse = unaryRpc(
      channel,
      DonationServiceGrpc.getListSubscriptionPlansMethod(),
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
    public suspend fun getActiveSubscription(request: Service.GetActiveSubscriptionRequest,
        headers: Metadata = Metadata()): Service.GetActiveSubscriptionResponse = unaryRpc(
      channel,
      DonationServiceGrpc.getGetActiveSubscriptionMethod(),
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
    public suspend fun unsubscribe(request: Service.UnsubscribeRequest, headers: Metadata =
        Metadata()): Service.UnsubscribeResponse = unaryRpc(
      channel,
      DonationServiceGrpc.getUnsubscribeMethod(),
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
    public suspend fun getTotalAmount(request: Service.GetTotalAmountRequest, headers: Metadata =
        Metadata()): Service.GetTotalAmountResponse = unaryRpc(
      channel,
      DonationServiceGrpc.getGetTotalAmountMethod(),
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
    public suspend fun listContributors(request: Service.ListContributorsRequest, headers: Metadata
        = Metadata()): Service.ListContributorsResponse = unaryRpc(
      channel,
      DonationServiceGrpc.getListContributorsMethod(),
      request,
      callOptions,
      headers
    )
  }

  /**
   * Skeletal implementation of the donation.v1.DonationService service based on Kotlin coroutines.
   */
  public abstract class DonationServiceCoroutineImplBase(
    coroutineContext: CoroutineContext = EmptyCoroutineContext,
  ) : AbstractCoroutineServerImpl(coroutineContext) {
    /**
     * Returns the response to an RPC for donation.v1.DonationService.CreateOneTimeCheckoutSession.
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
        fun createOneTimeCheckoutSession(request: Service.CreateOneTimeCheckoutSessionRequest):
        Service.CreateOneTimeCheckoutSessionResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method donation.v1.DonationService.CreateOneTimeCheckoutSession is unimplemented"))

    /**
     * Returns the response to an RPC for
     * donation.v1.DonationService.CreateSubscriptionCheckoutSession.
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
        fun createSubscriptionCheckoutSession(request: Service.CreateSubscriptionCheckoutSessionRequest):
        Service.CreateSubscriptionCheckoutSessionResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method donation.v1.DonationService.CreateSubscriptionCheckoutSession is unimplemented"))

    /**
     * Returns the response to an RPC for donation.v1.DonationService.GetPaymentUser.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun getPaymentUser(request: Service.GetPaymentUserRequest):
        Service.GetPaymentUserResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method donation.v1.DonationService.GetPaymentUser is unimplemented"))

    /**
     * Returns the response to an RPC for donation.v1.DonationService.UpdatePaymentUser.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun updatePaymentUser(request: Service.UpdatePaymentUserRequest):
        Service.UpdatePaymentUserResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method donation.v1.DonationService.UpdatePaymentUser is unimplemented"))

    /**
     * Returns the response to an RPC for donation.v1.DonationService.ListPaymentHistories.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun listPaymentHistories(request: Service.ListPaymentHistoriesRequest):
        Service.ListPaymentHistoriesResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method donation.v1.DonationService.ListPaymentHistories is unimplemented"))

    /**
     * Returns the response to an RPC for donation.v1.DonationService.ListSubscriptionPlans.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun listSubscriptionPlans(request: Service.ListSubscriptionPlansRequest):
        Service.ListSubscriptionPlansResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method donation.v1.DonationService.ListSubscriptionPlans is unimplemented"))

    /**
     * Returns the response to an RPC for donation.v1.DonationService.GetActiveSubscription.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun getActiveSubscription(request: Service.GetActiveSubscriptionRequest):
        Service.GetActiveSubscriptionResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method donation.v1.DonationService.GetActiveSubscription is unimplemented"))

    /**
     * Returns the response to an RPC for donation.v1.DonationService.Unsubscribe.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun unsubscribe(request: Service.UnsubscribeRequest):
        Service.UnsubscribeResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method donation.v1.DonationService.Unsubscribe is unimplemented"))

    /**
     * Returns the response to an RPC for donation.v1.DonationService.GetTotalAmount.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun getTotalAmount(request: Service.GetTotalAmountRequest):
        Service.GetTotalAmountResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method donation.v1.DonationService.GetTotalAmount is unimplemented"))

    /**
     * Returns the response to an RPC for donation.v1.DonationService.ListContributors.
     *
     * If this method fails with a [StatusException], the RPC will fail with the corresponding
     * [io.grpc.Status].  If this method fails with a [java.util.concurrent.CancellationException],
     * the RPC will fail
     * with status `Status.CANCELLED`.  If this method fails for any other reason, the RPC will
     * fail with `Status.UNKNOWN` with the exception as a cause.
     *
     * @param request The request from the client.
     */
    public open suspend fun listContributors(request: Service.ListContributorsRequest):
        Service.ListContributorsResponse = throw
        StatusException(UNIMPLEMENTED.withDescription("Method donation.v1.DonationService.ListContributors is unimplemented"))

    final override fun bindService(): ServerServiceDefinition = builder(getServiceDescriptor())
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = DonationServiceGrpc.getCreateOneTimeCheckoutSessionMethod(),
      implementation = ::createOneTimeCheckoutSession
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = DonationServiceGrpc.getCreateSubscriptionCheckoutSessionMethod(),
      implementation = ::createSubscriptionCheckoutSession
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = DonationServiceGrpc.getGetPaymentUserMethod(),
      implementation = ::getPaymentUser
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = DonationServiceGrpc.getUpdatePaymentUserMethod(),
      implementation = ::updatePaymentUser
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = DonationServiceGrpc.getListPaymentHistoriesMethod(),
      implementation = ::listPaymentHistories
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = DonationServiceGrpc.getListSubscriptionPlansMethod(),
      implementation = ::listSubscriptionPlans
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = DonationServiceGrpc.getGetActiveSubscriptionMethod(),
      implementation = ::getActiveSubscription
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = DonationServiceGrpc.getUnsubscribeMethod(),
      implementation = ::unsubscribe
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = DonationServiceGrpc.getGetTotalAmountMethod(),
      implementation = ::getTotalAmount
    ))
      .addMethod(unaryServerMethodDefinition(
      context = this.context,
      descriptor = DonationServiceGrpc.getListContributorsMethod(),
      implementation = ::listContributors
    )).build()
  }
}
