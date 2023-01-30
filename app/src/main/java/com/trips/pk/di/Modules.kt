package com.trips.pk.di

import com.trips.pk.data.PrefRepository
import com.trips.pk.data.TripsApi
import com.trips.pk.data.FlightRepository
import com.trips.pk.data.TourRepository
import com.trips.pk.ui.flight.book.FlightBookViewModel
import com.trips.pk.ui.flight.listing.FlightListingViewModel
import com.trips.pk.ui.flight.search.FlightSearchViewModel
import com.trips.pk.ui.tour.book.TourBookListViewModel
import com.trips.pk.ui.tour.detail.TourDetailViewModel
import com.trips.pk.ui.tour.listing.TourListingViewModel
import com.trips.pk.ui.tour.pkg.TourPackageViewModel
import com.trips.pk.ui.tour.search.TourSearchViewModel
import com.trips.pk.ui.visa.detail.VisaDetailViewModel
import com.trips.pk.ui.visa.search.VisaSearchViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//private const val FLIGHT_BASE_URL = "https://flightapis.green.pk/api/"
private const val FLIGHT_BASE_URL = "https://api.gotravel.pk/"
private const val TOUR_BASE_URL = "https://api.gotravel.pk/"

val viewModelsModule= module {
    //Flight
    viewModel { FlightSearchViewModel(get(), get()) }
    viewModel { FlightListingViewModel(get()) }
    viewModel { FlightBookViewModel(get(), get()) }

    //Tour
    viewModel { TourSearchViewModel(get(),get()) }
    viewModel { TourPackageViewModel() }
    viewModel { TourListingViewModel(get()) }
    viewModel { TourDetailViewModel(get()) }
    viewModel { TourBookListViewModel() }

    //Visa
    viewModel { VisaSearchViewModel(get()) }
    viewModel { VisaDetailViewModel(get()) }
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

    fun createFlightApi(factory: GsonConverterFactory, client: OkHttpClient) = Retrofit.Builder()
        .baseUrl(FLIGHT_BASE_URL)
        .addConverterFactory(factory)
        .client(client)
        .build()
        .create(TripsApi::class.java)

      fun createTourApi(factory: GsonConverterFactory, client: OkHttpClient) = Retrofit.Builder()
          .baseUrl(TOUR_BASE_URL)
          .addConverterFactory(factory)
          .client(client)
          .build()
          .create(TripsApi::class.java)

   // single { OAuthInterceptor(androidContext().resources.getString(R.string.access_token)) }
    single { provideHttpClient() }
    single { GsonConverterFactory.create() }
    single { FlightRepository(createFlightApi(get(),get())) }
    single { TourRepository(createTourApi(get(),get())) }
    single { PrefRepository(androidApplication()) }
}
