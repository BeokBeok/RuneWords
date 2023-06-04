import org.gradle.api.artifacts.dsl.DependencyHandler

internal fun DependencyHandler.implementation(dependencyNotation: Any) {
    add("implementation", dependencyNotation)
}

internal fun DependencyHandler.kapt(dependencyNotation: Any) {
    add("kapt", dependencyNotation)
}
