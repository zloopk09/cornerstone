package top.zloop.gradle.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project
import top.zloop.gradle.tasks.BuildTimeKotlinTask

class BuildTimeKotlinPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.logger.lifecycle("MyPlugin was successfully applied " +
                "to your project named '${target.name}'!")

        // This is an example of how you can iterate all app variants
        // your Android project defines.
        project.android.app
            .applicationVariants
            .matching { it.buildType.name == "debug" }
            .all {
                // Make great use of all debug variants.
            }
    }
}