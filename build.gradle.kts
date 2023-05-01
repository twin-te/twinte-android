import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.DetektCreateBaselineTask
import io.gitlab.arturbosch.detekt.report.ReportMergeTask

// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlinVersion by extra("1.6.21")
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:8.0.0")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath ("com.google.android.gms:oss-licenses-plugin:0.10.4")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}
plugins {
    id("io.gitlab.arturbosch.detekt").version("1.22.0")
}
// see https://detekt.dev/docs/introduction/reporting/#merging-reports
val reportMerge by tasks.registering(ReportMergeTask::class) {
    output.set(rootProject.layout.buildDirectory.file("reports/detekt/merge.xml")) // or "reports/detekt/merge.sarif"
}
subprojects {
    apply(plugin = "io.gitlab.arturbosch.detekt")

    dependencies {
        detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.22.0")
    }

    detekt {
        // デフォルトの設定を引き継ぐ
        buildUponDefaultConfig = true
        // 並列してファイルを見る
        parallel = true
        // Detekt 設定ファイルの指定
        config = files("${project.rootDir}/config/detekt/detekt.yml")
        // Detekt ベースラインファイルの指定
        baseline = file("${project.rootDir}/config/detekt/baseline.yml")
        // ルールに沿わないソースがあったら detekt タスクを失敗させる
        ignoreFailures = false
        // ルールに沿わない記述箇所には自動修正をかける
        autoCorrect = true
    }

    plugins.withType<DetektPlugin> {
        tasks.withType<Detekt> detekt@{
            finalizedBy(reportMerge)
            reports.xml.required.set(true)

            reportMerge.configure {
                input.from(this@detekt.xmlReportFile)
            }
        }
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}

tasks.withType<Detekt>().configureEach {
    jvmTarget = "17"
}
tasks.withType<DetektCreateBaselineTask>().configureEach {
    jvmTarget = "17"
}
val detektProjectBaseline by tasks.registering(DetektCreateBaselineTask::class) {
    description = "Overrides current baseline."
    ignoreFailures.set(true)
    parallel.set(true)
    buildUponDefaultConfig.set(true)
    setSource(files(rootDir))
    config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
    baseline.set(file("$rootDir/config/detekt/baseline.xml"))
    include("**/*.kt")
    include("**/*.kts")
    exclude("**/resources/**")
    exclude("**/build/**")
}
