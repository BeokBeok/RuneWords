#!/usr/bin/env kotlin

@file:DependsOn("it.krzeminski:github-actions-kotlin-dsl:0.22.0")

import it.krzeminski.githubactions.actions.actions.CheckoutV3
import it.krzeminski.githubactions.actions.actions.SetupJavaV3
import it.krzeminski.githubactions.actions.actions.SetupJavaV3.Distribution.Adopt
import it.krzeminski.githubactions.actions.ruby.SetupRubyV1
import it.krzeminski.githubactions.domain.RunnerType
import it.krzeminski.githubactions.domain.Workflow
import it.krzeminski.githubactions.domain.triggers.PullRequest
import it.krzeminski.githubactions.dsl.workflow
import it.krzeminski.githubactions.yaml.writeToFile
import java.nio.file.Paths

val workflowFirebaseAppDistributor: Workflow = workflow(
      name = "Firebase App Distributor",
      on = listOf(
        PullRequest(
          branches = listOf("develop"),
        ),
        ),
      sourceFile = Paths.get(".github/workflows/firebase_app_distributor.main.kts"),
    ) {
      job(
        id = "build",
        runsOn = RunnerType.UbuntuLatest,
      ) {
        uses(
          name = "Checkout",
          action = CheckoutV3(),
        )
        uses(
          name = "Setup ruby",
          action = SetupRubyV1(
            rubyVersion = "2.7",
          ),
        )
        uses(
          name = "Setup JDK 11",
          action = SetupJavaV3(
            javaVersion = "11",
            _customVersion = "v1",
              distribution = Adopt
          ),
        )
        run(
          name = "Create google-services.json",
          command = "echo '${'$'}{{ secrets.GOOGLE_SERVICES_JSON }}' > ./app/google-services.json",
        )
        run(
          name = "Create firebase-app-distribution-services.json",
          command =
              "echo '${'$'}{{ secrets.FIREBASE_APP_DISTRIBUTOR_SERVICES_JSON }}' > ./fastlane/firebase-app-distributor-services.json",
        )
        run(
          name = "Setup fastlane",
          command =
              "bundle install && bundle update fastlane && gem install bundler && bundle exec fastlane update_plugins",
        )
        run(
          name = "Deploy to Firebase App Distributor",
          command = "fastlane uploadDebug",
        )
      }

    }

workflowFirebaseAppDistributor.writeToFile()
