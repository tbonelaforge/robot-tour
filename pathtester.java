public class pathtester {
    public static void main( String args[] ) {
        
        // Test the easy case..
        int indices1[] = { 1, 3 };
        int indices2[] = { 2, 4 };
        Path pathsToBeJoined[] = { new Path( indices1 ), new Path( indices2 ) };
        //        Path joinedPath = Path.join( path1, path2 );
        Path joinedPath = Path.join( pathsToBeJoined );
        System.out.println( "The joined path looks like:" );
        for ( int i = 0; i < joinedPath.indices.length; i++ ) {
            System.out.printf( "%d, ", joinedPath.indices[i] );
        }

        // Test the cases where one of the paths represents a single point,
        int singlePoint[] = { 1, 1 };
        Path pathsToBeJoined2[] = { new Path( singlePoint ), new Path( indices2 ) };
        Path joinedPath2 = Path.join( pathsToBeJoined2 );
        System.out.println( "The second joined path looks like:" );
        for ( int i = 0; i < joinedPath2.indices.length; i++ ) {
            System.out.printf( "%d, ", joinedPath2.indices[i] );
        }

        Path pathsToBeJoined3[] = { new Path( indices2 ), new Path( singlePoint ) };
        Path joinedPath3 = Path.join( pathsToBeJoined3 );
        System.out.println( "The third joined path looks like:" );
        for ( int i = 0; i < joinedPath3.indices.length; i++ ) {
            System.out.printf( "%d, ", joinedPath3.indices[i] );
        }

        // Test the case where both paths represent a single point.
        int singlePoint2[] = { 2, 2 };
        Path pathsToBeJoined4[] = { new Path( singlePoint ), new Path( singlePoint2 ) };
        Path joinedPath4 = Path.join( pathsToBeJoined4 );
        System.out.println( "The fourth joined path looks like:" );
        for ( int i = 0; i < joinedPath4.indices.length; i++ ) {
            System.out.printf( "%d, ", joinedPath4.indices[i] );
        }

    }
}