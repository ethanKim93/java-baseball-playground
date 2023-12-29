import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import service.StringCalculator;

import java.util.Queue;

import static org.assertj.core.api.Assertions.assertThat;

class CalculatorTest {

    StringCalculator calculator;

    public void CalculatorTest(){
        calculator = new StringCalculator();
    }
    @ParameterizedTest
    @CsvSource(value = {"1,2"})
    void add(double val1 , double val2) {
        double result = calculator.add(val1,val2);
        assertThat(result).isEqualTo(3.0);
    }

    @ParameterizedTest
    @CsvSource(value = {"5,2"})
    void subtract(double val1 , double val2) {
        double result = calculator.subtract(val1,val2);
        assertThat(result).isEqualTo(3.0);
    }

    @ParameterizedTest
    @CsvSource(value = {"1.5,2"})
    void multiply(double val1 , double val2) {
        double result = calculator.multiply(val1,val2);
        assertThat(result).isEqualTo(3.0);
    }

    @ParameterizedTest
    @CsvSource(value = {"6,2"})
    void divide(double val1 , double val2) {
        double result = calculator.divide(val1,val2);
        assertThat(result).isEqualTo(3.0);
    }

    @ParameterizedTest
    @CsvSource(value = {"1 + 2 - 2"})
    void calculater(String inputStr ) {
        Queue<String> calData = calculator.parseString(inputStr);

        double result = calculator.calculate(calData);
        assertThat(result).isEqualTo(1.0);
    }
}