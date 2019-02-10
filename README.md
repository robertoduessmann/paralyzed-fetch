# paralyzed-fetch

>It's a example to how you can run some things in parallel, fetch from an API for example, and wait for the response to return to previously call using `ForkJoinPool`.

## How it works

On [ParalyzedFetchWeather](https://github.com/robertoduessmann/paralyzed-fetch/blob/master/src/main/java/com/paralyzedfetch/service/ParalyzedFetchWeather.java) we define a new `ForkJoinPool`, it will be responsable for handle the parallel requests.


When we instantiate the `ForkJoinPool` without parameters in constructor, it will take from `java.lang.Runtime#availableProcessors` (if we want, we can also define, like 8 or 16 parallelisms).
```java
private static final ForkJoinPool executorService = new ForkJoinPool();
```

To call in parallel all requests is only split the collection and for each item call the executor with method `submit`.
```java
cities.forEach(city -> executorService.submit(() -> weathers.add(getWeather(city))));
```

Once the call is done, we can use the method `awaitQuiescence` to the process waits for the finish for all parallels executions before continue.
```java
executorService.awaitQuiescence(TIMEOUT_MINUTES, TimeUnit.MINUTES);
```

## Installation

### Build
```console
$ mvn clean install
```

### Running
```console
$ mvn spring-boot:run
```

## Usage

### GET /weather/cities
```console
$ curl -X GET http://localhost:8080/weather/cities
```

## License
The MIT License (MIT)