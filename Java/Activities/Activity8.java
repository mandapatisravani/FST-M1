// Custom Exception class that extends Exception
class CustomException extends Exception {
    private String message = null;

    public CustomException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

public class Activity8 {

    public static void main(String[] a) {
        try {
            // Call exceptionTest with a valid string
            Activity8.exceptionTest("Will print to console");
            
            // This will throw a CustomException since we're passing null
            Activity8.exceptionTest(null); // Exception is thrown here
            
            // This line will not be executed
            Activity8.exceptionTest("Won't execute");
            
        } catch (CustomException ex) {
            // Printing the message of the CustomException
            System.out.println("Inside catch block: " + ex.getMessage());
        }
    }

    // Method that throws a CustomException if the input string is null
    static void exceptionTest(String str) throws CustomException {
        if (str == null) {
            throw new CustomException("String value is null");
        } else {
            System.out.println(str);
        }
    }
}