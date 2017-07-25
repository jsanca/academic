package com.github.jsanca.guice.aop;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

import java.util.regex.Matcher;

public class AppInjector extends AbstractModule {

	@Override
	protected void configure() {
		//bind the service to implementation class
		bind(UserAPI.class).to(UserAPIImpl.class);
		bind(UserDAO.class).to(UserDAOImpl.class);

		//bind interceptors
		bindInterceptor(Matchers.any(), Matchers.annotatedWith(Cacheable.class), new CacheableAspect());
		bindInterceptor(Matchers.any(), Matchers.annotatedWith(Transactional.class), new TransactionalAspect());
	}

}
