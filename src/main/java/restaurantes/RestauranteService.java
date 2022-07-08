package restaurantes;

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
@Path("restaurantes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RestauranteService {

    @PersistenceContext(unitName = "ProdutosPU")
    private EntityManager entityManager;

    public RestauranteService() {
    }

    @GET
    public List<Restaurante> getRestaurantes() {
        Query query = entityManager.createQuery("SELECT r FROM Restaurante r ORDER BY r.id asc");
        return query.getResultList();
    }

    @POST
    public Restaurante adicionar(Restaurante restaurante) {
        List<Restaurante> _restaurantes = this.getRestaurantes();
        long address_count = 0;
        long district_count = 0;
        long type_in_district_count = 0;
        long restaurantes_by_chefe_count = 0;
        
        
//      LAÇO QUE ITERA PELOS RESTAURANTES DO BANCO E INCREMENTA OS CONTADORES 
//      PARA VALIDAR AS REGRAS DE NEGÓCIO  
        for (int i = 0; i < _restaurantes.size(); i++)  {
//          CONTADOR RESTAURANTES POR DE ENDEREÇO (RUA)
            if (restaurante.getAddress().equals(_restaurantes.get(i).getAddress())) {
                address_count++;
            }
            
//          CONTADOR DE RESTAURANTE POR BAIRRO
            if (restaurante.getDistrict().equals(_restaurantes.get(i).getDistrict())) {
                district_count++;
                
//              CONTADOR DE TIPO DE COZINHA POR BAIRRO
                if (restaurante.getIdCozinhaTipo().equals(_restaurantes.get(i).getIdCozinhaTipo())) {
                    type_in_district_count++;
                }
            }
            
//          CONTADOR DE RESTAURANTES POR CHEFE
            if (restaurante.getIdChefe().equals(_restaurantes.get(i).getIdChefe())) {
                restaurantes_by_chefe_count++;
            }
    }
        
        
//      VALIDA REGRAS DE NEGÓCIO E RETORNA UM OBJETO RESTAURANTE COM ID 0 PARA REPRESENTAR UM ERRO
//      E UMA MENSAGEM DE ERRO NO NAME DO OBJETO
        if (address_count > 1) {
            restaurante.setId(0);
            restaurante.setName("MaxRestaraunteByAddressReached");
            return restaurante;
        }
        
        if (district_count > 3) {
            restaurante.setId(0);
            restaurante.setName("MaxRestaraunteByDistrictReached");
            return restaurante;
        }
        
        if (type_in_district_count > 0) {
            restaurante.setId(0);
            restaurante.setName("MaxTypeByDistrictReached");
            return restaurante;
        }
        
        if (restaurantes_by_chefe_count > 3) {
            restaurante.setId(0);
            restaurante.setName("MaxRestaurantesByChefeReached");
            return restaurante;
        }
        
        
        entityManager.persist(restaurante);
        return restaurante;
    }

    @PUT
    @Path("{id}")
    public Restaurante atualizar(@PathParam("id") Integer id, Restaurante restauranteAtualizado) {
        
        List<Restaurante> _restaurantes = this.getRestaurantes();
        long address_count = 0;
        long district_count = 0;
        long type_in_district_count = 0;
        long restaurantes_by_chefe_count = 0;
        
//      REPETIÇÃO DAS REGRAS DE NEGÓCIO PARA ATUALIZAR (REMOVER REPETIÇÃO POSTERIORMENTE)
//      LAÇO QUE ITERA PELOS RESTAURANTES DO BANCO E INCREMENTA OS CONTADORES 
//      PARA VALIDAR AS REGRAS DE NEGÓCIO  
        for (int i = 0; i < _restaurantes.size(); i++)  {
//          CONTADOR RESTAURANTES POR DE ENDEREÇO (RUA)
            if (restauranteAtualizado.getAddress().equals(_restaurantes.get(i).getAddress())) {
                address_count++;
            }
            
//          CONTADOR DE RESTAURANTE POR BAIRRO
            if (restauranteAtualizado.getDistrict().equals(_restaurantes.get(i).getDistrict())) {
                district_count++;
                
//              CONTADOR DE TIPO DE COZINHA POR BAIRRO
                if (restauranteAtualizado.getIdCozinhaTipo().equals(_restaurantes.get(i).getIdCozinhaTipo())) {
                    type_in_district_count++;
                }
            }
            
//          CONTADOR DE RESTAURANTES POR CHEFE
            if (restauranteAtualizado.getIdChefe().equals(_restaurantes.get(i).getIdChefe())) {
                restaurantes_by_chefe_count++;
            }
    }
        
        
//      VALIDA REGRAS DE NEGÓCIO E RETORNA UM OBJETO RESTAURANTE COM ID 0 PARA REPRESENTAR UM ERRO
//      E UMA MENSAGEM DE ERRO NO NAME DO OBJETO
        if (address_count > 1) {
            restauranteAtualizado.setId(0);
            restauranteAtualizado.setName("MaxRestaraunteByAddressReached");
            return restauranteAtualizado;
        }
        
        if (district_count > 3) {
            restauranteAtualizado.setId(0);
            restauranteAtualizado.setName("MaxRestaraunteByDistrictReached");
            return restauranteAtualizado;
        }
        
        if (type_in_district_count > 0) {
            restauranteAtualizado.setId(0);
            restauranteAtualizado.setName("MaxTypeByDistrictReached");
            return restauranteAtualizado;
        }
        
        if (restaurantes_by_chefe_count > 3) {
            restauranteAtualizado.setId(0);
            restauranteAtualizado.setName("MaxRestaurantesByChefeReached");
            return restauranteAtualizado;
        }
        
        Restaurante restauranteEncontrado = getRestaurante(id);
        restauranteEncontrado.setName(restauranteAtualizado.getName());
        restauranteEncontrado.setAddress(restauranteAtualizado.getAddress());
        restauranteEncontrado.setDistrict(restauranteAtualizado.getDistrict());
        restauranteEncontrado.setIdCozinhaTipo(restauranteAtualizado.getIdCozinhaTipo());
        restauranteEncontrado.setIdChefe(restauranteAtualizado.getIdChefe());
        entityManager.merge(restauranteEncontrado);
        return restauranteEncontrado;
    }

    @DELETE
    @Path("{id}")
    public Restaurante excluir(@PathParam("id") Integer id) {
        Restaurante restaurante = getRestaurante(id);
        entityManager.remove(restaurante);
        return restaurante;
    }
    
    @GET
    @Path("{id}")
    public Restaurante getRestaurante(@PathParam("id") Integer id) {
        return entityManager.find(Restaurante.class, id);
    }
}
