package com.nobutnk.callgraph;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class CallGraphParser {

    public Map<String, List<String>> parse(Path path) throws IOException {
        Map<String, List<String>> calleeMap = new HashMap<>();
        try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
            stream.forEach(line -> {
                String[] data = line.split(",");
                int remain = data.length;
                while (remain >= 2) {
                    String callee = data[remain - 1];
                    String call = data[remain - 2];
                    List<String> callList = calleeMap.get(callee);
                    if (callList == null || callList.isEmpty()) {
                        callList = new ArrayList<>();
                    }
                    if (!callList.contains(call)) {
                        callList.add(call);
                        calleeMap.put(callee, callList);
                    }
                    remain--;
                }
            });
        }
        
        return calleeMap;
    }
}
