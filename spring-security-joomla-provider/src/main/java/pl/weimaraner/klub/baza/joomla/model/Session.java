package pl.weimaraner.klub.baza.joomla.model;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

/**
 * This class represents session object in Joomla 3 as stored in database
 *
 */
@Entity
public class Session {

    /**
     * Contains session identification hash that is used in Joomla cookie as value
     */
    @Column(name = "session_id")
    @Size(max = 200)
    @NotEmpty
    @Id
    private String sessionId;

    /**
     * This field usually equals 0.
     * If user is using the same browser to access management panel and is required to authorize again, new cookie is
     * created and second row appears in database with clientId = 1.
     */
    @Column(name = "client_id")
    @Size(max = 3)
    @NotEmpty
    private short clientId;

    /**
     * This field defines if session belongs to existing user or to anonymous visitor.
     */
    @Column(name = "guest", columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isGuest;


    /**
     * This field defines time of creation. If timestamp + 1440s > now then session is expired and should be removed.
     */
    @Column(name = "time")
    private Long timestamp;

    /**
     * This field contains various session metadata.
     */
    @Column
    private String data;

    /**
     * This field contains numeric ID as described in users table.
     *
     * @see <a href="http://docs.joomla.org/Tables/users">http://docs.joomla.org/Tables/users</a>
     */
    @Column(name = "userid")
    @Size(max = 11)
    private Integer userId;


    /**
     * If session belongs to logged-in user, this field represents login name.
     *
     * @see <a href="http://docs.joomla.org/Tables/users">http://docs.joomla.org/Tables/users</a>
     */
    @Column(name = "username")
    @Size(max = 150)
    private String userName;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public short getClientId() {
        return clientId;
    }

    public void setClientId(short clientId) {
        this.clientId = clientId;
    }

    public Boolean getIsGuest() {
        return isGuest;
    }

    public void setIsGuest(Boolean isGuest) {
        this.isGuest = isGuest;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "Session{" +
                "sessionId='" + sessionId + '\'' +
                ", clientId=" + clientId +
                ", isGuest=" + isGuest +
                ", timestamp=" + timestamp +
                ", data='" + data + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
