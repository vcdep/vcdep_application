package test.java.unit;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestJUnit
{

    @Test
    public void testUnit()
    {
        String hello = "Hello";

        assertEquals("Hello", hello);
    }

}