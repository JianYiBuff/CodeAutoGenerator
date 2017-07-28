package tangxiaotang.code.generater.application.config;

import org.apache.commons.lang.StringUtils;

import java.util.Properties;

public class Config {

    private String url;
    private String dbName;
    private String ip;
    private String port;
    private String userName;
    private String password;
    private String auth;
    private String filepath;
    private String packageName;
    private String tbNames;
    private String version;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getTbNames() {
        return tbNames;
    }

    public void setTbNames(String tbNames) {
        this.tbNames = tbNames;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static Config init() throws Exception {
        //获取database文件的属性设置 完成 上下文初始化操作
        Properties properties = ResourceUtil.loadPropertiesFromClassPath("database.properties");
        String ip = properties.getProperty("ip");
        String port = properties.getProperty("port");
        String userName = properties.getProperty("userName");
        String password = properties.getProperty("password");
        String packageName = properties.getProperty("packageName");
        String dbName = properties.getProperty("dbName");
        String auth = properties.getProperty("auth");
        String filepath = properties.getProperty("filepath");
        String tbNames = properties.getProperty("tbNames");
        String version = properties.getProperty("version");

        if (StringUtils.isBlank(ip)) {
            throw new Exception("ip不能为空");
        }
        if (StringUtils.isBlank(port)) {
            port = "3306";
        }
        if (StringUtils.isBlank(userName)) {
            throw new Exception("userName不能为空");
        }
        if (StringUtils.isBlank(password)) {
            throw new Exception("password不能为空");
        }
        if (StringUtils.isBlank(packageName)) {
            throw new Exception("packageName不能为空");
        }
        if (StringUtils.isBlank(dbName)) {
            throw new Exception("dbName不能为空");
        }
        if (StringUtils.isBlank(auth)) {
            throw new Exception("auth不能为空");
        }
        if (StringUtils.isBlank(filepath)) {
            throw new Exception("filepath不能为空");
        }
        if (StringUtils.isBlank(version)) {
            version = "1.0.0";
        }
        Config cfg = new Config();
        cfg.setAuth(auth);
        cfg.setDbName(dbName);
        cfg.setFilepath(filepath);
        cfg.setIp(ip);
        cfg.setPackageName(packageName);
        cfg.setPassword(password);
        cfg.setPort(port);
        cfg.setUserName(userName);
        cfg.setUrl("jdbc:mysql://" + ip + ":" + port + "/" + dbName);
        cfg.setTbNames(tbNames);
        cfg.setVersion(version);
        return cfg;
    }
}