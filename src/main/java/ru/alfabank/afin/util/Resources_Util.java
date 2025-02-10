package ru.alfabank.afin.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Resources_Util {
    //Папка для временного хранения данных
    public static final String TMF = "tmp";
    public String USEFUL_PATH = "path";

    /**
     * Метод, который обрабатывает путь
     *
     * @param p = путь, который нужно обработать
     * @return p
     */
    public static String safePath(String p) {
        p = p.replace("\\", File.pathSeparator);
        if (p.startsWith("/") || p.startsWith("\\")) {
            p = p.substring(1);
        }

        if (p.endsWith("/")) {
            p = p.substring(0, p.length() - 1);
        }
        return p;
    }

    public static List<String> safePaths(List<String> patsh) {
        ArrayList<String> safePaths = null;
        for (int i = 0; i < patsh.size(); i++) {
            safePaths.add(safePath(patsh.get(0)));
        }
        return safePaths;
    }

    private static String GetRelativePath(String folderPath, String fileName) {
        //обработать путь к файлу, удалив лишние слэши
        String safeFilename = fileName.replace("/", "").replace("\\", "");
        return String.format("%s/%s", safePath(folderPath), safeFilename);
    }

    /**
     * Получение пути к ресурсу
     *
     * @param folderPath
     * @param fileName
     * @return
     */
    public static String getRp(String folderPath, String fileName) {
        //обработать путь к файлу, удалив лишние слэши
        String safeFilename = fileName.replace("/", "").replace("\\", "");
        String relativePath = GetRelativePath(safePath(folderPath), safeFilename);
        URL resource = Resources_Util.class.getClassLoader().getResource(relativePath);
        return Objects.requireNonNull(resource).getPath();
    }

    public static File getResource(String folderPath, String fileName) {
        //определить путь к ресурсу по заданным folderPath и fileName
        String safeFilename = fileName.replace("/", "").replace("\\", "");
        String relativePath = GetRelativePath(safePath(folderPath), safeFilename);
        URL resource = Resources_Util.class.getClassLoader().getResource(relativePath);
        String resourcePath = Objects.requireNonNull(resource).getPath();
        return new File(resourcePath);
    }

    public static void clearTmpFolder() {
        String tmpPath = String.format("%s/build/resources/test/%s", System.getProperty("user.dir"), TMF);
        File tmp = new File(tmpPath);
        if (!tmp.exists()) {
            FileUtil.deleteDir(tmp);
        }
    }
}





