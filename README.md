# Loja de jogos DS

Projeto do 4º bimestre de DS

## Planejamento

Será um programa que lida com o estoque de jogos físicos ou digitais de uma loja, por enquanto será bem simples.

### Objetivos secundários

Além da JTable obrigatória, pretendo fazer uma tela que tem um catálogo em grid de todos os jogos com uma imagem e título, podendo selecionar os registros de cada (possivelmente pesquisa de títulos nesse menu)

Aprender a fazer layout mais estilo html e CSS, de um jeito que fica mais bonito e correto, mais profissional

### Modelos

Jogo:
- id: 5 dígitos aleatórios (meio estúpido, mas gosto assim)
- titulo: string
- tipo: digital ou físico
- genero: relação many to many (de algum jeito)
- classificacao: etaria
- estudio: string
- preco: decimal
- lancamanto: data

Genero:
- id: auto increment provavelmente
- nome: string

GeneroJogo:
- Jogo: (PK/FK)
- Genero: (PK/FK) com cascade de delete
