package com.road.test;

import com.road.utils.Roles;
import org.assertj.core.util.Files;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        Path path = Paths.get("C:\\");
        File file = new File(String.valueOf(path.resolve("abc")));
        System.out.println(file.delete());
    }
}
