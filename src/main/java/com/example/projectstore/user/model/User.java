package com.example.projectstore.user.model;

import jakarta.persistence.*;
import lombok.*;

@Entity /*reconhece que essa classe representa uma tabela do banco de dados
e mapeia os campos da classe para as colunas da tabela*/
@Table(name = "userTable")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    @Id //identifica que é uma chave primaria
    @GeneratedValue(strategy = GenerationType.SEQUENCE)//valor da chave primária será gerado automaticamente
    private Long id;

    private String name;

    private String email;

    private String password;

    private String currency;
}
