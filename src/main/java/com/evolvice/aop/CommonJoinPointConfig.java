package com.evolvice.aop;

import org.aspectj.lang.annotation.Pointcut;

public class CommonJoinPointConfig {

	@Pointcut("execution(* com.evolvice.controller.*.*(..))")
	public void controllerMethods() {
	}
}