package components.traits

import org.json.JSONObject

import scala.jdk.CollectionConverters.MapHasAsScala

/**
 * To be mixed into subclasses of HttpMessage that have HTTP headers.
 *
 * https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers
 */
trait HeaderHolder {
	val headers: Map[String, String]
}

/**
 * Use this to parse a raw header string to a map of keys and values.
 *
 * @param jsonHeaders Raw JSON string representation of HTTP headers.
 * @return map representation of the given headers.
 */
object HeaderHolder {
	def parseHeaders(jsonHeaders: String): Map[String, String] = {
		new JSONObject(jsonHeaders).toMap.asScala.toMap.map(e => (e._1, e._2.asInstanceOf[String]))
	}
}
