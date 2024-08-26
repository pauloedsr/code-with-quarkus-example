package pessoa_juridica;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class PessoaJuridicaRepository implements PanacheRepository<PessoaJuridica> {
    
    public PessoaJuridica findByCnpj(String cnpj) {
        return find("cnpj", cnpj).firstResult();
    }

    
}

