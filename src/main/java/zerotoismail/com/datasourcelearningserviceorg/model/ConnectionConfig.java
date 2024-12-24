package zerotoismail.com.datasourcelearningserviceorg.model;


import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "connection_config")
public class ConnectionConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "json")
    private String mysql;

    @Column(name = "api_domain", nullable = false, unique = true, length = 50)
    private String apiDomain;

    @Column(name = "xmpp_domain", nullable = false, length = 200)
    private String xmppDomain;

    @Column(name = "signal_domain", length = 150)
    private String signalDomain;

    @Column(name = "xmpp_config", nullable = false, columnDefinition = "json")
    private String xmppConfig;

    @Column(name = "license_key", nullable = false, length = 200)
    private String licenseKey;

    @Column(name = "storage", nullable = false, length = 6)
    private String storage;

    @Column(name = "user_max_sessions", nullable = false)
    private Integer userMaxSessions;

    @Column(name = "aws_details", nullable = false, columnDefinition = "json")
    private String awsDetails;

    @Column(name = "oci_details", nullable = false, columnDefinition = "json")
    private String ociDetails;

    @Column(name = "android_push_details", columnDefinition = "json")
    private String androidPushDetails;

    @Column(name = "ios_push_details", columnDefinition = "json")
    private String iosPushDetails;

    @Column(name = "web_push_details", columnDefinition = "json")
    private String webPushDetails;

    @Column(name = "minio_details", columnDefinition = "json")
    private String minioDetails;

    @Column(name = "email_config", columnDefinition = "json")
    private String emailConfig;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMysql() {
        return mysql;
    }

    public void setMysql(String mysql) {
        this.mysql = mysql;
    }

    public String getApiDomain() {
        return apiDomain;
    }

    public void setApiDomain(String apiDomain) {
        this.apiDomain = apiDomain;
    }

    public String getXmppDomain() {
        return xmppDomain;
    }

    public void setXmppDomain(String xmppDomain) {
        this.xmppDomain = xmppDomain;
    }

    public String getSignalDomain() {
        return signalDomain;
    }

    public void setSignalDomain(String signalDomain) {
        this.signalDomain = signalDomain;
    }

    public String getXmppConfig() {
        return xmppConfig;
    }

    public void setXmppConfig(String xmppConfig) {
        this.xmppConfig = xmppConfig;
    }

    public String getLicenseKey() {
        return licenseKey;
    }

    public void setLicenseKey(String licenseKey) {
        this.licenseKey = licenseKey;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public Integer getUserMaxSessions() {
        return userMaxSessions;
    }

    public void setUserMaxSessions(Integer userMaxSessions) {
        this.userMaxSessions = userMaxSessions;
    }

    public String getAwsDetails() {
        return awsDetails;
    }

    public void setAwsDetails(String awsDetails) {
        this.awsDetails = awsDetails;
    }

    public String getOciDetails() {
        return ociDetails;
    }

    public void setOciDetails(String ociDetails) {
        this.ociDetails = ociDetails;
    }

    public String getAndroidPushDetails() {
        return androidPushDetails;
    }

    public void setAndroidPushDetails(String androidPushDetails) {
        this.androidPushDetails = androidPushDetails;
    }

    public String getIosPushDetails() {
        return iosPushDetails;
    }

    public void setIosPushDetails(String iosPushDetails) {
        this.iosPushDetails = iosPushDetails;
    }

    public String getWebPushDetails() {
        return webPushDetails;
    }

    public void setWebPushDetails(String webPushDetails) {
        this.webPushDetails = webPushDetails;
    }

    public String getMinioDetails() {
        return minioDetails;
    }

    public void setMinioDetails(String minioDetails) {
        this.minioDetails = minioDetails;
    }

    public String getEmailConfig() {
        return emailConfig;
    }

    public void setEmailConfig(String emailConfig) {
        this.emailConfig = emailConfig;
    }
}
