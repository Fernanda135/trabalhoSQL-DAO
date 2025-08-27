# 📚 Projeto MidiasDAO

Este projeto é um **sistema de gerenciamento de mídias** desenvolvido em **Java** com conexão ao banco de dados **PostgreSQL**.  

Ele permite realizar operações de **CRUD** (Criar, Ler, Atualizar e Deletar) em uma tabela de mídias, além de algumas consultas personalizadas.

---

## ⚙️ Funcionalidades
- **Cadastrar mídia** → insere uma nova mídia no banco.  
- **Buscar mídia por ID** → localiza uma mídia específica.  
- **Listar todas as mídias** → exibe todas as mídias cadastradas.  
- **Atualizar mídia** → altera os dados de uma mídia existente.  
- **Deletar mídia** → remove uma mídia pelo seu ID.  
- **Total de mídias** → retorna a quantidade total cadastrada.  
- **Listar por tipo** → exibe mídias filtradas por tipo (ex: filme, série, jogo).  
- **Listar por franquia** → exibe mídias de uma franquia específica.  
- **Quantidade por tipo** → mostra quantas mídias existem de cada tipo.  

---

## 🗄️ Estrutura do Projeto
- `Main.java` → menu interativo no terminal.  
- `Midia.java` → classe modelo que representa uma mídia.  
- `MidiaDAO.java` → contém os métodos de acesso ao banco (DAO).  
- `ConnectionFactory.java` → cria a conexão com o banco PostgreSQL.  
