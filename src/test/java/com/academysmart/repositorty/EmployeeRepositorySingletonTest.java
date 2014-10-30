package com.academysmart.repositorty;

import com.academysmart.exception.IncorrectEmailException;
import com.academysmart.exception.ServletException;
import com.academysmart.repository.EmployeeRepositorySingleton;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class EmployeeRepositorySingletonTest {

	@BeforeClass
	public static void beforeClass()throws Exception {
		EmployeeRepositorySingleton.getRepository().addEmployee("ivan",
				"ivanov", "ivanov@mail.ru");
	}

	@Test
	public void testGetRepositoryReturnOneInstance() {
        EmployeeRepositorySingleton first = EmployeeRepositorySingleton.getRepository();
        EmployeeRepositorySingleton second = EmployeeRepositorySingleton.getRepository();
		Assert.assertEquals(first, second);
	}

	@Test(expected=IncorrectEmailException.class)
	public void testAddEmployeWithIncorrectEmail() throws ServletException {
		Assert.fail(String.valueOf(EmployeeRepositorySingleton.getRepository().addEmployee("name", "lname", "ivanov@mail.ru")));
	}
}

