package another;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.Before;



public class testf {
	static Connection cnn=null;
	@Before                                         
    public void setUp() throws ClassNotFoundException, SQLException {
		if(cnn==null){
			cnn=Start.sqlmeth();
			System.out.println("called");
			System.out.println(cnn==null);
		}
    }
	@Test 
	public void test() throws ClassNotFoundException, SQLException {
		methes.checktype("user1",cnn);
		assertEquals(1,methes.checktype("user1",cnn));
	}
}
