package study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.HashSet;
import java.util.Set;
import static org.assertj.core.api.Assertions.*;

class SetTest {
    private Set<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

    @Test
    void size(){
        assertThat(numbers).contains(3);
    }

    //요소가 포함 되어있는지 TEST
    @Test
    void contains() {
        assertThat(numbers).contains(1, 2, 3);
    }
    // cotains에서 ParameterizedTest을 활용하여 중복을 제거한 Test
    @ParameterizedTest
    @ValueSource(ints = {1, 2 ,3})
    void containsParameterized(int number) {
        assertThat(numbers).contains(number);
    }

    // ParameterizedTest의 csvSource를 활용하여 true, false를 Test
    @ParameterizedTest
    @CsvSource(value = {"1,true", "2,true", "5,false"})
    void containsParameterized(int number,Boolean expected) {
        boolean actual = numbers.contains(number);
        assertThat(actual).isEqualTo(expected);
    }

}
