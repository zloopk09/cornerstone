package top.zloop.gradle.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class BuildTimeKotlinTask : DefaultTask() {

    /**
     * The name of the developer. This will be used for greeting.
     */
    var yourName = "developer"

    @TaskAction
    fun greet() {
        logger.lifecycle("Hello, $yourName! I warmly greet you.")
    }
}