package assertions;

import errors.AssertionFailed;

public class Assertions {
    public static  void assertEquals(Object expected, Object actual) {
        if (!expected.equals(actual)) {
            throw new AssertionFailed("Expected " + expected + " but found " + actual);
        }
    }
    public static void assertTrue(String message, boolean cond)
    {
            if(!cond){
                if(message == null) throw new AssertionFailed();
                else throw new AssertionFailed(message);
            }
    }
    public static void assertTrue(boolean cond)
    {
        assertTrue(null,cond);
    }
    public static void assertFalse(boolean cond)
    {
        assertTrue(null,!cond);
    }
    public static void assertFalse(String message, boolean cond)
    {
        assertTrue(message,!cond);
    }



}
