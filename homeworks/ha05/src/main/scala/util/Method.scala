package util

/**
 * Enum of valid HTTP-Methods.
 *
 * https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods
 */
object Method extends Enumeration {

	type Method = Value
	val GET, HEAD, POST, PUT, DELETE, CONNECT, OPTIONS, TRACE, PATCH, UNKNOWN = Value

	/**
	 * Use this to parse a string to the corresponding enum value.
	 *
	 * @param method String matching a value of this enum.
	 * @return corresponding enum value, UNKNOWN if the input does not match.
	 */
	def fromString(method: String) = values.find(_.toString.toLowerCase() == method.toLowerCase()).getOrElse(UNKNOWN)
}
