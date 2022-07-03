package components.traits

import util.Method._

/**
 * To be mixed into subclasses of HttpMessage that have an HTTP method.
 *
 * https://developer.mozilla.org/en-US/docs/Web/HTTP/Methods
 */
trait MethodHolder {
	val method: Method

	// https://developer.mozilla.org/en-US/docs/Glossary/Safe/HTTP
	def isSafe: Boolean = method == GET || method == HEAD || method == OPTIONS

	// https://developer.mozilla.org/en-US/docs/Glossary/Idempotent
	def isIdempotent: Boolean = isSafe || method == PUT
}
