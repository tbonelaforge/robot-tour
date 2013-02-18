public class binarynodetester {
    public static void main( String args[] ) {
        BinaryNode node0 = new BinaryNode();

        node0.insert( 2.9, 1 );
        
        System.out.println( "After performing 1 insert, the tree looks like:" );
        System.out.println( node0.printTreeAsHTML() );
        

        node0.insert( 10.2, 2 );
        
        System.out.println( "After performing 2 inserts, the tree looks like:" );
        System.out.println( node0.printTreeAsHTML() );
       

        node0.insert( 22.1, 3 );
        
        System.out.println( "After performing 3 inserts, the tree looks like:");
        System.out.println( node0.printTreeAsHTML() );

        node0.insert( 22.125, 4 );
        
        System.out.println( "After performing 4 inserts, the tree looks like:");
        System.out.println( node0.printTreeAsHTML() );
       

        node0.insert( 111.25, 5 );
        System.out.println( "After performing 5 inserts, the tree looks like:");
        System.out.println( node0.printTreeAsHTML() );
        
        // Insert a number of random double values.
        BinaryNode node50 = new BinaryNode();
        int i;
        double badSequence[] = {
            6.194, 6.377, 6.809, 1.174, 1.835, 
            6.035, 3.168, 7.961, 6.312, 2.712
        };
        for ( i = 0; i <  10; i++ ) {
            double randomValue = badSequence[i];
            node50.insert( randomValue, i + 1 );
            System.out.printf( "Just inserted: %f\n", randomValue );
        }
        System.out.println( "At the end of all the " + i + " inserts, the tree looks like:");
        System.out.println( node50.printTreeAsHTML() );
        System.out.println( "The result of calling locateSuccessor, with value 0.0, has value:<br />" );
        System.out.printf( "%f\n<br />", node50.locateSuccessor( 0.0 ).value );

        System.out.println( "The result of calling locateSuccessor, with value 1.174, has value:<br />" );
        System.out.printf( "%f\n<br />", node50.locateSuccessor( 1.174 ).value );

        System.out.println( "The result of calling locateSuccessor, with value 1.8, has value:<br />" );
        System.out.printf( "%f\n<br />", node50.locateSuccessor( 1.8 ).value );

        System.out.println( "The result of calling locateSuccessor, with value 2.0, has value:<br />" );
        System.out.printf( "%f\n<br />", node50.locateSuccessor( 2.0 ).value );


        System.out.println( "The result of calling locateSuccessor, with value 6.1, has value:<br />" );
        System.out.printf( "%f\n<br />", node50.locateSuccessor( 6.1 ).value );

        System.out.println( "The result of calling locateSuccessor, with value 8.0, has value:<br />" );
        BinaryNode successor = node50.locateSuccessor( 8.0 );
        if ( successor != null ) {
            System.out.printf( "%f\n<br />", node50.locateSuccessor( 8.0 ).value );
        } else {
            System.out.printf( "null\n<br />" );
        }



    }
}