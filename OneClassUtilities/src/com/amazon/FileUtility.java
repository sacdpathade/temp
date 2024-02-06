package com.amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * 
 * Implement UNIX find command as an API. The API will support finding:

Files that have a given size requirement.
Files with a certain naming pattern.

Focus on 2 uses cases at first:

Find all files over 5 MB somewhere under a directory.
Find all XML files somewhere under a directory.

Create a library that lets me do this easily. Keep in mind that these are just 2 use cases and that the library should be flexible. 
Asking a new question, such as finding all pdf files modified less then 2 days ago, shouldn't be too difficult.

Design the classes and/or interfaces.
Implement the core components.
Write out the code snippets showing how you would use this library.
*/

class File {
    boolean isDir;
    String name;
    long size;
    List<File> children;
}

enum Condition {
    MIN_SIZE, NAME, MAX_SIZE
}

public class FileUtility {
    
    public boolean isFileConditionValid(File file, Condition condition, Object value) {
        if(condition == Condition.MIN_SIZE) {
            long minSize = Long.parseLong(value.toString());
            return file.size > minSize;
        } else if(condition == Condition.NAME) {
            return Pattern.matcher(value, file.name);
        } else {
            return false;
        }
    }
    
    public List<File> findBySize(File base, Map<Condition, Object> conditionMap) {
        List<File> result = new ArrayList<>();
        if(!base.isDir) {
            
            for(Condition key : conditionMap.keySet()) {
                if(!isFileConditionValid(base, key, conditionMap.get(key))) {
                    return result;
                }
            }
            
            result.add(base);
            return result;
        }
        
        
        for(int index=0; index<base.children; index++) {
            result.addAll(findBySize(base.children.get(index), minSize));
        }
        
        return result;
        
    }
}

