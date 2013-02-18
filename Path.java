public class Path {
    public int indices[];

    public Path( int startIndices[] ) {
        indices = startIndices;
    }

    public static Path join( Path paths[] ) {
        int resultLength = 0;
        for ( int i = 0; i < paths.length; i++ ) {
            resultLength += paths[i].numUniqueIndices();
        }
        int resultIndices[] = new int[resultLength];
        int previousIndex = -1; // No real index should ever be -1.
        int k = 0;
        for ( int i = 0; i < paths.length; i++ ) {
            for ( int j = 0; j < paths[i].indices.length; j++ ) {
                if ( paths[i].indices[j] != previousIndex ) {
                    resultIndices[k] = paths[i].indices[j];
                    k++;
                }
                previousIndex = paths[i].indices[j];
            }
        }
        return new Path( resultIndices );
    }

    public Path reverse() {
        int numElements = indices.length;
        int reversed[] = new int[numElements];
        for ( int i = 0;  i < numElements; i++ ) {
            reversed[numElements - 1 - i] = indices[i];
        }
        return new Path( reversed );
    }

    private int numUniqueIndices() {
        if ( indices.length == 2 ) {
            if ( indices[0] == indices[1] ) {
                return 1;
            }
        }
        return indices.length;
    }
}