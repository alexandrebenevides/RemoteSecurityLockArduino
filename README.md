# Remote Security Lock Arduino

[![Kotlin](https://img.shields.io/badge/Kotlin-Android-green)](https://kotlinlang.org/)
[![Arduino](https://img.shields.io/badge/Arduino-blue)](https://www.arduino.cc/)

Este é um projeto Android desenvolvido em Kotlin que permite controlar uma fechadura utilizando comunicação Bluetooth. A fechadura em questão foi criada utilizando Arduino. Com este aplicativo, os usuários podem liberar a fechadura de forma conveniente e segura.

## Funcionalidades Principais

- **Controle da Fechadura:** Os usuários podem liberar a fechadura através do aplicativo.
- **Segurança:** Se o usuário errar a senha três vezes ou mais, um alarme é disparado e um LED vermelho é ativado.
- **Feedback Visual:** Se a senha for inserida corretamente, a fechadura é liberada e um alarme verde é acionado.

## Requisitos

- Dispositivo Android com suporte a Bluetooth.
- Fechadura Arduino com capacidade de comunicação Bluetooth.
- Conhecimento básico de programação em Arduino para configurar a fechadura.

## Configuração

1. Clone o repositório para o seu ambiente de desenvolvimento local.
2. Conecte o dispositivo Android ao computador via USB.
3. Abra o projeto no Android Studio.
4. Compile e execute o aplicativo em seu dispositivo Android.

Certifique-se de que o dispositivo Android está pareado com a fechadura Arduino via Bluetooth antes de usar o aplicativo.

## Documentação

Na pasta `docs`, localizada na raiz do projeto, você encontrará recursos importantes para entender e implementar o projeto:

- **Projeto Arduino:** Contém o código-fonte do projeto Arduino utilizado na fechadura. Aqui você encontrará o código necessário para configurar a fechadura Arduino e estabelecer a comunicação Bluetooth com o aplicativo Android.

- **Protótipo do Circuito:** Também na pasta `docs`, você encontrará o protótipo do circuito para a criação do projeto. Este recurso é valioso para entender a montagem física da fechadura e seu circuito de controle.

Esses recursos são essenciais para compreender e modificar o projeto de acordo com suas necessidades específicas.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
