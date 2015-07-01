package com.griddynamics.devschool.shop;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * @author Sergey Korneev
 */
public class AbstractConsoleTest {
    protected ByteArrayOutputStream out;
    protected ByteArrayInputStream in;

    protected void mockSystemOut() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    protected void mockSystemIn(String args) {
        in = new ByteArrayInputStream(args.getBytes());
        System.setIn(in);
    }

    protected String[] getSystemOutContent() throws IOException {
        out.flush();
        String whatWasPrinted = new String(out.toByteArray());
        return whatWasPrinted.split(System.getProperty("line.separator"));
    }
}
