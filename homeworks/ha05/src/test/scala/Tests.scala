import components.traits.{ContentHolder, HeaderHolder, MethodHolder, PathHolder, StatusHolder}
import impl.{HttpMessage, HttpRequest, HttpResponse, Main}
import org.junit.Assert.{assertEquals, assertFalse, assertNull, assertTrue}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import util.Method._
import util.{Data, Method}

import scala.collection.immutable.HashMap

class Tests extends AnyFlatSpec with should.Matchers {

	private val data = Data.readMessages("HttpMessages.csv")
	private val messages = Main.parseHttpMessages(data)

	private val expectedCountPerHeader = List(("Connection",8791), ("Content-Type",5143), ("content-length",5027), ("Host",4846), ("User-Agent",4805), ("Accept-Encoding",4790), ("Content-Length",4609), ("Date",4303), ("Cache-Control",3996), ("Server",3895), ("Via",3704), ("Expires",3693), ("Vary",3651), ("X-Cache",3635), ("ETag",3544), ("Last-Modified",3489), ("Accept-Ranges",3472), ("Expect-CT",3464), ("X-Amz-Cf-Id",3443), ("X-Amz-Cf-Pop",3443), ("CF-Cache-Status",3361), ("CF-RAY",3340), ("Age",3283), ("Accept-Language",702), ("Alt-Svc",491), ("date",441), ("content-type",432), ("server",423), ("x-content-type-options",408), ("Authorization",394), ("cache-control",389), ("Accept",387), ("access-control-allow-origin",375), ("X-Content-Type-Options",279), ("strict-transport-security",258), ("access-control-allow-credentials",250), ("X-Frame-Options",246), ("access-control-allow-methods",244), ("access-control-max-age",243), ("client-token",243), ("access-control-allow-headers",243), ("App-Platform",242), ("Spotify-App-Version",242), ("Pragma",224), ("Access-Control-Allow-Origin",211), ("Content-Security-Policy",210), ("X-RestLi-Protocol-Version",209), ("X-XSS-Protection",206), ("Cookie",197), ("X-LI-UUID",158), ("X-Li-Pop",158), ("X-Li-Fabric",158), ("vary",156), ("pragma",144), ("X-LI-Track",136), ("X-LI-Lang",136), ("X-UDID",136), ("Strict-Transport-Security",135), ("expires",132), ("last-modified",129), ("p3p",129), ("x-xss-protection",126), ("etag",125), ("Timing-Allow-Origin",121), ("x-c",120), ("xserver",120), ("Accept-Charset",119), ("X-Li-Proto",117), ("X-MSEdge-Ref",117), ("X-CDN",112), ("Csrf-Token",109), ("Set-Cookie",108), ("Cross-Origin-Resource-Policy",108), ("portal",103), ("trace_id",103), ("X-Requested-With",100), ("Sec-Fetch-Mode",100), ("Sec-Fetch-Site",100), ("Sec-Fetch-Dest",100), ("Access-Control-Expose-Headers",88), ("secureCookie",85), ("Access-Control-Allow-Credentials",85), ("X-Pinterest-Device",80), ("X-Pinterest-InstallId",80), ("X-Li-Content-Length",72), ("sn",72), ("X-LI-Server-Time",72), ("X-User-Agent",72), ("Referer",71), ("AKAMAI-GRN",68), ("x-envoy-upstream-service-time",67), ("x-amz-version-id",64), ("P3P",64), ("Cross-Origin-Opener-Policy",58), ("x-amz-request-id",58), ("x-restli-symbol-table-name",53), ("Access-Control-Allow-Headers",53), ("Report-To",52), ("Origin",48), ("X-DFE-Device-Id",44), ("X-Limit-Ad-Tracking-Enabled",44), ("Permissions-Policy",44), ("X-DFE-Request-Params",44), ("X-DFE-Phenotype",44), ("X-PS-RH",44), ("X-DFE-Cookie",44), ("X-li-page-instance",44), ("nel",44), ("X-DFE-Encoded-Targets",44), ("X-Ad-Id",44), ("Accept-CH",44), ("X-DFE-Client-Id",44), ("x-dfe-content-length",44), ("X-DFE-Network-Type",44), ("X-CDN-CLIENT-IP-VERSION",42), ("x-frame-options",42), ("x-pinterest-rid",41), ("X-Pinterest-App-Type-Detailed",41), ("X-Pinterest-AppState",41), ("X-LI-Proto",41), ("X-Pinterest-Device-HardwareId",41), ("Content-Disposition",40), ("X-CDN-Proto",40), ("X-Pinterest-Unauth-ID",39), ("X-Pinterest-Auth-ID",39), ("X-Pinterest-Debug",39), ("X-Pinterest-Cache",39), ("x-trace-id",38), ("x-pinterest-direct",37), ("pinterest-version",37), ("pinterest-generated-by",35), ("Access-Control-Allow-Methods",34), ("X-DFE-Device-Checkin-Consistency-Token",29), ("x-amz-id-2",28), ("X-Cache-Remote",27), ("Charset",27), ("Proxy-Connection",26), ("x-robots-tag",25), ("Server-Timing",23), ("If-None-Match",23), ("app",23), ("X-Google-Maps-Mobile-API",23), ("Referrer-Policy",23), ("X-Visit-Id",22), ("Network-Type",22), ("X-PX-AUTHORIZATION",22), ("secureToken",22), ("CF-Ray",22), ("X-Request-ID",22), ("X-PX-ORIGINAL-TOKEN",22), ("x-origin-cache-status",21), ("x-afma-drt-v2-cookie",20), ("X-Afma-Content-Url-Opted-Out",20), ("X-Afma-Content-Vertical-Opted-Out",20), ("X-Afma-Analytics-Personalization",20), ("Client-SDK",20), ("X-Afma-Gws-Query-Id",20), ("X-Afma-Bidding-Data",20), ("Content-Encoding",20), ("X-Afma-On-Device-Brand-Safety-Opted-Out",20), ("x-afma-drt-cookie",20), ("X-Afma-Use-Https",20), ("X-Client-Id",19), ("X-Afma-Auto-Protection-Configuration",16), ("X-Afma-Safe-Browsing",16), ("X-Afma-Backend-Query-Id",16), ("X-Afma-Ad-Format",16), ("x-dfe-soft-ttl",16), ("x-dfe-hard-ttl",16), ("X-SERVER-TOKEN",16), ("X-FS-UUID",15), ("gcm_ver",15), ("X-Goog-Server-Latency",15), ("If-Modified-Since",15), ("Upgrade-Insecure-Requests",15), ("X-Google-BTD",15), ("X-Gmail-BTAI",15), ("X-Afma-Use-Displayed-Impression",14), ("X-Afma-Debug-Dialog",14), ("X-Afma-ActiveView",14), ("Trans-Type",14), ("x-payload-length",13), ("X-Content-Language",13), ("X-FLIPKART-AB-IDS",13), ("X-Cache-Hits",12), ("cross-origin-resource-policy",12), ("Remote-Cache-Status",12), ("X-Served-By",12), ("x-dfe-signature-response",11), ("X-DFE-Signature-Request",11), ("X-Afma-Omid-Settings",11), ("x-amz-server-side-encryption",11), ("load_type",11), ("X-PX-BYPASS-REASON",11), ("Keep-Alive",11), ("X-Afma-Enable-Omid",11), ("x-request-id",11), ("Access-Control-Max-Age",10), ("Sec-Fetch-User",9), ("x-pinterest-sli-endpoint-name",9), ("x-api-client-id",9), ("x-li-graphql-pegasus-client",9), ("X-B3-TraceId",9), ("X-B3-ParentSpanId",9), ("x-e-id",9), ("X-DISPATCH-TIME",9), ("x-pinterest-sli-response-type",9), ("X-B3-SpanId",9), ("timing-allow-origin",9), ("device",8), ("Vungle-Build-Rev",8), ("Vungle-Version",8), ("AppLovin-Ad-Unit-Id",8), ("x-api-key",8), ("SRegion",8), ("AppLovin-Ad-Format",8), ("Hackers",8), ("X-EdgeConnect-Cache-Status",7), ("X-Spotify-Connection-Id",7), ("X-LI-Static-Content",6), ("x-amz-meta-s3cmd-attrs",6), ("X-Whom",6), ("NEL",6), ("X-NewRelic-Timestamp",6), ("x-uber-request-uuid",6), ("X-Requested-ID",6), ("X-Afma-Error-String",6), ("X-Transfer-Encoding",6), ("X-DFE-UserLanguages",6), ("DD-EVP-ORIGIN",5), ("X-DFE-Item-Field-Mask",5), ("DD-EVP-ORIGIN-VERSION",5), ("DD-API-KEY",5), ("x-amz-replication-status",5), ("Content-type",5), ("AppLovin-Event-Type",5), ("X-AL-DataCenter",5), ("content-language",5), ("DD-REQUEST-ID",5), ("AppLovin-Third-Party-Ad-Placement-ID",5), ("X-AL-Env-Type",5), ("user-agent",5), ("Jaeger-Version",5), ("X-Timer",5), ("AppLovin-Ad-Network-Name",5), ("Location",5), ("Who",5), ("x-datadog-origin",4), ("x-datadog-sampling-priority",4), ("X-EdgeConnect-Origin-MEX-Latency",4), ("SDK-Version",4), ("Upload-Time",4), ("spotify-accept-geoblock",4), ("X-Li-Source-Fabric",4), ("x-datadog-trace-id",4), ("APIKey",4), ("X-GUploader-UploadID",4), ("X-LI-User-Agent",4), ("X-EdgeConnect-MidMile-RTT",4), ("Edge-Cache-Tag",4), ("accept-language",4), ("x-datadog-parent-id",4), ("Cache-Tag",4), ("Client-Id",4), ("X-Layout-Version",4), ("clientVersion",3), ("X-Powered-By",3), ("x-api-app-info",3), ("attempt",3), ("sender",3), ("set-cookie",3), ("X-Log-Id",3), ("X-N",3), ("clientEpoch",3), ("x-pinterest-response-type",3), ("checksum",3), ("Etag",3), ("X-Account-Ordinal",3), ("X-Branch-Request-Id",3), ("x-accept-list-items",3), ("apikey",3), ("x-mat-responder",3), ("location",3), ("instanceUUID",3), ("package",3), ("content-encoding",2), ("X-CRASHLYTICS-API-CLIENT-VERSION",2), ("x-product",2), ("Assign",2), ("Pinterest-Generated-By",2), ("x-li-recipe-map",2), ("X-Vungle-Noserv",2), ("x-product-location",2), ("X-Base-Path",2), ("Collector-Error",2), ("Us_srv_url",2), ("x-amz-storage-class",2), ("X-Request-Id",2), ("Upd_svr_url_yy",2), ("Use_foxy_server",2), ("X-LI-GraphQL-Error",2), ("Cd_recycle",2), ("Upload_rtt",2), ("App-Secret",2), ("X-CRASHLYTICS-OS-DISPLAY-VERSION",2), ("Cross-Origin-Opener-Policy-Report-Only",2), ("Upload_srv_url",2), ("X-CRASHLYTICS-DEVICE-MODEL",2), ("Upd_misc",2), ("X-Set-Cookie",2), ("status",2), ("Upd_interval",2), ("X-CRASHLYTICS-API-CLIENT-TYPE",2), ("X-Debug-Endpoint-Name",2), ("X-ACK-RESPONSE",2), ("X-CRASHLYTICS-OS-BUILD-VERSION",2), ("Scrat-Version",2), ("Upload",2), ("WWW-Authenticate",2), ("x-amz-meta-lambda",2), ("authorization",2), ("Enable_tzip",2), ("age",2), ("X-Application-Context",2), ("trace-id",2), ("Intl_sus_srv_url",2), ("X-RestLi-Id",2), ("x-li-realtime-session",2), ("X-Cache-Lookup",2), ("X-IMS-ClientId",2), ("Install-ID",2), ("x-li-recipe-accept",2), ("uc-foxyserver-ok",2), ("Enable_time_stat",2), ("time-delta-millis",2), ("X-Parent-Request-ID",2), ("Client_ip",2), ("X-CRASHLYTICS-INSTALLATION-ID",2), ("X-CRASHLYTICS-GOOGLE-APP-ID",2), ("Upd_svr_url",2), ("X-CRASHLYTICS-DEVELOPER-TOKEN",2), ("X-NWS-LOG-UUID",2), ("Us_srv_url_https",2), ("Link",1), ("Status",1), ("Range",1), ("DCS",1), ("X-TID",1), ("X-Daa-Tunnel",1), ("x-creativesdk-versions",1), ("X-APOLLO-OPERATION-ID",1), ("LinkedIn-Action",1), ("X-Amz-Version-Id",1), ("x-pinterest-graphql-operation-name",1), ("Bastion-Version",1), ("x-ans-env",1), ("X-APOLLO-OPERATION-NAME",1), ("VND.Spotify.Ads-Payload",1), ("X-UCDC-Set-WhiteList",1), ("link",1), ("access-control-request-method",1), ("x-pinterest-graphql-engine",1), ("cookie",1), ("x-client-data",1), ("X-Branch-Source",1), ("Cf-Bgj",1), ("x-server",1), ("X-Version",1), ("X-Modality",1), ("X-Service",1), ("X-Debug-2-Facade-Request-To-Service",1), ("X-Spotify-Quicksilver-Uri",1), ("X-Debug-Upstream-Addr",1), ("X-Debug-1-Incoming-Request-To-Facade",1), ("X-UCDC-VERSION",1), ("x-unique-id",1), ("NAPI-ETAG",1), ("x-gw-aws-region",1), ("content-disposition",1), ("x-adobe-app-id",1), ("Akamai-Mon-Iucid-Del",1), ("X-UCDC-WhiteList",1), ("Retry-After",1), ("X-Origlength",1), ("x-user-token",1), ("uniqueid",1), ("x-spotify-subscription-expires-at",1), ("AppLovin-Event",1), ("x-timestamp",1), ("Content-Security-Policy-Report-Only",1), ("Content-Range",1), ("UniqueId",1), ("X-Debug-Service-Request-To-Backend",1), ("x-ans-version",1), ("x-requested-with",1), ("accept",1), ("X-UCDC-APPID",1), ("x-restli-method",1), ("X-HTTP-Method-Override",1), ("st-token",1), ("x-pinterest-graphql-equivalent-api-method",1))
	private val expectedReqsWithoutRes = List(105, 167, 353, 385, 390, 393, 399, 545, 734, 783, 927, 933, 968, 979, 999, 1022, 1024, 1026, 1030, 1168, 1528, 1643, 1687, 1709, 1718, 1720, 1737, 1783, 1831, 1990, 2073, 2175, 2327, 2397, 3519, 4391, 5431, 5534, 6715, 7819, 8976, 9583, 9589, 9601, 9606, 9607, 9608, 9613, 9625, 9658, 9664, 9688, 9766, 10262, 10833, 10875)

	/////////////////
	// Aufgabe 5.1 //
	/////////////////

	"parseHttpMessages()" should "produce a list of HttpMessages (1P)" in {
		assert(messages.isDefined)
		for (message <- messages.orNull) {
			assert(message.isInstanceOf[HttpMessage])
		}
	}

	it should "assign the correct subtype to each message (1P)" in {
		var anyHits = false
		assert(messages.isDefined)
		for (raw <- data; message <- messages.orNull if raw.head.toInt == message.id) {
			anyHits = true
			raw(5) match {
				case "REQUEST" => assert(message.isInstanceOf[HttpRequest])
				case "RESPONSE" => assert(message.isInstanceOf[HttpResponse])
				case _ => assertNull(message)
			}
		}
		assertTrue(anyHits)
	}

	it should "mix in the correct traits for each message (1P)" in {
		var anyHits = false
		assert(messages.isDefined)
		for (raw <- data; message <- messages.orNull if raw.head.toInt == message.id) {
			anyHits = true
			raw(5) match {
				case "REQUEST" =>
					assert(message.isInstanceOf[HeaderHolder])
					assert(message.isInstanceOf[ContentHolder])
					assert(message.isInstanceOf[MethodHolder])
					assert(message.isInstanceOf[PathHolder])
					assertFalse(message.isInstanceOf[StatusHolder])
				case "RESPONSE" =>
					assert(message.isInstanceOf[HeaderHolder])
					assert(message.isInstanceOf[ContentHolder])
					assert(message.isInstanceOf[StatusHolder])
					assertFalse(message.isInstanceOf[MethodHolder])
					assertFalse(message.isInstanceOf[PathHolder])
				case _ => assertNull(message)
			}
		}
		assertTrue(anyHits)
	}

	it should "correctly parse the headers (1P)" in {
		var anyHits = false
		assert(messages.isDefined)
		for (raw <- data; message <- messages.orNull if raw.head.toInt == message.id) {
			anyHits = true
			assert(message.isInstanceOf[HeaderHolder])
			assertEquals(HeaderHolder.parseHeaders(raw(2)), message.asInstanceOf[HeaderHolder].headers)
		}
		assertTrue(anyHits)
	}

	it should "correctly parse the method (1P)" in {
		var anyHits = false
		assert(messages.isDefined)
		for (raw <- data; message <- messages.orNull if raw.head.toInt == message.id && message.isInstanceOf[HttpRequest]) {
			anyHits = true
			assert(message.isInstanceOf[MethodHolder])
			assertEquals(Method.fromString(raw(4)), message.asInstanceOf[MethodHolder].method)
		}
		assertTrue(anyHits)
	}

	it should "correctly parse the status (1P)" in {
		var anyHits = false
		assert(messages.isDefined)
		for (raw <- data; message <- messages.orNull if raw.head.toInt == message.id && message.isInstanceOf[HttpResponse]) {
			anyHits = true
			assert(message.isInstanceOf[StatusHolder])
			assertEquals(raw(6).substring(0, 3).toInt, message.asInstanceOf[StatusHolder].status)
		}
		assertTrue(anyHits)
	}

	/////////////////
	// Aufgabe 5.2 //
	/////////////////

	"countGetRequests()" should "be 3885 (2P)" in {
		Main.countGetRequests(messages.getOrElse(List())).getOrElse(1) shouldBe 3885
	}

	"findMostCommonHeader()" should "be Connection (2P)" in {
		Main.findMostCommonHeader(messages.getOrElse(List())).getOrElse("") shouldBe "Connection"
	}

	"countEachHeader()" should "match expectedCountPerHeader (2P)" in {
		Main.countEachHeader(messages.getOrElse(List())).getOrElse(List()).sorted shouldBe expectedCountPerHeader.sorted
	}

	"findRequestsWithoutResponse()" should "match expectedReqsWithoutRes (2P)" in {
		Main.findRequestsWithoutResponse(messages.getOrElse(List())).getOrElse(List()).sorted shouldBe expectedReqsWithoutRes.sorted
	}

	"countEachIdempotentRequestType()" should "be HashMap(GET -> 3885, HEAD -> 6, PUT -> 11) (2P)" in {
		Main.countEachIdempotentRequestType(messages.getOrElse(List())).orNull shouldBe HashMap(GET -> 3885, HEAD -> 6, PUT -> 11)
	}

	"calculateAverageResponseContentLength()" should "be 38351.905 +- 0.01 (2P)" in {
		Main.calculateAverageResponseContentLength(messages.getOrElse(List())).getOrElse(1.0) shouldBe (38351.905 +- 0.01)
	}

	"calculateTotalResponseContentLength()" should "be 183705625 (2P)" in {
		Main.calculateTotalResponseContentLength(messages.getOrElse(List())).getOrElse(1) shouldBe 183705625
	}
}