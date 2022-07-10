package com.txservices.swissbackend.parsing;

import com.txservices.swissbackend.exception.ConnectionException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import static org.junit.Assert.*;

@SpringBootTest
public class TimeParserTest {

    @Autowired
    TimeParser timeParser;

    @Test
    public void testGetDuration() {
        String input1 = "00d03:10:20";
        String expectedDuration1 = "Days:0, Hours:3, Minutes:10, Seconds:20";
        String actualDuration1 = timeParser.getDuration(List.of(input1));
        assertEquals(expectedDuration1, actualDuration1);

        String input2 = "00d05:30:20";
        String expectedDuration2 = "Days:0, Hours:4, Minutes:20, Seconds:20"; //average given two durations
        String actualDuration2 = timeParser.getDuration(List.of(input1,input2));
        assertEquals(expectedDuration2, actualDuration2);
    }

}
