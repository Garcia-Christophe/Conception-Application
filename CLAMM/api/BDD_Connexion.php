<?php

ini_set('display_errors', 'On');
error_reporting(E_ALL);

/** 
 * BDD_Connexion est une classe d'acces a la base de donnees.
 * 
 * BDD_Connexion est une classe permettant de se connecter a
 * la base de donnees externe, afin de pouvoir realiser des 
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
     * La connexion a la base de donnees
     * 
     * @access private
     */
	private $connexion;

	/**
     * Methode magique __construct()
     * 
     * Permet de creer la BDD_Connexion
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
     * Methode d'acces a l'unique instance getInstance()
     * 
     * Cree une instance si elle n'existe deja pas, et retourne celle-ci
     */
	public static function getInstance() {
		if (self::$instance === null) {
			self::$instance = new BDD_Connexion();
		}
		return self::$instance;
	}

	/**
     * Methode d'acces a la connexion getConnexion()
     * 
     * Retourne la connexion etablie a la base de donnees
     */
	public function getConnexion() {
		return $this->connexion;
	}
}

?>
