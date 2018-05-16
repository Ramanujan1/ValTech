
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Layout {

    private final ArrayList<Integer> houseList;
    private  List<Integer> leftSideHouses = new ArrayList<>();
    private  List<Integer> rightSideHouses= new ArrayList<>();

    private static final Logger LOGGER = Logger.getLogger( Layout.class.getName() );

    Layout(ArrayList<Integer> houseList,List<Integer> leftSideHouses,List<Integer> rightSideHouses){
        this.houseList = houseList;
        this.leftSideHouses = leftSideHouses;
        this.rightSideHouses = rightSideHouses;
    }

    public Boolean isHouseSequenceValid() throws HouseListException {

        if (houseList.get(0).intValue() != 1){
            LOGGER.info(" The house List does not start with 1");
            throw new HouseListException(1001,"The house List does not start with 1");
        }

        if (IntStream.range(0, leftSideHouses.size()-1).filter(i ->
                   ( ((Integer) leftSideHouses.get(i + 1).intValue() != (((Integer) leftSideHouses.get(i)).intValue()+2))
               )).count() > 0){
            LOGGER.info(" The houses on the north(left) side of the street are not in sequence");
            throw new HouseListException(1002,"The houses on the north(left) side of the street are not in sequence");
        }

        if(IntStream.range(0,rightSideHouses.size()-1).filter(i ->
                (((Integer) rightSideHouses.get(i + 1).intValue() != (((Integer) rightSideHouses.get(i)).intValue()+2))
                )).count() > 0){
            LOGGER.info(" The houses on the right(south) side of the street are not in sequence");
            throw new HouseListException(1003,"The houses on the right(south) side of the street are not in sequence");
        }

        return true;
    }

    public Integer streetHouseCount() {
        LOGGER.info(" House count is total: "+ houseList.size());

        return houseList.size();
    }

    public Integer streetHouseCountLeftHouse() {
        Long countOddNew = IntStream.range(0,houseList.size()).filter(t ->  ((((Integer)t).intValue() % 2) != 0)
        ).count();
        LOGGER.info(" House count on the left(north) of the street: "+ countOddNew.intValue());

        return countOddNew.intValue();
    }

    public Integer streetHouseCountRightHouse() {
        Long countEvenNew = IntStream.range(0,houseList.size()-1).filter(t ->  ((((Integer)t).intValue() % 2) == 0)
        ).count();
        LOGGER.info(" House count on the right(south) of the street: "+ countEvenNew.intValue());

        return countEvenNew.intValue();
    }

    public List<Integer> houseOrderOfDeliveryListApproach1() {

        List<Integer> finalHouseDeliveryOrder = new ArrayList<>();

        Collections.reverse(rightSideHouses);

        finalHouseDeliveryOrder.addAll(leftSideHouses);
        finalHouseDeliveryOrder.addAll(rightSideHouses);

        return finalHouseDeliveryOrder;
    }

    public List<Integer> houseOrderOfDeliveryListApproach2() {

        List<Integer> finalHouseDeliveryOrder = new ArrayList<Integer>();

//        List<Integer> houseOddNumber = houseList.stream().filter(n -> n % 2 != 0)
//                .collect(Collectors.toList());
//        List<Integer> houseEvenNumber = houseList.stream().filter(n -> n % 2 == 0)
//                .collect(Collectors.toList());

        for(int i = 0 ; i < houseList.size()  ; i++){
            if(i <= (leftSideHouses.size() -1) &&  leftSideHouses.get(i) != null)
            finalHouseDeliveryOrder.add(leftSideHouses.get(i));

            if(i <= (rightSideHouses.size() -1) && rightSideHouses.get(i) != null ) {
                finalHouseDeliveryOrder.add(rightSideHouses.get(i));
            }
        }

        return finalHouseDeliveryOrder;
    }

    public  Integer numberOfroadcrossingsRequiredApproach1() {

        List<Integer> leftSideHouseCount = new ArrayList<>();
        List<Integer> rightSideHouseCount = new ArrayList<>();
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

        LOGGER.info(" Number of crossings  Approach1 : "+numberOfCrossings.intValue());

        return numberOfCrossings;
    }

    public  Integer numberOfroadcrossingsRequiredApproach2(Integer numberOfStreets) {
        LOGGER.info(" Number of crossings  Approach2 : "+numberOfStreets.intValue());
        return numberOfStreets.intValue();
    }
}
