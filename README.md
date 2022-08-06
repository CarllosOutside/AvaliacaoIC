# Avaliacao de IC
### Alunos

- PEDRO AFONSO VANDER DA SILVA
- FERNANDO SOBUTKA
- GUSTAVO MARENDA DE LIMA
- PEDRO HENRIQUE CEBULISKI

**EXERCÍCIOS**

**1:** [PROBLEMA DO CAIXEIRO  VIAJANTE](#pcv)

**2:** [PROBLEMA DA TORRE DE HANOI](#hanoi)
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

------
## <a href="https://github.com/CarllosOutside/AvaliacaoIC/tree/main/src/main/java/com/mycompany/avaliacaoic/problema2Hanoi">TORRE DE HANOI</a> <a name="hanoi"></a>

### Para este problema, modificamos a função do aima <a href="https://github.com/CarllosOutside/AvaliacaoIC/blob/main/src/main/java/aima/core/search/uninformed/DepthLimitedSearch.java"> depthLimitedSearch</a> de modo que cada nó seja acessado uma única vez. A quantidade total de nós são todas as combinações `C` possíveis das k peças nas 3 torres.

Para `k=3`, temos `C=27`. A seguinte fórmula dá o número total de combinações para k=3: $C = k!\cdot \Big(1+\frac{3}{(k-2)!}\Big)+3$.

Como a busca se encerra quando o objetivo é achado, e queremos um limite de `k=7`, fizemos o calculo para o valor máximo de k, e fixamos um limite de nós `C=2187` (Como no algoritmo depthLimitedSearch o limite C diminui uma unidade para cada nó `child` visitado, podemos visitar até um total de 2187 nós, o que garante que encontraremos solução para 7 discos).
Este valor 2187 foi encontrado usando o seguinte procedimento numerico: ![](https://i.ibb.co/hKL1jn8/hanoi7disks.jpg)

Para conferir que o calculo acima está correto, altere a função test() em HGoaltest para retornar sempre falso. Depois altere a funcao RecursiveDLS da classe DepthLimitedSearch, para imprimir visitedNodes.size() antes de return fresult. Desse modo, o programa executará até que todos os nós possíveis sejam visitados, não achará a solução, retornará um valor nulo, e imprimirá o número de nós visitados(27 para k=3, 2187 para k=7). 

A solução para o problema não é ótima. Para `3 discos`, obtemos `14 movimentos com a busca no grafo` , diferente dos `7 movimentos da solução ótima`. 

Para `7 discos`, obtemos uma solução com `1094 movimentos` buscando no grafo, ao invés dos `127 da solução ótima`. A velocidade do algoritmo, porém, é muito alta, e a solução é encontrada em segundos, devido ao número limitado de nós existentes(após nossa modificação do código de busca de profundidade limitada, cada nó é visitado uma única vez).
