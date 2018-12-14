package top.zloop.gradle.plugins;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import top.zloop.gradle.tasks.BuildTimeJavaTask;

public class BuildTimeJavaPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.getTasks().create("currentTimeTask", BuildTimeJavaTask.class);
    }
}