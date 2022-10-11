package wasm.java.sample;


import org.apache.commons.io.IOUtils;
import org.wasmer.Imports;
import org.wasmer.Instance;
import org.wasmer.Module;

import java.io.IOException;
import java.net.URL;


public class MyDemoTest {

    public static void main(String[] args) throws IOException {

        // golang 开发编译的 wasm
        URL url = MyDemoTest.class.getResource("/wasm/go/myDemo.wasm");
        byte[] bytes = IOUtils.toByteArray(url.openStream());

        org.wasmer.Module module = new Module(bytes);
        Imports imports = Imports.wasi(module);

        Instance instance = module.instantiate(imports);

        Integer result = (Integer) instance.exports.getFunction("Sum").apply(1, 2)[0];

        System.err.println(result);
        assert result == 3;

        instance.exports.getFunction("HelloWorld").apply();

        instance.close();
    }
}
