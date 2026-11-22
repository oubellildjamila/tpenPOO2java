import java.time.Year;   // pour obtenir l'année actuelle (Exercice 2)

// ==========================================================
// EXERCICE 1 : classe Rectangle
// ==========================================================
class Rectangle {

    // Attributs privés : accessibles seulement via les getters/setters (encapsulation)
    private double longueur;
    private double largeur;

    // Constructeur : initialise la longueur et la largeur
    Rectangle(double longueur, double largeur) {
        setLongueur(longueur);   // on réutilise les setters pour vérifier que les valeurs sont positives
        setLargeur(largeur);
    }

    // Getters : lire les attributs
    double getLongueur() { return longueur; }
    double getLargeur()  { return largeur; }

    // Setters : modifier les attributs (l'énoncé impose des réels positifs)
    void setLongueur(double longueur) {
        if (longueur <= 0) {
            throw new IllegalArgumentException("La longueur doit etre positive");
        }
        this.longueur = longueur;   // this.longueur = attribut ; longueur = paramètre
    }

    void setLargeur(double largeur) {
        if (largeur <= 0) {
            throw new IllegalArgumentException("La largeur doit etre positive");
        }
        this.largeur = largeur;
    }

    // Périmètre = 2 x (longueur + largeur)
    double perimetre() {
        return 2 * (longueur + largeur);
    }

    // Surface = longueur x largeur
    double surface() {
        return longueur * largeur;
    }

    // Diagonale = racine carrée de (longueur² + largeur²) : théorème de Pythagore
    double diagonale() {
        return Math.sqrt(longueur * longueur + largeur * largeur);
    }
}

// ==========================================================
// EXERCICE 2 : classe Livre
// ==========================================================
class Livre {

    // Attributs privés
    private String titre;
    private String auteur;
    private int anneePublication;
    private int nombrePages;

    // Constructeur qui initialise tous les attributs
    Livre(String titre, String auteur, int anneePublication, int nombrePages) {
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.nombrePages = nombrePages;
    }

    // Getters
    String getTitre()          { return titre; }
    String getAuteur()         { return auteur; }
    int getAnneePublication()  { return anneePublication; }
    int getNombrePages()       { return nombrePages; }

    // Setters
    void setTitre(String titre)                    { this.titre = titre; }
    void setAuteur(String auteur)                  { this.auteur = auteur; }
    void setAnneePublication(int anneePublication) { this.anneePublication = anneePublication; }
    void setNombrePages(int nombrePages)           { this.nombrePages = nombrePages; }

    // Vrai si le livre a été publié il y a plus de 50 ans
    boolean estAncien() {
        int anneeActuelle = Year.now().getValue();   // année en cours
        return (anneeActuelle - anneePublication) > 50;
    }

    // Vrai si le livre a plus de 500 pages
    boolean estVolumineux() {
        return nombrePages > 500;
    }
}

// ==========================================================
// EXERCICE 3 : classe Etudiant
// ==========================================================
class Etudiant {

    // Attribut de classe (static) : partagé par tous les étudiants, compte les objets créés
    private static int nbEtudiants = 0;

    // Attributs d'instance
    private int id;
    private String nom;
    private String prenom;
    private double moyenne;

    // Constructeur sans paramètre
    Etudiant() {
        nbEtudiants++;            // un étudiant de plus
        this.id = nbEtudiants;    // l'id est généré automatiquement
        this.nom = "";
        this.prenom = "";
        this.moyenne = 0;
    }

    // Constructeur avec nom, prénom et moyenne
    Etudiant(String nom, String prenom, double moyenne) {
        nbEtudiants++;
        this.id = nbEtudiants;
        this.nom = nom;
        this.prenom = prenom;
        this.moyenne = moyenne;
    }

    // Getters pour les 4 champs
    int getId()          { return id; }
    String getNom()      { return nom; }
    String getPrenom()   { return prenom; }
    double getMoyenne()  { return moyenne; }

    // Setters uniquement pour le prénom et la moyenne (comme demandé)
    void setPrenom(String prenom)    { this.prenom = prenom; }
    void setMoyenne(double moyenne)  { this.moyenne = moyenne; }

    // Affiche toutes les informations de l'étudiant
    void afficher() {
        System.out.println("Id : " + id + " | Nom : " + nom + " | Prenom : " + prenom
                + " | Moyenne : " + moyenne);
    }

    // Méthode de classe : retourne le nombre d'étudiants créés
    static int getNombreEtudiants() {
        return nbEtudiants;
    }
}

// ==========================================================
// CLASSE PRINCIPALE (public, même nom que le fichier) : les tests
// ==========================================================
public class TP2 {

    // ----- TestRectangle -----
    static void testRectangle() {
        Rectangle r = new Rectangle(5, 3);   // appelle le constructeur

        System.out.println("Longueur  : " + r.getLongueur());
        System.out.println("Largeur   : " + r.getLargeur());
        System.out.println("Perimetre : " + r.perimetre());   // 16
        System.out.println("Surface   : " + r.surface());     // 15
        System.out.println("Diagonale : " + r.diagonale());   // racine de 34

        // Test des setters
        r.setLongueur(10);
        r.setLargeur(4);
        System.out.println("\nApres modification (10 x 4) :");
        System.out.println("Perimetre : " + r.perimetre());   // 28
        System.out.println("Surface   : " + r.surface());     // 40
        System.out.println("Diagonale : " + r.diagonale());   // racine de 116
    }

    // ----- TestLivre -----
    static void testLivre() {
        Livre l1 = new Livre("Le Petit Prince", "Antoine de Saint-Exupery", 1943, 96);
        Livre l2 = new Livre("Les Miserables", "Victor Hugo", 1862, 1488);
        Livre l3 = new Livre("Java pour les debutants", "Jean Dupont", 2022, 320);

        // Tableau de livres pour les afficher avec une boucle
        Livre[] livres = {l1, l2, l3};

        for (int i = 0; i < livres.length; i++) {
            Livre l = livres[i];
            System.out.println("Titre      : " + l.getTitre());
            System.out.println("Auteur     : " + l.getAuteur());
            System.out.println("Annee      : " + l.getAnneePublication());
            System.out.println("Pages      : " + l.getNombrePages());
            // (condition ? siVrai : siFaux) : écriture courte d'un if/else
            System.out.println("Ancien     : " + (l.estAncien() ? "oui" : "non"));
            System.out.println("Volumineux : " + (l.estVolumineux() ? "oui" : "non"));
            System.out.println("-------------------------");
        }
    }

    // ----- TestEtudiant -----
    static void testEtudiant() {
        // (a) Tableau de 5 étudiants
        Etudiant[] tab = new Etudiant[5];
        tab[0] = new Etudiant("Benali", "Sara", 14.5);
        tab[1] = new Etudiant("Amrani", "Yanis", 11.0);
        tab[2] = new Etudiant("Kaci", "Lina", 16.25);
        tab[3] = new Etudiant("Mebarki", "Rayan", 9.75);
        tab[4] = new Etudiant("Haddad", "Amel", 13.0);

        // (b) Tri à bulles selon la moyenne, ordre décroissant (meilleure moyenne en premier)
        for (int i = 0; i < tab.length - 1; i++) {
            for (int j = 0; j < tab.length - 1 - i; j++) {
                if (tab[j].getMoyenne() < tab[j + 1].getMoyenne()) {
                    // On échange les deux étudiants
                    Etudiant temp = tab[j];
                    tab[j] = tab[j + 1];
                    tab[j + 1] = temp;
                }
            }
        }

        // (c) Affichage de chaque étudiant
        System.out.println("--- Etudiants tries par moyenne decroissante ---");
        for (int i = 0; i < tab.length; i++) {
            tab[i].afficher();
        }

        // Méthode de classe : appelée avec le nom de la classe
        System.out.println("\nNombre d'etudiants crees : " + Etudiant.getNombreEtudiants());
    }

    // ----- Programme principal -----
    public static void main(String[] args) {
        System.out.println("===== EXERCICE 1 : Rectangle =====");
        testRectangle();

        System.out.println("\n===== EXERCICE 2 : Livre =====");
        testLivre();

        System.out.println("\n===== EXERCICE 3 : Etudiant =====");
        testEtudiant();
    }
}
