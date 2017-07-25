This is an example with a dummy aspects with Guice and Ioc, it is basically doing a dummy stack call over differente layer, such as
DAO, API (Service) and App; all of them are living in the Guice Ioc Container and the bind for the AOP is done for two annotations
(Transactiona and Cacheable)

A transactional dummy (just printing on console)

and cacheable (same just printing on console).

Transactional and Cacheable used an annotation approach (see Transactional, Cacheable annotations).
For each of them we use a CacheableAspect and TransactionalAspect in order to process any class 
with a method annotated with the respective transaction.

Keep in mind, that these aspects will be added a runtime-time by proxy pattern and just works for objects created by Guice.

Just run: ./gradlew run

To see the MyApp running