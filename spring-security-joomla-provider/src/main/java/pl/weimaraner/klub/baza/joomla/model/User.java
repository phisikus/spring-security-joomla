package pl.weimaraner.klub.baza.joomla.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * This class represents User from Joomla 3 as stored in database
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * User's identification number. Primary Key.
     */
    @Id
    @Column
    @NotNull
    private Long id;

    /**
     * User's full name.
     */
    @Column
    @Size(max = 255)
    @NotNull
    private String name;

    /**
     * Name used as login.
     */
    @Column(name = "username")
    @Size(max = 150)
    @NotNull
    private String userName;

    /**
     * User's email address
     */
    @Column
    @Size(max = 100)
    @NotNull
    private String email;

    /**
     * Encrypted user's password
     */
    @Column
    @Size(max = 100)
    @NotNull
    private String password;

    /**
     * Defines if user is blocked/unblocked
     */
    @Column(columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @NotNull
    private Boolean block;

    /**
     *
     */
    @Column(name = "sendEmail", columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean sendEmail;

    /**
     * User's registration date.
     */
    @Column(name = "registerDate")
    @NotNull
    private Timestamp registerDate;

    /**
     * Date of last visit.
     */
    @Column(name = "lastvisitDate")
    @NotNull
    private Timestamp lastVisitDate;

    /**
     * Activation value - could be "1"/empty or string containing hash.
     */
    @Column
    @Size(max = 100)
    @NotNull
    private String activation;

    /**
     * Defines user settings (ex. language)
     */
    @Column
    @NotNull
    private String params;

    /**
     * Defines when was the last time user's password was reset (timestamp)
     */
    @Column(name = "lastResetTime")
    @NotNull
    private Timestamp lastResetTime;

    /**
     * Number of times user's password was reseted.
     */
    @Column(name = "resetCount")
    @NotNull
    private Long resetCount;

    /**
     * This field contains "Two factor authentication encrypted keys"
     */
    @Column(name = "otpKey")
    @Size(max = 1000)
    @NotNull
    private String otpKey;

    /**
     * This field contains "One time emergency passwords".
     */
    @Column
    @Size(max = 1000)
    @NotNull
    private String otep;

    /**
     * This field forces user to reset password on next login.
     */
    @Column(name = "requireReset", columnDefinition = "TINYINT")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    @NotNull
    private Boolean requireReset;

    /**
     * This set of entities represents Groups that are in relation to this user.
     *
     * @see pl.weimaraner.klub.baza.joomla.model.Group
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_usergroup_map", joinColumns = {
            @JoinColumn(name = "user_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "group_id", nullable = false, updatable = false)
            })
    private Set<Group> userGroups = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getBlock() {
        return block;
    }

    public void setBlock(Boolean block) {
        this.block = block;
    }

    public Boolean getSendEmail() {
        return sendEmail;
    }

    public void setSendEmail(Boolean sendEmail) {
        this.sendEmail = sendEmail;
    }

    public Timestamp getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Timestamp registerDate) {
        this.registerDate = registerDate;
    }

    public Timestamp getLastVisitDate() {
        return lastVisitDate;
    }

    public void setLastVisitDate(Timestamp lastVisitDate) {
        this.lastVisitDate = lastVisitDate;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public Timestamp getLastResetTime() {
        return lastResetTime;
    }

    public void setLastResetTime(Timestamp lastResetTime) {
        this.lastResetTime = lastResetTime;
    }

    public Long getResetCount() {
        return resetCount;
    }

    public void setResetCount(Long resetCount) {
        this.resetCount = resetCount;
    }

    public String getOtpKey() {
        return otpKey;
    }

    public void setOtpKey(String otpKey) {
        this.otpKey = otpKey;
    }

    public String getOtep() {
        return otep;
    }

    public void setOtep(String otep) {
        this.otep = otep;
    }

    public Boolean getRequireReset() {
        return requireReset;
    }

    public void setRequireReset(Boolean requireReset) {
        this.requireReset = requireReset;
    }

    public Set<Group> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(Set<Group> userGroups) {
        this.userGroups = userGroups;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", block=" + block +
                ", sendEmail=" + sendEmail +
                ", registerDate=" + registerDate +
                ", lastVisitDate=" + lastVisitDate +
                ", activation='" + activation + '\'' +
                ", params='" + params + '\'' +
                ", lastResetTime=" + lastResetTime +
                ", resetCount=" + resetCount +
                ", otpKey='" + otpKey + '\'' +
                ", otep='" + otep + '\'' +
                ", requireReset=" + requireReset +
                ", userGroups=" + userGroups +
                '}';
    }
}
