public class pointsettest {
    public static void main( String args[] ) {
        Point point1 = new Point( 1, 2 );
        Point point2 = new Point( 1 + 3, 2 + 4 );
        Point myPoints[] = { point1, point2 };
        PointSet myPointSet = new PointSet( myPoints );
        double distance = myPointSet.distanceBetweenPoints( 0, 1 );
        System.out.printf( "The distance is %f\n", distance );

        myPointSet.testGetClosestNonVisited();


        // Test the closest neighbor tour.
        Point p1 = new Point( 1, 2 );
        Point p2 = new Point( 4, 6 );
        Point p3 = new Point( 1, 4 );
        Point p4 = new Point( 4, 2 );
        Point[] fourPoints = { p1, p2, p3, p4 };
        PointSet fourPointSet = new PointSet( fourPoints );
        int[] closestNeighborTour = fourPointSet.getClosestNeighborTour();
        for ( int x = 0; x < closestNeighborTour.length; x++ ) {
            System.out.printf( "The point at position %d is %s\n", x, fourPointSet.getPoint(closestNeighborTour[x]).getKey() );
        }
        closestNeighborTour = fourPointSet.getClosestNeighborTour( 3 );
        System.out.printf( "Starting at position 3, the tour is:\n" );
        for ( int x = 0; x < closestNeighborTour.length; x++ ) {
            System.out.printf( "%s\n", fourPointSet.getPoint(closestNeighborTour[x]).getKey() );
        }
        
        // Test the getTourDistance function.
        double tourDistance = fourPointSet.getTourDistance( closestNeighborTour );
        System.out.println( "The distance for the closest neighbor tour is:" );
        System.out.printf( "%f\n", tourDistance );

        // Test the getBestTour function.
        p1 = new Point( 1, -10 );
        p2 = new Point( 1, 5 );
        p3 = new Point( 1, 9 );
        p4 = new Point( 1, 13 );
        Point p5 = new Point( 1, 25 );
        Point pointsInLine[] = { p1, p2, p3, p4, p5 };
        PointSet pointsInLineSet = new PointSet( pointsInLine );
        int optimumTour[] = pointsInLineSet.getBestTour( 2 );
        System.out.println( "The optimum tour starting from the third point in a line is:" );
        for ( int i = 0; i < optimumTour.length; i++ ) {
            System.out.printf( "%d\n", optimumTour[i] );
        }

        // Test the getStatistics function.
        Point myPointsInLine[] = { p3, p2, p4, p1, p5 };
        PointSet getStatisticsFromMe = new PointSet( myPointsInLine );
        Statistic testStatistics[] = getStatisticsFromMe.getStatistics();
        System.out.println( "The statistics are:" );
        for ( int i = 0; i < testStatistics.length; i++ ) {
            System.out.printf( "%s : %f", testStatistics[i].name, testStatistics[i].value );
        }


        // Test the makeDistanceDistribution function.
        System.out.printf( "<br />\nAbout to test the makeDistanceDistribution function:<br />\n" );
        Point p11 = new Point( 1, 1 );
        Point p12 = new Point( 1, 2 );
        Point p13 = new Point( 3, 1 );
        Point p14 = new Point( 4, 5 );
        Point distributedPoints[] = { p11, p12, p13, p14 };
        PointSet distributedPointSet = new PointSet( distributedPoints );
        BinaryNode distanceDistribution = distributedPointSet.makeDistanceDistribution( 0 );
        System.out.printf( "%s<br />\n", distanceDistribution.printTreeAsHTML() );
        System.out.printf( "The binary tree has a maximum value of: %f<br />\n", distanceDistribution.maxValue );

        // Test the getDistanceDistributions function.
        System.out.printf( "<br />\nAbout to test the getDistanceDistributions function:<br />\n" );
        BinaryNode[] distanceDistributions = distributedPointSet.getDistanceDistributions();
        for ( int i = 0; i < distanceDistributions.length; i++ ) {
            System.out.printf( "For point i = %d, the distance distribution looks like: %s<br />\n", i, distanceDistributions[i].printTreeAsHTML() );
        }


        // Test the getPsuedoClosestNeighborTour function.
        System.out.printf( "<br />\nThe Psuedo-closest neighbor tour looks like:<br />\n" );
        int tour[] = distributedPointSet.getPsuedoClosestNeighborTour( 0 );
        for ( int i = 0; i < tour.length; i++ ) {
            System.out.printf( "%d, ", tour[i] );
        }
    }
}