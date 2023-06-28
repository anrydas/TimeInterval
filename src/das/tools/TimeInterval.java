package das.tools;

import java.util.concurrent.TimeUnit;

public class TimeInterval {
    public static void main(String[] args) throws InterruptedException {
        final String LBL_TOTAL = "Total, s";
        final String LBL_FIRST = "First, μs";
        final String LBL_SECOND = "Second, ms";
        final String LBL_THIRD = "Third, ns";
        final String LBL_FOURS = "Fours, s";

        DasTimeInterval intervals = new DasTimeInterval();
        intervals.startInterval(LBL_TOTAL);
        TimeUnit.MILLISECONDS.sleep(2450);
        intervals.startInterval(LBL_FIRST);
        TimeUnit.MILLISECONDS.sleep(2230);
        intervals.stopInterval(LBL_FIRST, DasTimeInterval.getDurationMicroSeconds());
        intervals.startInterval(LBL_SECOND);
        TimeUnit.SECONDS.sleep(2);
        intervals.stopInterval(LBL_SECOND, DasTimeInterval.getDurationMilliSeconds());
        intervals.startInterval(LBL_THIRD);
        TimeUnit.SECONDS.sleep(2);
        intervals.stopInterval(LBL_THIRD, DasTimeInterval.getDurationNanoSeconds());
        intervals.startInterval(LBL_FOURS);
        TimeUnit.SECONDS.sleep(2);
        intervals.stopInterval(LBL_FOURS, DasTimeInterval.getDurationSeconds());
        // To test Warning message
        intervals.stopInterval(LBL_SECOND, DasTimeInterval.getDurationMilliSeconds());
        // To test Error message
        intervals.stopInterval("Wrong Label", DasTimeInterval.getDurationMilliSeconds());

        intervals.stopInterval(LBL_TOTAL, DasTimeInterval.getDurationMilliSeconds());
        System.out.println("Time Intervals:\n" + intervals);
    }
}