package top.zloop.mobile.lib.utils.httpstatuscode;

public class HttpStatusCodes {

    //region 1xx
    public static final StatusCode Continue=new StatusCode(100,"Continue","Informational","");
    public static final StatusCode SwitchingProtocols=new StatusCode(101,"Switching Protocols","Informational","");
    public static final StatusCode Processing=new StatusCode(102,"Processing","Informational","");
    //endregion


    //region 2xx
    public static final StatusCode Ok=new StatusCode(200,"OK","Successful","");
    public static final StatusCode Created=new StatusCode(201,"Created","Successful","");
    public static final StatusCode Accepted=new StatusCode(202,"Accepted","Successful","");
    public static final StatusCode NonAuthoritativeInformation=new StatusCode(203,"Non-Authoritative Information","Successful","");
    public static final StatusCode NoContent=new StatusCode(204,"No Content","Successful","");
    public static final StatusCode ResetContent=new StatusCode(205,"Reset Content","Successful","");
    public static final StatusCode PartialContent=new StatusCode(206,"Partial Content","Successful","");
    public static final StatusCode MultiStatus=new StatusCode(207,"Multi-Status","Successful","");
    public static final StatusCode AlreadyReported=new StatusCode(208,"Already Reported","Successful","");
    public static final StatusCode ImUsed=new StatusCode(226,"IM Used","Successful","");
    //endregion


    //region 3xx
    public static final StatusCode MultipleChoices=new StatusCode(300,"Multiple Choices","Redirection","");
    public static final StatusCode MovedPermanently=new StatusCode(301,"Moved Permanently","Redirection","");
    public static final StatusCode Found=new StatusCode(302,"Found","Redirection","");
    public static final StatusCode SeeOther=new StatusCode(303,"See Other","Redirection","");
    public static final StatusCode NotModified=new StatusCode(304,"Not Modified","Redirection","");
    public static final StatusCode UseProxy=new StatusCode(305,"Use Proxy","Redirection","");
    public static final StatusCode Reserved=new StatusCode(306,"Reserved","Redirection","");
    public static final StatusCode TemporaryRedirect=new StatusCode(307,"Temporary Redirect","Redirection","");
    public static final StatusCode PermanentRedirect=new StatusCode(308,"Permanent Redirect","Redirection","");

    //endregion


    //region 4xx
    public static final StatusCode BadRequest=new StatusCode(400,"Bad Request","Client Error","");
    public static final StatusCode Unauthorized=new StatusCode(401,"Unauthorized","Client Error","");
    public static final StatusCode PaymentRequired=new StatusCode(402,"Payment Required","Client Error","");
    public static final StatusCode Forbidden=new StatusCode(403,"Forbidden","Client Error","");
    public static final StatusCode NotFound=new StatusCode(404,"Not Found","Client Error","");
    public static final StatusCode MethodNotAllowed=new StatusCode(405,"Method Not Allowed","Client Error","");
    public static final StatusCode NotAcceptable=new StatusCode(406,"Not Acceptable","Client Error","");
    public static final StatusCode ProxyAuthenticationRequired=new StatusCode(407,"Proxy Authentication Required","Client Error","");
    public static final StatusCode RequestTimeout=new StatusCode(408,"Request Timeout","Client Error","");
    public static final StatusCode Conflict=new StatusCode(409,"Conflict","Client Error","");
    public static final StatusCode Gone=new StatusCode(410,"Gone","Client Error","");
    public static final StatusCode LengthRequired=new StatusCode(411,"Length Required","Client Error","");
    public static final StatusCode PreconditionFailed=new StatusCode(412,"Precondition Failed","Client Error","");
    public static final StatusCode RequestEntityTooLarge=new StatusCode(413,"Request Entity Too Large","Client Error","");
    public static final StatusCode RequestUriTooLong=new StatusCode(414,"Request-URI Too Long","Client Error","");
    public static final StatusCode UnsupportedMediaType=new StatusCode(415,"Unsupported Media Type","Client Error","");
    public static final StatusCode RequestedRangeNotSatisfiable=new StatusCode(416,"Requested Range Not Satisfiable","Client Error","");
    public static final StatusCode ExpectationFailed=new StatusCode(417,"Expectation Failed","Client Error","");
    public static final StatusCode ImATeapot=new StatusCode(418,"I’m a teapot","Client Error","");
    public static final StatusCode EnhanceYourCalm=new StatusCode(420,"Enhance Your Calm","Client Error","");
    public static final StatusCode UnprocessableEntity=new StatusCode(422,"Unprocessable Entity","Client Error","");
    public static final StatusCode Locked=new StatusCode(423,"Locked","Client Error","");
    public static final StatusCode FailedDependency=new StatusCode(424,"Failed Dependency","Client Error","");
    public static final StatusCode UnorderedCollection=new StatusCode(425,"Unordered Collection","Client Error","");
    public static final StatusCode UpgradeRequired=new StatusCode(426,"Upgrade Required","Client Error","");
    public static final StatusCode PreconditionRequired=new StatusCode(428,"Precondition Required","Client Error","");
    public static final StatusCode TooManyRequests=new StatusCode(429,"Too Many Requests","Client Error","");
    public static final StatusCode RequestHeaderFieldsTooLarge=new StatusCode(431,"Request Header Fields Too Large","Client Error","");
    public static final StatusCode NoResponse=new StatusCode(444,"No Response","Client Error","");
    public static final StatusCode RetryWith=new StatusCode(449,"Retry With","Client Error","");
    public static final StatusCode BlockedByWindowsParentalControls=new StatusCode(450,"Blocked by Windows Parental Controls","Client Error","");
    public static final StatusCode UnavailableForLegalReasons=new StatusCode(451,"Unavailable For Legal Reasons","Client Error","");
    public static final StatusCode ClientClosedRequest=new StatusCode(499,"Client Closed Request","Client Error","");
    //endregion


    //region 5xx
    public static final StatusCode InternalServerError=new StatusCode(500,"Internal Server Error","Server Error","");
    public static final StatusCode NotImplemented=new StatusCode(501,"Not Implemented","Server Error","");
    public static final StatusCode BadGateway=new StatusCode(502,"Bad Gateway","Server Error","");
    public static final StatusCode ServiceUnavailable=new StatusCode(503,"Service Unavailable","Server Error","");
    public static final StatusCode GatewayTimeout=new StatusCode(504,"Gateway Timeout","Server Error","");
    public static final StatusCode HttpVersionNotSupported=new StatusCode(505,"HTTP Version Not Supported","Server Error","");
    public static final StatusCode VariantAlsoNegotiates=new StatusCode(506,"Variant Also Negotiates","Server Error","");
    public static final StatusCode InsufficientStorage=new StatusCode(507,"Insufficient Storage","Server Error","");
    public static final StatusCode LoopDetected=new StatusCode(508,"Loop Detected","Server Error","");
    public static final StatusCode BandwidthLimitExceeded=new StatusCode(509,"Bandwidth Limit Exceeded","Server Error","");
    public static final StatusCode NotExtended=new StatusCode(510,"Not Extended","Server Error","");
    public static final StatusCode NetworkAuthenticationRequired=new StatusCode(511,"Network Authentication Required","Server Error","");
    public static final StatusCode NetworkReadTimeoutError=new StatusCode(598,"Network Read Timeout Error","Server Error","");
    public static final StatusCode NetworkConnectTimeoutError=new StatusCode(599,"Network Connect Timeout Error","Server Error","");
    //endregion
}
