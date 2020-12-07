# SMEI (Sistema Micro Empreendedor Individual)

#### FUNÇÃO

- `Gerenciador de estabelecimentos do setor alimentício.` Tem como objetivo calcular estrategicamente dados de estoque e vendas para solucionar problemas de gestão, mostrando estratégias de produção e valores. 

- Sistema é contemplado pelas seguintes entidades: 

1. USUÁRIO 
    - Entidade responsável pelo gerenciamento de acessos ao sistema (Atualmente este acesso parte do pressuposto que apenas o Gerente tem acesso);

2. FUNCIONÁRIO 
    - Entidade responsável pelo gerenciamento de funcionários contratados e de novas contratações;

3. PRODUTO
    - Entidade responsável pelo gerenciamento dos produtos vendidos no estabelecimento;

4. ESTOQUE
    - Entidade responsável pelo gerenciamento dos produtos disponíveis para venda;

5. VENDA
    - Entidade responsável pelo gerenciamento de todas vendas realizadas;

6. CAIXA
    - Entidade responsável pelo gerenciamento do meu caixa diário;

7. DESPESA
    - Entidade responsável pelo gerenciamento de todas despesas contabilizada no dia;

8. GESTÃO
    - Entidade responsável pelo gerenciamento de toda empresa, aqui é onde a mágica acontece.
	

#### ESPECIFICAÇÃO SERVICE IMPL

1. USUÁRIO 
    - Nesta service tenho CRUD básico, sem nenhuma regra de negocio especifica. 
	
	**Métodos implementados:** `adicionarUsuario - alterarUsuario - consultarUsuarioPorEmail - removerUsuario - listarUsuarios`  

2. FUNCIONÁRIO 
    - Nesta service tenho CRUD basico, com uma regra de negocio especifica.
	
	**Métodos implementados:** `adicionarFuncionario - alterarFuncionario - consultarFuncionarioPorCpf - listarFuncionarios - removerFuncionario - verificarDisponibilidadeCaixa`
	
	**Regras de negocio:** `verificarDisponibilidadeCaixa` Neste método é buscado um histórico do lucro mensal onde é feito uma analise verificando a disponibilidade para realizar uma nova contratação.   

3. PRODUTO
    - Nesta service tenho CRUD básico, com uma regra de negocio especifica. 
	
	**Métodos implementados:** `adicionarProduto - listarProdutos - consultarPorId - consultarPeloNome - alterarProduto - removerProduto`  
	
	**Regras de negocio:** `removerProduto` Neste método ao solicitar a remoção de um produto é verificado a existência do mesmo em **ESTOQUE**, caso tenha não é permitido a remoção do mesmo.

4. ESTOQUE
	- Nesta service tenho CRUD básico exceto DELETAR, com uma regra de negocio especifica. 
	
	**Métodos implementados:** `adicionarEstoque - alterarEstoque - consultarEstoquePorProduto - listarEstoques`  
	
	**Regras de negocio:** `contablizarPerda` Neste método ao solicitar é necessario informar qual o motivo da perda de um estoque e então é contabilizado como perda em estoque.
	
5. VENDA
    - Nesta service tenho CRUD com algumas regra de negocio especificas. 
	
	**Métodos implementados:** `adicionarVenda - alterarVenda - buscarVendaPorId - listarVendas - removerVenda`  
	
	**Regras de negocio:** `adicionarVenda` Neste método ao solicitar uma venda realizo algumas verificações para realizar uma venda o primeiro passo é necessário que tenha sido realizado a **Abertura de caixa**, segundo passo é verificado se existe o produto selecionado em estoque, terceiro passo é verificar se existe esta disponibilidade em estoque e então realizar a venda registrando a mesma na tabela **RELATORIOVENDA** descontando a quantidade em **ESTOQUE** e adicionando valor em **CAIXA**. Lembrando que ao realizar uma venda é descontado em estoque produtos que estão mais próximos a data de vencimento. 
	
	`alterarVenda` Neste método ao solicitar uma alteração de venda o primeiro passo realizado é uma verificação se venda pertence ao dia solicitado, caso pertença verifico se o novo valor de desconto é valido **(valorDesconto <= valorTotal)** então realiza a alteração e adição ou subtração em **CAIXA**. Lembrando que neste método não é realizado interação com **ESTOQUE** pois não possui alteração de produto. 		

	`removerVenda` Neste método a única regra específica é alteração de adição ou subtração em **CAIXA** referente aquela venda que esta sendo deletada.
	
6. CAIXA
    - Nesta service tenho CRUD com algumas regra de negocio especificas. 
	
	**Métodos implementados:** `adicionarCaixa - listarCaixa - consultarCaixaPorData - consultarPorId - alterarCaixa - removerCaixa - fechamentoCaixa`  
	
	**Regras de negocio:** `adicionarCaixa` Neste método ao solicitar abertura de caixa é realizado uma interação com **GESTÃO** verificando se houve uma **Abertura de Comercio** caso tenha sido realizado a abertura é verificado a disponibilidade de descontar saldo inicial de caixa em meu capital social e então é realizado a abertura.
	
	`alterarCaixa` Neste método ao realizar a alteração de caixa o mesmo reflete em **GESTÃO**.
	
	`removerCaixa` Neste método ao realizar a exclusão de caixa o mesmo reflete em **GESTÃO**.
	
	`fechamentoCaixa` Neste método ao solicitar o fechamento de caixa é verificado se já houve fechamento de caixa, segundo passo é somado total de vendas com saldo inicial e o mesmo é adicionado no capital social em **GESTÃO** e é realizado uma busca em **ESTOQUE** alterando o status de perda em todos produtos com data de vencimento igual atual.  
	
7. DESPESA
    - Nesta service tenho CRUD com algumas regra de negocio especifica. 
	
	**Métodos implementados:** `adicionarDespesa - listarDespesa - alterarDespesa - removerDespesa`  
	
	**Regras de negocio:** `adicionarDespesa` Neste método ao adicionar despesa realizo uma verificação em **GESTAO** e **CAIXA** verificando se houver **Abertura de comercio e caixa** após isto é verificado se tenho disponibilidade em meu **Capital Social** para pagar esta despesa e então realizo o cadastro da mesma adicionando o valor desta despesa em meu **Capital social e valor total despesa em CAIXA**
	
	`alterarDespesa` Neste método realizo se houve alteração em valor, caso tenha o mesmo deve ser valido de acordo com as regras anteriores e refletir em **GESTAO** e **CAIXA**

	`removerDespesa` Neste método ao remover despesa devo creditar em meu **Capital social - GESTAO** e debitar em meu **Valor total despesa - CAIXA**	
	
8. GESTÃO
    - Esta service é composta basicamente por métodos que retorna informações do sistema.  
	
	**Métodos implementados:** `aberturaComercio - adicionarInvestimentoCapitalSocial - encerrarComercio - calcularPrecoVendaPorProduto - calcularPrejuizoUnitarioPorProduto - calcularLucroDiario - calcularDespesasDiaria - calcularLucroMensal - calcularDespesasDoMes`  
	
	**Regras de negocio:** `aberturaComercio` Neste método é realizado uma abertura de comercio onde é passado o capital social que será responsável por toda interação de **CAIXA** - **VENDA** e **DESPESA**.
	
	`adicionarInvestimentoCapitalSocial` Neste método eu realizo uma adição em meu capital social, através de investimentos feitos pelo MEI.

	`encerrarComercio` Neste método eu realizo o fechamento do meu comercio impossibilitando a operação do restante do sistema.

	`calcularPrecoVendaPorProduto` Neste método é realizado buscas do produto passado, para então realizar um calculo capaz de sugerir um novo preço de venda. Para realizar este calculo é realizado uma busca de todas vendas realizadas daquele produto e todas perdas vinculadas ao mesmo, com esta informação é realizado o calculo (quantidadePerdas * valorCusto) e divido este valor em todos produtos vendidos, tento então uma sugestão de novo preço que consiga abater minhas despesas da perda.
	
	`calcularPrejuizoUnitarioPorProduto` Neste método é calculado o prejuízo total de um determinado produto que não foi vendido.
	
	`calcularLucroDiario` Neste método e realizado a soma de todo lucro diário além de retornar uma média mensal de lucro.
	
	`calcularDespesasDiaria`Neste método e realizado a soma de todas despesas diária além de retornar uma média mensal de despesas.
	
	`calcularLucroMensal` Neste método e realizado o mesmo que em **calcularLucroDiario** porém com informações referente ao mês atual.	

	`calcularDespesasDoMes` Neste método e realizado o mesmo que em **calcularDespesasDiaria** porém com informações referente ao mês atual.	

	
#### ESPECIFICAÇÃO DTO'S


|Classe  |    I/O   |     DTO'S          |                Atributos                                                                      |           
|--------|----------|--------------------|-----------------------------------------------------------------------------------------------|
|Usuario |  input   |  UsuarioDTO        | String email, String senha, String role                                                       |
|        |  out     |  MensagemDTO       | String mensagem                                                                               |
|        | input    |  AlterarUsuarioDTO | String senha, String role                                                                     |
|        | out      |  Usuario           | String email, String senha, String role                                                       |

|Classe     |I/O  |     DTO'S             |                Atributos                                                                      |           
|-----------|-----|-----------------------|-----------------------------------------------------------------------------------------------|
|Funcionario|input|CaixaDTO               |Long idCaixa, LocalDate data, Double saldoInicial, Double valorTotalDespesa, Double valorTotal|
|           |out  |MensagemDTO            |String mensagem                                                                               |
|           |input|AdicionarFuncionarioDTO|String cpf, endereco, telefone, email, cargo, LocalDate dataNascimento, Double salario, String numeroCarteiraTrabalho |
|           |out  |Funcionario            |Long idFuncionario, String nome, String email, String telefone, String endereco, String cargo, String cpf, LocalDate dataNascimento, LocalDate dataAdmissao, Double salario, String numeroCarteiraTrabalho|

|Classe  |    I/O   |     DTO'S    |                Atributos                                                                                                         |           
|--------|----------|--------------|-----------------------------------------------------------------------------------------------------------------------------------------|
|Produto |  input   |  ProdutoDTO  | String nome, String descricao, UnidadeMedida unidadeMedida, Double valorVenda, Double valorCusto, Double margemDesconto                 |
|        |  out     |  MensagemDTO | String mensagem                                                                                                                         |
|        |  out     |  Produto     | Long idProduto, String nome, String descricao, UnidadeMedida unidadeMedida, Double valorVenda, Double valorCusto, Double margemDesconto | 
  
|Classe  |    I/O   |     DTO'S          |                Atributos                                                                      |           
|--------|----------|--------------------|-----------------------------------------------------------------------------------------------|
|Estoque |  input   |  EstoqueDTO        | Long idProduto, int quantidade, LocalDate dataValidade                                        |
|        |  out     |  MensagemDTO       | String mensagem                                                                               |
|        |  input   |ContabilizaPerdaDTO | String motivoPerda                                                                            | 
|        |  out     | Estoque            | Long idEstoque, Produto produto, int quantidade, LocalDate dataValidade, boolean perda, String motivoPerda, boolean disponibilidade |

|Classe  |    I/O   |     DTO'S          |                Atributos                                                                      |           
|--------|----------|--------------------|-----------------------------------------------------------------------------------------------|
|Venda   |  input   |  AdicionarVendaDTO | String observacao, Double valorDesconto, List<ProdutosVendaDTO> produtosVenda                 |
|        |  out     |  MensagemDTO       | String mensagem                                                                               |
|        |  input   |  AlterarVendaDTO   | String observacao, Double valorDesconto                                                       |
|        |  out     |  Venda             | Long idVenda, String observacao, Double valorDesconto, Double valorTotal, LocalDate dataVenda, Caixa caixa |

|Classe  |    I/O   |     DTO'S        |                Atributos                                                                      |           
|--------|----------|------------------|-----------------------------------------------------------------------------------------------|
|Caixa   |  input   |  CaixaDTO        | Long idCaixa, LocalDate data, Double saldoInicial, Double valorTotalDespesa, Double valorTotal|
|        |  out     |  MensagemDTO     | String mensagem                                                                               |
|        |  input   | ConsultarDataDTO | LocalDate data                                                                                | 

|Classe  |    I/O   |     DTO'S    |                Atributos                                                                      |           
|--------|----------|--------------|-----------------------------------------------------------------------------------------------|
|Despesa |  input   |  DespesaDTO  | String descricao, Double valor                                                                |
|        |  out     |  MensagemDTO | String mensagem                                                                               |
|        |  input   | Despesa      | Long idDespesa, String descricao, Double valor, Caixa caixa                                   |
|        |  input   | Gestao       | Long idGestao, Double capitalSocial                                                           |
|        |  out     | Gestao       | Long idGestao, Double capitalSocial                                                           |
   
|Classe  |    I/O   |     DTO'S         |                Atributos                                                                      |           
|--------|----------|-------------------|-----------------------------------------------------------------------------------------------|
|Gestão  |  input   |AberturaComercioDTO| Double capitalSocial                                                                          |
|        |  out     |  MensagemDTO      | String mensagem                                                                               |   
|        |  out     |  RelatorioSugestaoNovoPrecoVendaDTO      | Double precoCusto, Double precoVendaAtual, Double sugestaoNovoPrecoVenda, Integer totalQuantidadeProduzida, Integer totalQuantidadeVendida, Integer totalQuantidadePerdida                                                                               |   
|        |  out     |  RelatorioPrejuizoUnitarioProdutoDTO      | Double precoCusto, Double precoVendaAtual, Double valorPrejuizoUnitario, Double valorTotalLucroPerdido, int totalQuantidadePerdida                                                                             |   
|        |  out     |  RelatorioLucroDespesaDTO      | LocalDate data, Double valor, Double mediaMensal                                  |                                            |   
 
|Classe           |    I/O   |     DTO'S    |                Atributos                                                                      |           
|-----------------|----------|--------------|-----------------------------------------------------------------------------------------------|
|RelatorioVenda   |  out     |  MensagemDTO | String mensagem                                                                               |
             
#### TAREFAS EM ABERTO

1. GESTÃO 
	- Elaboração do método (calcularQuantidadeProducaoPorProduto).

2. USUÁRIO
	- Implementação do serviço de autenticação
	
3. FUNCIONÁRIO
	- Estudar possibilidade de tornar este método generico em GESTÃO (verificarDisponibilidadeCaixa) 
	
	
4. CAIXA
	- `alterarCaixa` Neste método ao realizar a alteração de caixa o mesmo reflete em **GESTÃO**.
	
	`removerCaixa` Neste método ao realizar a exclusão de caixa o mesmo reflete em **GESTÃO**.
	
4. RELATORIO VENDA
	- Aceitar pull request do mesmo e verificar necessidade de incrementar métodos úteis para o gestor 
	
5. TESTES
	- Elaboração de testes unitários e também executar testes através do PostMan em buscar de possíveis bugs.
	
#### RELACIONAMENTOS - UML
![Sistema Micro Empreendedor Individual - SMEI-Page-1](https://user-images.githubusercontent.com/71403141/101295076-16df0b80-37fa-11eb-9f13-739f966736e2.jpg)

#### PRINCIPAIS BIBLIOTECAS UTILIZADAS PARA EXECUÇÃO DO PROJETO:

* [MySQL Connector/J](https://mvnrepository.com/artifact/mysql/mysql-connector-java)
* [Spring Web](https://mvnrepository.com/artifact/org.springframework/spring-web)
* [Spring Boot Starter Data JPA](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)
* [Spring Boot DevTools](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-devtools)
* [Swagger](https://swagger.io/)
* [Hibernate Validator](https://hibernate.org/validator/)
* [JUnit](https://mvnrepository.com/artifact/junit/junit)

#### PROGRAMAS UTILIZADOS PARA EXECUÇÃO DO PROJETO:

* [IDE Eclipse](https://www.eclipse.org/downloads/download.php?file=/oomph/epp/2020-06/R/eclipse-inst-win64.exe)
* [MySQL](https://dev.mysql.com/downloads/installer/)
* [Draw.io](https://app.diagrams.net/)
* [Trello](https://trello.com/)
* [Google Docs](https://www.google.com/intl/pt-BR/docs/about/)

#### AUTORES
- [Anderson Cancio](https://www.linkedin.com/in/anderson-cancio-b9754116b/)
- [Carlos Henrique](https://www.linkedin.com/in/carlos-henrique-bb4237192/)
- [Cleiton Oliveira]()
- [Elias Borges](https://www.linkedin.com/in/eliasborges)
- [Paulo César]()