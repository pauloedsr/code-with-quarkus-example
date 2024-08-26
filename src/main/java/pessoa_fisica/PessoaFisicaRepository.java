package pessoa_fisica;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class PessoaFisicaRepository implements PanacheRepository<PessoaFisica> {
    
    public PessoaFisica findByCpf(String cpf) {
        return find("cpf", cpf).firstResult();
    }

    
}