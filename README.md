# Memory-game

## Općenito o aplikaciji

Aplikacija je mrežna Memory igra.
Cilj igre je pobijediti protivnika u Memory igri. Aplikacija prikazuje 8x8 polja sa različitim
slikama i cilj je otvarati polja te pogoditi poziciju dva identična polja nakon čega se polja
zatvaraju. Pobjednik je igrač koji prije pogodi sva polja.

## Funkcionalnosti
- registracija na sustav, unos imena, prezimena, korisničkog imena, e-mail adrese te
lozinke, slanje linka za potvrdu registracije na e-mail adresu
- prijava na sustav: unos korisničkog imena i lozinke
- nakon uspješne prijave aplikacije prikazati listu prijavljenih igrača (protivnika)
- odabirom protivnika i njegovim prihvatom sudjelovanja u igri, pokrenuti igru i nakon
završetka igre, evidentira se pobjednik
- aplikacija ima mogućnost prikaza lista neuspješnijih igrača

## Backend

Aplikacija ima serversku komponentu koja se sastoji od REST usluga za registraciju, prijavu,
dohvat prijavljenih igrača, spajanje igrača i pokretanje igre, evidenciju pobjednika te dohvat
liste najuspješnijih igrača. 
Korišten je Spring Boot i lokalna h2 baza podataka.
