package ica.LB.Core;

import android.content.Context;
import java.io.*;
import java.util.*;

/**
 * Created by jcapuano on 5/17/2014.
 */
public class LbManager {

    private static List<Battle> battles;

	public static void initialize(Context ctx) {
        try {
            LbRepositoryXML.initialize(ctx);
            BattleRepositoryXML.load(ctx.getAssets().open(BattleRepositoryXML.getDatabaseFilePath()));
        } catch (Exception e) {
        }
	}
		
	public static Game getGame(int battleid, int scenarioid) {
		Battle battle = getBattle(battleid);
		Scenario scenario = battle.getScenario(scenarioid);
		Lb saved = LbRepositoryXML.getLb();
		
		return new Game(battle, scenario, saved);
	}
	
	public static void saveGame(Game game) throws FileNotFoundException, IOException
    {
		LbRepositoryXML.saveLb(game.getSaved());
	}
	
	public static List<Battle> getBattles() {
		if (battles == null)
			battles = BattleRepositoryXML.getBattles();
		return battles;
	}

	public static Battle getBattle(int id) {
        List<Battle> l = getBattles();
		for (Battle b : l) {
			if (b.getId() == id)
				return b;
		}
		return null;
	}			
}