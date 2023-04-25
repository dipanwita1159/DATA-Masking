package decryption;

import org.apache.poi.ss.usermodel.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;

public class data_masking {
	
static WebDriver driver;
	
	   
		@Test
		public static void test() throws IOException, InterruptedException {
		    // Load Excel file
		    FileInputStream file = new FileInputStream("C:\\Users\\DELL\\eclipse-workspace\\S.Grid\\DATAMASKING.xlsx");
		    Workbook workbook = WorkbookFactory.create(file);
		    Sheet sheet = workbook.getSheetAt(0);

		    // Get rows count
		    int rows = sheet.getLastRowNum();

		    // Iterate over rows
		    for (int i = 1; i <= rows; i++) {
		        // Get cell values
		        Row row = sheet.getRow(i);

		        // Null check for first cell
		        String firstName = "";
		        Cell firstNameCell = row.getCell(0);
		        if (firstNameCell != null) {
		            firstName = firstNameCell.getStringCellValue();
		        }

		        // Null check for second cell
		        String lastName = "";
		        Cell lastNameCell = row.getCell(1);
		        if (lastNameCell != null) {
		            lastName = lastNameCell.getStringCellValue();
		        }

		        // Null check for third cell
		        String email = "";
		        Cell emailCell = row.getCell(2);
		        if (emailCell != null) {
		            email = emailCell.getStringCellValue();
		        }

		        // Null check for fourth cell
		        String password = "";
		        Cell passwordCell = row.getCell(3);
		        if (passwordCell != null) {
		            password = passwordCell.getStringCellValue();
		        }
		     // Null check for fourth cell
		        String password2 = "";
		        Cell passwordCell2 = row.getCell(4);
		        if (passwordCell != null) {
		            password = passwordCell.getStringCellValue();
		        }

		        // Mask sensitive data
		        firstName=maskLastName(firstName);
		        System.out.println(firstName);
		        lastName=maskLastName(lastName);
		        System.out.println(lastName);
		        email = maskEmail(email);
		        System.out.println(email);
		        password = maskPassword(password);
		        System.out.println(password);
		        password2 = maskconfirmedpassword(password2);
		        System.out.println(password2);
		        //gender=maskEmail(gender);

		        // Perform registration using Selenium
		       driver = new ChromeDriver();
		        driver.get("https://demowebshop.tricentis.com/register");

		        WebElement firstNameInput = driver.findElement(By.name("FirstName"));
		        WebElement lastNameInput = driver.findElement(By.name("LastName"));
		        WebElement emailInput = driver.findElement(By.name("Email"));
		        WebElement passwordInput = driver.findElement(By.name("Password"));
		        WebElement passwordconfrim = driver.findElement(By.name("ConfirmPassword"));

		        WebElement submitButton = driver.findElement(By.xpath("//input[@id='register-button']"));
		        String genderStr = row.getCell(4).getStringCellValue(); // assuming gender is in column E
		        WebElement genderInput = driver.findElement(By.xpath("/html/body/div[4]/div[1]/div[4]/div[2]/form/div/div[2]/div[2]/div[2]/div[1]/label")); // assuming name attribute of radio buttons is "gender"
		        if (genderStr.equalsIgnoreCase("male")) {
		            WebElement maleGender = genderInput.findElement(By.xpath("//*[@id=\"gender-male\"]"));
		            ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('type', 'password')", maleGender);
		            maleGender.click();
		        } else if (genderStr.equalsIgnoreCase("female")) {
		            WebElement femaleGender = genderInput.findElement(By.xpath("//input[@id='gender-female']"));
		            ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('type', 'password')", femaleGender);
		            femaleGender.click();
				} /*
					 * else { WebElement otherGender =
					 * genderInput.findElement(By.xpath("//input[@value='other']"));
					 * ((JavascriptExecutor)
					 * driver).executeScript("arguments[0].setAttribute('type', 'password')",
					 * otherGender); otherGender.click(); }
					 */

		        Thread.sleep(5000);
		

	            //enter all the credentials
	            firstNameInput.sendKeys(firstName);
	            
	            lastNameInput.sendKeys(lastName);
	            emailInput.sendKeys(email);
	            passwordInput.sendKeys(password);
	            passwordconfrim.sendKeys(password2);
	            submitButton.click();
	            
	            // Wait for registration to complete
	           // WebDriverWait wait = new WebDriverWait(driver, 10);
	           // wait.until(ExpectedConditions.urlContains("/dashboard"));
	            driver.quit();
	            Thread.sleep(5000);
	            
	        }
	        
	        workbook.close();
	        
	    }
	    //masking email
	    private static String maskEmail(String email) {
	        // Replace characters with asterisks
	        String[] parts = email.split("@");
	        String username = parts[0];
	        int length = username.length();
	        String masked = "";
	        for (int i = 0; i < length; i++) {
	            masked += "x";
	        }
	        return masked + "@" + parts[1];
	    }
	    //masking password
	    private static String maskPassword(String password) {
	        // Replace characters with asterisks
	        int length = password.length();
	        String masked = "";
	        for (int i = 0; i < length; i++) {
	            masked += "x";
	        }
	        return masked;
	    }
	    //masking confirmed password
	    private static String maskconfirmedpassword(String password) {
	        // Replace characters with asterisks
	        int length = password.length();
	        String masked = "";
	        for (int i = 0; i < length; i++) {
	            masked += "x";
	        }
	        return masked;
	    }
	    //making Fisrtname
	    @SuppressWarnings("unused")
		private static String maskFirstName(String firstName) {
	    	//replacing characters with asterisks
	        int length = firstName.length();
	        String masked = "";
	        for (int i = 0; i < length; i++) {
	            masked += "x";
	        }
	        return masked;
	    }
	    // masking masking lastname
	    private static String maskLastName(String lastName) {
	    	//replacing characters with Asterisks
	        int length = lastName.length();
	        String masked = "";
	        for (int i = 0; i < length; i++) {
	            masked += "x";
	        }
	        return masked;
	    }

	}



