package wasm.java.sample;


import org.apache.commons.io.IOUtils;
import org.wasmer.Instance;

import java.io.IOException;
import java.net.URL;


public class SimpleTest {

    public static void main(String[] args) throws IOException {
        URL url = SimpleTest.class.getResource("/wasm/simple.wasm");
        byte[] bytes = IOUtils.toByteArray(url.openStream());

        Instance instance = new Instance(bytes);

        Integer result = (Integer) instance.exports.getFunction("sum").apply(1, 2)[0];

        System.out.println(result);
        assert result == 3;

        instance.close();
    }
}
