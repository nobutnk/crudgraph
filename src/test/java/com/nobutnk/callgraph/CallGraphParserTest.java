package com.nobutnk.callgraph;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class CallGraphParserTest {
    
    @Test
    public void test() throws IOException {
        
        String filePath = "src/test/resources/testdata.csv";
        CallGraphParser parser = new CallGraphParser();
        Map<String, List<String>> calleeMap = parser.parse(Paths.get(filePath));
        
        List<String> callList = calleeMap.get("repo1");
        List<String> expected = new ArrayList<>();
        expected.add("e");
        expected.add("e1");
        expected.add("n");
        expected.add("h");
        
        
        assertThat(callList, is(expected));
        
        callList = calleeMap.get("repo2");
        expected = new ArrayList<>();
        expected.add("w");
        expected.add("n2");
        expected.add("e2");
        
        assertThat(callList, is(expected));
    }

}
