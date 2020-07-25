package com.actitime.test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.actitime.generics.BaseClass;
import com.actitime.pom.EnterTimeTrackPage;
import com.actitime.pom.TaskListPage;
@Listeners(com.actitime.generics.ListenerImplementation.class)
public class CreateCustomer extends BaseClass{
	@Test
	public void testCreateCustomer() throws EncryptedDocumentException, IOException, InterruptedException
	{
		String ExpectedCustomerName = f.getExcelData("CreateCustomer", 1, 0);
		String customerDescription = f.getExcelData("CreateCustomer", 1, 1);
		EnterTimeTrackPage e=new EnterTimeTrackPage(driver);
		e.getTaskTab().click();
		TaskListPage t=new TaskListPage(driver);
		Thread.sleep(2000);
		t.getAddNewBtn().click();
		t.getNewCustomerMenu().click();
		t.getEnterCustomerNametbx().sendKeys(ExpectedCustomerName);
		t.getEnterCustomerDescription().sendKeys(customerDescription);
		t.getCopyProjectDropDown().click();
		t.getOurCompanyMenu().click();
		t.getCreateCustomerBtn().click();
		Thread.sleep(6000);
		String ActualCustomerCreated = t.getVerifyCustomerText().getText();
		Assert.assertEquals(ActualCustomerCreated, ExpectedCustomerName);
		
	}

}
