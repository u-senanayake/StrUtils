package com.example.StrUtils;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.example.strutils.StrUtils;

public class StrUtilsTest {

	@Test
    public void testReverse() {
        assertEquals("dcba", StrUtils.reverse("abcd"));
        assertEquals("", StrUtils.reverse(""));
        assertEquals("a", StrUtils.reverse("a"));
		// assertEquals("a", "a".toLowerCase());
    }

    @Test
    public void testToTitleCase() {
        assertEquals("Hello World", StrUtils.toTitleCase("hello world"));
        assertEquals("Java Programming", StrUtils.toTitleCase("java programming"));
        assertEquals("Java", StrUtils.toTitleCase("JAVA"));
    }

    @Test
    public void testSplitAndTrim() {
		assertArrayEquals(new String[] { "a", "b", "c" }, StrUtils.splitAndTrim("a, b, c", ","));
		assertArrayEquals(new String[] { "a", "b", "c" }, StrUtils.splitAndTrim("a,b,c", ","));
		assertArrayEquals(new String[] {}, StrUtils.splitAndTrim("", ","));
    }

    @Test
    public void testJoin() {
		assertEquals("a,b,c", StrUtils.join(Arrays.asList("a", "b", "c"), ","));
		assertEquals("a, b, c", StrUtils.join(Arrays.asList("a", " b", " c"), ", "));
		assertEquals("", StrUtils.join(Arrays.asList(), ","));
    }

	@Test
	public void testSmartTruncate() {
		assertEquals("Hello...", StrUtils.smartTruncate("Hello World", 5));
		assertEquals("Hello World", StrUtils.smartTruncate("Hello World", 20));
		assertEquals("", StrUtils.smartTruncate(null, 5));
	}

	@Test
	public void testRemoveDuplicateWords() {
		assertEquals("this is a test", StrUtils.removeDuplicateWords("this is is a test test"));
		assertEquals("hello world", StrUtils.removeDuplicateWords("hello world world"));
		assertEquals("", StrUtils.removeDuplicateWords(""));
	}

	@Test
	public void testLevenshteinDistance() {
		assertEquals(0, StrUtils.levenshteinDistance("kitten", "kitten"));
		assertEquals(3, StrUtils.levenshteinDistance("kitten", "sitting"));
		assertEquals(1, StrUtils.levenshteinDistance("flaw", "flaws"));
	}

	@Test
	public void testNormalizeWhitespace() {
		assertEquals("Hello World", StrUtils.normalizeWhitespace("  Hello   World  "));
		assertEquals("", StrUtils.normalizeWhitespace(""));
		assertEquals("A B C", StrUtils.normalizeWhitespace("A   B   C"));
	}

	@Test
	public void testExtractNumbers() {
		List<String> expected = Arrays.asList("123", "456");
		assertEquals(expected, StrUtils.extractNumbers("abc 123 def 456"));
		assertEquals(Arrays.asList(), StrUtils.extractNumbers("no numbers here"));
	}

	@Test
	public void testReverseWords() {
		assertEquals("World Hello", StrUtils.reverseWords("Hello World"));
		assertEquals("Programming Java", StrUtils.reverseWords("Java Programming"));
		assertEquals("", StrUtils.reverseWords(""));
	}

	@Test
	public void testMaskSensitiveData() {
		assertEquals("Email: ***@example.com",
				StrUtils.maskSensitiveData("Email: test@example.com", "test@example.com", "***@example.com"));
		assertEquals("Credit Card: **** **** **** 1234", StrUtils.maskSensitiveData(
				"Credit Card: 1234 5678 9101 1234", "\\d{4} \\d{4} \\d{4} \\d{4}", "**** **** **** 1234"));
	}

	@Test
	public void testCharacterFrequency() {
		Map<Character, Integer> frequencyMap = StrUtils.characterFrequency("hello world");
		assertEquals(Integer.valueOf(3), frequencyMap.get('l'));
		assertEquals(Integer.valueOf(1), frequencyMap.get('h'));
		assertEquals(Integer.valueOf(1), frequencyMap.get('d'));
	}
}
