package com.baijia123.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * �ļ�������
 * �ɸ���MyFileClassLoader ���ļ��ж�̬������
 * @author WangFuCai
 *
 */
public class MyFileClassLoader extends ClassLoader {
    private String classPath;

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        MyFileClassLoader fileClsLoader = new MyFileClassLoader();
        fileClsLoader.setClassPath("D:\\eclipse-workspace3\\nubia_channel\\channel-common\\target\\classes\\");
        Class cls = fileClsLoader.loadClass("com.nubia.channel.utils.TimeUtil");
        Object obj = cls.newInstance();
        Method[] mthds = cls.getMethods();
        for (Method mthd : mthds) {
            String methodName = mthd.getName();
            System.out.println("mthd.name=" + methodName);
        }
        System.out.println("obj.class=" + obj.getClass().getName());
        System.out.println("obj.class=" + cls.getClassLoader().toString());
        System.out.println("obj.class=" + cls.getClassLoader().getParent().toString());
    }

    /**
     * ���������ַ�����ָ����Ŀ¼�����࣬�����������
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] classData = null;
        try {
            classData = loadClassData(name);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return super.defineClass(name, classData, 0, classData.length);
    }

    /**
     * ���������ַ��������� byte ������
     * 
     * @param name
     *            �����ַ��� ���磺 com.cmw.entity.SysEntity
     * @return �������ļ� byte ������
     * @throws IOException
     */
    private byte[] loadClassData(String name) throws IOException {
        File file = getFile(name);
        FileInputStream fis = new FileInputStream(file);
        byte[] arrData = new byte[(int) file.length()];
        fis.read(arrData);
        return arrData;
    }

    /**
     * ���������ַ�������һ�� File ����
     * 
     * @param name
     *            �����ַ���
     * @return File ����
     * @throws FileNotFoundException
     */
    private File getFile(String name) throws FileNotFoundException {
        File dir = new File(classPath);
        if (!dir.exists())
            throw new FileNotFoundException(classPath + " Ŀ¼�����ڣ�");
        String _classPath = classPath.replaceAll("[\\\\]", "/");
        int offset = _classPath.lastIndexOf("/");
        name = name.replaceAll("[.]", "/");
        if (offset != -1 && offset < _classPath.length() - 1) {
            _classPath += "/";
        }
        _classPath += name + ".class";
        dir = new File(_classPath);
        if (!dir.exists())
            throw new FileNotFoundException(dir + " �����ڣ�");
        return dir;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }
}
