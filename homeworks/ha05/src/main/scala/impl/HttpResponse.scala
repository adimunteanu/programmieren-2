package impl

import components.traits.{MethodHolder, PathHolder, StatusHolder}
import util.Method.Method

/**
 * Subclass of HttpMessage representing an HTTP response.
 *
 * https://developer.mozilla.org/en-US/docs/Web/HTTP/Messages#http_responses
 *
 * A response has the following components:
 * - headers
 * - content
 * - status
 * - host
 *
 * ...as well as a unique ID and a reqResId mapping it to a request.
 */
class HttpResponse(
                    override val id: Int,
                    override val reqResId: Int,
                    override val rawHeaders: String,
                    override val host: String,
                    override val status: Int,
                    override val contentLength: Int
                  ) extends HttpMessage(id, reqResId, rawHeaders, host) with StatusHolder {}
