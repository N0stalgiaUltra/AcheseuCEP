# AcheseuCEP

App criado para ajudar usuários a encontrarem seu CEP

<h1 align="center">Ache seu CEP</h1>
<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api"> <img alt="API" src="https://img.shields.io/badge/API-25%2B-brightgreen.svg?style=flat"/></a>

  <br>
  <a href="https://wa.me/+5521990399627"><img alt="WhatsApp" src="https://img.shields.io/badge/WhatsApp-25D366?style=for-the-badge&logo=whatsapp&logoColor=white"/></a>
  <a href="https://www.linkedin.com/in/vinicius-santos-b217b5168/"><img alt="Linkedin" src="https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white"/></a>
  <a href="mailto:viniciusantos0898@gmail.com"><img alt="Gmail" src="https://img.shields.io/badge/Gmail-D14836?style=for-the-badge&logo=gmail&logoColor=white"/></a>
</p>

<p align="center">  

Ache seu CEP é um app desenvolvido para Android de maneira nativa com Kotlin. O app é capaz de buscar por um determinado CEP ou encontrar um CEP com informações apresentadas pelo usuário, como Estado, Cidade e Nome da rua (ou algum nome da rua). Além disso, o usuário também pode favoritar endereços para que eles fiquem guardados no cache do celular. Falando de forma técnica, o design do App é facilitado com a utilização do Recycler View e Live Data; a organização é feita com MVVM e Clean Architecture, de modo que o codigo da aplicação seja cada vez mais desacoplado, legível e reutilizavel; ademais, os UseCases foram devidamente implementados e atualizados na camada de domínio. Além disso, o app também utiliza os Fragments junto do NavGraph para exibir tanto as tabs de busca e favoritos. Por fim, em relação aos dados, existem dois tipos de comunicação: remota, feita através do Retrofit com OKHTTP; e local, graças ao Room. 

</p>

</br>

<p float="left" align="center">
  <img alt="screenshot" width="30%" src="https://github.com/N0stalgiaUltra/AcheseuCEP/blob/main/screenshots/main_menu.png"/>
  <img alt="screenshot" width="30%" src="https://github.com/N0stalgiaUltra/AcheseuCEP/blob/main/screenshots/searchCep.png"/>
  <img alt="screenshot" width="30%" src="https://github.com/N0stalgiaUltra/AcheseuCEP/blob/main/screenshots/fav_tab.png"/>
</p>

## Download
<!--
<a href='https://play.google.com/store/apps/details?id=com.n0stalgiaultra.head2head'><img alt='Disponível no Google Play' width="30%" src='https://play.google.com/intl/en_us/badges/static/images/badges/pt_badge_web_generic.png'/></a> -->
App ainda não está disponível para download.

## Tech Stack/Tecnologias Utilizadas

- Minimo SDK Level 25
- <a href="https://kotlinlang.org/">Kotlin</a>

- [Jetpack](https://developer.android.com/jetpack?hl=pt-br)
  - Live Data: Usado para observar dados de maneira reativa, permitindo que os dados utilizados da camada de view sejam atualizados de maneira automática.
  - Corrotinas: Padrão de projeto utilizado para executar tarefas assincronas simultaneamente. 
  - Lifecycle: Observa o ciclo de vida do Android e manipula estados da UI após cada mudança no ciclo de vida.
  - Fragments: Um Fragment representa o comportamento ou uma parte da interface do usuário em um Activity.
  - Navigation: A navegação se refere às interações que permitem aos usuários navegar, entrar e sair de diferentes partes do conteúdo no aplicativo.
  - ViewModel: Usado como uma camada que recupera dados da camada do Model e aplica na View.
  - ViewBinding: Gerencia Views de layouts XML em Kotlin. 
  - Recycler View:  Apresenta uma maneira mais eficiente de exibir uma lista de Views na tela, reciclando as Views em busca de uma performance melhorada.
  - Room: Biblioteca de persistencia que oferece uma camada de abstração do SQLite do sistema, permitindo um acesso mais robusto ao banco de dados.
    
- Architecture
  - MVVM (Model-View-ViewModel) + Clean Architecture: O primeiro item é utilizado para separar a logica da UI do aplicativo, ajutando com testagem e organização de codigo, Somado a Clean Architecture que ajuda a separar o sistema em camadas bem definidas, que funcionam com independencia, facilitando a escalabilidades e manutenção.
  - Repository pattern: Esse padrão de projeto ajuda com a abstração da camada de Data.

- Libraries
  - [Retrofit & OkHttp](https://square.github.io/retrofit/): Utilizado para se comunicar com uma API RESTful e desserializar os dados.
  - [Koin](https://insert-koin.io/): Utilizado para Injeção de Dependência
  - [SDP](https://github.com/intuit/sdp): Uma biblioteca Android que providencia um novo tipo de unidade de tamanho, que é escalavel com o tamanho das telas, abrangendo diversos tipos de dispositivos.
  - [Mockk](https://mockk.io/): Biblioteca de Mock para Kotlin, utilizado em Testes Unitários
  - [JUnit](https://junit.org/junit5/): Framework de testes em Java e JVM

## Architecture/Arquitetura
**AcheSeuCepAPP** foi desenvolvido utilizando MVVM, Clean Architecture e o Repository pattern. Seguindo a [Recomendação oficial da Google](https://developer.android.com/topic/architecture)
</br>


## Third Party APIs/API de terceiros

[VIACep](https://viacep.com.br/), Essa API gratuita providencia todos os tipos de informações relacionados a endereços e CEPs no Brasil.

## Features/Funcionalidades

### Busque por um CEP específico
<img alt="search_cep" width="25%" src="https://github.com/N0stalgiaUltra/AcheseuCEP/blob/main/screenshots/searchCepGIF.gif"/>

Realiza uma busca aos dados remotos, retornando as informações relacionadas a um cep inserido.

### Busque por um CEP de um endereço
<img alt="advanced_search" src="https://github.com/N0stalgiaUltra/AcheseuCEP/blob/main/screenshots/advancedSearchCep.gif" width="25%"/>

Realiza uma busca aos dados remotos com outros dados de entrada, sendo eles: Estado (providenciado pelo App), Cidade e Nome da Rua. 
Caso o usuário não saiba o nome completo da rua, a busca exibirá todas as ruas que contiverem o nome inserido.


### Salve os CEPs/Endereços favoritos
<img alt="favorite_cep" src="https://github.com/N0stalgiaUltra/AcheseuCEP/blob/main/screenshots/fav_tab.png" width="25%"/>

Usuários podem salvar as informações dos CEPs favoritos de maneira local, não precisando do acesso à internet ou ao banco para consultar suas informações!
## Testes
Os testes unitários e instrumentados serão inseridos no projeto e adicionados à um arquivo .md à parte! 


# License
```xml

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```

# Privacy Policy

```html 
Privacy Policy
Your privacy is important to us. It is Head to Head App's policy to respect your privacy regarding any information we may collect from you across our website, Head to Head App, and other sites we own and operate.

We only ask for personal information when we truly need it to provide a service to you. We collect it by fair and lawful means, with your knowledge and consent. We also let you know why we’re collecting it and how it will be used.

We only retain collected information for as long as necessary to provide you with your requested service. What data we store, we’ll protect within commercially acceptable means to prevent loss and theft, as well as unauthorised access, disclosure, copying, use or modification.

We don’t share any personally identifying information publicly or with third-parties, except when required to by law.

Our website may link to external sites that are not operated by us. Please be aware that we have no control over the content and practices of these sites, and cannot accept responsibility or liability for their respective privacy policies.

You are free to refuse our request for your personal information, with the understanding that we may be unable to provide you with some of your desired services.

Your continued use of our website will be regarded as acceptance of our practices around privacy and personal information. If you have any questions about how we handle user data and personal information, feel free to contact us.

More Information
Hopefully that has clarified things for you and as was previously mentioned if there is something that you aren't sure whether you need or not it's usually safer to leave cookies enabled in case it does interact with one of the features you use on our site.

This policy is effective as of Sep 2023.
```

