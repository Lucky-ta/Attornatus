# Attornatus Procuradoria Digital - Teste prático.

## Começando
Qualidade de código:

1 - Durante a implementação de uma nova funcionalidade de software solicitada, 
quais critérios você avalia e implementa para garantia de qualidade de software?

   ```
   São alguns critérios que levo em consideração ao implementar/criar um software, alguns deles são:
   - Escrever testes bem estruturados e que garantem a confiabilidade do projeto.
   - Escrever a documentação de modo a ser facilmente compreendida para que o usuário ou outro desenvolvedor possa entender.
   - Boas praticas de programação, como padronização de variáveis, clean code, identação padronizada do código e a separação de funcionalidades.
   ```


2 - Em qual etapa da implementação você considera a qualidade de software?

   ```
   Considero a qualidade de software em todas as etapas da implementação, desde a análise dos requisitos até a escrita da documentação.
   Assim eu garanto que o software vai estar bem estrturado seguindo as boas práticas de software.
   ```
   
   
## 🚀 Introdução:
O projeto é uma API criada em Spring boot para gerenciar Pessoas(Clients).

   ```
 A API permite:
  - Criar uma pessoa
  - Editar uma pessoa
  - Consultar uma pessoa
  - Listar pessoas
  - Criar endereço para pessoa
  - Listar endereços da pessoa
   ```
   ```
Cada pessoa tem as seguintes propriedades:
  - Nome
  - Data de nascimento
  - Endereço:
  - Logradouro
  - CEP
  - Número
  - Cidade
  ```
  
  ## 🛠️ Construído com:

O projeto foi desenvolvido com as seguintes ferramentas:
 * [H2 DataBase](https://www.h2database.com/html/main.html)
 * [Java](https://www.java.com/pt-BR/)
 * [JUnit](https://junit.org/junit5/)
 * [Spring Boot](https://spring.io/)


### 🔧 Instalação do projeto:

1 -
  Faça um fork do projeto clicando no botão 'Fork' na parte superior direita do seu github.

2 -
   Faça um clone do projeto na sua máquina, copiando o link SSH ou HTTPS disponivel no seu fork e rodando o seguinte comando no seu terminal:
   ```
   git clone 'seu link SSH ou HTTPS'
   ```
3 -
  Entre no arquivo e instale as dependencias necessarias com o seguinte comando:
  ```
   cd Attornatus
  ```
   ```
   mvn install
  ```
  
 ### 📌 Uso do projeto:
  Para iniciar o projeto rode o seguinte comando no seu terminal:
    ```
    mvn spring-boot:run
    ```
    
  Após rodar o comando, o projeto vai estar rodando localmente na sua máquina na porta padrão (localhost:8080).
  
  Agora você pode criar Usuários e Endereços nas rotas citadas abaixo:
  # ⚙️ Rotas:
  
  Rota para USUÁRIOS:
  - localhost:8080/client (POST);
  
  ```
   Formato do corpo da requisção:
   {
    "name": "umNomeQualquer,
    "birthDate": "DD-MM-YYYY"
   }
  ```
  
  - localhost:8080/client/id (PUT);
        
  ```
   Formato do corpo da requisção:
   {
    "name": "umNomeQualquer, (opcional)
    "birthDate": "DD-MM-YYYY" (opcional)
   }
  ```

  - localhost:8080/client/id (GET);
  ```
   Tipo de retorno:
   {
    "id": 1,
    "name": "Lucas",
    "birthdate": "22-07-2001"
   }
  ```
  
    - localhost:8080/client (GET);
  ```
   Tipo de retorno:
   [
    {
        "id": 1,
        "name": "Lucas",
        "birthdate": "22-07-2001"
    },
    {
        "id": 2,
        "name": "Maieski",
        "birthdate": "22-07-2001"
    },
    {
        "id": 3,
        "name": "Attornatus",
        "birthdate": "22-07-1998"
    }
]
  ```
  

* Rota para ENDEREÇO:
  - localhost:8080/address/client/id (POST, onde "id" é o id do usuário);
   ```
   Formato do corpo da requisção:
  {
        "street": "POA",
        "zipcode": "123",
        "number": 30,
        "city": "poa",
        "mainAddress": true
  }
  ```
  
   - localhost:8080/address/id (GET);
   ```
   Tipo de retorno:
  {
    "id": 1,
    "street": "POA",
    "zipcode": "928",
    "number": 30,
    "city": "poa",
    "mainAddress": true,
    "client": {
        "id": 1,
        "name": "Maieski",
        "birthdate": "22-07-2001"
    }
  }
  ```
  
  
  ### 🔩 Testes
  O projeto tem dois arquivos de testes, um para rota de USUÁRIO e outro para rota de ENDEREÇOS.
   - Os testes podem ser encontrados no sguinte caminho:
 ````
 test\java\br\com\lucas\attornatus\AddressRouterTest.java
  e
 test\java\br\com\lucas\attornatus\ClientRouterTest.java
  ````
  
  Para rodar os testes, rode o seguinte comando no terminal:
   ````
   ./mvnw test
   ````
   
 ## 📌 Versão
[Git](https://git-scm.com/) - para controle de versão.
