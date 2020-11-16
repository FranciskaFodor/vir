# Vállalati Információs Rendszerek 2020
##### Egy kép böngésző web alkalmazás jogosultsági szintekkel
Készítsünk egy webes Java alkalmazást (pl. Spring Boot), ami felhasználói bejelentkezést kér. Spring Security segítségével oldjuk meg a felhasználók és jogosultságok kezelését. A felhasználókat és jelszavaikat egy adatbázisban tároljuk, a jelszavak hash-elve legyenek elmentve. Alapvetően egy admin és egy sima user szerepkört hozzunk létre, de több fajta jogosultsággal. A bejelentkezett felhasználó egy előre beállított könyvtárban levő képeket tudja böngészni. Ám minden felhasználó csak azokat a kiterjesztésű képfájlokat láthatja, amelyre jogosultsága van (pl. jpg, gif, png). Az admin felhasználó minden képkiterjesztést láthat és ő meg is tudja változtatni az egyes felhasználók kép formátum jogosultságait.