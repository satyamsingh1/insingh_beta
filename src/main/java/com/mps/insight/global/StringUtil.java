package com.mps.insight.global;

public class StringUtil {

	   public static String findValue(String state,String token){
 		  String strValue=null;
 		  String result[]=state.split(";");
				for(int i=0;i<=result.length-1;i++){
					if(result[i].startsWith(token)){
						int index=result[i].indexOf("::");
						strValue=result[i].substring(index+2);
					}
					
				}
				return strValue;
 	  }
}
