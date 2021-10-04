package idv.heimlich.config;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import idv.heimlich.config.evn.EVNConfigProducer;
import idv.heimlich.interceptors.ExecutionTimerInterceptor;
import idv.heimlich.interceptors.HeadlerInterceptor;

/**
 * 設定檔
 */
@Configuration
@ComponentScan("idv.heimlich")
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private HeadlerInterceptor headlerInterceptor;

	@Autowired
	private ExecutionTimerInterceptor executionTimerInterceptor;

//	@Bean
//	public DataSource dataSource() {
//		final JndiDataSourceLookup dsLookup = new JndiDataSourceLookup();
//		dsLookup.setResourceRef(true);
//		DataSource dataSource = dsLookup.getDataSource("jdbc/db");
//		return dataSource;
//	}

	@Bean
	public DataSource dataSource() {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(EVNConfigProducer.getConfig().getDriverClass());
		dataSource.setUrl(EVNConfigProducer.getConfig().getConnectionIP());
		dataSource.setUsername(EVNConfigProducer.getConfig().getUserName());
		dataSource.setPassword(EVNConfigProducer.getConfig().getPassword());
		return dataSource;
	}

	/**
	 * 建立MultipartResolver，用來接收requestMappingAndParamHome.jsp的上傳檔案enctype="multipart/form-data"資料
	 * 此CommonsMultipartResolver可以設定編碼方式、接收檔案大小等
	 * •CommonsMultipartResolver：使用Commons Fileupload 來處理multipart請求
	 * •StandardServletMultipartResolver：基於Servlet 3.0來處理multipart請求
	 */
	@Bean
	public MultipartResolver multipartResolver() {
		return new CommonsMultipartResolver();
	}

	/**
	 * 
	 * @return
	 */
	@Bean
	public RequestMappingHandlerMapping requestMappingHandlerMapping() {
		final RequestMappingHandlerMapping rmhm = new RequestMappingHandlerMapping();
		// 路徑符合都會導入 ex:xxx xxx.html xxx.jsp 皆到同一page
		rmhm.setUseSuffixPatternMatch(true); // '/ members'與'/ members.xyz'，皆同位置
		rmhm.setUseTrailingSlashMatch(true); // '/ members'與'/ members /'，皆同位置

		// 路徑要完全相同才會導入 ex:xxx xxx.html xxx.jsp 為不同page
//		rmhm.setUseSuffixPatternMatch(false);
//		rmhm.setUseTrailingSlashMatch(false);
		return rmhm;
	}

	/**
	 * ViewResolver接口的簡單實現，轉化為邏輯視圖名稱直接解析為URL，而沒有顯式映射定義。
	 * 如果邏輯名稱以直截了當的方式與視圖資源的名稱匹配，而不需要任意映射，則這是合適的
	 * 
	 * @return
	 */
	@Bean
	public UrlBasedViewResolver urlBasedViewResolver() {
		final UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		resolver.setViewClass(JstlView.class);
		return resolver;
	}

	/* 檢視跳轉控制器 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("testMvcHome");
//		registry.addViewController("/").setViewName("home");
//		registry.addViewController("/toLogin").setViewName("login");
//		<mvc:view-controller path="/" view-name="home">;
	}

	/* 攔截器配置 */
	// addPathPatterns("/XXX/**").excludePathPatterns("/XX/*")
	// 對來自 /XXX/** 鏈結的請求進行攔截，對來自 /XX/* 鏈結的請求不進行攔截
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new HeaderInterceptor());
		registry.addInterceptor(this.headlerInterceptor);
		registry.addInterceptor(this.executionTimerInterceptor).addPathPatterns("/location");

//		registry.addInterceptor(this.headlerInterceptor).addPathPatterns("/**").excludePathPatterns("/emp/toLogin",
//				"/emp/login", "/js/**", "/css/**", "/images/**");
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer configurer) {
		// TODO Auto-generated method stub

	}

	/* 配置內容裁決的一些選項 */
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		// 如果configureViewResolvers使用registry.enableContentNegotiation(defaultViews);
		/* 是否通過請求Url的副檔名來決定media type */
//		configurer.favorPathExtension(true)
//				/* 不檢查Accept請求頭 */
//				.ignoreAcceptHeader(true).parameterName("mediaType")
//				/* 設定預設的media yype */
//				.defaultContentType(MediaType.TEXT_HTML)
//				/* 請求以.html結尾的會被當成MediaType.TEXT_HTML */
//				.mediaType("html", MediaType.TEXT_HTML)
//				/* 請求以.json結尾的會被當成MediaType.APPLICATION_JSON */
//				.mediaType("json", MediaType.APPLICATION_JSON);

	}

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		// TODO Auto-generated method stub

	}

	/* 預設靜態資源處理器 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		// 註冊一個預設的Handler：DefaultServletHttpRequestHandler
		// 未找到合適的Handler，即會使用此DefaultServletHttpRequestHandler
//		configurer.enable();
//		configurer.enable("defaultServletName");
		// 另WebMvcInitialiser的dispatcher攔截了所有"/"的訪問，會造成無法訪問根目錄下的靜態資源ex:圖1.png，可以透過這個訪問
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		// TODO Auto-generated method stub

	}

	/* 靜態資源處理 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// ex: 輸入 http://localhost:8080/my/elephant.jpg
//		registry.addResourceHandler("/my/**").addResourceLocations("classpath:/my/"); // 訪問自定義的my資料夾的elephant.jpg
//		registry.addResourceHandler("/my/**").addResourceLocations("file:E:/my/"); // 訪問外部路徑
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub

	}

	/* 這裡配置檢視解析器 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		// 註冊一個內部資源檢視解析器InternalResourceViewResolver 所有jsp都是它進行解析的。該方法引數用來指定路徑的字首和檔案字尾
//		registry.jsp("/WEB-INF/jsp/", ".jsp");
		// 註冊一個BeanNameViewResolver 檢視解析器，假如返回的檢視名稱是example，它會到spring容器中找有沒有一個叫example的bean，並且這個bean是View.class型別的，如果有，返回這個bean
//		registry.beanName();
		// 建立一個內容裁決解析器ContentNegotiatingViewResolver，管理你註冊的所有檢視解析器
//		registry.enableContentNegotiation(defaultViews);
//		registry.enableContentNegotiation(new MappingJackson2JsonView());
		// 註冊各種各樣的檢視解析器的
//		registry.viewResolver(viewResolver);

		// 舉例
//		registry.jsp("/WEB-INF/jsp/", ".jsp");
//		registry.enableContentNegotiation(new MappingJackson2JsonView());		
		/*
		 * 依此例開啟兩種解析器，L.183會取得/WEB-INF/jsp/，
		 * 下候選的.jsp，以及L.188一個預設的MappingJackson2JsonView，
		 * 再依configureContentNegotiation的MediaType規則來選擇
		 * 
		 * 如果http://localhost:8080/test.json，表示請求的media type
		 * 為MediaType.APPLICATION_JSON，InternalResourceViewResolver
		 * 解析出來的檢視的ContentType與其不符，與MappingJackson2JsonView 的ContentType相符
		 * 
		 * 如果http://localhost:8080/test， 表示請求為預設的media type
		 * 為MediaType.TEXT_HTML， 就選用InternalResourceViewResolver
		 * /WEB-INF/jsp/test.jsp
		 */
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub

	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		// TODO Auto-generated method stub

	}

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		// TODO Auto-generated method stub

	}

	@Override
	public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		// TODO Auto-generated method stub

	}

	@Override
	public Validator getValidator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MessageCodesResolver getMessageCodesResolver() {
		// TODO Auto-generated method stub
		return null;
	}

}
