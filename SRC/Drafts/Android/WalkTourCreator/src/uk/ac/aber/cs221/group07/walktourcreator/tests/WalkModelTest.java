package uk.ac.aber.cs221.group07.walktourcreator.tests;

import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import uk.ac.aber.cs221.group07.walktourcreator.model.LocationPoint;
import uk.ac.aber.cs221.group07.walktourcreator.model.PointOfInterest;
import uk.ac.aber.cs221.group07.walktourcreator.model.WalkModel;

public class WalkModelTest {
	
	WalkModel wm;
	PointOfInterest poi;
		
	@Before
	public void init(){
		wm = new WalkModel();
	}
	
	@Test
	public void testGetLastPoi() {
		poi = new PointOfInterest(100,100);
		assertSame(poi ,wm.getLastPoi());
	}

	@Test
	public void testGetRoutePath() {
		Vector<LocationPoint> testPath = new Vector<LocationPoint>();
		assertEquals(testPath, wm.getRoutePath());
	}

	@Test
	public void testGetDistance() {
		double testDistance = 0;
		assertEquals(testDistance, wm.getDistance(), 5);
	}

	@Test
	public void testGetTimeTaken() {
		double testTime = 0;
		assertEquals(testTime, wm.getTimeTaken(), 5);
	}
	
	@Test
	public void testTitle() {
		String title = "";
		String title2 = "title";
		String title3 = "a long title a long title a long title a long title a long title a long title a long title a long title a long title a long title";
		
		assertFalse("failed, too short", wm.setTitle(title));
		assertTrue("correct", wm.setTitle(title2));
		assertFalse("failed, too long", wm.setTitle(title3));
		
		assertEquals(title, wm.getTitle());
	}

	@Test
	public void testShortDescription() {
		String testString = "";
		String testString2 = "test string";
		String testString3 = "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttest";
		String testString4 = "A really long string that is supposed to fail A really long string that is supposed to fail A really long string that is supposed to fail A really long string that is supposed to fail A really long string that is supposed to fail A really long string that is supposed to fail A really long string that is supposed to fail A really long string that is supposed to fail A really long string that is supposed to fail A really long string that is supposed to fail A really long string that is supposed to fail A really long string that is supposed to fail A really long string that is supposed to fail";		
		
		assertFalse("failed, too short", wm.setShortDescription(testString));
		assertTrue("correct", wm.setShortDescription(testString2));
		assertFalse("failed, too long", wm.setShortDescription(testString3));
		assertFalse("failed, too long", wm.setShortDescription(testString4));
		
		assertEquals(testString2, wm.getShortDescription());
	}

	@Test
	public void testlongDescription() {
		String testString = "";
		String testString2 = "test string";
		String testString3 = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc sit amet nibh nec nisl faucibus varius. Phasellus egestas orci non justo condimentum, et ornare mi accumsan. Nam dictum luctus urna vel auctor. Cras elit ante, semper in fermentum et, pulvinar vitae tellus. Mauris ac egestas dolor. Ut dui justo, elementum sed convallis in, auctor a lorem. Nulla sed pulvinar quam, ut aliquet magna. Sed pretium malesuada neque, id mollis tellus cursus sed. Sed auctor tristique odio, nec sollicitudin leo ultricies id. Maecenas id nulla tincidunt, tempus orci egestas, feugiat augue.Quisque eget augue tellus. Proin rhoncus a tortor lobortis pulvinar. Sed laoreet diam lorem, ut accumsan orci tristique id. Nullam molestie libero lacus, vel hendrerit leo iaculis ac. Aliquam id varius quam. Mauris nec urna vel neque viverra mattis nec nec nulla. Etiam commodo nisl nec blandit sodales. Integer egestas nibh eu neque convallis cursus. Duis luctus lectus dui, eu gravida lorem euismod nec. Praesent gravida elementum lorem, in malesuada est sollicitudin convallis. Nullam id ullamcorper eros, et mattis lectus. Cras eu ligula sem. Proin pulvinar nunc vitae massa cursus, et condimentum libero hendrerit. "
		
		assertFalse("failed, too short", wm.setLongDescription(testString));
		assertTrue("correct", wm.setLongDescription(testString2));
		assertFalse("failed, too long", wm.setLongDescription(testString3));
		
		assertEquals(testString2, wm.getLongDescription());
	}

	@Test
	public void testAddLocation() {
		poi = new PointOfInterest(100, 100);
		Vector<LocationPoint> testPath = new Vector<LocationPoint>();
		wm.addLocation(poi);
		assertEquals(1, testPath.size());
	}
	
	@Test
	public void testIsValidTitle() {
		assertTrue("correct", WalkModel.isValidTitle("title"));
	}
	
	@Test
	public void testIsValidLongDesc() {
		assertTrue("correct", WalkModel.isValidLongDesc("Long Description"));
	}
	
	@Test
	public void testIsValidShortDesc() {
		assertTrue("correct", WalkModel.isValidShortDesc("Short Description"));
	}
	

}
