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
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author salva
 */
@Entity
@Table(name = "gruppo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gruppo.findAll", query = "SELECT g FROM Gruppo g")
    , @NamedQuery(name = "Gruppo.findByIdG", query = "SELECT g FROM Gruppo g WHERE g.idG = :idG")
    , @NamedQuery(name = "Gruppo.findByNameG", query = "SELECT g FROM Gruppo g WHERE g.nameG = :nameG")})
public class Gruppo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_G")
    private Integer idG;
    @Size(max = 50)
    @Column(name = "name_g")
    private String nameG;
    @ManyToMany(mappedBy = "gruppoCollection")
    private Collection<Users> usersCollection;
    @OneToMany(mappedBy = "idG")
    private Collection<Messaggigruppo> messaggigruppoCollection;

    public Gruppo() {
    }

    public Gruppo(Integer idG) {
        this.idG = idG;
    }

    public Integer getIdG() {
        return idG;
    }

    public void setIdG(Integer idG) {
        this.idG = idG;
    }

    public String getNameG() {
        return nameG;
    }

    public void setNameG(String nameG) {
        this.nameG = nameG;
    }

    @XmlTransient
    public Collection<Users> getUsersCollection() {
        return usersCollection;
    }

    public void setUsersCollection(Collection<Users> usersCollection) {
        this.usersCollection = usersCollection;
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
        hash += (idG != null ? idG.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gruppo)) {
            return false;
        }
        Gruppo other = (Gruppo) object;
        if ((this.idG == null && other.idG != null) || (this.idG != null && !this.idG.equals(other.idG))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Gruppo[ idG=" + idG + " ]";
    }
    
}
