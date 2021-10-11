package user.springbook.templateandcallback;

import java.io.BufferedReader;
import java.io.IOException;

public interface GenericBufferedReaderCallback<T> {
    String doSomethingWithReader(BufferedReader bufferedReader) throws IOException;
}
