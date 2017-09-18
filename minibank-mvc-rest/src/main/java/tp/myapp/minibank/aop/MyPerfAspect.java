package tp.myapp.minibank.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/*
 *  <aop:aspectj-autoproxy/> ou ...
 */

@Aspect
@Component
public class MyPerfAspect {
	
	private Logger logger = LoggerFactory.getLogger(MyPerfAspect.class);

	@Around("execution(* tp.myapp.minibank.impl.domain.service.*.*(..))")
	public Object perfLogOnServices(ProceedingJoinPoint pjp) throws Throwable {
		return perfLog(pjp);
	}
	
	private Object perfLog(ProceedingJoinPoint pjp) throws Throwable {
		logger.trace("<< trace == debut == " + pjp.getSignature().toLongString() + " <<");
		long td = System.nanoTime();
		Object objRes = pjp.proceed();
		long tf = System.nanoTime();
		logger.trace(
				">> trace == fin == " + pjp.getSignature().toShortString() + " [" + (tf - td) / 1000000.0 + " ms] >>");
		return objRes;
	}

}
