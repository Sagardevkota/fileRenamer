package com.sagardev;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        System.out.println("Welcome To File Renamer!!! Developed By SAGAR DEVKOTA \n Working Directory = " + System.getProperty("user.dir"));
        System.out.println("Enter file extension you want to rename your files to");
        Scanner scanner = new Scanner(System.in);
        String newExt = scanner.next();
        if (newExt.length() < 2) {
            System.out.println("Enter valid extension!!! \n Exiting...");
            return;
        }
        convertFilesExtensions(System.getProperty("user.dir"), newExt);
    }

    public static void convertFilesExtensions(String dir, String newExt) throws IOException {
        List<Path> pathList = Files.list(Paths.get(dir)).collect(Collectors.toList());
        pathList.stream()
                .map(path -> new File(String.valueOf(path)))
                .filter(file -> !file.isDirectory())
                .forEach(file -> {
                    int index = file.getName().lastIndexOf(".");
                    String fileName = file.getName().substring(0, index);
                    boolean renamed = file.renameTo(new File(fileName + "." + newExt));
                    if (renamed) System.out.println(fileName + " extension has been changed to "+newExt);
                });
    }


}