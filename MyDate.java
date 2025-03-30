import java.util.GregorianCalendar;

public class MyDate {
    private int year;
    private int month;
    private int day;

    public static void main(String[] args) {
        // Create a MyDate object using the no-arg constructor
        MyDate date1 = new MyDate();
        System.out.println("Current date: " + date1.getYear() + "-" + (date1.getMonth() + 1) + "-" + date1.getDay());

        // Create a MyDate object using the specified elapsed time
        MyDate date2 = new MyDate(34355555133101L);
        System.out.println("Specified date: " + date2.getYear() + "-" + (date2.getMonth() + 1) + "-" + date2.getDay());
    }

    // No-arg constructor that creates a MyDate object for the current date
    public MyDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        this.year = calendar.get(GregorianCalendar.YEAR);
        this.month = calendar.get(GregorianCalendar.MONTH);
        this.day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
    }

    // A constructor that constructs a MyDate object with a specified elapsed time since midnight, January 1, 1970, in milliseconds.
    public MyDate(long elapsedTime) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(elapsedTime);
        this.year = calendar.get(GregorianCalendar.YEAR);
        this.month = calendar.get(GregorianCalendar.MONTH);
        this.day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
    }

    // A constructor that constructs a MyDate object with the specified year, month, and day.
    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    // Three getter methods for the data fields year, month, and day, respectively.
    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    // A method named setDate(long elapsedTime) that sets a new date for the object using the elapsed time.
    public void setDate(long elapsedTime) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(elapsedTime);
        this.year = calendar.get(GregorianCalendar.YEAR);
        this.month = calendar.get(GregorianCalendar.MONTH);
        this.day = calendar.get(GregorianCalendar.DAY_OF_MONTH);
    }


}
    

