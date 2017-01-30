package com;

import com.dispatcher.Dispatcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main implements Runnable  {

    private static final String QUIT = "quit";

    private static BufferedReader in;
    private static PrintWriter out;


    public Main(BufferedReader in, PrintWriter out) {
        this.in = in;
        this.out = out;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        new Main(in, out).run();
    }
    public void run() {
        while (true) {
            out.print("> ");
            out.flush();
            String command;
            try {
                command = in.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (command.equals(QUIT)) {
                break;
            }
            Dispatcher.execute(command);
        }
    }
    public static BufferedReader getIn() {
        return in;
    }

    public static PrintWriter getOut() {
        return out;
    }

}
