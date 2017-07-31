package com.github.jsanca.guice.aop;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;

public class AppInjector extends AbstractModule {

	@Override
	protected void configure() {
		//bind the service to implementation class
		bind(UserAPI.class).to(UserAPIImpl.class);
		bind(UserDAO.class).to(UserDAOImpl.class);
		bind(UserAPI.class).annotatedWith(Names.named("superUserAPI")).to(SuperUserAPI.class);
        bind(UserAPI.class).annotatedWith(Names.named("myUserAPI")).toProvider(UserAPIFactory.class);
        bind(UserAPI.class).annotatedWith(CustomUserAPI.class).to(CustomUserAPIImpl.class);

		//bind interceptors
		bindInterceptor(Matchers.any(), Matchers.annotatedWith(Cacheable.class), new CacheableAspect());
		bindInterceptor(Matchers.any(), Matchers.annotatedWith(Transactional.class), new TransactionalAspect());
	}

}
