package by.clevertec.config;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;




    @Component
    public class DataInputStreamReader {


        public static String getString(InputStream inputStream) {
            try(InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {

                StringBuilder sb = new StringBuilder();
                int c;
                while ((c = reader.read()) != -1) {
                    sb.append((char) c);
                }
                return sb.toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                closeInputStream(inputStream);
            }
        }

        private static void closeInputStream(InputStream is) {
            try {
                is.close();
            } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }

}
