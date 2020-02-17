package com.mps.insight.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import com.mps.insight.dto.RequestMetaData;
import com.mps.insight.dto.UserDTO;
import com.mps.insight.exception.MyException;
import com.mps.insight.product.Users;

@Provider
public class CORSResponseFilter implements ContainerResponseFilter, ContainerRequestFilter {

	Authorization authorization=null;
	ContainerRequestContext requestContext;
	ContainerResponseContext responseContext;
	
	 @Context
	 private HttpServletRequest sr;
	 
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		this.requestContext=requestContext;
		MultivaluedMap<String, String> tokenFromHeader = requestContext.getHeaders();
		List<String> token =null;
		//Getting token From Request Header (will not be executed in case of login page)
		 
		try{
			
			//getting token from POST method & header parameter
			try{token = tokenFromHeader.get("token");}catch(Exception e){/*token = null;*/}
			if(token == null || token.isEmpty()){
				// no token found in POST request Header
				//looking for token in query parameters
				try{token = requestContext.getUriInfo().getQueryParameters().get("token");}catch(Exception e){/*token = null;*/}
			}else{
				
			}
			RequestMetaData rmd=null;
			try{rmd = authenticateToken(token.get(0));}catch(Exception e){}
			requestContext.setProperty("RMD", rmd);
			
		}catch(Exception e){
			
		}
	}
	
	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {

		
		
		responseContext.getHeaders().add("Access-Control-Allow-Headers",
                "X-Requested-With, "
                + "Content-Type, "
                + "X-Codingpedia, "
                + "authorization, "
                + "origin, "
                + "cache-control, "
                + "content-type, "
                + "token, "
                + "apikey, "
                + "accept");
		responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
		responseContext.getHeaders().add("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS, HEAD");
		try{
			RequestMetaData rmd = (RequestMetaData)requestContext.getProperty("RMD");
			rmd.destroyConnection();
		}catch(Exception e){}
		
	}

	private RequestMetaData authenticateToken(String token) throws Exception {
		RequestMetaData rmd =null;
		try{
			if (token == null) {
				throw new MyException("Token : NULL");
			}
			if (token.trim().equalsIgnoreCase("")) {
				throw new MyException("Token : BLANK");
			}
			if (token.contains(" ")) {
				token=token.replace(" ", "+");
			}
			String remoteIP="";
			try{remoteIP= sr.getRemoteAddr();}catch(Exception e1){}
	       
			
			Users tempUser=new Users();
			authorization = new Authorization();
			String decode = authorization.decryptData(token);
			String[] temp = decode.split("~#~");
			if (temp == null) {
				throw new MyException("decode split : NULL");
			}
			if (temp.length != 3) {
				throw new MyException("decode split : Invalid Length");
			}

			rmd =new RequestMetaData();
			
			rmd.setUserCode(temp[1].trim());
			rmd.setWebmartID(Integer.parseInt(temp[2].trim()));
			UserDTO user = tempUser.getUserDetailByUserCode(rmd.getUserCode(),rmd.getWebmartID());			
			rmd.setParameters(user, token);
			rmd.setRmd(rmd);
			rmd.setLiveYearMoth();
			rmd.setRemoteIP(remoteIP);
		}catch(Exception e){
			throw e;
		}
		return rmd;
	}
	
	
/*
	@Override
	public void aroundWriteTo(WriterInterceptorContext writer) throws IOException, WebApplicationException {
		
		MultivaluedMap<String, Object> header = writer.getHeaders();
		System.out.println(header);
		writer.proceed();
		
	}

	*/

}
