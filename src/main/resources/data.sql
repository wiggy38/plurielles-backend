--
-- Déchargement des données de la table `roles`
--

INSERT INTO `roles` (`id`, `defaut`, `nom`, `slug`) VALUES
                                                        (1, b'0', 'Super Admin', 'SUPERADMIN'),
                                                        (2, b'0', 'Administrateur', 'ADMIN'),
                                                        (3, b'1', 'Candidat', 'CANDIDATE'),
                                                        (4, b'0', 'Manager', 'MANAGER'),
                                                        (5, b'0', 'Membre du comité', 'COMITEE');

INSERT INTO `formula` (`id`, `created`, `deleted`, `libelle`, `slug`, `updated`) VALUES
                                                                                      (NULL, NULL, NULL, 'Premium', 'PREMIUM', NULL),
                                                                                      (NULL, NULL, NULL, 'Freemium', 'Freemium', NULL);

INSERT INTO `category` (`id`, `created`, `deleted`, `libelle`, `updated`) VALUES
                                                                                   (NULL, NULL, NULL, 'Membre', NULL),
                                                                                   (NULL, NULL, NULL, 'Partenaire', NULL);

INSERT INTO `secteur` (`id`, `created`, `deleted`, `libelle`, `updated`) VALUES
                                                                                (NULL, NULL, b'0', 'Informatique', NULL),
                                                                                (NULL, NULL, b'0', 'Commerce', NULL);
--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `active`, `activetoken`, `displayname`, `email`, `nom`, `password`, `phone`, `prenoms`, `username`) VALUES
    (1, b'1', NULL, 'John DOE', 'miguel.lao@orange.com', 'DOE', '$2a$10$PM5nq1iZ.iFm7VSGlf2A2O3Y7rtUQKuI5TLU6a4xCbWbgu2BHhRFu', '74707070', 'John', 'HRVN5598');

--
-- Déchargement des données de la table `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
    (1, 1);