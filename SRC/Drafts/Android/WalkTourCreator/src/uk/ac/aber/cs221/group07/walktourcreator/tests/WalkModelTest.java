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
		//wm = new WalkModel();
	}
	
	@Test
	public void testGetLastPoi() {
		poi = new PointOfInterest(100,100);
		assertSame(poi ,wm.getLastPoi());
	}

	@Test
	public void testGetRoutePath() {
		Vector<LocationPoint> testPath;
		//assertEquals(testPath, wm.getRoutePath());
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
	public void testGetTitle() {
		String title = "title";
		assertEquals(title, wm.getTitle());
	}

	@Test
	public void testShortDescription() {
		fail("Not yet implemented");
	}

	@Test
	public void testlongDescription() {
	}

	@Test
	public void testAddLocation() {
		fail("Not yet implemented");
	}

}
