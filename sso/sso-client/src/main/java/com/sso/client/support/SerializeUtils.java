package com.sso.client.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by hidehai on 2015/11/3.
 */
public class SerializeUtils {
    private static Logger logger = LoggerFactory.getLogger(SerializeUtils.class);

    public SerializeUtils() {
    }

    public static Object deserialize(byte[] bytes) {
        Object result = null;
        if(isEmpty(bytes)) {
            return null;
        } else {
            try {
                ByteArrayInputStream e = new ByteArrayInputStream(bytes);

                try {
                    ObjectInputStream ex = new ObjectInputStream(e);

                    try {
                        result = ex.readObject();
                    } catch (ClassNotFoundException var5) {
                        throw new Exception("Failed to deserialize object type", var5);
                    }
                } catch (Throwable var6) {
                    throw new Exception("Failed to deserialize", var6);
                }
            } catch (Exception var7) {
                logger.error("Failed to deserialize", var7);
            }

            return result;
        }
    }

    public static boolean isEmpty(byte[] data) {
        return data == null || data.length == 0;
    }

    public static byte[] serialize(Object object) {
        byte[] result = null;
        if(object == null) {
            return new byte[0];
        } else {
            try {
                ByteArrayOutputStream ex = new ByteArrayOutputStream(128);

                try {
                    if(!(object instanceof Serializable)) {
                        throw new IllegalArgumentException(SerializeUtils.class.getSimpleName() + " requires a Serializable payload " + "but received an object of type [" + object.getClass().getName() + "]");
                    }

                    ObjectOutputStream ex1 = new ObjectOutputStream(ex);
                    ex1.writeObject(object);
                    ex1.flush();
                    result = ex.toByteArray();
                } catch (Throwable var4) {
                    throw new Exception("Failed to serialize", var4);
                }
            } catch (Exception var5) {
                logger.error("Failed to serialize", var5);
            }

            return result;
        }
    }
}
