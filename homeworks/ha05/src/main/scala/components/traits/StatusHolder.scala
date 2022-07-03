package components.traits

/**
 * To be mixed into subclasses of HttpMessage that have an HTTP status.
 *
 * https://developer.mozilla.org/en-US/docs/Web/HTTP/Status
 */
trait StatusHolder {
	val status: Int
}
