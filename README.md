uc 15

Status: Em desenvolvimento

O stack do projeto é como segue abaixo:

Java 25.0.2 

Apache NetBeans IDE 29 

MySQL Workbench 8.0.46 

SQL Server 9.6.0

MySQL -connector -j-9.6.0

Time: Projeto Solo, sem time

Objetivo: Servir como sistema de cadastro de livros e fornecer simultaneamente as ferramentas de gestão do estoque da biblioteca 
através de uma hierarquia concisa de usuários e ferramentas para a averiguação do prazo dos empréstimos dos livros.

Funcionalidade:

Requisitos Funcionais:

Autenticação de usuário

Encerramento de sessão

Cadastro de Leitor

Cadastro de Gestor

Cadastro de Adminitrador

Cadastro de Livro

Catálogo de Livro

Aluguel de Livro

Restrição de aluguel duplicado

Controle de Estoque

Painel de Visualização da Lista de ALugéis do Leitor

Controle de Aluguéis

Acessibilidade Visual (aluguéis vencidos ficam em vermelho, livros sem estoque ficam em cinza escuro)

Requisitos Não Funcionais:

Hashing de Senha (SHA2)

Isolamento de Sessão 

Persistência de Dados via Mysql

Arquitetura MVC

Usabilidade por Perfil de Usuário (perfis diferentes possuem menus próprios com opções distintas)

Prazo de Devolução Pré Definido



