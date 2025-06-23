# 🧾 EmployeeManagerPro

Sistema simples de gerenciamento de **Funcionários** e **Departamentos** com funcionalidades completas de CRUD utilizando Java e MySQL. Ideal para fins de estudo ou como base para projetos maiores.

---

## ⚙️ Tecnologias Utilizadas

- **Linguagem:** Java 17
- **Banco de Dados:** MySQL
- **Arquitetura:** MVC (Model-View-Controller)
- **Padrões:** DAO
- **IDE Sugerida:** IntelliJ IDEA
- **Ferramentas Auxiliares:**
  - JDBC
    
---

## 🎯 Objetivo

Desenvolver um sistema desktop em Java para gerenciar funcionários e departamentos de forma eficiente, permitindo operações de cadastro, listagem, atualização e exclusão com persistência em banco de dados.

---

## 📦 Entidades

### 🧑 Funcionario

| Atributo     | Tipo         | Descrição                      |
|--------------|--------------|-------------------------------|
| id           | Long         | Identificador único           |
| nome         | String       | Nome completo                 |
| email        | String       | E-mail corporativo            |
| salario      | Double       | Salário mensal                |
| departamento | Departamento | Departamento relacionado      |

---

### 🏢 Departamento

| Atributo | Tipo   | Descrição               |
|----------|--------|-------------------------|
| id       | Long   | Identificador único     |
| nome     | String | Nome do departamento    |

---

## 🛠️ Funcionalidades

### Funcionário
- ✅ Cadastrar novo funcionário  
- ✅ Listar todos os funcionários  
- ✅ Atualizar dados de um funcionário  
- ✅ Excluir funcionário  
- ✅ Buscar por nome ou ID  

### Departamento
- ✅ Cadastrar novo departamento  
- ✅ Listar todos os departamentos  
- ✅ Atualizar dados do departamento  
- ✅ Excluir departamento  
- ✅ Buscar por nome ou ID

## 📌 Autor
Desenvolvido por Erick Mendes.
Este projeto é livre para uso acadêmico e profissional.
