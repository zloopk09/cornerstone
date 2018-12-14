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

/**
 * Access the `android` extension of this project. If the project is not an
 * Android app or library module, this method will throw.
 */
val Project.android: BaseExtension
    get() = extensions.findByName("android") as? BaseExtension
        ?: error("Project '$name' is not an Android module. Can't " +
                "access 'android' extension.")

/**
 * Accesses the app module-specific extensions of an Android module.
 */
val BaseExtension.app: AppExtension
    get() = this as? AppExtension
        ?: error("Android module is not an app module. Can't " +
                "access 'android' app extension.")