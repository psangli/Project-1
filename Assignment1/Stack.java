import java.util.Iterator;

public class Stack implements Cloneable
{
    // This array is used to store the items in the stack.

    private Object[] data;

    // This is an index indicating the location of the top item in the stack.

    private int TOS;

    // The following specifies the size of the array in the default constructor.

    private static final int DEFAULT_SIZE = 20;

    // The following specifies the maximum number of items on the stack that will
    // be included in the toString method.

    private static int printLimit = 20;

    public static final int TOP_TO_BOTTOM = 0;
    public static final int BOTTOM_TO_TOP = 1;

    // This constructor creates an empty stack using the default array size.

    public Stack()
    {
        data = new Object[DEFAULT_SIZE];
        TOS = -1;
    }

    // This constructor creates an empty stack using a user-supplied array size.

    public Stack(int initSize)
    {
        if (initSize <=1)
            initSize = DEFAULT_SIZE;
        data = new Object[initSize];
        TOS = -1;
    }

    public Iterator iterator()
    {
        return new ITR();
    }

    public Iterator iterator(int order)
    {
        return new ITR(order);
    }

    private class ITR implements Iterator
    {
        public int pointer;
        public int isReverse;

        public ITR()
        {
            pointer = TOS;
        }

        public ITR(int order)
        {
            if (order == Stack.BOTTOM_TO_TOP)
            {
                pointer = 0;
                isReverse = 1;
            }
        }
        public boolean hasNext()
        {
            if (pointer > 0 && data[pointer-1] != null && isReverse == 0)
            {
                return true;
            }
            if (data[pointer+1] != null && isReverse == 1)
            {
                return true;
            }
            return false;
        }

        public void remove()
        {
            throw new UnsupportedOperationException();
        }

        public Object next()
        {
            if (isReverse == 1)
            {
                pointer++;
                return data[pointer-1];
            }
            pointer--;
            return data[pointer];
        }
    }

    // The height of a stack is the number of items it contains. Note that these
    // items are in data[0]..data[TOS].  So the number of items is TOS+1.

    public int height()
    {
        return TOS+1;
    }

    // This method is used just for our lab activities to report the size of the
    // underlying data array.

    public int capacity()
    {
        return data.length;
    }

    // Create an exact copy of the invoking object

    public Object clone()
    {
        Stack s = new Stack(data.length);
        s.TOS = TOS;
        for(int i=0;i<=TOS;++i)
            s.data[i] = data[i];
        return s;
    }
    public boolean equals(Object x)
    {
        // The following tests are common to all implementations of "equals"
        // in this course, regardless of the class to which it applies.

        if (this == x)
            return true;
        if (x == null)
            return false;
        if (getClass() != x.getClass())
            return false;

        Stack s = (Stack) x;
        if (TOS != s.TOS)
            return false;
        for(int i=0;i<=TOS;++i)
            if (!data[i].equals(s.data[i]))
                return false;
        return true;
    }

    // Add a new item to the top of the stack.  Resize the underlying array if
    // necessary.

    public void push(Object item)
    {
        if (data.length > 0 && TOS == data.length-1)
        {
            Object[] temp = new Object[(int)(1.5*data.length)];
            for(int i=0;i<data.length;++i)
                temp[i] = data[i];
            data = temp;
        }
        data[++TOS] = item;
    }

    // This method allows the client to control the number of stack items
    // that will be included in the toString method.

    public static void setPrintLimit(int limit)
    {
        if (limit >= 1)
            printLimit = limit;
    } // This method produces a string of the form {item1,item2,...} where item1
    // is the top of the stack. The number of items included is the smaller of
    // the number of items in the stack and the value of printLimit. The "..."
    // is shown only if there are stack items that were not included.

    public String toString()
    {
        int limit = Math.min(height(),printLimit);
        String answer = "{";
        for(int i=0;i<limit;++i)
        {
            answer = answer + data[TOS-i];
            if (i != limit-1)
                answer = answer + ",";
        }
        if (height() > limit)
            answer = answer + ",...";
        return answer + "}";
    }

    public Object pop()
    {
        // Fill in the details as required on the assignment sheet.
        if (TOS == -1)
        {
            throw new Stack.Error("Empty Stack");
        }

        this.TOS = TOS - 1;
        return this.data[TOS + 1];
    }
    public Object peek()
    {
        // Fill in the details as required on the assignment sheet.
        if (TOS == -1)
        {
            throw new Stack.Error("Empty Stack");
        }
        return this.data[TOS];
    }

    // Begin Part II

    // Nested exception class for reporting all errors that occur in Stack metho$

    public static class Error extends RuntimeException
    {
        public Error(String message)
        {
            super(message);
        }
    }


    public boolean contains(Object item)
    {
        // Fill in the details as required on the assignment sheet.
        for (int i = 0; i < height(); i++)
        {
            if(item.equals(this.data[i]))
                return true;
        }
        return false;
    }

    public boolean isEmpty()
    {
        // Fill in the details as required on the assignment sheet.
        if (TOS == -1)
        {
            return true;
        }
        for (int i = 0; i <= TOS; i++)
        {
            if (data[i] != null)
                return false;
        }
        return true;
    }

    public void clear()
    {
        // Fill in the details as required on the assignment sheet.
        TOS = -1;
    }
}