package gov.nasa.pds.services.impl;

import java.io.IOException;
import java.io.Reader;

public class LineReader {
    private Reader in;
    int maxCharacters;

    private char cb[];
    private int nChars, nextChar;

    /** If the next character is a line feed, skip it */
    private boolean skipLF = false;

    private static int defaultExpectedLineLength = 80;

    /**
     * Creates a buffering character-input stream that uses an input buffer of the specified size.
     *
     * @param in
     *            a Reader
     * @param maxCharacters
     *            the maximum number of characters to read
     *
     * @exception IllegalArgumentException
     *                if in == null or bytesToRead is <= 0
     */
    public LineReader(Reader in, int maxCharacters) {
        if (in == null) {
            throw new IllegalArgumentException("in == null");
        }
        if (maxCharacters <= 0) {
            throw new IllegalArgumentException("maxCharacters <= 0");
        }
        this.in = in;
        cb = new char[maxCharacters];
        this.maxCharacters = maxCharacters;

        nextChar = 0;
        nChars = 0;
    }

    private void fill() throws IOException {
        int n;
        do {
            n = in.read(cb, 0, maxCharacters);
        } while (n == 0);
        nextChar = 0;
        nChars = n;
    }

    /**
     * Reads a line of text. A line is considered to be terminated by any one of a line feed ('\n'), a carriage return
     * ('\r'), or a carriage return followed immediately by a linefeed.
     *
     * @return A String containing the contents of the line, not including any line-termination characters, or null if
     *         the end of the stream has been reached
     *
     * @exception IOException
     *                If an I/O error occurs
     */
    public String readLine() throws IOException {
        StringBuilder s = null;
        int startChar;
        boolean omitLF = skipLF;

        if (nChars == 0) {
            fill();
        }

        for (;;) {
            if (nextChar >= nChars) { /* EOF */
                if (s != null && s.length() > 0)
                    return s.toString();
                else
                    return null;
            }
            boolean eol = false;
            char c = 0;
            int i;

            /* Skip a leftover '\n', if necessary */
            if (omitLF && (cb[nextChar] == '\n'))
                nextChar++;
            skipLF = false;
            omitLF = false;

            charLoop: for (i = nextChar; i < nChars; i++) {
                c = cb[i];
                if ((c == '\n') || (c == '\r')) {
                    eol = true;
                    break charLoop;
                }
            }

            startChar = nextChar;
            nextChar = i;

            if (eol) {
                String str;
                if (s == null) {
                    str = new String(cb, startChar, i - startChar);
                } else {
                    s.append(cb, startChar, i - startChar);
                    str = s.toString();
                }
                nextChar++;
                if (c == '\r') {
                    skipLF = true;
                }
                return str;
            }

            if (s == null)
                s = new StringBuilder(defaultExpectedLineLength);
            s.append(cb, startChar, i - startChar);
        }
    }

    public void close() throws IOException {
        if (in == null)
            return;
        in.close();
        in = null;
        cb = null;
    }
}
