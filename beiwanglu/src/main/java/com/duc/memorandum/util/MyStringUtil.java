package com.duc.memorandum.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

public class MyStringUtil {
	public static List<Long> getLongListByString(String targetString) {
		List<Long> longList = new ArrayList<Long>();
		try {
			if (StringUtils.isNotBlank(targetString)) {

				String[] longStringArray = targetString.split(",");
				List<String> longStringList = Arrays.asList(longStringArray);
				if (CollectionUtils.isNotEmpty(longStringList)) {
					for (String longString : longStringList) {
						Long longValue = Long.valueOf(longString);
						if (longValue != null && longValue > 0) {
							longList.add(longValue);
						}
					}
				}
			}
		} catch (Exception e) {
		}
		return longList;
	}

	
	public static List<String> getListByString(String targetString) {
		List<String> longList = new ArrayList<String>();
		try {
			if (StringUtils.isNotBlank(targetString)) {

				String[] longStringArray = targetString.split(",");
				List<String> longStringList = Arrays.asList(longStringArray);
				if (CollectionUtils.isNotEmpty(longStringList)) {
					for (String longString : longStringList) {
							longList.add(longString);
					}
				}
			}
		} catch (Exception e) {
		}
		return longList;
	}
	
	// 过滤特殊字符
   public static String StringFilter(String str) throws PatternSyntaxException { 
	    String result=null;
	   
	   String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？?]"; 
	   Pattern p = Pattern.compile(regEx); 
	   Matcher m = p.matcher(str);
	   
	   result=m.replaceAll("").trim();

	   return result;
	} 
   
/*   public static void main(String[] args) {
//	   StringFilter("MR?");
	   System.out.println(StringFilter("MR?"));
}*/
	
}
