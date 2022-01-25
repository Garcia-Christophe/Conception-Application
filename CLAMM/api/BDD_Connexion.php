<?php

ini_set('display_errors', 'On');
error_reporting(E_ALL);

/** 
 * BDD_Connexion est une classe d'accès à la base de données.
 * 
 * BDD_Connexion est une classe permettant de se connecter à
 * la base de données externe, afin de pouvoir réaliser des 
 * insertions, modifications, suppressions.
 * 
 * @author Garcia Christophe
 * @version $Revision: 1.0 $
 * @access public
 */
class BDD_Connexion {

	/**
     * L'unique instance de cette classe
     * 
     * @access private
     */
	private static $instance;

	/**
     * La connexion à la base de données
     * 
     * @access private
     */
	private $connexion;

	/**
     * Méthode magique __construct()
     * 
     * Permet de créer la BDD_Connexion
     */
	public function __construct() {
		try {
			$servername = 'obiwan2.univ-brest.fr';
			$databasename = 'zil3-zgoasguma';
			$username = 'zgoasguma';
			$password = 'hfe3a838';
			$this->connexion = new PDO("mysql:host=$servername;port=3306;dbname=$databasename;", $username, $password);
		} catch (PDOException $e) {
			print "Erreur : " . $e->getMessage() . "\r\n";
			die();
		}
	}

	/**
     * Méthode d'accès à l'unique instance getInstance()
     * 
     * Crée une instance si elle n'existe déjà pas, et retourne celle-ci
     */
	public static function getInstance() {
		if (self::$instance === null) {
			self::$instance = new BDD_Connexion();
		}
		return self::$instance;
	}

	/**
     * Méthode d'accès à la connexion getConnexion()
     * 
     * Retourne la connexion établie à la base de données
     */
	public function getConnexion() {
		return $this->connexion;
	}
}

BDD_Connexion::getInstance()->getConnexion();

?>
