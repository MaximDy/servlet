package com.academysmart.repositorty;

import com.academysmart.database.EmployeeDatabase;
import com.academysmart.exception.IncorrectEmailException;
import com.academysmart.exception.ServletException;
import com.academysmart.model.Employee;
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

    @Test
    public void checkInsertData() throws ServletException{
        Employee e = new Employee("name1", "lname1", "some@mail.com");
        EmployeeDatabase.getDatabase().insertData(e);
        Employee e2 = EmployeeDatabase.getDatabase().getData().get
                (EmployeeDatabase.getDatabase().getData().size()-1);
        Assert.assertEquals(e.getEmail(), e2.getEmail());
    }
}

