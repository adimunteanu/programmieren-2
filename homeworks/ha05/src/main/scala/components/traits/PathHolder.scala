package components.traits

/**
 * To be mixed into subclasses of HttpMessage that have a path.
 *
 * https://developer.mozilla.org/en-US/docs/Web/HTTP/Basics_of_HTTP/Identifying_resources_on_the_Web#path
 */
trait PathHolder {
    val path: String
}
