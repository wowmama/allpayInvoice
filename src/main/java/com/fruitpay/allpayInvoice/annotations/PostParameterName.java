package com.fruitpay.allpayInvoice.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fruitpay.allpayInvoice.machine.MachineType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface PostParameterName {
	public String name() default "";
	public boolean isEncode() default true;
	public MachineType[] method();
}
