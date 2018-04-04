/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author salva
 */
@Entity
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u")
    , @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username")
    , @NamedQuery(name = "Users.findByPasswordc", query = "SELECT u FROM Users u WHERE u.passwordc = :passwordc")
    , @NamedQuery(name = "Users.findByUsernamec", query = "SELECT u FROM Users u WHERE u.usernamec = :usernamec")
    , @NamedQuery(name = "Users.findByNomeV", query = "SELECT u FROM Users u WHERE u.nomeV = :nomeV")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Username")
    private String username;
    @Size(max = 36)
    @Column(name = "Password_c")
    private String passwordc;
    @Size(max = 36)
    @Column(name = "Username_c")
    private String usernamec;
    @Size(max = 20)
    @Column(name = "nome_v")
    private String nomeV;
    @JoinTable(name = "partecipa", joinColumns = {
        @JoinColumn(name = "Username", referencedColumnName = "Username")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_G", referencedColumnName = "ID_G")})
    @ManyToMany
    private Collection<Gruppo> gruppoCollection;
    @OneToMany(mappedBy = "mitt")
    private Collection<Messaggi> messaggiCollection;
    @OneToMany(mappedBy = "dest")
    private Collection<Messaggi> messaggiCollection1;
    @OneToMany(mappedBy = "mitt")
    private Collection<Messaggigruppo> messaggigruppoCollection;

    public Users() {
    }

    public Users(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordc() {
        return passwordc;
    }

    public void setPasswordc(String passwordc) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(passwordc.getBytes());
        byte byteData[] = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        this.passwordc=sb.toString();
    }

    public String getUsernamec() {
        return usernamec;
    }

    public void setUsernamec(String usernamec) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(usernamec.getBytes());
        byte byteData[] = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        this.usernamec = sb.toString();
    }

    public String getNomeV() {
        return nomeV;
    }

    public void setNomeV(String nomeV) {
        this.nomeV = nomeV;
    }

    @XmlTransient
    public Collection<Gruppo> getGruppoCollection() {
        return gruppoCollection;
    }

    public void setGruppoCollection(Collection<Gruppo> gruppoCollection) {
        this.gruppoCollection = gruppoCollection;
    }

    @XmlTransient
    public Collection<Messaggi> getMessaggiCollection() {
        return messaggiCollection;
    }

    public void setMessaggiCollection(Collection<Messaggi> messaggiCollection) {
        this.messaggiCollection = messaggiCollection;
    }

    @XmlTransient
    public Collection<Messaggi> getMessaggiCollection1() {
        return messaggiCollection1;
    }

    public void setMessaggiCollection1(Collection<Messaggi> messaggiCollection1) {
        this.messaggiCollection1 = messaggiCollection1;
    }

    @XmlTransient
    public Collection<Messaggigruppo> getMessaggigruppoCollection() {
        return messaggigruppoCollection;
    }

    public void setMessaggigruppoCollection(Collection<Messaggigruppo> messaggigruppoCollection) {
        this.messaggigruppoCollection = messaggigruppoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Users[ username=" + username + " ]";
    }

}
