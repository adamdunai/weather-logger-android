# Weather Logger

[![Build Status](https://app.bitrise.io/app/1eaf9cb6c4a86285/status.svg?token=aFS8TI29EVFujJchD67R2A&branch=development)](https://app.bitrise.io/app/1eaf9cb6c4a86285)

Weather Logger is an Android project using [OpenweatherMap API](https://openweathermap.org/api) based on [MVVM architecture](https://developer.android.com/jetpack/guide).
When Save button is pressed a fresh weather data about the current location is fetched from the API.
You can also click on a list item to view some detailed information.

![Showcase](/docs/showcase.png)

## Features
- 100% Kotlin
- MVVM architecture
- Reactive pattern
- Android Jetpack
- Kotlin Coroutines + Flow
- Single activity app with fragments
- Dependency injection
- CI/CD support

## Tech stack
- [Android Jetpack](https://developer.android.com/jetpack) - A collections of libraries to help developers follow best practices, reduce boilerplate code, and write code that works consistently across Android versions and devices.
    - [Hilt](https://developer.android.com/jetpack/androidx/releases/hilt) - Extend the functionality of Dagger Hilt to enable dependency injection of certain classes from the androidx libraries.
    - [Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle) - Lifecycle-aware components that can adjust behavior based on the current lifecycle state of an activity or fragment.
    - [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation) - Build and structure your in-app UI, handle deep links, and navigate between screens.
    - [Room](https://developer.android.com/jetpack/androidx/releases/room) - Create, store, and manage persistent data backed by a SQLite database.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java
- [Moshi](https://github.com/square/moshi) - A modern JSON library for Kotlin and Java.
- [PermissionDispatcher](https://github.com/permissions-dispatcher/PermissionsDispatcher) - A declarative API to handle Android runtime permissions.
- [Fused Location Provider](https://developers.google.com/location-context/fused-location-provider) - Get location data for your app based on combined signals from the device sensors using a battery-efficient API.
- [Coil](https://coil-kt.github.io/coil/) - Image loading for Android backed by Kotlin Coroutines.
- [Logcat](https://github.com/square/logcat) - Tiny Kotlin API for cheap logging on top of Android's normal Log class.
- [Bitrise](https://www.bitrise.io) - Mobile-first CI/CD in the cloud, for any platform.

## Development setup
You need [Android Studio Arctic Fox](https://developer.android.com/studio) (or newer) to be able to build the app.

### Code style
This project uses [ktlint](https://github.com/pinterest/ktlint). Use the `ktlint` and `ktlint-format` Gradle tasks
to check and format according to proper Kotlin lint rules.

### API key :key:
You will need to provide an API key to use the app.
You can find information about how to gain access in the following link.
- [OpenweatherMap API](https://openweathermap.org/api)

Add the key to the `gradle.properties` file:

```
# OpenWeather API key
OPENWEATHER_API_KEY=key
```
