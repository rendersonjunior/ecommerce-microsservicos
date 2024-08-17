# Projeto Java Spring com Microsserviços

Este projeto implementa uma arquitetura de microsserviços utilizando Java Spring, Docker, Kubernetes e Kafka.

O objetivo deste projeto é servir como uma base de estudo para aprofundar conhecimentos em diversas tecnologias 
aplicáveis a uma arquitetura de microsserviços. As referências do projeto fornecem uma base prática e fundamentação 
teórica, mas, além disso, foram incorporadas outras tecnologias e boas práticas, baseadas em experiências prévias, 
quando apropriado.

## Índice

1. [Introdução](#introdução)
2. [Tecnologias](#tecnologias)
3. [Pré-Requisitos](#pré-requisitos)
    - [Minikube](#minikube)
    - [Kubectl](#kubectl)
4. [Instalação](#instalação)
5. [Uso](#uso)
6. [Referências](#referências)
7. [Licença](#licença)

## Tecnologias

- Java 17
- Lombok
- Postgres 13
- MapStruct
- JUnit/Mockito
- JaCoCo (Cobertura de Testes)
- Spring Gateway
- Docker
- Kubernetes (Minikube)

## Pré-Requisitos

### Minikube

O projeto foi configurado em um cluster do Kubernetes. O Minikube foi escolhido por ser um ambiente de desenvolvimento local fácil de usar, além de permitir simular um ambiente de produção e integração com CI/CD. Os passos para a implantação do Minikube no Ubuntu podem ser encontrados no link:

[Instalação do Minikube](https://minikube.sigs.k8s.io/docs/start/?arch=%2Fwindows%2Fx86-64%2Fstable%2F.exe+download)

### Kubectl

Para interação com o cluster do Kubernetes (Minikube), foi escolhido o Kubectl, que permite realizar operações como criar, inspecionar, atualizar e excluir recursos do Kubernetes, além de monitorar o status de aplicações e gerenciar o ciclo de vida dos pods e outros recursos.

[Instalação do Kubectl](https://kubernetes.io/pt-br/docs/tasks/tools/install-kubectl-linux/)

### Kafka
(Em construção...)

## Instalação

### 1. Build dos Projetos

Navegue até a raiz dos projetos e execute os seguintes comandos para construir os projetos usando o Maven:
```bash
mvn -f shopping-client/ clean install
mvn -f ecommerce-user-api/ clean install
mvn -f ecommerce-product-api/ clean install
mvn -f ecommerce-shopping-api/ clean install
```

### 2. Criação das Imagens Docker

Para que a criação das imagens ocorra dentro do cluster, é necessário aplicar o comando:

```bash
eval $(minikube docker-env)
```
Em seguida, crie as imagens Docker:
```bash
docker build -t loja/ecommerce-user-api ecommerce-user-api/ 
docker build -t loja/ecommerce-product-api ecommerce-product-api/
docker build -t loja/ecommerce-shopping-api ecommerce-shopping-api/
```

### 3. Aplicação das Configurações do Banco de Dados
Aplique os arquivos presentes na pasta minikube-configuration no Kubernetes. Essa pasta contém as configurações do banco
de dados e do usuário do cluster:
```bash
kubectl apply -f minikube-configuration/postgres-deployment.yaml
kubectl apply -f minikube-configuration/postgres-service.yaml
kubectl apply -f minikube-configuration/postgres-configmap.yaml
kubectl apply -f minikube-configuration/cluster-user.yaml
```

### 4. Aplicação dos Microsserviços no Kubernetes
Aplique os microsserviços que estão nas pastas de deploy de cada projeto:

```bash
kubectl apply -f ecommerce-user-api/deploy/deployment.yaml
kubectl apply -f ecommerce-user-api/deploy/service.yaml

kubectl apply -f ecommerce-product-api/deploy/deployment.yaml
kubectl apply -f ecommerce-product-api/deploy/service.yaml

kubectl apply -f ecommerce-shopping-api/deploy/deployment.yaml
kubectl apply -f ecommerce-shopping-api/deploy/service.yaml
kubectl apply -f ecommerce-shopping-api/deploy/configmap.yaml
```
Obs: O `ecommerce-shopping-api` precisa aplicar o ConfigMap para que seja possível, a partir deste microsserviço, 
acessar os demais, conhecendo a rota interna do Kubernetes pelo nome do serviço.

### 5. Aplicação do Ingress Controller
Aplique o Ingress Controller para gerenciar o tráfego externo que entra no cluster e distribuir para os serviços 
internos com base em regras definidas. Ele atua como um load balancer que lida com o roteamento HTTP e HTTPS para os serviços.

```bash
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-ngix/controller-v1.11.1/deploy/static/provider/cloud/deploy.yaml
```
Obs: Esse passo também pode ser feito via add-on pelo Minikube:

```bash
minikube addons enable ingress
```

Após a instalação deste recurso, aplique a configuração do Ingress:
```bash
kubectl apply -f minikube-configuration/gateway-ingress.yaml
```

## Uso
Após seguir os passos acima, verifique se os serviços estão expostos pelos seguintes endereços:
```bash
curl http://localhost/user
curl http://localhost/product
curl http://localhost/shopping
```
Caso o cluster esteja sendo instalado em um WSL (Windows Subsystem for Linux), pode ser necessário criar um túnel de 
acesso do Minikube para a máquina local:
```bash
minikube tunnel
```
Obs: Este comando requer privilégios de root e o terminal deve permanecer aberto para uso. Após esse passo, 
teste novamente o acesso aos serviços do cluster.

## Referências
- *Backend Java com Microsserviços, Spring Boot e Kubernetes* de Eduardo Santana;
- *Apache Kafka e Spring Boot* de Eduardo Santana.<br>

## Licença
Este projeto está licenciado sob a Licença MIT.