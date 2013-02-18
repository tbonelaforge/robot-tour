public class PathSet {
    private Path[] _paths;
    private PointSet _pointSet;
    private int _firstClosestPathIndex;
    private int _secondClosestPathIndex;
    private PathConnection _closestPathConnection;

    public PathSet( PointSet initialPointSet ) {
        _pointSet = initialPointSet;
        _getInitialPaths();
        _closestPathConnection = null;
    }

    public PathSet( Path initialPaths[], PointSet initialPointSet ) {
        _paths = initialPaths;
        _pointSet = initialPointSet;
        _closestPathConnection = null;
    }

    public PathSet joinClosestPair() {
        Path newPaths[] = new Path[_paths.length - 1];
        int k = 0;
        Path joinedPath = _getJoinedPath();

        for ( int i = 0; i < _paths.length; i++ ) {
            if ( i != _firstClosestPathIndex &&
                 i != _secondClosestPathIndex ) {
                newPaths[k++] = _paths[i];
            }
        }
        newPaths[k] = _getJoinedPath();
        return new PathSet( newPaths, _pointSet );
    }

    public Path[] getPaths() {
        return _paths;
    }

    private Path[] _getInitialPaths() {
        Point thesePoints[] = _pointSet.getPoints();
        Path initialPaths[] = new Path[thesePoints.length];
        for ( int i = 0; i < thesePoints.length; i++ ) {
            int singlePointArray[] = { i, i };
            initialPaths[i] = new Path( singlePointArray );
        }
        _paths = initialPaths;
        return initialPaths;
    }


    private Path _getJoinedPath() {
        Path firstPath = _getClosestPathConnection().firstPath;
        Path secondPath = _getClosestPathConnection().secondPath;
        if ( _closestPathConnection.firstPathReversed ) {
            firstPath = firstPath.reverse();
        }
        if ( _closestPathConnection.secondPathReversed ) {
            secondPath = secondPath.reverse();
        }
        return Path.join( new Path[]{ firstPath, secondPath } );
    }

    private PathConnection _getClosestPathConnection() {
        PathConnection thisPathConnection;
        double thisDistance;

        if ( _closestPathConnection != null ) {
            return _closestPathConnection;
        }
        for ( int i = 0; i < _paths.length - 1; i++ ) {
            for ( int j = i + 1; j < _paths.length; j++ ) {
                thisPathConnection = new PathConnection( _paths[i], _paths[j] );
                thisDistance = thisPathConnection.getMinDistance(_pointSet);
                if ( _closestPathConnection == null ||
                     thisDistance < _closestPathConnection.minDistance ) {
                    _closestPathConnection = thisPathConnection;
                    _firstClosestPathIndex = i;
                    _secondClosestPathIndex = j;
                }
            }
        }
        return _closestPathConnection;
    }
}