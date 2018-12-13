import org.gradle.api.*
import org.gradle.api.tasks.*

class HelloWorldTask extends DefaultTask {
    @Optional
    String message = 'I am a programmer'

    @TaskAction
    def getDateOfBuild(){
        return new Date().format("yyyy-MM-dd'T'HH:mm'Z'").toString().trim()
    }
}