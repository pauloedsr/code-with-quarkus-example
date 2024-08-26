package pessoa_juridica;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa_juridica")
public class PessoaJuridica extends PanacheEntity {

    public String nomeFantasia;
    public String razaoSocial;
    public String cnpj;
    public String endereco;
    public String telefone;
   
    public PessoaJuridica() {
    }

    
}
