package plugin

import extension.libs
import extension.implementation
import java.io.File
import extension.ksp
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.PathSensitive
import org.gradle.api.tasks.PathSensitivity
import org.gradle.kotlin.dsl.dependencies
import org.gradle.process.CommandLineArgumentProvider

internal class AndroidLibraryRoomConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("com.google.devtools.ksp")

            val ksp = extensions.getByName("ksp")
            val argProviderMethod = ksp.javaClass.getMethod("arg", CommandLineArgumentProvider::class.java)
            argProviderMethod.invoke(ksp, RoomSchemaArgProvider(File(projectDir, "schemas")))
            val argStringMethod = ksp.javaClass.getMethod("arg", String::class.java, String::class.java)
            argStringMethod.invoke(ksp, "room.generateKotlin", "true")

            dependencies {
                implementation(libs.findLibrary("room.ktx").get())
                ksp(libs.findLibrary("room.compiler").get())
            }
        }
    }

    class RoomSchemaArgProvider(
        @get:InputDirectory
        @get:PathSensitive(PathSensitivity.RELATIVE)
        val schemaDir: File,
    ) : CommandLineArgumentProvider {
        override fun asArguments() = listOf("room.schemaLocation=${schemaDir.path}")
    }
}
