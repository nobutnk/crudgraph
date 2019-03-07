package com.nobutnk.callgraph;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class ParentSearcherTest {

    @Test
    public void test() throws IOException {
        String filePath = "src/test/resources/testdata.csv";
        CallGraphParser parser = new CallGraphParser();
        Map<String, List<String>> calleeMap = parser.parse(Paths.get(filePath));
        
        ParentSearcher searcher = new ParentSearcher();
        List<String> repo1Parents = searcher.search(calleeMap, "repo1");
        
        assertThat(repo1Parents, is(Arrays.asList("f1", "f2", "f3")));
        
        List<String> repo2Parents = searcher.search(calleeMap, "repo2");
        
        assertThat(repo2Parents, is(Arrays.asList("f2", "f6", "f4")));
    }
}
