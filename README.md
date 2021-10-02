## Marvel App
This project consists of an app made with Kotlin that uses the Marvel API to list all the heroes, appearances and the description.

<img src="https://user-images.githubusercontent.com/65807152/120926816-0867a300-c6fc-11eb-82ad-a7b55b024c7a.jpg" width=250 align=left>
<img src="https://user-images.githubusercontent.com/65807152/120926825-0ef61a80-c6fc-11eb-917e-6c107cba4305.jpg" width=250 align=right>
<img src="https://user-images.githubusercontent.com/65807152/120926822-0c93c080-c6fc-11eb-8fd8-a575e127883a.jpg" width=250 align=center>  

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
