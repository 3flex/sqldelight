package com.squareup.sqldelight

import com.google.common.truth.Truth.assertThat
import java.io.File
import org.gradle.testkit.runner.GradleRunner
import org.gradle.testkit.runner.TaskOutcome
import org.junit.Test

class VerifyMigrationTaskTest {
  @Test fun `verifyMigration task is up to date when inputs are unchanged`() {
    val fixtureRoot = File("src/test/schema-file")

    val initialRunner = GradleRunner.create()
        .withProjectDir(fixtureRoot)
        .withArguments("verifyMainDatabaseMigration")
        .build()

    // verify
    assertThat(initialRunner.task(":verifyMainDatabaseMigration")!!.outcome)
        .isEqualTo(TaskOutcome.SUCCESS)

    val cachedRunner = GradleRunner.create()
        .withProjectDir(fixtureRoot)
        .withArguments("verifyMainDatabaseMigration")
        .build()

    // verify
    assertThat(cachedRunner.task(":verifyMainDatabaseMigration")!!.outcome)
        .isEqualTo(TaskOutcome.UP_TO_DATE)
  }
}
