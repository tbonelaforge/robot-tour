public class BinaryNode {
    public BinaryNode children[] = new BinaryNode[2];
    public double value;
    public int data;
    public double maxValue;
    public boolean hasValue;
    public boolean hasMaxValue;
    public int height;
    public BinaryNode parent;
    public boolean hasParent;


    public BinaryNode( double initialValue, int initialData ) {
        value = initialValue;
        data = initialData;
        maxValue = initialValue;
        hasValue = true;
        hasMaxValue = true;
        height = 1;
        hasParent = false;
        for ( int i = 0; i < children.length; i++ ) {
            children[i] = null;
        }
    }

    public BinaryNode() {
        hasValue = false;
        hasMaxValue = false;
        height = 1;
        hasParent = false;
        for ( int i = 0; i < children.length; i++ ) {
            children[i] = null;
        }
    }

    public BinaryNode setChild( int childIndex, BinaryNode child ) {
        children[childIndex] = child;
        if ( child != null ) {
            child.parent = this;
            child.hasParent = true;
        }
        return this;
    }

    public BinaryNode search( double targetValue ) {
        if ( !hasParent && children[0] != null ) {
            return children[0].search( targetValue );
        }
        if ( !hasValue ) {
            return this;
        }
        if ( targetValue < value ) {
            if ( children[0] != null ) {
                return children[0].search( targetValue );
            }
        } else if ( targetValue > value ) {
            if ( children[1] != null ) {
                return children[1].search( targetValue );
            }
        }
        return this;
    }

    public BinaryNode insert( double targetValue, int targetData ) {
        BinaryNode searchResult = search( targetValue );
        if (!searchResult.hasParent) {
            searchResult.setChild(0, new BinaryNode( targetValue, targetData ));
        } else if ( searchResult.value < targetValue ) {
            searchResult.setChild( 1, new BinaryNode( targetValue, targetData ) );
        } else if ( searchResult.value > targetValue ) {
            searchResult.setChild( 0, new BinaryNode( targetValue, targetData ) );
        }
        searchResult.calculateHeight();
        searchResult.balance();
        if ( hasMaxValue ) {
            if ( targetValue > maxValue ) {
                maxValue = targetValue;
            }
        } else {
            maxValue = targetValue;
            hasMaxValue = true;
        }
        return this;
    }

    public void balance() {
        BinaryNode originalParent = ( hasParent ) ? parent : null;
        if ( originalParent == null ) {
            return;
        }
        if ( Math.abs( getChildHeight( 0 ) - getChildHeight( 1 ) ) >= 2 ) {
            for ( int i = 0; i < 2; i++ ) {
                int other = ( i + 1 ) % 2;
                if ( getChildHeight( i ) > getChildHeight( other ) ) {
                    if ( children[i].getChildHeight( i ) > children[i].getChildHeight( other ) ) {

                        // right-right or left-left imbalance.
                        rotate(other);
                        break;
                    } else { // right-left or left-right imbalance.
                        children[i].rotate(i);
                        rotate(other);
                        break;
                    }
                }
            }
        }
        if ( originalParent !=  null ) {
            originalParent.balance();
        }
    }


    public BinaryNode rotate( int direction ) {
        int other = ( direction + 1 ) % 2;
        BinaryNode  otherChild = children[other];

        _replaceSelfInParent( otherChild );
        setChild( other, otherChild.children[direction] );
        otherChild.setChild( direction, this );
        calculateHeight();
        return otherChild;
    }


    public String printTreeAsHTML() {
        String childStrings[] = { "", "" };
        String template = 
            "<table border='1'>" +
            "<tr align='center'><td colspan='2'>%f, %d<br />h = %d</td></tr>" +
            "<tr><td>%s</td><td>%s</td></tr></table>";

        for ( int i = 0; i < children.length; i++ )  {
            if ( children[i] == null ) {
                childStrings[i] = "";
            } else {
                childStrings[i] = children[i].printTreeAsHTML();
            }
        }

        if (!hasParent) {
            return childStrings[0];
        }

        return String.format( template, value, data, height, childStrings[0], childStrings[1] );
    }

    private void _replaceSelfInParent( BinaryNode replacement ) {
        int childIndex = 0;

        if ( !hasParent ) {
            return;
        }
        if ( parent.children[1] == this ) {
            childIndex = 1;
        }
        parent.setChild( childIndex, replacement );
    }

    private void calculateHeight() {
        int leftChildHeight = ( children[0] != null ) ? children[0].height : 0;
        int rightChildHeight = ( children[1] != null ) ? children[1].height : 0;
        height = Math.max( leftChildHeight, rightChildHeight ) + 1;
        if ( hasParent ) {
            parent.calculateHeight();
        }
    }

    public int getChildHeight( int childIndex ) {
        if ( children[childIndex] != null ) {
            return children[childIndex].height;
        }
        return 0;
    }

    public BinaryNode locateSuccessor( double target ) {
        BinaryNode searchResult = search( target );
        if ( searchResult.value >= target ) {
            return searchResult;
        }
        return searchResult.inOrderSuccessor();
    }

    public BinaryNode inOrderSuccessor() {
        if ( children[1] != null ) {
            return children[1].leftMostDescendent();
        }
        if ( isLeftChild() && parent.hasParent ) {
            return parent;
        }

        // This node is a right child, or left child of root.
        // find first parent which is a left child.
        BinaryNode ancestor = this;
        while ( ancestor != null ) {
            ancestor = ancestor.getParent();
            if ( ancestor.isLeftChild() ) {
                if ( !ancestor.parent.hasParent ) { // ancestor is top element.
                    return null;
                }
                return ancestor.parent;
            }
        }
        return null;
    }

    public BinaryNode leftMostDescendent() {
        BinaryNode descendent = this;
        
        while ( descendent.children[0] != null ) {
            descendent = descendent.children[0];
        }
        return descendent;
    }

    public boolean isLeftChild() {
        if ( !hasParent || parent.children[0] != this ) {
            return false;
        }
        return true;
    }

    public BinaryNode getParent() {
        if ( !hasParent ) {
            return null;
        }
        return parent;
    }
    
}