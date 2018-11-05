package com.zac.batch.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {

	public static boolean isValidDate(String str, String formatStr) {
		boolean convertSuccess = true;
		// 指定日期格式为四位年/两位月份/两位日期，注意yyyy/MM/dd区分大小写；
//		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		try {
			// 设置lenient为false.
			// 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
			format.setLenient(false);
			format.parse(str);
		} catch (ParseException e) {
			// e.printStackTrace();
			// 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
			convertSuccess = false;
		}
		return convertSuccess;
	}
}
