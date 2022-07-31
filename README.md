# Avaliacao de IC
### Alunos

- PEDRO AFONSO VANDER DA SILVA
- FERNANDO SOBUTKA
- GUSTAVO MARENDA DE LIMA
- PEDRO HENRIQUE CEBULISKI

**EXERCÍCIOS**

**1:** [PROBLEMA DO CAIXEIRO  VIAJANTE](#pcv)

**2:** [...](#http)
<br/>
## <a href="https://github.com/CarllosOutside/AvaliacaoIC/tree/main/src/main/java/com/mycompany/avaliacaoic/problema1">CAIXEIRO VIAJANTE</a> <a name="pcv"></a>

#### Gerando e Lendo Grafos

São gerados grafos com caminho hamiltoniano, com quantidade de vértices definida pelo problema. A classe geradora de grafos <a href="https://github.com/CarllosOutside/AvaliacaoIC/blob/main/src/main/java/com/mycompany/avaliacaoic/problema1/pt1Grafo/GrafoGenerator.java">GrafoGenerator</a> cria $C_{2}^{maxVertex} = \frac{n\cdot(n-1)}{2}$ arestas no mínimo, de modo que garanta a existência de caminhos hamiltonianos (n = número de vértices).
Mais arestas são criadas se o usuário escolher um valor de `e` (número de arestas) maior que a combinação acima.

Após criar uma lista de arestas, com `Vértice A` $\rightarrow$ `Vértice B` com pesos, a mesma classe geradora salva os dados do grafo em um arquivo .txt.

A classe leitora de grafos <a href="https://github.com/CarllosOutside/AvaliacaoIC/blob/main/src/main/java/com/mycompany/avaliacaoic/problema1/pt1Grafo/GrafoReader.java"> GrafoReader </a> realiza o processo inverso, lendo o arquivo .txt e criando uma lista de arestas.

---

#### Criando caminhos e populações

##### PATH

A classe <a href="https://github.com/CarllosOutside/AvaliacaoIC/blob/main/src/main/java/com/mycompany/avaliacaoic/problema1/pt2PCV/Path.java"> Path </a> representa objetos do tipo caminho. Ela contem um caminho de tamanho `n` (número de vértices), que perfaz todo o ciclo hamiltoniano, passando uma vez por cada vértice.

Cada caminho tem seu custo, e existem vários caminhos possíveis no grafo. Esta classe calcula o custo de um caminho, que é proporcional aos pesos que definimos da classe geradora de grafos. O fitness é inversamente proporcional ao custo(soma de pesos das arestas) de um caminho.

Dentro desta classe também definimos a função de mutação.

##### POPULATION

A classe <a href="https://github.com/CarllosOutside/AvaliacaoIC/blob/main/src/main/java/com/mycompany/avaliacaoic/problema1/pt2PCV/Population.java">Population</a> representa objetos que são listas de caminhos.

Esta população tem tamanho, taxa de mutação e número de vértices(genomas). O constructor da classe cria os elementos aleatórios de uma população, que são objetos do tipo Path, com custos.

Escolhemos através de PickRank() os dois melhores Path's de uma população, e usamos crossing() para gerar todos os novos elementos de uma nova geração de objetos do tipo Path.
Podemos evoluir uma população realizando crossing, unindo genomas das melhores soluções e em seguida realizando mutações.

##### Main

Na classe <a href="https://github.com/CarllosOutside/AvaliacaoIC/blob/main/src/main/java/com/mycompany/avaliacaoic/problema1/pt2PCV/Main.java">main</a> unimos todas as anteriores. Geramos e lemos grafos, e em seguida criamos populações de caminhos hamiltonianos usando esses grafos(no exemplo, criamos populações de 50 indivíduos). 

Fazemos com que 100 gerações evoluam+mutem em cada população, e depois pegamos o melhor caminho obtido. Em seguida, criamos uma nova população, com elementos aleatórios novos, e repetimos o processo.
