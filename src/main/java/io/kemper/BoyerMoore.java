package io.kemper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The {@code BoyerMoore} class finds the first occurrence of a pattern string in a text string.
 * <p>
 * This implementation uses the Boyer-Moore algorithm with the bad-character rule, but not the good suffix rule.
 * </p>
 * 
 * @author tjkemper
 *
 */
public class BoyerMoore {
	
	private static final char[] CHARS = new char[]{'A', 'C', 'G', 'T'};
	
	private String pattern;
	private List<Map<Character, Integer>> badCharacter;
	
	public BoyerMoore(String pattern) {
		
		this.pattern = pattern;
		badCharacter = new ArrayList<>();
		
		for(int i = 0; i < pattern.length(); i++) {
			Map<Character, Integer> temp = new HashMap<>();
			
			for(Character character : CHARS) {
				if(pattern.charAt(i) != character) {
					int distance = 1;
					
					superinner:
					for(int j = i-1; j >= 0; j--) {
						if(pattern.charAt(j) == character) {
							break superinner;
						}
						distance++;
					}
					temp.put(character, distance);
					
				}
			}
			
			badCharacter.add(temp);
		}
	}
	
	public int search(String text) {
		int indexFound = -1;
		
		int skip;
		for(int tIndex = pattern.length()-1; tIndex < text.length(); tIndex += skip) {
			
			skip = 0;
			int currentTIndex = tIndex;
			
			patternsearch:
			for(int pIndex = pattern.length()-1; pIndex >= 0; pIndex--) {
				if(pattern.charAt(pIndex) != text.charAt(currentTIndex)) {
					
					Integer badCharacterSkip = badCharacter.get(pIndex).get(text.charAt(currentTIndex));
					if(badCharacterSkip == null) {
						badCharacterSkip = pIndex + 1;
					}
					
					skip = Math.max(1, badCharacterSkip);
					break patternsearch;
				}
				currentTIndex--;
			}
			
			if(skip == 0) {
				return currentTIndex+1;
			}
			
		}
		
		return indexFound;
	}

	public List<Map<Character, Integer>> getBadCharacter() {
		return badCharacter;
	}
	
}
