package seedu.badMaths.trigograph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
class TrigoGraphAnalyserTest {
    @Test
    void negativeAmplitudeShouldReturnIllegalArgumentException(){
        String equation = "-2*sin(2*pi*x+1)+3";
        TrigoGraphAnalyser analyser = new TrigoGraphAnalyser(equation);
        assertThrows(GraphException.class,()->{
            analyser.splitAmplitudeFromTrigoEqn();
        });
    }
    @Test
    void amplitudeOfOneShouldReturnOne() throws GraphException {
        String equation = "sin(2*pi*x+1)+3";
        TrigoGraphAnalyser analyser = new TrigoGraphAnalyser(equation);
        String [] input = analyser.splitAmplitudeFromTrigoEqn();
        analyser.findAmplitude(input);
        assertEquals(1.0,analyser.getAmplitude());
    }

    @Test
    void wrongFreqFormat_withMinus_expects_NumberFormatException(){
        String eqn = "2*cos(-*x+5)-2";
        TrigoGraphAnalyser test = new TrigoGraphAnalyser(eqn);
        assertThrows(NumberFormatException.class,()->{
            test.findFreq("-*x",true);
        });
    }
}
