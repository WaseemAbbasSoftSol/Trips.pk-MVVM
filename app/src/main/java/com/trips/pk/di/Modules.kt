package com.trips.pk.di

import com.trips.pk.R
import com.trips.pk.data.OAuthInterceptor
import com.trips.pk.data.PrefRepository
import com.trips.pk.data.TripsApi
import com.trips.pk.data.TripsRepository
import com.trips.pk.ui.flight.book.FlightBookViewModel
import com.trips.pk.ui.flight.listing.FlightListingViewModel
import com.trips.pk.ui.flight.search.FlightSearchViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://flightapis.green.pk/api/"

val viewModelsModule= module {
    viewModel { FlightSearchViewModel(get(), get()) }
    viewModel { FlightListingViewModel(get()) }
    viewModel { FlightBookViewModel(get(), get()) }
    }

  val repositoriesModule = module {

    fun provideHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }

    fun createApi(factory: GsonConverterFactory, client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(factory)
        .client(client)
        .build()
        .create(TripsApi::class.java)
   // single { OAuthInterceptor(androidContext().resources.getString(R.string.access_token)) }
    single { provideHttpClient() }
    single { GsonConverterFactory.create() }
    single { createApi(get(), get()) }
    single { TripsRepository(get()) }
    single { PrefRepository(androidApplication()) }
}
