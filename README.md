# DSW1-Servlets
Atividade avaliativa da disciplina Desenvolvimento de Software para Web 01 cujo objetivo é desenvolver um sistema para locação de bicicletas utilizando Servlets

# Sistema para Locação de Bicicletas
Este projeto foi desenvolvido como uma parte avaliativa da disciplina Desenvolvimento de Software para Web 1, ministrada pelo professor Alan Demetrius. O principal objetivo desse trabalho
é criar uma aplicação web para um sistema de locação de bicicletas, a partir de um conjunto de requisitos.

## Requisitos da Aplicação
Aqui está listado os requisitos que foram utilizados para desenvolver a aplicação:

DESENVOLVIMENTO DE SOFTWARE PARA A WEB 1 <br>
Profs. Alan D. B. Valejo & Delano M. Beder (UFSCar) <br>
Atividade A-1: Sistema para locação de bicicletas <br>
O sistema deve possuir um cadastro de clientes, com os seguintes dados: e-mail, senha, CPF, nome, telefone, sexo e data de nascimento. <br>
O sistema deve possuir um cadastro de locadoras, com os seguintes dados: e-mail, senha, CNPJ, nome e cidade. <br>
O sistema deve possuir um cadastro de locações, com os seguintes dados: CPF do cliente, CNPJ da locadora e dia/horário da locação. Assume-se que a duração da locação é de 1 hora e sempre inicia-se em "hora cheia" (13h 00min etc) <br>
O sistema deve atender aos seguintes requisitos: <br>
• R1: CRUD(1] de clientes (requer login de administrador) <br>
• R2: CRUD de locadoras (requer login de administrador) <br>
• R3: Listagem de todos as locadoras em uma única página (não requer login) <br>
• R4: Listagem de todos as locadoras por cidade (não requer login) <br>
• R5: Locação de uma bicicleta em uma locadora (requer login do cliente via e-mail + senha). <br>
Depois de fazer login, o cliente pode cadastrar uma locação. Para isso, deve escolher uma locadora (escolhendo a partir de uma lista), uma data, e deve ser gravado a locação na base de dados. Após a efetivação da locação, o cliente e a locadora devem ser informados (via e-mail) sobre a locação realizada. <br>
• R6: Listagem de todas as locações de um cliente (requer login do cliente via e-mail + senha). <br>
Depois de fazer login, o cliente pode visualizar todas as suas locações gravadas. <br>
• R7: O sistema não deve permitir o cadastro de locações de um mesmo cliente ou de um mesma <br>
locadora em um mesmo dia/horário. <br>
• R8: Listagem de todas as locações de uma locadora (requer login da locadora via e-mail + <br>
senha). Depois de fazer login, a locadora pode visualizar todas as suas locações gravadas. <br>
• R9: O sistema deve ser internacionalizado em pelo menos dois idiomas: português + outro de sua escolha. <br>
O sistema deve tratar todos os erros possíveis (cadastros duplicados, problemas técnicos, etc) mostrando uma página de erros amigável ao usuário e registrando o erro no console. <br>
*Arquitetura: * Modelo-Visão-Controlador <br>
Tecnologias <br>
• Servlet, JSP, JSTL & JDBC (Lado Servidor) <br>
• Javascript & CSS (Lado Cliente) <br>
Ambiente de Desenvolvimento <br>
• A compilaçao e o deployment deve ser obrigatoriamente ser realizado via maven. <br>
• Os arquivos fonte do sistema devem estar hospedados obrigatoriamente em um repositório 
(preferencialmente github). <br>
1. CRUD: Create, Read, Update & Delete. D <br>

## Tecnologias utilizadas
Como especificado nos requisitos, o projeto foi criado utilizando as seguintes tecnologias (Lado Servidor): <br>
• Servlet   <br>
• JSP (JavaServer Pages) <br>
• JSTL (JavaServer Pages Standard Tag Library) <br>
• JDBC (Java Database Connectivity) <br>
E, no ambiente de desenvolvimento: <br>
• Maven
• Apache Tomcat 
Foi utilizado tais tecnologias pois o principal objtivo desse trabalho era aprender a desenvolver um ambiente web sem frameworks, a fim de entender melhor o assunto, uma vez que foi utilizado ferramentas mais complexas e detalhadas.
Por fim, a arquitetura de separação de arquivos utilizou o modelo MVC (Model-View-Controller), o qual é em Controladores, DAOs e Domain, no contexto da aplicação.
## Página inicial
Está página é a login.jsp, em que é cumprido os requisitos R3 e R4, além de conter um campo de login para o usuário se autenticar como Cliente, Locadora ou Admin.
Portanto, para atender os requisitos, a página apresenta todas as locadoras e disponibiliza um filtro para exibir apenas as locadoras de uma determinada cidade, a qual é escolhida pelo usuário.
![image](https://github.com/thaleswinther/DSW1-Servlets/assets/123703093/7f9a6d4d-1d89-404f-bc66-b683baed22a2)


## Página do Administrador
Esta página é a pagina referente ao administrador, na qual é possível realizar o CRUD (Create, Read, Update & Delete) de Clientes e Locadoras. Dessa forma, está página atende os requisitos R1 e R2.
É importante mencionar, também, que apenas um usuário cadastrado como ADMIN tem acesso a essa página, ou seja, as permissões estão configuradas corretamente e não é possível outros usuáriso acessarem esta página (OBS: caso alguém tente acessar essa página pela URL, por exemplo, sem possuir tal permissão, aparecerá um mensagem de erro, informando que tal acesso não é permitido).
* Página do administrador logado
  
![image](https://github.com/thaleswinther/DSW1-Servlets/assets/123703093/97605f89-52ce-4925-be8e-7d23f0a465f2)

* CRUD de Clientes
![image](https://github.com/thaleswinther/DSW1-Servlets/assets/123703093/ef315ed3-bf6f-4779-a874-91839f1e7b24)

* CRUD de Locadoras
![image](https://github.com/thaleswinther/DSW1-Servlets/assets/123703093/030a7aae-51aa-4574-a9bd-afa8ed3dfe49)

* Página para adicionar um cliente
  
![image](https://github.com/thaleswinther/DSW1-Servlets/assets/123703093/7878e3cc-8335-4f9a-a119-0303b0216ef4)





## Página do Usuário
### Cliente
Esta é a página do usuário cliente, a qual atende os requisitos R5, R6 e R7. Nesta página é possível realizar o cadastro de uma locação, selecionando a locadora a partir de uma lista, a data e a a hora da locação (a hora é selecionada a partir de uma lista, para atender a especificação de que as locações tem que ser realizadas em horas inteiras). Além disso, também é possível visualizar todas as locações do cliente logado. Por fim, ao realizar a locação, o cliente e a locadora são informados via email.
É importante mencionar, também, que impossibilidades e incoerências são tratadas: um cliente cadastrar mais de uma locação no mesmo dia e horário, uma locadora possuir mais de uma locação no mesmo dia e horário e o cliente tentar cadastrar uma locação em uma data anterior a data atual. Qualquer um desses casos retornará uma mensgem de erro amigável ao usuário.
* Página inicial do Cliente
  
![image](https://github.com/thaleswinther/DSW1-Servlets/assets/123703093/c208c2e9-3096-4065-bea4-742a2b4e707d)
* Página para cadastar uma locação
 
![image](https://github.com/thaleswinther/DSW1-Servlets/assets/123703093/cd02ee25-cac0-48b0-923a-1baf6292ceae)

* Tratando a especificação de locações só poderem ser feitas em horas inteiras
  
![image](https://github.com/thaleswinther/DSW1-Servlets/assets/123703093/20e42c6b-9d73-4b36-84c0-db9d16bfa82e)

### Locadora
Está é a página do usuário locadora, a qual atende o requisito R8. Neste página é possível visualizar todas as locações da locadora logada.
![image](https://github.com/thaleswinther/DSW1-Servlets/assets/123703093/183967c7-31df-4a14-aa22-d45d4429ec46)

## Internacionalização do sistema
Por fim, para atender o requisito R9, o sistema também está disponível em EN-US, dependendo da configuração do navegador do usuário.

## Tratamento de erros
O sistema também trata os possíveis erros do sistema de forma amigável ao usuário, mostrando-o uma mensagem explicando o que erro ocorrido.
* Alguns exemplos:
  * Cadastrar um usuário com um email já em uso:
    ![image](https://github.com/thaleswinther/DSW1-Servlets/assets/123703093/3cb36feb-f604-4423-a52d-fd75886b2892)

  * Cadastrar um usuário com um CPF já em uso:
    
      ![image](https://github.com/thaleswinther/DSW1-Servlets/assets/123703093/f0e7b287-ab2b-48d9-931d-a609c6b91daf)
  * Cadastrar uma locação em um data anterior a data atual:
    
    ![image](https://github.com/thaleswinther/DSW1-Servlets/assets/123703093/396293bf-5035-44cd-bdda-e953ab0f7c7f)
  * Acessar uma página pela URL, sem possuir a autorização necessária:
    
    ![image](https://github.com/thaleswinther/DSW1-Servlets/assets/123703093/97ff2aa8-f85a-4dee-8224-c9fec3653cd4)


# Colaboradores
* Thales Winther de Castro Moreira, aluno BCC UFSCar, github: https://github.com/thaleswinther
* Matheus Goulart Ranzani, github: https://github.com/matheusranzani 
* Arisa Abiko Sakaguti, aluna BCC UFSCar, github: https://github.com/arisaabiko
* Gabriel Ripper de Mendonça Furtado, aluno BCC UFSCar, github: https://github.com/gabripper






