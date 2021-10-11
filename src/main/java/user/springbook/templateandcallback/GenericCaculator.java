package user.springbook.templateandcallback;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GenericCaculator {

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

    public <T> T lineReadTemplate(String filepath, GenericLineCallback<T> lineCallback, T initVal) throws IOException {
            @Override
            public <T> T doSomethingWithReader(BufferedReader bufferedReader) throws IOException {
                T sum = initVal;
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    sum = lineCallback.lineCallback(line, sum);
                }
                return sum;
            }
        });
        return result;
    }

    public String addAsString(String numFilePath) throws IOException {
        GenericLineCallback<String> callback = new GenericLineCallback<String>() {
            @Override
            public String lineCallback(String line, String res) {
                return;
            }
        };

        return lineReadTemplate(numFilePath, callback, "");

    }
}
