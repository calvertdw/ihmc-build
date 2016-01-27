package us.ihmc.gradle

import org.gradle.api.Project
import org.gradle.api.artifacts.repositories.ArtifactRepository

/**
 * Created by dstephen on 1/26/16.
 */
class IHMCBuildExtension {
    private final Project containingProject;

    def IHMCBuildExtension(Project project) {
        this.containingProject = project
    }

    def void removeRepository(ArtifactRepository repository) {
        if(containingProject.repositories.contains(repository))
        {
            containingProject.logger.lifecycle("[IHMC Build] Removing artifact repository ${repository.name}")
            containingProject.repositories.remove(repository)
        }
    }

    def void useDefaultRepositories() {
        containingProject.repositories {
            mavenLocal()

            maven {
                url "https://bengal.ihmc.us/nexus/content/repositories/releases/"
            }

            maven {
                url "https://bengal.ihmc.us/nexus/content/repositories/thirdparty/"
            }

            maven {
                url "https://bengal.ihmc.us/nexus/content/repositories/central/"
            }

            maven {
                url "https://bengal.ihmc.us/nexus/content/repositories/swt-repo/"
            }

            jcenter()

            mavenCentral()
        }
    }

    def Project getProjectDependency(String projectName) {
        def projects = getAllProjects(containingProject.getRootProject())

        for(Project project : projects)
        {
            if(project.path.endsWith(projectName))
            {
                containingProject.logger.debug("IHMC Build Extension Found ${project.path} for getProjectDependency(${projectName})")
                return project
            }
        }

        return null
    }

    private List<Project> getAllProjects(Project rootProject)
    {
        def ret = new ArrayList<Project>()

        if(!rootProject.childProjects.isEmpty())
        {
            getAllProjectsFlattened(rootProject.childProjects.values(), ret)
        }

        return ret
    }

    private void getAllProjectsFlattened(Collection<Project> projects, List<Project> flatProjects)
    {
        for(Project project : projects)
        {
            if(!project.childProjects.isEmpty())
            {
                getAllProjectsFlattened(project.childProjects.values(), flatProjects)
            }
            else
            {
                flatProjects.add(project)
            }
        }
    }
}
