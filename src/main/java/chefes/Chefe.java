package chefes;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "chefes", schema = "public")
public class Chefe implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @NotNull(message = "O campo nome não pode ser nulo")
    private String name;
    
    @Size(min = 3, message = "O campo descrição precisa ter no mínimo 3 caracteres")
    @NotNull(message = "O campo descrição não pode ser nulo")
    private String descricao;
    
    @NotNull(message = "O campo anos_experiencia não pode ser nulo")
    @Min(value = 3, message = "O campo anos_experiencia não pode ser inferior a 3")
    private Integer anos_experiencia; 
    
    @NotNull(message = "O campo documento_geral não pode ser nulo")
    private String documento_geral;
   
    public Chefe() {
    }

    public Chefe(Integer id, String name, String descricao, Integer anos_experiencia, String documento_geral) {
        this.id = 1;
        this.name = name;
        this.descricao = descricao;
        this.anos_experiencia = anos_experiencia;
        this.documento_geral = documento_geral;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAnosExperiencia() {
        return anos_experiencia;
    }

    public void setAnosExperiencia(Integer anos_experiencia) {
        this.anos_experiencia = anos_experiencia;
    }

    public String getDocumentoGeral() {
        return documento_geral;
    }

    public void setDocumentoGeral(String documento_geral) {
        this.documento_geral = documento_geral;
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
