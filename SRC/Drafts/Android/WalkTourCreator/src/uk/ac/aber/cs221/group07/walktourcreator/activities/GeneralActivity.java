package uk.ac.aber.cs221.group07.walktourcreator.activities;

import uk.ac.aber.cs221.group07.walktourcreator.R;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public abstract class GeneralActivity extends Activity{

	protected static String username;
	
	protected static int backgroundColor = Color.RED;
	protected static int foregroundColor = Color.BLUE;
	
	protected static int textColor = Color.BLACK;
	protected static int textSize = 12;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.getWindow().getDecorView().setBackgroundColor(backgroundColor);		
	}
	public void setBackgroundColor(int c){
		backgroundColor = c;
	}
	public void setForegroundColor(int c){
		foregroundColor = c;
	}
	public void setTextColor(int c){
		textColor = c;
	}
	public void setTextSize(int s){
		textSize=s;
	}
}
