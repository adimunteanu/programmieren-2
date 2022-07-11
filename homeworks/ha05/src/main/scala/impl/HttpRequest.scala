package impl

import components.traits.{ContentHolder, HeaderHolder, MethodHolder, PathHolder}
import util.Method
import util.Method.Method

/**
 * Subclass of HttpMessage representing an HTTP request.
 *
 * https://developer.mozilla.org/en-US/docs/Web/HTTP/Messages#http_requests
 *
 * A request has the following components:
 * - headers
 * - content
 * - method
 * - host
 * - path
 *
 * ...as well as a unique ID and a reqResId mapping it to a response.
 */
class HttpRequest(
                   override val id: Int,
                   override val reqResId: Int,
                   override val rawHeaders: String,
                   override val host: String,
                   override val method: Method,
                   override val path: String,
                   override val contentLength: Int
                 ) extends HttpMessage(id, reqResId, rawHeaders, host)
  with MethodHolder
  with PathHolder
  with ContentHolder
  with HeaderHolder {
  override val headers: Map[String, String] = HeaderHolder.parseHeaders(rawHeaders)
}
