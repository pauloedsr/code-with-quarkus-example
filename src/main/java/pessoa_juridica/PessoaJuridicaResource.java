package pessoa_juridica;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/pessoa-juridica")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaJuridicaResource {

    @Inject
    PessoaJuridicaRepository pessoaJuridicaRepository; 

    @GET
    public List<PessoaJuridica> listarPessoas() {
        return pessoaJuridicaRepository.listAll(); 
    }

    @GET
    @Path("/{cnpj}")
    public PessoaJuridica buscaPorCnpj(String cnpj) {
        return pessoaJuridicaRepository.findByCnpj(cnpj);
    }


    @POST
    @Transactional
    public PessoaJuridica inserirPessoaJuridica(PessoaJuridica pessoa) {
        pessoaJuridicaRepository.persist(pessoa); 
        
        return pessoa;
    }

    @DELETE
    @Transactional
    @Path("/{cnpj}")
    public void removerPessoaJuridica(String cnpj) {
        PessoaJuridica pessoa = pessoaJuridicaRepository.findByCnpj(cnpj);
        pessoaJuridicaRepository.delete(pessoa);
    }

    @PUT
    @Transactional
    @Path("/{cnpj}")
    @Operation(summary = "Atualiza uma Pessoa Jurídica existente por CNPJ.")
    @APIResponses(value = {
        @APIResponse(responseCode = "200", description = "Pessoa Jurídica atualizada com sucesso", 
                     content = @Content(mediaType = "application/json", 
                                       schema = @Schema(implementation = PessoaJuridica.class))),
        @APIResponse(responseCode = "404", description = "Pessoa Jurídica não encontrada")
    })
    public PessoaJuridica atualizarPessoaJuridica(String cnpj, PessoaJuridica pessoa) {
        PessoaJuridica pessoaExistente = pessoaJuridicaRepository.findByCnpj(cnpj);
        pessoaExistente.nomeFantasia = pessoa.nomeFantasia;
        pessoaExistente.razaoSocial = pessoa.razaoSocial;
        pessoaExistente.endereco = pessoa.endereco;
        pessoaExistente.telefone = pessoa.telefone;
        pessoaJuridicaRepository.persist(pessoaExistente);
        return pessoaExistente;
    }

    
}
