package com.joaoahaupt.model.config;

import com.joaoahaupt.model.Annotation;

import java.util.HashMap;
import java.util.Map;

public class UserMemorySave {

    private static final Map<Long, Annotation> annotations = new HashMap<>();

    public static Annotation selectAnnotation(Long id){
        return annotations.get(id);
    }

    public static void insertAnnotation(Long id, Annotation tag){
        annotations.put(id, tag);
    }

    public static void deleteAnnotation(Long id){
        annotations.remove(id);
    }


}
