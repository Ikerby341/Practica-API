# `💻` PRÀCTICA 「JDBC i API」
> [!NOTE]
> L'objectiu d'aquest projecte es guardar i gestionar mitjançant una $${\color{red}API \space NO \space OFICIAL}$$ i una $${\color{lightgreen} API \space OFICIAL}$$

## ~ EXPLICACIÓ API ` 📡 `
> [!WARNING]
> Has d'actualizar la variable [APIKEY](https://github.com/Ikerby341/Practica-API/blob/main/src/main/java/sapalomera/controller/EndPointController.java#L21)


- 📂 [EndpointController.java](https://github.com/Ikerby341/Practica-API/blob/main/src/main/java/sapalomera/controller/EndPointController.java) <br/>
En aquesta clase gestionem tot el que té relació amb la API i el JSON.<br/>
Gestionem la creació del [JSON](https://github.com/Ikerby341/Practica-API/blob/main/brawlers.json) mitjançant les dades de l'API<br/>
En conclusió, obtenim les dades de la APi i després ho transformem a Objecte per poder mostrar les dades.
## ~ EXPLICACIÓ JDBC ` 🛢️ `
> [!NOTE]
> La nostre base de dades está amb [SQLite](https://github.com/Ikerby341/Practica-API/blob/main/BrawlStars.db), perqué és més sencill de gestionar i perqué ara mateix aquest programa no necesita
treballar sobre moltes conexions i consultes a la vegada, llavors no necesitem MySQL u otra base de dades per aixó.<br/>


En canvi, si hauria moltes consultes i instancies a la vegada, llavors si fa falta migrar a un altre base de dades com MySQL<br/>
En la base de dades guardem els brawlers actuals segons lo que ens dona la API oficial mitjançant el seu EndPoint.<br/>
A més a més, hi ha una opció del menú que pregunta si vols actualizar la base de dades sencera, para integrar els nous brawlers.

### ~ TAULES ESCOLLIDES ` 🥀 `
- 『Brawlers』․ Aquesta taula gestiona l'informació principal dels personatjes (ID, NOM i els camps per relacionar les altres taules).
- 『Gadgets』․ Gestionem els gadgets que té el personaje (Només té 2 máxims cada personatje)
- 『Rarity』․ Gestionem el tipus de rareza que és el personatje (Cómun, Raro, Super raro, Epico, Mitico, Legendario)
- 『StarPowers』․ Gestionem les habilitats finals dels personatjes (`La Ultimate` per als gamers)
