package com.baijia123.offheap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdkSerializer implements IObjectSerializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(JdkSerializer.class);

    @Override
    public <T> byte[] serialize(T obj) {
        // TODO Auto-generated method stub
        byte[] bytes = null;
        if (obj != null) {
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    GZIPOutputStream gzout = new GZIPOutputStream(bos);
                    ObjectOutputStream out = new ObjectOutputStream(gzout)) {

                out.writeObject(obj);
                bytes = bos.toByteArray();

            } catch (IOException e) {
                LOGGER.error("Jdk serialize error", e);
                throw new RuntimeException(e);
            }
        }

        return bytes;
    }

    @Override
    public <T> T deserialize(byte[] bytes) {
        // TODO Auto-generated method stub
        T obj = null;
        if (bytes != null) {
            try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                    GZIPInputStream gzin = new GZIPInputStream(bis);
                    ObjectInputStream ois = new ObjectInputStream(gzin)) {

                obj = (T) ois.readObject();

            } catch (Exception e) {
                LOGGER.error("Hessian deserialize error", e);
                throw new RuntimeException(e);
            }
        }

        return obj;
    }

}
