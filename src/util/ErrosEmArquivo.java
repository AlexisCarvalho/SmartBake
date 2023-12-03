package util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class ErrosEmArquivo {

    String nomeArquivoLog;
    FileOutputStream fileOutputStream;
    PrintStream logPrintStream;

    public ErrosEmArquivo() {
    }

    public void ativarSaidaErrosArquivo() {

        nomeArquivoLog = "log.txt";

        try {
            fileOutputStream = new FileOutputStream(nomeArquivoLog, true);
            logPrintStream = new PrintStream(fileOutputStream);
            System.setErr(logPrintStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}