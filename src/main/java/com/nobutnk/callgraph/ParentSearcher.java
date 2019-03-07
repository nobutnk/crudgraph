package com.nobutnk.callgraph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParentSearcher {
    
    public List<String> search(Map<String, List<String>> calleeMap, String methodName) {
        
        List<String> callerList = calleeMap.get(methodName);
        if (callerList == null) {
            return callerList;
        }
        
        List<String> parentList = new ArrayList<>();
        for (String caller : callerList) {
            sub(calleeMap, caller, parentList);
        }
        
        return parentList;
    }
    
    private void sub(Map<String, List<String>> calleeMap, String method, List<String> parentList) {
        List<String> children = calleeMap.get(method);
        if (children == null) {
            return;
        } else {
            for (String c : children) {
                if (c.startsWith("f") && !parentList.contains(c)) {
                    parentList.add(c);
                } else {
                    sub(calleeMap, c, parentList);
                }
            }
            return;
        }
    }

}
