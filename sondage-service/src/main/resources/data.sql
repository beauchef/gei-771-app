INSERT INTO sondage (id, description) values
(1, 'Lecture à la maison'),
(2, 'Consommation de café et de boissons alcoolisées');

INSERT INTO question (id, text, sondage_id) values
(1, 'À quelle tranche d''âge appartenez-vous? a:0-25 ans, b:25-50 ans, c:50-75 ans, d:75 ans et plus', 1),
(2, 'À quel sexe vous identifiez-vous? a:Féminin, b:Masculin, c:Autre, d:Je ne veux pas répondre', 1),
(3, 'Quel journal lisez-vous à la maison? a:La Presse, b:Le Journal de Montréal, c:The Gazette, d:Le Devoir, e:Autre, f: Aucun', 1),
(4, 'Combien de temps accordez-vous à la lecture de votre journal quotidiennement? a:Moins de 10 minutes; b:Entre 10 et 30 minutes, c:Entre 30 et 60 minutes, d:60 minutes ou plus', 1),
(5, 'À quelle tranche d''âge appartenez-vous? a:0-25 ans, b:25-50 ans, c:50-75 ans, d:75 ans et plus', 2),
(6, 'À quel sexe vous identifiez-vous? a:Féminin, b:Masculin, c:Autre, d:Je ne veux pas répondre', 2),
(7, 'Combien de tasses de café buvez-vous chaque jour? a:Je ne bois pas de café, b:Entre 1 et 5 tasses, c:Entre 6 et 10 tasses, d:10 tasses ou plus', 2),
(8, 'Combien de consommations alcoolisées buvez-vous chaque jour? a:0, b:1, c:2 ou 3, d:3 ou plus', 2);