package com.mps.insight.product;

import com.mps.insight.dao.MyDataTable;
import com.mps.insight.dao.QueryTemplate;
import com.mps.insight.dto.RequestMetaData;

public class MyRunnableTR implements Runnable {
	int webmartID;
	String dynamicQuery;
	RequestMetaData rmd;
	QueryTemplate template = new QueryTemplate(rmd);
    MyDataTable mdt;
	
	public MyRunnableTR(int webmartID,String dynamicQuery,RequestMetaData rmd) {
		// TODO Auto-generated constructor stub
		this.webmartID=webmartID;
		this.dynamicQuery=dynamicQuery;
		this.rmd=rmd;
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		mdt = template.getMDTofQueryWithWebmartID(webmartID, dynamicQuery);
	}
	
	 public   MyDataTable getReportTr()
	{
	return mdt;
	}

}
