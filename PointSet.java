public class PointSet {
    private Point[] _points;
    private double[][] _distance;
    private BinaryNode[] _distanceDistributions;
    private boolean[] _visited;
    private boolean _bestTourFound;
    private int[] _bestTour;
    private double _bestDistance;
    private int[] _worstTour;
    private double _worstDistance;


    // Public methods.
    public Point getPoint( int index ) {
        return _points[index];
    }

    public Point[] getPoints() {
        return _points;
    }

    public PointSet( Point[] startPoints ) {
        int numPoints = startPoints.length;
        _points = new Point[numPoints];
        _distance = new double[numPoints][numPoints];
        _distanceDistributions = null;
        _visited = new boolean[numPoints]; 
        _bestTourFound = false;
        _bestDistance = -1.0;
        _worstDistance = -1.0;
        for ( int i = 0; i < numPoints; i++ ) {
            _points[i] = startPoints[i]; // Copy given points into the object.
            _visited[i] = false;
            for ( int j = 0; j < numPoints; j++ ) {
                _distance[i][j] = -1.0; // Initialize to an impossible distance.
            }
        }
    }

    public double distanceBetweenPoints( int i, int j ) {
        if ( _distance[i][j] < 0 ) {
            Point point1 = _points[i];
            Point point2 = _points[j];
            int deltaX = point2.x - point1.x;
            int deltaY = point2.y - point1.y;
            _distance[i][j] = Math.sqrt( Math.pow( deltaX, 2 ) + Math.pow( deltaY, 2 ) );
            _distance[j][i] = _distance[i][j]; // Distance function is symmetric.
        }
        return _distance[i][j];
    }

    public BinaryNode[] getDistanceDistributions() {
        if ( _distanceDistributions != null ) {
            return _distanceDistributions;
        }
        _distanceDistributions = new BinaryNode[_points.length];
        for ( int i = 0; i < _points.length; i++ ) {
            _distanceDistributions[i] = makeDistanceDistribution( i );
        }
        return _distanceDistributions;
    }


    public BinaryNode makeDistanceDistribution( int i ) {
        BinaryNode newBinaryTree = new BinaryNode();
        double total = 0;
        for ( int d = 1; d < _points.length; d++ ) {
            int otherIndex = ( i + d ) % _points.length;
            double distance = distanceBetweenPoints( i, otherIndex );
            //            total += 1.0 / Math.pow( distance/50.0 + 1.0, 6 ) + 1.0;
            total += Math.pow( 3.0, -distance );
            newBinaryTree.insert( total, otherIndex );

        }
        return newBinaryTree;
    }


    public int[] getClosestNeighborTour() {
        return getClosestNeighborTour(0);
    }

    public int[] getClosestNeighborTour( int startIndex ) {
        int[] tour = new int[_points.length];
        int previous = startIndex;
        resetVisitedArray();
        for ( int i = 0; i < _points.length; i++ ) {
            tour[i] = previous;
            _visited[previous] = true;
            previous = _getClosestNonVisited( previous );
        }
        return tour;
    }

    public int[] getPsuedoClosestNeighborTour() {
        return getPsuedoClosestNeighborTour( 0 );
    }

    public int[] getPsuedoClosestNeighborTour( int startIndex ) {
        int tour[] = new int[_points.length];
        int previous = startIndex;
        resetVisitedArray();
        for ( int i = 0; i < _points.length; i++ ) {
            tour[i] = previous;
            _visited[previous] = true;
            if ( i < _points.length -1 ) {
                previous = _getPsuedoClosestNonVisited( previous );
            }
        }
        return tour;
    }

    public int[] getClosestPairTour() {
        PathSet pathSet = new PathSet( this );
        while ( pathSet.getPaths().length > 1 ) {
            pathSet = pathSet.joinClosestPair();
        }
        return pathSet.getPaths()[0].indices;
    }
    
    public int[] getBestTour( int startingIndex ) {
        if ( _bestTourFound ) {
            return _bestTour;
        }
        double minDistance = -1.0;
        double maxDistance = -1.0;
        int initialTour[] = _getInitialTour( startingIndex );
        Permutation initialPermutation = new Permutation( initialTour );
        int allTours[][] = initialPermutation.generatePermutations();
        for ( int i = 0; i < allTours.length; i++ ) {
            int tour[] = allTours[i];
            double distance = getTourDistance( tour );
            if ( distance > maxDistance ) {
                maxDistance = distance;
                _worstTour = tour;
            }
            if ( distance < minDistance || minDistance < 0 ) {
                minDistance = distance;
                _bestTour = tour;
            }
        }
        _bestTourFound = true;
        _bestDistance = minDistance;
        _worstDistance = maxDistance;
        return _bestTour;
    }

    public int[] getBestTour() {
        return getBestTour(0);
    }

    public double getBestDistance() {
        if ( !_bestTourFound ) {
            getBestTour(); 
        }
        return _bestDistance;
    }

    public double getWorstDistance() {
        if ( !_bestTourFound ) {
            getBestTour();
        }
        return _worstDistance;
    }

    public void testGetClosestNonVisited() {

        // Override the points.
        Point point1 = new Point( 1, 2 );
        Point point2 = new Point( 4, 6 );
        Point point3 = new Point( 1, 4 );
        Point point4 = new Point( 4, 2 );
        _points = new Point[4];
        _points[0] = point1;
        _points[1] = point2;
        _points[2] = point3;
        _points[3] = point4;

        // Overrided the visited array.
        _visited = new boolean[4];
        _visited[0] = true;
        _visited[1] = false;
        _visited[2] = true;
        //        _visited[2] = false;
        _visited[3] = false;

        // Override the distance array.
        _distance = new double[4][4];
        for ( int i = 0; i < 4; i++ ) {
            for ( int j = 0; j < 4; j++ ) {
                _distance[i][j] = -1.0;
            }
        }
        int result = _getClosestNonVisited( 0 );
        System.out.printf( "The closest index is: %d\n", result );
    }

    public double getTourDistance( int tour[] ) {
        double distance = 0.0;
        int i;
        int j;
        for ( i = 0; i < tour.length; i++ ) {
            j = ( i + 1 ) % tour.length;
            double thisDistance = distanceBetweenPoints( tour[i], tour[j] );
            distance = distance + thisDistance;
        }
        return distance;
    }

    public double getOptimality( int tour[] ) {
        double worstDistance = getWorstDistance();
        double bestDistance = getBestDistance();
        if ( worstDistance == bestDistance ) {
            return 1.0; // All tours optimal.
        }
        double tourDistance = getTourDistance( tour );
        return ( worstDistance - tourDistance ) / ( worstDistance - bestDistance );
    }

    public Statistic[] getStatistics() {
        Statistic statistics[] = new Statistic[3];
        int tour[];
        double optimality;
        Statistic statistic;

        tour = getClosestNeighborTour();
        optimality = getOptimality( tour );
        statistic = new Statistic( "closestNeighbor", optimality );
        statistics[0] = statistic;

        tour = getClosestPairTour();
        optimality = getOptimality( tour );
        statistic = new Statistic( "closestPair", optimality );
        statistics[1] = statistic;
        //        return statistics;

        tour = getPsuedoClosestNeighborTour();
        optimality = getOptimality( tour );
        statistic = new Statistic( "psuedoClosestNeighbor", optimality );
        statistics[2] = statistic;
        return statistics;
    }

    // Private Methods.

    private void resetVisitedArray() {
        for ( int i = 0; i < _visited.length; i++ ) {
            _visited[i] = false;
        }
    }

    private int _getClosestNonVisited( int i ) {
        double minDistance = -1.0;
        int minIndex = -1;
        for ( int j = 0; j < _points.length; j++ ) {
            if ( !_visited[j] ) {
                double thisDistance = distanceBetweenPoints( i, j );
                if ( thisDistance < minDistance || minDistance < 0.0 ) {
                    minDistance = thisDistance;
                    minIndex = j;
                }
            }
        }
        return minIndex;
    }

    private int _getPsuedoClosestNonVisited( int i ) {
        int result = -1;
        BinaryNode distanceDistribution = getDistanceDistributions()[i];
        do {
            //System.out.println("Inside getPsuedoClosestNonVisited do loop.");
            double r = Math.random() * distanceDistribution.maxValue;
            BinaryNode rNode = distanceDistribution.locateSuccessor( r );
            result = rNode.data;
            //System.out.println("considering result: " + result);
        } while ( _visited[result] );
        return result;
    }

    private int[] _getInitialTour( int initialIndex ) {
        int tour[] = new int[_points.length];
        for ( int i = 0; i < _points.length; i++ ) {
            tour[i] = ( i + initialIndex ) % _points.length;
        }
        return tour;
    }

    public void testGetInitialTour() {
        int initialTour[] = _getInitialTour( 1 );
        for ( int i = 0; i < initialTour.length; i++ ) {
            System.out.printf( "%d\n", initialTour[i] );
        }
    }

}