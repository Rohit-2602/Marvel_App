## Marvel App
MarvelApp is an application based on Modern Android Application tech-stacks and MVVM Architecture.
Fetches data from Marvel Api.

| All Characters | Character Search | Favourite Characters | Character Comics |
| -------------- | ---------------- | -------------------- | ---------------- |
| <img src="https://user-images.githubusercontent.com/65807152/136015476-f9c70464-c525-474c-8c99-5683ad8d2ef6.jpg" width=250> | <img src="https://user-images.githubusercontent.com/65807152/136015489-328d8eef-043f-4d0c-9a5f-236c1206321f.jpg" width=250> | <img src="https://user-images.githubusercontent.com/65807152/136015554-966a0d86-13ef-4bba-bf78-edbb3f7c453d.jpg" width=250> | <img src="https://user-images.githubusercontent.com/65807152/136015531-6954d268-4154-42be-9617-277dc3c03c54.jpg" width=250> |

| Comic Detail | Character Series | Series Detail |
| ------------ | ---------------- | ------------- |
| <img src="https://user-images.githubusercontent.com/65807152/136015514-9de47d72-9bc9-4302-a53b-597dcd08500a.jpg" width=215> | <img src="https://user-images.githubusercontent.com/65807152/136015587-c2ec5e8b-76b6-4d2c-aafe-9def50e07283.jpg" width=215> | <img src="https://user-images.githubusercontent.com/65807152/136015575-eb74b351-23b0-4ec6-80a3-9c05b8f7fd41.jpg" width=215> |

## Download
You can Install and test the app from below 👇

[![MarvelApp](https://img.shields.io/badge/Marvel_App-APK-silver.svg?style=for-the-badge&logo=android)](https://github.com/Rohit-2602/Marvel_App/releases)

<img src="/previews/preview.gif" align="right" width="32%"/>

## Techs Used
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - For asynchronous.
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
- [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) - StateFlow is a state-holder observable flow that emits the current and new state updates to its collectors.
- [Android Architecture Components](https://developer.android.com/topic/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - MVVM Architecture (Model - View - ViewModel) 
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes, lifecycle aware.
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
  - [Room Database](https://developer.android.com/training/data-storage/room) - Room is an android library which is an ORM which wraps android's native SQLite database.
- Jetpack
  - Navigation - Build and structure "in-app" UI, handle deep links, and navigate between screens.
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - Paging3 - Load data in pages, and present it in a RecyclerView.
- [Dagger-Hilt](https://dagger.dev/hilt/) - Standard library to incorporate Dagger dependency injection into an Android application.
- [Retrofit & Gson](https://github.com/square/retrofit) - construct the REST APIs.
- [OkHttp3](https://github.com/square/okhttp) - implementing interceptor, logging and mocking web server.
- [Glide](https://github.com/bumptech/glide) - An image loading and caching library for Android focused on smooth scrolling


## Find this repository useful? ❤️
Support it by joining [stargazers](https://github.com/Rohit-2602/Marvel_App/stargazers) for this repository. ⭐
And [follow](https://github.com/Rohit-2602) me for my next creations! 🤩

## Setup Project
- Fork and Clone the project
- Get Your [Api Key](https://www.marvel.com/signin?referer=https%3A%2F%2Fdeveloper.marvel.com%2Faccount)
- According to the [Documentation](https://developer.marvel.com/documentation/authorization) you need **Timestamp, ApiKey (Your Public Key), and Hash**
```
ts      -> a timestamp (or other long string which can change on a request-by-request basis)
apiKey  -> Your Public Key
hash    -> a md5 digest of the ts parameter, your private key and your public key (e.g. md5(ts+privateKey+publicKey)
```
- Generate Your **Hash** from [here](https://www.md5hashgenerator.com/)
- Add this to your **gradle.properties**
```
public_key="Your_Public_Key"
private_key="Your_Private_Key"
hash="Your_hash"
```
- Rebuild the project
- And you are good to go 😃.
