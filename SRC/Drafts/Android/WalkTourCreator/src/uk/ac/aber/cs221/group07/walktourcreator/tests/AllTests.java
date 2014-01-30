package uk.ac.aber.cs221.group07.walktourcreator.tests;

import static org.junit.Assert.*;
import java.util.Vector;
import org.junit.Before;
import org.junit.Test;

import uk.ac.aber.cs221.group07.walktourcreator.model.LocationPoint;
import uk.ac.aber.cs221.group07.walktourcreator.model.PointOfInterest;
import uk.ac.aber.cs221.group07.walktourcreator.model.WalkModel;


/**
 * This class is responsible for conducting JUnit tests, mostly for the methods from WalkModel class, 
 * but there is also a test for one method from LocationPoint class.
 * @author Maciek Dobrzanski
 */
public class AllTests {
	
	WalkModel wm;
		
	@Before
	public void init(){
		wm = new WalkModel();
	}
	
	/**
	 * Checks if the object returned by the getLastPoi method is the same as the test object.. 
	 */
	@Test
	public void testGetLastPoi() {
		LocationPoint lp = new LocationPoint(100,100);
		PointOfInterest poi = new PointOfInterest(lp);
		wm.addLocation(poi);
		assertSame(poi ,wm.getLastPoi());
	}

	/**
	 * Checks if the vector returned by the getRoutePath method is the same as the test vector.
	 */
	@Test
	public void testGetRoutePath() {
		Vector<LocationPoint> testPath = new Vector<LocationPoint>();
		assertEquals(testPath, wm.getRoutePath());
	}

	/**
	 * Checks if the total distance of the walk is returned properly.
	 */
	@Test
	public void testGetDistance() {
		double testDistance = 0;
		assertEquals(testDistance, wm.getDistance(), 0);
	}
	
	/**
	 * Checks if the duration of the walk is returned properly.
	 */
	@Test
	public void testGetTimeTaken() {
		double testTime = 0;
		assertEquals(testTime, wm.getTimeTaken(), 0);
	}
	
	/**
	 * Checks if it is possible to set certain strings as a title and if 
	 * the title that was set is returned properly by getTitle method
	 */
	@Test
	public void testTitle() {
		String title = "";
		String title2 = "title";
		String title3 = "a long title a long title a long title a long title a long title a long title a long title a long title a long title a long title";
		
		assertFalse("failed, too short", wm.setTitle(title));
		assertTrue("correct", wm.setTitle(title2));
		assertFalse("failed, too long", wm.setTitle(title3));
		
		wm.setTitle(title2);
		assertEquals(title2, wm.getTitle());
		
	}

	/**
	 * Checks if it is possible to set certain strings as a short description and if 
	 * the short description that was set is returned properly by getShortDescription method
	 */
	@Test
	public void testShortDescription() {
		String testString = "";
		String testString2 = "test string";
		String testString3 = "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttestt";
		String testString4 = "A really long string that is supposed to fail A really long string that is supposed to fail A really long string that is supposed to fail A really long string that is supposed to fail A really long string that is supposed to fail A really long string that is supposed to fail A really long string that is supposed to fail A really long string that is supposed to fail A really long string that is supposed to fail A really long string that is supposed to fail A really long string that is supposed to fail A really long string that is supposed to fail A really long string that is supposed to fail";		
		
		assertFalse("failed, too short", wm.setShortDescription(testString));
		assertTrue("correct", wm.setShortDescription(testString2));
		assertFalse("failed, too long", wm.setShortDescription(testString3));
		assertFalse("failed, too long", wm.setShortDescription(testString4));
		
		wm.setShortDescription(testString2);
		assertEquals(testString2, wm.getShortDescription());
	}

	/**
	 * Checks if it is possible to set certain strings as a long description and if 
	 * the long description that was set is returned properly by getLongDescription method
	 */
	@Test
	public void testlongDescription() {
		String testString = "";
		String testString2 = "test string";
		String testString3 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc sit amet nibh nec nisl faucibus varius. Phasellus egestas orci non justo condimentum, et ornare mi accumsan. Nam dictum luctus urna vel auctor. Cras elit ante, semper in fermentum et, pulvinar vitae tellus. Mauris ac egestas dolor. Ut dui justo, elementum sed convallis in, auctor a lorem. Nulla sed pulvinar quam, ut aliquet magna. Sed pretium malesuada neque, id mollis tellus cursus sed. Sed auctor tristique odio, nec sollicitudin leo ultricies id. Maecenas id nulla tincidunt, tempus orci egestas, feugiat augue.Quisque eget augue tellus. Proin rhoncus a tortor lobortis pulvinar. Sed laoreet diam lorem, ut accumsan orci tristique id. Nullam molestie libero lacus, vel hendrerit leo iaculis ac. Aliquam id varius quam. Mauris nec urna vel neque viverra mattis nec nec nulla. Etiam commodo nisl nec blandit sodales. Integer egestas nibh eu neque convallis cursus. Duis luctus lectus dui, eu gravida lorem euismod nec. Praesent gravida elementum lorem, in malesuada est sollicitudin convallis. Nullam id ullamcorper eros, et mattis lectus. Cras eu ligula sem. Proin pulvinar nunc vitae massa cursus, et condimentum libero hendrerit. ";
		
		assertFalse("failed, too short", wm.setLongDescription(testString));
		assertTrue("correct", wm.setLongDescription(testString2));
		assertFalse("failed, too long", wm.setLongDescription(testString3));
		
		wm.setLongDescription(testString2);
		assertEquals(testString2, wm.getLongDescription());
	}

	/**
	 * Checks if the location added to the vector is saved there properly.
	 */
	@Test
	public void testAddLocation() {
		LocationPoint lp = new LocationPoint(100,100);
		Vector<LocationPoint> testPath = new Vector<LocationPoint>();
		testPath.add(lp);
		assertEquals(1, testPath.size());
	}
	
	/**
	 * Checks if the long description that was is not too short and does not exceed
	 * the maximum amount of characters.
	 */
	@Test
	public void testIsValidTitle() {
		assertTrue("correct", WalkModel.isValidTitle("title"));
	}
	
	/**
	 * Checks if the long description that was is not too short and does not exceed
	 * the maximum amount of characters.
	 */
	@Test
	public void testIsValidLongDesc() {
		assertTrue("correct", WalkModel.isValidLongDesc("Long Description"));
	}
	
	/**
	 * Checks if the short description that was is not too short and does not exceed
	 * the maximum amount of characters.
	 */
	@Test
	public void testIsValidShortDesc() {
		assertTrue("correct", WalkModel.isValidShortDesc("Short Description"));
	}
	
	/**
	 * Checks if the distance between two points on the map is calculated properly.
	 */
	@Test
	public void testDistBetween() {
		LocationPoint lp = new LocationPoint(52.413, -4.081); //Aberystwyth coordinates according to http://www.geobytes.com/CityDistance.htm?d&pt_1=UKWAABER&pt_2=UKWACARD
		LocationPoint lp2 = new LocationPoint(51.481, -3.174); //Cardiff coordinates according to http://www.geobytes.com/CityDistance.htm?d&pt_1=UKWAABER&pt_2=UKWACARD
		LocationPoint lp3 = new LocationPoint(-12.05, -77.05); //Lima (Peru) coordinates according to http://www.geobytes.com/CityDistance.htm?d&pt_1=UKWAABER&pt_2=PELILIMA
		
		//the tolerance used for the distances is 1% of the expected distance between the cities
		assertEquals(120, LocationPoint.distBetween(lp, lp2), 1.2);
		assertEquals(9957, LocationPoint.distBetween(lp, lp3), 99.57); 
	}
	
}
