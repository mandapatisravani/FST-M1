package examples;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;

public class CalculatorTest {

	Calculator calc;
	
	@BeforeEach
    public void setUp(){
        calc = new Calculator();
    }
	
	
	@Test
	public  void addTest() {
		//perform the add function to get the Actual result
		int result1 = calc.add(10, 20);
		int result2 = calc.add(3, 5);
		int result3 = calc.add(10, 5);
		
		
		//Add Assertion statement to verify  against  Expected result
		assertAll(
				() -> assertEquals(30,result1),
				() -> assertEquals(10,result2),
				() -> assertEquals(15,result3)
				
				
			);
		
	}
		
	
	@ParameterizedTest
	@ValueSource(ints = {10,20,25,100})
	public  void paramsqrtTest(int num1) {
		int result = calc.square(num1);
		
		assertEquals(num1 * num1, result);
	}
	
	
	@ParameterizedTest
	@CsvSource({
		"10,90","50,50","60,40"
		})
	public  void paramAddTest(int num1, int num2) {
		//perform the add function to get the Actual result
		int result1 = calc.add(num1, num2);
		
		
		
		//Add Assertion statement to verify  against  Expected result
		assertEquals(100, result1);
		
	}
	
	@ParameterizedTest
	@CsvFileSource(files = "src/test/resources/input.csv", nullValues = {"N/A","NA","-"})
	public void AggregateTest(ArgumentsAccessor args) {
		System.out.println(args.getString(0) + "has only " + (args.getInteger(3)+3) + " sick  days remaining");
	}
	
	
	
	
	@Test
	public void addMultiply() {
		// perform the add function to get the Actual result
		int result = calc.multiply(5, 5);

		// Add Assertion statement to verify against Expected result

		assertEquals(25, result);
	}
	

}
