# Hilt

- https://developer.android.com/training/dependency-injection/hilt-android
- Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project
- Hilt provides a standard way to use DI in your application by providing containers for every Android class in your project and managing their lifecycles automatically.
- Hilt is built on top of the popular DI library Dagger to benefit from the compile-time correctness, runtime performance, scalability, and Android Studio support that Dagger provides.

## Annotations used in Hilt
- https://developer.android.com/images/training/dependency-injection/hilt-annotations.pdf
- **@HiltAndroidApp** - Used on the application class
- ViewModel (by using @HiltViewModel)
- Activity
- Fragment
- View
- Service
- BroadcastReceiver
- **@AndroidEntryPoint** generates an individual Hilt component for each Android class in your project.
- To obtain dependencies from a component, use the @Inject annotation to perform field injection
    - **@Inject lateinit var analytics: AnalyticsAdapter**

## Field injection with Hilt
```
@AndroidEntryPoint
class LogsFragment : Fragment() {
    ...
}
```

## Hilt Binding
```
class AnalyticsAdapter @Inject constructor(
  private val service: AnalyticsService
) { ... }
```
## Hilt modules
- @Module
- @InstallIn

## Inject interface instances with @Binds
- The @Binds annotation tells Hilt which implementation to use when it needs to provide an instance of an interface.
```
interface AnalyticsService {
  fun analyticsMethods()
}

// Constructor-injected, because Hilt needs to know how to
// provide instances of AnalyticsServiceImpl, too.
class AnalyticsServiceImpl @Inject constructor(
  ...
) : AnalyticsService { ... }

@Module
@InstallIn(ActivityComponent::class)
abstract class AnalyticsModule {

  @Binds
  abstract fun bindAnalyticsService(
    analyticsServiceImpl: AnalyticsServiceImpl
  ): AnalyticsService
}
```

## Inject instances with @Provides
```
@Module
@InstallIn(ActivityComponent::class)
object AnalyticsModule {

  @Provides
  fun provideAnalyticsService(
    // Potential dependencies of this type
  ): AnalyticsService {
      return Retrofit.Builder()
               .baseUrl("https://example.com")
               .build()
               .create(AnalyticsService::class.java)
  }
}
```

## Provide multiple bindings for the same type
```
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthInterceptorOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class OtherInterceptorOkHttpClient
```

```
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

  @AuthInterceptorOkHttpClient
  @Provides
  fun provideAuthInterceptorOkHttpClient(
    authInterceptor: AuthInterceptor
  ): OkHttpClient {
      return OkHttpClient.Builder()
               .addInterceptor(authInterceptor)
               .build()
  }

  @OtherInterceptorOkHttpClient
  @Provides
  fun provideOtherInterceptorOkHttpClient(
    otherInterceptor: OtherInterceptor
  ): OkHttpClient {
      return OkHttpClient.Builder()
               .addInterceptor(otherInterceptor)
               .build()
  }
}
```

- **@ActivityContext**
- **@InstallIn**
- **@Singleton**
- **@ActivityRetainedScoped**
- **@ViewModelScoped**
- **@ActivityScoped**
- **@FragmentScoped**
- **@ViewScoped**
- **@ServiceScoped**


