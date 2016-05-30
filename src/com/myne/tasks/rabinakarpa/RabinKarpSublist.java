package com.myne.tasks.rabinakarpa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class RabinKarpSublist {

	/**
	 * Используя алгоритм Рабина - Карпа, реализовать функцию поиска первого
	 * вхождения подпоследовательности объектов в последовательности объектов.
	 * Функция должна иметь интерфейс public static int subsequence(List whole,
	 * List part) и выдавать индекс начала части в целом, а если
	 * подпоследовательность не найдена, то результатом должно быть значение -1.
	 * Равенство элементов списков определяется применением метода equals.
	 * 
	 * Алгоритм Рабина - Карпа разработан для работы со строками, тестируем на
	 * них
	 */

	public static int subsequence(List whole, List part) {
		if (whole == null || part == null || whole.size() == 0 || part.size() == 0 || whole.size() < part.size()) {
			return -1;
		}
		int result = -1;

		try {
			String wholeString = "";
			String allParts = "";
			List<Integer> listOfLengthOfElements = new ArrayList<>();

			// делаем строки из входных данных
			for (Object object : part) {
				allParts += object;
			}
			for (Object object : whole) {
				wholeString += object;
				listOfLengthOfElements.add(object.toString().length());
			}

			int indexOfStartSubstringIsWhole = rabinKarp(wholeString, allParts);
			// если нет вхождение -1
			if (indexOfStartSubstringIsWhole == -1)
				return -1;

			// первоe вхождениe подпоследовательности объектов в
			// последовательности объектов
			int summCurrent = 0;
			for (int i = 0; i < listOfLengthOfElements.size(); i++) {
				System.out.println(summCurrent);
				if (summCurrent > indexOfStartSubstringIsWhole) {
					System.out.println("gggwp " + summCurrent + " " + indexOfStartSubstringIsWhole);
					System.out.println("ind: " + (i-1));
					return i-1;
				}
				summCurrent += listOfLengthOfElements.get(i);
			}

		} catch (Exception e) {
			// if something wrong, for example different types return -1
			e.printStackTrace();
			return -1;
		}
		return result;
	}

	/**
	 * Returns the index of the first character of the substring if found, -1 if
	 * not
	 * 
	 */
	private static int rabinKarp(String wholeString, String substring) {
		if (substring.length() > wholeString.length()) {
			return -1; // s is not a substring of t.
		}

		final int BASE = 26;
		int hashOfWholeStr = 0, hashOfSubstr = 0; // Hash codes
		int powerS = 1; // BASE^wholeString.
		for (int i = 0; i < substring.length(); i++) {
			powerS = i > 0 ? powerS * BASE : 1;
			hashOfWholeStr = hashOfWholeStr * BASE + wholeString.charAt(i);
			hashOfSubstr = hashOfSubstr * BASE + substring.charAt(i);
		}

		for (int i = substring.length(); i < wholeString.length(); i++) {
			// Checks the two substrings are actually equal or not, to protect
			// against hash collision.
			if (hashOfWholeStr == hashOfSubstr && wholeString.substring(i - substring.length(), i).equals(substring)) {
				return i - substring.length(); // Found a match.
			}

			// Uses rolling hash to compute the new hash code.
			hashOfWholeStr -= wholeString.charAt(i - substring.length()) * powerS;
			hashOfWholeStr = hashOfWholeStr * BASE + wholeString.charAt(i);
		}
		// Tries to match s and t.substring(t.length() - s.length()).
		if (hashOfWholeStr == hashOfSubstr
				&& wholeString.substring(wholeString.length() - substring.length()).equals(substring)) {
			return wholeString.length() - substring.length();
		}
		return -1; // s is not a substring of t.
	}

	/**
	 * Test of algorithm.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> list1 = new ArrayList<>();
		List<String> subList1 = new ArrayList<>();
		List subList2Without = new ArrayList<>();
		for (int i = 0; i < 25; i++) {
			list1.add(String.valueOf(i));
		}
		Collections.addAll(subList1, "11", "12", "13", "14", "15", "16");
		Collections.addAll(subList2Without, "11", "12", "13", "abc");

		// Check in standart way
		int indexFirst = list1.indexOf(subList1.get(0));
		int indexLast = list1.indexOf(subList1.get(subList1.size() - 1));
		System.out.println(indexFirst + " " + indexLast);
		List<String> subListTest = list1.subList(indexFirst, indexLast + 1);
		System.out.println(subListTest);
		System.out.println(subList1);
		System.out.println("List and subList iquals: " + subList1.equals(subListTest));
		//

		int resTest1 = subsequence(list1, subList1);
		int resTest2 = subsequence(list1, subList2Without);
		System.out.println("\nTest algorithm...");
		System.out.println("Expected 11:" + resTest1);
		System.out.println("Expected -1:" +resTest2);
	}

}
