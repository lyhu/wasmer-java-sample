package wasm.java.sample;

import org.apache.commons.io.IOUtils;
import org.wasmer.Instance;
import org.wasmer.Memory;

import java.io.IOException;
import java.net.URL;
import java.nio.ByteBuffer;

class MemoryTest {
    public static void main(String[] args) throws IOException {


        URL url = MyDemoTest.class.getResource("/wasm/memory.wasm");
        byte[] bytes = IOUtils.toByteArray(url.openStream());

        Instance instance = new Instance(bytes);
        Integer pointer = (Integer) instance.exports.getFunction("return_hello").apply()[0];

        Memory memory = instance.exports.getMemory("memory");

        ByteBuffer memoryBuffer = memory.buffer();

        byte[] data = new byte[13];
        memoryBuffer.position(pointer);
        memoryBuffer.get(data);

        String result = new String(data);
        System.out.println(result);

        assert result.equals("Hello, World!");

        instance.close();
    }
}
