package ru.avalon.j130.java;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {
    /**
     * Стандартный порт, на котором принимается файл.
     */
    public static final int SERVER_PORT = 34567;
    InetAddress address = InetAddress.getByName("localhost");
    public static final int LOCAL_PORT = 34568;

    /**
     * Стандартный размер буфера.
     */
    public static final int BUFFER_SIZE =96;
    /**
     * Стандартное расширение, которое добавляется к имени принятого файла.
     */
    private static final String SUFFIX = "-copy";

    public Main() throws UnknownHostException {
    }

    /**
     * Стартовый метод приложения.
     *
     * @param args массив аргументов командной строки.
     */
    public static void main(String[] args) throws UnknownHostException {
        System.out.println("File sending started...");
        new Main().run();
        System.out.println("File sending finished.");
    }

    private void run() {
        try (Socket s = new Socket(address, SERVER_PORT, address, LOCAL_PORT)) {

            OutputStream out = s.getOutputStream();
            File file = new File("C:\\Java\\test.txt");
            // Посылаем название файла
           // byte[] bufName = new byte[BUFFER_SIZE];
           //   String fileName = file.getName();
           //   byte[] bufName = fileName.getBytes();
            out.write(file.getName().getBytes());

            // Посылаем файл

            byte[] buf = new byte[BUFFER_SIZE];
            try (FileInputStream fis = new FileInputStream(file)) {
                int n = fis.read(buf);
                while (n != -1) {
                         out.write(buf, 0, n);
                         n = fis.read(buf);
                }

            } catch (IOException ioException) {
                ioException.printStackTrace();
              }
        }
        catch (IOException ioException) {

        }

    }
}
