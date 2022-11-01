package com.greenart.ch1;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AopLoggingAdvice {
	@Around("execution(* com.greenart.ch1.AopMyMath.*(..))") // pointcut - 부가기능이 적용될 메서드의 패턴
	public Object methodCallLog(ProceedingJoinPoint pjp) throws Throwable {
		// before
		long start = System.currentTimeMillis();
		System.out.println("<<[start]" + pjp.getSignature().getName()+Arrays.toString(pjp.getArgs()));
		
		Object result = pjp.proceed(); // target의 메서드 호출
		
		// after
		System.out.println("result =" + result);
		System.out.println("[end]>>" + (System.currentTimeMillis() - start)+ "ms");
		return result;
	}
}
