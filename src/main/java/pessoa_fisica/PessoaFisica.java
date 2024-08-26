package pessoa_fisica;

import java.time.LocalDate;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa_fisica")
public class PessoaFisica extends PanacheEntity {
    public String nome;
    public String cpf;

    @Column(name = "data_nascimento")
    public LocalDate dataNascimento;
    public String endereco;
    public String telefone;
    
    public PessoaFisica() {
    }

    
}