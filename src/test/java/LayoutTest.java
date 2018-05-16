import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@RunWith(MockitoJUnitRunner.class)
public class LayoutTest {

    static ArrayList<Integer> initArrayValid = new ArrayList<>();
    static ArrayList<Integer> initArrayInValidStartValue = new ArrayList<>();
    static ArrayList<Integer> initArrayInValidSequence = new ArrayList<>();

    List<Integer> leftSideHousesValid =  null;
    List<Integer> rightSideHousesValid =  null;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void setUpClass() throws FileNotFoundException {

        File file = new File(LayoutTest.class.getClassLoader().getResource("streetValid.txt").getFile());
        Scanner scanner = new Scanner(file);

        while(scanner.hasNextInt()){
            initArrayValid.add((scanner.nextInt()));
        }

        File fileInvalidStart = new File(LayoutTest.class.getClassLoader().getResource("streetInvalidStart.txt").getFile());
          scanner = new Scanner(fileInvalidStart);
        while(scanner.hasNextInt()){
            initArrayInValidStartValue.add((scanner.nextInt()));
        }

        File fileInvalidSequence = new File(LayoutTest.class.getClassLoader().getResource("streetInvalidSequence.txt").getFile());
        scanner = new Scanner(fileInvalidSequence);
        while(scanner.hasNextInt()){
            initArrayInValidSequence.add((scanner.nextInt()));
        }
    }

    @Test
    public void testFileValidity() throws HouseListException {
        List<Integer> leftSideHousesValid =  initArrayValid.stream().filter(n -> n % 2 != 0)
                .collect(Collectors.toList());
        List<Integer> rightSideHousesValid =  initArrayValid.stream().filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        Layout layout = new Layout(initArrayValid,leftSideHousesValid,rightSideHousesValid);
        Assert.assertTrue(layout.isHouseSequenceValid());
    }

    @Test
    public void testFileInValidityStart() throws HouseListException {

        List<Integer> leftSideHouses =  initArrayInValidStartValue.stream().filter(n -> n % 2 != 0)
                .collect(Collectors.toList());
        List<Integer> rightSideHouses =  initArrayInValidStartValue.stream().filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        thrown.expect(HouseListException.class);
        thrown.expectMessage("The house List does not start with 1");

        Layout layout = new Layout(initArrayInValidStartValue,leftSideHouses,rightSideHouses);

        layout.isHouseSequenceValid();

    }

    @Test
    public void testFileInValiditySequence() throws HouseListException {

        thrown.expect(HouseListException.class);
        thrown.expectMessage("The houses on the north(left) side of the street are not in sequence");

        List<Integer> leftSideHouses =  initArrayInValidSequence.stream().filter(n -> n % 2 != 0)
                .collect(Collectors.toList());
        List<Integer> rightSideHouses =  initArrayInValidSequence.stream().filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        Layout layout = new Layout(initArrayInValidSequence,leftSideHouses,rightSideHouses);
        layout.isHouseSequenceValid();

    }

    @Test
    public void testStreetHouseCount(){
        Layout layout = new Layout(initArrayValid,leftSideHousesValid,leftSideHousesValid);
        Assert.assertEquals(layout.streetHouseCount().intValue(),14);
    }

    @Test
    public void testStreetCountLeft(){
        Layout layout = new Layout(initArrayValid,leftSideHousesValid,leftSideHousesValid);
        Assert.assertEquals(layout.streetHouseCountLeftHouse().intValue(),7);
    }

    @Test
    public void testStreetCountRight(){
        Layout layout = new Layout(initArrayValid,leftSideHousesValid,leftSideHousesValid);
        Assert.assertEquals(layout.streetHouseCountRightHouse().intValue(),7);
    }

    @Test
    public void testHouseOrderOfDeliveryApproach1(){
        List<Integer> leftSideHousesValid =  initArrayValid.stream().filter(n -> n % 2 != 0)
                .collect(Collectors.toList());
        List<Integer> rightSideHousesValid =  initArrayValid.stream().filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        Layout layout = new Layout(initArrayValid,leftSideHousesValid,rightSideHousesValid);
        List<Integer> houseOrderOfDelivery = layout.houseOrderOfDeliveryListApproach1();
        Assert.assertEquals(houseOrderOfDelivery.get(houseOrderOfDelivery.size()-1).intValue(),2);
    }

    @Test
    public void testHouseOrderOfDeliveryApproach2(){
        List<Integer> leftSideHousesValid =  initArrayValid.stream().filter(n -> n % 2 != 0)
                .collect(Collectors.toList());
        List<Integer> rightSideHousesValid =  initArrayValid.stream().filter(n -> n % 2 == 0)
                .collect(Collectors.toList());

        Layout layout = new Layout(initArrayValid,leftSideHousesValid,rightSideHousesValid);
        List<Integer> houseOrderOfDelivery = layout.houseOrderOfDeliveryListApproach2();
        Assert.assertEquals(houseOrderOfDelivery.get(houseOrderOfDelivery.size()-1).intValue(),14);
    }

    @Test
    public void testNumberOfCrossingApproach1(){
        Layout layout = new Layout(initArrayValid,leftSideHousesValid,leftSideHousesValid);
        Assert.assertEquals(layout.numberOfroadcrossingsRequiredApproach1().intValue(),13);
    }

    @Test
    public void testNumberOfCrossingApproach2(){
        Layout layout = new Layout(initArrayValid,leftSideHousesValid,leftSideHousesValid);
        Assert.assertEquals(layout.numberOfroadcrossingsRequiredApproach2(1).intValue(),1);
    }
}
