package user.springbook.templateandcallback;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {

    public Integer fileReadTemplate(String filepath, BufferedReaderCallback callback) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filepath));
            int ret = callback.doSomethingWithReader(br);
            return ret;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public Integer lineReadTemplate(String filepath, LineCallback lineCallback, Integer initVal) throws IOException {
        Integer result = fileReadTemplate(filepath, new BufferedReaderCallback() {
            @Override
            public Integer doSomethingWithReader(BufferedReader bufferedReader) throws IOException {
                Integer sum = initVal;
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    sum = lineCallback.lineCallback(line, sum);
                }
                return sum;
            }
        });
        return result;
    }

    public int calcSum(String filepath) throws IOException {
        Integer sum = lineReadTemplate(
                filepath
                , new LineCallback() {
                    @Override
                    public Integer lineCallback(String line, Integer res) {
                        return Integer.parseInt(line) + res;
                    }
                }
                , 0
        );
        return sum;
    }

    public int calcMultiply(String numFilePath) throws IOException {
        Integer multiplication = lineReadTemplate(
                numFilePath
                , new LineCallback() {
                    @Override
                    public Integer lineCallback(String line, Integer res) {
                        return Integer.parseInt(line) * res;
                    }
                }
                , 1
        );
        return multiplication;
    }

}
