/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author salva
 */
@Entity
@Table(name = "messaggi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Messaggi.findAll", query = "SELECT m FROM Messaggi m")
    , @NamedQuery(name = "Messaggi.findByIdM", query = "SELECT m FROM Messaggi m WHERE m.idM = :idM")
    , @NamedQuery(name = "Messaggi.findByTypeM", query = "SELECT m FROM Messaggi m WHERE m.typeM = :typeM")})
public class Messaggi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_M")
    private Integer idM;
    @Size(max = 10)
    @Column(name = "type_m")
    private String typeM;
    @Lob
    @Size(max = 65535)
    @Column(name = "text_m")
    private String textM;
    @JoinColumn(name = "mitt", referencedColumnName = "Username")
    @ManyToOne
    private Users mitt;
    @JoinColumn(name = "dest", referencedColumnName = "Username")
    @ManyToOne
    private Users dest;

    public Messaggi() {
    }

    public Messaggi(Integer idM) {
        this.idM = idM;
    }

    public Integer getIdM() {
        return idM;
    }

    public void setIdM(Integer idM) {
        this.idM = idM;
    }

    public String getTypeM() {
        return typeM;
    }

    public void setTypeM(String typeM) {
        this.typeM = typeM;
    }

    public String getTextM() {
        return textM;
    }

    public void setTextM(String textM) {
        this.textM = textM;
    }

    public Users getMitt() {
        return mitt;
    }

    public void setMitt(Users mitt) {
        this.mitt = mitt;
    }

    public Users getDest() {
        return dest;
    }

    public void setDest(Users dest) {
        this.dest = dest;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idM != null ? idM.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Messaggi)) {
            return false;
        }
        Messaggi other = (Messaggi) object;
        if ((this.idM == null && other.idM != null) || (this.idM != null && !this.idM.equals(other.idM))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Database.Messaggi[ idM=" + idM + " ]";
    }
    
}
