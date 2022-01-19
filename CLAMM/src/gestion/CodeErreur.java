package gestion;


/**
 * Enumération des codes d'erreurs
 * 
 * <p>
 * Les codes erreurs pour les {@code Evenement} sont les suivants :
 * </p>
 * <ul>
 * <li>- ID_NEGATIF : la taille du nom est trop grandre</li>
 * <li>- NOM_TROP_LONG : la taille du nom est trop grande</li>
 * <li>- NOM_VIDE : le nom est vide</li>
 * <li>- NOM_NULL : le nom est null</li>
 * <li>- NOM_ESPACE_EN_TROP : le nom commence ou fini par des espaces</li>
 * <li>- DESCRIPTIF_TROP_LONG : la taille du descriptif est trop grande</li>
 * <li>- DESCRIPTIF_VIDE : le descriptif est vide</li>
 * <li>- DESCRIPTIF_NULL : le descriptif est null</li>
 * <li>- DESCRIPTIF_ESPACE_EN_TROP : le descriptif commence ou fini par des espaces</li>
 * <li>- DATE_PASSEE : la date est passée</li>
 * <li>- DATE_NULL : la date est null</li>
 * <li>- LIEU_TROP_LONG : la taille du lieu est trop grande</li>
 * <li>- LIEU_VIDE : le lieu est vide</li>
 * <li>- LIEU_NULL : le lieu est null</li>
 * <li>- LIEU_ESPACE_EN_TROP : le lieu commence ou fini par des espaces</li>
 * <li>- NB_MAX_PERSONNES_TROP_PETIT : le nombre de personnes maximun est trop petit (inférieur à 2)</li>
 * <li>- TYPE_NULL : le type est null</li>
 * </ul>
 * 
 * <p>
 * Les codes erreurs pour les {@code Membre} sont les suivants :
 * </p>
 * <ul>
 * <li>- PSEUDO_DEJA_EXISTANT : le pseudo est déjà existant</li>
 * </ul>
 */
public enum CodeErreur {
  PSEUDO_DEJA_EXISTANT, PSEUDO_TROP_LONG, NOM_CARACTERE_INVALIDE, MEMBRE_INTROUVABLE, EVENEMENT_INTROUVABLE, ID_NEGATIF, DATE_PASSEE, DATE_NULL, NB_MAX_PERSONNES_TROP_PETIT, TYPE_NULL, NOM_NULL, NOM_ESPACE_EN_TROP, NOM_VIDE, NOM_TROP_LONG, DESCRIPTIF_NULL, DESCRIPTIF_ESPACE_EN_TROP, DESCRIPTIF_TROP_LONG, LIEU_NULL, LIEU_ESPACE_EN_TROP, LIEU_VIDE, LIEU_TROP_LONG, PSEUDO_NULL, PSEUDO_VIDE, PSEUDO_INVALIDE, NOM_HORS_ALPHABET, NOM_TROP_GRAND, PRENOM_NULL, PRENOM_VIDE, PRENOM_HORS_ALPHABET, PRENOM_TROP_GRAND, LIEU_NOM_INCORRECT, LIEU_TAILLE_INCORRECTE, DATE_IMPOSSIBLE, DATE_AGE_IMPOSSIBLE, VILLE_NULL, VILLE_NOM_INCORRECT, VILLE_TAILLE_INCORRECTE, MAIL_NULL, MAIL_VIDE, MAIL_INVALIDE, MDP_NULL, MDP_VIDE, MDP_TAILLE_INCORRECTE
}
