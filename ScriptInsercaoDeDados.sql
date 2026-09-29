INSERT INTO CLIENTE (cpf, cliente, contato)
VALUES('73222644696', 'Helena', '31940435235'),
('64413057635', 'Arthur', '31993945490');

INSERT INTO ESPECIE(ESPECIE)
VALUES
('CANINA'), ('FELINA');

INSERT INTO RACA(CODESPECIE, RACA)
VALUES
-- Caninos
(1, 'SRD(Sem Raça Definida)'),
(1, 'Maltês'),
(1, 'Labrador Retriever'),
(1, 'Golden Retriever'),
(1, 'Poodle'),
(1, 'Bulldog Francês'),
(1, 'Shih Tzu'),
(1, 'Beagle'),
(1, 'Pastor Alemão'),
(1, 'Yorkshire Terrier'),
(1, 'Chihuahua'),
(1, 'Pug'),
(1, 'Husky Siberiano'),
(1, 'Border Collie'),
(1, 'Boxer'),
(1, 'Dachshund (Salsicha)'),
(1, 'Cocker Spaniel'),
(1, 'Doberman'),
(1, 'Rottweiler'),
(1, 'Spitz Alemão (Lulu da Pomerânia)'),
(1, 'Pinscher'),
(1, 'Akita Inu'),
(1, 'Buldogue Inglês'),
(1, 'Weimaraner'),
(1, 'Shar Pei'),
(1, 'Pastor de Shetland'),
(1, 'Setter Irlandês'),
(1, 'Basenji'),
(1, 'Dálmata'),
-- Felinos
(2, 'Siamês'),
(2, 'Persa'),
(2, 'Maine Coon'),
(2, 'Sphynx'),
(2, 'Bengal'),
(2, 'Ragdoll'),
(2, 'British Shorthair'),
(2, 'Norueguês da Floresta'),
(2, 'Scottish Fold'),
(2, 'Pelo Curto Brasileiro');

INSERT INTO especializacao(especializacao)
VALUES ('NEUROLOGIA'),
('DERMATOLOGIA'),
('CARDIOLOGIA'),
('OFTAMOLOGIA'),
('ONCOLOGIA'),
('ODONTOLOGIA'),
('ORTOPEDIA'),
('CLINICO GERAL');

INSERT INTO veterinario (veterinario, codespecializacao, custobase)
VALUES 
('JULIANA RAMOS DA SILVA', 17, 130.00),
('GUSTAVO VIEIRA TAVARES', 16, 200.00),
('BEATRIZ MENDES SALLES', 13, 250.00),
('HENRIQUE SOUZA PRADO', 12, 340.00),
('LARISSA LIMA MONTEIRO', 14, 450.00),
('ANDRÉ CARVALHO RIBEIRO', 10, 310.00),
('TATIANE LOPES DE FREITAS', 15, 210.00),
('DIEGO DE SOUZA MATOS', 17, 130.00),
('CAROLINA BATISTA AMARAL', 11, 200.00),
('THIAGO SANTOS MEIRELES', 12, 260.00),
('VIVIANE PIRES ANTUNES', 13, 320.00),
('EDUARDA BARCELLOS NEVES', 16, 250.00);