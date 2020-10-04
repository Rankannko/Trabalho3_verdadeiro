package br.maua.classes.parser;

import br.maua.classes.Anime;
import br.maua.classes.DAO.MangaDAO;
import br.maua.classes.Manga;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MangaParser {
    private MangaDAO mangaDAO;

    public static Manga parseJson(String json){
        try {
            JSONObject meuJson = new JSONObject(json);
            JSONArray result = meuJson.getJSONArray("results");
            Manga manga = new Manga(
                    ((JSONObject) result.get(0)).getString("url"),
                    ((JSONObject) result.get(0)).getString("title"),
                    ((JSONObject) result.get(0)).getString("type"),
                    ((JSONObject) result.get(0)).getString("synopsis"),
                    ((JSONObject) result.get(0)).getInt("chapters"),
                    ((JSONObject) result.get(0)).getInt("volumes"),
                    ((JSONObject) result.get(0)).getFloat("score")
            );
            return manga;
        }catch(JSONException e){
            System.out.println(e);
        }
        return null;
    }
}

