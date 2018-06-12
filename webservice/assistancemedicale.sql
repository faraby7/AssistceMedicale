-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  mar. 12 juin 2018 à 03:57
-- Version du serveur :  10.1.30-MariaDB
-- Version de PHP :  7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `assistancemedicale`
--

-- --------------------------------------------------------

--
-- Structure de la table `medecin`
--

CREATE TABLE `medecin` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `specialite` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `medecin`
--

INSERT INTO `medecin` (`id`, `nom`, `prenom`, `specialite`, `email`, `adresse`, `telephone`, `username`, `password`) VALUES
(1, 'youssfe', 'youssef', 'youseff', 'youssef', 'youssef', '068263', 'youssef', 'youssef'),
(2, 'yassine', 'yassine', 'bebe', '', 'ensias', '067215788', 'yassine', 'yassine'),
(3, 'g', 'g', 'g', '', 'g', '5', 'g', 'g');

-- --------------------------------------------------------

--
-- Structure de la table `medicament`
--

CREATE TABLE `medicament` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `description` varchar(250) DEFAULT NULL,
  `image` varchar(333) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `medicament`
--

INSERT INTO `medicament` (`id`, `nom`, `type`, `description`, `image`) VALUES
(1, 'ABBOTICINE 200 MG', 'Antibiotique de la famille des macrolides', 'Malgré tous les efforts fournis par notre équipe, certains médicaments peuvent figurer sur la base medicament.ma et ne pas être disponibles sur le marché marocain. Ceci peut être le cas dans plusieurs situations :\r\n', 'https://www.farmalisto.com.co/90190-thickbox_default/comprar-zitromax-caja-x-suspension-200mg-5ml-rx2-precio-7703283115012.jpg'),
(2, 'DOLIPRANE 500 mg', 'Médicament non soumis à prescription médicale.', 'Traitement symptomatique des douleurs d\'intensité légère à modérée et/ou des états fébriles.\r\n\r\nCette présentation est réservée à l\'adulte et à l\'enfant à partir de 27 kg (soit à partir d\'environ 8 ans).', 'https://www.pharmacie-prado-mermoz.com/client/840002/media/files/Doliprane-500-mg-20555_101_1397668294.jpg'),
(3, 'EFFERALGAN 500 mg ', 'EFFERALGAN : Ses indications', 'EFFERALGAN 500 mg est indiqué dans le traitement symptomatique des douleurs d\'intensité légère à modérée et/ou des états fébriles.', 'http://www.pharma-medicaments.com/media/3257001__000042100_1440_22042013.jpg'),
(4, 'DAFALGAN 500 mg', '	\r\nAntalgiques', 'Traitement symptomatique des douleurs d\'intensité légère à modérée et/ou des états fébriles.', 'https://www.pharma-gdd.com/images/catalog/pictures/thumbnails/400/oberlin-upsa-dafalgan-douleurs-et-fievre-500-mg-16-comprimes-face.jpg'),
(5, 'LEVOTHYROX 25 microgrammes', 'Endocrinologie', 'Hypothyroïdie Circonstances, associes ou non à une hypothyroïdie, où il est nécessaire de freiner la TSH.', 'http://pharmacie-phm.swiss/203-large_default/l-thyroxin-henning-25-microgrammes-100-comprimes.jpg'),
(6, 'IMODIUM 2 mg, gélule, boîte de 20', '	\r\nGastro-Entéro-Hépatologie', 'Traitement symptomatique des diarrhées aiguës et chroniques.\r\n\r\nLe traitement ne dispense pas de mesures diététiques et d\'une réhydratation si elle est nécessaire.\r\n\r\nL\'importance de la réhydratation par soluté de réhydratation orale .', 'https://www.zwitserseapotheek.com/116146-thickbox_default/imodium-2mg-200-caps.jpg'),
(7, 'KARDEGIC 500 mg/5 ml', 'Hémostase et sang', 'Syndromes coronariens aigus (angor instable, infarctus sans onde Q) et à la phase aiguë de l\'infarctus du myocarde, notamment lorsque la voie orale ne peut être utilisée.', 'https://www.pharmacie-prado-mermoz.com/client/840002/media/files/Kardegic-300-mg-20817_101_1397668075.jpg'),
(8, 'SPASFON, comprimé enrobé, boîte de 30', 'Antalgiques', 'Traitement symptomatique des douleurs liées aux troubles fonctionnels du tube digestif et des voies biliaires.\r\n\r\n\r\n· Traitement des manifestations spasmodiques et douloureuses aiguës des voies urinaires: coliques néphrétiques.', 'http://www.notrepharma.com/3516/spasfon-80-mg-boite-de-30-comprimes-enrobes.jpg'),
(9, 'ISIMIG ', 'Neurologie-psychiatrie', 'Traitement de la phase cephalalgique de la crise de migraine avec ou sans aura.\r\nISIMIG est indique chez les adultes.', 'https://timbuktoo.name/wp-content/uploads/2017/06/prod_47556-1-large-1000x430.jpg'),
(10, 'Ee', 'eee', 'eeeee', 'eeeeee'),
(11, 'Ggg', 'g', 'g', 'g');

-- --------------------------------------------------------

--
-- Structure de la table `ordonnance`
--

CREATE TABLE `ordonnance` (
  `id` int(11) NOT NULL,
  `id_patient` int(11) NOT NULL,
  `id_medicament` int(11) NOT NULL,
  `duree` int(11) NOT NULL,
  `heure_matin` timestamp NULL DEFAULT NULL,
  `heure_midi` timestamp NULL DEFAULT NULL,
  `heure_soir` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `patient`
--

CREATE TABLE `patient` (
  `id` int(11) NOT NULL,
  `id_medecin` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `adresse` varchar(255) NOT NULL,
  `telephone` varchar(255) NOT NULL,
  `datenaissance` datetime NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `patient`
--

INSERT INTO `patient` (`id`, `id_medecin`, `nom`, `prenom`, `email`, `adresse`, `telephone`, `datenaissance`, `username`, `password`) VALUES
(1, 2, 'Aymen', 'Chla', 'Chla.aymen@gmail.com', 'Ensias Batiment C chambre C24', '066253749', '2018-06-06 00:00:00', 'chla', 'Faraby'),
(2, 3, 'chla', 'chla', '', '', '0661457831', '2006-06-18 00:00:00', 'chla', 'chla'),
(3, 2, 'sd', 'sd', 'ds', 'ds', 'dsa', '0000-00-00 00:00:00', 'da', ''),
(4, 2, 'ffr', 'frf', '', 'rfr', 'fr', '0000-00-00 00:00:00', 'fr', 'fr'),
(5, 2, 'ffr', 'frf', '', 'rfr', 'fr', '0000-00-00 00:00:00', 'fr', 'fr'),
(6, 0, 'ffr', 'frf', '', 'rfr', 'fr', '0000-00-00 00:00:00', 'fr', 'fr'),
(7, 2, 'ffr', 'frf', '', 'rfr', 'fr', '0000-00-00 00:00:00', 'fr', 'fr'),
(8, 0, 'ffr', 'frf', '', 'rfr', 'fr', '0000-00-00 00:00:00', 'fr', 'fr'),
(9, 2, 'ffr', 'frf', '', 'rfr', 'fr', '0000-00-00 00:00:00', 'fr', 'fr'),
(10, 0, 'ffr', 'frf', '', 'rfr', 'fr', '0000-00-00 00:00:00', 'fr', 'fr');

-- --------------------------------------------------------

--
-- Structure de la table `rdv`
--

CREATE TABLE `rdv` (
  `id` int(11) NOT NULL,
  `id_patient` int(11) NOT NULL,
  `Description` varchar(300) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `rdv`
--

INSERT INTO `rdv` (`id`, `id_patient`, `Description`, `date`) VALUES
(15, 1, 'Faraby', '2018-06-16'),
(16, 1, 'Aymen', '2018-06-30'),
(17, 4, 'ghandour', '2018-06-16'),
(18, 1, 'Traitement bla bla bla bla', '2018-08-15');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `medecin`
--
ALTER TABLE `medecin`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `medicament`
--
ALTER TABLE `medicament`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `ordonnance`
--
ALTER TABLE `ordonnance`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_patient` (`id_patient`),
  ADD KEY `id_medicament` (`id_medicament`);

--
-- Index pour la table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_medecin` (`id_medecin`);

--
-- Index pour la table `rdv`
--
ALTER TABLE `rdv`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_patient` (`id_patient`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `medecin`
--
ALTER TABLE `medecin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `medicament`
--
ALTER TABLE `medicament`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `ordonnance`
--
ALTER TABLE `ordonnance`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `patient`
--
ALTER TABLE `patient`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `rdv`
--
ALTER TABLE `rdv`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
