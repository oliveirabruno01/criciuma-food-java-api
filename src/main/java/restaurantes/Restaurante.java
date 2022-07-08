package restaurantes;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "restaurantes", schema = "public")
public class Restaurante implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull(message = "O campo nome não pode ser nulo")
    private String name;
    
    @Size(min = 3, message = "O campo address precisa ter no mínimo 3 caracteres")
    @NotNull(message = "O campo descrição não pode ser nulo")
    private String address;
    
    @Size(min = 3, message = "O campo address precisa ter no mínimo 3 caracteres")
    @NotNull(message = "O campo descrição não pode ser nulo")
    private String district; 
    
    @NotNull(message = "O campo id_cozinha não pode ser nulo")
    Integer id_cozinha_tipo;
    
    @NotNull(message = "O campo id_chefe não pode ser nulo")
    Integer id_chefe;
    
//    @OneToMany(mappedBy = "produto", fetch = FetchType.EAGER)
//    private List<Movimentacao> movimentacoes;

    public Restaurante() {
    }

    public Restaurante(Integer id, String name, String address, String district, Integer id_cozinha_tipo, Integer id_chefe) {
        this.id = 1;
        this.name = name;
        this.address = address;
        this.district = district;
        this.id_cozinha_tipo = id_cozinha_tipo;
        this.id_chefe = id_chefe;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getIdCozinhaTipo() {
        return id_cozinha_tipo;
    }

    public void setIdCozinhaTipo(Integer id_cozinha_tipo) {
        this.id_cozinha_tipo = id_cozinha_tipo;
    }

    public Integer getIdChefe() {
        return id_chefe;
    }

    public void setIdChefe(Integer id_chefe) {
        this.id_chefe = id_chefe;
    }
    
    //
//    public List<Movimentacao> getMovimentacoes() {
//        return movimentacoes;
//    }
//
//    public void setMovimentacoes(List<Movimentacao> movimentacoes) {
//        this.movimentacoes = movimentacoes;
//    }
 
}
