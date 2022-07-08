package chefes;

import java.util.List;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("chefes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChefeService {

    @PersistenceContext(unitName = "ProdutosPU")
    private EntityManager entityManager;

    public ChefeService() {
    }

    @GET
    public List<Chefe> getChefes() {
        Query query = entityManager.createQuery("SELECT c FROM Chefe c ORDER BY c.id ASC");
        return query.getResultList();
    }

    @POST
    public Chefe adicionar(Chefe chefe) {
        List<Chefe> _chefes = this.getChefes();
        long name_count = 0;
        
        for (int i = 0; i < _chefes.size(); i++)  {
//          CONTADOR NOME POR CHEFES
            if (chefe.getName().equals(_chefes.get(i).getName())) {
                name_count++;
            }
        }
        
//      verifica regra de negócio do nome  
        if (name_count != 0) {
            chefe.setId(0);
            chefe.setName("NameInUse");
            return chefe;
        }
        
//      verifica regra de negócio dos anos de experiência
        if (chefe.getAnosExperiencia() < 4) {
            chefe.setId(0);
            chefe.setName("NotEnoughXp");
            return chefe;
        }
        entityManager.persist(chefe);
        return chefe;
    }

    @PUT
    @Path("{id}")
    public Chefe atualizar(@PathParam("id") Integer id, Chefe chefeAtualizado) {
        List<Chefe> _chefes = this.getChefes();
        long name_count = 0;
        
//      REPETIÇÃO DAS REGRAS DE NEGÓCIO
        for (int i = 0; i < _chefes.size(); i++)  {
//          CONTADOR NOME POR CHEFES
            if (chefeAtualizado.getName().equals(_chefes.get(i).getName())) {
                name_count++;
            }
        }
        
        if (name_count != 0) {
            chefeAtualizado.setId(0);
            chefeAtualizado.setName("NameInUse");
            return chefeAtualizado;
        }
        
        if (chefeAtualizado.getAnosExperiencia() < 4) {
            chefeAtualizado.setId(0);
            chefeAtualizado.setName("NotEnoughXp");
            return chefeAtualizado;
        }
        
        Chefe chefeEncontrado = getChefe(id);
        chefeEncontrado.setName(chefeAtualizado.getName());
        chefeEncontrado.setDescricao(chefeAtualizado.getDescricao());
        chefeEncontrado.setAnosExperiencia(chefeAtualizado.getAnosExperiencia());
        chefeEncontrado.setDocumentoGeral(chefeAtualizado.getDocumentoGeral());
        entityManager.merge(chefeEncontrado);
        return chefeEncontrado;
    }

    @DELETE
    @Path("{id}")
    public Chefe excluir(@PathParam("id") Integer id) {
        Chefe chefe = getChefe(id);
        entityManager.remove(chefe);
        return chefe;
    }
    
    @GET
    @Path("{id}")
    public Chefe getChefe(@PathParam("id") Integer id) {
        return entityManager.find(Chefe.class, id);
    }
}
