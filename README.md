# Assembléia de votação em pauta

## Resumo
A aplicação foi desenvolvida na linguagem Java, utilizado o framework Spring Boot na versão 2.3.3 e o Maven para controle de dependências. Junto ao Spring, foi utilizado o framework Hibernate, para realizar o mapeamento das classes Java e conversão em tabelas no banco de dados. A API REST desenvolvida possui endpoints com métodos dos tipos GET e POST para criação e busca de dados. 
Utilizado também o Swagger para gerar a documentação da api.

## Banco de dados
O banco de dados escolhido para integrar a aplicação foi o  H2, com as seguintes configurações.

## Executar projeto

Link do repositório: https://github.com/Andremachado82/desafio-cwi

### Projeto configurado para a porta https://localhost:8083

1. Rode no terminal git clone https://github.com/Andremachado82/desafio-cwi 
2. Abra a basta desafio-cwi
3. Abra o eclipse ou intelijj e import o projeto.
4. Com o projeto importado expanda a pasta com.desafio.cwi e roder o arquivo DesafioCwiApplication.java 
5. Para acessar o banco de dados h2 copie e cole o link e rode no navegador: http://localhost:8083/h2-console 
- imagem da tela incial: https://miro.medium.com/max/1400/1*siuOUhRCqO8eP6gxaBIZ4w.png
6. Para acessar a documentação pelo Swagger copie e cole o link no seu navegador: http://localhost:8083/swagger-ui.html

#### Resumo do desafio
- No cooperativismo, cada associado possui um voto e as decisões são tomadas em assembleias, por votação. A partir disso, você precisa criar uma solução back-end para gerenciar essas sessões de votação. Essa solução deve ser executada na nuvem e promover as seguintes funcionalidades através de uma API REST:
- Cadastrar uma nova pauta;
- Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default);
- Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta);
- Contabilizar os votos e dar o resultado da votação na pauta
- Para fins de exercício, a segurança das interfaces pode ser abstraída e qualquer chamada para as interfaces pode ser considerada como autorizada. A escolha da linguagem, frameworks e bibliotecas é livre (desde que não infrinja direitos de uso).

- É importante que as pautas e os votos sejam persistidos e que não sejam perdidos com o restart da aplicação.

## Notas sobre dados persistidos

- Para os dados inseridos na aplicação sejam persistidos no banco e não sejam perdidos ao restart da aplicação é necessário que no arquivo apllication.properties deve existir as seguinte configurações:

- spring.jpa.hibernate.ddl-auto=update
- spring.datasource.url=jdbc:h2:file:~/test

## Endpoints

Seguem abaixo os seguintes endpoints criados para o funcionamento da API versão 1.0:
### GET

Listar pautas: `/v1/pautas`

Buscar pauta específica: `/v1/pautas/{id}`

Listar sessões: `/v1/pauta/sessao`

Buscar sessões específica: `/v1/pauta/sessao/{id}`

Listar votos: `/v1/pautas/sessoes/votos`

Buscar voto específico: `/v1/pautas/sessoes/votos/{id}`

### POST
Cadastrar pauta: `/v1/pautas`

Cadastrar sessão: `/v1/pauta/{idPauta}/sessao`

Cadastrar voto: `/v1/pautas/{idPauta}/sessoes/{idSessao}/votos`

Ao iniciar sessão de votação essa terá o tempo máximo de 1 minuto de duração após esse tempo a sessão é expirada.
