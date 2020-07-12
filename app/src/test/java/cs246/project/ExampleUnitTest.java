package cs246.project;

import org.junit.Test;

import cs246.project.Entity.SingleClient;
import cs246.project.Entity.SingleProduct;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }


    /* Simple tests to get familiar with testing units. Delete the following tests
    once our application is constructed and we are ready to test relevant parts of our
    final project. Database, reader, etc.
     */
    @Test
    public void subtraction_isCorrect(){assertEquals(3,5-2);}

    @Test
    public void testNameExclamation(){
        SingleClient client1 = new SingleClient("testName");
    }

    @Test
    public void testProductClass(){

        SingleProduct product1 = new SingleProduct(32,"hello","description",3);

        //Set new name product
        product1.setName("pink");

        System.out.println(product1.getName());

        //change name again
        product1.setName("black");
        System.out.println(product1.getName());
    }



}