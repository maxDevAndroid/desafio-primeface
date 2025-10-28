# 🚀 Desafio Técnico — Desenvolvedor(a) Java EE / PrimeFaces

## 📘 Visão Geral

Este projeto foi desenvolvido como parte de um desafio técnico com o objetivo de demonstrar domínio em **Java EE**, **JSF**, **PrimeFaces** e boas práticas de arquitetura, componentização e integração entre frontend e backend.

A aplicação implementa um **módulo de cadastro e listagem de produtos**, permitindo:
- Criar novos produtos (Nome, Preço, Categoria);
- Listar produtos cadastrados em tempo real (AJAX);
- Exibir mensagens de sucesso ou erro (Growl);
- Atualizar a tabela sem recarregar a página.

---

## 🧩 Stack Técnica

- **Java:** 21 (OpenJDK Corretto)
- **Jakarta EE / Java EE**
- **JSF + PrimeFaces 13**
- **Maven 3.9+**
- **Servidor:** WildFly 30+
- **Escopo CDI:** `@SessionScoped`, `@ApplicationScoped`
- **Injeção de dependência:** `@Inject`
- **Empacotamento:** WAR

---

## ⚙️ Estrutura do Projeto

projeto-teste/
├── pom.xml
├── README.md
├── src/
│ ├── main/java/com/teste/model/Product.java
│ ├── main/java/com/teste/service/ProductService.java
│ ├── main/java/com/teste/bean/ProductBean.java
│ ├── main/java/com/teste/bean/FacesUtil.java
│ ├── main/webapp/product.xhtml
│ ├── main/webapp/index.xhtml
│ └── main/webapp/WEB-INF/web.xml
│ └── main/webapp/WEB-INF/jboss-deployment-structure.xml

---

## 🧠 Arquitetura

A arquitetura segue o padrão **JSF + CDI**, separando responsabilidades:

- **Model (`Product`)** — Representa a entidade de produto.
- **Service (`ProductService`)** — Mantém e gerencia a lista de produtos em memória.
- **Managed Bean (`ProductBean`)** — Controla a interação da tela, persistência e exibição de mensagens.
- **FacesUtil** — Classe utilitária para mensagens de feedback via `FacesContext`.

---

## 📦 Build e Deploy

### 1️⃣ Pré-requisitos

Certifique-se de ter instalado:
- [Java 21+ (Corretto ou OpenJDK)](https://adoptium.net/)
- [Maven 3.9+](https://maven.apache.org/install.html)
- [WildFly 26+](https://www.wildfly.org/downloads/)

Verifique as versões:
```bash
java -version
mvn -v
```

### 2️⃣ Gerar o WAR
Dentro do diretório do projeto:
```bash
mvn clean package
```
O arquivo gerado estará em:
**target/projeto-teste.war**

### 3️⃣ Fazer o Deploy no WildFly
Copie o WAR para o diretório de deploy do WildFly:
```bash
cp target/projeto-teste.war /usr/local/opt/wildfly-as/libexec/standalone/deployments/
```

### 4️⃣ Iniciar o Servidor
Execute:
```bash
brew services start wildfly-26
```
ou manualmente:
```bash
JBOSS_HOME="/usr/local/opt/wildfly-26/libexec" WILDFLY_HOME="/usr/local/opt/wildfly-26/libexec" /usr/local/opt/wildfly-26/libexec/bin/standalone.sh
```

### 🌐 Acesso à Aplicação
Após o deploy bem-sucedido, acesse:
👉 http://localhost:8080/desafio-primeface/product.xhtml

### 💡 Funcionalidades
 Recurso Descrição
**Cadastro de Produto**
- Preenche nome, preço e categoria e salva com feedback visual.
  **Listagem Dinâmica**	
- Tabela atualiza via AJAX após salvar um produto.
  **Growl Message**
- Exibe alertas de sucesso ou erro.
  **Validação**	
- Campos obrigatórios com feedback imediato.

### 🧰 Estrutura Técnica Interna
✅ CDI e JSF
Uso correto das anotações:
- @Named
- @SessionScoped
- @Inject
- @ApplicationScoped

✅ PrimeFaces Components
p:inputText, p:commandButton, p:dataTable
p:growl para mensagens dinâmicas
update para atualização parcial da página via AJAX

✅ Clean Code
Classes pequenas e coesas
Separação clara de responsabilidades
Código comentado e intuitivo

### 🔒 Observações Técnicas
Persistência em memória (sem banco de dados).
Estrutura facilmente expansível para integração futura com JPA ou REST.
Projeto testado e compatível com WildFly 26+ no macOS (Homebrew install).

### 👨‍💻 Autor
Maxwell Chaves

Android & Backend Developer
🔗 linkedin.com/in/maxwellchavesdev/
