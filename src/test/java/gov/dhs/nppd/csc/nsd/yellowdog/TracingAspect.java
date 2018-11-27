package gov.dhs.nppd.csc.nsd.yellowdog;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TracingAspect {

	Logger logger = LoggerFactory.getLogger(TracingAspect.class);

	@Before("execution(* gov.dhs..*.*(..))")
	public void before(JoinPoint joinPoint) {
		StringBuilder sb = new StringBuilder();
		for (Object arg : joinPoint.getArgs()) {
			sb.append(arg).append(" --- ");
		}
		logger.info(">>>> Entering for {}", joinPoint);
		logger.info(">>>> args {}", sb);
	}

	@After("execution(* gov.dhs..*.*(..))")
	public void after(JoinPoint joinPoint) {
		StringBuilder sb = new StringBuilder();
		for (Object arg : joinPoint.getArgs()) {
			sb.append(arg).append(" --- ");
		}
		logger.info(">>>> args {}", sb);
		logger.info(">>>> Exiting for {}", joinPoint);
	}
}