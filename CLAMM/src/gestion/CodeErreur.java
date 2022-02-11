package gestion;

/**
 * Enumération des codes d'erreurs.
 * 
 * <p>
 * Les codes erreurs pour les {@code Evenement} sont les suivants :
 * </p>
 * <ul>
 * <li>- AJOUT_EVENEMENT_IMPOSSIBLE : échec de l'ajout de l'événement dans la base de données</li>
 * <li>- MODIFICATION_EVENEMENT_IMPOSSIBLE : échec de la modification de l'événement dans la base de
 * données</li>
 * <li>- SUPPRESSION_EVENEMENT_IMPOSSIBLE : échec de la suppression de l'évenement dans la base de
 * données</li>
 * <li>- EVENEMENT_INTROUVABLE : l'évènement est introuvable</li>
 * <li>- ID_NEGATIF : l'identifiant est inférieur ou égal à 0</li>
 * <li>- ID_DEJA_EXISTANT : l'identifiant est déjà existant dans la base de données</li>
 * <li>- DATE_PASSEE : la date est passée</li>
 * <li>- NOM_ESPACE_EN_TROP : le nom commence ou finit par des espaces</li>
 * <li>- NOM_TROP_LONG : la taille du nom est trop grande</li>
 * <li>- LIEU_ESPACE_EN_TROP : le lieu commence ou finit par des espaces</li>
 * <li>- LIEU_VIDE : le lieu est vide</li>
 * <li>- LIEU_TROP_LONG : la taille du lieu est trop grande</li>
 * <li>- DESCRIPTIF_TROP_LONG : la taille du descriptif est trop grande</li>
 * <li>- DESCRIPTIF_NULL : le descriptif est null</li>
 * <li>- DESCRIPTIF_ESPACE_EN_TROP : le descriptif commence ou finit par des espaces</li>
 * <li>- NB_MAX_PERSONNES_TROP_PETIT : le nombre de personnes maximum est trop petit (inférieur à
 * 2)</li>
 * <li>- TYPE_NULL : le type est null</li>
 * </ul>
 * 
 * <p>
 * Les codes erreurs pour les {@code Membre} sont les suivants :
 * </p>
 * <ul>
 * <li>- AJOUT_MEMBRE_IMPOSSIBLE : échec de l'ajout du membre dans la base de données</li>
 * <li>- MODIFICATION_MEMBRE_IMPOSSIBLE : échec de la modification du membre dans la base de
 * données</li>
 * <li>- SUPPRESSION_MEMBRE_IMPOSSIBLE : échec de la suppression du membre dans la base de
 * données</li>
 * <li>- MEMBRE_INTROUVABLE : le membre est introuvable</li>
 * <li>- PSEUDO_DEJA_EXISTANT : le pseudo est déjà existant</li>
 * <li>- PSEUDO_TROP_LONG : la taille du pseudo est trop grande</li>
 * <li>- PSEUDO_NULL : le pseudo est null</li>
 * <li>- PSEUDO_VIDE : le pseudo est vide</li>
 * <li>- PSEUDO_INVALIDE : le pseudo est invalide</li>
 * <li>- NOM_HORS_ALPHABET : le nom contient des caractères hors alphabet</li>
 * <li>- NOM_TROP_GRAND : la taille du nom est trop grande</li>
 * <li>- PRENOM_NULL : le prénom est null</li>
 * <li>- PRENOM_VIDE : le prénom est vide</li>
 * <li>- PRENOM_HORS_ALPHABET : le prénom contient des caractères hors alphabet</li>
 * <li>- PRENOM_TROP_GRAND : la taille du prénom est trop grande</li>
 * <li>- LIEU_NOM_INCORRECT : le nom du lieu de naissance est incorrect (lettres ou espaces
 * autorisés)</li>
 * <li>- LIEU_TAILLE_INCORRECTE : la taille du nom du lieu de naissance est trop petite ou trop
 * grande</li>
 * <li>- DATE_IMPOSSIBLE : la date est une date future à la date actuelle</li>
 * <li>- DATE_AGE_IMPOSSIBLE : la date est passée de plus de 130 ans</li>
 * <li>- VILLE_NULL : la ville est null</li>
 * <li>- VILLE_NOM_INCORRECT : le nom de la ville est incorrect</li>
 * <li>- VILLE_TAILLE_INCORRECTE : la taille du nom de la ville est trop petite ou trop grande</li>
 * <li>- MAIL_NULL : l'adresse mail est null</li>
 * <li>- MAIL_VIDE : l'adresse mail est vide</li>
 * <li>- MAIL_TROP_LONG : la taille de l'adresse mail est trop longue</li>
 * <li>- MAIL_INVALIDE : l'adresse mail est invalide</li>
 * <li>- MDP_NULL : le mot de passe est null</li>
 * <li>- MDP_TAILLE_INCORRECTE : la taille du mot de passe est trop petite ou trop grande</li>
 * <li>- MDP_INCORRECT : il manque au moins un caractère (majuscule, minuscule, chiffre, caractère
 * spécial)</li>
 * </ul>
 * 
 * <p>
 * Les codes erreurs pour les {@code Participation} sont les suivants :
 * </p>
 * <ul>
 * <li>- PARTICIPATION_DEJA_EXISTANTE : la participation est déjà existante</li>
 * <li>- NBINSCRIT_TROP_GRAND : le nombre d'inscrits dépasse le nombre maximal de participants à
 * l'événement</li>
 * <li>- NBINSCRIT_TROP_PETIT : le nombre d'inscrits est inférieur au nombre minimal (1)</li>
 * <li>- AJOUT_PARTICIPATION_IMPOSSIBLE : échec de l'ajout de la participation dans la base de
 * données</li>
 * <li>- MEMBRE_INEXISTANT : le membre de la participation n'existe pas</li>
 * <li>- EVENEMENT_INEXISTANT : les évènements de la participation n'existe pas</li>
 * <li>- MEMBRE_NULL : le membre est null</li>
 * <li>- EVENEMENT_NULL : l'évènement est null</li>
 * <li>- MODIFICATION_PARTICIPATION_IMPOSSIBLE : modification impossible de la participation dans la
 * base de données</li>
 * <li>- PARTICIPATION_INEXISTANTE : la participation n'existe pas dans la base de données</li>
 * <li>- SUPPRESSION_PARTICIPATION_IMPOSSIBLE : suppression impossible de la participation dans la
 * base de données</li>
 * </ul>
 * 
 * <p>
 * Les codes erreurs communs aux {@code Evenement} et {@code Membre} sont les suivants :
 * </p>
 * <ul>
 * <li>- DATE_NULL : la date est null</li>
 * <li>- NOM_NULL : le nom est null</li>
 * <li>- NOM_VIDE : le nom est vide</li>
 * <li>- LIEU_NULL : le lieu est null</li>
 * <li>- NO_ERROR : il n'y a pas d'erreur</li>
 * </ul>
 */
public enum CodeErreur {
  AJOUT_EVENEMENT_IMPOSSIBLE, AJOUT_MEMBRE_IMPOSSIBLE, MODIFICATION_EVENEMENT_IMPOSSIBLE, MODIFICATION_MEMBRE_IMPOSSIBLE, SUPPRESSION_EVENEMENT_IMPOSSIBLE, SUPPRESSION_MEMBRE_IMPOSSIBLE, MEMBRE_INTROUVABLE, EVENEMENT_INTROUVABLE, ID_NEGATIF, ID_DEJA_EXISTANT, DATE_PASSEE, DATE_NULL, NB_MAX_PERSONNES_TROP_PETIT, TYPE_NULL, NOM_NULL, NOM_ESPACE_EN_TROP, NOM_VIDE, NOM_TROP_LONG, DESCRIPTIF_NULL, DESCRIPTIF_ESPACE_EN_TROP, DESCRIPTIF_TROP_LONG, LIEU_NULL, LIEU_ESPACE_EN_TROP, LIEU_VIDE, LIEU_TROP_LONG, PSEUDO_NULL, PSEUDO_VIDE, PSEUDO_INVALIDE, PSEUDO_DEJA_EXISTANT, PSEUDO_TROP_LONG, NOM_HORS_ALPHABET, NOM_TROP_GRAND, PRENOM_NULL, PRENOM_VIDE, PRENOM_HORS_ALPHABET, PRENOM_TROP_GRAND, LIEU_NOM_INCORRECT, LIEU_TAILLE_INCORRECTE, DATE_IMPOSSIBLE, DATE_AGE_IMPOSSIBLE, VILLE_NULL, VILLE_NOM_INCORRECT, VILLE_TAILLE_INCORRECTE, MAIL_NULL, MAIL_VIDE, MAIL_TROP_LONG, MAIL_INVALIDE, MDP_NULL, MDP_TAILLE_INCORRECTE, MDP_INCORRECT, NO_ERROR, PARTICIPATION_DEJA_EXISTANTE, NBINSCRIT_TROP_GRAND, NBINSCRIT_TROP_PETIT, AJOUT_PARTICIPATION_IMPOSSIBLE, MEMBRE_INEXISTANT, EVENEMENT_INEXISTANT, MEMBRE_NULL, EVENEMENT_NULL, MODIFICATION_PARTICIPATION_IMPOSSIBLE, PARTICIPATION_INEXISTANTE,SUPPRESSION_PARTICIPATION_IMPOSSIBLE
}
