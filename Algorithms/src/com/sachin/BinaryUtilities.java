package com.sachin;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Sessions :
 * youtube.com/watch?v=h7meukyY_bQ&list=PLUcsbZa0qzu3yNzzAxgvSgRobdUUJvz7p&index=1
 */
public class BinaryUtilities {
    public static void main(String[] args) throws Exception {
        invokeMethod("checkOddOrEven", 1);
        invokeMethod("swapNumbers", -10, -33);
        invokeMethod("findBitValue", 12, 2);
        invokeMethod("setBitValue", 12, 1);
        invokeMethod("clearBitValue", 12, 2);
        invokeMethod("findNumberOfSetBitsInNumber", 10);
        invokeMethod("findOnlyNonRepeatingElement", 1, 5, 4, 4, 3, 1, 3);
        invokeMethod("findTwoNonRepeatingElement", 1, 5, 4, 4, 3, 1, 8, 3);
        invokeMethod("checkIfNumberIsPowerOfTwo", 8);
        invokeMethod("findNonRepeatingNumberWithOtherNumberRepeatingKTimes");

    }

    /**
     * Utility method to have uniform input/output for all methods. 
     * 
     * @param methodName
     * @param parameters
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static void invokeMethod(String methodName, Object... parameters) throws IllegalAccessException, InvocationTargetException {
        Method[] methods = BinaryUtilities.class.getDeclaredMethods();

        for(Method method : methods) {
            if(method.getName().equalsIgnoreCase(methodName)) {                
                System.out.println("======================================================================");
                System.out.println("METHOD    => " + methodName );
                System.out.println("Arguments => " + Arrays.asList(parameters) );
                if(method.getParameterCount() == parameters.length) {
                    System.out.println("Output    => " + (method.invoke(BinaryUtilities.class, parameters)));
                    return;
                } else {
                    System.out.println("Output    => " + (method.invoke(BinaryUtilities.class, Arrays.asList(parameters))));
                    return;
                }
            }
        }
        throw new IllegalAccessException("Method not found with name " + methodName + " and number of arguments " + parameters.length);

    }

    /**
     * Check whether given number is odd or even. 
     * 
     * @param a
     * @return
     */
    public static String checkOddOrEven(int a) {
        if ((a & 1) == 0) {
            return "Even number";
        } else {
            return "Odd number";
        }
    }

    /**
     * Swap given two numbers. 
     * 
     * @param a
     * @param b
     * @return
     */
    public static String swapNumbers(int a, int b) {

        a = a ^ b;
        b = a ^ b;
        a = a ^ b;

        return ("Numbers after swap a=> " + a + ", b=>" + b);
    }

    /**
     * Find given index'th bit value of the number. 
     * 
     * @param number
     * @param bitIndex
     * @return
     */
    public static int findBitValue(int number, int bitIndex) {
        int mask = 1 << bitIndex;
        if ((number & mask) == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    /***
     * Set given bitIndex'th value of the number. 
     * 
     * @param number
     * @param bitIndex
     * @return
     */
    public static String setBitValue(int number, int bitIndex) {
        int mask = 1 << bitIndex;
        number = number | mask;
        return ("Number after setting bit " + number);
    }

    /**
     * Clear/reset given index bit value from number. 
     * 
     * @param number
     * @param bitIndex
     * @return
     */
    public static String clearBitValue(int number, int bitIndex) {
        int mask = ~(1 << bitIndex);
        number = number & mask;
        return ("Number after clearing bit " + number);
    }

    /**
     * Find number of set bits in number. 
     * 
     * @param number
     * @return
     */
    public static String findNumberOfSetBitsInNumber(int number) {
        int origNumber = number;
        int count = 0;
        while (number > 0) {
            number = (number & (number - 1));
            count++;
        }
        return ("Number of bits set in " + origNumber + " are " + count);
    }

    /**
     * Find single non repeating number.
     * 
     * @param numbers
     * @return
     */
    public static String findOnlyNonRepeatingElement(List<Integer> numbers) {
        int result = 0;
        for (int number : numbers) {
            result = result ^ number;
        }
        return ("Non repeating element " + result);
    }

    /**
     * Find two non repeating numbers.
     * 
     * @param numbers
     * @return
     */
    public static String findTwoNonRepeatingElement(List<Integer> numbers) {
        int result = 0;
        for (int number : numbers) {
            result = result ^ number;
        }
        int temp = result;

        int index = 0;
        int mask = 1;
        while ((temp & mask) == 0) {
            index++;
            mask = mask << 1;
        }

        List<Integer> subList = new ArrayList<>();
        for (int number : numbers) {
            if (findBitValue(number, index) == 1) {
                subList.add(number);
            }
        }

        int firstNumber = result;
        for (int number : subList) {
            firstNumber = firstNumber ^ number;
        }

        return ("First number " + firstNumber + " And Second number " + (firstNumber ^ result));
    }

    public static String findNonRepeatingNumberWithOtherNumberRepeatingKTimes() {
        List<Integer> list = Arrays.asList(1, 2, 3, 1, 7, 2, 3, 1, 2, 3, 2, 1, 3);
        int k = 4;
        int count = 0;
        int[] counterList = new int[32];
        for(int index = 0; index < 32; index++) {
            counterList[index] = 0;
        }
        for(int number : list) {
            count = 0;
            while(number > 0) {
                if((number % 2) != 0) {
                    counterList[count] = counterList[count] + 1;
                }
                number = number >> 1;
                count++;
            }
        }

        int result = 0;
        for(int index = 31; index >= 0; index--) {
            result = result << 1;
            if((counterList[index] % k) > 0) {
                result = result + 1;
            }
        }

        return "Non repeating element is " + result;

    }

    public static String checkIfNumberIsPowerOfTwo(int number) {
        if((number & (number-1)) == 0) {
            return ("Number " + number + " is power of 2");
        } 
        return ("Number " + number + " is not power of 2");
    }
}
