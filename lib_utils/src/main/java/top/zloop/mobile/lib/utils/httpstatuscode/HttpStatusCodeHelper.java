package top.zloop.mobile.lib.utils.httpstatuscode;

public class HttpStatusCodeHelper {
    /**
     * Gets Http Status Code Information for given Code. No Reflection, No Enum
     * @param code
     * @return type + text
     */
    public static String getHttpTypeAndTextByCode(final int code) {

        //region 1xx
        if (code==HttpStatusCodes.Continue.getCode()) {
            return HttpStatusCodes.Continue.toString();
        }else if (code==HttpStatusCodes.SwitchingProtocols.getCode()) {
            return HttpStatusCodes.SwitchingProtocols.toString();
        }else if (code==HttpStatusCodes.Processing.getCode()) {
            return HttpStatusCodes.Processing.toString();
        }
        //endregion

        //region 2xx
        else if (code==HttpStatusCodes.Ok.getCode()) {
            return HttpStatusCodes.Ok.toString();
        }else if (code==HttpStatusCodes.Created.getCode()) {
            return HttpStatusCodes.Created.toString();
        }else if (code==HttpStatusCodes.Accepted.getCode()) {
            return HttpStatusCodes.Accepted.toString();
        }else if (code==HttpStatusCodes.NonAuthoritativeInformation.getCode()) {
            return HttpStatusCodes.NonAuthoritativeInformation.toString();
        }else if (code==HttpStatusCodes.NoContent.getCode()) {
            return HttpStatusCodes.NoContent.toString();
        }else if (code==HttpStatusCodes.ResetContent.getCode()) {
            return HttpStatusCodes.ResetContent.toString();
        }else if (code==HttpStatusCodes.PartialContent.getCode()) {
            return HttpStatusCodes.PartialContent.toString();
        }else if (code==HttpStatusCodes.MultiStatus.getCode()) {
            return HttpStatusCodes.MultiStatus.toString();
        }else if (code==HttpStatusCodes.AlreadyReported.getCode()) {
            return HttpStatusCodes.AlreadyReported.toString();
        }else if (code==HttpStatusCodes.ImUsed.getCode()) {
            return HttpStatusCodes.ImUsed.toString();
        }
        //endregion

        //region 3xx
        else if (code==HttpStatusCodes.MultipleChoices.getCode()) {
            return HttpStatusCodes.MultipleChoices.toString();
        }else if (code==HttpStatusCodes.MovedPermanently.getCode()) {
            return HttpStatusCodes.MovedPermanently.toString();
        }else if (code==HttpStatusCodes.Found.getCode()) {
            return HttpStatusCodes.Found.toString();
        }else if (code==HttpStatusCodes.SeeOther.getCode()) {
            return HttpStatusCodes.SeeOther.toString();
        }else if (code==HttpStatusCodes.NotModified.getCode()) {
            return HttpStatusCodes.NotModified.toString();
        }else if (code==HttpStatusCodes.UseProxy.getCode()) {
            return HttpStatusCodes.UseProxy.toString();
        }else if (code==HttpStatusCodes.Reserved.getCode()) {
            return HttpStatusCodes.Reserved.toString();
        }else if (code==HttpStatusCodes.TemporaryRedirect.getCode()) {
            return HttpStatusCodes.TemporaryRedirect.toString();
        }else if (code==HttpStatusCodes.PermanentRedirect.getCode()) {
            return HttpStatusCodes.PermanentRedirect.toString();
        }
        //endregion


        //region 4xx
        else if (code==HttpStatusCodes.BadRequest.getCode()) {
            return HttpStatusCodes.BadRequest.toString();
        }else if (code==HttpStatusCodes.Unauthorized.getCode()) {
            return HttpStatusCodes.Unauthorized.toString();
        }else if (code==HttpStatusCodes.PaymentRequired.getCode()) {
            return HttpStatusCodes.PaymentRequired.toString();
        }else if (code==HttpStatusCodes.Forbidden.getCode()) {
            return HttpStatusCodes.Forbidden.toString();
        }else if (code==HttpStatusCodes.NotFound.getCode()) {
            return HttpStatusCodes.NotFound.toString();
        }else if (code==HttpStatusCodes.MethodNotAllowed.getCode()) {
            return HttpStatusCodes.MethodNotAllowed.toString();
        }else if (code==HttpStatusCodes.NotAcceptable.getCode()) {
            return HttpStatusCodes.NotAcceptable.toString();
        }else if (code==HttpStatusCodes.ProxyAuthenticationRequired.getCode()) {
            return HttpStatusCodes.ProxyAuthenticationRequired.toString();
        }else if (code==HttpStatusCodes.RequestTimeout.getCode()) {
            return HttpStatusCodes.RequestTimeout.toString();
        }else if (code==HttpStatusCodes.Conflict.getCode()) {
            return HttpStatusCodes.Conflict.toString();
        }else if (code==HttpStatusCodes.Gone.getCode()) {
            return HttpStatusCodes.Gone.toString();
        }else if (code==HttpStatusCodes.LengthRequired.getCode()) {
            return HttpStatusCodes.LengthRequired.toString();
        }else if (code==HttpStatusCodes.PreconditionFailed.getCode()) {
            return HttpStatusCodes.PreconditionFailed.toString();
        }else if (code==HttpStatusCodes.RequestEntityTooLarge.getCode()) {
            return HttpStatusCodes.RequestEntityTooLarge.toString();
        }else if (code==HttpStatusCodes.RequestUriTooLong.getCode()) {
            return HttpStatusCodes.RequestUriTooLong.toString();
        }else if (code==HttpStatusCodes.UnsupportedMediaType.getCode()) {
            return HttpStatusCodes.UnsupportedMediaType.toString();
        }else if (code==HttpStatusCodes.RequestedRangeNotSatisfiable.getCode()) {
            return HttpStatusCodes.RequestedRangeNotSatisfiable.toString();
        }else if (code==HttpStatusCodes.ExpectationFailed.getCode()) {
            return HttpStatusCodes.ExpectationFailed.toString();
        }else if (code==HttpStatusCodes.ImATeapot.getCode()) {
            return HttpStatusCodes.ImATeapot.toString();
        }else if (code==HttpStatusCodes.EnhanceYourCalm.getCode()) {
            return HttpStatusCodes.EnhanceYourCalm.toString();
        }else if (code==HttpStatusCodes.UnprocessableEntity.getCode()) {
            return HttpStatusCodes.UnprocessableEntity.toString();
        }else if (code==HttpStatusCodes.Locked.getCode()) {
            return HttpStatusCodes.Locked.toString();
        }else if (code==HttpStatusCodes.FailedDependency.getCode()) {
            return HttpStatusCodes.FailedDependency.toString();
        }else if (code==HttpStatusCodes.UnorderedCollection.getCode()) {
            return HttpStatusCodes.UnorderedCollection.toString();
        }else if (code==HttpStatusCodes.UpgradeRequired.getCode()) {
            return HttpStatusCodes.UpgradeRequired.toString();
        }else if (code==HttpStatusCodes.PreconditionRequired.getCode()) {
            return HttpStatusCodes.PreconditionRequired.toString();
        }else if (code==HttpStatusCodes.TooManyRequests.getCode()) {
            return HttpStatusCodes.TooManyRequests.toString();
        }else if (code==HttpStatusCodes.RequestHeaderFieldsTooLarge.getCode()) {
            return HttpStatusCodes.RequestHeaderFieldsTooLarge.toString();
        }else if (code==HttpStatusCodes.NoResponse.getCode()) {
            return HttpStatusCodes.NoResponse.toString();
        }else if (code==HttpStatusCodes.RetryWith.getCode()) {
            return HttpStatusCodes.RetryWith.toString();
        }else if (code==HttpStatusCodes.BlockedByWindowsParentalControls.getCode()) {
            return HttpStatusCodes.BlockedByWindowsParentalControls.toString();
        }else if (code==HttpStatusCodes.UnavailableForLegalReasons.getCode()) {
            return HttpStatusCodes.UnavailableForLegalReasons.toString();
        }else if (code==HttpStatusCodes.ClientClosedRequest.getCode()) {
            return HttpStatusCodes.ClientClosedRequest.toString();
        }
        //endregion

        //region 5xx
        else if (code==HttpStatusCodes.InternalServerError.getCode()) {
            return HttpStatusCodes.InternalServerError.toString();
        }else if (code==HttpStatusCodes.NotImplemented.getCode()) {
            return HttpStatusCodes.NotImplemented.toString();
        }else if (code==HttpStatusCodes.BadGateway.getCode()) {
            return HttpStatusCodes.BadGateway.toString();
        }else if (code==HttpStatusCodes.ServiceUnavailable.getCode()) {
            return HttpStatusCodes.ServiceUnavailable.toString();
        }else if (code==HttpStatusCodes.GatewayTimeout.getCode()) {
            return HttpStatusCodes.GatewayTimeout.toString();
        }else if (code==HttpStatusCodes.HttpVersionNotSupported.getCode()) {
            return HttpStatusCodes.HttpVersionNotSupported.toString();
        }else if (code==HttpStatusCodes.VariantAlsoNegotiates.getCode()) {
            return HttpStatusCodes.VariantAlsoNegotiates.toString();
        }else if (code==HttpStatusCodes.InsufficientStorage.getCode()) {
            return HttpStatusCodes.InsufficientStorage.toString();
        }else if (code==HttpStatusCodes.LoopDetected.getCode()) {
            return HttpStatusCodes.LoopDetected.toString();
        }else if (code==HttpStatusCodes.BandwidthLimitExceeded.getCode()) {
            return HttpStatusCodes.BandwidthLimitExceeded.toString();
        }else if (code==HttpStatusCodes.NotExtended.getCode()) {
            return HttpStatusCodes.NotExtended.toString();
        }else if (code==HttpStatusCodes.NetworkAuthenticationRequired.getCode()) {
            return HttpStatusCodes.NetworkAuthenticationRequired.toString();
        }else if (code==HttpStatusCodes.NetworkReadTimeoutError.getCode()) {
            return HttpStatusCodes.NetworkReadTimeoutError.toString();
        }else if (code==HttpStatusCodes.NetworkConnectTimeoutError.getCode()) {
            return HttpStatusCodes.NetworkConnectTimeoutError.toString();
        }
        //endregion


        else{
            return "WTF Error: Given Http Code Does Not Have a Match";
        }
    }
}
