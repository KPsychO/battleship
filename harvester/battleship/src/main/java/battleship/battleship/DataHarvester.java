package battleship.battleship;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataHarvester {

	String url;
	
	public DataHarvester (String url) {
		
		this.url = url;
		
	}
	
	public ArrayList<boolean[][]> getData(int n){
		
		ArrayList<boolean[][]> battlefields = new ArrayList<boolean[][]>();
		
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setJavascriptEnabled(true);
		WebDriverManager.phantomjs().setup();
		WebDriver driver = new PhantomJSDriver(caps);
		driver.get(url);
    	
    	for(int i = 0; i < n; i++)
    		battlefields.add(this.getBtf(driver));
    	
    	driver.close();
    	
    	return battlefields;
		
	}
	
	public  boolean[][] getBtf(WebDriver driver) {
		
		boolean[][] btf = new boolean[10][10];

		driver.findElement(By.cssSelector(".placeships-variant-link")).click();
		
		List<WebElement> board = driver.findElements(By.cssSelector(".battlefield-table > tbody > tr"));
		
		int y = 0;
		for (WebElement row : board) {
			
			List<WebElement> cells = row.findElements(By.cssSelector("td"));
			
			int x = 0;
			for(WebElement cell : cells) {
				
				if(cell.getAttribute("class").equals("battlefield-cell battlefield-cell__empty"))
					btf[x][y] = false;
				else
					btf[x][y] = true;
				
				x++;
				
			}
			
			y++;
			
			if(y > 9)
				break;
			
		}
	
		System.out.println(btf.hashCode());
		
		return btf;
		
	}
	
}
