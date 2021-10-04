package idv.heimlich.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebMvcInitialiser implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		final AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(WebMvcConfig.class);
		// appContext.setConfigLocation(/WEB-INF/spring/dispatcher-config.xml);

		final ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher",
				new DispatcherServlet(rootContext));
		registration.addMapping("/");
		registration.setLoadOnStartup(1);
	}

}
