package com.petStore.utils;

import java.io.*;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;



import com.petStore.dto.UsersDto;



public class Util {
    private static final Logger LOGGER = Logger.getLogger(Util.class);
    
    

    public static String getType(int type) {
        switch (type) {
            case java.sql.Types.CHAR:
            case java.sql.Types.LONGVARCHAR:
            case java.sql.Types.VARCHAR:
                return "string";

            case java.sql.Types.BIGINT:
            case java.sql.Types.BINARY:
            case java.sql.Types.DECIMAL:
            case java.sql.Types.DOUBLE:
            case java.sql.Types.FLOAT:
            case java.sql.Types.INTEGER:
            case java.sql.Types.LONGVARBINARY:
            case java.sql.Types.NUMERIC:
            case java.sql.Types.VARBINARY:
            case java.sql.Types.TINYINT:
            case java.sql.Types.SMALLINT:
            case java.sql.Types.REAL:
                return "numberic";

            case java.sql.Types.BIT:
                return "boolean";

            case java.sql.Types.DATE:
            case java.sql.Types.TIME:
            case java.sql.Types.TIMESTAMP:
                return "date";

            case java.sql.Types.ARRAY:
            case java.sql.Types.BLOB:
            case java.sql.Types.CLOB:
            case java.sql.Types.JAVA_OBJECT:
            case java.sql.Types.NULL:
            case java.sql.Types.OTHER:
            case java.sql.Types.REF:
            case java.sql.Types.STRUCT:
            case java.sql.Types.DISTINCT:
            default:
                return "other";
        }
    }

   

    

    public static String formatObjectsToString(String object ,String type){
    	if(object != null && !"".equals(object)){
        	if("money".equals(type)){
        		return String.format("$%s", Double.valueOf(object));
        	}else if("percentage".equals(type)){
        		DecimalFormat df = new DecimalFormat("0.00");
        		return String.format("%s", df.format(Double.valueOf(object))) + " %";
        	}else {
        		return object;
        	}
    	}
    	return "";
    }
    
    
    public static java.sql.Date convertToDate(String d) throws Exception {
        if (d == null)
            throw new Exception("Must supply a Start and End Date.  At least one not found.");
        try {
            long ms = Long.parseLong(d);
            java.sql.Date dt = new java.sql.Date(ms);
            return dt;
        } catch (Exception e) {
            throw new Exception("Invalid date value was sent for the date range.");
        }
    }

    public static String stringLimit(String s, int l) {
        int sl = s.length();
        if (sl < l) {
            for (int i = sl; i < l; i++) {
                s += " ";
            }
        } else if (sl > l) {
            s = s.substring(0, l);
        }
        return s;
    }

    
    
   

    public static void writeToFile(String filename, String output) throws IOException {
        try {
            // Create file
            FileWriter fstream = new FileWriter(filename);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(output);
            //Close the output stream
            out.close();
        } catch (IOException e) {
            LOGGER.error("error", e);
            throw e;
        }
    }

    public static java.sql.Date conversionToSQLDate(Date date) {
        if (date != null) {
            return new java.sql.Date(date.getTime());
        }
        return null;
    }

    
    public static String convertingDateToFormat(Date date, String format) {
        if (format == null) {
            format = "yyyy-MM-dd";
        }
        SimpleDateFormat sdfDestination = new SimpleDateFormat(format);
        return sdfDestination.format(date);
    }
    
    public static Date nextDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        Date tomorrow = calendar.getTime();        
        return tomorrow;
    }
    
    
    public static String getHexString(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String readTextFromStream(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String res = br.readLine();
        String line;
        while ((line = br.readLine()) != null) {
            res += line;
        }
        br.close();

        return res;
    }
    
    
    
   
    public static String  getNumbers(String numberData) {
        String digits = numberData.replaceAll("[^0-9.]", "");
        return digits;
    }
    
    public static String getStackTrace(Throwable aThrowable) {
        final Writer result = new StringWriter();
        final PrintWriter printWriter = new PrintWriter(result);
        aThrowable.printStackTrace(printWriter);
        return result.toString();
    }
    
    public static String getCheckSum(String value) {    	
		int b = 0;
		for (char c: value.toCharArray()) {
			b += (int)c;
		}
		b %= 256;		
		LOGGER.info(Integer.toHexString(b));
		return Integer.toHexString(b); 
    }
    
    public static UsersDto getCurrentUserDetails(HttpSession session) {		
		return (UsersDto) session.getAttribute("usersDetails");
	}
	
	public static void setCurrentUserDetails(HttpSession session, UsersDto usuarioDetails){
		session.setAttribute("usersDetails", usuarioDetails);
	}	
	
	
	
	 
	 
	 public static Date convertStringToDate(String dateValue) throws ParseException {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 return sdf.parse(dateValue);
	 }
	 
	 public static String convertDateToSQLString(Date dateValue) {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 return sdf.format(dateValue);
	 }
	 
}