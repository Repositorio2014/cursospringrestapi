package votacao.api.rest.dto;

import java.io.Serializable;

public class RoleDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nomeRole;

    public RoleDTO(String nomeRole) {
        this.nomeRole = nomeRole;
    }

    public RoleDTO() {
    }

    public String getNomeRole() {
        return nomeRole;
    }

    public void setNomeRole(String nomeRole) {
        this.nomeRole = nomeRole;
    }
}
