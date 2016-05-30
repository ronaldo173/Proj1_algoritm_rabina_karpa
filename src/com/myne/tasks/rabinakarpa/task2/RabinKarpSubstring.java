package com.myne.tasks.rabinakarpa.task2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RabinKarpSubstring {

	/**
	 * Используя алгоритм Рабина - Карпа, реализовать функцию поиска в строке
	 * первого вхождения одной из заданных подстрок. Функция должна иметь
	 * интерфейс public static IntPair substring(String whole, List parts) и
	 * выдавать значение типа IntPair, содержащее пару индексов - индекс начала
	 * найденной подстроки в строке и индекс найденной подстроки в списке
	 * подстрок. Если ни одна из заданных подстрок в строке не найдена, функция
	 * должна выдавать значение null. Строки в списке parts, вообще говоря,
	 * могут иметь разную длину.
	 * 
	 * @param whole целая строка
	 * @param parts список частей строк
	 * @return
	 */
	public static IntPair substring(String whole, List parts) {

		for (int i = 0; i < parts.size(); i++) {
			Object object = parts.get(i);
			String substring = object.toString();
			int indexStartSubString = rabinKarp(whole, substring);
			
			if(indexStartSubString != -1){
				return new IntPair(indexStartSubString, i);
			}
		}
		return null;

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
	 * Test work.
	 */
	public static void main(String[] args) {
		//строка для теста
		String wholeString = "abcdefgh";
		
		//1 лист с вхождением
		List<String> listOfParts1 = new ArrayList<>();
		Collections.addAll(listOfParts1, "ac", "de", "gh");		
		System.out.println("Expected 3, 1 : " + substring(wholeString, listOfParts1));
		
		//2 лист без вхождения
		List<String> listOfParts2 = new ArrayList<>();
		Collections.addAll(listOfParts1, "ac", "de1", "gh2");		
		System.out.println("Expected null : " + substring(wholeString, listOfParts2));
	}
}
