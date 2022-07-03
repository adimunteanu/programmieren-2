package components.traits

/**
 * To be mixed into subclasses of HttpMessage that have a body (content).
 */
trait ContentHolder {
	val contentLength: Int
}
