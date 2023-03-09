1. openssl req -newkey rsa:2048 -nodes -keyout domain.key -x509 -days 365 -out domain.pem

Country Name (2 letter code) [AU]:RU
State or Province Name (full name) [Some-State]:RU
Locality Name (eg, city) []:RZN
Organization Name (eg, company) [Internet Widgits Pty Ltd]:ELMANOV
Organizational Unit Name (eg, section) []:ELMANOV
Common Name (e.g. server FQDN or YOUR name) []:localhost
Email Address []:elmanov-mr@yandex.ru

2.openssl pkcs12 -export -out server.keystore.p12 -password pass:123 -in domain.pem -inkey domain.key
 password: 123 (pass: - преписка)

3.keytool -keystore clinet.truststore.jks -import -file ./domain.pem
Enter keystore password:  123456
Re-enter new password: 123456

4.keytool -importkeystore -srckeystore ./server.keystore.p12 -destkeystore server.keystore.jks -srcstoretype pkcs12

create jks keystore by pkcs12 keystore - keytool -importkeystore -srckeystore ./server.keystore.p12 -destkeystore server.keystore.jks -srcstoretype pkcs12 -deststoretype jks -srcalias 1 -destalias 1 -deststorepass 123456