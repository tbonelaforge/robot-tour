public class pointsettestertest {
    public static void main( String args[] ) {

        // Test the accumulateStatistics function.
        PointSetTester bigPointSetTester = new PointSetTester(
            500,
            5,
            1,
            9,
            1,
            9
        );
        Statistic accumulatedStatistics[] = bigPointSetTester.accumulateStatistics();
        System.out.println( "The accumulated statistics are:" );
        for ( int i = 0; i < accumulatedStatistics.length; i++ ) {
            System.out.printf( "%s, %f\n", accumulatedStatistics[i].name, accumulatedStatistics[i].value );
        }

    }
}