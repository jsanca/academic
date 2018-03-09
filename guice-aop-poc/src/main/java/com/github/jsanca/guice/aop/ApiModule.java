package com.github.jsanca.guice.aop;

import com.github.jsanca.guice.aop.test.SuperTester;
import com.github.jsanca.guice.aop.test.TestImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;

import java.util.Arrays;

public class ApiModule extends AbstractModule {

	@Override
	protected void configure() {
		//bind the service to implementation class
        bind(Util.class);
		bind(UserAPI.class).to(UserAPIImpl.class);
		bind(UserAPI.class).annotatedWith(Names.named("superUserAPI")).to(SuperUserAPI.class);
        bind(UserAPI.class).annotatedWith(Names.named("myUserAPI")).toProvider(UserAPIProvider.class);
        bind(UserAPI.class).annotatedWith(CustomUserAPI.class).to(CustomUserAPIImpl.class);

        Class aClass = SuperTester.class;
        Class [] interfaces = aClass.getInterfaces();

        System.out.println(Arrays.asList(interfaces));

        aClass = TestImpl.class;
        interfaces = aClass.getInterfaces();

        // if the DotBean has an interfaces set (check if the interfaces is part of the implementation), will use it, otherwise will use the first one

        System.out.println(Arrays.asList(interfaces));

        aClass = MyApp.class;
        interfaces = aClass.getInterfaces();

        // if the DotBean has an interfaces set (check if the interfaces is part of the implementation), will use it, otherwise will use the first one

        System.out.println(Arrays.asList(interfaces));

        //bind interceptors
		bindInterceptor(Matchers.any(), Matchers.annotatedWith(Transactional.class), new TransactionalAspect());
	}

	@Provides
	protected PrintHelper printHelper () {

	    return new PrintHelperImpl();
    }

    // we should be able to add modules, in the modules we should be able to add standard module, set of class or set of packages.
}
