public class PointSetTester {
    private PointSet[] _pointSets;
    private int _numPointSets;
    private int _numPointsPerSet;
    private int _xmin;
    private int _xmax;
    private int _ymin;
    private int _ymax;

    public PointSetTester( int numPointSets, int numPointsPerSet, int xmin, int xmax, int ymin, int ymax ) {
        _numPointSets = numPointSets;
        _numPointsPerSet = numPointsPerSet;
        _xmin = xmin;
        _xmax = xmax;
        _ymin = ymin;
        _ymax = ymax;
        _pointSets = new PointSet[0];
    }

    public PointSetTester() {
        _numPointSets = 10;
        _numPointsPerSet = 4;
        _xmin = 1;
        _xmax = 5;
        _ymin = 1;
        _ymax = 5;
        _pointSets = new PointSet[0];
    }


    public PointSet[] getPointSets() {
        if ( _pointSets.length > 0 ) {
            return _pointSets;
        }
        return _generatePointSets();
    }

    public Statistic[] accumulateStatistics() {
        Statistic statisticsArrays[][] = _getStatisticsArrays();
        Statistic accumulatedStatistics[] = _getInitialStatistics( statisticsArrays[0] );
        for ( int i = 0; i < statisticsArrays.length; i++ ) {
            for ( int j = 0; j < accumulatedStatistics.length; j++ ) {
                Statistic accumulatedStatistic = accumulatedStatistics[j];
                Statistic statistic = statisticsArrays[i][j];
                accumulatedStatistic.addValue( statistic.value );
            }
        }
        for ( int i = 0; i < accumulatedStatistics.length; i++ ) {
            accumulatedStatistics[i].divideValue( statisticsArrays.length );
        }
        return accumulatedStatistics;
    }

    ////////////////////////////////////////////////////////////
    //// Private Methods ///////////////////////////////////////
    ////////////////////////////////////////////////////////////

    private Statistic[][] _getStatisticsArrays() {
        Statistic statisticsArrays[][] = new Statistic[_numPointSets][];
        for ( int i = 0; i < getPointSets().length; i++ ) {
            statisticsArrays[i] = getPointSets()[i].getStatistics();
        }
        return statisticsArrays;
    }

    private Statistic[] _getInitialStatistics( Statistic exampleStatistics[] ) {
        Statistic initialStatistics[] = new Statistic[exampleStatistics.length];
        for ( int i = 0; i < exampleStatistics.length; i++ ) {
            initialStatistics[i] = new Statistic( exampleStatistics[i].name, 0 );
        }
        return initialStatistics;
    }

    private PointSet[] _generatePointSets() {
        _pointSets = new PointSet[_numPointSets];
        for ( int i = 0; i < _numPointSets; i++ ) {
            _pointSets[i] = new PointSet(_getRandomPoints());
        }
        return _pointSets;
    }

    private Point[] _getRandomPoints() {
        Point randomPoints[] = new Point[_numPointsPerSet];
        for ( int i = 0; i < _numPointsPerSet; i++ ) {
            randomPoints[i] = _getRandomPoint();
        }
        return randomPoints;
    }

    private Point _getRandomPoint() {
        int x = _randomInteger( _xmin, _xmax );
        int y = _randomInteger( _ymin, _ymax );
        return new Point( x, y );
    }

    private static int _randomInteger( int min, int max ) {
        double r = min + Math.random() * ( max - min + 1 );
        return (int) Math.floor( r );
    }
}