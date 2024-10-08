import com.keep.password.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidNavigationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            dependencies {
                add("implementation",libs.findLibrary("androidx-navigation-ui-ktx").get())
                add("implementation",libs.findLibrary("androidx-navigation-fragment-ktx").get())
            }
        }
    }
}