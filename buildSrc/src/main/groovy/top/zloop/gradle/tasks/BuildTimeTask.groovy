package top.zloop.gradle.tasks

import org.gradle.api.*
import org.gradle.api.tasks.*

class BuildTimeTask extends DefaultTask {
    @Optional
    def user = 'username'

    @TaskAction
    def getDateOfBuild(){
        return new Date().format("yyyy-MM-dd'T'HH:mm'Z'").toString().trim()
    }
}