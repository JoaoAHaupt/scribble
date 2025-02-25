package com.joaoahaupt.model.config;

import java.util.HashMap;
import java.util.Map;

public class UserMemorySave {

    private static final Map<Long, Long> selectedFolders = new HashMap<>();
    private static final Map<Long, String> tagsFolders = new HashMap<>();

    public static void insertTag(Long id, String tag){
        tagsFolders.put(id, tag);
    }

    public static void deleteTag(Long id){
        tagsFolders.remove(id);
    }

    public static void insertFolder(Long id, Long idFolder){
        selectedFolders.put(id, idFolder);
    }

    public static void deleteFolder(Long id){
        selectedFolders.remove(id);
    }


}
