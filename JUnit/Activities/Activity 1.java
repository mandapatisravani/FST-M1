package activities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

public class Activity1 {
    // Test fixtures
    static ArrayList<String> list;

    // Initialize test fixtures before each test method
    @BeforeEach
    void setUp() throws Exception {
        list = new ArrayList<String>();
        list.add("alpha"); // at index 0
        list.add("beta"); // at index 1
    }

    // Test method to test the insert operation
    @Test
    public void insertTest() {
    	
    	List<String> expectedList = Arrays.asList("alpha","beta","gamma");
        // Assertion for size
        assertEquals(2, list.size(), "Wrong size");
        // Add new element
        list.add(2, "gamma");
        // Assert with new elements
        assertEquals(3, list.size(), "Wrong size");

		/*
		 * // Assert individual elements assertAll( () -> assertEquals("alpha",
		 * list.get(0), "Wrong element"), () -> assertEquals("beta", list.get(1),
		 * "Wrong element"), () -> assertEquals("charlie", list.get(2), "Wrong element")
		 * );
		 */
        assertIterableEquals(expectedList, list);
    }

    // Test method to test the replace operation
    @Test
    public void replaceTest() {
        // Expected List
    	List<String> expectedList = Arrays.asList("alpha","gamma");
    	//Replaced Value
        list.set(1, "gamma");

        // Assert size of list
        assertEquals(2, list.size(), "Wrong size");
        // Assert individual elements
		/*
		 * assertAll( () -> assertEquals("alpha", list.get(0), "Wrong element"), () ->
		 * assertEquals("charlie", list.get(1), "Wrong element") );
		 */
        assertIterableEquals(expectedList, list);
    }
}
