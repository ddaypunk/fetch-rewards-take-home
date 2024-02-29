# fetch-rewards-take-home

A take home app for Fetch Rewards interview querying a single endpoint for data to display to a
single screen

## Stack/Technologies

- **Android**
- **Kotlin**
- **Dagger/Hilt** for Dependency Injection
- **Jetpack Compose** for User Interface
- **Ktor** for HTTP client
- **MVVM** architecture
- **Junit5** for Unit Tests
- **AssertK** for assertions

## Clean Package Structure

### Main

- hiring (single screen app for now - feature level package)
  - data (models and repositories)
  - presentation (components and screens)
- core (theoretically shared in the future)
  - client (ktor for api calls)
  - di (Hilt modules)
  - screen (error and loading screens)

### UnitTest

- set to mimic application structure
