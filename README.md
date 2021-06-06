## Marvel App
This project consists of an app made with Kotlin that uses the Marvel API to list all the heroes, appearances and the description.

## Techs Used
Retrofit (for api call), Dagger (for Dependency Injection), Paging3

## Getting Started
- Create your account on the [**Marvel website**](https://developer.marvel.com/) and request your public key and private key to access the API.

- Now, you get Your Public Key and Private Key.

## Api Setup
- Api needs 3 Parameters 
- Timestamp, ApiKey (Your Public Key), Hash
- Without Hash Your will get Error -> **You must provide a hash**

Hash is the md5 Hash of **Timestamp, Private Key and Public Key** [Link To Documentation](https://developer.marvel.com/documentation/authorization)  
Your can generate Hash [Here](https://www.md5hashgenerator.com/)

- To pass query
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
```
fun provideRetrofit(httpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()
```
