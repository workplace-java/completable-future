# completable-future
This project is a standalone example for completableFuture class with jdk 25

# CompletableFuture
* CompletableFuture is a class that implements Future interface and CompletionStage interface
* Future interface had many drawbacks
  * synchronous operations
  * cannot chain operations
  * cannot combine multiple operations
  * exception handling not efficient
* CompletableFuture addresses all these issues and provides a robust solution for writing code that is
  * asynchronously
  * non-blocking
* The main methods to trigger CompletableFuture are
  * supplyAsync
    * accepts a supplier and run the task asynchronously
    * returns a CompletableFuture as value
  * runAsync
    * accepts a supplier and run the task asynchronously
    * does not return a value
* Other methods that you can be used to chain are
  * thenAccept
    * accept a return value
  * thenApply
    * transforms the result
    * operation similar to map in streams
  * thenCompose
    * then multiple CompletableFuture and compose a new  CompletableFuture
    * it is a sequential operation
    * it is like performing flatmap
  * thenCombine
    * is a parallel operation
    * take multiple CompletableFuture and combine 
    * it is like a zip operation on a folder
  * allOf
    * wait till all the CompletableFuture are complete
  * anyOf
    * wait till any one CompletableFuture is complete
  * exceptionally
    * sequential operation
    * return a fallback value in case of exception
* Terminal Operations
  * join()
    * throws unchecked exception
    * preferred in lambda expression
  * get()
    * throws checked exception

# How to test
* Execute the command `./gradlew bootRun`
* hit the endpoints in the file
  * test_async.http
  * travel_builder.http
