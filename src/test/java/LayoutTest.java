import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class LayoutTest {

    static ArrayList<Integer> initArrayValid = new ArrayList<>();
    static ArrayList<Integer> initArrayInValidStartValue = new ArrayList<>();
    static ArrayList<Integer> initArrayInValidSequence = new ArrayList<>();

     Layout layout = mock(Layout.class);

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
    public void testFileValidity(){
        Assert.assertTrue(layout.isHouseSequenceValid(initArrayValid));
    }

    @Test
    public void testFileInValidityStart(){
        Assert.assertFalse(layout.isHouseSequenceValid(initArrayInValidStartValue));
    }

    @Test
    public void testFileInValiditySequence(){
        Assert.assertFalse(layout.isHouseSequenceValid(initArrayInValidSequence));
    }

    @Test
    public void testStreetHouseCount(){
        Assert.assertEquals(layout.streetHouseCount(initArrayValid).intValue(),14);
    }

    @Test
    public void testStreetCountLeft(){
        Assert.assertEquals(layout.streetHouseCountLeftHouse(initArrayValid).intValue(),7);
    }

    @Test
    public void testStreetCountRight(){
        Assert.assertEquals(layout.streetHouseCountRightHouse(initArrayValid).intValue(),7);
    }

    @Test
    public void testHouseOrderOfDeliveryApproach1(){
        List<Integer> houseOrderOfDelivery = layout.houseOrderOfDeliveryListApproach1(initArrayValid);
        Assert.assertEquals(houseOrderOfDelivery.get(houseOrderOfDelivery.size()-1).intValue(),2);
    }

    @Test
    public void testHouseOrderOfDeliveryApproach2(){
        List<Integer> houseOrderOfDelivery = layout.houseOrderOfDeliveryListApproach2(initArrayValid);
        Assert.assertEquals(houseOrderOfDelivery.get(houseOrderOfDelivery.size()-1).intValue(),14);
    }

    @Test
    public void testNumberOfCrossingApproach1(){
        Assert.assertEquals(layout.numberOfroadcrossingsRequiredApproach1(initArrayValid).intValue(),13);
    }

    @Test
    public void testNumberOfCrossingApproach2(){
        Assert.assertEquals(layout.numberOfroadcrossingsRequiredApproach2(1).intValue(),1);
    }
}
