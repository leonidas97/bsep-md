insert into template(id, name, extensions) values (-1, 'Root CA', '[{"type":"BASIC_CONSTRAINTS","isCritical":"true","isCa":"true","pathLength":""},{"type":"KEY_USAGE","isCritical":"false","digitalSignature":"false","nonRepudiation":"false","keyEncipherment":"false","dataEncipherment":"false","keyAgreement":"false","keyCertSign":"true","cRLSign":"false","encipherOnly":"false","decipherOnly":"false"},{"type":"SUBJECT_KEY_IDENTIFIER","isCritical":"false"}]');
insert into template(id, name, extensions) values (-2, 'Intermediate CA', '[{"type":"BASIC_CONSTRAINTS","isCritical":"true","isCa":"true","pathLength":""},{"type":"KEY_USAGE","isCritical":"false","digitalSignature":"false","nonRepudiation":"false","keyEncipherment":"false","dataEncipherment":"false","keyAgreement":"false","keyCertSign":"true","cRLSign":"false","encipherOnly":"false","decipherOnly":"false"},{"type":"SUBJECT_KEY_IDENTIFIER","isCritical":"false"},{"type":"AUTHORITY_KEY_IDENTIFIER","isCritical":"false"},{"type":"AUTHORITY_INFO_ACCESS","isCritical":"false"}]');
insert into template(id, name, extensions) values (-3, 'SSL Server', '[{"type":"KEY_USAGE","isCritical":"false","digitalSignature":"true","nonRepudiation":"false","keyEncipherment":"true","dataEncipherment":"false","keyAgreement":"false","keyCertSign":"false","cRLSign":"false","encipherOnly":"false","decipherOnly":"false"},{"type":"EXTENDED_KEY_USAGE","isCritical":"false","anyExtendedKeyUsage":"false","serverAuth":"true","clientAuth":"false","codeSigning":"false","emailProtection":"false","OCSPSigning":"false"},{"type":"SUBJECT_KEY_IDENTIFIER","isCritical":"false"},{"type":"AUTHORITY_KEY_IDENTIFIER","isCritical":"false"},{"type":"AUTHORITY_INFO_ACCESS","isCritical":"false"},{"type":"SUBJECT_ALTERNATIVE_NAME","isCritical":"false","dnsNames":[],"ipAddresses":[]}]');
insert into template(id, name, extensions) values (-4, 'SSL Client', '[{"type":"KEY_USAGE","isCritical":"false","digitalSignature":"true","nonRepudiation":"false","keyEncipherment":"true","dataEncipherment":"false","keyAgreement":"false","keyCertSign":"false","cRLSign":"false","encipherOnly":"false","decipherOnly":"false"},{"type":"EXTENDED_KEY_USAGE","isCritical":"false","anyExtendedKeyUsage":"false","serverAuth":"false","clientAuth":"true","codeSigning":"false","emailProtection":"false","OCSPSigning":"false"},{"type":"SUBJECT_KEY_IDENTIFIER","isCritical":"false"},{"type":"AUTHORITY_KEY_IDENTIFIER","isCritical":"false"},{"type":"AUTHORITY_INFO_ACCESS","isCritical":"false"},{"type":"SUBJECT_ALTERNATIVE_NAME","isCritical":"false","dnsNames":[],"ipAddresses":[]}]');
