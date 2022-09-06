package ru.bardinpetr.itmo.lab1;

import java.util.Random;


/**
 * ITMO programming laboratory work #1, variant #311902  
 * @author Bardin Petr
 */
public class Main {
	static final Random rnd = new Random();

	// Defines how much will random distribution shifted to make inclusive range
	// Please refer to Main.inclusiveRandom for usage.
	static final double RNG_INCLUSIVE_DELTA = 1e-5; 

	// Set of values of y when formula #2 is used
	static final int[] Y_VALUES_FOR_ARCTAN = new int[] {6, 8, 9, 10, 12, 15, 17}; 
	// Values of y when formula #1 is used
	static final int Y_VALUE_FOR_ARCSIN = 11; 

	// Bounds for sequential int array
	static final int Y_START_NUMBER = 5;
	static final int Y_END_NUMBER = 18;

	// Bounds for random double array
	static final double X_LOWER_LIMIT = -7.0;
	static final double X_UPPER_LIMIT = 8.0;
	static final int X_SIZE = 13;

	/**
	 * Generates a sequential array and a random array to produce 2d array according to formulas in the Task
	 */
	public static void main(String[] args) {
		double[] x = generateRandomArray(X_LOWER_LIMIT, X_UPPER_LIMIT, X_SIZE);

		int[] y = generateSequentialArray(Y_START_NUMBER, Y_END_NUMBER);
		int ySize = y.length;

		double[][] r = new double[ySize][X_SIZE];

		for(int i = 0; i < ySize; i++) {
			for(int j = 0; j < X_SIZE; j++) {
				if (y[i] == Y_VALUE_FOR_ARCSIN) {
					r[i][j] = calcFormulaWithArcsin(x[j]);
				} else if (contains(Y_VALUES_FOR_ARCTAN, y[i])) {
					r[i][j] = calcFormulaWithArctan(x[j]);
				} else {
					r[i][j] = calcFormulaWithSquare(x[j]);
				}
			}
		}

		// displayTableClassic(r);
		displayTablePretty(r, x, y);
	}

	/**
	 * Function for calculating formula #1 from the Task (used when y==11). 
	 * For reference see documentation in root of the project
	 */
	public static double calcFormulaWithArcsin(double x) {
		double base = (Math.asin((x + 0.5) / 15) + 1) / 2;
		base = Math.pow(base, 2) - 0.25;
		base = 2 / 3 / base;
		double power = 2 * (3 / 4 + Math.pow(x * (x + 3), 3));
		power = Math.pow(x / 2, power);
		return Math.pow(base, power);
	}

	/**
	 * Function for calculating formula #2 from the Task (used when y is in specific set). 
	 * For reference see documentation in root of the project
	 */
	public static double calcFormulaWithArctan(double x) {
		double root = Math.sqrt(Math.acos((x + 0.5) / 15));
		return Math.atan(1 / Math.pow(Math.E, root));
	}
	
	/**
	 * Function for calculating formula #3 from the Task (used when y is not matching functions #1 and #2). 
	 * For reference see documentation in root of the project
	 */
	public static double calcFormulaWithSquare(double x) {
		double ePower = Math.pow(Math.pow(2 * x, x) / 3, 3);
		double base = Math.pow(Math.E, ePower) / 2;
		return Math.pow(base / 2, 2);
	}

	/**
	 * Check if array contains an integer element (O(N))
	 * @param arr			Array to be iterated
	 * @param search	Element to search
	 * @return				If such element exists in array
	 */
	public static boolean contains(int[] arr, int search) {
		boolean res = false;
		for(int i = 0; i < arr.length; i++) res |= (arr[i] == search);
		return res;
	}

	/**
	 * Wrapper around Random.nextDouble to generate numbers in inclusive range [low, high] as original has [low, high).
	 * Attention: this mehod slightly stretches the original distribution by RNG_INCLUSIVE_DELTA! 
	 * To prevent shifting of distribution also applying delta to lower bound.
	 * Therefore [low-RNG_INCLUSIVE_DELTA, low] becomes low, and [high, high + RNG_INCLUSIVE_DELTA) becomes high.
	 * @param low		Inclusive lower bound
	 * @param high 	Inclusive higher bound
	 */
	public static double inclusiveRandom(double low, double high) {
		double res = rnd.nextDouble(low - RNG_INCLUSIVE_DELTA, high + RNG_INCLUSIVE_DELTA);
		return Math.min(high, Math.max(low, res));
	}

	/**
	 * Generate array of random double numbers within specified bounds
	 * @param lowerBound	Minimum random number
	 * @param upperBound	Maximum random number
	 * @param count				Result array length
	 * @return						Double array of random numbers
	 */
	public static double[] generateRandomArray(double lowerBound, double upperBound, int count) {
		double[] arr = new double[count];

		for(int i = 0; i < count; i++)
			arr[i] = inclusiveRandom(lowerBound, upperBound);

		return arr;
	}

	/**
	 * Generates integer array with sequential numbers. 
	 * Inverse order can be used by putting higher number as first and lower as second argument
	 * @param startNumber	This number will be a beginning of the sequence
	 * @param endNumber		This is the end number of the sequence
	 * @return						Result array of length calculated from input bounds
	 */
	public static int[] generateSequentialArray(int startNumber, int endNumber) {
		boolean inverse = startNumber > endNumber;
		int count = (inverse ? -1 : 1) * (endNumber - startNumber) + 1;
		int[] arr = new int[count];		

		for(int i = 0; i < count; i += 1)
			arr[i] = inverse ? (startNumber - i) : (startNumber + i);

 		return arr;
	}

	/**
	 * Prints 2d array of doubles rounded to 4 decimal digits to stdout
	 * @param table Input 2d array. Will not print anything if table is empty
	 */
	public static void displayTableClassic(double[][] table) {
		for(int i = 0; i < table.length; i++) {
			for(int j = 0; j < table[0].length; j++) 
				System.out.printf("%.4f ", table[i][j]);
			System.out.println();
		}
	}

	/**
	 * Prints 2d array of doubles rounded to 4 decimal digits to stdout
	 * This method prints separated columns of fixed size (11 chars) and items from "x" and "y" source arrays 
	 * @param table Input 2d array. Will not print anything if table is empty
	 * @param x			1d double labels array for colums (should have same dimension as second dim. of table)
	 * @param	y			1d int labels array for rows (should have same dimension as first dim. of table)
	 */
	public static void displayTablePretty(double[][] table, double[] x, int[] y) {
		System.out.print("     |");
		for(int j = 0; j < table[0].length; j++) {
			System.out.printf(" x=%-9.2f|", x[j]);
		}
		System.out.println();

		// Prints horizontal row (6 symbols for "y" column and 12 for each cell + 1 for right border)
		for(int j = 0; j < (table[0].length * 13 + 6); j++)
			System.out.print("-");

		System.out.println();

		for(int i = 0; i < table.length; i++) {
			System.out.printf("y=%02d | ", y[i]);
			for(int j = 0; j < table[0].length; j++) {
				System.out.printf("%10.4f | ", table[i][j]);
			}
			System.out.println();
		}
	}
}
