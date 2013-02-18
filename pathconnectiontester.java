public class pathconnectiontester {
    public static void main( String args[] ) {
        Point a = new Point( 3, 0 );
        Point b = new Point( 0, 3 );
        Point c = new Point( 3, 1 );
        Point d = new Point( 0, 4 );
        Point e = new Point( 3, 3 );
        Point f = new Point( 0, 5 );
        Point g = new Point( 0, 6 );

        Point points[] = { a, b, c, d, e, f, g };

        PointSet testPointSet = new PointSet( points );

        int path1Indices[] = { 0, 2, 4 };
        int path2Indices[] = { 1, 3, 5, 6 };
        
        Path path1 = new Path( path1Indices );
        Path path2 = new Path( path2Indices );

        PathConnection testPathConnection = new PathConnection( path1, path2 );

        double gotDistance = testPathConnection.getMinDistance( testPointSet );
        System.out.printf( "The calculated least distance connection between the paths is: %f\n", gotDistance );
        System.out.printf( "The first path is %s\n", ( testPathConnection.firstPathReversed ) ? "reversed" : "not reversed" );
        System.out.printf( "The second path is %s\n", ( testPathConnection.secondPathReversed ) ? "reversed" : "not reversed" );
    }
}