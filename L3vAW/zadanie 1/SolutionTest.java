package zadanie1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SolutionTest {

	Solution solution;
    @BeforeEach
    void setUp() {
        solution = new Solution();
    }

    @Test
    void solutionTest1() {
        assertEquals(1, solution.solution("{}"));
        assertEquals(1, solution.solution("[]"));
        assertEquals(1, solution.solution("()"));
        assertEquals(1, solution.solution("{[()]}"));
        assertEquals(1, solution.solution(""));
        assertEquals(1, solution.solution("(((((())))))"));
        assertEquals(1, solution.solution("[{}{}()[]]"));

    }

    @Test
    void solutionTest2() {
        assertEquals(1, solution.solution("(((((())))))"));
        assertEquals(1, solution.solution("{{{{{{{{{{{}}}}}}}}}}}"));
        assertEquals(1, solution.solution("[[[[[[[[[[]]]]]]]]]]"));
    }

    @Test
    void solutionTest3() {
        String s="";
        for(int i=0; i<50000; i++)
        {
            s+="{(";
        }
        for(int i=0; i<50000; i++)
        {
            s+=")}";
        }
        assertEquals(1, solution.solution(s));
    }

    @Test
    void solutionTest4() {
        assertEquals(0, solution.solution("AiSD"));
        assertEquals(0, solution.solution("{(})[]"));
        assertEquals(0, solution.solution("{([]"));
        assertEquals(0, solution.solution("{{})"));
        assertEquals(0, solution.solution("}{"));
        assertEquals(0, solution.solution("("));
        assertEquals(0, solution.solution(")"));
        assertEquals(0, solution.solution("((][)"));
    }
}
