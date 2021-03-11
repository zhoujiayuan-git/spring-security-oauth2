/**
 * 
 */
package cn.zjy.study.security.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 *  @Author: zjy
 *  @Date: 2021/3/11 14:47
 *  @Description: 切片
 */
@Aspect
@Component
public class TimeAspect {

	/**
	 *  @Author: zjy
	 *  @Date: 2021/3/11 14:51
	 *  @Description:
	 * //@Before(相当于拦截器中的preHandle)
	 * //@AfterReturning(相当于拦截器中的postHandle)
	 * //@AfterThrowing(相当于拦截器中的afterCompletion)
	 * //@Around(相当于各种情况)
	 * //注解定义了在什么时候起作用
	 *
	 * //注解里面定义了在哪些方法上起作用
	 * //这里表示UserController中的任何方法都可以起作用
	 * @Param ProceedingJoinPoint 当前拦截的方法的信息
	 *
	 * // 缺点：切片不能获取到原始的http请求（HttpServletRequest）和响应（HttpServletResponse）
	 */
	@Around("execution(* cn.zjy.study.security.web.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		
		System.out.println("time aspect start");

		// 拦截的所有方法的请求参数
		Object[] args = pjp.getArgs();
		for (Object arg : args) {
			System.out.println("arg is "+arg);
		}
		
		long start = new Date().getTime();

		// 调用的控制器返回的信息
		Object object = pjp.proceed();
		
		System.out.println("time aspect 耗时:"+ (new Date().getTime() - start));
		
		System.out.println("time aspect end");
		
		return object;
	}

}
