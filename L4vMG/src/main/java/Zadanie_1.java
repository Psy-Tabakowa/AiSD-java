import com.sun.jdi.ClassNotPreparedException;
import com.google.common.base.Preconditions;


import java.util.ArrayList;

public class Zadanie_1{

    ArrayList<Double> listOfSmaller, listOfEqual, listOfBigger, listOfAllSmaller=new ArrayList<>(), listOfAllBigger=new ArrayList<>();
    double element;

    public double Element_K(ArrayList<Double> list, int k)
    {
        if(listOfAllBigger.size()==0 && listOfAllSmaller.size()==0)
        {
            Preconditions.checkNotNull(list, "Argument was %s but expected Array with size>0", list);
            Preconditions.checkArgument(list.size()>0, "Array size was 0, but expected bigger");
            int n=k-1;
            Preconditions.checkElementIndex(k-1, list.size(), "Index equals k-1:"+n+"\n" );
        }
        listOfSmaller=new ArrayList<>();
        listOfEqual=new ArrayList<>();
        listOfBigger=new ArrayList<>();
        element=list.get((int)Math.floor(Math.random()*(list.size())));
        for (double value: list) {
            if(value<element)
                listOfSmaller.add(value);
            else if(value==element)
                listOfEqual.add(value);
            else
                listOfBigger.add(value);
        }
        if(listOfSmaller.size()+listOfAllSmaller.size()<k && (listOfSmaller.size()+listOfAllSmaller.size()+listOfEqual.size())>=k)
        {
            listOfAllSmaller=new ArrayList<>();
            listOfAllBigger=new ArrayList<>();
            return element;
        }
        else if((listOfSmaller.size()+listOfAllSmaller.size()+listOfEqual.size())<k) {
            listOfAllSmaller.addAll(listOfSmaller);
            listOfAllSmaller.addAll(listOfEqual);
            return this.Element_K(listOfBigger, k);
        }
        else {
            listOfAllBigger.addAll(listOfBigger);
            listOfAllBigger.addAll(listOfEqual);
            return this.Element_K(listOfSmaller, k);
        }
    }
}
