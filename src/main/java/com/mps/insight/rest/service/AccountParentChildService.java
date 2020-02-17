package com.mps.insight.rest.service;

import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mps.insight.dao.MyDataTable;
import com.mps.insight.dto.RequestMetaData;
import com.mps.insight.exception.MyException;
import com.mps.insight.global.InsightConstant;
import com.mps.insight.product.AccountParentChild;
import com.mps.insight.security.Authorization;

@Path("accountparentchild")
public class AccountParentChildService {
	@Context
	private HttpServletRequest servletRequest; 
	
	private static final Logger LOG = LoggerFactory.getLogger(AccountParentChildService.class);

	String userCode;
	int webmartID;
	Authorization authorization;
	RequestMetaData rmd;
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getparents")
	public Response getParent(@FormParam("accountCode") String accountCode) throws Exception {
		double iTracker=0.0;
		rmd = (RequestMetaData)servletRequest.getAttribute("RMD");
		ResponseBuilder rb = null;
		AccountParentChild accountParentChild = null;
		try {
			iTracker=1.0;
			webmartID = rmd.getWebmartID();
			iTracker=2.0;
			userCode = rmd.getUserCode();
			rmd.log("In getParent Method Getting Tiles getChild detail ");
			if (webmartID == 0) {
				throw new MyException(InsightConstant.ERROR_SESSION);
			}
			accountParentChild = new AccountParentChild(rmd);
			iTracker=3.0;
			MyDataTable mdt = accountParentChild.getParents(webmartID, accountCode);
			iTracker=4.0;
			JsonObject jobject=mdt.getJson();
			iTracker=5.0;
			rb = Response.status(200).entity(jobject.toString());
			
		} catch (Exception e) {
			rmd.exception("AccountParentChildService : getParent : iTracker : "+iTracker+" : "+e.toString());
			rb = Response.status(Response.Status.EXPECTATION_FAILED).entity(e.toString());
		}
		return rb.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("getchild")
	public Response getChild(@FormParam("accountCode") String accountCode) throws Exception {
		double iTracker=0.0;
		rmd = (RequestMetaData)servletRequest.getAttribute("RMD");
		ResponseBuilder rb = null;
		AccountParentChild accountParentChild = null;
		try {
			iTracker=1.0;
			webmartID = rmd.getWebmartID();
			iTracker=2.0;
			userCode = rmd.getUserCode();
			rmd.log("In getChild Method Getting Tiles getChild detail ");
			if (webmartID == 0) {
				throw new MyException(InsightConstant.ERROR_SESSION);
			}
			
			
			
			accountParentChild = new AccountParentChild(rmd);
			String accountType=accountParentChild.getAccountType(accountCode);
			
			MyDataTable mdt =null;
			if(accountType.equalsIgnoreCase("INSTITUTION")){
				 mdt = accountParentChild.getParents(webmartID, accountCode);
			}else{
				iTracker=3.0;
				mdt = accountParentChild.getChild(webmartID, accountCode);
			}
			
			iTracker=4.0;
			JsonObject jobject=mdt.getJson();
			iTracker=5.0;
			rb = Response.status(200).entity(jobject.toString());
		} catch (Exception e) {
			rmd.exception("AccountParentChildService : getChild : iTracker : "+iTracker+" : "+e.toString());
			rb = Response.status(Response.Status.EXPECTATION_FAILED).entity(e.toString());
			
		}
		return rb.build();
	}

}
