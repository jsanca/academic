This is an example with a dummy aspect (HelloAspect)

A transactional dummy (just printing on console)

and cacheable (same just printing on console).

Transactional and Cacheable used an annotation approach (see Transactional, Cacheable annotations).
For each of them we use a CacheableAspect and TransactionalAspect in order to process any class 
with a method annotated with the respective transaction.

Keep in mind, that these aspects will be added a compile-time to the byte code.

Just run: ./gradlew run

To see the MyApp running