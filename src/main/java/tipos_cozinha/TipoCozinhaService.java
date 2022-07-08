package tipos_cozinha;

import chefes.*;
import java.util.Collections;
import java.util.Comparator;
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
@Path("tipos_cozinha")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TipoCozinhaService {

    @PersistenceContext(unitName = "ProdutosPU")
    private EntityManager entityManager;

    public TipoCozinhaService() {
    }

    @GET
    public List<TipoCozinha> getTiposCozinha() {
        Query query = entityManager.createQuery("SELECT t FROM TipoCozinha t ORDER BY t.id ASC");
        List result = query.getResultList();
        return result;
    }

    @POST
    public TipoCozinha adicionar(TipoCozinha tipo_cozinha) {
        entityManager.persist(tipo_cozinha);
        return tipo_cozinha;
    }

    @PUT
    @Path("{id}")
    public TipoCozinha atualizar(@PathParam("id") Integer id, TipoCozinha tipoCozinhaAtualizado) {
        TipoCozinha tipoCozinhaEncontrado = getTipoCozinha(id);
        tipoCozinhaEncontrado.setName(tipoCozinhaAtualizado.getName());
        tipoCozinhaEncontrado.setDescricao(tipoCozinhaAtualizado.getDescricao());
        tipoCozinhaEncontrado.setOrigin(tipoCozinhaAtualizado.getOrigin());
        tipoCozinhaEncontrado.setSamples(tipoCozinhaAtualizado.getSamples());
        entityManager.merge(tipoCozinhaEncontrado);
        return tipoCozinhaEncontrado;
    }

    @DELETE
    @Path("{id}")
    public TipoCozinha excluir(@PathParam("id") Integer id) {
        TipoCozinha tipo = getTipoCozinha(id);
        entityManager.remove(tipo);
        return tipo;
    }
    
    @GET
    @Path("{id}")
    public TipoCozinha getTipoCozinha(@PathParam("id") Integer id) {
        return entityManager.find(TipoCozinha.class, id);
    }
}
