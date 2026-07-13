insert into bestellijnen(bestelId, artikelId, aantalBesteld, aantalGeannuleerd)
values((select bestelId from bestellingen where klantId = 1 and betalingsCode = 'ABC123'),
       (select artikelId from artikelen where naam = 'naam1'), 1, 0);