--
-- Déchargement des données de la table roles
--

INSERT INTO roles (defaut, nom, slug) VALUES
                                                        (false , 'Super Admin', 'SUPERADMIN'),
                                                        (false, 'Administrateur', 'ADMIN'),
                                                        (true, 'Manager', 'MANAGER'),
                                                        (false, 'Agent', 'AGENT');

INSERT INTO formula ( created, deleted, libelle, slug, updated) VALUES
                                                                                      ( NULL, NULL, 'Premium', 'PREMIUM', NULL),
                                                                                      ( NULL, NULL, 'Freemium', 'Freemium', NULL);

INSERT INTO category ( created, deleted, libelle, updated) VALUES
                                                                                   ( NULL, NULL, 'Membre', NULL),
                                                                                   ( NULL, NULL, 'Partenaire', NULL);

INSERT INTO secteur ( created, deleted, libelle, updated) VALUES
                                                                                ( NULL, false , 'Informatique', NULL),
                                                                                (NULL, false , 'Commerce', NULL);
--
-- Déchargement des données de la table user
--

INSERT INTO users (id, active, activetoken, displayname, email, nom, password, phone, prenoms, username) VALUES
   (1, true, NULL, 'John DOE', 'miguel.lao@orange.com', 'DOE', '$2a$10$PM5nq1iZ.iFm7VSGlf2A2O3Y7rtUQKuI5TLU6a4xCbWbgu2BHhRFu', '74707070', 'John', 'HRVN5598');

-- --
-- -- Déchargement des données de la table user_role
-- --
--
INSERT INTO user_role (user_id, role_id) VALUES
    (1, 1);
