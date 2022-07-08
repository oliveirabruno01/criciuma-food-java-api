package tipos_cozinha;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tipos_cozinha", schema = "public")
public class TipoCozinha implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull(message = "O campo nome não pode ser nulo")
    private String name;
    
    @NotNull(message = "O campo descrição não pode ser nulo")
    private String descricao;
    
    @NotNull(message = "O campo origem não pode ser nulo")
    private String origin;
    
    @NotNull(message = "O campo exemplos não pode ser nulo")
    private String samples;
        
    public TipoCozinha() {
    }

    public TipoCozinha(Integer id, String name, String descricao, String origin, String samples) {
        this.id = 1;
        this.descricao = descricao;
        this.origin = origin;
        this.samples = samples;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getSamples() {
        return samples;
    }

    public void setSamples(String samples) {
        this.samples = samples;
    }
    
}