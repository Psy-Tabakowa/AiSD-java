package Test;

import Program.Solution;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    Solution solut;

    @BeforeEach
    void setUp() {
        solut = new Solution();
    }

    @Test
    void solutionTest() {
        assertEquals(1, solut.solution("{}"));
        assertEquals(1, solut.solution("{[()]}"));
        assertEquals(1, solut.solution(""));
        assertEquals(1, solut.solution("(((((())))))"));
        String s = "";
        for (int i = 0; i < 50000; i++) {
            s += "{(";
        }
        for (int i = 0; i < 50000; i++) {
            s += ")}";
        }
        assertEquals(1, solut.solution(s));
        assertEquals(1, solut.solution("[{}{}()[]]"));
        s = "";
        for (int i = 0; i < 50001; i++) {
            s += "{(";
        }
        for (int i = 0; i < 50001; i++) {
            s += ")}";
        }
        assertEquals(0, solut.solution(s));
        assertEquals(0, solut.solution("jmo niiobcaeinn123124.,"));
        assertEquals(0, solut.solution("{(})[]"));
        assertEquals(0, solut.solution("}{"));
        assertEquals(0, solut.solution("(("));
    }
}