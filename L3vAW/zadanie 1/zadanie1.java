package zadanie1;

import java.util.Stack;

public class Solution {

	public int solution(String s) {
		Stack<Character> stack = new Stack<Character>();
		char c;
		for(int i=0; i<s.length(); i++) {
			c = s.charAt(i); 
			switch(c) {
			case '(':
			case '[':
			case '{':
				stack.push(c);
				break;
			case ')':
			case ']':
			case '}':
				if(stack.empty()) return 0;
				else if(stack.peek().equals(reverse(c))) stack.pop();
				else return 0;
				break;
			default: return 0;	
			}
		}
		
		return stack.isEmpty() ? 1 : 0;

	}
	
	public char reverse(char c) {
		switch(c) {
		case ')': return '(';
		case ']': return '[';
		case '}': return '{';
		}
		return 0;
	}

}
