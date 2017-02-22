package com.baijia123.ldap;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.SortControl;

public class LdapHelper {
    private static LdapContext ctx;

    public static LdapContext getCtx() {
        // 用户名
        // String account = "rms&cdc@nubia.cn";
        // 密码
        // String password = "rmsCDC#147369";

        String account = "0016001400@nubia.cn";
        String password = "XiaoZhong860722";

        // AD的root目录
        String root = "OU=Nubia,DC=nubia,DC=cn";
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, "ldap://10.206.2.6:389/" + root);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, account);
        env.put(Context.SECURITY_CREDENTIALS, password);
        try {
            // 链接ldap
            ctx = new InitialLdapContext(env, null);
            System.out.println("认证成功");
        } catch (javax.naming.AuthenticationException e) {

            System.out.println("123 = " + e.getMessage());
            System.out.println("认证失败");
        } catch (Exception e) {
            System.out.println("认证出错：");
            e.printStackTrace();
        }
        return ctx;
    }

    // 查询
    public static void testSearch() throws Exception {
        LdapContext ctx = getCtx();
        // 设置过滤条件
        String uid = "0016001400";
        String filter = "(&(objectClass=top)(objectClass=organizationalPerson)(sAMAccountName=" + uid + "))";
        // 限制要查询的字段内容
        // String[] attrPersonArray = { "uid", "userPassword", "displayName", "cn", "sn", "mail", "description" };
        // String[] attrPersonArray = { "sAMAccountName", "name", "sn"};
        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        // 设置将被返回的Attribute
        // searchControls.setReturningAttributes(attrPersonArray);
        // 三个参数分别为：
        // 上下文；
        // 要搜索的属性，如果为空或 null，则返回目标上下文中的所有对象；
        // 控制搜索的搜索控件，如果为 null，则使用默认的搜索控件
        NamingEnumeration<SearchResult> answer = ctx.search("", filter.toString(), searchControls);
        // 输出查到的数据
        while (answer.hasMore()) {
            SearchResult result = answer.next();
            NamingEnumeration<? extends Attribute> attrs = result.getAttributes().getAll();
            while (attrs.hasMore()) {
                Attribute attr = attrs.next();
                System.out.println(attr.getID() + "=" + attr.get());
            }
            System.out.println("============");
        }
    }

    public static void closeCtx() {
        try {
            ctx.close();
        } catch (NamingException ex) {
            Logger.getLogger(LdapHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean verifySHA(String ldappw, String inputpw) throws NoSuchAlgorithmException {

        // MessageDigest 提供了消息摘要算法，如 MD5 或 SHA，的功能，这里LDAP使用的是SHA-1
        MessageDigest md = MessageDigest.getInstance("SHA-1");

        // 取出加密字符
        if (ldappw.startsWith("{SSHA}")) {
            ldappw = ldappw.substring(6);
        } else if (ldappw.startsWith("{SHA}")) {
            ldappw = ldappw.substring(5);
        }

        // 解码BASE64
        byte[] ldappwbyte = Base64.decode(ldappw);
        byte[] shacode;
        byte[] salt;

        // 前20位是SHA-1加密段，20位后是最初加密时的随机明文
        if (ldappwbyte.length <= 20) {
            shacode = ldappwbyte;
            salt = new byte[0];
        } else {
            shacode = new byte[20];
            salt = new byte[ldappwbyte.length - 20];
            System.arraycopy(ldappwbyte, 0, shacode, 0, 20);
            System.arraycopy(ldappwbyte, 20, salt, 0, salt.length);
        }

        // 把用户输入的密码添加到摘要计算信息
        md.update(inputpw.getBytes());
        // 把随机明文添加到摘要计算信息
        md.update(salt);

        // 按SSHA把当前用户密码进行计算
        byte[] inputpwbyte = md.digest();

        // 返回校验结果
        return MessageDigest.isEqual(shacode, inputpwbyte);
    }

    private static LdapHelper instance;
    private String url;
    private String baseDN;
    private String bindDN;
    private String bindPassword;
    private final Hashtable<String, String> env = new Hashtable<String, String>();
    private final Control[] sortConnCtls = new SortControl[1];

    {
        try {
            sortConnCtls[0] = new SortControl("sAMAccountName", Control.CRITICAL);
        } catch (IOException ex) {
        }
    }

    private LdapHelper() {
        try {
            URL fileUrl = getClass().getClassLoader().getResource("ldap.properties");
            File resource = new File(fileUrl.getFile());
            Properties properties = new Properties();
            properties.load(new FileInputStream(resource));
            url = properties.getProperty("url");
            baseDN = properties.getProperty("baseDN");
            bindDN = properties.getProperty("bindDN");
            bindPassword = properties.getProperty("bindPassword");
            // set up environment for creating initial context
            env.put(Context.PROVIDER_URL, url + baseDN);
            env.put(Context.SECURITY_PRINCIPAL, bindDN);
            env.put(Context.SECURITY_CREDENTIALS, bindPassword);
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put("java.naming.batchsize", "50");
            env.put("com.sun.jndi.ldap.connect.timeout", "3000");
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
            env.put("com.sun.jndi.ldap.connect.pool", "true");
            env.put("com.sun.jndi.ldap.connect.pool.maxsize", "3");
            env.put("com.sun.jndi.ldap.connect.pool.prefsize", "1");
            env.put("com.sun.jndi.ldap.connect.pool.timeout", "300000");
            env.put("com.sun.jndi.ldap.connect.pool.initsize", "1");
            env.put("com.sun.jndi.ldap.connect.pool.authentication", "simple");
            System.out.println("认证成功");
        } catch (Exception e) {
            // ignore error
            System.out.println("认证失败:" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static LdapHelper getInstance() {
        if (instance == null)
            instance = new LdapHelper();
        return instance;
    }

    public boolean validateUser(String username, String password) {
        boolean passed = false;
        LdapContext dirContext = null;
        try {
            // create initial context
            dirContext = new InitialLdapContext(env, sortConnCtls);
            dirContext.setRequestControls(sortConnCtls);
            SearchControls controls = new SearchControls();
            controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            String filter = "(sAMAccountName=" + username + ")";
            NamingEnumeration<?> answer = dirContext.search("", filter, controls);
            String userDN = null;
            while (answer.hasMore()) {
                userDN = ((NameClassPair) answer.nextElement()).getName();
            }
            // set up environment for creating initial context
            Hashtable<String, String> env = new Hashtable<String, String>();
            env.put(Context.PROVIDER_URL, url + baseDN);
            env.put(Context.SECURITY_PRINCIPAL, userDN + "," + baseDN);
            env.put(Context.SECURITY_CREDENTIALS, password);
            env.put(Context.SECURITY_AUTHENTICATION, "simple");
            env.put("com.sun.jndi.ldap.connect.timeout", "1000");
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");

            // create initial context
            DirContext context = new InitialDirContext(env);
            passed = true;
            context.close();
        } catch (NamingException e) {
            // ignore error
            // e.printStackTrace();
        } finally {
            if (dirContext != null) {
                try {
                    dirContext.close();
                } catch (NamingException e) {
                    e.printStackTrace();
                }
            }

        }
        return passed;
    }

    public static void main(String[] args) {
        // try {
        // testSearch();
        // } catch (Exception e) {
        // // TODO Auto-generated catch block
        // System.out.println("检索出错：");
        // e.printStackTrace();
        // }
        String account = "0016001400";//输入自己的账号
        String password = "XiaoZhong860722";//输入自己的nubia密码
        LdapHelper helper = LdapHelper.getInstance();
        boolean result = helper.validateUser(account, password);
        System.out.println(result);
    }
}
