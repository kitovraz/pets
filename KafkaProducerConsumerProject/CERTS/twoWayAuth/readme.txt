(	Client check server)
1. openssl req -newkey rsa:2048 -nodes -keyout domain.key -x509 -days 365 -out domain.pem

Country Name (2 letter code) [AU]:RU
State or Province Name (full name) [Some-State]:RU
Locality Name (eg, city) []:RZN
Organization Name (eg, company) [Internet Widgits Pty Ltd]:ELMANOV
Organizational Unit Name (eg, section) []:ELMANOV
Common Name (e.g. server FQDN or YOUR name) []:localhost
Email Address []:elmanov-mr@yandex.ru

2.openssl pkcs12 -export -out server.keystore.p12 -password pass:123 -in domain.pem -inkey domain.key
 [password: 123 (pass: - преписка, entire password - 123)
 -export             Output PKCS12 file]

3.keytool -keystore clinet.truststore.jks -import -file ./domain.pem
[password: 123456]

4.keytool -importkeystore -srckeystore ./server.keystore.p12 -destkeystore server.keystore.jks -srcstoretype pkcs12

create jks keystore by pkcs12 keystore - keytool -importkeystore -srckeystore ./server.keystore.p12 -destkeystore server.keystore.jks -srcstoretype pkcs12 -deststoretype jks -srcalias 1 -destalias 1 -deststorepass 123456

(Server check client)
1. openssl req -newkey rsa:2048 -nodes -keyout client-domain.key -x509 -days 365 -out client-domain.pem
Country Name (2 letter code) [AU]:RU
State or Province Name (full name) [Some-State]:RZN
Locality Name (eg, city) []:RZN
Organization Name (eg, company) [Internet Widgits Pty Ltd]:SBER
Organizational Unit Name (eg, section) []:SBER
Common Name (e.g. server FQDN or YOUR name) []:clientname
Email Address []:email@address.ru

2. openssl pkcs12 -export -out client.keystore.p12 -password pass:123456 -in client-domain.pem -inkey client-domain.key
[password: 123456]

3.keytool -import -alias clientname -file client-domain.pem -keystore server.truststore.jks
[password: 123456]

(Full trustores)
1. 