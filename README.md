# Exchange app

<p>Usługa umożliwiająca obliczenie kwoty po przewalutowaniu z uwzględnieniem 2% prowizji od transakcji</p>

<p>Po uruchomieniu wywołujemy endpointy oraz podajemy parametry:</p>

<p>amount = ilość sprzedawanej waluty/ilość waluty, jaką chcemy zakupić (w postaci numerycznej wiekszej od zera)</p>
<p>code = trzyliterowy kod waluty  (obsługiwane waluty EUR, USD, GBP)</p>
<p></p>
dla zakupu waluty:</p>
<p></p>
<h5>http://localhost:8080/transaction/ask</h5>
<p></p>
dla sprzedaży waluty:</p>
<p></p>
<h5>http://localhost:8080/transaction/bid</h5>

Przykładowe zapytanie o zakup 10 Euro:</p>

http://localhost:8080/transaction/ask?amount=10&code=EUR


W przypadku zadania nieprawidłowo sformułowanych zapytań serwis zwraca komunikat 400 Bad Request

