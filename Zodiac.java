package org.itstep;

import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Zodiac {
    private static int count = 13;
    private static String[] signs = {"Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricorn", "Capricorn", "Aquarius", "Pisces"};
    private static String[][] dates = {{"21.03", "20.04"}, {"21.04", "21.05"}, {"22.05", "21.06"},
            {"22.06", "22.07"}, {"23.07", "22.08"}, {"23.08", "23.09"},
            {"24.09", "23.10"}, {"24.10", "22.11"}, {"23.11", "21.12"},
            {"1.01", "20.01"}, {"22.12", "31.12"}, {"21.01", "19.02"}, {"20.02", "20.03"}};

    public static void main(String[] args) throws ParseException {
        ZodiacSign[] zodiacSigns = new ZodiacSign[count];
        for (int i = 0; i < count; i++)
            zodiacSigns[i] = new ZodiacSign(signs[i], dates[i][0], dates[i][1]);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the date of your birth in format dd.MM.yyyy: ");
        String date = scanner.next();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date dateOfBirth = dateFormat.parse(date);

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dateOfBirth);
        //System.out.printf("Month: %s, day: %s%n",calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));

        if (!(calendar.get(Calendar.MONTH) == 11 && (calendar.get(Calendar.DAY_OF_MONTH) == 31)))
            calendar.add(Calendar.SECOND, 1); //Add one second
        else calendar.add(Calendar.SECOND, -1);

        int sign = -1;
        for (int i = 0; i < count; i++) {
            Calendar calendarBegin = new GregorianCalendar();
            Calendar calendarEnd = new GregorianCalendar();
            calendarBegin.setTime(zodiacSigns[i].getDateBegin());
            calendarBegin.set(Calendar.YEAR, calendar.get(calendar.YEAR));
            calendarEnd.setTime(zodiacSigns[i].getDateEnd());
            calendarEnd.set(calendar.YEAR, calendar.get(Calendar.YEAR));

            /*if (calendar.getTime.after(calendarBegin.getTime()) &&
                    calendar.getTime().before(calendarEnd.getTime())) ;  */
            sign = i;
        }
        System.out.println(signs[sign]);

        Locale localeRU = new Locale("ru", "RU");
        Locale localeUS = new Locale("en", "US");

        ResourceBundle bundleRU = ResourceBundle.getBundle("com/company/data/Signs", localeRU);
        String signRU = bundleRU.getString(signs[sign]);
        ResourceBundle bundleUS = ResourceBundle.getBundle("com/company/data/Signs", localeUS);
        String signUS = bundleUS.getString(signs[sign]);
        System.out.println(signRU);
        System.out.println(signUS);
    }
}


     class ZodiacSign {
        private String name;
        private String sDateBegin;
        private String sDateEnd;
        private Date dateBegin;
        private Date dateEnd;

        public ZodiacSign(String name, String sDateBegin, String sDateEnd) throws ParseException {
            this.name = name;
            this.sDateBegin = sDateBegin;
            this.sDateEnd = sDateEnd;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM");
            dateBegin = dateFormat.parse(sDateBegin);
            dateEnd = dateFormat.parse(sDateEnd);
        }

        public Date getDateBegin() {
            return dateBegin;
        }

        public Date getDateEnd() {
            return dateEnd;
        }
    }
