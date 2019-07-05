package com.gsta.app.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * 
 * 日期处理工具类
 * 
 * @version 1.0
 */

public class DateUtil {
	/***
	 * 
	 * 日期格式类型
	 * 
	 */
	public enum DateFormatType {
		/**
		 * 格式为：yyyy-MM-dd HH:mm:ss
		 */
		DATE_FORMAT_STR1("yyyy-MM-dd HH:mm:ss"),
		/**
		 * 格式为:yyyy-MM-dd HH:mm
		 * */
		DATE_FORMAT_STR2("yyyy-MM-dd  HH:mm"),
		/**
		 * 格式为：yyyyMMddHHmmss
		 */
		DATE_FORMAT_STR3("yyyyMMddHHmmss"),

		/**
		 * 格式为：yyyy-MM-dd
		 */
		DATE_FORMAT_STR4("yyyy-MM-dd"),

		/**
		 * 格式为：yyyy/MM/dd
		 */
		DATE_FORMAT_STR5("yyyy/MM/dd"),

		/**
		 * 格式为：HH:mm:ss
		 */
		DATE_FORMAT_STR6("HH:mm:ss"),

		/**
		 * 格式为：HH:mm
		 */
		DATE_FORMAT_STR7("HH:mm"),
		/**
		 * 格式为：yyyyMMdd
		 */
		DATE_FORMAT_STR8("yyyyMMdd"),
		
		/**
		 * 格式为：yyyy
		 */
		DATE_FORMAT_STR9("yyyy"),
		
		/**
		 * 格式为:yyyy年MM月dd日 HH:mm
		 * */
		DATE_FORMAT_STR10("yyyy年MM月dd日 HH:mm"),
		/**
		 * 格式为：yyyyMM
		 */
		DATE_FORMAT_STR11("yyyyMM"),
		/**
		 * 格式为：MM月dd日
		 */
		DATE_FORMAT_STR12("MM月dd日"),
		
		/**
		 * 格式为：yyMM
		 */
		DATE_FORMAT_STR13("yyMM"),
		DATE_FORMAT_STR14("yyMMddHHmmss"),
		DATE_FORMAT_STR15("yyMMdd"),
		DATE_FORMAT_STR16("yyMMdd-HH:mm:ss"),
		DATE_FORMAT_STR17("yyyy.MM.dd HH:mm"),
		DATE_FORMAT_STR18("MM/dd HH:mm");
		private final String value;

		DateFormatType(String formatStr) {
			this.value = formatStr;
		}

		public String getValue() {
			return value;
		}
	}

	/***
	 * 
	 * 时间单位类型
	 * 
	 */
	public enum TimeFormatType {

		YEAR(1), MONTH(2), DAY(5), HOUR(11), MINUTE(12), SECOND(13);
		private final int value;

		TimeFormatType(int formatStr) {
			this.value = formatStr;
		}

		public int getValue() {
			return value;
		}
	}

	/***
	 * 
	 * 日期操作类型
	 * 
	 */
	public enum DateOperationType {

		/**
		 * 加操作
		 */
		ADD(true),

		/**
		 * 减操作
		 */
		DIFF(false);

		private final boolean value;

		DateOperationType(boolean operation) {
			this.value = operation;
		}

		public boolean getValue() {
			return value;
		}
	}

	/**
	 * 获取当前时间(原始格式)
	 */
	public static Date getCurrentDate() {
		Date date = new Date();
		return date;
	}

	/**
	 * 获取当前时间日期的字符串
	 */
	public static String getCurrentDateStr(DateFormatType dateFormatType) {
		Date date = getCurrentDate();
		return (String) OpearationDate(date, dateFormatType.getValue());
	}

	/**
	 * 时间、日期格式化成字符串
	 */
	public static String formatDateToStr(Date date,
			DateFormatType dateFormatType) {
		return (String) OpearationDate(date, dateFormatType.getValue());
	}

	/**
	 * 从字符串解析成时间、日期
	 */
	public static Date formatStrToDate(String dateStr,
			DateFormatType dateFormatType) {
		return (Date) OpearationDate(dateStr, dateFormatType.getValue());
	}

	/**
	 * 获取当前日期的年、月、日、时、分、秒
	 */
	public static int getTime(TimeFormatType timeFormatType) {
		return getTime(getCurrentDate(), timeFormatType);
	}

	/**
	 * 获取指定日期的年、月、日、时、分、秒
	 */
	public static int getTime(Date date, TimeFormatType timeFormatType) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			int type = timeFormatType.getValue();
			int i = c.get(type);
			return type == 2 ? i + 1 : i;
		} catch (Exception e) {
			throw new RuntimeException("获取失败", e);
		}
	}

	/**
	 * 获取指定日期的毫秒数
	 */
	public static long getMillis(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 获取当前日期的毫秒数
	 */
	public static long getMillis() {
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		return c.getTimeInMillis();
	}

	/***
	 * 日期相加、减操作
	 * 
	 * @param date
	 * @param diffDate
	 * @param dateOperationType
	 *            操作类型
	 * @return 天数
	 */
	public static int operationDate(Date date1, Date date2,
			DateOperationType dateOperationType) {
		long add = getMillis(date1) + getMillis(date2);
		long diff = getMillis(date1) - getMillis(date1);
		return (int) ((dateOperationType.getValue() ? add : diff) / (24 * 3600 * 1000));
	}

	public static Object OpearationDate(Object object, String formatStr) {
		if (object == null || null == formatStr || "".equals(formatStr)) {
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat(formatStr);
		try {
			if (object instanceof Date)
				return format.format(object);
			else
				return format.parse(object.toString());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("操作失败", e);
		}

	}

	/**
	 * 将java.util.Date 格式转换为字符串格式'yyyy-MM-dd HH:mm:ss'(24小时制)<br>
	 * 
	 * @param time
	 *            Date 日期<br>
	 * @return String 字符串<br>
	 */

	public static String dateToString24(Date time) {
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ctime = formatter.format(time);

		return ctime;
	}

	/**
	 * 将java.util.Date 格式转换为字符串格式'yyyy-MM-dd HH:mm:ss a'(12小时制)<br>
	 * 
	 * @param time
	 *            Date 日期<br>
	 * @return String 字符串<br>
	 */
	public static String dateToString12(Date time) {
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("yyyy-MM-dd KK:mm:ss a");
		String ctime = formatter.format(time);

		return ctime;
	}

	/***
	 * 得到二个日期间的间隔天数
	 * 
	 * @param dateString1
	 * @param dateString2
	 * @return
	 */
	public static long getIntervalDay(String dateString1, String dateString2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		try {
			Date date = myFormatter.parse(dateString1);
			Date mydate = myFormatter.parse(dateString2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return day;
	}

	/***
	 * 得到二个日期间的间隔天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getIntervalDay(Date date1, Date date2) {

		long day = 0;
		try {
			day = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return day;
	}
	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	/***
	 * 计算当月最后一天,返回字符串
	 * 
	 * @return
	 */
	public static String getDefaultDay() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天

		str = sdf.format(lastDate.getTime());
		return str;
	}

	/***
	 * 获取上月第一天
	 * 
	 * @return
	 */
	public static String getPreviousMonthFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, -1);// 减一个月，变为下月的1号
		// lastDate.add(Calendar.DATE,-1);//减去一天，变为当月最后一天

		str = sdf.format(lastDate.getTime());
		return str;
	}

	/***
	 * 获取当月第一天
	 * 
	 * @return
	 */
	public static String getFirstDayOfMonth() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		str = sdf.format(lastDate.getTime());
		return str;
	}

	/***
	 * 获得上月最后一天的日期
	 * 
	 * @return
	 */
	public static String getPreviousMonthEnd() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, -1);// 减一个月
		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	/***
	 * 获得下个月第一天的日期
	 * 
	 * @return
	 */
	public static String getNextMonthFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, 1);// 减一个月
		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	/***
	 * 获得下个月最后一天的日期
	 * 
	 * @return
	 */
	public static String getNextMonthEnd() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.MONTH, 1);// 加一个月
		lastDate.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		lastDate.roll(Calendar.DATE, -1);// 日期回滚一天，也就是本月最后一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	/***
	 * 获得明年最后一天的日期
	 * 
	 * @return
	 */
	public static String getNextYearEnd() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.YEAR, 1);// 加一个年
		lastDate.set(Calendar.DAY_OF_YEAR, 1);
		lastDate.roll(Calendar.DAY_OF_YEAR, -1);
		str = sdf.format(lastDate.getTime());
		return str;
	}

	/***
	 * 获得明年第一天的日期
	 * 
	 * @return
	 */
	public static String getNextYearFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar lastDate = Calendar.getInstance();
		lastDate.add(Calendar.YEAR, 1);// 加一个年
		lastDate.set(Calendar.DAY_OF_YEAR, 1);
		str = sdf.format(lastDate.getTime());
		return str;

	}

	/***
	 * 获得本年有多少天
	 * 
	 * @return
	 */
	public static int getMaxYear() {
		Calendar cd = Calendar.getInstance();
		cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		return MaxYear;
	}

	private static int getYearPlus() {
		Calendar cd = Calendar.getInstance();
		int yearOfNumber = cd.get(Calendar.DAY_OF_YEAR);// 获得当天是一年中的第几天
		cd.set(Calendar.DAY_OF_YEAR, 1);// 把日期设为当年第一天
		cd.roll(Calendar.DAY_OF_YEAR, -1);// 把日期回滚一天。
		int MaxYear = cd.get(Calendar.DAY_OF_YEAR);
		if (yearOfNumber == 1) {
			return -MaxYear;
		} else {
			return 1 - yearOfNumber;
		}
	}

	/***
	 * 获得本年第一天的日期
	 * 
	 * @return
	 */
	public static String getCurrentYearFirst() {
		int yearPlus = getYearPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, yearPlus);
		Date yearDay = currentDate.getTime();
		DateFormat df = DateFormat.getDateInstance();
		String preYearDay = df.format(yearDay);
		return preYearDay;
	}

	/***
	 * 获得本年最后一天的日期
	 * 
	 * @return
	 */
	public static String getCurrentYearEnd() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		return years + "-12-31";
	}

	/***
	 * 获得上年第一天的日期
	 * 
	 * @return
	 */
	public static String getPreviousYearFirst() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);
		years_value--;
		return years_value + "-1-1";
	}

	/***
	 * 获得本季度
	 * 
	 * @param month
	 * @return
	 */
	public static String getThisSeasonTime(int month) {
		int array[][] = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 }, { 10, 11, 12 } };
		int season = 1;
		if (month >= 1 && month <= 3) {
			season = 1;
		}
		if (month >= 4 && month <= 6) {
			season = 2;
		}
		if (month >= 7 && month <= 9) {
			season = 3;
		}
		if (month >= 10 && month <= 12) {
			season = 4;
		}
		int start_month = array[season - 1][0];
		int end_month = array[season - 1][2];

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");// 可以方便地修改日期格式
		String years = dateFormat.format(date);
		int years_value = Integer.parseInt(years);

		int start_days = 1;// years+"-"+String.valueOf(start_month)+"-1";//getLastDayOfMonth(years_value,start_month);
		int end_days = getLastDayOfMonth(years_value, end_month);
		String seasonDate = years_value + "-" + start_month + "-" + start_days
				+ ";" + years_value + "-" + end_month + "-" + end_days;
		return seasonDate;

	}

	/**
	 * 获取某年某月的最后一天
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getLastDayOfMonth(int year, int month) {
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
				|| month == 10 || month == 12) {
			return 31;
		}
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		}
		if (month == 2) {
			if (isLeap(year)) {
				return 29;
			} else {
				return 28;
			}
		}
		return 0;
	}


	/***
	 * 求字符串格式的日期的下一天
	 * 
	 * @param date
	 *            字符串格式的日期
	 * @return 返回 日期下一天的日期
	 */

	public static String nextDayAfterDate(String date) {
		String[] current = date.split("-");
		// 分隔字符串格式的日期
		int year = Integer.parseInt(current[0]);
		int month = Integer.parseInt(current[1]);
		int day = Integer.parseInt(current[2]);
		// 如果日数少于该月的最大天数，天数加一天
		if (day < maxDay(year, month)) {
			day++;
		} else {
			// 如果月份少于12，则月份加一个月
			if (month < 12) {

				month++;
			} else {
				// 如果月份大于等于12，则月份置1，年份加1年
				month = 1;
				year++;
			}
			// 如果日数超过该月的天数，天数置1
			day = 1;
		}
		// 计算出处理后的日期
		date = year + "-" + formatData(month) + "-" + formatData(day);

		return date;
	}

	/**
	 * 
	 * 日期数据格式的处理
	 * 
	 * @param 整型
	 *            data
	 * @return 返回String类型的新数据
	 * @exception 异常描述
	 */
	public static String formatData(int data) {
		// 如果数字小于10，数字前加0
		if (data < 10) {
			return "0" + data;
		} else {
			return data + "";
		}

	}

	/***
	 * 判断是否是闰年,如果是闰年返回true，否则为false
	 * 
	 * @param year
	 * @return boolean
	 */
	public static boolean isLeap(int year) {
		// 如果是闰年返回true，否则为false
		if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
			return true;
		} else {
			return false;
		}
	}

	/***
	 * 求一个月的最大一天
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @return
	 */
	public static int maxDay(int year, int month) {
		// 初始化12个月份的天数
		int[] months = { 31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		// 如果不是2月则返回该月份的天数
		if (month != 2) {
			return months[month - 1];
		} else {
			// 如果是闰年返回为29天，否则28天
			if (isLeap(year)) {
				return 29;
			} else {
				return 28;
			}
		}
	}

	/***
	 * 比较两个字符串格式的日期的大小, 如果date1小于等于date2返回为true,否则为false
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean compareToDate(String date1, String date2) {
		// 如果date1小于等于date2返回为true,否则为false
		if (date1.compareTo(date2) <= 0) {
			return true;
		}
		return false;
	}

	/***
	 * 根据一个日期,返回是星期几的数字
	 * 
	 * @param date
	 * @return 1：星期日；2：星期一；3：星期二；4：星期三；5：星期四；6：星期五；7：星期六；
	 */
	public static int getWeekValue(Date date) {
		// 实例化Calendar
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		return now.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 * 
	 * @param sdate
	 * @return 1：星期日；2：星期一；3：星期二；4：星期三；5：星期四；6：星期五；7：星期六；
	 */
	public static String getWeekValueToString(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	/**
	 * 获取当前星期几
	 * 
	 * @return
	 */
	public static String getWeek() {
		SimpleDateFormat formatter;
		formatter = new SimpleDateFormat("E");
		String ctime = formatter.format(new Date());
		return ctime;
	}
	
	//获得today的一周时间
	public static Timestamp[] getWeekBeginEnd(Timestamp today) {
        Timestamp result[] = new Timestamp[2];
        
        Calendar c = Calendar.getInstance();
        
        c.setTime(today);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND , 0);
        c.set(Calendar.MILLISECOND , 0);
        c.set(Calendar.AM_PM, Calendar.AM);
        
        int weekDay = c.get(Calendar.DAY_OF_WEEK);
        switch(weekDay) {
            case Calendar.SUNDAY : break;
            case Calendar.MONDAY : c.add(Calendar.DAY_OF_MONTH, -1); break;
            case Calendar.TUESDAY : c.add(Calendar.DAY_OF_MONTH, -2); break;
            case Calendar.WEDNESDAY : c.add(Calendar.DAY_OF_MONTH, -3); break;
            case Calendar.THURSDAY : c.add(Calendar.DAY_OF_MONTH, -4); break;
            case Calendar.FRIDAY : c.add(Calendar.DAY_OF_MONTH, -5); break;
            case Calendar.SATURDAY : c.add(Calendar.DAY_OF_MONTH, -6); break;
        }
        
        //c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        result[0] = new Timestamp(c.getTime().getTime());
        
        
        c.set(Calendar.HOUR, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND , 59);
        c.set(Calendar.MILLISECOND , 999);
        c.set(Calendar.AM_PM, Calendar.AM);
        c.add(Calendar.DAY_OF_MONTH, 6);
        result[1] = new Timestamp(c.getTime().getTime());

        return result;
    }
	//获得today的一周时间
	public static Timestamp[] getWeekBeginEnd(Calendar c) {
        Timestamp result[] = new Timestamp[2];
        
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND , 0);
        c.set(Calendar.MILLISECOND , 0);
        c.set(Calendar.AM_PM, Calendar.AM);
        
        int weekDay = c.get(Calendar.DAY_OF_WEEK);
        switch(weekDay) {
            case Calendar.SUNDAY : break;
            case Calendar.MONDAY : c.add(Calendar.DAY_OF_MONTH, -1); break;
            case Calendar.TUESDAY : c.add(Calendar.DAY_OF_MONTH, -2); break;
            case Calendar.WEDNESDAY : c.add(Calendar.DAY_OF_MONTH, -3); break;
            case Calendar.THURSDAY : c.add(Calendar.DAY_OF_MONTH, -4); break;
            case Calendar.FRIDAY : c.add(Calendar.DAY_OF_MONTH, -5); break;
            case Calendar.SATURDAY : c.add(Calendar.DAY_OF_MONTH, -6); break;
        }
        
        //c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        result[0] = new Timestamp(c.getTime().getTime());
        
        
        c.set(Calendar.HOUR, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND , 59);
        c.set(Calendar.MILLISECOND , 999);
        c.set(Calendar.AM_PM, Calendar.AM);
        c.add(Calendar.DAY_OF_MONTH, 6);
        result[1] = new Timestamp(c.getTime().getTime());

        return result;
    }
	/**
	 * 按指定时间获取当月第一天和最后一天的信息
	 * @param today
	 * @return
	 */
	public static Timestamp[] getMonthBeginEnd(Timestamp today) {
        Timestamp result[] = new Timestamp[2];
        Calendar c = Calendar.getInstance();
        
        c.setTime(today);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND , 0);
        c.set(Calendar.MILLISECOND , 0);
        c.set(Calendar.AM_PM, Calendar.AM);
        c.set(Calendar.DAY_OF_MONTH, 1);
        result[0] = new Timestamp(c.getTime().getTime());
        
        c.set(Calendar.HOUR, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND , 59);
        c.set(Calendar.MILLISECOND , 999);
        c.set(Calendar.AM_PM, Calendar.AM);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        result[1] = new Timestamp(c.getTime().getTime());
        return result;
    }
	/**
	 * 按指定时间获取当月第一天和最后一天的信息
	 * @param today
	 * @return
	 */
	public static Timestamp[] getMonthBeginEnd(Calendar c) {
        Timestamp result[] = new Timestamp[2];
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND , 0);
        c.set(Calendar.MILLISECOND , 0);
        c.set(Calendar.AM_PM, Calendar.AM);
        c.set(Calendar.DAY_OF_MONTH, 1);
        result[0] = new Timestamp(c.getTime().getTime());
        
        c.set(Calendar.HOUR, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND , 59);
        c.set(Calendar.MILLISECOND , 999);
        c.set(Calendar.AM_PM, Calendar.AM);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        result[1] = new Timestamp(c.getTime().getTime());
        return result;
    }
	/**
	 * 按指定年份获取当年第一天和最后一天的信息
	 * @param today
	 * @return
	 */
	public static Timestamp[] getYearBeginEnd(Integer year) {
        Timestamp result[] = new Timestamp[2];
        Calendar c = Calendar.getInstance();
        
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,0);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND , 0);
        c.set(Calendar.MILLISECOND , 0);
        c.set(Calendar.AM_PM, Calendar.AM);
        c.set(Calendar.DAY_OF_MONTH, 1);
        result[0] = new Timestamp(c.getTime().getTime());
        
        c.set(Calendar.MONTH,11);
        c.set(Calendar.HOUR, 23);
        c.set(Calendar.MINUTE, 59);
        c.set(Calendar.SECOND , 59);
        c.set(Calendar.MILLISECOND , 999);
        c.set(Calendar.AM_PM, Calendar.AM);
        c.add(Calendar.MONTH, 1);
        c.add(Calendar.DAY_OF_MONTH, -1);
        result[1] = new Timestamp(c.getTime().getTime());
        return result;
    }
	
	/**
	 * 获得 later 年后的时间
	 * @param later
	 * @param dateFormatType
	 * @return
	 */
	public static String yearsLater(int later,DateFormatType dateFormatType ){
		Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR)+later);
		return  DateUtil.formatDateToStr(calendar.getTime(), dateFormatType);
	}
	
	public static String getStartTime(){  
        Calendar todayStart = Calendar.getInstance();  
        todayStart.set(Calendar.HOUR_OF_DAY, 0);  
        todayStart.set(Calendar.MINUTE, 0);  
        todayStart.set(Calendar.SECOND, 0);  
        todayStart.set(Calendar.MILLISECOND, 0);  
        return DateUtil.formatDateToStr(todayStart.getTime(), DateFormatType.DATE_FORMAT_STR1);  
    }  
	
	public static Date getStartTimeOfDate(){  
        Calendar todayStart = Calendar.getInstance();  
        todayStart.set(Calendar.HOUR_OF_DAY, 0);  
        todayStart.set(Calendar.MINUTE, 0);  
        todayStart.set(Calendar.SECOND, 0);  
        todayStart.set(Calendar.MILLISECOND, 0);  
        return todayStart.getTime(); 
    } 
      
	public static String getEndTime(){  
        Calendar todayEnd = Calendar.getInstance();  
        todayEnd.set(Calendar.HOUR, 23);  
        todayEnd.set(Calendar.MINUTE, 59);  
        todayEnd.set(Calendar.SECOND, 59);  
        todayEnd.set(Calendar.MILLISECOND, 999);  
        return DateUtil.formatDateToStr(todayEnd.getTime(), DateFormatType.DATE_FORMAT_STR1);  
    }  

	public static int getMonthSpace(String date1, String date2) throws ParseException {

		int result = 0;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();

		c1.setTime(sdf.parse(date1));
		c2.setTime(sdf.parse(date2));
		int year = c2.get(Calendar.YEAR)-c1.get(Calendar.YEAR);
		result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
		result = year*12+result;
		return result == 0 ? 1 : Math.abs(result);

	}

	public static Timestamp getCurrentTime(){
		return new Timestamp(new Date().getTime());
	}
	
	public static String getAmPmTime(){
		String time="上午";
		SimpleDateFormat df = new SimpleDateFormat(" hh:mm分 "); 
		Calendar now = Calendar.getInstance();
		if(now.get(Calendar.AM_PM)==1){
			time="下午";
		}
		time+=df.format(now.getTime());
		return time;
	}
	
	public static Date getWeekAgo(){
		return getDayAgo(7);
	}
	
	@SuppressWarnings("deprecation")
	public static Date getDayAgo(Integer day){
		Calendar c = Calendar.getInstance();  
        Date endDate=getCurrentDate();
        endDate.setHours(0);
		endDate.setMinutes(0);
		endDate.setSeconds(0);
		c.setTime(endDate);
		c.add(Calendar.DAY_OF_MONTH, - (day-1));
        Date monday = c.getTime();
        return monday;
	}
	
	@SuppressWarnings("deprecation")
	public static Date getDayEnd(){
		Date endDate=getCurrentDate();
        endDate.setHours(23);
		endDate.setMinutes(59);
		endDate.setSeconds(59);
		return endDate;
	}
	
	public static boolean  getMinutesAgo(Date time,Integer minu){
		boolean istrue=false;
		Long t1=new Date().getTime()-(minu*60*1000);
		if(time.getTime()<t1){
			istrue=true;
		}
		return istrue;
	}
	
	public static Long getMinuAgo(Date time,Integer minu){
		Long t1=time.getTime()-(minu*60*1000);
		return t1;
	}
	public static Long getMinuLater(Date time,Integer minu){
		Long t1=time.getTime()+(minu*60*1000);
		return t1;
	}
	
	@SuppressWarnings("deprecation")
	public static Date getDayAgo(Date time,Integer day){
		Calendar c = Calendar.getInstance();  
        Date endDate=new Date(time.getTime());
        endDate.setHours(0);
		endDate.setMinutes(0);
		endDate.setSeconds(0);
		c.setTime(endDate);
		c.add(Calendar.DAY_OF_MONTH, - (day-1));
        Date monday = c.getTime();
        return monday;
	}
	
	public static String formatDaysOfToday(Date date) {

		Calendar aCalendar = Calendar.getInstance();
        Calendar bCalendar = Calendar.getInstance();
        aCalendar.setTime(date);
        bCalendar.setTime(new Date());
        if(bCalendar.before(aCalendar)){
        	Calendar temp=bCalendar;
        	bCalendar=aCalendar;
        	aCalendar=temp;
        }
        int days = 0;
        while(aCalendar.before(bCalendar)){
        	if(!(aCalendar.get(Calendar.YEAR) == bCalendar.get(Calendar.YEAR)  
		            && aCalendar.get(Calendar.MONTH) == bCalendar.get(Calendar.MONTH)  
		            && aCalendar.get(Calendar.DAY_OF_MONTH) == bCalendar  
		                    .get(Calendar.DAY_OF_MONTH))){
        		 days++;
        	}	           
            aCalendar.add(Calendar.DAY_OF_YEAR, 1);
        }

		String res = "";
		if (0 == days) {
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			res = sdf.format(date);
		} else if (1 == days) {
			res = "昨天";
		} else {
			res = days + "天前";
		}

		return res;
	}
}


