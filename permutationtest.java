public class permutationtest {
    public static void main( String args[] ) {
        int testIndices[] = { 0, 1, 2, 3 };
        Permutation testPermutation = new Permutation( testIndices );
        int spliceResult[] = testPermutation.splice( 1 );
        System.out.println( "The splice result is:" );
        for ( int i = 0; i < spliceResult.length; i++ ) {
            System.out.printf( "%d\n", spliceResult[i] );
        }

        // Test the extend function.
        int testExtension[] = { 4, 5, 6 };
        int extendResult[] = testPermutation.addOn( testExtension );
        System.out.println( "The extend result is:" );
        for ( int i = 0; i < extendResult.length; i++ ) {
            System.out.printf( "%d\n", extendResult[i] );
        }


        // Test the generatePermuations function.
        int smallIndices[] = { 0, 1, 2 };
        Permutation smallPermutation = new Permutation( smallIndices );
        int allPermutations[][] = smallPermutation.generatePermutations();
        System.out.println( "All the permutations are:" );
        for ( int i = 0; i < allPermutations.length; i++ ) {
            int permutation[] = allPermutations[i];
            System.out.printf( "( " );
            for ( int j = 0; j < permutation.length; j++ ) {
                System.out.printf( "%d, ", permutation[j] );
            }
            System.out.printf( ")\n" );
        }
    }
}