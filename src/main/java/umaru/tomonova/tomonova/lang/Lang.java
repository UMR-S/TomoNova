package umaru.tomonova.tomonova.lang;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

public enum Lang {
    PREFIX("prefix", "Tomo Nova"),
    KICK_MESSAGE("kickMessage", "Gomen c'est fini"),
    SB_PREFIX("scoreboard.prefix", "Tomo Nova"),
    SB_STARIN("scoreboard.startIn", "Démarrage dans : "),
    SB_PLAYERS("scoreboard.players", "Joueurs : "),
    SB_TIME("scoreboard.time", "Joueurs : "),
    SB_BORDER("scoreboard.border", "Bordure : "),
    SB_SPAWN("scoreboard.spawn", "Spawn : "),
    SB_PVP("scoreboard.pvp", "PvP : "),
    SB_KILLS("scoreboard.kills", "Kills : "),
    SB_ACTIVATED("scoreboard.actived", "Activé"),
    SB_ACTIVATED_FEMININ("scoreboard.activedFem", "Activée"),
    SB_SOLO("scoreboard.solo", " SOLO"),
    SB_TEAMOF("scoreboard.teamsOf", " To"),
    SB_TEAM("scoreboard.team", "Equipe :"),
    SB_PLAYERS_IN_TEAM("scoreboard.playersInTeam", ""),
    SB_PLAYERS_IN_TEAM_ENDS("scoreboard.playersInTeamEnd", ""),
    ONLY_IN_LOBBY("onlyInLobby", "Possible uniquement lors du lobby"),
    PLAYER_NO_EXIST("playerNotExist", "Le joueur {0} n'existe pas"),
    TEAMDEAD("deadTeam", "La team {0} est morte"),
    DAMAGE_WILL_BE_ACTIVATED("damageWillBeActivated", "Dégâts activés dans 1min"),
    DAMAGE_ACTIVATED("damageActivated", "Les dégâts sont activés"),
    END_GAME("endGame", "Fin de la partie"),
    WIN_OF_PLAYER("winOfPlayer", "Le joueur {0} a gagné, bravo à lui!"),
    WIN_OF_TEAM("winOfTeam", "La team {0}, bravo à eux!"),
    START_IN("startIn", "La partie commence dans {0} secondes"),
    START_IN_ONE_SECOND("startInOneSeconde", "La partie commence dans 1 seconde"),
    NETHER_END_IN("netherEndIn", "Le nether sera désactivé et mortel dans {0} minutes(s)"),
    NETHER_END("netherEnd", "Il faut sortir du nether maintenant hap"),
    PVP_ACTIVATED("pvpActivated", "Le PvP est désormais actif"),
    BORDER_START("borderStart", "La bordure commence à se déplacer, post avant sélection naturelle "),
    BORDER_REDUCTION_SIZE("borderReductionSize", "Elle se réduira jusqu'en {0}"),
    BORDER_TEMP_END("borderTempEnd", "Vous êtes sauf ... pour cette fois"),
    BORDER_END("borderEnd", "La bordure a atteint sa taille minimale"),
    SUDDEN_DEATH("suddenDeath", "Tu ne le sais pas encore mais tu es déjà mort"),
    DRAG_ITEMS("dragItems", "Place les items dans ton inventaire"),
    VALID_ITEMS("validItems", "Valider"),
    TEAM_FULL("teamFull", "La team est pleine comme le cul de mon trap"),
    JOIN_TEAM("joinTeam", "Tu as rejoint l'équipe {0}"),
    VALID("valid", "Valider"),
    TEAMS("teams", "Equipes"),
    JOIN_MESSAGE("joinMessage", "{0} est dans Laplace, ce qui est un peu gay"),
    QUIT_MESSAGE("quitMessage", "{0} est parti, tant mieux je l'aimais pas"),
    SERVER_FULL("serverFull", "Y'a plus de place"),
    NETHER_DAMAGE("netherDamage", "Sors du nether ou cancer toutes les 30 secondes"),
    PVP_IN("pvpIn", "Le PvP sera actif dans {0} secondes"),
    GUIS_BACK("guis.back", "Retour"),
    GUIS_BASE_INVENTORY("guis.baseInventory", "Inventaire de départ"),
    GUIS_BD_NAME("guis.border.name", "Bordure"),
    GUIS_BD_INITIAL_SIZE("guis.border.initialSize", "Taille initiale"),
    GUIS_BD_INITIAL_SIZE_LORE("guis.border.initialSizeLore", "Choisis la taille initiale de la bordure"),
    GUIS_BD_SPEED("guis.border.speed", "Vitesse"),
    GUIS_BD_SPEED_LORE("guis.border.speedLore", "Choisis la vitesse de la bordure (en comptant les deux côtés)"),
    GUIS_BD_FINAL_SIZE("guis.border.finalSize", "Taille finale"),
    GUIS_BD_FINAL_SIZE_LORE("guis.border.finalSizeLore", "Chosis la taille finale de la bordure"),
    GUIS_BD_TIME("guis.border.time", "Temps "),
    GUIS_BD_TIME_LORE("guis.border.timeLore", "Au bout de combien de temps la bordure commence à se réduire"),
    GUIS_BDS_NAME("guis.borderSpeed.name", "Vitesse : "),
    GUIS_BDS_LORE("guis.borderSpeed.lore", "Choisis la vitesse de la bordure"),
    GUIS_BDS_LORE1("guis.borderSpeed.lore1", "La vitesse est en block/seconde"),
    GUIS_BDT_NAME("guis.borderTime.name", "Temps avant début de rétrécissement de la bordure : "),
    GUIS_BDT_LORE("guis.borderTime.lore", "Choisis le temps avant le début de rétrécissement de la bordure"),
    GUIS_BDFS_NAME("guis.finalBorderSize.name", "Taille finale : "),
    GUIS_BDFS_LORE("guis.finalBorderSize.lore", "Choisis la taille finale de la bordure"),
    GUIS_BDFS_LORE1("guis.finalBorderSize.lore1", "Ce choix concerne la longueur de la bordure"),
    GUIS_BDFS_LORE2("guis.finalBorderSize.lore2", "Donc pour une taille de 100 la map fera 100x100 blocks à la fin"),
    GUIS_BDSS_NAME("guis.startBorderSize.name", "Taille initiale"),
    GUIS_BDSS_LORE("guis.startBorderSize.lore", "Choisis la taille initiale de la bordure"),
    GUIS_BDSS_LORE1("guis.startBorderSize.lore1", "Ce choix concerne la longueur de la bordure"),
    GUIS_BDSS_LORE2("guis.startBorderSize.lore2", "Donc pour une taille de 1000, la map fera 1000x1000 au début"),
    GUIS_CUST_INV_NAME("guis.customInventory.name", "Inventaire de départ"),
    GUIS_CUST_INV_ADD_ITEM("guis.customInventory.addItem", "Ajouter des items"),
    GUIS_CUST_INV_ADD_ITEM_LORE("guis.customInventory.addItemLore", "Cliquer pour ajouter des items"),
    GUIS_CUST_INV_ITEMSTACK_LORE("guis.customInventory.itemStackLore", "Clique gauche pour éditer"),
    GUIS_CUST_INV_ITEMSTACK_LORE1("guis.customInventory.itemStackLore1", "Clique droit pour retirer"),
    GUIS_CUST_INV_VALID("guis.customInventory.valid", "Valider"),
    GUIS_NET_NAME("guis.netherEndTime.name", "Durée du nether"),
    GUIS_NET_LORE("guis.netherEndTime.lore", "Choisis la durée pendant laquelle le nether est actif"),
    GUIS_NETHER_NAME("guis.nether.name", "Nether"),
    GUIS_NETHER_STATE_ON("guis.nether.stateOn", "Etat : On"),
    GUIS_NETHER_STATE_ON_LORE("guis.nether.stateOff", "Clique pour désactiver le nether"),
    GUIS_NETHER_OFF("guis.nether.netherOff", "Etat : Off"),
    GUIS_NETHER_OFF_LORE("guis.nether.netherOffLore", "Clique pour activer le nether"),
    GUIS_NETHER_TIME("guis.nether.netherTime", "Temps"),
    GUIS_NETHER_TIME_LORE("guis.nether.netherTimeLore", "Choisis au bout de combien de temps le nether est actif"),
    GUIS_TEAMS_NAME("guis.teams.name", "Teams"),
    GUIS_TEAMSOF_NAME("guis.teamsOf", "Joueurs par team"),
    GUIS_TEAMSOF_LORE("guis.teamsOf", "Nombre de joueurs par team"),
    GUIS_TIMER_PREVIOUS("guis.timer", "Précédent"),
    GUIS_TIMER_NEXT("guis.timer", "Suivant"),
    GUIS_MAX_PLAYERS_NAME("guis.maxPlayers.name", "Nombre de joueurs maximum : "),
    GUIS_MAX_PLAYERS_LORE("guis.maxPlayers.lore", "Choisis le nombre de joueurs maximum"),
    GUIS_MIN_PLAYERS_NAME("guis.minPlayers.name", "Nombre de joueurs minimum : "),
    GUIS_MIN_PLAYERS_LORE("guis.minPlayers.lore", "Choisis le nombre de joueur minimum"),
    GUIS_MIN_PLAYERS_LORE1("guis.minPlayers.lore1", "Si atteint, la partie se lance seule"),
    GUIS_PVP_TIME_NAME("guis.pvpTime.name", "Temps avant le PvP en minute"),
    GUIS_PVP_TIME_LORE("guis.pvpTime.lore", "Choisis le temps à partir duquel le PvP est activé"),
    GUIS_SUDDEN_DEATH_TIME_NAME("guis.suddenDeathTime.name", "Temps avant la mort subite en minute"),
    GUIS_SUDDEN_DEATH_TIME_LORE("guis.suddenDeathTime.lore", "Choisis le temps à partir duquel la mort subite est activée"),
    GUIS_LOAD_CONFIG_NAME("guis.loadConfig.name", "Configs sauvegardées"),
    GUIS_LOAD_CONFIG_LORE("guis.loadConfig.lore", "Clique gauche pour chager"),
    GUIS_LOAD_CONFIG_LORE1("guis.loadConfig.lore1", "Clique droit pour supprimer"),
    GUIS_LOAD_CONFIG_CONFIG_LOADED("guis.loadConfig.configLoaded", "Config {0} chargée"),
    GUIS_MAIN_NAME("guis.main.name", "Configuration de la partie"),
    GUIS_MAIN_MAX_PLAYERS("guis.main.maxPlayers", "Joueurs maximum"),
    GUIS_MAIN_MAX_PLAYERS_LORE("guis.main.maxPlayersLore", "Choisis le nombre maximum de joueurs"),
    GUIS_MAIN_MIN_PLAYERS("guis.main.minPlayers", "Minimum de jouers"),
    GUIS_MAIN_MIN_PLAYERS_LORE("guis.main.minPlayersLore", "Choisis le nombre minimun de jouers"),
    GUIS_MAIN_MIN_PLAYERS_LORE1("guis.main.minPlayersLore1", "Une fois atteint la parti se lance"),
    GUIS_MAIN_PVP("guis.main.pvp", "PvP"),
    GUIS_MAIN_PVP_LORE("guis.main.pvpLore", "Choisis au bout de combien de temps le PvP est activé"),
    GUIS_MAIN_BORDER("guis.main.border", "Bordure"),
    GUIS_MAIN_BORDER_LORE("guis.main.borderLore", "Configuration de la bordure"),
    GUIS_MAIN_START_STUFF("guis.main.startStuff", "Stuff de départ"),
    GUIS_MAIN_START_STUFF_LORE("guis.main.startStuffLore", "Choisis ce que les gens auront au départ"),
    GUIS_MAIN_LITTLE_RULES("guis.main.littleRules", "Petites règles"),
    GUIS_MAIN_LITTLE_RULES_LORE("guis.main.littleRulesLore", "Les préférées de Natsume"),
    GUIS_MAIN_TEAMS("guis.main.teams", "Teams"),
    GUIS_MAIN_TEAMS_LORE("guis.main.teamsLore", "Nombre de joueur par team"),
    GUIS_MAIN_SUDDEN_DEATH("guis.main.suddenDeath", "Mort subite"),
    GUIS_MAIN_SUDDEN_DEATH_LORE("guis.main.suddenDeathLore", "Su **bite** =w="),
    GUIS_MAIN_NETHER("guis.main.nether", "Nether"),
    GUIS_MAIN_NETHER_LORE("guis.main.netherLore", "Celui de la 1.18 pour bien faire chier Kud"),
    GUIS_MAIN_START("guis.main.start", "Commencer"),
    GUIS_MAIN_START_LORE("guis.main.startLore", "Avec 30 min de retard comme d'hab"),
    GUIS_MAIN_STOP("guis.main.stop", "On arrête tout"),
    GUIS_MAIN_LOAD("guis.main.load", "Charger"),
    GUIS_MAIN_LOAD_LORE("guis.main.loadLore", "Charge une config"),
    GUIS_MAIN_SAVE("guis.main.save", "Sauvegarder"),
    GUIS_MAIN_SAVE_LORE("guis.main.saveLore", "Sauvegarde la config actuelle"),
    GUIS_MAIN_GAMEMODE("guis.main.gamemode", "Mode de jeu"),
    GUIS_MAIN_GAMEMODE_LORE("guis.main.gamemodeLore", "Choisis le mode de jeu"),
    GUIS_MAIN_CLOSE("guis.main.close", "Fermer"),
    GUIS_GAMEMODE_NAME("guis.gamemode", "Mode de jeu"),
    GUIS_PETITES_REGLES_NAME("guis.littleRules.eternalDay", "Les petits trucs nuls"),
    GUIS_PETITES_REGLES_ETERNAL_DAY("guis.littleRules.eternalDay", "Jour permanent"),
    GUIS_PETITES_REGLES_AUTOSMELL("guis.littleRules.autosmell", "Mineraux et nourriture sont déjà cuits"),
    GUIS_PETITES_REGLES_COLLISIONS("guis.littleRules.collisions", "Active les collisions entre joueurs dans les équipes"),
    GUIS_PETITES_REGLES_FRIENDLY_FIRE("guis.littleRules.friendlyFire", "Active le friendly fire"),
    GUIS_PETITES_REGLES_HORSELESS("guis.littleRules.horseless", "Impossible de chevaucher les cheveaux"),
    GUIS_PETITES_REGLES_RODLESS("guis.littleRules.rodless", "Banni Road du serveur"),
    GUIS_PETITES_REGLES_WOODCUTTER("guis.littleRules.woodCutter", "Les arbres sont entièrement coupés"),
    GUIS_PETITES_REGLES_FIRELESS("guis.littleRules.fireless", "Désactive les dégâts de feu"),
    GUIS_PETITES_REGLES_REGEN("guis.littleRules.regen", "Active la régen de coeurs"),
    //Gamemodes
    ;

    private String path;
    private String def;
    private static YamlConfiguration LANG;

    Lang(String path, String start) {
        this.path = path;
        this.def = start;
    }

    public static void setFile(YamlConfiguration config) {
        LANG = config;
    }

    @Override
    public String toString() {
        return ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, def));
    }

    public String getDefault() {
        return this.def;
    }

    public String getPath() {
        return this.path;
    }
}
