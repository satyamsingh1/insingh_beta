package com.mps.insight.security;

import java.util.Calendar;

public class Authorization {	
	
	//method to encrypt/encode data to BASE64
	public String encryptData(String data){
		EncoderDecoder ed=new EncoderDecoder();
		return ed.encrypt(data);
	}
	
	//method to decrypt/decode data from BASE64
	public String decryptData(String data){
		String decoded = "";
		EncoderDecoder ed=new EncoderDecoder();
		decoded=ed.decrypt(data);
		if(!validateLastLogin(decoded)){
			decoded="0~#~0~#~0";
		}
		return decoded;
	}
	
	@SuppressWarnings("deprecation")
	public boolean validateLastLogin(String data){
		String [] decoded = data.split("~#~");
		boolean result=true;
		int tokentime=Integer.parseInt(decoded[0].trim());
		Calendar cal=Calendar.getInstance();
		//String date=cal.getWeekYear()+""+cal.getTime().getMonth()+""+cal.getTime().getDay()+""+cal.getTime().getHours()+""+cal.getTime().getMinutes();
		String date=cal.getWeekYear()+""+cal.getTime().getMonth()+""+cal.getTime().getDay()+""+cal.getTime().getHours();
		int currenttime=Integer.parseInt(date.trim());
		if(currenttime-tokentime>1){
			result=false;
		}else if(currenttime-tokentime==0){
			result=true;
		}else if(currenttime-tokentime==1){
			result=true;
		}
		return result;
	}

}
