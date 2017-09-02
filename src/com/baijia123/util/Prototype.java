package com.baijia123.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Prototype implements Cloneable, Serializable {

    public static class SerializableObject implements Serializable {

        private Integer id;
        private String name;

        public SerializableObject(Integer id, String name) {
            super();
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        /**
         * 
         */
        private static final long serialVersionUID = 1L;

    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private String string;
    private SerializableObject object;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public SerializableObject getObject() {
        return object;
    }

    public void setObject(SerializableObject object) {
        this.object = object;
    }

    /**
     * 深复制
     * 
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public Object deepClone() throws IOException, ClassNotFoundException {
        /* 写入当前对象的二进制流 */
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        /* 读出二进制流产生的新对象 */
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return ois.readObject();
    }

    public static void main(String[] args) throws CloneNotSupportedException, ClassNotFoundException, IOException {
        SerializableObject so = new SerializableObject(3, "test");
        Prototype p = new Prototype();
        p.setObject(so);
        p.setString("type");

        Prototype shallowCopy = (Prototype) p.clone();
        shallowCopy.getObject().setName("test1");
        System.out.println("shallow so get name = " + shallowCopy.getObject().getName());
        System.out.println("shallow so new name = " + so.getName());

        Prototype deepCopy = (Prototype) p.deepClone();
        deepCopy.getObject().setName("test2");
        System.out.println("deep so get name = " + deepCopy.getObject().getName());
        System.out.println("deep so new name = " + so.getName());
    }
}
