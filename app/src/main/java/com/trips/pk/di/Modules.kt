package com.trips.pk.di

import com.trips.pk.data.OAuthInterceptor
import com.trips.pk.data.PrefRepository
import com.trips.pk.data.TripsApi
import com.trips.pk.data.TripsRepository
import com.trips.pk.model.rent_a_car.VehicleCategory
import com.trips.pk.ui.DashboardViewModel
import com.trips.pk.ui.agent.listing.AgentListingViewModel
import com.trips.pk.ui.agent.main.pkg.AgentVisaAssistanceViewModel
import com.trips.pk.ui.agent.search.AgentSearchViewModel
import com.trips.pk.ui.destination.listing.DestinationListingViewModel
import com.trips.pk.ui.destination.pkg.DestinationPackageViewModel
import com.trips.pk.ui.destination.search.DestinationSearchViewModel
import com.trips.pk.ui.flight.book.FlightBookViewModel
import com.trips.pk.ui.flight.listing.FlightListingViewModel
import com.trips.pk.ui.flight.search.FlightSearchViewModel
import com.trips.pk.ui.hajj.detail.HajjDetailsViewModel
import com.trips.pk.ui.hajj.list.view_all.HajjListViewAllViewModel
import com.trips.pk.ui.insurance.agent.InsuranceAgentListViewModel
import com.trips.pk.ui.insurance.list.InsurancesListViewModel
import com.trips.pk.ui.insurance.search.InsuranceSearchViewModel
import com.trips.pk.ui.news.NewsDetailViewModel
import com.trips.pk.ui.rent_a_car.listing.RentACarSearchResultViewModel
import com.trips.pk.ui.rent_a_car.listing.VehicleListViewModel
import com.trips.pk.ui.rent_a_car.search.RentCarSearchViewModel
import com.trips.pk.ui.tour.book.TourBookListViewModel
import com.trips.pk.ui.tour.detail.TourDetailViewModel
import com.trips.pk.ui.tour.listing.TourListingViewModel
import com.trips.pk.ui.tour.pkg.TourPackageViewModel
import com.trips.pk.ui.tour.search.TourSearchViewModel
import com.trips.pk.ui.visa.detail.VisaDetailViewModel
import com.trips.pk.ui.visa.search.VisaSearchViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//private const val FLIGHT_BASE_URL = "https://flightapis.green.pk/api/"
private const val BASE_URL = "https://api.gotravel.pk/"

val viewModelsModule= module {

    viewModel { DashboardViewModel(get()) }
    viewModel { (heading:String) -> NewsDetailViewModel(get(), heading) }

    //Flight
    viewModel { FlightSearchViewModel(get(), get()) }
    viewModel { FlightListingViewModel(get()) }
    viewModel { FlightBookViewModel(get(), get()) }

    //Tour
    viewModel { TourSearchViewModel(get(),get()) }
    viewModel { TourPackageViewModel(get()) }
    viewModel { (placeName:String) ->TourListingViewModel(get(),placeName) }
    viewModel { (tourId:Int) -> TourDetailViewModel(get(),tourId) }
    viewModel { TourBookListViewModel() }

    //Rent a Car
    viewModel { RentCarSearchViewModel(get()) }
    viewModel { (vehicleList : List<VehicleCategory>) -> VehicleListViewModel(vehicleList) }
    viewModel { RentACarSearchResultViewModel(get()) }

    //Visa
    viewModel { VisaSearchViewModel(get()) }
    viewModel { VisaDetailViewModel(get()) }

    //Insurance
    viewModel { InsuranceSearchViewModel(get()) }
    viewModel { InsuranceAgentListViewModel(get()) }
    viewModel { InsurancesListViewModel(get()) }

    //Hajj
    viewModel { HajjListViewAllViewModel() }
    viewModel { HajjDetailsViewModel() }

    //Destinations
    viewModel { DestinationSearchViewModel() }
    viewModel { DestinationPackageViewModel() }
    viewModel { DestinationListingViewModel() }

    //Agent
    viewModel { AgentSearchViewModel() }
    viewModel { AgentListingViewModel() }
    viewModel { AgentVisaAssistanceViewModel() }
    }

  val repositoriesModule = module {

//    fun provideHttpClient(): OkHttpClient {
//        val logging = HttpLoggingInterceptor()
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//        return OkHttpClient.Builder()
//            .connectTimeout(1, TimeUnit.MINUTES)
//            .readTimeout(60, TimeUnit.SECONDS)
//            .writeTimeout(30, TimeUnit.SECONDS)
//            .addInterceptor(logging)
//            .build()
//    }

fun provideAccessToken(prefRepository: PrefRepository) =
    prefRepository.getBearerToken()

      fun provideHttpClientWithInterceptor(oAuthInterceptor: OAuthInterceptor) =
          OkHttpClient.Builder()
              .addInterceptor(oAuthInterceptor)
              .connectTimeout(1, TimeUnit.MINUTES)
              .readTimeout(60, TimeUnit.SECONDS)
              .writeTimeout(30, TimeUnit.SECONDS)
              .build()

     fun createToursApi(factory: GsonConverterFactory, client: OkHttpClient) = Retrofit.Builder()
         .baseUrl(BASE_URL)
         .addConverterFactory(factory)
         .client(client)
         .build()
         .create(TripsApi::class.java)

      factory { OAuthInterceptor(provideAccessToken(get())) }
      factory { provideHttpClientWithInterceptor(get()) }
   // single { OAuthInterceptor(androidContext().resources.getString(R.string.access_token)) }
   // single { provideHttpClient() }
    single { GsonConverterFactory.create() }
    single { TripsRepository(createToursApi(get(),get())) }
    single { PrefRepository(androidApplication()) }
}
