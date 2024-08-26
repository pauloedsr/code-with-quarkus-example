package pessoa_fisica;

import java.util.List;

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


@Path("/pessoa-fisica")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PessoaFisicaResource {

    @Inject
    PessoaFisicaRepository pessoaFisicaRepository; // Injete o repositório

    @GET
    public List<PessoaFisica> listarPessoas() {
        return pessoaFisicaRepository.listAll(); // Retorna todas as pessoas físicas
    }

    @GET
    @Path("/{cpf}")
    public PessoaFisica buscaPorCpf(String cpf) {
        return pessoaFisicaRepository.findByCpf(cpf);
    }


    @POST
    @Transactional
    public PessoaFisica inserirPessoaFisica(PessoaFisica pessoa) {
        pessoaFisicaRepository.persist(pessoa); // Salva a pessoa no banco de dados
        
        return pessoa;
    }

    @DELETE
    @Transactional
    @Path("/{cpf}")
    public void removerPessoaFisica(String cpf) {
        PessoaFisica pessoa = pessoaFisicaRepository.findByCpf(cpf);
        pessoaFisicaRepository.delete(pessoa);
    }

    @PUT
    @Transactional
    @Path("/{cpf}")
    public PessoaFisica atualizarPessoaFisica(String cpf, PessoaFisica pessoa) {
        PessoaFisica pessoaExistente = pessoaFisicaRepository.findByCpf(cpf);
        pessoaExistente.nome = pessoa.nome;
        pessoaExistente.dataNascimento = pessoa.dataNascimento;
        pessoaExistente.endereco = pessoa.endereco;
        pessoaExistente.telefone = pessoa.telefone;
        pessoaFisicaRepository.persist(pessoaExistente);
        return pessoaExistente;
    }

    
}
