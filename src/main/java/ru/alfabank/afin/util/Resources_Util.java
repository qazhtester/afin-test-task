package ru.alfabank.afin.util;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Resources_Util {
    //Папка для временного хранения данных
    public static final String TMF = "tmp";

    // Рекомендую удалить т.к. переменная не используется
    public String USEFUL_PATH = "path";

    /**
     * Метод, который обрабатывает путь
     *
     * @param p = путь, который нужно обработать
     * @return p
     */
    public static String safePath(String p) {
        // Заменить File.pathSeparator на File.separator.
        // File.pathSeparator - используется для разделения путей в переменной окружения PATH, а не частей пути к файлу

        p = p.replace("\\", File.pathSeparator);
        if (p.startsWith("/") || p.startsWith("\\")) {
            p = p.substring(1);
        }

        if (p.endsWith("/")) {
            p = p.substring(0, p.length() - 1);
        }
        return p;
    }

    // если метод не используется - удалить
    public static List<String> safePaths(List<String> patsh) {
        // инициализировать safePaths иначе NullPointerException

        ArrayList<String> safePaths = null;
        // заменить 0 на i ри добавлении элемента

        for (int i = 0; i < patsh.size(); i++) {
            safePaths.add(safePath(patsh.get(0)));
        }
        return safePaths;
    }

    // исправить заглавную букву в названии метода
    private static String GetRelativePath(String folderPath, String fileName) {
        // Переписать логику метода -> оздать объект File для безопасной работы с путями, сейчас
        // замена всех "/" и "\" может удалить вложенные директории из fileName

        //обработать путь к файлу, удалив лишние слэши
        String safeFilename = fileName.replace("/", "").replace("\\", "");
        return String.format("%s/%s", safePath(folderPath), safeFilename);
    }

    // метод не используется
    /**
     * Получение пути к ресурсу
     *
     * @param folderPath
     * @param fileName
     * @return
     */
    public static String getRp(String folderPath, String fileName) {
        // см. комментарий в GetRelativePath

        //обработать путь к файлу, удалив лишние слэши
        String safeFilename = fileName.replace("/", "").replace("\\", "");
        String relativePath = GetRelativePath(safePath(folderPath), safeFilename);
        URL resource = Resources_Util.class.getClassLoader().getResource(relativePath);
        return Objects.requireNonNull(resource).getPath();
    }

    // снова не используемый метод
    public static File getResource(String folderPath, String fileName) {
        //определить путь к ресурсу по заданным folderPath и fileName
        String safeFilename = fileName.replace("/", "").replace("\\", "");
        String relativePath = GetRelativePath(safePath(folderPath), safeFilename);
        URL resource = Resources_Util.class.getClassLoader().getResource(relativePath);

        // здесь я бы скореё всего посоветовался с коллегами, т.к. в своей практике не сталкивался
        // но судя по найденной информации .getPath() может некорректно работать с пробелами.
        // в связи с чем рекомендуется использовать resource.toURI().getPath()

        String resourcePath = Objects.requireNonNull(resource).getPath();
        return new File(resourcePath);
    }

    // опять не используемый метод
    public static void clearTmpFolder() {
        // "build/resources/test" - лучше вынести в константу (лично моё мнение, данных дополнений в скобках
        // на реальном ревью я конечно же не оставляю)

        String tmpPath = String.format("%s/build/resources/test/%s", System.getProperty("user.dir"), TMF);
        File tmp = new File(tmpPath);
        // неверное условие

        if (!tmp.exists()) {
            FileUtil.deleteDir(tmp);
        }
    }
}





