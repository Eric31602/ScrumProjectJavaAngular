insert into artikelen(ean, naam, beschrijving, prijs, gewichtInGram, bestelPeil, voorraad, minimumVoorraad, maximumVoorraad,
                      levertijd, aantalBesteldLeverancier, maxAantalInMagazijnPlaats, leveranciersId)
values('test1', 'naam1', 'b1', 1, 1, 1, 5, 1, 1, 1,
       1,1,
       (select leveranciersId from leveranciers where naam ='naamTest1'));