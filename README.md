# Jagger - LEFEVRE

## Tester le projet

- Tester son propre code dans le terminal :
```shell
$ make
$ make run
```
>Attention :  c'est un Scope qui est attendu en entrée (se référer à la [grammaire](#grammaire))

- Il est plus simple de tester le projet avec un fichier .txt. Pour cela vous pouvez modifier le fichier *tests/test.txt* en prenant en compte la contrainte énnoncée précédement et exécuter les lignes suivantes :
```shell
$ make
$ java -cp ./out/ src.Jagger tests/test.txt
```
- Nous avons écrits des tests unitaires avec JUnit pour simplifier le développement. Ces tests peuvent  être trouvés [ici](tests/JaggerTest.java) et lancés avec les lignes suivantes :
```shell
$ make
$ make check
```

- Si vous voulez lire le code en local sans être parasité par les fichiers générés par JavaCC vous pouvez entrer la ligne suivante :
 ```shell
$ make clean
```

## Travail réalisé
Une fois l'intégralité des fonctionnalités implémentés, nous avons voulu développer quelques fonctionnalités supplémentaires.    
  
Tout d'abord, les if cases peuvent prendre plusieurs instructions dans les body du then et du else à l'image du while. 
```javascript
let var i:=1 var a:=2
in 
	if true then(
		print(i),
		print(a)
	)else(print("error"))
end 
```
Nous avons d'ailleurs fait la même chose pour le for.
```javascript
let
in 
	for i:=1 to 3 do(
		print("Cnt:"),
		print(i)
	)	
end 
```
## Grammaire 
| Nom|Axiome |ID |
|--|--|--|
|factor|\<NUMBER> \| \<ID> \| \<STR> \| \<TRUE> \| \<FALSE> \| "(" R ")" \| '+'F \| '-'F \| '<>'F|F|
|term|F ( '\*' F \| '/' F \| '&&' F \| \<ASSIGN> F)*|T|
|expression| T ('+' T \| '-' T \| '\|\|' T)*|E|
|relation |(E\|T) ('<'R \| '>'R \| '<='R \| '>='R \| '='R)|R|
|print|\<PRINT> '(' R ')'|P|
|ternary |\<IF> R \<THEN> '(' St(,St)* ')' \<ELSE> '(' St(,St)* ')'|Te|
|statement|P \| R \| W \| F \| S \| D|St|
|assignment |\<ID> \<ASSIGN> R|A|
|declaration| \<VAR> \<ID> \<ASSIGN> R|D|
|for_loop |\<FOR> R \<TO> R \<DO> '(' St(,St)* ')'|F|
|while_loop|\<WHILE> R \<DO> '(' St(,St)* ')'|W|
|scope|\<LET> (D)+ \<IN> St'(',St')'* \<END>|S|

## Difficultés
### Problèmes de grammaire
Le problème qui nous a suivis le plus longtemps est l'élaboration de la grammaire. Nous avons souvent du la retravailler pour des problèmes d'ambiguïté ou de priorité. Un bon exemple est le suivant. Nous ne pouvions pas évaluer une variable seule. En effet, il y avait une ambiguïté entre une variable et l'affectation d'une variable. Pour lever l'ambiguïté, il faut mettre la variable entre parenthèses comme suit :
```javascript
let var a:= 1
in 
	(a) // a seul ne fonctionnait pas
end 
```
En retravaillant sur la grammaire nous avons réussi a supprimer ce problème.
### Binder
Nous avons passé beaucoup de temps sur les scopes et le binder. Il nous a fallu relire le cours plusieurs fois pour comprendre le principe du **threaded AST**. Nous avons donc créé deux classes : VarDecl et Var.  
  
VarDecl contient l'expression affectée à une variable lors de sa déclaration et le Binder va venir lier une Var à sa déclaration.
```javascript
let var a:= 1 
// on instancie ici une VarDecl qui contien une Expression et l'id de la variable
in 
	print(a) 
// on instancie ici une Var que le Binder va lier avec la VarDecl de même id
end 
```