import com.keep.password.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin


class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply("kpass.android.library")
                apply("kpass.android.hilt")
            }

            dependencies {
                add("implementation", project(":core:data"))
                add("implementation", project(":core:database"))
                add("implementation", project(":core:designsystem"))

                add("testImplementation", kotlin("test"))
                add("androidTestImplementation", kotlin("test"))

                //add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
            }

        }

    }

}