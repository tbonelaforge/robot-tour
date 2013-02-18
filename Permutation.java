public class Permutation {
    private int _indices[];

    public Permutation( int startIndices[] ) {
        _indices = startIndices;
    }

    public int[] splice( int index ) {
        int result[] = new int[_indices.length - 1];
        int resultIndex = 0;
        for ( int i = 0; i < _indices.length; i++ ) {
            if ( i != index ) {
                result[resultIndex] = _indices[i];
                resultIndex++;
            }
        }
        return result;
    }

    public int[] addOn( int extension[] ) {
        int result[] = new int[_indices.length + extension.length];
        int i;
        for ( i = 0; i < _indices.length; i++ ) {
            result[i] = _indices[i];
        }
        for ( int j = 0; j < extension.length; j++ ) {
            result[i + j] = extension[j];
        }
        return result;
    }

    public int[][] generatePermutations() {
        int numResults = factorial( _indices.length );
        int results[][] = new int[numResults][_indices.length];
        if ( _indices.length == 1 ) {
            int result[] = { _indices[0] };
            results[0] = result;
            return results;
        }
        int resultIndex = 0;
        for ( int i = 0; i < _indices.length; i++ ) {
            int extension[] = { _indices[i] };
            int splicedIndices[] = splice( i );
            Permutation splicedPermutation = new Permutation( splicedIndices );
            int partialResults[][] = splicedPermutation.generatePermutations();
            for ( int j = 0; j < partialResults.length; j++ ) {
                Permutation partialResult = new Permutation( partialResults[j] );
                int extendedResult[] = partialResult.addOn( extension );
                results[resultIndex] = extendedResult;
                resultIndex++;
            }
        }
        return results;
    }

    public static int factorial(int n) {
        int fact = 1; // this  will be the result
        for (int i = 1; i <= n; i++) {
            fact *= i;
        }
        return fact;
    }


}