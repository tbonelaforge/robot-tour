public class PathConnection {
    public Path firstPath;
    public Path secondPath;
    public boolean firstPathReversed;
    public boolean secondPathReversed;
    public double minDistance;

    public PathConnection( Path path1, Path path2 ) {
        firstPath = path1;
        secondPath = path2;
        minDistance = -1.0;
    }

    public double getMinDistance( PointSet pointSet ) {
        double thisDistance;

        minDistance = -1.0;
        for ( int i = 0; i <= 1; i++ ) {
            for ( int j = 0; j <= 1; j++ ) {
                thisDistance = pointSet.distanceBetweenPoints( _getFirstEndpoint( i ), _getSecondEndpoint( j ) );
                if ( thisDistance < minDistance || minDistance < 0.0 ) {
                    minDistance = thisDistance;
                    _recordPathOrientation( i, j );
                }
            }
        }
        return minDistance;
    }

    private int _getFirstEndpoint( int i ) {
        return firstPath.indices[i * (firstPath.indices.length - 1)];
    }

    private int _getSecondEndpoint( int j ) {
        return secondPath.indices[j * (secondPath.indices.length - 1)];
    }

    private void _recordPathOrientation( int i, int j ) {
        firstPathReversed = ( i == 0 ) ? true : false;
        secondPathReversed = ( j == 0 ) ? false : true;
    }
}