import com.sun.tools.hat.internal.util.ArraySorter;
import com.sun.xml.internal.bind.v2.runtime.reflect.ListIterator;
import javafx.util.converter.IntegerStringConverter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Layout {

    public Boolean isHouseSequenceValid(ArrayList<Integer> houseList) {

        if (houseList.get(0).intValue() != 1){
            return false;
        }

        List<Integer> newIntOdd =  houseList.stream().filter(n -> n % 2 != 0)
                .collect(Collectors.toList());
        List<Integer> newIntEven =  houseList.stream().filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        newIntOdd.forEach(newInt1-> System.out.println("new Int "+newInt1.intValue()));

        if (IntStream.range(0,newIntOdd.size()-1).filter(i ->
                   ( ((Integer) newIntOdd.get(i +1 ).intValue() != (((Integer) newIntOdd.get(i)).intValue()+2))
               )).count() > 0){
            return false;
        }


        if(IntStream.range(0,newIntEven.size()-1).filter(i ->
                (((Integer) newIntEven.get(i + 1).intValue() != (((Integer) newIntEven.get(i)).intValue()+2))
                )).count() > 0){
            return false;
        }

        return true;
    }

    public Integer streetHouseCount(ArrayList<Integer> houseList) {
        boolean flag = true;
        System.out.println("HouseCount is " + houseList.size());

        return houseList.size();
    }

    public Integer streetHouseCountLeftHouse(ArrayList<Integer> houseList) {
        Long countOddNew = IntStream.range(0,houseList.size()-1).filter(t ->  ((((Integer)t).intValue() % 2) != 0)
        ).count();

        return countOddNew.intValue();
    }

    public Integer streetHouseCountRightHouse(ArrayList<Integer> houseList) {
        Long countEvenNew = IntStream.range(0,houseList.size()-1).filter(t ->  ((((Integer)t).intValue() % 2) == 0)
        ).count();

        return countEvenNew.intValue();
    }

    public List<Integer> houseOrderOfDeliveryListApproach1(ArrayList<Integer> houseList) {

        LinkedList<Integer> finalHouseDeliveryOrder = new LinkedList<Integer>();

        List<Integer> countOddNew = houseList.stream().filter(n -> n % 2 != 0)
                .collect(Collectors.toList());
        List<Integer> countEvenNew = houseList.stream().filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        Collections.reverse(countEvenNew);

        finalHouseDeliveryOrder.addAll(countOddNew);
        finalHouseDeliveryOrder.addAll(countEvenNew);

        return finalHouseDeliveryOrder;
    }

    public List<Integer> houseOrderOfDeliveryListApproach2(ArrayList<Integer> houseList) {

        LinkedList<Integer> finalHouseDeliveryOrder = new LinkedList<Integer>();

        List<Integer> countOddNew = houseList.stream().filter(n -> n % 2 != 0)
                .collect(Collectors.toList());
        List<Integer> countEvenNew = houseList.stream().filter(n -> n % 2 == 0)
                .collect(Collectors.toList());


        for(int i = 0 ; i < houseList.size()  ; i++){
            if(i <= (countOddNew.size() -1) &&  countOddNew.get(i) != null)
            finalHouseDeliveryOrder.add(countOddNew.get(i));

            if(i <= (countEvenNew.size() -1) && countEvenNew.get(i) != null ) {
                finalHouseDeliveryOrder.add(countEvenNew.get(i));
            }

        }

        return finalHouseDeliveryOrder;
    }

    public  Integer numberOfroadcrossingsRequiredApproach1(ArrayList<Integer> houseList) {

        LinkedList<Integer> leftSideHouseCount = new LinkedList<Integer>();
        LinkedList<Integer> rightSideHouseCount = new LinkedList<Integer>();
        Integer numberOfCrossings = 0 ;

        for (int i = 0; i <= houseList.size() - 1; i++) {
            if (houseList.get(i) % 2 == 0) {
                rightSideHouseCount.add(houseList.get(i));
            }else {
                leftSideHouseCount.add(houseList.get(i));
            }
        }

        if(leftSideHouseCount.size() > rightSideHouseCount.size()){
            numberOfCrossings = rightSideHouseCount.size() *2;
        }else if(leftSideHouseCount.size() < rightSideHouseCount.size()){
            numberOfCrossings = leftSideHouseCount.size() *2;
        }else{
            numberOfCrossings = (leftSideHouseCount.size() *2) -1;
        }

        return numberOfCrossings;
    }

    public  Integer numberOfroadcrossingsRequiredApproach2(Integer numberOfStreets) {
        return numberOfStreets;
    }
}
