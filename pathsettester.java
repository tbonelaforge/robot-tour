public class pathsettester {
    public static void main( String args[] ) {
        
        // Construct some points to work on.
        Point p0 = new Point( 5, 1 );
        Point p1 = new Point( 6, 1 );
        Point p2 = new Point( 3, 1 );
        Point p3 = new Point( 11, 1 );

        Point pointArray[] = { p0, p1, p2, p3 };
        PointSet pointSet = new PointSet( pointArray );
        PathSet pathSet = new PathSet( pointSet );
        System.out.println( "Before joining the closest path, the paths are:" );
        for ( int i = 0; i < pathSet.getPaths().length; i++ ) {
            System.out.printf( "(" );
            for ( int j = 0; j < pathSet.getPaths()[i].indices.length; j++ ) {
                System.out.printf( "%d, ", pathSet.getPaths()[i].indices[j] );
            }
            System.out.printf( ")\n" );
        }
        PathSet newPathSet = pathSet.joinClosestPair();
        Path newPaths[] = newPathSet.getPaths();
        System.out.println( "After joining the closest path, the resulting paths are:" );
        for ( int i = 0; i < newPaths.length; i++ ) {
            Path thisPath = newPaths[i];
            System.out.printf( "(" );
            for ( int j = 0; j < thisPath.indices.length; j++ ) {
                System.out.printf( "%d, ", thisPath.indices[j] );
            }
            System.out.printf( " )\n" );
        }

        // Do it again...
        newPathSet = newPathSet.joinClosestPair();
        newPaths = newPathSet.getPaths();
        System.out.println( "After joining the closest path, the resulting paths are:" );
        for ( int i = 0; i < newPaths.length; i++ ) {
            Path thisPath = newPaths[i];
            System.out.printf( "(" );
            for ( int j = 0; j < thisPath.indices.length; j++ ) {
                System.out.printf( "%d, ", thisPath.indices[j] );
            }
            System.out.printf( " )\n" );
        }


        // Do it again...
        newPathSet = newPathSet.joinClosestPair();
        newPaths = newPathSet.getPaths();
        System.out.println( "After joining the closest path, the resulting paths are:" );
        for ( int i = 0; i < newPaths.length; i++ ) {
            Path thisPath = newPaths[i];
            System.out.printf( "(" );
            for ( int j = 0; j < thisPath.indices.length; j++ ) {
                System.out.printf( "%d, ", thisPath.indices[j] );
            }
            System.out.printf( " )\n" );
        }
        

    }
}