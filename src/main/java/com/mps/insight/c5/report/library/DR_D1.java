package com.mps.insight.c5.report.library;

import com.mps.insight.c4.report.DynamicMonthCreater;
import com.mps.insight.dto.Counter5DTO;
import com.mps.insight.dto.RequestMetaData;
import com.mps.insight.global.InsightConstant;
import com.mps.insight.global.TableMapper;

public class DR_D1 {
	RequestMetaData rmd;
	DynamicMonthCreater dmc = new DynamicMonthCreater();
	private String tableName = TableMapper.TABALE.get("dr_master_table");
	private String query="";
	private String totalMonth ="";
	private String monthQuery="";
	private String previewType="";
	
 public Counter5DTO dto;
	public DR_D1(Counter5DTO dto,String previewType,RequestMetaData rmd)
	{
		this.dto=dto;
		this.previewType = previewType;
		this.rmd=rmd;
		run();	
	}
	public void run()
	{
		includeMonth();
		generatDR1Report();	
	}
	public void generatDR1Report()
	{
		StringBuilder stb=new StringBuilder();
		try
		{
			stb .append("SELECT "); 
			stb.append(" `Database`,`Publisher`,`Publisher_ID`,`Platform`,");
			stb.append(" CONCAT('"+dto.getPublisher()+"',':',`Proprietary_ID`) AS Proprietary_ID,`Metric_Type`, ");
			stb.append("SUM"+totalMonth+"AS `Reporting_Period_Total`,");
			stb.append(" "+monthQuery+"");
			stb.append(" from "+tableName+" where");
			stb.append(" Institution_ID='"+dto.getInstitutionID()+"' ");
			stb.append(" and "+InsightConstant.DR_D1_WHERE_CONDITION);
			stb.append(" AND"+ totalMonth+">0 ");
			stb.append("GROUP BY "+InsightConstant.DR_D1_MASTER_TITLE);
			stb.append(" ORDER BY `Database` ");
			if(previewType.equalsIgnoreCase("preview")){
				stb.append(" limit 500 ");
			}else{
				
			}
		}
		catch(Exception e)
		{
			rmd.exception("DR_D1 : generatDR_D1Report : Unable to create query "+e.toString());
		}
		this.query=stb.toString();
	}
	public void includeMonth()
	{
		try
		{
			String [] fromarr=dto.getFromDate().split("-");
			String [] toarr=dto.getToDate().split("-");
			monthQuery=dmc.createMonthQueryC5(Integer.parseInt(toarr[0]), Integer.parseInt(toarr[1]),
					Integer.parseInt(fromarr[0]), Integer.parseInt(fromarr[1]));
			monthQuery = monthQuery.substring(0, monthQuery.lastIndexOf(","));
			totalMonth= dmc.createTotalMonthQueryC5(Integer.parseInt(toarr[0]), Integer.parseInt(toarr[1]),
					Integer.parseInt(fromarr[0]), Integer.parseInt(fromarr[1]), "");
		}
		catch(Exception e)
		{
			rmd.exception("DR_D1 : unable to add month in query"+e.toString());
		}
	}
	
	
	
	public String getQuery() {
		rmd.log(query.toString());
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
	
}
