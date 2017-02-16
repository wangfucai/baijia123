package com.baijia123.offheap;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;

public class Hessian2Serializer implements IObjectSerializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Hessian2Serializer.class);

    @Override
    public <T> byte[] serialize(T obj) {
        // TODO Auto-generated method stub
        if (obj != null) {
            try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {

                Hessian2Output out = new Hessian2Output(os);
                out.writeObject(obj);
                out.close();
                return os.toByteArray();

            } catch (IOException e) {
                LOGGER.error("Hessian serialize error ", e);
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    @Override
    public <T> T deserialize(byte[] bytes) {
        // TODO Auto-generated method stub
        if (bytes != null) {
            try (ByteArrayInputStream is = new ByteArrayInputStream(bytes)) {

                Hessian2Input in = new Hessian2Input(is);
                T obj = (T) in.readObject();
                in.close();

                return obj;

            } catch (IOException e) {
                LOGGER.error("Hessian deserialize error ", e);
                throw new RuntimeException(e);
            }
        }
        return null;
    }

}
