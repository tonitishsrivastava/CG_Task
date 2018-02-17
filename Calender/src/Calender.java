import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;  
import java.util.Date;

public class Calender {
	
 public static void main(String[] args) { 
	 System.out.println("First day of the month: " + getFirstDate("2014-2-9"));
	 System.out.println("Last day of the month: " + getLastDate("2015-1-1"));

 }  
 
 public static String getFirstDate(String date){
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 Calendar cal = Calendar.getInstance();
     Date date1;
	try {
		date1 = sdf.parse(date);
		cal.setTime(date1);
	} catch (ParseException e) {
		System.out.println("Please enter valid date");
	}
	 
     cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
	 return sdf.format(cal.getTime());
 }
 
 public static String getLastDate(String date){
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 Calendar cal = Calendar.getInstance();
     Date date1;
	try {
		date1 = sdf.parse(date);
		 cal.setTime(date1);
	} catch (ParseException e) {
		System.out.println("Please enter valid date");
	}
     cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
     Date today = new Date(); 
     Date lastDate = cal.getTime();
     if(today.after(lastDate)) {
    	 return sdf.format(lastDate);
    	}
	 return sdf.format(today);
 }
 
}
