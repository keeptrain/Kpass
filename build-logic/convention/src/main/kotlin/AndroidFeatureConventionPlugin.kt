import com.android.build.gradle.LibraryExtension
import com.keep.password.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            apply(plugin = "kpass.android.library")
            apply(plugin = "kpass.hilt")
            apply(plugin = "kpass.android.navigation")

            extensions.configure<LibraryExtension> {
                testOptions.animationsDisabled = true
            }

            dependencies {
                add("implementation", project(":core:common"))
                add("implementation", project(":core:designsystem"))
                add("implementation", project(":core:domain"))
                add("implementation", project(":core:model"))

                add("implementation", libs.findLibrary("androidx.core.ktx").get())
                add("implementation", libs.findLibrary("androidx.appcompat").get())
                add("implementation", libs.findLibrary("material").get())
                add("implementation", libs.findLibrary("androidx.activity").get())
                add("implementation", libs.findLibrary("androidx.constraintlayout").get())

                add("implementation", libs.findLibrary("androidx.lifecycle.viewmodel.ktx").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.livedata.ktx").get())
                add("implementation", libs.findLibrary("androidx.lifecycle.runtime.ktx").get())
                add("ksp", libs.findLibrary("androidx.lifecycle.compiler").get())

                add("testImplementation", libs.findLibrary("junit").get())
                add("androidTestImplementation", libs.findLibrary("androidx.junit").get())
                add("androidTestImplementation", libs.findLibrary("androidx.espresso.core").get())

//                add("testImplementation", kotlin("test"))
//                add("androidTestImplementation", kotlin("test"))
//
//                add("implementation", libs.findLibrary("kotlinx.coroutines.android").get())
            }
        }
    }
}