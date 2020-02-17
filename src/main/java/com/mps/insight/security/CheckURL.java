package com.mps.insight.security;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

//@Priority(value = 1000)
@Provider
public class CheckURL implements ReaderInterceptor{

	@Override
	public Object aroundReadFrom(ReaderInterceptorContext reader) throws IOException, WebApplicationException {
		System.out.println(reader.getHeaders());
		return reader.proceed();
	}
	
	


	
}
