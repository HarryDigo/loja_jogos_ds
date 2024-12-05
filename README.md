# Loja de jogos DS

Projeto do 4º bimestre de DS

## Planejamento

Será um programa que lida com o estoque de jogos físicos ou digitais de uma loja, por enquanto será bem simples.

### Extras

Infelizmente não conseguir fazer os campos formatados demonstrarem seus formatos, então o formato dos campos de inserção, são:
- Lançamento: "AAAA-MM-DD"
- Preço: "####0.00"
- Classificação etária: "#0"
    * #: qualquer dígito, ou vazio;
    * 0: qualquer dígito, padrão 0;

Na source, está incluso o banco de dados final e uma versão vazia deste
Todas as imagem utilizadas também foram salvas em assets

Desculpa pela demora professores, estava gripado e me sentindo mal para gravar o vídeo e comentar o código a tempo, no caso, ainda tenho que comentar o código, mas farei isto o mais cedo possível, obrigado pela compreensão.

Vários erros não demonstrados no vídeo foram tratados, como tentar editar/deletar registros inexistentes, erros com arquivos incorretos ou corrompidos no lugar das imagens no bd e erros gerais que posso ter esquecido aqui.

Comando para compilar (dentro de src): javac -d ../bin \*.\* config/\*.java controllers/\*.java repositories/\*.java views/\*.java

Comando para rodar (dentrod de src): java -cp "../bin;../lib/\*" App