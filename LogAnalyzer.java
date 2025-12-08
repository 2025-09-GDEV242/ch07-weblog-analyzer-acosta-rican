/** CHAPTER 7: WEB LOG ANALYZER PROJECT
 * edited by Franco Acosta
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader("demo.log");
    }
    
    /**
     * 7.12 EXERCISE:
     * Create an object to analyze hourly web accesses using a user-given log file.
     */
    public LogAnalyzer(String filename)
    {
        hourCounts = new int[24];
        reader = new LogfileReader(filename);
    }
    
    /**
     * 7.14 EXERCISE:
     * Return the number of accesses recorded in the log file.
     */
    public int numberOfAccesses()
    {
        int total = 0;
        // Add the value from the elements of hourCounts to total.
        for(int count : hourCounts) {
            total += count;
        }
        return total;
    }

    
    /**
     * EXERCISE 7.15:
     * Return the hour with the most accesses.
     */
    public int busiestHour()
    {
        int busiestHr = 0;  // index busiestHr hour
        int maxCount = 0; // highest count

        for(int hour = 0; hour < hourCounts.length; hour++) {
            if(hourCounts[hour] > maxCount) {
                maxCount = hourCounts[hour];
                busiestHr = hour;
            }
        }

        return busiestHr;
    }
    
    
    /**
     * EXERCISE 7.16:
     * Return the hour with the least accesses.
     */
    public int quietestHour()
    {
        int quietestHr = 0;
        int minCount = hourCounts[0];  // Need to give an original value

        for (int hour = 1; hour < hourCounts.length; hour++) {
            if (hourCounts[hour] < minCount) {
                minCount = hourCounts[hour];
                quietestHr = hour;
            }
        }

        return quietestHr;
    }

    /**
     * EXERCISE 7.18:
     * Return the first hour from the busiest two-hour period.
     */
    public int busiestTwoHours()
    {
        int busiestStartHour = 0;
        int maxTotal = hourCounts[0] + hourCounts[1];

        for (int hour = 1; hour < hourCounts.length - 1; hour++) {
            int twoHourTotal = hourCounts[hour] + hourCounts[hour + 1];
            //first iterates every pair up to hr 22 as 1st hr
            if (twoHourTotal > maxTotal) { //changes max value if higher pair appears
                maxTotal = twoHourTotal;
                busiestStartHour = hour;
            }
        }

        return busiestStartHour;
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}