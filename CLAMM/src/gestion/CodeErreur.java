package gestion;

/**
 * Enum�ration des codes d'erreurs.
 * 
 * <p>
 * Les codes erreurs pour les {@code Evenement} sont les suivants :
 * </p>
 * <ul>
 * <li>- AJOUT_EVENEMENT_IMPOSSIBLE : �chec de l'ajout de l'�v�nement dans la base de donn�es</li>
 * <li>- MODIFICATION_EVENEMENT_IMPOSSIBLE : �chec de la modification de l'�v�nement dans la base de
 * donn�es</li>
 * <li>- SUPPRESSION_EVENEMENT_IMPOSSIBLE : �chec de la suppression de l'�venement dans la base de
 * donn�es</li>
 * <li>- EVENEMENT_INTROUVABLE : l'�v�nement est introuvable</li>
 * <li>- ID_NEGATIF : l'identifiant est inf�rieur ou �gal � 0</li>
 * <li>- ID_DEJA_EXISTANT : l'identifiant est d�j� existant dans la base de donn�es</li>
 * <li>- DATE_PASSEE : la date est pass�e</li>
 * <li>- NOM_ESPACE_EN_TROP : le nom commence ou finit par des espaces</li>
 * <li>- NOM_TROP_LONG : la taille du nom est trop grande</li>
 * <li>- LIEU_ESPACE_EN_TROP : le lieu commence ou finit par des espaces</li>
 * <li>- LIEU_VIDE : le lieu est vide</li>
 * <li>- LIEU_TROP_LONG : la taille du lieu est trop grande</li>
 * <li>- DESCRIPTIF_TROP_LONG : la taille du descriptif est trop grande</li>
 * <li>- DESCRIPTIF_NULL : le descriptif est null</li>
 * <li>- DESCRIPTIF_ESPACE_EN_TROP : le descriptif commence ou finit par des espaces</li>
 * <li>- NB_MAX_PERSONNES_TROP_PETIT : le nombre de personnes maximum est trop petit (inf�rieur �
 * 2)</li>
 * <li>- TYPE_NULL : le type est null</li>
 * </ul>
 * 
 * <p>
 * Les codes erreurs pour les {@code Membre} sont les suivants :
 * </p>
 * <ul>
 * <li>- AJOUT_MEMBRE_IMPOSSIBLE : �chec de l'ajout du membre dans la base de donn�es</li>
 * <li>- MODIFICATION_MEMBRE_IMPOSSIBLE : �chec de la modification du membre dans la base de
 * donn�es</li>
 * <li>- SUPPRESSION_MEMBRE_IMPOSSIBLE : �chec de la suppression du membre dans la base de
 * donn�es</li>
 * <li>- MEMBRE_INTROUVABLE : le membre est introuvable</li>
 * <li>- PSEUDO_DEJA_EXISTANT : le pseudo est d�j� existant</li>
 * <li>- PSEUDO_TROP_LONG : la taille du pseudo est trop grande</li>
 * <li>- PSEUDO_NULL : le pseudo est null</li>
 * <li>- PSEUDO_VIDE : le pseudo est vide</li>
 * <li>- PSEUDO_INVALIDE : le pseudo est invalide</li>
 * <li>- NOM_HORS_ALPHABET : le nom contient des caract�res hors alphabet</li>
 * <li>- NOM_TROP_GRAND : la taille du nom est trop grande</li>
 * <li>- PRENOM_NULL : le pr�nom est null</li>
 * <li>- PRENOM_VIDE : le pr�nom est vide</li>
 * <li>- PRENOM_HORS_ALPHABET : le pr�nom contient des caract�res hors alphabet</li>
 * <li>- PRENOM_TROP_GRAND : la taille du pr�nom est trop grande</li>
 * <li>- LIEU_NOM_INCORRECT : le nom du lieu de naissance est incorrect (lettres ou espaces
 * autoris�s)</li>
 * <li>- LIEU_TAILLE_INCORRECTE : la taille du nom du lieu de naissance est trop petite ou trop
 * grande</li>
 * <li>- DATE_IMPOSSIBLE : la date est une date future � la date actuelle</li>
 * <li>- DATE_AGE_IMPOSSIBLE : la date est pass�e de plus de 130 ans</li>
 * <li>- VILLE_NULL : la ville est null</li>
 * <li>- VILLE_NOM_INCORRECT : le nom de la ville est incorrect</li>
 * <li>- VILLE_TAILLE_INCORRECTE : la taille du nom de la ville est trop petite ou trop grande</li>
 * <li>- MAIL_NULL : l'adresse mail est null</li>
 * <li>- MAIL_VIDE : l'adresse mail est vide</li>
 * <li>- MAIL_TROP_LONG : la taille de l'adresse mail est trop longue</li>
 * <li>- MAIL_INVALIDE : l'adresse mail est invalide</li>
 * <li>- MDP_NULL : le mot de passe est null</li>
 * <li>- MDP_TAILLE_INCORRECTE : la taille du mot de passe est trop petite ou trop grande</li>
 * <li>- MDP_INCORRECT : il manque au moins un caract�re (majuscule, minuscule, chiffre, caract�re
 * sp�cial)</li>
 * </ul>
 * 
 * <p>
 * Les codes erreurs pour les {@code Participation} sont les suivants :
 * </p>
 * <ul>
 * <li>- PARTICIPATION_DEJA_EXISTANTE : la participation est d�j� existante</li>
 * <li>- NBINSCRIT_TROP_GRAND : le nombre d'inscrits d�passe le nombre maximal de participants �
 * l'�v�nement</li>
 * <li>- NBINSCRIT_TROP_PETIT : le nombre d'inscrits est inf�rieur au nombre minimal (1)</li>
 * <li>- AJOUT_PARTICIPATION_IMPOSSIBLE : �chec de l'ajout de la participation dans la base de
 * donn�es</li>
 * <li>- MEMBRE_INEXISTANT : le membre de la participation n'existe pas</li>
 * <li>- EVENEMENT_INEXISTANT : les �v�nements de la participation n'existe pas</li>
 * <li>- MEMBRE_NULL : le membre est null</li>
 * <li>- EVENEMENT_NULL : l'�v�nement est null</li>
 * <li>- MODIFICATION_PARTICIPATION_IMPOSSIBLE : modification impossible de la participation dans la
 * base de donn�es</li>
 * <li>- PARTICIPATION_INEXISTANTE : la participation n'existe pas dans la base de donn�es</li>
 * <li>- SUPPRESSION_PARTICIPATION_IMPOSSIBLE : suppression impossible de la participation dans la
 * base de donn�es</li>
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
