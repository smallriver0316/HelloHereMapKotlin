# Hello HERE Map (Jetpack Compose)

This is a simple sample project built with Kotlin and Jetpack Compose that displays a map using the HERE SDK for Android (Premium Edition).

## Overview

This project demonstrates how to embed a traditional Android View, the HERE `MapView`, within the modern Jetpack Compose UI framework. It achieves Compose-View interoperability using the `AndroidView` composable.

## Features

-   **Jetpack Compose:** The UI is built entirely with Jetpack Compose.
-   **HERE SDK Integration:** Displays a HERE Map within the Compose UI using `AndroidView`.
-   **Secure Key Management:** Safely manages API keys by separating them from the source code using `local.properties` and `buildConfigField`.

## 🛠️ Tech Stack & Libraries

-   [Kotlin](https://kotlinlang.org/)
-   [Jetpack Compose](https://developer.android.com/jetpack/compose) - Android's modern UI toolkit
-   [HERE SDK for Android](https://www.here.com/docs/) - Provides map functionalities
-   [Gradle (Kotlin DSL)](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
-   [Version Catalogs](https://docs.gradle.org/current/userguide/platforms.html) - For dependency version management

## 🚀 Setup Instructions

To build and run this project, you need credentials from the HERE SDK.

1.  Register for an account on the [HERE Developer Portal](https://platform.here.com/), create a new app, and get your **Access Key ID** and **Access Key Secret**.

2.  Create a file named `local.properties` in the root directory of the project (if it doesn't already exist).

3.  Add your credentials to the `local.properties` file in the following format:
