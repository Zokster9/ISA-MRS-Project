# ISA-MRS-Project

Projekat iz predmeta internet softverske arhitekture i metodologije razvoja softvera.

Studenti:
    Teodor Sakal Francišković, SW 22/2019  
    Matija Zarić, SW 24/2019
    Zoran Bukorac, SW 40/2019

Način pokretanja projekta:
    Pokretanje backend-a:
        - Pokretanje u IntelliJ-u: Otvoriti folder SpringProject kao IntelliJ projekat i pokrenuti ga
        - Pokretanje u Eclipse-u:
            1. importovati projekat: Import -> Maven -> Existing Maven Project
            2. instalirati sve dependency-je iz pom.xml
            3. desni klik na projekat -> Run as -> Java Application / Spring Boot app (ako je instaliran STS plugin sa Eclipse marketplace)
    
    Pokretanje frontend-a:
        - Skinuti i instalirati Node.js sa sledećeg linka: https://nodejs.org/en/download/
        - Otvoriti command prompt ili GitBash
        - Instalirati vue/cli komadnom: npm install -g @vue/cli
        - Pozicionirati se u folder VueProject/isa-mrs-front
        - Pokrenuti komandu za instaliranje paketa potrebnih za projekat: $ npm install
        - Pokrenuti aplikaciju: $ npm run serve [ -- --port 8081]

Heroku link za deploy-anu aplikaciju:
    https://isa-front-tim3.herokuapp.com
