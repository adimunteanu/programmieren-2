package impl

import components.traits.{ContentHolder, HeaderHolder}
import util.Method
import util.Method.Method

/**
 * Abstract superclass of HttpRequest and HttpResponse. To be extend with a factory method which constructs an object of
 * type HttpRequest or HttpResponse depending on the type specified in the dataset.
 *
 * DO NOT MODIFY THE CONSTRUCTOR SIGNATURE!
 *
 * https://developer.mozilla.org/en-US/docs/Web/HTTP/Messages
 *
 * @param id         Unique identifier of the HttpMessage.
 * @param reqResId   Unique identifier of a request-response pair. Responses are always mapped to a request via this identifier.
 *                   Requests, however, do not always have a corresponding request, since a server may not have responded.
 * @param rawHeaders Raw JSON string representing the headers of the HttpMessage.
 * @param host       Hostname of the server this HttpMessage was exchanged with.
 */
abstract class HttpMessage(val id: Int, val reqResId: Int, val rawHeaders: String, val host: String) extends ContentHolder with HeaderHolder {
  override val headers: Map[String, String] = HeaderHolder.parseHeaders(rawHeaders)
}

object HttpMessage {
  def apply(line: List[String]): HttpMessage = {
    val id: Int = line(0).toInt
    val reqResId: Int = line(1).toInt
    val rawHeaders: String = line(2)
    val contentLength: Int = line(3).toInt
    val httpMessageType: String = line(5)
    val host: String = line(7)

    httpMessageType match {
      case "REQUEST" =>
        val method: Method = Method.fromString(line(4))
        val path: String = line(8)
        new HttpRequest(id, reqResId, rawHeaders, host, method, path, contentLength)
      case "RESPONSE" =>
        val status = line(6).split(" ")(0).toInt
        new HttpResponse(id, reqResId, rawHeaders, host, status, contentLength)
    }
  }
}

