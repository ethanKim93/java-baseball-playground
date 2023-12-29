package study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class StringTest {
    @Test
    void replace() {
        String actual = "abc".replace("b", "d");
        assertThat(actual).isEqualTo("adc");
    }

    @Test
    void split() {
        String[] actual1 = "1,2".split(",");
        String[] actual2 = "1".split(",");
        String[] result1 = {"1", "2"};
        String[] result2 = {"1"};

        assertThat(actual1).contains(result1);
        assertThat(actual2).contains(result2);

        assertThat(actual1).containsExactly(result1);
        assertThat(actual2).containsExactly(result2);

//        Assertions.assertArrayEquals(result,actual);
    }

    @Test
    void subString() {
        String str = "(1,2)";
        String actual = str.substring(str.indexOf("(") + 1, str.indexOf(")"));

        assertThat(actual).isEqualTo("1,2");
    }

    // String에서 index 범위 에러 확인
    @DisplayName("String Out of Memory Test")
    @Test
    void charAt() {
        String str = "abc";

        //assertThatThrownBy를 사용한 방법
        assertThatThrownBy(() -> str.charAt(10))
                .isInstanceOf(StringIndexOutOfBoundsException.class)
                .hasMessageContaining("out of range: 10");

        //assertThatExceptionOfType을 사용한 방법
        assertThatExceptionOfType(StringIndexOutOfBoundsException.class).isThrownBy(() -> {
            str.charAt(10);
        }).withMessageMatching("String index out of range: 10");
    }

}
