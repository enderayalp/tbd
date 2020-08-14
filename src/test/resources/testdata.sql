TRUNCATE creditline;
TRUNCATE transaction;


INSERT INTO creditline (creditline, accountnumber)
VALUES (1000, 1234);


INSERT INTO transaction (amount, nonce, sender, receiver)
VALUES (-200, '057856aa-de39-11ea-87d0-0242ac130001', 1234, 9999);

INSERT INTO transaction (amount, nonce, sender, receiver)
VALUES (-300, '057856aa-de39-11ea-87d0-0242ac130002', 1234, 9999);