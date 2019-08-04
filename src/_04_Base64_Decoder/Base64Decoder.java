package _04_Base64_Decoder;

import java.io.BufferedReader;
import java.io.FileReader;

public class Base64Decoder {
	public static void main(String[] args) {
		//convertBase64Char('e');
		convert4CharsTo24Bits("////");
	}
	/*
	 * Base 64 is a way of encoding binary data using text.
	 * Each number 0-63 is mapped to a character. 
	 * NOTE: THIS IS NOT THE SAME AS ASCII OR UNICODE ENCODING!
	 * Since the numbers 0 through 63 can be represented using 6 bits, 
	 * every four (4) characters will represent twenty four (24) bits of data.
	 * 4 * 6 = 24
	 * 
	 * For this exercise, we won't worry about what happens if the total bits being converted
	 * do not divide evenly by 24.
	 * 
	 * If one char is 8 bits, is this an efficient way of storing binary data?
	 * (hint: no)
	 * 
	 * It is, however, useful for things such as storing media data inside an HTML file (for web development),
	 * so that way a web site does not have to look for an image, sound, library, or whatever in a separate location.
	 * 
	 * View this link for a full description of Base64 encoding
	 * https://en.wikipedia.org/wiki/Base64
	 */
	
	
	final static char[] base64Chars = {
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
		'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
		'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 
		'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
	};
	
	//1. Complete this method so that it returns the the element in
	//   the base64Chars array that corresponds to the passed in char.
	public static byte convertBase64Char(char c){
		byte a = 0;
		for (byte i = 0; i < base64Chars.length; i++) {
			if(base64Chars[i] == c) {
				a = i;
			}
		}
	
		return a;
	}
	
	//2. Complete this method so that it will take in a string that is 4 
	//   characters long and return an array of 3 bytes (24 bits). The byte 
	//   array should be the binary value of the encoded characters.
	public static byte[] convert4CharsTo24Bits(String s){
		byte[] hey = {0b000000, 0b000000, 0b000000};
		if(s.length()==4) {
			for (int i = 0; i < hey.length; i++) {
				String a = String.format("%8s", (Integer.toBinaryString(convertBase64Char(s.charAt(i))))).replace(' ', '0');
				byte firstByte = (byte) Integer.parseInt(a);
				System.out.println("A" + a);
				String b = String.format("%8s", (Integer.toBinaryString(convertBase64Char(s.charAt(i+1))))).replace(' ', '0');
				byte secondByte = (byte) Integer.parseInt(b);
				System.out.println("B " + b);
				//byte ab = (Integer.toBinaryString(s.charAt((convertBase64Char(s.charAt(i+1))))));
				
				//System.out.println("B" + ab);
				secondByte = (byte)(secondByte<<6);System.out.println(secondByte);
				secondByte = (byte) (secondByte&(1<<24)-1);System.out.println(secondByte);
				byte secondByteShifted = (byte)Integer.parseInt(String.format("%8s", (Integer.toBinaryString(secondByte).replace(' ', '0'))));
	
				System.out.println("second byte " + Integer.toBinaryString(secondByte));
				byte finalByte = (byte) (firstByte|secondByte);
				System.out.println(Integer.toBinaryString(finalByte));
				System.out.println("final byte " + finalByte);
	
				hey[i]=finalByte;
				System.out.println("hey array " + hey[i]);
				
			}
		}else{
			return hey;
		}
		return hey;
	}
	
	//3. Complete this method so that it takes in a string of any length
	//   and returns the full byte array of the decoded base64 characters.
	public static byte[] base64StringToByteArray(String file) {
		return null;
	}
}
