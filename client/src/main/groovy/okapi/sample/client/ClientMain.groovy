package okapi.sample.client

import groovyx.net.http.*

public class ClientMain {

    public static void main(String[] args) {

        println "Client Starting"
        println "Client Started"

        println "Response from First Module"
        println get('http://localhost:9201')

        println "Response from Second Module"
        println get('http://localhost:9202')
    }

    static get(url) {
        def http = new HTTPBuilder(url)

        try {
            http.request(Method.GET) { req ->
                response.success = { resp, body ->
                    body
                }
            }
        }
        catch (ConnectException ex) {
            println "Failed to access ${url} error: ${ex}"
        }
        catch (ResponseParseException ex) {
            println "Failed to access ${url} error: ${ex}"
        }
        catch (HttpResponseException ex) {
            println "Failed to access ${url} error: ${ex}"
        }
    }
}