#!/usr/bin/env kotlin

@file:DependsOn("it.krzeminski:github-actions-kotlin-dsl:0.22.0")

import it.krzeminski.githubactions.actions.CustomAction
import it.krzeminski.githubactions.actions.actions.CheckoutV2
import it.krzeminski.githubactions.actions.actions.SetupJavaV3
import it.krzeminski.githubactions.actions.actions.UploadArtifactV3
import it.krzeminski.githubactions.domain.RunnerType
import it.krzeminski.githubactions.domain.Workflow
import it.krzeminski.githubactions.domain.triggers.Push
import it.krzeminski.githubactions.dsl.expressions.expr
import it.krzeminski.githubactions.dsl.workflow
import it.krzeminski.githubactions.yaml.toYaml
import it.krzeminski.githubactions.yaml.writeToFile
import java.nio.`file`.Paths
import kotlin.collections.mapOf

public val workflowCi: Workflow = workflow(
      name = "CI",
      on = listOf(
        Push(
          branches = listOf("main"),
        ),
        ),
      sourceFile = Paths.get(".github/workflows/ci.main.kts"),
    ) {
      job(
        id = "build",
        runsOn = RunnerType.MacOSLatest,
      ) {
        uses(
          name = "Checkout",
          action = CheckoutV2(),
        )
        uses(
          name = "Set up JDK 11",
          action = SetupJavaV3(
            javaVersion = "11",
            _customVersion = "v1",
          ),
        )
        run(
          name = "Create google-services.json",
          command = "echo '${'$'}{{ secrets.GOOGLE_SERVICES_JSON }}' > ./app/google-services.json",
        )
        run(
          name = "Update Application file",
          command = """
mv app/src/release/java/com/beok/runewords/RuneWordsApplication.kt app/src/main/java/com/beok/runewords/RuneWordsApplication.kt
rm -f app/src/debug/java/com/beok/runewords/RuneWordsApplication.kt
""".trimMargin(),
        )
        run(
          name = "Clean Managed Devices",
          command = "./gradlew cleanManagedDevices --unused-only",
        )
        run(
          name = "Generate Baseline Profile",
          command = """
./gradlew :benchmark:pixel2Api31BenchmarkAndroidTest -Pandroid.testoptions.manageddevices.emulator.gpu="swiftshader_indirect" -Pandroid.testInstrumentationRunnerArguments.androidx.benchmark.enabledRules=BaselineProfile -Dorg.gradle.workers.max=4
""".trimMargin(),
        )
        run(
          name = "Move & Rename Baseline Profiles",
          command = """
mv -f benchmark/build/outputs/managed_device_android_test_additional_output/pixel2Api31/ExampleStartupBenchmark_startup-baseline-prof.txt app/src/main/baseline-prof.txt
""".trimMargin(),
        )
        run(
          name = "Build Release AAB",
          command = "./gradlew bundleRelease",
        )
        uses(
          name = "Sign AAB",
          action = CustomAction(
            actionOwner = "r0adkll",
            actionName = "sign-android-release",
            actionVersion = "v1",
            inputs = mapOf(
              "releaseDirectory" to "app/build/outputs/bundle/release",
              "signingKeyBase64" to "${'$'}{{ secrets.SIGNING_KEY }}",
              "alias" to "${'$'}{{ secrets.ALIAS }}",
              "keyStorePassword" to "${'$'}{{ secrets.KEY_STORE_PASSWORD }}",
              "keyPassword" to "${'$'}{{ secrets.KEY_PASSWORD }}",
            )
          ),
        )
        uses(
          name = "Upload AAB",
          action = UploadArtifactV3(
            name = "app",
            path = listOf(
              "app/build/outputs/bundle/release/app-release.aab",
            )
            ,
            _customVersion = "v1",
          ),
        )
        run(
          name = "Create service_account.json",
          command = "echo '${'$'}{{ secrets.SERVICE_ACCOUNT_JSON }}' > service_account.json",
        )
        uses(
          name = "Deploy to Play Store (ALPHA)",
          action = CustomAction(
            actionOwner = "r0adkll",
            actionName = "upload-google-play",
            actionVersion = "v1.0.15",
            inputs = mapOf(
              "serviceAccountJson" to "service_account.json",
              "packageName" to "com.beok.runewords",
              "releaseFiles" to "app/build/outputs/bundle/release/app-release.aab",
              "track" to "alpha",
              "whatsNewDirectory" to "whatsnew/",
            )
          ),
        )
      }

    }

workflowCi.writeToFile()