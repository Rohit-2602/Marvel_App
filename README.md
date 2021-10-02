## Marvel App
MarvelApp is an application based on Modern Android Application tech-stacks and MVVM Architecture.
Fetches data from Marvel Api.

| All Characters | Character Search | Character Comics | Character Series |
| -------------- | ---------------- | ---------------- | ---------------- |
| <img src="https://user-images.githubusercontent.com/65807152/135730153-14b3e1eb-0479-4a7a-a899-64ac68405a58.jpg" width=250> | <img src="https://user-images.githubusercontent.com/65807152/135730158-6b8c8e32-eaa5-402e-8687-bf1d952b3c01.jpg" width=250> | <img src="https://user-images.githubusercontent.com/65807152/135730154-68f36a23-bb3f-42a5-907f-68cb4fbf2f0a.jpg" width=250> | <img src="https://user-images.githubusercontent.com/65807152/135730160-db6574ad-4498-4cb0-8c9a-ef6c3a137b0d.jpg" width=250> |

## Techs Used
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - For asynchronous.
- [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) - A cold asynchronous data stream that sequentially emits values and completes normally or with an exception.
- [StateFlow](https://developer.android.com/kotlin/flow/stateflow-and-sharedflow) - StateFlow is a state-holder observable flow that emits the current and new state updates to its collectors.
- [Android Architecture Components](https://developer.android.com/topic/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - MVVM Architecture (Model - View - ViewModel) 
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes, lifecycle aware.
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.
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


## Getting Started
- Create your account on the [**Marvel website**](https://developer.marvel.com) and request your public key and private key to access the API.

- Now, you get Your Public Key and Private Key.

## Api Setup
- Marvel Api needs 3 Parameters **Timestamp, ApiKey (Your Public Key), Hash**

- **Hash** is the md5 Hash of **Timestamp, Private Key and Public Key** [Link To Documentation](https://developer.marvel.com/documentation/authorization)  
- Your can generate Hash [Here](https://www.md5hashgenerator.com/)

- Passing query
```
fun provideHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("apikey", PUBLIC_KEY)
                .addQueryParameter("ts", "1")
                .addQueryParameter("hash", HASH)
                .build()

            chain.proceed(original.newBuilder().url(url).build())
        }
        return httpClient.build()
    }
```
- Providing Retrofit Using Dagger-Hilt
```
fun provideRetrofit(httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()
```
