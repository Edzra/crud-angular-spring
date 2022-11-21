# Projeto e tecnologias utilizadas.

Projeto desenvolvido utilizando os frameworks Angular e Spring Boot (em Java).

O projeto permite realizar todas as operações CRUD com produtos, é possível filtrar via categoria na tela de listagem.

Para a criação dos components, modules, resolvers e services foram os comandos de terminal via Angular CLI, além da instação de pacotes como o ngx-mask, utilizando npm. Foi utilizado angular 14.2.9 com pré-processador scss e Angular Material para os principais components de tela. O fomulário de cadastro possuí validação dos dados e impede a inserção de valores fora do padrão esperado pelo Spring.

O banco de dados utilizado é o H2, onde é feita uma inserção de categorias e produtos ao iniciar do Spring. Na modelagem do banco de datos temos uma tabela para produtos que possui um chave estrangeira que relaciona o produto com cada Categoria. A tabela categoria possuí, então, uma relação @ManyToOne com Produtos, pois cada produto possuí uma tabela.

*Foram implementados testes dos endpoints da API.

# Configuração e utilização da aplicação.

Para o Angular, basta baixar as dependências com "npm install" e subir com "ng serve".

Para o Spring, qualquer ambiente pode ser utilizado para subir a API. Foi realizado uma configuração para que não ocorra CORS independentemente da porta utilizada. 
