package user.springbook.templateandcallback;

import java.io.BufferedReader;

public interface GenericLineCallback<T> {
    T lineCallback(String line, T res);
}
