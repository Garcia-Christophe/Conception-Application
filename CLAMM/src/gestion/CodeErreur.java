package gestion;

/**
 * Enumération des codes d'erreurs.
 * 
 * <p>Les codes erreurs pour les {@code Evenement} sont les suivants :
 * </p>
 * <ul>
 * <li>- EVENEMENT_INTROUVABLE : l'évènement est introuvable</li>
 * <li>- ID_NEGATIF : la taille du nom est trop grande</li>
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
 * <p>Les codes erreurs pour les {@code Membre} sont les suivants :
 * </p>
 * <ul>
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
 * <li>- LIEU_NOM_INCORRECT : le nom du lieu de naissance est incorrect</li>
 * <li>- LIEU_TAILLE_INCORRECTE : la taille du nom du lieu de naissance est trop grande</li>
 * <li>- DATE_IMPOSSIBLE : la date est une date future à la date actuelle</li>
 * <li>- DATE_AGE_IMPOSSIBLE : la date est passée de plus de 130 ans</li>
 * <li>- VILLE_NULL : la ville est null</li>
 * <li>- VILLE_NOM_INCORRECT : le nom de la ville est incorrect</li>
 * <li>- VILLE_TAILLE_INCORRECTE : la taille du nom de la ville est trop petite ou trop grande</li>
 * <li>- MAIL_NULL : l'adresse mail est null</li>
 * <li>- MAIL_VIDE : l'adresse mail est vide</li>
 * <li>- MAIL_INVALIDE : l'adresse mail est invalide</li>
 * <li>- MDP_NULL : le mot de passe est null</li>
 * <li>- MDP_VIDE : le mot de passe est vide</li>
 * <li>- MDP_TAILLE_INCORRECTE : la taille du mot de passe est trop petite ou trop grande</li>
 * </ul>
 * 
 * <p>Les codes erreurs communs aux {@code Evenement} et {@code Membre} sont les suivants :
 * </p>
 * <ul>
 * <li>- DATE_NULL : la date est null</li>
 * <li>- NOM_NULL : le nom est null</li>
 * <li>- NOM_VIDE : le nom est vide</li>
 * <li>- LIEU_NULL : le lieu est null</li>
 * </ul>
 */
public enum CodeErreur {
  MEMBRE_INTROUVABLE, 
  EVENEMENT_INTROUVABLE, 
  ID_NEGATIF, 
  DATE_PASSEE, 
  DATE_NULL, 
  NB_MAX_PERSONNES_TROP_PETIT, 
  TYPE_NULL, 
  NOM_NULL, 
  NOM_ESPACE_EN_TROP, 
  NOM_VIDE, 
  NOM_TROP_LONG, 
  DESCRIPTIF_NULL, 
  DESCRIPTIF_ESPACE_EN_TROP, 
  DESCRIPTIF_TROP_LONG, 
  LIEU_NULL, 
  LIEU_ESPACE_EN_TROP, 
  LIEU_VIDE, 
  LIEU_TROP_LONG, 
  PSEUDO_NULL, 
  PSEUDO_VIDE, 
  PSEUDO_INVALIDE, 
  PSEUDO_DEJA_EXISTANT, 
  PSEUDO_TROP_LONG, 
  NOM_HORS_ALPHABET, 
  NOM_TROP_GRAND, 
  PRENOM_NULL, 
  PRENOM_VIDE, 
  PRENOM_HORS_ALPHABET, 
  PRENOM_TROP_GRAND, 
  LIEU_NOM_INCORRECT, 
  LIEU_TAILLE_INCORRECTE, 
  DATE_IMPOSSIBLE, 
  DATE_AGE_IMPOSSIBLE, 
  VILLE_NULL, 
  VILLE_NOM_INCORRECT, 
  VILLE_TAILLE_INCORRECTE, 
  MAIL_NULL, MAIL_VIDE, 
  MAIL_INVALIDE, 
  MDP_NULL, 
  MDP_VIDE, 
  MDP_TAILLE_INCORRECTE
}
