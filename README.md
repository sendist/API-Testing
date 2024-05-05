
# 3B-Kelompok 5 - API Testing untuk Aplikasi dummyapi.io

Projek automation testing untuk menguji API untuk management user dari tautan (https://dummyapi.io/). Proyek ini dikembangkan menggunakan bahasa java (pembuatan script test) dan Maven (build management)



## Build With

Proyek pengujian otomatis melibatkan library:
- JUnit
- Rest Assured

## Prerequisite

Sebelum menjalankan proyek ini, diperluan persyaratan environtmen yang harus disiapkan pada device eksekusi proyek
- JDK diatas versi 8
- Code Editor, disarankan VS Code
- Maven

## Instalation
Proses instalasi proyek ini pada local environment

- Clone repo ini dengan perintah:
```bash
https://github.com/sendist/API-Testing.git
```
- Masuk ke folder project: 
```bash
cd apitesting
```
- Buka Code Editor, Misalnya VS code

## File Configuration
Proses konfigurasi project pad file pom.xml.
- Menambahkan dependency Junit
```bash
<dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>5.7.0</version>
            <scope>test</scope>
        </dependency>
```
- Menambahkan dependency Rest Assured
```bash
<dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>5.4.0</version>
            <scope>test</scope>
        </dependency>
```
## How to Run Execution Testing

### Terminal
Proses menjalankan eksekusi testing melalui terminal dengan menjalankan kode berikut:
```bash
  mvn test -D"test={nama_package_test}.{method_ingin_dijalankan}"
```
contohnya
```bash
  mvn test -D"test=com.apitesting.GetUserByIdTest"
```
Namun bisa juga menjalankan keseluruhan test yang ada pada project dengan menjalankan kode berikut:
```bash
  mvn test
```
## Authors
Kami kelompok B5 dari kelas 3B-D4 Teknik Informatika Politeknik Negeri Bandung
- Muhammad Daffa Raihandika (211524050) (username github: [daffaraihandika](https://github.com/daffaraihandika))
- Reza Ananta Permadi Supriyo (211524059) (username github: [rzanta](https://github.com/rzanta))
- Sendi Setiawan (211524062) (username github: [sendist](https://github.com/sendist))

## Reference
Daftar resource
- [dummyapi.io](https://dummyapi.io/)