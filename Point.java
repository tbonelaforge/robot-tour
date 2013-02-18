public class Point {
    public int x;
    public int y;
    private String _key;

    public Point( int startX, int startY ) {
        x = startX;
        y = startY;
        _key = "";
    }

    public String getKey() {
        if ( _key.length() <= 0 ) {
            _key = String.format( "(%d, %d)", x, y );
        }
        return _key;
    }
}