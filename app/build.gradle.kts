plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.opentriviaquiz"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.opentriviaquiz"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.8.4")
    // Retrofit jadro – HTTP klient pre REST volania
    implementation("com.squareup.retrofit2:retrofit:2.11.0") // ver. 2.11.0 = stabilná verzia Retrofit
    // Moshi konvertor – prevod JSON <-> Kotlin dátové triedy
    implementation("com.squareup.retrofit2:converter-moshi:2.11.0") // konvertor pre Retrofit
    // OkHttp logging – jednoduché logovanie request/response v Logcate (len pre debug účely)
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.14") // interceptor pre HTTP logy
    // Moshi Kotlin – reflexia/anotácie pre pohodlný parsing JSONu
    implementation("com.squareup.moshi:moshi-kotlin:1.15.1") // Moshi s podporou Kotlinu
    // Coroutines pre Android – asynchrónne operácie (sieť/IO) v ViewModeloch
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0") // coroutines runtime na Androide
    // (ak používaš v HomeScreen ikony ExpandMore/ExpandLess)
    implementation("androidx.compose.material:material-icons-extended") // voliteľné, pre Material ikony
    // Splash screen API (potrebné pre Theme.SplashScreen)
    implementation("androidx.core:core-splashscreen:1.0.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.core:core-splashscreen:1.0.1") // už máš, pre istotu nech tu je
    implementation("androidx.compose.material3:material3:1.3.1") // Compose M3 (ak ti chýba)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}