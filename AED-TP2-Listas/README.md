# AED — TP2: Listas Ligadas (2026/27)

Projeto inicial de **Algoritmos e Estruturas de Dados** da Universidade do Algarve.

> **Estado:** estrutura inicial. As funcionalidades de leitura de ficheiros, classificação, divisão e gravação ainda não estão implementadas. O formato dos registos e o modelo oficial do manual devem ser confirmados nos materiais da tutoria.

## Organização

```text
AED-TP2-Listas/
  src/
    Main.java
    Pessoa.java
    No.java
    ListaLigada.java
    FileManager.java
    ClassificadorGenero.java
    DivisorListas.java
  dados/entrada/
  resultados/texto/
  resultados/binario/
  docs/manual_tecnico.md
  testes/casos_teste.md
  out/
```

## Primeiro teste (estrutura da lista)

A partir desta pasta, em PowerShell:

```powershell
New-Item -ItemType Directory -Path out -Force | Out-Null
javac -encoding UTF-8 -d out src\*.java
java -cp out Main
```

Por agora, a `Main` demonstra apenas a inserção e visualização de três nomes. Não é a versão final do TP2.

## Planeamento da implementação

1. Confirmar o formato real dos dados fornecidos pelo docente.
2. Adaptar `Pessoa` a todos os campos do registo, sem inventar o esquema.
3. Implementar leitura e gravação em `FileManager` (formato textual e binário conforme especificado).
4. Implementar a regra de classificação acordada e documentar casos ambíguos.
5. Implementar `DivisorListas`, mantendo a ordem relativa dos registos.
6. Criar testes: listas vazias, um único elemento, repetidos, inválidos e integridade dos ficheiros.
7. Adaptar o manual ao template oficial.

## Observações

- `No` guarda uma referência para o elemento seguinte; `ListaLigada` mantém primeiro e último para inserir ao final em O(1).
- As pastas `resultados/` e `out/` são destinadas a ficheiros gerados; o `.gitignore` da raiz do repositório já os ignora. Os ficheiros `.gitkeep` servem apenas para tornar as pastas visíveis após a clonagem.
- **Não incluir dados pessoais reais em repositórios públicos** sem autorização.
