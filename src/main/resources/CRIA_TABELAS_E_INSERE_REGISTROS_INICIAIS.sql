CREATE TABLE exame (
  rowid BIGINT AUTO_INCREMENT PRIMARY KEY,
  nm_exame VARCHAR(255)
);
INSERT INTO exame (nm_exame) VALUES ('Acuidade Visual'), ('Urina'), ('Clinico'), ('Sangue');

CREATE TABLE funcionario (
  rowid BIGINT AUTO_INCREMENT PRIMARY KEY,
  nm_funcionario VARCHAR(255)
);
INSERT INTO funcionario (nm_funcionario) VALUES ('Eduardo'), ('Pamela'), ('Marco'), ('Malu');

CREATE TABLE exame_funcionario (
  rowid BIGINT AUTO_INCREMENT PRIMARY KEY,
  cd_exame BIGINT NOT NULL,
  cd_funcionario BIGINT NOT NULL,
  dt_exame DATETIME  NOT NULL,
  FOREIGN KEY (cd_exame) REFERENCES exame(rowid),
  FOREIGN KEY (cd_funcionario) REFERENCES funcionario(rowid)
  ON DELETE CASCADE
  ON UPDATE CASCADE
);

INSERT INTO exame_funcionario (cd_exame, cd_funcionario, dt_exame) VALUES (1,1,'2022-03-19'), (2,2,'2022-03-19'), (3,3,'2022-03-19');
