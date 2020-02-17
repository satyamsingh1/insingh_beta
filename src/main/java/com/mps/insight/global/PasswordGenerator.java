package com.mps.insight.global;

public class PasswordGenerator {

	public static final String IS_NUMERIC = "isNumeric";
	public static final String IS_ALPHANUM = "isAplhanum";	

	private static final String CAPALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String NUM = "0123456789";	
	private boolean allowDups = false;
	private boolean isAlphanum = false;

	private int passwordLength = 0;
	
	public PasswordGenerator() {}
	
	public PasswordGenerator(int passwordLength,boolean allowDups) {
		this.passwordLength = passwordLength;
		this.allowDups = allowDups;		
		this.isAlphanum = true;				
	}
	
	/**
	 * calculate the max length that a password can be.
	 * @return length
	 */
	public int getMaxLength() {
		int maxLength = 0;
		
		if (isAlphanum) {
			maxLength = 36;
		}
		
		if (allowDups) {
			maxLength = passwordLength + 1;
		}
		return maxLength;
	}
	
	/**
	 * randomly builds a new password string for the user.
	 * @return string 
	 */
	public String getNewPassword() {
		String returnVal = "";
		try {
			StringBuilder values = buildList();
			for (int inx = 0; inx < passwordLength; inx++) {
				int selChar = (int) (Math.random() * (values.length() - 1));
				returnVal += values.charAt(selChar);
				if (!allowDups) {
					values.deleteCharAt(selChar);
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return returnVal;
	}
	
	/**
	 * Randomly builds the StringBuffer to build the password from.
	 * @return StringBuilder for password
	 */
	private StringBuilder buildList() {
		StringBuilder list = new StringBuilder(0);
		if (isAlphanum) {
			list.append(NUM);
			list.append(CAPALPHA);
		}

		int currLen = list.length();
		String returnVal = "";
		for (int inx = 0; inx < currLen; inx++) {
			int selChar = (int) (Math.random() * (list.length() - 1));
			returnVal += list.charAt(selChar);
			list.deleteCharAt(selChar);
		}
		list = new StringBuilder(returnVal);
		return list;
	}	

}
