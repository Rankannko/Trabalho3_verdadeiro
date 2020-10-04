package br.maua.classes.parser;

import br.maua.classes.Anime;
import org.json.JSONArray;
import org.json.JSONObject;
import br.maua.classes.DAO.AnimeDAO;
import org.json.JSONException;

public class AnimeParser {
    private AnimeDAO animeDAO;

    public static Anime parseJson(String json){
        try {
            JSONObject meuJson = new JSONObject(json);
            JSONArray result = meuJson.getJSONArray("results");
            Anime anime = new Anime(
                    ((JSONObject) result.get(0)).getString("url"),
                    ((JSONObject) result.get(0)).getString("title"),
                    ((JSONObject) result.get(0)).getString("synopsis"),
                    ((JSONObject) result.get(0)).getInt("episodes"),
                    ((JSONObject) result.get(0)).getFloat("score")
            );
            return anime;
        }catch(JSONException e){
            System.out.println(e);
        }
        return null;
    }
}
