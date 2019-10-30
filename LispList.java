class LispList<E>
{
    private Cell<E> myList;

    private LispList(Cell<E> list)
    {
        myList=list;
    }

    public boolean isEmpty()
    {
        return myList==null;
    }

    public E head()
    {
        return myList.first;
    }

    public LispList<E> tail()
    {
        return new LispList<E>(myList.rest);
    }

    public LispList<E> cons(E item)
    {
        return new LispList<E>(new Cell<E>(item,myList));
    }

    public static <T> LispList<T> empty()
    {
        return new LispList<T>(null);
    }

    public boolean equals(Object other)
    {
        if(!(other instanceof LispList))
            return false;
        Cell<E> ptr1 = this.myList;
        Cell<E> ptr2 = ((LispList) other).myList;
        for(;ptr1!=ptr2&&ptr1!=null&&ptr2!=null; ptr1=ptr1.rest,ptr2=ptr2.rest)
            if(!ptr1.first.equals(ptr2.first))
                return false;
        return (ptr1==ptr2);
    }

    public String toString()
    {
        if(myList==null)
            return "[]";
        else
            return "["+myList.first+restToString(myList.rest);
    }

    private static <T> String restToString(Cell<T> l)
    {
        if(l==null)
            return "]";
        else
            return ","+l.first+restToString(l.rest);
    }

    private static class Cell <T>
    {
        T first;
        Cell<T> rest;

        Cell(T h,Cell<T> t)
        {
            first=h;
            rest=t;
        }
    }

}