package projectx.problem;

import com.google.common.collect.Lists;
import com.sun.corba.se.spi.orbutil.closure.Closure;
import org.junit.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mihails Volkovs on 2015.02.09.
 */
public class Problem45 {

    public static final void main(String... args) {
        Value nTriangle = new Value(1, 1, n -> triangle(n));
        Value nPentagonal = new Value(1, 1, n -> pentagonal(n));
        Value nHexagonal = new Value(1, 1, n -> hexagonal(n));
        List<Value> values = Lists.newArrayList(nTriangle, nPentagonal, nHexagonal);
        int counter = 0;
        while (counter < 3) {

            // increment the smallest value
            Collections.sort(values);
            Iterator<Value> iterator = values.iterator();
            Value minimumValue = iterator.next();
            Value next = iterator.next();
            Value last = iterator.next();
            System.out.println(minimumValue + ", " + next + ", " + last);

            // comparing
            if (minimumValue.equals(next) && next.equals(last)) {
                System.out.println(String.format("Found the same number: %s, %s, %s", minimumValue, next, last));
                counter++;
            }
            minimumValue.increment();

        }
    }

    private static long triangle(long n) {
        return n * (n + 1) / 2;
    }

    private static long pentagonal(long n) {
        return n * (3 * n - 1) / 2;
    }

    private static long hexagonal(long n) {
        return n * (2* n - 1);
    }

    private static class Value implements Comparable<Value> {

        private long n;

        private long value;

        private Function<Long, Long> function;

        public Value(long n, long value, Function<Long, Long> function) {
            this.n = n;
            this.value = value;
            this.function = function;
        }

        @Override
        public int compareTo(Value value) {
            return (int) (this.value - value.value);
        }

        @Override
        public boolean equals(Object o) {
            return value == ((Value)o).value;
        }

        @Override
        public String toString() {
            return String.format("f(%s)=%s", n, value );
        }

        public void increment() {
            n++;
            value = function.apply(n);
        }
    }

    @Test
    public void triangle() {
        assertEquals(1, triangle(1));
        assertEquals(3, triangle(2));
        assertEquals(6, triangle(3));
        assertEquals(10, triangle(4));
        assertEquals(15, triangle(5));
        assertEquals(40755, triangle(285));
    }

    @Test
    public void pentagonal() {
        assertEquals(1, pentagonal(1));
        assertEquals(5, pentagonal(2));
        assertEquals(12, pentagonal(3));
        assertEquals(22, pentagonal(4));
        assertEquals(35, pentagonal(5));
        assertEquals(40755, pentagonal(165));
    }

    @Test
    public void hexagonal() {
        assertEquals(1, hexagonal(1));
        assertEquals(6, hexagonal(2));
        assertEquals(15, hexagonal(3));
        assertEquals(28, hexagonal(4));
        assertEquals(45, hexagonal(5));
        assertEquals(40755, hexagonal(143));
    }

}
