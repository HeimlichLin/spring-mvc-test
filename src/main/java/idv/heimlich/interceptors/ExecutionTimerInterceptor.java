package idv.heimlich.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 攔截器
 */
@Service
public class ExecutionTimerInterceptor implements HandlerInterceptor {

	private static Logger LOGGER = LoggerFactory.getLogger(ExecutionTimerInterceptor.class);
	long PREHANDLE_TIME = 0;
	long POSTHANDLE_TIME = 0;
	long AFTER_COMPLETION_TIME = 0;

	/**
	 * 請求送到Controller前執行，回傳一個布林值，如果是true通過攔截器，反之則否
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		this.PREHANDLE_TIME = System.currentTimeMillis();
		LOGGER.info("Info Message: PREHANDLE_TIME = " + this.PREHANDLE_TIME);
		return true;
	}

	/**
	 * Controller處理完後執行
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		this.POSTHANDLE_TIME = System.currentTimeMillis();
		LOGGER.info("Info Message: POSTHANDLE_TIME = " + this.POSTHANDLE_TIME);
	}

	/**
	 * 整個請求及回應結束後執行
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		this.AFTER_COMPLETION_TIME = System.currentTimeMillis();
		LOGGER.info("Info Message: AFTER_COMPLETION_TIME = " + this.AFTER_COMPLETION_TIME);
		final long HANDLER_EXECUTION_TIME = this.POSTHANDLE_TIME - this.PREHANDLE_TIME;
		final long TOTAL_EXECUTION_TIME = this.AFTER_COMPLETION_TIME - this.PREHANDLE_TIME;
		LOGGER.info("Info Message: HANDLER_EXECUTION_TIME = " + HANDLER_EXECUTION_TIME);
		LOGGER.info("Info Message: TOTAL_EXECUTION_TIME = " + TOTAL_EXECUTION_TIME);
	}

}
