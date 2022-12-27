package curso.api.rest.dto;

import curso.api.rest.model.Usuario;

import java.io.Serializable;

public class UsuarioDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userLogin;
    private String userNome;
    private String userCpf;
    private EnderecoDTO enderecoDto;

    public UsuarioDTO(Usuario usuario) {

        EnderecoDTO enderecoDTO = new EnderecoDTO(usuario.getEndereco());
        this.userLogin = usuario.getLogin();
        this.userNome = usuario.getNome();
        this.userCpf = usuario.getCpf();
        this.enderecoDto = enderecoDTO;

    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserNome() {
        return userNome;
    }

    public void setUserNome(String userNome) {
        this.userNome = userNome;
    }

    public String getUserCpf() {
        return userCpf;
    }

    public void setUserCpf(String userCpf) {
        this.userCpf = userCpf;
    }

    public EnderecoDTO getEnderecoDto() {
        return enderecoDto;
    }

    public void setEnderecoDto(EnderecoDTO enderecoDto) {
        this.enderecoDto = enderecoDto;
    }
}
