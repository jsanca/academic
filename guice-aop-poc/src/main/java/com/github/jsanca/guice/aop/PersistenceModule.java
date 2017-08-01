package com.github.jsanca.guice.aop;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class PersistenceModule extends AbstractModule {

	@Override
	protected void configure() {
		//bind the service to implementation class
		bind(UserDAO.class).to(UserDAOImpl.class);

		//bind interceptors
		bindInterceptor(Matchers.any(), Matchers.annotatedWith(Cacheable.class), new CacheableAspect());
	}

}
